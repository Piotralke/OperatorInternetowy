package pl.psk.upc.web;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.web.notice.SaveNoticeRequestDto;
import pl.psk.upc.web.product.ProductEditRequestDto;
import pl.psk.upc.web.product.SaveProductRequestDto;

import java.util.List;
import java.util.UUID;


@Testcontainers
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/upcTest",
        "spring.datasource.username=postgres",
        "spring.datasource.password=dcfc107313a64a2c85aff761e0676313"
})
public class UpcTest {

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
//        container
//                .withInitScript("data.sql");
        container.start();
    }

}
