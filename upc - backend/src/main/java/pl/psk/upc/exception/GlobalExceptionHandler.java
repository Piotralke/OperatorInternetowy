package pl.psk.upc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorDto handleException(UsernameNotFoundException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .responseCode(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(GenericNotFoundException.class)
    public ErrorDto handleException(GenericNotFoundException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .responseCode(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(GenericException.class)
    public ErrorDto handleException(GenericException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .responseCode(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(NoAccessRightsException.class)
    public ErrorDto handleException(NoAccessRightsException e) {
        return ErrorDto.builder()
                .message(e.getMessage())
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .responseCode(HttpStatus.FORBIDDEN)
                .build();
    }

}
