package com.example.demoapplication.service;

import com.example.demoapplication.model.User;
import com.example.demoapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> search(String keyword) {
        String k = keyword == null ? "" : keyword.trim();
        if (k.isEmpty()) {
            return Collections.emptyList();
        }
        return repo.searchByStoredProcedure(k);
    }
}
