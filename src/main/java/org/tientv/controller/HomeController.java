package org.tientv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.tientv.repository.UserRepository;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @GetMapping({"/", "/home"})
    public String index() {
        return "home";
    }
}
