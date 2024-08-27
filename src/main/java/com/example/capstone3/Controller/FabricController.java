package com.example.capstone3.Controller;
import com.example.capstone3.Model.Fabric;
import com.example.capstone3.Service.FabricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fabric")
@RequiredArgsConstructor
public class FabricController {

    private final FabricService fabricService;

    @GetMapping("/get")
    public ResponseEntity getAllFabric() {
        return ResponseEntity.ok(fabricService.getAllFabric());
    }

    @PostMapping("/add")
    public ResponseEntity addFabric(@RequestBody @Valid Fabric fabric) {
        fabricService.addFabric(fabric);
        return ResponseEntity.status(200).body("Fabric added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Fabric fabric) {
        fabricService.updateFabric(id, fabric);
        return ResponseEntity.status(200).body("Fabric updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        fabricService.deleteFabric(id);
        return ResponseEntity.status(200).body("Fabric deleted");
    }
}