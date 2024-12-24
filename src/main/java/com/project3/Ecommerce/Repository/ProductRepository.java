package com.project3.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.Ecommerce.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    List<Product> findAllByCategory_Id(int id);
    
    
}
