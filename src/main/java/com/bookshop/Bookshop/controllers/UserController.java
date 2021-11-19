package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.axiliary.IAuthenticationFacade;
import com.bookshop.Bookshop.axiliary.Utils;
import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.entities.Cart;
import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.repos.BooksRepository;
import com.bookshop.Bookshop.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping(value = "userProfile")
    String getUserProfile(Model model) {
        User currentUser = new Utils(authenticationFacade).getUser();
        model.addAttribute("user", currentUser);
        return "userProfilePage";
    }

    @GetMapping(value = "cart")
    String getUserCart(Model model) {
        User currentUser = new Utils(authenticationFacade).getUser();
        Long userId = currentUser.getUser_id();

        List<Cart> a = cartRepository
                .getAllByUserId(userId);

        List<Long> booksIds = cartRepository
                .getAllByUserId(userId).stream()
                .map(Cart::getBooks_id)
                .collect(Collectors.toList());

        List<Book> bookList = booksIds.stream()
                .map(booksRepository::findByBook_id)
                .collect(Collectors.toList());

        model.addAttribute("bookList", bookList);
        return "cart";
    }
}
