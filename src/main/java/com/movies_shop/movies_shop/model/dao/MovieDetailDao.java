package com.movies_shop.movies_shop.model.dao;


import com.movies_shop.movies_shop.model.entity.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDetailDao extends JpaRepository<MovieDetail, Integer> {
}
