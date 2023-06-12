package pl.psk.upc.application.client;

import pl.psk.upc.application.order.OrderConverter;
import pl.psk.upc.application.service.ServiceConverter;
import pl.psk.upc.application.userproblem.UserProblemConverter;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;

import java.util.List;

public class ClientConverter {

    public static ClientDto convertFrom(ClientAccountEntity user) {
        return ClientDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .balance(user.getBalance())
                .phoneNumber(user.getPhoneNumber())
                .nip(user.getNip())
                .pesel(user.getPesel())
                .orders(OrderConverter.convertFrom(user.getOrderEntities()).getContent())
                .userProblems(UserProblemConverter.convertFrom(user.getUserProblems()).getContent())
                .services(ServiceConverter.convertFrom(user.getServices()).getContent())
                .isBusinessClient(user.isBusinessClient())
                .build();
    }

    public static ClientDtoWrapper convertFrom(List<ClientAccountEntity> users) {
        MethodArgumentValidator.requiredNotNullOrEmptyCollection(users, "users");
        List<ClientDto> convertedClients = users.stream()
                .map(ClientConverter::convertFrom)
                .toList();

        return ClientDtoWrapper.builder()
                .content(convertedClients)
                .build();
    }

}
