package com.zaneta.movielibrary.controllers;

import com.zaneta.movielibrary.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zaneta.movielibrary.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("")
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public Movie getByID(@PathVariable("id") int id) {
        return movieService.getById(id);
    }

    @PostMapping("addmultiple")
    public String add(@RequestBody List<Movie> movies) {
        return movieService.add(movies);
    }

    @PostMapping("add")
    public String add(@RequestBody Movie movie) {
        return movieService.add(movie);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody
    Movie updateMovie) {
        return movieService.updateMovie(id, updateMovie);
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        return movieService.partiallyUpdate(id, updatedMovie);

    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return movieService.delete(id);
    }
}
