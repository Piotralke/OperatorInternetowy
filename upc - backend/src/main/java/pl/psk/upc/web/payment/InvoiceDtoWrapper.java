package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class InvoiceDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<InvoiceDto> content;

    @Builder
    public InvoiceDtoWrapper(@JsonProperty(CONTENT) List<InvoiceDto> content) {
        this.content = content;
    }

    public List<InvoiceDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
