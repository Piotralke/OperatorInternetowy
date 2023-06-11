package pl.psk.upc.application.product;

import org.springframework.stereotype.Service;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.infrastructure.repository.ProductRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;
import pl.psk.upc.web.product.ProductEditRequestDto;
import pl.psk.upc.web.product.SaveProductRequestDto;

import java.util.List;
import java.util.UUID;

@Service
class ProductServiceImpl implements ProductService {
    private final static String NOT_FOUND_MESSAGE = "Product not found";

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDtoWrapper getProductsByType(ProductType productType) {
        MethodArgumentValidator.requiredNotNullEnum(productType, "productType");
        List<ProductEntity> productsByType = productRepository.findByProductType(productType);
        return ProductConverter.convertFrom(productsByType);
    }

    @Override
    public ProductDtoWrapper getAllProducts() {
        return ProductConverter.convertFrom(productRepository.findAll());
    }

    @Override
    public ProductDto getProduct(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        ProductEntity product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        return ProductConverter.convertFrom(product);
    }

    @Override
    public ProductDto editProduct(ProductEditRequestDto productEditRequestDto) {
        MethodArgumentValidator.requiredNotNull(productEditRequestDto, "productEditRequestDto");
        ProductEntity product = productRepository.findByUuid(productEditRequestDto.getUuid())
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        product.setPrice(productEditRequestDto.getPrice());
        product.setDescription(productEditRequestDto.getDescription());
        return ProductConverter.convertFrom(productRepository.save(product));
    }

    @Override
    public ProductDto saveProduct(SaveProductRequestDto saveProductRequestDto) {
        MethodArgumentValidator.requiredNotNull(saveProductRequestDto, "saveProductRequestDto");
        ProductEntity newProduct = ProductEntity.builder()
                .uuid(UUID.randomUUID())
                .name(saveProductRequestDto.getName())
                .price(saveProductRequestDto.getPrice())
                .description(saveProductRequestDto.getDescription())
                .productType(saveProductRequestDto.getProductType())
                .build();
        return ProductConverter.convertFrom(productRepository.save(newProduct));
    }
}
