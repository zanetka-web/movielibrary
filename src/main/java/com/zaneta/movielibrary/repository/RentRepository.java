package com.zaneta.movielibrary.repository;

import com.zaneta.movielibrary.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class RentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public boolean hasUserRanted(int id) {
        try {
            Rent rent = jdbcTemplate
                    .queryForObject("SELECT * from rent where userId = ? and isRented = 1",
                            BeanPropertyRowMapper.newInstance(Rent.class), id);
            return rent.getIsRented() == 1;

        } catch (Exception e) {
            return false;
        }
    }

    public Rent getLastMovieRentedReturn(int userId) {
        try {
            Rent lastRent = jdbcTemplate
                    .queryForObject("SELECT * from rent WHERE userId = ? order by dateOfReturn desc limit 1",
                            BeanPropertyRowMapper.newInstance(Rent.class), userId);
            return lastRent;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isMovieAvailable(int movieId) {
        try {
            Rent rent = jdbcTemplate
                    .queryForObject("SELECT * from rent WHERE movieId = ?",
                            BeanPropertyRowMapper.newInstance(Rent.class), movieId);
            return rent.getIsRented() == 0;
        } catch (Exception e) {
            return false;
        }
    }


    public int rent(RentRequest request, Date dateOfRenting, Date expectingReturn) {
        return jdbcTemplate
                .update("INSERT into rent(userId, movieId, isRented, dateOfRenting, expectingDateToReturn) values(?, ?, true, ?, ?)",
                        request.getUserId(), request.getMovieId(), dateOfRenting, expectingReturn);

    }

    public RentReport getAll() {
        return jdbcTemplate
                .queryForObject("SELECT u.username, m.name as movieName, r.isRented, r.dateOfRenting, r.expectingDateToReturn, r.dateOfReturn from rent as r " +
                                " JOIN user as u on r.userId = u.id " +
                                "JOIN movie as m on m.id = r.movieId"
                        , BeanPropertyRowMapper.newInstance(RentReport.class));
    }
}


