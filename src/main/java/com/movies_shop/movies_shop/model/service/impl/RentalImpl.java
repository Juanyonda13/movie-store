package com.movies_shop.movies_shop.model.service.impl;

import com.movies_shop.movies_shop.model.dao.MovieDao;
import com.movies_shop.movies_shop.model.dao.RentalDao;
import com.movies_shop.movies_shop.model.dto.RentalDto;
import com.movies_shop.movies_shop.model.entity.Movie;
import com.movies_shop.movies_shop.model.entity.Rental;
import com.movies_shop.movies_shop.model.service.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalImpl implements IRentalService {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private RentalDao rentalDao;

    @Override
    public Integer rentMovieByDetailId(Integer movieDetailId) {
        Movie availableMovie = movieDao.findFirstByMovieDetailIdAndState(movieDetailId, "Available");

        if (availableMovie == null) {
             throw new IllegalStateException("No movies available for rental.");
        }

        availableMovie.setState("Rented");
        movieDao.save(availableMovie);

        Rental rental = Rental.builder()
                .movie(availableMovie)
                .build();
        rentalDao.save(rental);

        return availableMovie.getId();
    }
}
