package com.zaneta.movielibrary.service;


import com.zaneta.movielibrary.interfaces.UserServiceInterface;
import com.zaneta.movielibrary.models.User;
import com.zaneta.movielibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;


    public String createNewUer(User user) {
        return userRepository.save(user);
    }


    public User getById(int id) {
        return userRepository.getById(id);
    }


    public String updateNameAndEmail(int id, User updateUser) {
        User user = userRepository.getById(id);
        if (user != null) {
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());

            userRepository.update(user);
            return "data updated!";
        } else {
            return "did not update";
        }


    }

    public String changePassword(int id, String previous, String newPassword) {
        User user = userRepository.getById(id);
        if (user != null) {
            if (user.getPassword().equals(previous)) {
                userRepository.updatePassword(id, newPassword);
                return "password updated";
            } else {
                return "wrong password";
            }

        } else {
            return "password cannot be updated";
        }
    }

    public String deleteUser(int id, String username, String password) {
        User user = userRepository.getById(id);
        User userAdmin = userRepository.getByUsernameAndPassword(username, password);
        if (user != null) {
            if (userAdmin.getType().equals("admin")) {
                userRepository.deleteUser(id);
                return "user deleted";
            } else {
                return "you are not allowed to delete the user";
            }

        } else {
            return "user is not found";
        }
    }
}
