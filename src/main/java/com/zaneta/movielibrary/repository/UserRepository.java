package com.zaneta.movielibrary.repository;


import com.zaneta.movielibrary.models.Movie;
import com.zaneta.movielibrary.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public String save(User user) {
        jdbcTemplate
                .update("INSERT INTO user(username, name, email, password, type) VALUES(?, ?, ?, ?, ?)",
                        user.getUsername(), user.getName(), user.getEmail(), user.getPassword(),
                        user.getType()
                );
        return "User created!";
    }

    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, username, name, email, password, type FROM user WHERE " + "id = ?",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public User getByUsernameAndPassword(String username, String password) {
        return jdbcTemplate.queryForObject("SELECT id, username, name, email, password, type FROM user WHERE " + "username = ? and password = ?",
                BeanPropertyRowMapper.newInstance(User.class), username, password);
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE user SET name=?, email=? WHERE id=?",
                user.getName(), user.getEmail(), user.getId());
    }

    public int updatePassword(int id, String newPassword) {
        return jdbcTemplate.update("UPDATE user SET password=? WHERE id=?",
                newPassword, id);
    }

    public int deleteUser(int id) {
        return jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }

}
