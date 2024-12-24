package com.project3.Ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.Ecommerce.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role , Integer> {
    
}
