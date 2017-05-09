package com.converter.currency.demo.service;

import com.converter.currency.demo.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
