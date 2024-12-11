package com.movies_shop.movies_shop;

import com.movies_shop.movies_shop.controller.AuthController;
import com.movies_shop.movies_shop.controller.MovieController;
import com.movies_shop.movies_shop.controller.RentalController;
import com.movies_shop.movies_shop.model.dto.MovieDetailDto;
import com.movies_shop.movies_shop.model.payload.MessageResponse;
import com.movies_shop.movies_shop.model.service.IUserService;
import com.movies_shop.movies_shop.model.service.IMovieDetailService;
import com.movies_shop.movies_shop.model.service.IRentalService;
import com.movies_shop.movies_shop.model.service.impl.MovieReportService;
import com.movies_shop.movies_shop.model.service.impl.MovieSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MoviesShopApplicationControllerTests {

	@Mock
	private IUserService userService;

	@Mock
	private IMovieDetailService movieDetailService;

	@Mock
	private IRentalService rentalService;

	@Mock
	private MovieReportService movieReportService;

	@Mock
	private MovieSearchService movieSearchService;

	@InjectMocks
	private AuthController authController;

	@InjectMocks
	private MovieController movieController;

	@InjectMocks
	private RentalController rentalController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRegisterUser() {
		when(userService.save(any())).thenReturn(null);

		MessageResponse response = (MessageResponse) authController.register(null).getBody();

		assertNotNull(response);
		assertEquals("User registered successfully", response.getMessage());
		verify(userService, times(1)).save(any());
	}

	@Test
	void testLoginUser() {
		MessageResponse response = (MessageResponse) authController.login(null).getBody();

		assertNotNull(response);
		assertEquals("Invalid credentials", response.getMessage());
	}


	@Test
	void testGetMovieReport() {
		when(movieReportService.getMovieReport())
				.thenReturn(Collections.emptyList());

		List<?> response = movieController.getMovieReport().getBody();

		assertNotNull(response);
		assertTrue(response.isEmpty());
		verify(movieReportService, times(1)).getMovieReport();
	}

	@Test
	void testSearchMovies() {
		when(movieSearchService.searchMovies(anyString()))
				.thenReturn(Collections.emptyList());

		MessageResponse response = (MessageResponse) movieController.searchMovies("test").getBody();

		assertNotNull(response);
		assertEquals("No available movies found for the keyword: test", response.getMessage());
		verify(movieSearchService, times(1)).searchMovies(anyString());
	}

	@Test
	void testRentMovie() {
		when(rentalService.rentMovieByDetailId(anyInt()))
				.thenReturn(1);

		MessageResponse response = (MessageResponse) rentalController.rentMovie(1).getBody();

		assertNotNull(response);
		assertEquals("Movie rented successfully. Movie ID: 1", response.getMessage());
		verify(rentalService, times(1)).rentMovieByDetailId(anyInt());
	}
}
