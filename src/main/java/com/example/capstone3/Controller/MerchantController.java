package com.example.capstone3.Controller;

import com.example.capstone3.Model.Merchant;
import com.example.capstone3.Model.Rating;
import com.example.capstone3.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchant() {
        return ResponseEntity.ok(merchantService.getMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant) {
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant) {
        merchantService.updateMerchant(id, merchant);
        return ResponseEntity.status(200).body("Merchant updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.status(200).body("Merchant deleted");
    }

    //Average for Merchant
    @GetMapping("/average-rating/{id}")
    public ResponseEntity getMerchantAverage(@PathVariable Integer id) {
        Double averageRating = merchantService.getAverageRatingForMerchant(id);
        return ResponseEntity.status(200).body("The average is : "+averageRating);
    }

    //List of Ratings
    @GetMapping("/merchant-ratings/{merchantId}")
    public ResponseEntity getRatingsForMerchant(@PathVariable Integer merchantId) {
        List<Rating> ratings = merchantService.getRatingsForMerchant(merchantId);
        return ResponseEntity.status(200).body(ratings);
    }

}
