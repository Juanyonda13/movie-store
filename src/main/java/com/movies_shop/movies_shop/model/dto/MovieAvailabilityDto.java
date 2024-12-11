package com.movies_shop.movies_shop.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieAvailabilityDto {
    private String name;
    private String description;
    private Integer availableCopies;
}