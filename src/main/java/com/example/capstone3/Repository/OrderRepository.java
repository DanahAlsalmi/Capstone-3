package com.example.capstone3.Repository;

import com.example.capstone3.Model.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    List<Order> findOrderByFabric(Fabric fabric);

    @Query("SELECT o.fabric FROM Order o GROUP BY o.fabric.id ORDER BY COUNT(o.id) DESC")
    List<Fabric> findBestSellingFabric(Pageable pageable);

    List<Order> findOrderByOrderStatus(String orderStatus);

    List<Order> findOrderByMerchant(Merchant merchant);

    Order findOrderByMerchantAndCustom(Merchant merchant, Customer customer);

    Order findOrderByTailorAndCustom(Tailor tailor, Customer customer);

    Order findOrderByDesignerAndCustom(Designer designer, Customer customer);






}
