package com.betaplan.krisela.beltexam.services;

import com.betaplan.krisela.beltexam.models.LoginUser;
import com.betaplan.krisela.beltexam.models.User;
import com.betaplan.krisela.beltexam.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User newUser, BindingResult result){
        Optional<User> potentialUser = this.userRepository.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent()){
            result.rejectValue("email", "EmailTaken","Email address is already in use.");
        }
        if (!newUser.getPassword().equals(newUser.getConfirm())){
            result.rejectValue("confirm", "Matches", "The confirm password must match the pasword.");
        }
        if(result.hasErrors()){
            return null;
        }
        else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepository.save(newUser);
        }
    }

    public User login(LoginUser newLoginObject, BindingResult result){
        Optional<User> potentialUser = this.userRepository.findByEmail(newLoginObject.getEmail());
        if(!potentialUser.isPresent()){
            result.rejectValue("email", "EmailNotFound","No user found with this email.");
        } else {
            if(!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())){
                result.rejectValue("password", "PasswordNotMatches", "Invalid password.");
            }
        }

        if(result.hasErrors()){
            return null;
        } else {
            return  potentialUser.get();
        }
    }


    public User findOneUser(Long id){
        return this.userRepository.findById(id).orElse(null);
    }
}

