package pl.psk.upc.web.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class ProductDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<ProductDto> content;

    @Builder
    public ProductDtoWrapper(@JsonProperty(CONTENT) List<ProductDto> content) {
        this.content = content;
    }

    public List<ProductDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }


}
