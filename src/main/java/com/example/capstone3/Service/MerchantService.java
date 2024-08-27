package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Merchant;
import com.example.capstone3.Model.Rating;
import com.example.capstone3.Repository.MerchantRepository;
import com.example.capstone3.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    private final RatingRepository ratingRepository;

    public List<Merchant> getMerchant() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public void updateMerchant(Integer id, Merchant merchant) {
        Merchant m = merchantRepository.findMerchantById(id);
        if(m == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }
        m.setEmail(merchant.getEmail());
        m.setPhone(merchant.getPhone());
        m.setOwnerName(merchant.getOwnerName());
        m.setStoreName(m.getStoreName());
        merchantRepository.save(m);
    }

    public void deleteMerchant(Integer id) {
        Merchant m = merchantRepository.findMerchantById(id);
        if ( m == null) {
            throw new ApiException("Customer with id " + id + " not found");
        }
        merchantRepository.delete(m);
    }

    //Average Rating
    public Double getAverageRatingForMerchant(Integer merchantId) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        List<Rating> ratings = merchant.getRatings();

        if (ratings.isEmpty()) {
            throw new ApiException("there are no ratings for merchant with id " + merchantId);
        }

        int totalRating = 0;
        for (Rating rating : ratings) {
            totalRating += rating.getValue();
        }

        return (double) totalRating / ratings.size();
    }

    //All Ratings
    public List<Rating> getRatingsForMerchant(Integer merchantId) {
        Merchant merchant = merchantRepository.findMerchantById(merchantId);
        return ratingRepository.findRatingByMerchant(merchant);
    }

    public Merchant getMerchantById(Integer id) {
        return merchantRepository.findMerchantById(id);
    }
}
