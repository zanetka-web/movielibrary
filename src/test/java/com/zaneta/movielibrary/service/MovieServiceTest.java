package com.zaneta.movielibrary.service;


import com.zaneta.movielibrary.models.Movie;
import com.zaneta.movielibrary.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetAll() {
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie(1, "Titanic", 7));
        expected.add(new Movie(2, "Avengers", 10));
        when(movieRepository.getAll()).thenReturn(expected);

        List<Movie> movies = movieService.getAll();

        assertThat(movies.size()).isSameAs(expected.size());
        assertThat(movies.get(movies.size() - 1).getName()).isSameAs(expected.get(movies.size() - 1).getName());

    }

    @Test
    void shouldGetById() {
        Movie expected = new Movie(1, "Titanic", 7);
        when(movieRepository.getById(1)).thenReturn(expected);

        Movie movie = movieService.getById(1);

        assertThat(movie.getId()).isSameAs(expected.getId());

    }

    @Test
    void shouldAddMultiple() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Titanic", 7));
        movies.add(new Movie(2, "Avengers", 10));
        when(movieRepository.save((List<Movie>) any())).thenReturn("It's saved!");

        String result = movieService.add(movies);
        assertThat(result).isSameAs("It's saved!");

    }

    @Test
    void shouldAddOne() {
        Movie movie = new Movie(1, "Titanic", 7);
        when(movieRepository.save((Movie) any())).thenReturn("Movie saved!");

        String result = movieService.add(movie);
        assertThat(result).isSameAs("Movie saved!");
    }

    @Test
    void shouldUpdateMovie() {
        Movie movie = new Movie(1, "Titanic", 7);
        when(movieRepository.getById(any(Integer.class))).thenReturn(movie);
        when(movieRepository.update((Movie) any())).thenReturn(1);

        int result = movieService.updateMovie(1, new Movie());
        assertThat(result).isSameAs(1);
    }

    @Test
    void shouldNotUpdateMovieWhenNull() {
        when(movieRepository.getById(any(Integer.class))).thenReturn(null);
        int result = movieService.updateMovie(1, new Movie());
        assertThat(result).isSameAs(-1);
    }

    @Test
    void shouldUpdateNameAndRating() {
        when(movieRepository.getById(any(Integer.class))).thenReturn(new Movie());
        when(movieRepository.update((Movie) any())).thenReturn(1);

        int result = movieService.partiallyUpdate(1, new Movie());
        assertThat(result).isSameAs(1);
    }

    @Test
    void shouldNotUpdatePartially() {
        when(movieRepository.getById(any(Integer.class))).thenReturn(null);
        int result = movieService.partiallyUpdate(1, new Movie());
        assertThat(result).isSameAs(-1);
    }

    @Test
    void shouldDelete() {
        when(movieRepository.delete(1)).thenReturn(1);
        int result = movieService.delete(1);
        assertThat(result).isSameAs(1);
    }
}