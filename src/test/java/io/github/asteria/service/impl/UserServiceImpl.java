package io.github.asteria.service.impl;

import io.github.asteria.domain.User;
import io.github.asteria.entity.UserDTO;
import io.github.asteria.mapper.UserMapper;
import io.github.asteria.service.UserService;

public class UserServiceImpl implements UserService {
    private UserMapper UserMapper;

    @Override
    public void save(UserDTO entity) {
    }
}