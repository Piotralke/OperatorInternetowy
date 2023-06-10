package pl.psk.upc.web.product;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.product.ProductService;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.web.UpcRestPaths;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(UpcRestPaths.GET_PRODUCT_TYPES)
    public List<String> getProductsTypes() {
        return Arrays.stream(ProductType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping(UpcRestPaths.GET_PRODUCTS_BY_TYPE)
    public ProductDtoWrapper getProductsByType(@RequestParam ProductType productType) {
        return productService.getProductsByType(productType);
    }

    @GetMapping(UpcRestPaths.GET_ALL_PRODUCTS)
    public ProductDtoWrapper getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(UpcRestPaths.GET_PRODUCT)
    public ProductDto getProduct(@PathVariable(value = "uuid") UUID uuid) {
        return productService.getProduct(uuid);
    }

    @PutMapping(UpcRestPaths.EDIT_PRODUCT)
    public ProductDto editProduct(@RequestBody ProductEditRequestDto productEditRequestDto) {
        return productService.editProduct(productEditRequestDto);
    }

    @PostMapping(UpcRestPaths.SAVE_PRODUCT)
    public ProductDto saveProduct(@RequestBody SaveProductRequestDto saveProductRequestDto) {
        return productService.saveProduct(saveProductRequestDto);
    }

}
