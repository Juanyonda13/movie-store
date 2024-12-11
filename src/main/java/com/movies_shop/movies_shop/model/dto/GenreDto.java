package com.movies_shop.movies_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class GenreDto implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private Date created_at;
    private Date updated_at;
}