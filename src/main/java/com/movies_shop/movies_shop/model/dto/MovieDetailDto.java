package com.movies_shop.movies_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class MovieDetailDto implements Serializable {
    private Integer id;
    private String name;
    private Integer genreId;
    private List<GenreDto> genre;
    private Date created_at;
    private Date updated_at;
}