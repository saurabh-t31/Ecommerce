package com.project3.Ecommerce.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project3.Ecommerce.Model.Product;
import com.project3.Ecommerce.Repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productrepo;

     public List<Product> getAllProducts(){
        return productrepo.findAll();
     }
     public void addProduct(Product product){
           productrepo.save(product);
     }
     public void removeProductById(long id){
           productrepo.deleteById(id);
     }

     public Optional<Product> getProductById(long id){
           return productrepo.findById(id);
     }
     public List<Product> getAllProductByCategoryId(int id){
          return productrepo.findAllByCategory_Id(id);
     }
     
}
