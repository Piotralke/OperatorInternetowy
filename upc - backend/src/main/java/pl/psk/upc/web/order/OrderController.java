package pl.psk.upc.web.order;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.order.OrderService;
import pl.psk.upc.infrastructure.entity.OrderEntity;
import pl.psk.upc.infrastructure.repository.OrderRepository;
import pl.psk.upc.web.UpcRestPaths;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping(UpcRestPaths.GET_ORDERS_BY_CLIENT_EMAIL)
    public OrderDtoWrapper getOrdersByClientEmail(@RequestParam String email) {
        return orderService.getOrdersByClient(email);
    }

    @GetMapping(UpcRestPaths.GET_ORDERS_BY_CLIENT_UUID)
    public OrderDtoWrapper getOrdersByClientUuid(@PathVariable(value = "uuid") UUID uuid) {
        return orderService.getOrdersByClient(uuid);
    }

    @GetMapping(UpcRestPaths.GET_ORDERS_BY_EMPLOYEE_EMAIL)
    public OrderDtoWrapper getOrdersByEmployeeEmail(@RequestParam String email) {
        return orderService.getOrdersByEmployee(email);
    }

    @GetMapping(UpcRestPaths.GET_ORDERS_BY_EMPLOYEE_UUID)
    public OrderDtoWrapper getOrdersByEmployeeUuid(@PathVariable(value = "uuid") UUID uuid) {
        return orderService.getOrdersByEmployee(uuid);
    }

    @GetMapping(UpcRestPaths.GET_ORDER_BY_UUID)
    public OrderDto getOrder(@PathVariable(value = "uuid") UUID uuid) {
        OrderDto orderByUuid = orderService.getOrderByUuid(uuid);
        return orderByUuid;
    }

    @PostMapping(UpcRestPaths.SAVE_ORDER)
    public UUID saveOrder(@RequestBody OrderInputDto order) {
        return orderService.saveOrder(order);
    }

//    @GetMapping(UpcRestPaths.GET_ALL_ORDERS)
//    public OrderDtoWrapper getAllOrder() {
//        OrderDtoWrapper all = orderService.getAll();
//        return all;
//    }

    @GetMapping(UpcRestPaths.GET_ALL_ORDERS)
    public List<OrderEntity> getAllOrder() {
//        OrderDtoWrapper all = orderService.getAll();
        List<OrderEntity> all = orderRepository.findAll();
        return orderRepository.findAll();
    }
}
