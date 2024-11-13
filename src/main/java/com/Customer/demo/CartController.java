package com.Customer.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/all")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }


    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable int cartId) {
        return cartService.getCartById(cartId);
    }


    @PostMapping("/new")
    public List<Cart> addNewCart(@RequestBody Cart cart) {
        cartService.addNewCart(cart);
        return cartService.getAllCarts();
    }


    @PutMapping("/update/{cartId}")
    public Cart updateCart(@PathVariable int cartId, @RequestBody Cart cart) {
        cartService.updateCart(cartId, cart);
        return cartService.getCartById(cartId);
    }


    @DeleteMapping("/remove/{cartId}")
    public List<Cart> removeCart(@PathVariable int cartId) {
        cartService.removeCart(cartId);
        return cartService.getAllCarts();
    }
}




