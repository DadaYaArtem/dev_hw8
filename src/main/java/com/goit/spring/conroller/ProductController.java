package com.goit.spring.conroller;

import com.goit.spring.model.Product;
import com.goit.spring.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;


    @GetMapping("/product")
    public String productMainPage(Model model) {
        return "product/product_main";
    }

    @GetMapping("/product/list")
    public String showProductList(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("listSize", listProducts.size());
        return "product/getAllProducts";
    }

    @GetMapping("/product/product_info")
    public String showUserById(Model model, HttpServletRequest req) {
        try {
            model.addAttribute("product", service.get(Long.valueOf(req.getParameter("productId"))));
            return "product/getProductById";
        } catch (Exception e) {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/product/new")
    public String showNewForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/product_form";
    }

    @PostMapping("/product/save")
    public String saveProduct(Product product, HttpServletResponse res) {
        service.save(product);
        return "redirect:/product/list";

    }

    @PostMapping("/product/edit")
    public String showEditForm(ModelMap model, HttpServletRequest req) {
        try {
            Long id = Long.valueOf(req.getParameter("productId"));
            Product product = service.get(id);
            model.addAttribute("product", product);
            return "product/product_form";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/product/list";
        }
    }

    @PostMapping("/product/delete")
    public String deleteProduct(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("productId"));
        service.delete(id);
        return "redirect:/product/list";
    }

}