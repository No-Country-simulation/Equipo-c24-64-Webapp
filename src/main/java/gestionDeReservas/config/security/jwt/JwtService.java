package gestionDeReservas.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtService {
    // CAMBIAR ESTO ESTA HORRIBLE
     static final String SECRET_KEY = "fHjqZ9LwR3vC7nKy2tXsD4mW8bN6pVeAxJ5cGhUz1rM0dFBoOQlIuEeYmPiS";
     final Map<String, Date> blacklist = new HashMap<>();
     ScheduledExecutorService scheduler;

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
                && !isTokenBlacklisted(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim (String token, Function<Claims,T> claimsResolver){
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    public void addToBlacklist(String token) {
        Date expiration = getExpiration(token);
        blacklist.put(token, expiration);
    }

    public boolean isTokenBlacklisted(String token) {
        if (blacklist.containsKey(token)) {
            Date expiration = blacklist.get(token);
            if (expiration.before(new Date())) {
                blacklist.remove(token);
                return false;
            }
            return true;
        }
        return false;
    }

    public void cleanExpiredTokens() {
        Date now = new Date();
        blacklist.entrySet().removeIf(entry -> entry.getValue().before(now));
    }

    @PostConstruct
    public void scheduleTokenCleanup() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::cleanExpiredTokens, 0, 1, TimeUnit.DAYS);
    }
}