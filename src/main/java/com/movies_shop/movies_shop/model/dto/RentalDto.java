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
public class RentalDto implements Serializable {
    private Long id;
    private Long movieId;
    private List<MovieDto> movie;
    private Date created_at;
    private Date updated_at;
}
