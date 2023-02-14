package pl.jaknauczycsieprogramowania.movielibrary.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.jaknauczycsieprogramowania.movielibrary.models.Movie;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll(){
       return jdbcTemplate.query("SELECT id, name, rating From movie",
                BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getById(int id){
      return jdbcTemplate.queryForObject("SELECT id, name, rating FROM movie WHERE " + "id = ?",
                BeanPropertyRowMapper.newInstance(Movie.class), id);
    }


    public String save(List<Movie> movies) {
        movies.forEach(movie -> save(movie));

        return "It's saved!";
    }

    public String save(Movie movie) {
        jdbcTemplate
                .update("INSERT INTO movie(name, rating) VALUES(?, ?)",
                        movie.getName(), movie.getRating()
                );

        return "Movie saved!";
    }

    public int update(Movie movie){
        return jdbcTemplate.update("UPDATE movie SET name=?, rating=? WHERE id=?",
                movie.getName(), movie.getRating(), movie.getId());
    }

    public int delete(int id){
       return jdbcTemplate.update("DELETE FROM movie WHERE id=?", id);
    }


}
