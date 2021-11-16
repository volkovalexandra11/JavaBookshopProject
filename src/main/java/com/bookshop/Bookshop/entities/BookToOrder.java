package com.bookshop.Bookshop.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books_orders")
public class BookToOrder {
    @Id
    @Column(name = "order_id", nullable = false)
    private Long order_id;

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    private Long book_id;


    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
}
