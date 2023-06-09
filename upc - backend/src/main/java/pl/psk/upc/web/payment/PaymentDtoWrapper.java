package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class PaymentDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<PaymentDto> content;

    @Builder
    public PaymentDtoWrapper(@JsonProperty(CONTENT) List<PaymentDto> content) {
        this.content = content;
    }

    public List<PaymentDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
