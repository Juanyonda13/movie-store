package com.movies_shop.movies_shop.model.service.impl;

import com.movies_shop.movies_shop.model.dao.MovieDao;
import com.movies_shop.movies_shop.model.dto.MovieAvailabilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSearchService {

    private final MovieDao movieDao;

    @Autowired
    public MovieSearchService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<MovieAvailabilityDto> searchMovies(String keyword) {
        List<Object[]> result = movieDao.findAvailableMoviesByNameOrDescription(keyword);

        return result.stream().map(data -> MovieAvailabilityDto.builder()
                        .name((String) data[0])
                        .description((String) data[1])
                        .availableCopies(((Number) data[2]).intValue())
                        .build())
                .collect(Collectors.toList());
    }
}
