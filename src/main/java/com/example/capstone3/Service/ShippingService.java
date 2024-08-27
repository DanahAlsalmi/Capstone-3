package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ShippingDTO;
import com.example.capstone3.Model.Order;
import com.example.capstone3.Model.Shipping;
import com.example.capstone3.Repository.OrderRepository;
import com.example.capstone3.Repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final ShippingRepository shippingRepository;
    private final OrderRepository orderRepository;

    public List<Shipping> getAllShipping(){
        return shippingRepository.findAll();
    }

    public void addShipping(ShippingDTO shippingDTO) {
        Order o = orderRepository.findOrderById(shippingDTO.getOrderId());
        if (o == null) {
            throw new ApiException("Order not found");
        }
        Shipping shipping = new Shipping(null,shippingDTO.getShipperName(),shippingDTO.getPrice(),shippingDTO.getStatus(),o);
        shippingRepository.save(shipping);
    }

    public void deleteShipping(Integer id){
        Shipping shipping = shippingRepository.findShippingById(id);
        if(shipping == null){
            throw new ApiException("Shipping not found");
        }
        shippingRepository.delete(shipping);
    }
}