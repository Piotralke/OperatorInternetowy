package pl.psk.upc.web.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.web.UpcTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ProductControllerTest extends UpcTest {

    private final SaveProductRequestDto saveProductRequestDto = SaveProductRequestDto.builder()
            .name("Testowy produkt")
            .price(200.0)
            .description("testowy opis produktu")
            .productType(ProductType.DEVICE)
            .build();

    private final ProductEditRequestDto productEditRequestDto = ProductEditRequestDto.builder()
            .uuid(UUID.fromString("153b4547-494b-4288-a3f0-f500868601e7"))
            .price(619.0)
            .description("New description")
            .build();

    @Autowired
    ProductController productController;

    @Test
    void shouldReturnAllProductsType() {
        ProductType[] productTypes = ProductType.values();
        ProductType firstProductType = productTypes[0];

        List<String> productsTypesFromApi = productController.getProductsTypes();

        assertEquals(productTypes.length, productsTypesFromApi.size());
        assertEquals(firstProductType.name(), productsTypesFromApi.get(0));
    }

    @Test
    void shouldReturnAllProductsWithTvType() {
        ProductDtoWrapper productsByType = productController.getProductsByType(ProductType.TV);
        for (ProductDto product : productsByType.getContent()) {
            assertEquals(product.getProductType(), ProductType.TV);
        }
    }

    @Test
    void shouldReturn39Products() {
        ProductDtoWrapper allProducts = productController.getAllProducts();
        assertEquals(39, allProducts.getContent().size());
    }

    @Test
    void whenPassUuidShouldReturnProductWithGivenUuid() {
        UUID uuid = UUID.fromString("46b742de-eb35-4c12-873f-8471b0c30cf2");
        ProductDto product = productController.getProduct(uuid);

        assertEquals(uuid, product.getUuid());
        assertEquals("Xiaomi POCO X5 Pro 5G 8/256GB", product.getName());
        assertEquals(1799.0, product.getPrice());
        assertEquals(ProductType.MOBILE, product.getProductType());
        assertFalse(product.getDescription().isBlank());
    }

    @Test
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

    @Test
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