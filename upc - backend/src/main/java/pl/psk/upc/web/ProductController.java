package pl.psk.upc.web;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.infrastructure.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(UpcRestPaths.GET_PRODUCT_TYPES)
    public List<String> getProductsTypes() {
        return Arrays.stream(OfferType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping(UpcRestPaths.GET_PRODUCTS_BY_TYPE)
    public List<ProductEntity> getProductsByType(@RequestParam ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    @GetMapping(UpcRestPaths.GET_ALL_PRODUCTS)
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> all = productRepository.findAll();
        return productRepository.findAll();
    }

    @GetMapping(UpcRestPaths.GET_PRODUCT)
    public ProductEntity getProduct(@PathVariable(value = "uuid") UUID uuid) {
        ProductEntity byUuid = productRepository.findByUuid(uuid);
        return productRepository.findByUuid(uuid);
    }

}
