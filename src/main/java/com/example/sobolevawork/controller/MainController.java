package com.example.sobolevawork.controller;

import com.example.sobolevawork.model.User;
import com.example.sobolevawork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {

        // Проверка регистрации
        if (!userService.createUserEntity(user)) {
            model.addAttribute("error", "Возникла ошибка при регистрации");
            return "registration";
        }

        return "redirect:/login";
    }
}
