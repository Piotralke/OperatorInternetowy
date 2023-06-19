package pl.psk.upc.web.unit.application.client;

import org.junit.jupiter.api.Test;
import pl.psk.upc.application.client.ClientConverter;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientConverterTest extends UpcTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWithNullMessageWhenArgumentIsNull() {
        boolean isExceptionThrown = false;
        ClientAccountEntity clientAccount = null;

        try {
            ClientConverter.convertFrom(clientAccount);
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("user is null", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldConvertEntityToClientDto() {
        ClientDto clientDto = ClientConverter.convertFrom(clientAccount);

        testClient(clientAccount, clientDto);
    }

    @Test
    void shouldConvertEntityCollection() {
        ClientAccountEntity newClient = ClientAccountEntity.builder()
                .uuid(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .address("address")
                .balance(100)
                .phoneNumber("phoneNumber")
                .nip("nip")
                .pesel("pesel")
                .isBusinessClient(true)
                .build();

        ClientDtoWrapper clientDtoWrapper = ClientConverter.convertFrom(List.of(clientAccount, newClient));

        assertNotNull(clientDtoWrapper);
        assertFalse(clientDtoWrapper.getContent().isEmpty());
        assertEquals(2, clientDtoWrapper.getContent().size());
        testClient(clientAccount, clientDtoWrapper.getContent().get(0));
        testClient(newClient, clientDtoWrapper.getContent().get(1));
    }

    private void testClient(ClientAccountEntity clientAccountEntity, ClientDto clientDto) {
        assertEquals(clientAccountEntity.getUuid(), clientDto.getUuid());
        assertEquals(clientAccountEntity.getFirstName(), clientDto.getFirstName());
        assertEquals(clientAccountEntity.getLastName(), clientDto.getLastName());
        assertEquals(clientAccountEntity.getEmail(), clientDto.getEmail());
        assertEquals(clientAccountEntity.getAddress(), clientDto.getAddress());
        assertEquals(clientAccountEntity.getBalance(), clientDto.getBalance());
        assertEquals(clientAccountEntity.getPhoneNumber(), clientDto.getPhoneNumber());
        assertEquals(clientAccountEntity.getNip(), clientDto.getNip());
        assertEquals(clientAccountEntity.getPesel(), clientDto.getPesel());
        assertEquals(clientAccountEntity.isBusinessClient(), clientDto.isBusinessClient());
    }
}