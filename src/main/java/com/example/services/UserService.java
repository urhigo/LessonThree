package com.example.services;


import com.example.models.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public void addUser(User user){
        userRepository.save(user);
    }


    public User getUserById(long id){
        if (userRepository.findById(id) != null){
            return userRepository.findById(id);
        } else {
            throw new NullPointerException("No found ticket with id: " + id);
        }
    }

}
