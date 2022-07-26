package com.xoriant.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.shop.model.Admin;

public interface AdminRepo extends JpaRepository<Admin,Long>{

}
