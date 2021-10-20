package com.bookshop.Bookshop.repos;

import com.bookshop.Bookshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
