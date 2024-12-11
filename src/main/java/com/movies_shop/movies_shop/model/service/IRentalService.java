package com.movies_shop.movies_shop.model.service;


import com.movies_shop.movies_shop.model.dto.RentalDto;
import com.movies_shop.movies_shop.model.entity.Rental;

public interface IRentalService {
    Integer rentMovieByDetailId(Integer movieDetailId);
}
