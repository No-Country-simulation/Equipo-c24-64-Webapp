package gestionDeReservas.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gestionDeReservas.factory.ErrorFactory;

@ControllerAdvice(basePackages = "gestionDeReservas.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    protected boolean isHandler(Class<?> beanType) {
        return !beanType.getPackage().getName().startsWith("org.springdoc");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handlerException(Exception e){
        ApiError error = ErrorFactory.buildError(e.getClass().toString(), ErrorFactory.SERVER_RROR, 500);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(RegisterException.class)
    protected ResponseEntity<ApiError> handleRegisterException(RegisterException e){
        ApiError error = ErrorFactory.buildError(e.getClass().toString(), ErrorFactory.USER_EXISTS, 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ApiError> handlerLoginException(LoginException e) {
        ApiError error = ErrorFactory.buildError(e.getClass().toString(), ErrorFactory.LOGIN_ERROR, 400);
        return ResponseEntity.status(error.getStatus()).body(error);

    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiError> handlerBadRequestException(BadRequestException e) {
        ApiError error = ErrorFactory.buildError(e.getClass().toString(), ErrorFactory.BAD_REQUEST, 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ApiError> handlerUserNotFoundExceptionException(UserNotFoundException e){
        ApiError error = ErrorFactory.buildError(e.getClass().toString(), ErrorFactory.USER_NOT_FOUND, 404);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(NotRoomFoundException.class)
    protected ResponseEntity<?> NotRoomFoundExceptionException(NotRoomFoundException e){
        ApiError error = ErrorFactory.buildError(e.getClass().toString(), ErrorFactory.ROOM_NOT_FOUND, 404);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}