package pl.jaknauczycsieprogramowania.movielibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jaknauczycsieprogramowania.movielibrary.models.Movie;
import pl.jaknauczycsieprogramowania.movielibrary.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAll(){
      return movieRepository.getAll();
    }

    public Movie getById(int id){
        return movieRepository.getById(id);
    }


    public String add(List<Movie> movies){
        return movieRepository.save(movies);
    }

    public String add(Movie movie){
        return movieRepository.save(movie);
    }

    public int updateMovie(int id, Movie updateMovie) {
        Movie movie = movieRepository.getById(id);
        if (movie != null) {
            movie.setName(updateMovie.getName());
            movie.setRating(updateMovie.getRating());

            movieRepository.update(movie);

            return 1;
        } else {
            return -1;
        }
    }

    public int partiallyUpdate(int id, Movie updatedMovie){
        Movie movie = movieRepository.getById(id);

        if( movie != null){
            if(updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if(updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1;
        }else {
            return -1;
        }
    }

    public int delete(int id){
      return movieRepository.delete(id);

    }

}
