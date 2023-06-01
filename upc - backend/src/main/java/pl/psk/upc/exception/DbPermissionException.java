package pl.psk.upc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DbPermissionException extends RuntimeException {
    public DbPermissionException(String message) {
        super(message);
    }
}
