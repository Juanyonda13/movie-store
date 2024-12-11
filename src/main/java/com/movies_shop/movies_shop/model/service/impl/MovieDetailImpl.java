package com.movies_shop.movies_shop.model.service.impl;


import com.movies_shop.movies_shop.model.dao.MovieDao;
import com.movies_shop.movies_shop.model.dao.MovieDetailDao;
import com.movies_shop.movies_shop.model.dto.MovieDetailDto;
import com.movies_shop.movies_shop.model.dto.MovieReportDto;
import com.movies_shop.movies_shop.model.entity.Genre;
import com.movies_shop.movies_shop.model.entity.Movie;
import com.movies_shop.movies_shop.model.entity.MovieDetail;
import com.movies_shop.movies_shop.model.service.IGenreService;
import com.movies_shop.movies_shop.model.service.IMovieDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieDetailImpl implements IMovieDetailService {
    @Autowired
    private MovieDetailDao movieDetailDao;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private MovieDao movieDao;
    @Override
    public MovieDetail save(MovieDetailDto movieDetailDto) {
        Genre genre = genreService.findById(movieDetailDto.getGenreId());
        if (genre == null) {
            throw new IllegalArgumentException("Genre not found");
        }

        MovieDetail movieDetail = MovieDetail.builder()
                .name(movieDetailDto.getName())
                .genre(genre)
                .build();
        movieDetail = movieDetailDao.save(movieDetail);
        if (movieDetail.getId() == null) {
            throw new IllegalStateException("Failed to persist MovieDetail");
        }
        for (int i = 0; i < 3; i++) {
            Movie movie = Movie.builder()
                    .movieDetail(movieDetail)
                    .code(UUID.randomUUID().toString())
                    .state("Available")
                    .build();
            movieDao.save(movie);
        }

        return movieDetail;
    }

    @Override
    public MovieDetail findById(Integer id) {
        return movieDetailDao.findById(id).orElse(null);
    }
}