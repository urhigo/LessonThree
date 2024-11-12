package com.example.services;


import com.example.models.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public void addUser(User user){
        userRepository.save(user);
    }


    public User getUserById(long id){
        if (userRepository.findUserById(id) != null){
            return userRepository.findUserById(id);
        } else {
            throw new NullPointerException("No found ticket with id: " + id);
        }
    }

}
