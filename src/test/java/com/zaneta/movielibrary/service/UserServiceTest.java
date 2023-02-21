package com.zaneta.movielibrary.service;


import com.zaneta.movielibrary.models.User;
import com.zaneta.movielibrary.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void shouldCreateNewUer() {
        User user = new User(1, "Neti", "Żaneta", "hello@gmail.com", "szafa", "admin");
        when(userRepository.save((User) any())).thenReturn("user created!");

        String result = userService.createNewUer(user);
        assertThat(result).isSameAs("user created!");
    }

    @Test
    void shouldGetById() {
        User expected = new User(1, "Neti", "Żaneta", "hello@gmail.com", "szafa", "admin");
        when(userRepository.getById(1)).thenReturn(expected);

        User user = userService.getById(1);
        assertThat(user.getId()).isSameAs(expected.getId());
    }

    @Test
    void shouldUpdateNameAndEmail() {
        when(userRepository.getById(any(Integer.class))).thenReturn(new User());
        when(userRepository.update((User) any())).thenReturn(1);

        String result = userService.updateNameAndEmail(1, new User());
        assertThat(result).isSameAs("data updated!");
    }

    @Test
    void shouldNotUpdateNameAndEmail() {
        when(userRepository.getById(any(Integer.class))).thenReturn(null);

        String result = userService.updateNameAndEmail(1, new User());
        assertThat(result).isSameAs("did not update");
    }

    @Test
    void shouldChangePassword() {
        User user = new User(1, "Neti", "Żaneta", "hello@gmail.com", "szafa", "admin");
        when(userRepository.getById(1)).thenReturn(user);
        when(userRepository.updatePassword(1, "okno")).thenReturn(1);

        String result = userService.changePassword(1, "szafa", "okno");
        assertThat(result).isSameAs("password updated");
    }

    @Test
    void shouldNotChangePasswordWhileIsWrong() {
        User user = new User(1, "Neti", "Żaneta", "hello@gmail.com", "szafa", "admin");
        when(userRepository.getById(1)).thenReturn(user);
        when(userRepository.updatePassword(1, "okno")).thenReturn(1);

        String result = userService.changePassword(1, "drzwi", "okno");
        assertThat(result).isSameAs("wrong password");
    }

    @Test
    void shouldNotChangePasswordWhileNull() {
        when(userRepository.getById(any(Integer.class))).thenReturn(null);
        String result = userService.changePassword(1, "drzwi", "okno");
        assertThat(result).isSameAs("password cannot be updated");
    }

    @Test
    void shouldDeleteUser() {
        User user = new User(1, "Paulon", "Paulo", "hello@gmail.com", "krzesło", "user");
        User Admin = new User(2, "Neti", "Żaneta", "hello@gmail.com", "szafa", "admin");
        when(userRepository.getById(1)).thenReturn(user);
        when(userRepository.getByUsernameAndPassword("Neti", "szafa")).thenReturn(Admin);
        when(userRepository.deleteUser(1)).thenReturn(1);

        String result = userService.deleteUser(1, "Neti", "szafa");

        assertThat(result).isSameAs("user deleted");
    }

    @Test
    void shouldNotDeleteUserWhileAdminPasswordAndUsernameAreWrong() {
        User user = new User(1, "Paulon", "Paulo", "hello@gmail.com", "krzesło", "user");
        when(userRepository.getById(1)).thenReturn(user);
        when(userRepository.getByUsernameAndPassword(any(String.class), any(String.class))).thenReturn(user);
        when(userRepository.deleteUser(1)).thenReturn(1);

        String result = userService.deleteUser(1, "Paulon", "krzesło");

        assertThat(result).isSameAs("you are not allowed to delete the user");
    }

    @Test
    void shouldNotDeleteUserWhileNull() {
        when(userRepository.getById(any(Integer.class))).thenReturn(null);

        String user = userService.deleteUser(1, "Neti", "szafa");
        assertThat(user).isSameAs("user is not found");

    }
}