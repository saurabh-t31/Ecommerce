package com.project3.Ecommerce.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.project3.Ecommerce.DTO.GlobalData;
import com.project3.Ecommerce.Service.CategoryService;
import com.project3.Ecommerce.Service.ProductService;

@Controller
public class HomeController {
    @Autowired
private PasswordEncoder passwordEncoder; // Inject the password encoder
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping({"/","/home"})
    public String homepage(Model model){
                model.addAttribute("cartCount", GlobalData.cart.size());

        return "index";
    }

    @GetMapping("/shop")
    public String shoppage(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());

        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopbyCategory(Model model, @PathVariable int id){

        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "shop";
        
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model,@PathVariable int id){
    model.addAttribute("product", productService.getProductById(id).get());//Optional Return ker raha hai
        return "viewProduct";
    }
}
