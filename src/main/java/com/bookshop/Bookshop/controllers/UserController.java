package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.services.BookService;
import com.bookshop.Bookshop.services.CartService;
import com.bookshop.Bookshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    @GetMapping(value = "userProfile")
    String getUserProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "userProfilePage";
    }

    @GetMapping(value = "cart")
    String getUserCart(Model model) {
        User currentUser = userService.getCurrentUser();
        Long userId = currentUser.getUser_id();

        List<Long> booksIds = cartService.getBookIds(userId);

        List<Book> bookList = booksIds.stream()
                .map(bookService::getById)
                .collect(Collectors.toList());

        model.addAttribute("bookList", bookList);
        return "cart";
    }
}
