package pl.psk.upc.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Value
public class ErrorDto {

    private final static String MESSAGE = "message";
    private final static String DATE = "date";
    private final static String RESPONSE_CODE = "responseCode";

    @JsonProperty(MESSAGE)
    String message;

    @JsonProperty(DATE)
    ZonedDateTime date;

    @JsonProperty(RESPONSE_CODE)
    int responseCode;

    @Builder
    public ErrorDto(String message, ZonedDateTime date, int responseCode) {
        this.message = message;
        this.date = date;
        this.responseCode = responseCode;
    }
}
