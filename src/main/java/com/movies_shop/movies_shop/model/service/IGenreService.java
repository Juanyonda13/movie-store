package com.movies_shop.movies_shop.model.service;


import com.movies_shop.movies_shop.model.entity.Genre;

public interface IGenreService {
    Genre findById(Integer id);
}
