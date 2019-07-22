package com.kon.websl.controllers;


import com.kon.websl.Entity.User;
import com.kon.websl.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class SignInController {

    @Autowired

    private UserRepository userRepository;

    @GetMapping("/signin")
    public String signInForm() {
        return "signInFormM";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String login,
                         @RequestParam String password,
                         Map<String, Object> model) {

        User user = userRepository.findByLogin(login);

        if (user != null) {

            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                model.put("autorising", "Пользователь авторизирован");
                model.put("bul", true);
                model.put("login", login);
                model.put("password", password);
                return "signInResultM";
            } else {
                model.put("autorising", "Такой комбинации <Пользователь - Пароль> не существует!");
                model.put("bul", false);
            }
        } else {
            model.put("autorising", "Такой комбинации <Пользователь - Пароль> не существует!");
            model.put("bul", false);
        }
        return "signInResultM";
    }
}
