package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.configurations.IAuthenticationFacade;
import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.entities.BookToOrder;
import com.bookshop.Bookshop.entities.Order;
import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.repos.BooksOrderRepository;
import com.bookshop.Bookshop.repos.BooksRepository;
import com.bookshop.Bookshop.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("/books")
    public String listAll(Model model) {
        List<Book> bookList = booksRepository.findAll();
        model.addAttribute("bookList", bookList);

        return "books";
    }

    @Autowired
    private BooksOrderRepository booksOrderRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "orders")
    String GetOrderDetails(Model model) {
        Authentication authentication = authenticationFacade.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long userId = user.getUser_id();

        List<Order> orders = orderRepository.findAllByUser_id(userId);
        Long orderId = orders.get(0).getId();
        List<Long> bookIdsList = booksOrderRepository
                .findAllByOrder_id(orderId).stream()
                .map(BookToOrder::getBook_id)
                .collect(Collectors.toList());

        List<Book> bookList = bookIdsList.stream()
                .map(booksRepository::findByBook_id)
                .collect(Collectors.toList());


        model.addAttribute("bookList", bookList);

        return "order";
    }

}


