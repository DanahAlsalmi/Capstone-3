package com.example.capstone3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotNull
    @Min(value = 16 , message = "Customer Age must be more than 16")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotEmpty
    @Pattern(regexp = "^(Female|Male)$")
    @Column(columnDefinition = "varchar(25) not null")
    private String gender;

    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    @Email
    private String email;

    @NotEmpty
    @Column(columnDefinition = "varchar(11) not null")
    @Size(min = 10, max = 10)
    private String phone;

    @NotNull
    @Column(columnDefinition = "double not null")
    private double height;

    @NotNull
    @Column(columnDefinition = "double not null")
    private double weight;

    @Column(columnDefinition = "date")
    private LocalDate registrationDate = LocalDate.now();

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "custom")
    private Set<Order> orders;
}
