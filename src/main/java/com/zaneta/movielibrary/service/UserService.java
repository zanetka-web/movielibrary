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
        try {
            return userRepository.save(user);
        } catch (Exception e){
            return null;
        }

    }

    public User getById(int id) {
        try {
            return userRepository.getById(id);
        } catch (Exception e) {
            return null;
        }
    }


    public String updateNameAndEmail(int id, User updateUser) {
        try {
            User user = userRepository.getById(id);

            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());

            userRepository.update(user);
            return "data updated!";
        } catch (Exception e) {
            return "did not update";
        }
    }

    public String changePassword(int id, String previous, String newPassword) {
        try {
            User user = userRepository.getById(id);

            if (user.getPassword().equals(previous)) {
                userRepository.updatePassword(id, newPassword);
                return "password updated";
            } else {
                return "wrong password";
            }

        } catch (Exception e) {
            return "password cannot be updated " + e.getMessage();
        }
    }

    public String deleteUser(int id, String username, String password) {

        try {
            User user = userRepository.getById(id);
            User userAdmin = userRepository.getByUsernameAndPassword(username, password);
            if (userAdmin.getType().equals("admin")) {
                userRepository.deleteUser(id);
                return "user deleted";
            } else {
                return "you are not allowed to delete the user";
            }
        } catch (Exception e) {
            return "user is not found " + e.getMessage();
        }
    }
}
