package com.Customer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }


    @PostMapping("/new")
    public void addNewOrder(@RequestBody Order order) {
        orderService.addNewOrder(order);
    }


    @PutMapping("/update/{orderId}")
    public void updateOrder(@PathVariable int orderId, @RequestBody Order order) {
        orderService.updateOrder(orderId, order);
    }


    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
    }
}
