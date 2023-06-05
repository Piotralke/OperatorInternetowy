package pl.psk.upc.web.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class WarehouseDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<WarehouseDto> content;

    @Builder
    public WarehouseDtoWrapper(@JsonProperty(CONTENT) List<WarehouseDto> content) {
        this.content = content;
    }

    public List<WarehouseDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
