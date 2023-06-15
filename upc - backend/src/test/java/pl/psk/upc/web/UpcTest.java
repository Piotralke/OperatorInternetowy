package pl.psk.upc.web;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.enums.ContractForm;
import pl.psk.upc.infrastructure.enums.ContractLengthEnum;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.web.notice.SaveNoticeRequestDto;
import pl.psk.upc.web.offer.SaveOfferRequestDto;
import pl.psk.upc.web.offer.SaveProductWithOfferRequestDto;
import pl.psk.upc.web.order.OrderInputDto;
import pl.psk.upc.web.product.ProductEditRequestDto;
import pl.psk.upc.web.product.SaveProductRequestDto;
import pl.psk.upc.web.user.ClientEditRequestDto;
import pl.psk.upc.web.user.ClientRegisterRequestDto;
import pl.psk.upc.web.user.EmployeeEditRequestDto;
import pl.psk.upc.web.user.EmployeeRegisterRequestDto;
import pl.psk.upc.web.userproblem.UserProblemInputDto;

import java.util.List;
import java.util.UUID;


@Testcontainers
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/upcTest",
        "spring.datasource.username=postgres",
        "spring.datasource.password=dcfc107313a64a2c85aff761e0676313"
})
public class UpcTest {

    public final SaveProductWithOfferRequestDto saveProductWithOfferRequestDto = SaveProductWithOfferRequestDto.builder()
            .name("New test device")
            .price(70.0)
            .description("New test device for test offer")
            .productType(ProductType.DEVICE)
            .build();

    public final SaveOfferRequestDto saveOfferRequestDtoWithDevice = SaveOfferRequestDto.builder()
            .name("New test offer with device")
            .description("New test offer - description")
            .price(59.0)
            .withDevice(true)
            .saveProductWithOfferRequestDto(saveProductWithOfferRequestDto)
            .offerType(OfferType.TV)
            .build();

    public final SaveOfferRequestDto saveOfferRequestDtoWithoutDevice = SaveOfferRequestDto.builder()
            .name("New test offer with device")
            .description("New test offer - description")
            .price(59.0)
            .withDevice(true)
            .offerType(OfferType.TV)
            .build();

    public final UserProblemInputDto userProblemInputDto = UserProblemInputDto.builder()
            .description("Testowy problem")
            .email("tynisha.bergstrom@gmail.com")
            .build();

    public final UserProblemInputDto userProblemInputDto2 = UserProblemInputDto.builder()
            .description("Testowy problem2")
            .email("tynisha.bergstrom@gmail.com")
            .build();

    public final OrderInputDto clientOrderInputDtoWithoutProducts = OrderInputDto.builder()
            .clientEmail("tynisha.bergstrom@gmail.com")
            .offerUuid(UUID.fromString("f5924b75-347b-44de-9705-702109da3ff2"))
            .contractLength(ContractLengthEnum.TWELVE)
            .build();

    public final OrderInputDto clientOrderInputDtoWithProducts = OrderInputDto.builder()
            .clientEmail("tynisha.bergstrom@gmail.com")
            .productUuids(List.of(UUID.fromString("116d7422-d34a-41cf-9db2-5838fc2b60c7"),
                    UUID.fromString("23e0061b-99d4-4540-8432-43f7055fce5f")))
            .offerUuid(UUID.fromString("f5924b75-347b-44de-9705-702109da3ff2"))
            .contractLength(ContractLengthEnum.TWELVE)
            .build();

    public final OrderInputDto employeeOrderInputDto = OrderInputDto.builder()
            .clientEmail("tynisha.bergstrom@gmail.com")
            .employeeEmail("jarod.howell@yahoo.com")
            .productUuids(List.of(UUID.fromString("116d7422-d34a-41cf-9db2-5838fc2b60c7"),
                    UUID.fromString("23e0061b-99d4-4540-8432-43f7055fce5f")))
            .offerUuid(UUID.fromString("f5924b75-347b-44de-9705-702109da3ff2"))
            .contractLength(ContractLengthEnum.TWELVE)
            .build();


