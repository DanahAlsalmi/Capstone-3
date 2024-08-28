package com.example.capstone3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TailorInfoDTO {
    private Integer orderId;
    private String orderStatus;
    private double price;
}
