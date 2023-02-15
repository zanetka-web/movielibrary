package com.zaneta.movielibrary.interfaces;

import com.zaneta.movielibrary.models.Movie;

import java.util.List;

public interface MovieServiceInterface {
    List<Movie> getAll();

    Movie getById(int id);

    String add(List<Movie> movies);

    String add(Movie movie);

    int updateMovie(int id, Movie updateMovie);

    int partiallyUpdate(int id, Movie updatedMovie);

    int delete(int id);

}
