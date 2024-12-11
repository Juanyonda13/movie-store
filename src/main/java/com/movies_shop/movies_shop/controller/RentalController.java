package com.movies_shop.movies_shop.controller;

import com.movies_shop.movies_shop.model.dto.RentalDto;
import com.movies_shop.movies_shop.model.entity.Rental;
import com.movies_shop.movies_shop.model.payload.MessageResponse;
import com.movies_shop.movies_shop.model.service.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {
    @Autowired
    private IRentalService rentalService;

    @PostMapping("/{movieDetailId}")
    public ResponseEntity<?> rentMovie(@PathVariable Integer movieDetailId) {
        try {
            Integer rentedMovieId = rentalService.rentMovieByDetailId(movieDetailId);
            return new ResponseEntity<>(MessageResponse.builder().message("Movie rented successfully. Movie ID: "+ rentedMovieId ).object(null).build(),HttpStatus.OK);
        }catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MessageResponse.builder()
                            .message(ex.getMessage())
                            .object(null)
                            .build()
            );
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    MessageResponse.builder()
                            .message("Database error: " + exDt.getMostSpecificCause().getMessage())
                            .object(null)
                            .build()
            );
        }
    }
}
