package com.example.capstone3.Controller;

import com.example.capstone3.Model.Rating;
import com.example.capstone3.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllRatings() {
        return ResponseEntity.status(200).body(ratingService.getAllRatings());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addRating(@Valid @RequestBody Rating rating) {
        ratingService.addRating(rating);
        return ResponseEntity.status(200).body("Rating added successfully");
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateRating(@PathVariable Integer id, @Valid @RequestBody Rating rating) {
        ratingService.updateRating(id, rating);
        return ResponseEntity.status(200).body("Rating updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(200).body("Rating deleted successfully");
    }
}