    public final ClientAccountEntity clientAccount = ClientAccountEntity.builder()
            .id(1L)
            .uuid(UUID.fromString("522346b2-acf8-449d-99f5-c10f491f3658"))
            .firstName("Darrell")
            .lastName("Schmidt")
            .email("tynisha.bergstrom@gmail.com")
            .password("client0")
            .address("64640 Upton Spurs, Port Cliffview, GA 27920")
            .balance(0.0)
            .roles("USER")
            .phoneNumber("1-310-276-7229")
            .isBusinessClient(false)
            .build();

    public final SaveNoticeRequestDto saveNoticeRequestDto = SaveNoticeRequestDto.builder()
            .description("Notice test")
            .clientsUuid(List.of(clientAccount.getUuid()))
            .build();

    public final SaveProductRequestDto saveProductRequestDto = SaveProductRequestDto.builder()
            .name("Testowy produkt")
            .price(200.0)
            .description("testowy opis produktu")
            .productType(ProductType.DEVICE)
            .build();

    public final ProductEditRequestDto productEditRequestDto = ProductEditRequestDto.builder()
            .uuid(UUID.fromString("e3154cab-640e-49cf-9b7e-f537578a72be"))
            .price(619.0)
            .description("New description")
            .build();

    public final ClientRegisterRequestDto clientRegisterRequestDto = ClientRegisterRequestDto.builder()
            .firstName("testowe imię")
            .lastName("testowe nazwisko")
            .email("test@wp.pl")
            .password("test")
            .address("Testowy adres")
            .phoneNumber("661415528")
            .nip("1111111111")
            .pesel("11111111111")
            .isBusinessClient(true)
            .build();

    public final EmployeeRegisterRequestDto employeeRegisterRequestDto = EmployeeRegisterRequestDto.builder()
            .firstName("testowe imię")
            .lastName("testowe nazwisko")
            .email("test@wp.pl")
            .password("test")
            .address("Testowy adres")
            .workplace("Kielce")
            .salary(6000.0)
            .contractForm(ContractForm.B2B)
            .phoneNumber("661415528")
            .nip("1111111111")
            .pesel("11111111111")
            .build();

    public final EmployeeEditRequestDto employeeEditRequestDto = EmployeeEditRequestDto.builder()
            .uuid(UUID.fromString("53bd49ca-9599-4c9a-b179-9c6d597845b5"))
            .firstName("testowe imię")
            .lastName("testowe nazwisko")
            .password("test")
            .address("Testowy adres")
            .workplace("Kielce")
            .salary(6000.0)
            .contractForm(ContractForm.PERMANENT)
            .phoneNumber("661415528")
            .nip("1111111111")
            .build();

    public final ClientEditRequestDto clientEditRequestDto = ClientEditRequestDto.builder()
            .uuid(UUID.fromString("522346b2-acf8-449d-99f5-c10f491f3658"))
            .firstName("testowe imię")
            .lastName("testowe nazwisko")
            .password("test")
            .address("Testowy adres")
            .balance(4321.0)
            .phoneNumber("661415528")
            .nip("1111111111")
            .isBusinessClient(true)
            .build();

    @BeforeAll
    public static void setEnvironmentVariables() {
        System.setProperty("paypal.clientId", "AaqDntfE_PTyQabo-E_Uw43YeK8lNu4wP3FuMji7UGbdrQkqIeJTv5LOOGl1yCYVK9naOp-N1uCo__bc");
        System.setProperty("paypal.clientSecret", "EHrKW84WZ3y3uYns0kBYa9tWRHRNWh6f4nIdEIMjqwJ4hBzoslf18fLLlloWf56NqfQfCHYWruWOWjh-");
        System.setProperty("paypal.mode", "sandbox");
    }

    @Container
    private static final PostgreSQLContainer<?> container;

    static {
        container = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("upcTest")
            .withUsername("postgres")
            .withPassword("dcfc107313a64a2c85aff761e0676313");
        container.start();
    }

}
