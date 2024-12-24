package com.project3.Ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project3.Ecommerce.Model.Category;
import com.project3.Ecommerce.Repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository catrepo;

     public List<Category> getAllCategory(){
        return catrepo.findAll();
     }
     public void addCategory(Category cat){
           catrepo.save(cat);
     }
     public void deleteCategoryById(int id){
      catrepo.deleteById(id);
     }
     public Optional<Category> getCategoryById(int id){
         return catrepo.findById(id);
     }


}
