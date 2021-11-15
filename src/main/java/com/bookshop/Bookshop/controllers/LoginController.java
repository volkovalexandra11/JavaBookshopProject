//package com.bookshop.Bookshop.controllers;
//
//import com.bookshop.Bookshop.entities.User;
//import com.bookshop.Bookshop.repos.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Objects;
//
//@Controller
//public class LoginController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/login")
//    public String loginForm(Model model) {
//        model.addAttribute("user", new User());
//        return "login";
//    }
//
//    @GetMapping("/userLogin")
//    public String loginSubmit(@ModelAttribute User user, Model model) {
//
//        User userFromDb = userRepository.findByLogin(user.getLogin());
//        String password = new BCryptPasswordEncoder().encode(user.getPassword());
//
//        if (userFromDb == null) {
//            model.addAttribute("login", user.getLogin());
//            model.addAttribute("incorrectLogin", true);
//            return "no_user";
//        }
//
//        if (!Objects.equals(userFromDb.getPassword(), password)) {
//            model.addAttribute("login", user.getLogin());
//            model.addAttribute("incorrectLogin", false);
//            return "no_user";
//        }
//
//
//        model.addAttribute("user", userFromDb);
//        model.addAttribute("newUser", false);
//        return "welcome";
//    }
//}
