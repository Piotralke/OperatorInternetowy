package pl.psk.upc.application.order;

import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.order.OrderDtoWrapper;
import pl.psk.upc.web.order.OrderInputDto;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    OrderDtoWrapper getOrdersByClient(UUID clientUuid);
    OrderDtoWrapper getOrdersByClient(String email);
    OrderDto getOrderByUuid(UUID uuid);
    OrderDtoWrapper getOrdersByEmployee(UUID employeeUuid);
    OrderDtoWrapper getOrdersByEmployee(String email);
    UUID saveOrder(OrderInputDto order);
    OrderDtoWrapper getAll();
}
