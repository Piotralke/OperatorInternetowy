package pl.psk.upc.application.warehouse;

import pl.psk.upc.application.product.ProductConverter;
import pl.psk.upc.infrastructure.entity.WarehouseEntity;
import pl.psk.upc.web.warehouse.WarehouseDto;
import pl.psk.upc.web.warehouse.WarehouseDtoWrapper;

import java.util.List;

public class WarehouseConverter {

    public static WarehouseDto convertFrom(WarehouseEntity warehouse) {
        return WarehouseDto.builder()
                .uuid(warehouse.getUuid())
                .productName(warehouse.getProductName())
                .quantity(warehouse.getQuantity())
                .productDto(ProductConverter.convertFrom(warehouse.getProductEntity()))
                .build();
    }

    public static WarehouseDtoWrapper convertFrom(List<WarehouseEntity> warehouses) {
        List<WarehouseDto> convertedWarehouses = warehouses.stream()
                .map(WarehouseConverter::convertFrom)
                .toList();

        return WarehouseDtoWrapper.builder()
                .content(convertedWarehouses)
                .build();
    }

}
