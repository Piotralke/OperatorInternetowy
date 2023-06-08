package pl.psk.upc.application.product;

import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;

import java.util.List;

public class ProductConverter {

    public static ProductDto convertFrom(ProductEntity product) {
        if (product == null) {
            return ProductDto.builder().build();
        }
        return ProductDto.builder()
                .uuid(product.getUuid())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .productType(product.getProductType())
                .build();
    }

    public static ProductDtoWrapper convertFrom(List<ProductEntity> products) {
        List<ProductDto> convertedProducts = products.stream()
                .map(ProductConverter::convertFrom)
                .toList();

        return ProductDtoWrapper.builder()
                .content(convertedProducts)
                .build();
    }

}
