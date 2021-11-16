package com.bookshop.Bookshop.repos;

import com.bookshop.Bookshop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book where book.book_id = ?1")
    Book findByBook_id(Long bookId);
}
