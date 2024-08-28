package com.example.capstone3.Repository;

import com.example.capstone3.Model.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Integer> {

    Fabric findFabricById(Integer id);

    List<Fabric> findFabricByName(String name);

    List<Fabric> findFabricByColor(String color);

    List<Fabric> findFabricByPattern(String pattern);

    List<Fabric> findFabricByType(String type);

    @Query("select f from Fabric f where f.price between :minPrice and :maxPrice")
    List<Fabric> findFabricByPriceRange(double minPrice, double maxPrice);

    List<Fabric> findFabricByMerchantId(Integer merchantId);

}
