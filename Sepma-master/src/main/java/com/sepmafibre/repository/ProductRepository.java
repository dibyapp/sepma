package com.sepmafibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sepmafibre.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
