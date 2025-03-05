package gestionDeReservas.exception;

import gestionDeReservas.enums.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gestionDeReservas.factory.ErrorFactory;

@RestControllerAdvice(basePackages = "gestionDeReservas.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    protected boolean isHandler(Class<?> beanType) {
        return !beanType.getPackage().getName().startsWith("org.springdoc");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handlerException(Exception e){
        ApiError error = ErrorFactory.buildError(Error.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), 500);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(RegisterException.class)
    protected ResponseEntity<ApiError> handleRegisterException(RegisterException e){
        ApiError error = ErrorFactory.buildError(Error.REGISTER_EXCEPTION.toString(),e.getMessage(), 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BookingException.class)
    protected ResponseEntity<ApiError> handlerBookingException(BookingException e) {
        ApiError error = ErrorFactory.buildError(Error.BOOKING_EXCEPTION.toString(), e.getMessage(), 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiError> handlerBadRequestException(BadRequestException e) {
        ApiError error = ErrorFactory.buildError(Error.BAD_REQUEST_EXCEPTION.toString(), e.getMessage(), 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ApiError> handlerNotFoundExceptionException(NotFoundException e){
        ApiError error = ErrorFactory.buildError(Error.NOT_FOUND_EXCEPTION.toString(), e.getMessage(), 404);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(DateRangeException.class)
    protected ResponseEntity<?> handleDateRangeException(DateRangeException e){
        ApiError error = ErrorFactory.buildError(Error.DATE_RANGE_EXCEPTION.toString(), e.getMessage(), 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }
    @ExceptionHandler(VisitorEmailException.class)
    protected ResponseEntity<?> handleDuplicatedEmailException(VisitorEmailException e){
        ApiError error = ErrorFactory.buildError(Error.VISITOR_EMAIL_EXCEPTION.toString(),e.getMessage(), 400);
        return ResponseEntity.status(error.getStatus()).body(error);
    }

}