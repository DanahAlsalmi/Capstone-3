package com.example.capstone3.Repository;

import com.example.capstone3.Model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Rating findRatingById(Integer id);
}
