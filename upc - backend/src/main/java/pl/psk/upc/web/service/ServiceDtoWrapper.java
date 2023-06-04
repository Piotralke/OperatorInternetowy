package pl.psk.upc.web.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class ServiceDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<ServiceDto> content;

    @Builder
    public ServiceDtoWrapper(@JsonProperty(CONTENT) List<ServiceDto> content) {
        this.content = content;
    }

    public List<ServiceDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }

}
