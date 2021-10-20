package com.bookshop.Bookshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;

    private String title;
    private String author;
    private double price;
    private String cover;


    // Getter
    public Integer getBook_id() {
        return book_id;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    // Getter
    public String getAuthor() {
        return author;
    }

    // Getter
    public Double getPrice() {
        return price;
    }

    // Getter
    public String getCover() {
        return cover;
    }
}