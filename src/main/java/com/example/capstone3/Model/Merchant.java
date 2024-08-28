package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String storeName;

    @NotEmpty
    private String ownerName;

    @Email
    @NotEmpty
    private String email;

    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must be 10 digits and start with 05")
    private String phone;

    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String city;
    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null")
    private String street;


    @OneToMany(mappedBy = "merchant")
    private List<Fabric> fabrics;

    @OneToMany(mappedBy = "merchant")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "merchant")
    private List<Order> orders;

    @OneToMany(mappedBy = "merchant")
    private List<Rating> ratings;


}