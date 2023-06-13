package pl.psk.upc.web;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/upc",
        "spring.datasource.username=postgres",
        "spring.datasource.password=dcfc107313a64a2c85aff761e0676313"
})
public class UpcTest {

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
                .withDatabaseName("upc")
            .withUsername("postgres")
            .withPassword("dcfc107313a64a2c85aff761e0676313");
        container
                .withInitScript("data.sql");
        container.start();
    }

}
