package com.example.jspwithboot.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.jspwithboot.model.Product;
import com.example.jspwithboot.model.User;
import com.example.jspwithboot.service.iface.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    // Inject the interface
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Existing method for listing products
    @GetMapping("/products")
    public String showProductList(HttpSession session, Model model) {
        // Check if the user is logged in
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // If the user is not logged in, redirect to login page
            return "redirect:/login";
        }

        // If logged in, show the product list
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list"; // JSP file name
    }

    // New GET mapping for /products/add to ensure only admin can access
    @GetMapping("/products/add")
    public String showAddProductPage(HttpSession session) {
        // Check if the user is logged in and if they are an admin
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !"ADMIN".equals(loggedInUser.getRole())) {
            // If not logged in or not an admin, redirect to login
            return "redirect:/login";
        }

        // If logged in and admin, show add-product page
        return "add-product"; // JSP file for adding a product
    }

    // POST mapping to handle form submission for adding a product
    @PostMapping("/products/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products"; // Redirect to the product list after adding
    }
}
