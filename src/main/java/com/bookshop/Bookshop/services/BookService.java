package com.bookshop.Bookshop.services;

import com.bookshop.Bookshop.entities.Book;
import com.bookshop.Bookshop.repos.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public class BookService {
    @Autowired
    BooksRepository booksRepository;

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book getById(Long id) {
        return booksRepository.findByBook_id(id);
    }

    public List<Book> findAllByTitle(String title) {
        return booksRepository.findAllByTitle(title);
    }

    public List<Book> findAllByAuthor(String authorName) {
        return booksRepository.findAllByAuthor(authorName);
    }
}
