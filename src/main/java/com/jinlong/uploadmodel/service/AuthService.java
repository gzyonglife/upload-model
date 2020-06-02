package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.UserTable;

public interface AuthService {
    UserTable register(UserTable userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}