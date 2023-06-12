package pl.psk.upc.web.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.web.contract.ContractDto;

@Value
public class ServiceDto {

    private final static String UUID = "uuid";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String SERVICE_TYPE = "offerType";
    private final static String CONTRACT_DTO = "contract";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(NAME)
    String name;

    @JsonProperty(SERVICE_TYPE)
    OfferType offerType;

    @JsonProperty(CONTRACT_DTO)
    ContractDto contract;

    @Builder
    public ServiceDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(NAME) String name, @JsonProperty(SERVICE_TYPE) OfferType offerType, @JsonProperty(CONTRACT_DTO) ContractDto contract) {
        this.uuid = uuid;
        this.name = name;
        this.offerType = offerType;
        this.contract = contract;
    }
}
