package gestionDeReservas.factory;



import org.springframework.stereotype.Component;

import gestionDeReservas.exception.ApiError;



@Component
public class ErrorFactory {
    

    public static final String NOT_FOUND = "Not found";
    public static final String LOGIN_ERROR = "Login error";
    public static final String ROOM_NOT_FOUND = "Room not found";
    public static final String RESERVATION_NOT_FOUND = "Reservation not found";
    public static final String USER_NOT_FOUND = "User not found";

    public static ApiError buildError(String error, String message, int status){
        return ApiError.builder()
                .error(error)
                .message(message)
                .status(status)
                .build();
    }

}
