package com.movies_shop.movies_shop.model.service.impl;


import com.movies_shop.movies_shop.model.dao.UserDao;
import com.movies_shop.movies_shop.model.dto.UserDto;
import com.movies_shop.movies_shop.model.entity.User;
import com.movies_shop.movies_shop.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserImpl implements IUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User save(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        return userDao.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
