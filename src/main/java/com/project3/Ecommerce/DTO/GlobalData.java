package com.project3.Ecommerce.DTO;

import java.util.ArrayList;
import java.util.List;

import com.project3.Ecommerce.Model.Product;

public class GlobalData {

    public static List<Product> cart;

    static{
       cart = new ArrayList<Product>();
    }
    
}
