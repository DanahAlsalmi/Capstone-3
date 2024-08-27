package com.example.capstone3.Repository;

import com.example.capstone3.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    Shipping findShippingById(Integer id);
}
