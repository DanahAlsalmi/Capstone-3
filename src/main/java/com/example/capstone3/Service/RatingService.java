package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MerchantRepository merchantRepository;
    private final TailorRepository tailorRepository;
    private final DesignerRepository designerRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    //*****  Done by Danah *****

    //Get Ratings
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    //Add Rating
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }

    //Update Rating
    public void updateRating(Integer id , Rating rating) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating with id '" + id + "' not found");
        }
        r.setValue(rating.getValue());
        r.setReview(rating.getReview());
        ratingRepository.save(r);
    }

    //Delete Rating
    public void deleteRating(Integer id) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating with id '" + id + "' not found");
        }
        ratingRepository.delete(r);
    }

    // Save a new rating for a merchant
    public Rating rateMerchant(Integer merchantId,Integer customerId,Rating rating) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        if(merchant == null) {
            throw new ApiException("Merchant with id '" + merchantId + "' not found");
        }
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null) {
            throw new ApiException("Customer with id '" + customerId + "' not found");
        }
        Order order = orderRepository.findOrderByMerchantAndCustom(merchant,customer);
        if(order == null) {
            throw new ApiException("Customer must make order with merchant to rate");
        }
        Rating r = new Rating();
        r.setMerchant(merchant);
        if(rating.getValue()>5){
            throw new ApiException("Rating value must be from 1-5 ");
        }else{
            r.setValue(rating.getValue());
        }
        r.setReview(rating.getReview());
        merchant.getRatings().add(r);
        return ratingRepository.save(r);
    }

    // Save a new rating for a Tailor
    public Rating rateTailor(Integer tailorId,Integer customerId,Rating rating) {
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        if(tailor == null) {
            throw new ApiException("Tailor with id '" + tailorId + "' not found");
        }
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null) {
            throw new ApiException("Customer with id '" + customerId + "' not found");
        }
        Order order = orderRepository.findOrderByTailorAndCustom(tailor,customer);
        if(order == null) {
            throw new ApiException("Customer must make order with tailor to rate");
        }
        Rating r = new Rating();
        r.setTailor(tailor);
        if(rating.getValue()>5){
            throw new ApiException("Rating value must be from 1-5 ");
        }else{
            r.setValue(rating.getValue());
        }
        r.setReview(rating.getReview());
        tailor.getRatings().add(r);
        return ratingRepository.save(r);
    }

    // Save a new rating for a Tailor
    public Rating rateDesigner(Integer designerId,Integer customerId,Rating rating) {
        Designer designer = designerRepository.findDesignerById(designerId);
        if(designer == null) {
            throw new ApiException("Designer with id '" + designerId + "' not found");
        }
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null) {
            throw new ApiException("Customer with id '" + customerId + "' not found");
        }
        Order order = orderRepository.findOrderByDesignerAndCustom(designer,customer);
        if(order == null) {
            throw new ApiException("Customer must make order with designer to rate");
        }

        Rating r = new Rating();
        r.setDesigner(designer);
        if(rating.getValue()>5){
            throw new ApiException("Rating value must be from 1-5 ");
        }else{
            r.setValue(rating.getValue());
        }

        r.setReview(rating.getReview());
        designer.getRatings().add(r);
        return ratingRepository.save(r);
    }

}
