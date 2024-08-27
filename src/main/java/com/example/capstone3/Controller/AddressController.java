package com.example.capstone3.Controller;


import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.DTO.AddressDTO;
import com.example.capstone3.Model.Address;
import com.example.capstone3.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/get")
    public ResponseEntity getAllAddresses() {
        return ResponseEntity.status(200).body(addressService.getAllAddresses());
    }

    @PostMapping("/add")
    public ResponseEntity addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Address created successfully"));
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity updateAddress(@PathVariable Integer addressId,@Valid @RequestBody Address address) {
        addressService.updateAddress(addressId, address);
        return ResponseEntity.status(200).body(new ApiResponse("Address updated successfully"));
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity deleteAddress(@PathVariable Integer addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.status(200).body(new ApiResponse("Address deleted successfully"));
    }


}
