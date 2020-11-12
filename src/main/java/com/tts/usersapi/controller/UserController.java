package com.tts.usersapi.controller;

import java.util.List;
import java.util.Optional;

import com.tts.usersapi.model.User;
import com.tts.usersapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> getUsers(@RequestParam(value="state", required=false) String state){
        if (state != null) {
        return (List<User>) userRepository.findByState(state);
        }
        return (List<User>) userRepository.findAll();
}

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable(value="id") Long id){
    return userRepository.findById(id);

}

    @PostMapping("/")
    public void createUser(@RequestBody User user){
    userRepository.save(user);
}

    @PutMapping("/{id}")
    public void updateUser(@PathVariable(value="id") Long id, @RequestBody User user){
    userRepository.save(user);
}
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value="id") Long id){
    userRepository.deleteById(id);
}
}
