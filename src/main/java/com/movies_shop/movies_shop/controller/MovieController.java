package com.movies_shop.movies_shop.controller;

import com.movies_shop.movies_shop.model.dto.MovieAvailabilityDto;
import com.movies_shop.movies_shop.model.dto.MovieDetailDto;
import com.movies_shop.movies_shop.model.dto.MovieReportDto;
import com.movies_shop.movies_shop.model.entity.MovieDetail;
import com.movies_shop.movies_shop.model.payload.MessageResponse;
import com.movies_shop.movies_shop.model.service.IMovieDetailService;
import com.movies_shop.movies_shop.model.service.impl.MovieReportService;
import com.movies_shop.movies_shop.model.service.impl.MovieSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private IMovieDetailService movieDetailService;
    @Autowired
    private MovieReportService movieReportService;
    @Autowired
    private MovieSearchService movieSearchService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MovieDetailDto movieDetailDto){
        MovieDetail movieSave = null;

        try{
            movieSave = movieDetailService.save(movieDetailDto);
            
            movieDetailDto = MovieDetailDto.builder().name(movieSave.getName()).build();

            return new ResponseEntity<>(MessageResponse.builder().message("save success").object(movieDetailDto).build(), HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MessageResponse.builder().message(exDt.toString()).object(null).build(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/report")
    public ResponseEntity<List<MovieReportDto>> getMovieReport() {
        List<MovieReportDto> report = movieReportService.getMovieReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMovies(@RequestParam String keyword) {
        List<MovieAvailabilityDto> movies = movieSearchService.searchMovies(keyword);
        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MessageResponse.builder()
                            .message("No available movies found for the keyword: " + keyword)
                            .object(null)
                            .build()
            );
        }
        return ResponseEntity.ok(movies);
    }
}