package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/userSaving")
    public String greetingSubmit(@ModelAttribute User user, Model model) {

        try {
            userRepository.save(new User(
                    user.getFirst_name(),
                    user.getLast_name(),
                    user.getLogin(), new BCryptPasswordEncoder().encode(user.getPassword())));
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            model.addAttribute("login", user.getLogin());
            return "already_exist";
        }
        model.addAttribute("user", user);
        return "welcome_new";
    }
}
