package pl.psk.upc.application.product;

import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.infrastructure.repository.ProductRepository;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.product.ProductDtoWrapper;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

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
        ProductEntity product = productRepository.findByUuid(uuid);
        return ProductConverter.convertFrom(product);
    }
}
