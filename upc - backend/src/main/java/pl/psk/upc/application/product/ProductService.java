package pl.psk.upc.application.product;

import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;
import pl.psk.upc.web.product.ProductEditRequestDto;
import pl.psk.upc.web.product.SaveProductRequestDto;

import java.util.UUID;

public interface ProductService {
    ProductDtoWrapper getProductsByType(ProductType productType);
    ProductDtoWrapper getAllProducts();
    ProductDto getProduct(UUID uuid);
    ProductDto editProduct(ProductEditRequestDto productEditRequestDto);
    ProductDto saveProduct(SaveProductRequestDto saveProductRequestDto);
}
