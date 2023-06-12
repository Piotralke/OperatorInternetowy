package pl.psk.upc.application.employee;

import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.web.user.EmployeeDto;
import pl.psk.upc.web.user.EmployeeWrapper;

import java.util.List;

public class EmployeeConverter {

    public static EmployeeDto convertFrom(EmployeeEntity user) {
        if (user == null || user.getUuid() == null) {
            return EmployeeDto.builder().build();
        }
        return EmployeeDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
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
