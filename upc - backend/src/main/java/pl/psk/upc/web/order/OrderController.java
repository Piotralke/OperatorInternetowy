package pl.psk.upc.web.order;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.order.OrderService;
import pl.psk.upc.infrastructure.enums.OrderStatus;
import pl.psk.upc.web.UpcRestPaths;

import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
        return orderService.getOrderByUuid(uuid);
    }

    @PostMapping(UpcRestPaths.SAVE_ORDER)
    public UUID saveOrder(@RequestBody OrderInputDto order) {
        return orderService.saveOrder(order);
    }

    @PutMapping()
    public UUID editOrderStatus(@PathVariable(value = "uuid") UUID uuid, @RequestParam OrderStatus orderStatus) {
        return orderService.updateOrderStatus(uuid, orderStatus);
    }

}
