package com.example.capstone3.Repository;

import com.example.capstone3.Model.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Integer> {

    Fabric findFabricById(Integer id);

}
