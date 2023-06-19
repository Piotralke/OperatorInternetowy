package pl.psk.upc.web.integration.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.product.ProductController;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest extends UpcTest {

    @Autowired
    ProductController productController;

    @Test @Transactional
    void shouldReturnAllProductsType() {
        ProductType[] productTypes = ProductType.values();
        ProductType firstProductType = productTypes[0];

        List<String> productsTypesFromApi = productController.getProductsTypes();

        assertEquals(productTypes.length, productsTypesFromApi.size());
        assertEquals(firstProductType.name(), productsTypesFromApi.get(0));
    }

    @Test @Transactional
    void shouldReturnAllProductsWithTvType() {
        ProductDtoWrapper productsByType = productController.getProductsByType(ProductType.TV);
        for (ProductDto product : productsByType.getContent()) {
            assertEquals(product.getProductType(), ProductType.TV);
        }
    }

    @Test @Transactional
    void shouldReturn13Products() {
        ProductDtoWrapper allProducts = productController.getAllProducts();
        assertEquals(13, allProducts.getContent().size());
    }

    @Test @Transactional
    void whenPassUuidShouldReturnProductWithGivenUuid() {
        UUID uuid = UUID.fromString("81ac8656-f3a2-47e2-9db5-01b9e3e99b3e");
        ProductDto product = productController.getProduct(uuid);

        assertEquals(uuid, product.getUuid());
        assertEquals("Sony KD-55X85K", product.getName());
        assertEquals(3999.0, product.getPrice());
        assertEquals(ProductType.TV, product.getProductType());
        assertFalse(product.getDescription().isBlank());
    }

    @Test @Transactional
    void editProduct() {
        ProductDto productDto = productController.getAllProducts().getContent()
                .get(0);

        assertEquals("TP-LINK Deco M4 Mesh WiFi System", productDto.getName());
        assertEquals(408.0, productDto.getPrice());
        assertEquals(ProductType.INTERNET, productDto.getProductType());


        productController.editProduct(productEditRequestDto);
        ProductDto updatedProduct = productController.getProduct(productDto.getUuid());

        assertEquals("TP-LINK Deco M4 Mesh WiFi System", updatedProduct.getName());
        assertEquals(productEditRequestDto.getPrice(), updatedProduct.getPrice());
        assertEquals(productEditRequestDto.getDescription(), updatedProduct.getDescription());
        assertNotEquals(productDto.getDescription(), updatedProduct.getDescription());
        assertEquals(ProductType.INTERNET, updatedProduct.getProductType());
    }

    @Test @Transactional
    void shouldSaveProduct() {
        ProductDtoWrapper allProductsBeforeSave = productController.getAllProducts();
        ProductDto savedProduct = productController.saveProduct(saveProductRequestDto);
        ProductDtoWrapper allProductsAfterSave = productController.getAllProducts();

        assertEquals(allProductsBeforeSave.getContent().size() + 1, allProductsAfterSave.getContent().size());

        assertNotNull(savedProduct.getUuid());
        assertEquals(saveProductRequestDto.getName(), savedProduct.getName());
        assertEquals(saveProductRequestDto.getPrice(), savedProduct.getPrice());
        assertEquals(saveProductRequestDto.getDescription(), savedProduct.getDescription());
        assertEquals(saveProductRequestDto.getProductType(), savedProduct.getProductType());
    }
}