package com.converter.currency.demo.service.api;

import com.converter.currency.demo.model.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);
}
