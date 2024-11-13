package com.Customer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    public Cart getCartById(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }


    public void addNewCart(Cart cart) {
        cartRepository.save(cart);
    }


    public void updateCart(int cartId, Cart cart) {
        Cart existing = getCartById(cartId);
        if (existing != null) {
            existing.setDescription(cart.getDescription());
            existing.setCategory(cart.getCategory());
            existing.setColor(cart.getColor());
            existing.setSize(cart.getSize());
            existing.setPrice(cart.getPrice());
            existing.setQuantity(cart.getQuantity());
            cartRepository.save(existing);
        }
    }


    public void removeCart(int cartId) {
        cartRepository.deleteById(cartId);
    }
}
