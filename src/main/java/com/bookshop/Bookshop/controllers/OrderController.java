package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.axiliary.IAuthenticationFacade;
import com.bookshop.Bookshop.axiliary.Utils;
import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.entities.BookToOrder;
import com.bookshop.Bookshop.entities.Order;
import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.repos.BooksOrderRepository;
import com.bookshop.Bookshop.repos.BooksRepository;
import com.bookshop.Bookshop.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private BooksOrderRepository booksOrderRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;


    @RequestMapping(value = "orders")
    String GetOrderDetails(Model model) {
        User user = new Utils(authenticationFacade).getUser();
        Long userId = user.getUser_id();

        List<Order> orders = orderRepository.findAllByUser_id(userId);
        model.addAttribute("orderList", orders);

        return "orderList";
    }

    @RequestMapping(value = "orders/{id}")
    String getBookList(Model model, @PathVariable("id") Long orderId) {
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



