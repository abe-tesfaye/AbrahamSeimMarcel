package com.Customer.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order getOrderById(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }


    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }


    public void updateOrder(int orderId, Order order) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get();
            updatedOrder.setName(order.getName());
            updatedOrder.setState(order.getState());
            updatedOrder.setDescription(order.getDescription());
            updatedOrder.setItem(order.getItem());
            updatedOrder.setCategory(order.getCategory());
            updatedOrder.setColor(order.getColor());
            updatedOrder.setTotalAmount(order.getTotalAmount());
            updatedOrder.setSize(order.getSize());
            updatedOrder.setPaymentType(order.getPaymentType());
            updatedOrder.setStatus(order.getStatus());
            updatedOrder.setQuantity(order.getQuantity());
            orderRepository.save(updatedOrder);
        }
    }


    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }
}
