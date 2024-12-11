package com.movies_shop.movies_shop.model.dao;

import com.movies_shop.movies_shop.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre, Integer> {
}
