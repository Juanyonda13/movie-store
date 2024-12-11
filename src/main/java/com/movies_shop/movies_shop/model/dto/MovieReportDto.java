package com.movies_shop.movies_shop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
public class MovieReportDto implements Serializable {
    private String name;
    private String description;
    private Integer rentalCount;
    private Double totalRentalValue;
}
