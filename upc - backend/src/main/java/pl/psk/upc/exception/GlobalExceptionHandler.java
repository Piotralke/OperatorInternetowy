package pl.psk.upc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(e.getMessage())
                        .date(ZonedDateTime.now(ZoneId.systemDefault()))
                        .responseCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @ExceptionHandler(GenericNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(GenericNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(e.getMessage())
                        .date(ZonedDateTime.now(ZoneId.systemDefault()))
                        .responseCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorDto> handleException(GenericException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(e.getMessage())
                        .date(ZonedDateTime.now(ZoneId.systemDefault()))
                        .responseCode(HttpStatus.BAD_REQUEST.value())
                        .build());
    }

    @ExceptionHandler(NoAccessRightsException.class)
    public ResponseEntity<ErrorDto> handleException(NoAccessRightsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .message(e.getMessage())
                        .date(ZonedDateTime.now(ZoneId.systemDefault()))
                        .responseCode(HttpStatus.FORBIDDEN.value())
                        .build());
    }

}
