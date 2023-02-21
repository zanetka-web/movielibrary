package com.zaneta.movielibrary.interfaces;


import com.zaneta.movielibrary.models.User;

public interface UserServiceInterface {

    User getById(int id);
    String createNewUer(User user);

    String updateNameAndEmail(int id, User updateUser);

    String changePassword(int id, String previous, String newPassword);

    String deleteUser(int id, String username, String password);
}
