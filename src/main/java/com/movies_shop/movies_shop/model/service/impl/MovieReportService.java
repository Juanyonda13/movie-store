package com.movies_shop.movies_shop.model.service.impl;

import com.movies_shop.movies_shop.model.dao.MovieDao;
import com.movies_shop.movies_shop.model.dao.RentalDao;
import com.movies_shop.movies_shop.model.dto.MovieReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieReportService {

    private final MovieDao movieDao;
    private final RentalDao rentalDao;

    @Autowired
    public MovieReportService(MovieDao movieDao, RentalDao rentalDao) {
        this.movieDao = movieDao;
        this.rentalDao = rentalDao;
    }

    public List<MovieReportDto> getMovieReport() {
        List<Object[]> reportData = rentalDao.getMovieRentalReport();

        return reportData.stream().map(data -> new MovieReportDto(
                        (String) data[0],
                        (String) data[1],
                        ((Number) data[2]).intValue(),
                        ((Number) data[3]).doubleValue()
                )).sorted(Comparator.comparingDouble(MovieReportDto::getTotalRentalValue).reversed())
                .collect(Collectors.toList());
    }
}
