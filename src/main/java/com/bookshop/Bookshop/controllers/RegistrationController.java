package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }
        if (!user.getPassword().equals(user.getPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "signup";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "signup";
        }

        userService.saveUser(user);
        model.addAttribute("newUser", true);
        model.addAttribute("user", user);
        return "redirect:/";
    }
}