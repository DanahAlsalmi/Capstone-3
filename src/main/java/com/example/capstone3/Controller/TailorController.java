package com.example.capstone3.Controller;

import com.example.capstone3.DTO.TailorInfoDTO;
import com.example.capstone3.Model.Rating;
import com.example.capstone3.Model.Tailor;
import com.example.capstone3.Service.TailorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tailor")
@RequiredArgsConstructor
public class TailorController {

    private final TailorService tailorService;

    //***** Done CRUD By Danah *****
    //Get
    @GetMapping("/get")
    public ResponseEntity getAllTailors() {
        return ResponseEntity.status(200).body(tailorService.getAllTailors());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addTailor(@Valid @RequestBody Tailor tailor) {
        tailorService.addTailor(tailor);
        return ResponseEntity.status(200).body("Tailor successfully added");
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateTailor(@PathVariable Integer id, @Valid @RequestBody Tailor tailor) {
        tailorService.updateTailor(id, tailor);
        return ResponseEntity.status(200).body("Tailor successfully updated");
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTailor(@PathVariable Integer id) {
        tailorService.deleteTailor(id);
        return ResponseEntity.status(200).body("Tailor successfully deleted");
    }

    @PutMapping("/accept/{orderId}/{tailorId}")
    public ResponseEntity acceptOrder(@PathVariable Integer orderId, @PathVariable Integer tailorId) {
        tailorService.acceptOrder(orderId, tailorId);
        return ResponseEntity.status(200).body("Order accepted successfully");
    }

    @PutMapping("/reject/{orderId}/{tailorId}")
    public ResponseEntity rejectOrder(@PathVariable Integer orderId, @PathVariable Integer tailorId) {
        tailorService.rejectOrder(orderId, tailorId);
        return ResponseEntity.status(200).body("Order accepted successfully");
    }

    @GetMapping("/total/{tailorId}")
    public ResponseEntity getTotalRevenue(@PathVariable Integer tailorId) {
        return ResponseEntity.status(200).body(tailorService.totalRevenue(tailorId));
    }

    //***** Done CRUD By Danah *****
    //Average for Tailor
    @GetMapping("/average-rating/{id}")
    public ResponseEntity getTailorAverage(@PathVariable Integer id) {
        Double averageRating = tailorService.getAverageRatingForTailor(id);
        return ResponseEntity.status(200).body("The average is : "+averageRating);
    }

    //***** Done CRUD By Danah *****
    //List of Ratings
    @GetMapping("/tailor-ratings/{tailorId}")
    public ResponseEntity getRatingsForTailor(@PathVariable Integer tailorId) {
        List<Rating> ratings = tailorService.getRatingsForTailor(tailorId);
        return ResponseEntity.status(200).body(ratings);
    }

    //***** Done By Danah *****
    @GetMapping("/order-history/{tailorId}")
    public ResponseEntity getTailorOrderHistory(@PathVariable Integer tailorId) {
        List<TailorInfoDTO> tailorInfo = tailorService.getTailorOrderHistory(tailorId);
        return ResponseEntity.status(200).body(tailorInfo);
    }

    //***** Done By Danah *****
    //Top designer
    @GetMapping("/top-designer")
    public ResponseEntity getTopTailor() {
        String topTailor = tailorService.getTopTailorName();
        return ResponseEntity.status(200).body(topTailor);
    }
    @PutMapping("/finish/{orderId}/{tailorId}")
    public ResponseEntity finishOrder(@PathVariable Integer orderId, @PathVariable Integer tailorId) {
        tailorService.finish(orderId, tailorId);
        return ResponseEntity.status(200).body("Order finished successfully");
    }

}
