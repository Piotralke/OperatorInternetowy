package pl.psk.upc.web.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class ContractDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<ContractDto> content;

    @Builder
    public ContractDtoWrapper(@JsonProperty(CONTENT) List<ContractDto> content) {
        this.content = content;
    }

    public List<ContractDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
