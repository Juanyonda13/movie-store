package com.movies_shop.movies_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Builder
public class UserDto {
    private Integer id;
    private String email;
    private String password;
    private Date created_at;
    private Date updated_at;
}