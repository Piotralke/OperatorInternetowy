package pl.psk.upc.web.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.product.ProductDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WarehouseControllerTest extends UpcTest {

    @Autowired
    WarehouseController warehouseController;

    @Test
    @Transactional
    void getAll() {
        WarehouseDtoWrapper all = warehouseController.getAll();

        assertEquals(13, all.getContent().size());
    }

    @Test
    @Transactional
    void getByUuid() {
        WarehouseDto byUuid = warehouseController.getByUuid(UUID.fromString("e3154cab-640e-49cf-9b7e-f537578a72be"));

        assertNotNull(byUuid.getUuid());
        assertEquals(17, byUuid.getQuantity());
        assertEquals(PRODUCT_NAME, byUuid.getProductName());

        ProductDto productDto = byUuid.getProductDto();
        assertEquals(UUID.fromString("e3154cab-640e-49cf-9b7e-f537578a72be"), productDto.getUuid());
        assertEquals(408, productDto.getPrice());
        assertEquals(ProductType.INTERNET, productDto.getProductType());
        assertEquals(PRODUCT_NAME, productDto.getName());
    }
}