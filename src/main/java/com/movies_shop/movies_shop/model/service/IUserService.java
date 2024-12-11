package com.movies_shop.movies_shop.model.service;



import com.movies_shop.movies_shop.model.dto.UserDto;
import com.movies_shop.movies_shop.model.entity.User;

import java.util.Optional;

public interface IUserService {
    User save(UserDto userDto);
    Optional<User> findByEmail(String email);
}