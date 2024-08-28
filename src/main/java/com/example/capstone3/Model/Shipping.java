package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(25) ")
    private String shipperName;
    @NotNull
    @Column(columnDefinition = "double not null")
    private double price;
    @NotEmpty
    @Pattern(regexp = "^(initialled|Shipped|Out for Delivery| Delivered )$")
    private String status;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Order order;
}