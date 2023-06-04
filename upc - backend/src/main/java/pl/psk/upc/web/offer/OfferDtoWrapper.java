package pl.psk.upc.web.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class OfferDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<OfferDto> content;

    @Builder
    public OfferDtoWrapper(@JsonProperty(CONTENT) List<OfferDto> content) {
        this.content = content;
    }

    public List<OfferDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
