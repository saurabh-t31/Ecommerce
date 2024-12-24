package com.project3.Ecommerce.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.project3.Ecommerce.DTO.ProductDto;
import com.project3.Ecommerce.Model.Category;
import com.project3.Ecommerce.Model.Product;
import com.project3.Ecommerce.Service.CategoryService;
import com.project3.Ecommerce.Service.ProductService;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir")+"Ecommerce/src/main/resources/static/productImages";
    @Autowired
    CategoryService catservice;
    
    @Autowired
    ProductService proservice;

    @GetMapping("/admin")
    public String adminhome(){
        return "adminHome";
    }
    
    @GetMapping("/admin/categories")
    public String getCategory(Model model){
        model.addAttribute("categories", catservice.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCategory(@ModelAttribute("category") Category category){
        catservice.addCategory(category);
        
        return "redirect:/admin/categories";    
    }

@GetMapping("/admin/categories/delete/{id}")
public String deleteCategory(@PathVariable int id) {
    try {
        catservice.deleteCategoryById(id);
        return "redirect:/admin/categories"; // Redirect after successful delete
    } catch (DataIntegrityViolationException ex) {
        // Add an error message to the RedirectAttributes
        return "redirect:/admin/categories?error=Category+cannot+be+deleted+as+it+is+associated+with+products"; // Redirect with the error message
    }
}

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id , Model model){
        Optional<Category> cat = catservice.getCategoryById(id);
        if(cat.isPresent()){
            model.addAttribute("category", cat.get());
            return "categoriesAdd";
        }
        else{
            return "404";
        }
    }

    //PRODUCT SECTION
    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products", proservice.getAllProducts() );
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories", catservice.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
public String saveproduct(@ModelAttribute("productDTO") ProductDto productDto,
                          @RequestParam("productImage") MultipartFile file,
                          @RequestParam("imgName") String imgName) throws IOException{

    Product product = new Product(productDto);
    // product.setId(productDto.getId());
    // product.setName(productDto.getName());
    product.setCategory(catservice.getCategoryById(productDto.getCategoryId()).get());
    // product.setPrice(productDto.getPrice());
    // product.setWeight(productDto.getWeight());
    // product.setDescription(productDto.getDescription());

    // Ensure the upload directory exists
    File uploadDirFile = new File(uploadDir);
    if (!uploadDirFile.exists()) {
        uploadDirFile.mkdirs();  // Create the directory if it does not exist
    }

    String imageUUID;
    if (!file.isEmpty()) {
        imageUUID = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
        // Check if the file was successfully written to disk
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            // Log error (or handle as needed)
            e.printStackTrace();
            return "error";  // Return error page if image upload fails
        }
    } else {
        imageUUID = imgName;
    }
    product.setImageName(imageUUID);
    proservice.addProduct(product);

    return "redirect:/admin/products";
}
@GetMapping("/admin/product/delete/{id}")
public String deleteProduct(@PathVariable long id){
           proservice.removeProductById(id);;
    return "redirect:/admin/products";   
}

@GetMapping("/admin/product/update/{id}")
public String updateProduct(@PathVariable long id, Model model){
    Product product = proservice.getProductById(id).get();
    
    ProductDto productDto = new ProductDto();

    productDto.setId(product.getId());
    productDto.setName(product.getName());
    productDto.setDescription(product.getDescription());
    productDto.setCategoryId(product.getCategory().getId());
    productDto.setPrice(product.getPrice());
    productDto.setWeight(product.getWeight()); 
    productDto.setImageName(product.getImageName());

    model.addAttribute("categories", catservice.getAllCategory());
    model.addAttribute("productDTO", productDto);
    
    return "productsAdd";
}
}
