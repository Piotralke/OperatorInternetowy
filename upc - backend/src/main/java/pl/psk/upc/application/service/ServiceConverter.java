package pl.psk.upc.application.service;

import pl.psk.upc.application.contract.ContractConverter;
import pl.psk.upc.infrastructure.entity.ServiceEntity;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.service.ServiceDtoWrapper;

import java.util.List;

public class ServiceConverter {

    public static ServiceDto convertFrom(ServiceEntity service) {
        return ServiceDto.builder()
                .uuid(service.getUuid())
                .name(service.getName())
                .offerType(service.getOfferType())
                .contract(ContractConverter.convertFrom(service.getContractEntity()))
                .build();
    }

    public static ServiceDtoWrapper convertFrom(List<ServiceEntity> services) {
        List<ServiceDto> convertedServices = services.stream()
                .map(ServiceConverter::convertFrom)
                .toList();

        return ServiceDtoWrapper.builder()
                .content(convertedServices)
                .build();
    }

}
