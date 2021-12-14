package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.Cart;
import com.bookshop.Bookshop.services.CartService;
import com.bookshop.Bookshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/cart/books/{id}")
    String addToCart(@PathVariable Long id) {
        save(id);
        return "redirect:/books";
    }

    @RequestMapping(value="/tocart/books/{id}")
    String addToCartFromBook(@PathVariable Long id) {
        save(id);
        return "redirect:books/{id}";
    }

    private void save(Long id) {
        Long userId = userService.getCurrentUser().getUser_id();
        Cart c = new Cart();
        c.setBooks_id(id);
        c.setUser_id(userId);
        cartService.save(c);
    }
}
