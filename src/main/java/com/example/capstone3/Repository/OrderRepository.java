package com.example.capstone3.Repository;

import com.example.capstone3.Model.Customer;
import com.example.capstone3.Model.Designer;
import com.example.capstone3.Model.Order;
import com.example.capstone3.Model.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderById(Integer id);

    List<Order> findOrderByCustom(Customer customer);

    List<Order> findOrderByCustomId(Integer customerId);

    ///
    List<Order> findOrderByTailor(Tailor tailor);
    ///
    List<Order> findOrderByDesigner(Designer designer);
    ///
    List<Order> findOrderByFabricId(Integer fabricId);


}
