package com.xoriant.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
