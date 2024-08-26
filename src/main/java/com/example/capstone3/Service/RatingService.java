package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Rating;
import com.example.capstone3.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

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
}
