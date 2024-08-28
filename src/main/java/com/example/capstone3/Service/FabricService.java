package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.FabricInfoDTO;
import com.example.capstone3.Model.Fabric;
import com.example.capstone3.Model.Merchant;
import com.example.capstone3.Model.Order;
import com.example.capstone3.Model.Tailor;
import com.example.capstone3.Repository.FabricRepository;
import com.example.capstone3.Repository.MerchantRepository;
import com.example.capstone3.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FabricService {

    private final FabricRepository fabricRepository;
    private final MerchantRepository merchantRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;



    public List<Fabric> getAllFabric() {
        return fabricRepository.findAll();
    }

    public void addFabric(Integer merchantId,Fabric fabric) {
        Merchant m = merchantRepository.findMerchantById(merchantId);
        if(m == null) {
            throw new ApiException("Merchant with id " + merchantId + " not found");
        }
        fabric.setMerchant(m);
        fabricRepository.save(fabric);
    }

    public void updateFabric(Integer id, Fabric fabric){
        Fabric f = fabricRepository.findFabricById(id);
        if(f == null) {
            throw new ApiException("Fabric with id " + id + " not found");
        }
        f.setColor(fabric.getColor());
        f.setName(fabric.getName());
        f.setPrice(fabric.getPrice());
        f.setType(fabric.getType());
        f.setPattern(fabric.getPattern());
        f.setDescription(fabric.getDescription());
        fabricRepository.save(f);
    }

    public void deleteFabric(Integer id) {
        Fabric f = fabricRepository.findFabricById(id);
        if(f == null) {
            throw new ApiException("Fabric with id " + id + " not found");
        }
        fabricRepository.delete(f);
    }

    public Fabric getFabricById(Integer id) {
        return fabricRepository.findById(id).orElseThrow(() -> new RuntimeException("Fabric not found"));
    }

    public List<Fabric> getFabricByName(String name) {
        return fabricRepository.findFabricByName(name);
    }

    public List<Fabric> getFabricByColor(String color) {
        return fabricRepository.findFabricByColor(color);
    }

    public List<Fabric> getFabricByPattern(String pattern) {
        return fabricRepository.findFabricByPattern(pattern);
    }

    public List<Fabric> getFabricByType(String type) {
        return fabricRepository.findFabricByType(type);
    }
    public List<Fabric> getFabricByPriceRange(double minPrice, double maxPrice) {
        return fabricRepository.findFabricByPriceRange(minPrice, maxPrice);
    }

    public List<Fabric> getFabricByMerchant(Integer merchantId) {
        return fabricRepository.findFabricByMerchantId(merchantId);
    }

    public void assignMerchant(Integer fabricId, Integer merchantId) {
        Fabric f = fabricRepository.findFabricById(fabricId);
        Merchant m = merchantRepository.findMerchantById(merchantId);
        if(f == null || m == null) {
            throw new RuntimeException("can not assign merchant");
        }
        f.setMerchant(m);
        m.getFabrics().add(f);
        fabricRepository.save(f);
        merchantRepository.save(m);
    }

    public String topSellColor(){
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            throw new ApiException("No orders found");
        }
        Map<String, Long> colorCountMap = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getFabric().getColor(), Collectors.counting()));

        // Find the color with the maximum count
        return colorCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())  // Find the color with the highest count
                .map(Map.Entry::getKey)  // Get the color
                .orElseThrow(() -> new ApiException("No fabrics found in orders"));
    }

    //**** Done by danah ****
    public List<FabricInfoDTO> getFabricOrderHistory(Integer fabricId) {
        Fabric fabric = fabricRepository.findFabricById(fabricId);
        if(fabric == null) {
            throw new ApiException("Fabric with id " + fabricId + " not found");
        }

        List<Order> orders = orderRepository.findOrderByFabric(fabric);

        List<FabricInfoDTO> fabricInfoList = new ArrayList<>();
        for (Order order : orders) {
            FabricInfoDTO fabricInfo = new FabricInfoDTO(
                    order.getId(),
                    order.getMerchant().getId(),
                    order.getOrderStatus(),
                    fabric.getName(),
                    fabric.getPrice()
            );
            fabricInfoList.add(fabricInfo);
        }

        return fabricInfoList;
    }
}
