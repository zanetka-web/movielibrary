package com.zaneta.movielibrary.service;

import com.zaneta.movielibrary.interfaces.RentServiceInterface;
import com.zaneta.movielibrary.models.*;
import com.zaneta.movielibrary.repository.RentRepository;
import com.zaneta.movielibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class RentService implements RentServiceInterface {
    @Autowired
    RentRepository rentRepository;
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    UserRepository userRepository;

    final int DAYS_FOR_RENT = 5;

    public String rent(RentRequest request) {
        try {
            User user = userService.getById(request.getUserId());
            Movie movie = movieService.getById(request.getMovieId());
            boolean canRent = !rentRepository.hasUserRanted(request.getUserId());
            Rent lastRent = rentRepository.getLastMovieRentedReturn(request.getUserId());
            boolean isMovieAvailable = rentRepository.isMovieAvailable(request.getMovieId());

            if (canRent && lastRent == null && isMovieAvailable) {
                int result = rentRepository.rent(request, getDateOfRenting(), getExpectingDateToReturn());
                if (result == 1) {
                    return getMovieResponse(user.getName(), movie.getName());
                }
            } else if (canRent && lastRent != null && isMovieAvailable) {

                //verify if the last rent was returned in time.
                if (lastRent.getExpectingDateToReturn().compareTo(lastRent.getDateOfReturn()) >= 0) {
                    int result = rentRepository.rent(request, getDateOfRenting(), getExpectingDateToReturn());
                    if (result == 1) {
                        return getMovieResponse(user.getName(), movie.getName());
                    }
                }
            }

            return user.getName() + " cannot rent: " + movie.getName();
        } catch (Exception e) {
            return null;
        }
    }

    public Date getDateOfRenting() {
        Calendar date = new GregorianCalendar();
        return date.getTime();
    }

    public Date getExpectingDateToReturn() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, DAYS_FOR_RENT);
        return cal.getTime();
    }

    public String getMovieResponse( String userName,String movieName){
        return userName + " rented the movie: " +movieName;
    }

    public RentReport getAllRantingDataForAllUsers(String username, String password) {
        try {
            User userAdmin = userRepository.getByUsernameAndPassword(username, password);
            if (userAdmin.getType().equals("admin")) {
                RentReport result = rentRepository.getAll();
                return result;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}


