package pl.psk.upc.application.employee;

import pl.psk.upc.application.client.ClientConverter;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;
import pl.psk.upc.web.user.EmployeeDto;
import pl.psk.upc.web.user.EmployeeWrapper;

import java.util.List;

public class EmployeeConverter {

    public static EmployeeDto convertFrom(EmployeeEntity user) {
        return EmployeeDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .accountStatus(user.getAccountStatus())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .workplace(user.getWorkplace())
                .salary(user.getSalary())
                .contractForm(user.getContractForm())
                .nip(user.getNip())
                .pesel(user.getPesel())
                .build();
    }

    public static EmployeeWrapper convertFrom(List<EmployeeEntity> users) {
        List<EmployeeDto> convertedEmployees = users.stream()
                .map(EmployeeConverter::convertFrom)
                .toList();

        return EmployeeWrapper.builder()
                .content(convertedEmployees)
                .build();
    }

}
