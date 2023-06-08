package pl.psk.upc.web.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class OrderDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<OrderDto> content;

    @Builder
    public OrderDtoWrapper(@JsonProperty(CONTENT) List<OrderDto> content) {
        this.content = content;
    }

    public List<OrderDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
