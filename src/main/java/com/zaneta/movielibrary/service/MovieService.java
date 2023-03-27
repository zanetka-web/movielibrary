package com.zaneta.movielibrary.service;

import com.zaneta.movielibrary.interfaces.MovieServiceInterface;
import com.zaneta.movielibrary.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zaneta.movielibrary.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService implements MovieServiceInterface {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAll() {
        try {
            return movieRepository.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Movie getById(int id) {
        try {
            return movieRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }


    public String add(List<Movie> movies) {
        try {
            return movieRepository.save(movies);
        } catch (Exception e) {
            return null;
        }

    }

    public String add(Movie movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            return null;
        }
    }

    public int updateMovie(int id, Movie updateMovie) {

        try {
            Movie movie = movieRepository.getById(id);
            movie.setName(updateMovie.getName());
            movie.setRating(updateMovie.getRating());

            movieRepository.update(movie);

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int partiallyUpdate(int id, Movie updatedMovie) {
        try {
            Movie movie = movieRepository.getById(id);
            if (updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int delete(int id) {
        try {
            return movieRepository.delete(id);
        } catch (Exception e) {
            return -1;
        }
    }
}
