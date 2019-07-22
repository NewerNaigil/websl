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
public class SignUpController {

    @Autowired

    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signUpForm() {
        return "signUpFormM";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String login,
                         @RequestParam String password,
                         Map<String, Object> model) {

        User logUser = userRepository.findByLogin(login);

        if (logUser == null) {

            User user = new User(login, password);
            user.setLogin(login);
            user.setPassword(password);

            userRepository.save(user);

            model.put("temp", "Успех");
            return "signUpResultM";
        } else {
            model.put("temp", "Пользователь с таким логином уже существует.");
            return "signUpResultM";
        }
    }

}
