package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
public class ClientDtoWrapper {

    private final static String CONTENT = "content";

    @JsonProperty(CONTENT)
    List<ClientDto> content;

    @Builder
    public ClientDtoWrapper(@JsonProperty(CONTENT) List<ClientDto> content) {
        this.content = content;
    }

    public List<ClientDto> getContent() {
        return this.content == null ? Collections.emptyList() : this.content;
    }
}
