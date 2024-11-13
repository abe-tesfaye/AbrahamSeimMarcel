package com.Customer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }


    @PostMapping("/new")
    public List<Product> addNewProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
        return productService.getAllProducts();
    }


    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
        return productService.getProductById(productId);
    }
}

