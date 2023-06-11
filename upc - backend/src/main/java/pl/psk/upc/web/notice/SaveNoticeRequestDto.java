package pl.psk.upc.web.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Value
public class SaveNoticeRequestDto {

    private final static String DESCRIPTION = "description";
    private final static String CLIENTS = "clientsUuid";

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(CLIENTS)
    List<UUID> clientsUuid;

    @Builder
    public SaveNoticeRequestDto(String description, List<UUID> clientsUuid) {
        this.description = description;
        this.clientsUuid = clientsUuid;
    }

    public List<UUID> getClientsUuid() {
        return this.clientsUuid == null ? Collections.emptyList() : this.clientsUuid;
    }
}
