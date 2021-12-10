package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.axiliary.IAuthenticationFacade;
import com.bookshop.Bookshop.axiliary.Utils;
import com.bookshop.Bookshop.entities.Book;;
import com.bookshop.Bookshop.repos.BooksRepository;

import com.bookshop.Bookshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;


    @GetMapping({"/books", "/", "/welcome", "/search"})
    public String listAll(Model model) {
        return getBookModel(model);
    }


    @PostMapping("/search")
    String search(@ModelAttribute Book book, Model model, BindingResult bindingResult) {
        List<Book> bookList = booksRepository.findAllByTitle(book.getTitle());
        if (bookList.isEmpty()) {
            bookList = booksRepository.findAllByAuthor(book.getTitle());
        }
        boolean authorized = userService.isLogged();
        model.addAttribute("bookList", bookList);
        model.addAttribute("authorized", authorized);
        return "books";
    }

    @RequestMapping(value = "books/{id}")
    String GetBookInfo(Model model, @PathVariable("id") Long id) {
        Book book = booksRepository.findByBook_id(id);
        model.addAttribute("book", book);
        return "book";
    }

    private String getBookModel(Model model) {
        List<Book> bookList = booksRepository.findAll();
        boolean authorized = userService.isLogged();
        model.addAttribute("bookList", bookList);
        model.addAttribute("authorized", authorized);
        model.addAttribute("book", new Book());
        return "books";
    }
}


