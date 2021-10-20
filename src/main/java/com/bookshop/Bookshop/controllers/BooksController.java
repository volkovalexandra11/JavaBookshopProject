package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.repos.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
