package pl.psk.upc.application.client;

import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientConverter {

    public static ClientDto convertFrom(ClientAccountEntity user) {
        return ClientDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .balance(user.getBalance())
                .accountStatus(user.getAccountStatus())
                .phoneNumber(user.getPhoneNumber())
                .nip(user.getNip())
                .pesel(user.getPesel())
                .orders(user.getOrderEntities())
                .userProblems(user.getUserProblems())
                .services(user.getServices())
                .isBusinessClient(user.isBusinessClient())
                .build();
    }

    public static ClientDtoWrapper convertFrom(List<ClientAccountEntity> users) {
        List<ClientDto> convertedClients = users.stream()
                .map(ClientConverter::convertFrom)
                .toList();

        return ClientDtoWrapper.builder()
                .content(convertedClients)
                .build();
    }

}
