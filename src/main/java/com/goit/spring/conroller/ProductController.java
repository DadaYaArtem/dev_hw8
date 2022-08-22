package com.goit.spring.conroller;

import com.goit.spring.model.Product;
import com.goit.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired private ProductService service;

    @GetMapping("/products")
    public String showProductList(Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    @GetMapping("/products/new")
    public String showNewForm(Model model){
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product){
        service.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);
            return "product_form";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/products";
        }
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/products";
    }

}