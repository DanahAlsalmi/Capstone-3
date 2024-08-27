package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Designer;
import com.example.capstone3.Model.Merchant;
import com.example.capstone3.Model.Rating;
import com.example.capstone3.Model.Tailor;
import com.example.capstone3.Repository.RatingRepository;
import com.example.capstone3.Repository.TailorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TailorService {

    private final TailorRepository tailorRepository;
    private final RatingRepository ratingRepository;

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

    //All Ratings
    public List<Rating> getRatingsForTailor(Integer tailorId) {
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        return ratingRepository.findRatingByTailor(tailor);
    }
}
