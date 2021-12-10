package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.*;
import com.bookshop.Bookshop.repos.OrderRepository;
import com.bookshop.Bookshop.services.BookOrderService;
import com.bookshop.Bookshop.services.BookService;
import com.bookshop.Bookshop.services.CartService;
import com.bookshop.Bookshop.services.UserService;
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
    private BookOrderService bookOrderService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping(value = "orders")
    String GetOrderDetails(Model model) {
        User user = userService.getCurrentUser();
        Long userId = user.getUser_id();

        List<Order> orders = orderRepository.findAllByUser_id(userId);
        model.addAttribute("orderList", orders);

        return "orderList";
    }

    @RequestMapping(value = "orders/{id}")
    String getBookList(Model model, @PathVariable("id") Long orderId) {
        List<Long> bookIdsList = bookOrderService.getBookIds(orderId);

        List<Book> bookList = bookIdsList.stream()
                .map(bookService::getById)
                .collect(Collectors.toList());

        model.addAttribute("bookList", bookList);

        double sum = bookList
                .stream()
                .map(Book::getPrice)
                .mapToDouble(price -> price)
                .sum();

        model.addAttribute("sum", sum);
        Order order = orderRepository.findById(orderId).get();
        model.addAttribute("order", order);
        return "order";
    }

    @RequestMapping(value = "orderCart")
    String order(Model model) {
        Order order = new Order();
        Long userId = userService.getCurrentUser().getUser_id();
        order.setUser_id(userId);
        order.setPayed(false);

        List<Book> books = cartService.getBooks(userId);

        double sum = books
                .stream()
                .map(Book::getPrice)
                .mapToDouble(price -> price)
                .sum();


        order.setSum(sum);
        order.setStatus("processing");
        orderRepository.save(order);

        Long id = orderRepository.findTopByOrderByIdDesc();

        for (Book b :
                books) {
            BookToOrder bo = new BookToOrder();
            bo.setOrder_id(id);
            bo.setBook_id(b.getBook_id());
            bookOrderService.save(bo);
        }

        cartService.deleteAllByUserId(userId);

        model.addAttribute("sum", sum);
        return "redirect:/orders/" + id;
    }

    @RequestMapping(value = {"/toBooks", "/toBookList", "/back"})
    String toBooks() {
        return "redirect:/";
    }

    @RequestMapping(value = "/pay/{id}")
    String pay(@PathVariable Long id) {
        Order order = orderRepository.getById(id);
        order.setStatus("payed");
        orderRepository.update(id);
        return "payed";
    }
}



