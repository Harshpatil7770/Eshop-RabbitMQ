package com.xoriant.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
