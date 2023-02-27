package com.zaneta.movielibrary.controllers;

import com.zaneta.movielibrary.config.ResponseConfig;
import com.zaneta.movielibrary.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zaneta.movielibrary.service.MovieService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("")
    public ResponseEntity getAll() {
        ArrayList<Movie> result = new ArrayList<>(movieService.getAll());
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, "list not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID(@PathVariable("id") int id) {
        Movie result = movieService.getById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, "movie not found");
        }
    }

    @PostMapping("addmultiple")
    public ResponseEntity add(@RequestBody List<Movie> movies) {
        if (movies != null) {
            return ResponseEntity.ok(movieService.add(movies));
        } else {
            return ResponseConfig.getResponse(HttpStatus.BAD_REQUEST, "movies were not added");
        }
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody Movie movie) {
        if (movie != null) {
            return ResponseEntity.ok(movieService.add(movie));
        } else {
            return ResponseConfig.getResponse(HttpStatus.BAD_REQUEST, " movie is not added");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody
    Movie updateMovie) {
        Movie movie = movieService.getById(id);
        if (movie != null && movie.getName() != null && movie.getRating() != 0) {
            return ResponseEntity.ok(movieService.updateMovie(id, updateMovie));
        } else {
            return ResponseConfig.getResponse(HttpStatus.BAD_REQUEST, "movie cannot be updated");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieService.getById(id);
        int result = movieService.updateMovie(id, updatedMovie);
        if (movie != null && movie.getName() != null && movie.getRating() != 0) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseConfig.getResponse(HttpStatus.BAD_REQUEST, "movie cannot be updated");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        Movie movie = movieService.getById(id);
        if (movie != null) {
            return ResponseEntity.ok(movieService.delete(id));
        } else {
            return ResponseConfig.getResponse(HttpStatus.NOT_FOUND, "movie not found");
        }
    }
}
