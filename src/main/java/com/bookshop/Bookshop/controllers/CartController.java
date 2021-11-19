package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.axiliary.IAuthenticationFacade;
import com.bookshop.Bookshop.axiliary.Utils;
import com.bookshop.Bookshop.entities.Cart;
import com.bookshop.Bookshop.repos.BooksRepository;
import com.bookshop.Bookshop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private BooksRepository booksRepository;

    @RequestMapping(value = "/cart/books/{id}")
    String addToCart(@PathVariable Long id) {
        Long userId = new Utils(authenticationFacade).getUser().getUser_id();
        Cart c = new Cart();
        c.setBooks_id(id);
        c.setUser_id(userId);
        cartRepository.save(c);
        return "redirect:/books";
    }

    @RequestMapping(value="/tocart/books/{id}")
    String addToCartFromBook(@PathVariable Long id) {
        Long userId = new Utils(authenticationFacade).getUser().getUser_id();
        Cart c = new Cart();
        c.setBooks_id(id);
        c.setUser_id(userId);
        cartRepository.save(c);
        return "redirect:books/{id}";
    }

}
