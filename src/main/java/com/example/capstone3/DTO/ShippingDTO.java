package com.example.capstone3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingDTO {
    @NotNull
    private Integer orderId;

    @NotEmpty
    private String shipperName;

    @NotNull
    private double price;

    @NotEmpty
    @Pattern(regexp = "^(Shipped|Out of Delivery| Delivered )$")
    private String status;
}
