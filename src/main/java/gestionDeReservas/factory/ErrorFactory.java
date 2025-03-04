package gestionDeReservas.factory;

import org.springframework.stereotype.Component;

import gestionDeReservas.exception.ApiError;

@Component
public class ErrorFactory {
    public static final String NOT_FOUND = "Not found";
    public static final String LOGIN_ERROR = "Login error";
    public static final String ROOM_NOT_FOUND = "Room not found";
    public static final String USER_EXISTS = "User already exists";
    public static final String RESERVATION_FAILED = "Room already reserved";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String SERVER_ERROR = "internal_server_error";
    public static final String DATE_RANGE_EXCEPTION = "range of the dates are invalid";
    public static final String DUPLICATE_EMAIL = "EMAIL ALREADY REGISTER";


    public static ApiError buildError(String error, String message, int status){
        return ApiError.builder()
                .error(error.substring(34))
                .message(message)
                .status(status)
                .build();
    }
}