package com.example.demoapplication.controller;

import com.example.demoapplication.model.User;
import com.example.demoapplication.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String keyword) {
        return service.search(keyword);

    }
}
