package com.techpro.project.repository;

import com.techpro.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT MAX(o.orderId) FROM Order o")
  Long findLatestId();
}
