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
public class MovieDto implements Serializable {
    private Integer id;
    private Integer movieId;
    private String code;
    private String state;
    private List<MovieDetailDto> movieDetail;
    private Date created_at;
    private Date updated_at;
}