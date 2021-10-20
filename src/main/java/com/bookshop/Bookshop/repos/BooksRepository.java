package com.bookshop.Bookshop.repos;

import com.bookshop.Bookshop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Integer> {

}
