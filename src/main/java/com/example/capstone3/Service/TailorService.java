package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.FabricInfoDTO;
import com.example.capstone3.DTO.TailorInfoDTO;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.OrderRepository;
import com.example.capstone3.Repository.RatingRepository;
import com.example.capstone3.Repository.TailorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TailorService {

    private final TailorRepository tailorRepository;
    private final OrderRepository orderRepository;
    private final RatingRepository ratingRepository;

    //***** CRUD Done by Danah *****
    //Get Tailors
    public List<Tailor> getAllTailors() {
        return tailorRepository.findAll();
    }

    //Add Tailor
    public void addTailor(Tailor tailor) {
        tailorRepository.save(tailor);
    }

    //Update Tailor
    public void updateTailor(Integer id,Tailor tailor) {
        Tailor t = tailorRepository.findTailorById(id);
        if (t == null) {
            throw new ApiException("Tailor with id '" + id + "' not found");
        }
        t.setName(tailor.getName());
        t.setEmail(tailor.getEmail());
        t.setPhone(tailor.getPhone());
        t.setAcceptOrder(tailor.isAcceptOrder());
        t.setPriceByMeter(tailor.getPriceByMeter());
        tailorRepository.save(t);
    }

    //Delete Tailor
    public void deleteTailor(Integer id) {
        Tailor t = tailorRepository.findTailorById(id);
        if (t == null) {
            throw new ApiException("Tailor with id '" + id + "' not found");
        }
        tailorRepository.delete(t);
    }

    public void acceptOrder(Integer orderId,Integer tailorId) {
        Order o = orderRepository.findOrderById(orderId);
        if (o==null){
            throw new ApiException("Order not found");
        }
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        if(tailor==null) {
            throw new ApiException("Tailor not found");
        }
        if (!o.getTailor().getId().equals(tailorId)){
            throw new ApiException("Tailor with id '" + tailorId + "' does not belong to this order");
        }
        if (!o.getOrderStatus().equals("Processing in Designer")){
            throw new ApiException("Order status is not Pending");
        }
        o.setOrderStatus("Processing in Tailor");
        orderRepository.save(o);
    }

    public void rejectOrder(Integer orderId,Integer tailorId) {
        Order o = orderRepository.findOrderById(orderId);
        if (o==null){
            throw new ApiException("Order not found");
        }
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        if(tailor==null) {
            throw new ApiException("Tailor not found");
        }
        if (!o.getTailor().getId().equals(tailorId)){
            throw new ApiException("Tailor with id '" + tailorId + "' does not belong to this order");
        }
        if (!o.getOrderStatus().equals("Processing in Designer")){
            throw new ApiException("Order status is not Pending");
        }
        o.setOrderStatus("Reject By Tailor");
        orderRepository.save(o);
    }

    //***** Done by Danah *****
    //Get Average Rating
    public Double getAverageRatingForTailor(Integer tailorId) {
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        Set<Rating> ratings = tailor.getRatings();

        if (ratings.isEmpty()) {
            throw new ApiException("there are no ratings for merchant with id " + tailorId);
        }

        int totalRating = 0;
        for (Rating rating : ratings) {
            totalRating += rating.getValue();
        }

        return (double) totalRating / ratings.size();
    }

    //***** Done by Danah *****
    //All Ratings
    public List<Rating> getRatingsForTailor(Integer tailorId) {
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        return ratingRepository.findRatingByTailor(tailor);
    }

    public List<TailorInfoDTO> getTailorOrderHistory(Integer tailorId) {
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        if (tailor == null) {
            throw new ApiException("Tailor with id '" + tailorId + "' not found");
        }

        List<Order> orders = orderRepository.findOrderByTailor(tailor);

        List<TailorInfoDTO> tailorInfoList = new ArrayList<>();
        for (Order order : orders) {
            TailorInfoDTO tailorInfo = new TailorInfoDTO(
                    order.getId(),
                    order.getOrderStatus(),
                    tailor.getPriceByMeter()
            );
            tailorInfoList.add(tailorInfo);
        }

        return tailorInfoList;
    }



}
