package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.axiliary.IAuthenticationFacade;
import com.bookshop.Bookshop.axiliary.Utils;
import com.bookshop.Bookshop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @GetMapping(value = "userProfile")
    String getUserProfile(Model model) {
        User currentUser = new Utils(authenticationFacade).getUser();
        model.addAttribute("user", currentUser);
        return "userProfilePage";
    }
}
