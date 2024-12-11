package com.movies_shop.movies_shop.model.service;


import com.movies_shop.movies_shop.model.dto.MovieDetailDto;
import com.movies_shop.movies_shop.model.entity.MovieDetail;

public interface IMovieDetailService {
    MovieDetail save(MovieDetailDto movieDetailDto);
    MovieDetail findById(Integer id);
}
