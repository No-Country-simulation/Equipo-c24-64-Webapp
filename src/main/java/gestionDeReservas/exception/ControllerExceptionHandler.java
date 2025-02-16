package gestionDeReservas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "gestionDeReservas.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    protected boolean isHandler(Class<?> beanType) {
        return !beanType.getPackage().getName().startsWith("org.springdoc");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handlerException(Exception e){
        ApiError apiError = ApiError.builder()
                .error("internal_server_error")
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return  ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(RegisterException.class)
    protected ResponseEntity<ApiError> handleRegisterException(RegisterException e){
        ApiError apiError = ApiError.builder()
                .error("register_exception")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ApiError> handlerLoginException(LoginException e) {
        ApiError apiError = ApiError.builder()
                .error("login_exception")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);

    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiError> handlerBadRequestException(BadRequestException e) {
        ApiError apiError = ApiError.builder()
                .error("bad_request")
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ApiError> handlerNotFoundExceptionException(NotFoundException e){
        ApiError apiError = ApiError.builder()
                .error("not_found")
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return  ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}