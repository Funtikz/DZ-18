package org.example.dz18.controller;

import lombok.RequiredArgsConstructor;
import org.example.dz18.exceptions.UserNotFoundException;
import org.example.dz18.models.User;
import org.example.dz18.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) throws UserNotFoundException {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
