package pl.psk.upc.application.order;

import pl.psk.upc.application.client.ClientConverter;
import pl.psk.upc.application.employee.EmployeeConverter;
import pl.psk.upc.application.product.ProductConverter;
import pl.psk.upc.application.service.ServiceConverter;
import pl.psk.upc.infrastructure.entity.OrderEntity;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.order.OrderDtoWrapper;

import java.util.List;

public class OrderConverter {

    public static OrderDto convertFrom(OrderEntity order) {

        return OrderDto.builder()
                .uuid(order.getUuid())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .amount(order.getAmount())
                .employeeDto(EmployeeConverter.convertFrom(order.getEmployeeEntity()))
                .productDtos(ProductConverter.convertFrom(order.getProductEntities())
                        .getContent())
                .service(ServiceConverter.convertFrom(order.getService()))
                .build();
    }

    public static OrderDtoWrapper convertFrom(List<OrderEntity> orders) {
        List<OrderDto> convertedOrders = orders.stream()
                .map(OrderConverter::convertFrom)
                .toList();

        return OrderDtoWrapper.builder()
                .content(convertedOrders)
                .build();
    }

}
