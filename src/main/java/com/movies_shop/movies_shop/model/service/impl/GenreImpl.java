package com.movies_shop.movies_shop.model.service.impl;


import com.movies_shop.movies_shop.model.dao.GenreDao;
import com.movies_shop.movies_shop.model.entity.Genre;
import com.movies_shop.movies_shop.model.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreImpl implements IGenreService {
    @Autowired
    private GenreDao genreDao;
    @Override
    public Genre findById(Integer id) {
        return genreDao.findById(id).orElse(null);
    }
}