package com.bookshop.Bookshop.controllers;

import com.bookshop.Bookshop.entities.User;
import com.bookshop.Bookshop.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="/loginSaving", method = RequestMethod.POST)
    public String loginSaving(
           User formData, Model model
    ) {

        userRepository.save(new User(
                formData.getFirst_name(),
                formData.getLast_name(),
                formData.getLogin(), formData.getPassword()));

        model.addAttribute("firstName", formData.getFirst_name());
        model.addAttribute("secondName", formData.getLast_name());

        return "welcome";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView login() {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("user", new User());
//        mv.setViewName("login");
//        return mv;
//    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new User());
        mv.setViewName("login");
        return mv;
    }
}
