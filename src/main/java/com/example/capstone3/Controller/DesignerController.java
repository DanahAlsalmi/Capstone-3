package com.example.capstone3.Controller;

import com.example.capstone3.Model.Designer;
import com.example.capstone3.Model.Rating;
import com.example.capstone3.Service.DesignerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/designer")
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllDesigners() {
        return ResponseEntity.status(200).body(designerService.getAllDesigners());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addDesigner(@Valid @RequestBody Designer designer) {
        designerService.addDesigner(designer);
        return ResponseEntity.status(200).body("Designer added successfully");
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateDesigner(@PathVariable Integer id, @Valid @RequestBody Designer designer) {
        designerService.updateDesigner(id, designer);
        return ResponseEntity.status(200).body("Designer updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDesigner(@PathVariable Integer id) {
        designerService.deleteDesigner(id);
        return ResponseEntity.status(200).body("Designer deleted successfully");
    }

    //Average for Designer
    @GetMapping("/average-rating/{id}")
    public ResponseEntity getDesignerAverage(@PathVariable Integer id) {
        Double averageRating = designerService.getAverageRatingForDesigner(id);
        return ResponseEntity.status(200).body("The average is : "+averageRating);
    }

    //List of Ratings
    @GetMapping("/designer-ratings/{designerId}")
    public ResponseEntity getRatingsForDesigner(@PathVariable Integer designerId) {
        List<Rating> ratings = designerService.getRatingsForDesigner(designerId);
        return ResponseEntity.status(200).body(ratings);
    }
}
