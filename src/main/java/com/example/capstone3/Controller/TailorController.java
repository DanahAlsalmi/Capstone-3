package com.example.capstone3.Controller;

import com.example.capstone3.Model.Tailor;
import com.example.capstone3.Service.TailorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tailor")
@RequiredArgsConstructor
public class TailorController {
    private final TailorService tailorService;

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
}
