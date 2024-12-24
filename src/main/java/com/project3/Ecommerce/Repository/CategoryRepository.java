package com.project3.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.Ecommerce.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    

}
