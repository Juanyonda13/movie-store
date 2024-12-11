package com.movies_shop.movies_shop.model.dao;

import com.movies_shop.movies_shop.model.entity.MovieDetail;
import com.movies_shop.movies_shop.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {

    @Query("SELECT m.movieDetail.name, m.movieDetail.genre.name, COUNT(r.id) AS rentalCount, SUM(r.movie.movieDetail.genre.price) AS totalRentalValue " +
            "FROM Rental r " +
            "JOIN r.movie m " +
            "GROUP BY m.movieDetail.name, m.movieDetail.genre.name " +
            "ORDER BY totalRentalValue DESC")
    List<Object[]> getMovieRentalReport();
}
