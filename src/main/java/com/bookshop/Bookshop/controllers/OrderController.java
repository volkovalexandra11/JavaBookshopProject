package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.configurations.IAuthenticationFacade;
import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.repos.BooksOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private BooksOrderRepository booksOrderRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;


//    String GetOrderDetails(Model model) {
//        Authentication authentication =  authenticationFacade.getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        Long userId = user.getUser_id();
//        List<Book> bookList = booksOrderRepository.findAllByOrderId(userId);
//
//        model.addAttribute("bookList", bookList);
//
//        return "order";
//    }
}



