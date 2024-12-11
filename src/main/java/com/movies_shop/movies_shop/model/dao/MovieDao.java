package com.movies_shop.movies_shop.model.dao;


import com.movies_shop.movies_shop.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieDao extends JpaRepository<Movie, Integer> {
    Movie findFirstByMovieDetailIdAndState(Integer movieDetailId, String state);
    @Query("SELECT m.movieDetail.name, m.movieDetail.genre.name, COUNT(m.id) AS availableCopies " +
            "FROM Movie m " +
            "WHERE (LOWER(m.movieDetail.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(m.movieDetail.genre.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "   AND m.state = 'Available' " +
            "GROUP BY m.movieDetail.name, m.movieDetail.genre.name")
    List<Object[]> findAvailableMoviesByNameOrDescription(@Param("keyword") String keyword);
}