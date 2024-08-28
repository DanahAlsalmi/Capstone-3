package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Order;
import com.example.capstone3.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        Order order = orderRepository.findOrderById(id);
        if (order == null) {
            throw new ApiException("Order not found");
        }
        orderRepository.delete(order);
    }
}
