package gestionDeReservas.factory;

import gestionDeReservas.enums.Error;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;

import gestionDeReservas.exception.ApiError;

@Component
public class ErrorFactory {

    @Enumerated(EnumType.STRING)
    Error error;

    public static ApiError buildError(String error, String message, int status){
        return ApiError.builder()
                .error(error)
                .message(message)
                .status(status)
                .build();
    }
}