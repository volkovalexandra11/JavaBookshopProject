package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.axiliary.IAuthenticationFacade;
import com.bookshop.Bookshop.axiliary.Utils;
import com.bookshop.Bookshop.entities.Book;;
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

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    private Utils utils;


    @GetMapping("/books")
    public String listAll(Model model) {
        return getBookModel(model, utils.isLogged());
    }

    @GetMapping("/")
    public String listAllBooks(Model model) {
        utils = new Utils(authenticationFacade);
        return getBookModel(model, utils.isLogged());
    }

    @GetMapping("/welcome")
    public String listAllBooksAuthorized(Model model) {
       return getBookModel(model, utils.isLogged());
    }



    @RequestMapping(value = "books/{id}")
    String GetBookInfo(Model model, @PathVariable("id") Long id) {
        Book book = booksRepository.findByBook_id(id);
        model.addAttribute("book", book);
        return "book";
    }

    private String getBookModel(Model model, boolean authorized) {
        List<Book> bookList = booksRepository.findAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("authorized", authorized);
        return "books";
    }
}


