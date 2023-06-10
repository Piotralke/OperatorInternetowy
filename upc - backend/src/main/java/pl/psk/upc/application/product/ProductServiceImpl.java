package pl.psk.upc.application.product;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.infrastructure.repository.ProductRepository;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;
import pl.psk.upc.web.product.ProductEditRequestDto;
import pl.psk.upc.web.product.SaveProductRequestDto;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDtoWrapper getProductsByType(ProductType productType) {
        List<ProductEntity> productsByType = productRepository.findByProductType(productType);
        return ProductConverter.convertFrom(productsByType);
    }

    @Override
    public ProductDtoWrapper getAllProducts() {
        return ProductConverter.convertFrom(productRepository.findAll());
    }

    @Override
    public ProductDto getProduct(UUID uuid) {
        ProductEntity product = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return ProductConverter.convertFrom(product);
    }

    @Override
    public ProductDto editProduct(ProductEditRequestDto productEditRequestDto) {
        ProductEntity product = productRepository.findByUuid(productEditRequestDto.getUuid())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        product.setPrice(productEditRequestDto.getPrice());
        product.setDescription(productEditRequestDto.getDescription());
        return ProductConverter.convertFrom(productRepository.save(product));
    }

    @Override
    public ProductDto saveProduct(SaveProductRequestDto saveProductRequestDto) {
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
