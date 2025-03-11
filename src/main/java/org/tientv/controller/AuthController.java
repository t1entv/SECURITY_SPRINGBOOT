package org.tientv.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tientv.dao.UserDAO;
import org.tientv.model.User;
import org.tientv.repository.UserRepository;
import org.tientv.util.AuthUtil;

import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDAO dao;

    @GetMapping("/login")
    public String login() {
            Authentication auth = AuthUtil.getAuthentication();
            if (auth != null && auth.isAuthenticated()
                    && !"anonymousUser".equals(auth.getPrincipal())) {
                return "redirect:/home";
            }
        return "login";
    }

    @GetMapping("/check-user")
    public String checkUser(Model model) {
        Authentication auth = AuthUtil.getAuthentication();
        String username = auth.getName();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            model.addAttribute("message", "No user logged in");
            return "check-user";
        }

        model.addAttribute("message", "User logged in: " + auth.getName());
        return "check-user";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            dao.addUser(user);
            model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Username đã tồn tại!");
            return "redirect:/register";
        }
    }
}
