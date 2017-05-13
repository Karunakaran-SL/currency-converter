package com.converter.currency.demo.service.api;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
