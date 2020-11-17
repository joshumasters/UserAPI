package com.tts.usersapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.tts.usersapi.model.User;
import com.tts.usersapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/v1")
@Api(value="users", description="Operations pertaining to Users")
public class UserControllerV1 {
    
    @Autowired
    private UserRepository userRepository;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved users"),
        @ApiResponse(code = 401, message = "You are not authorized to view the users")
                }) 
    @ApiOperation(value = "Get all users", response = User.class, 
                  responseContainer = "List")  
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(@RequestParam(value="state", required=false) String state){
        if (state != null) {
            List<User> userList = userRepository.findByState(state);
            return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        } else {
        Iterable<User> userList = userRepository.findAll();
        return new ResponseEntity<List<User>>((List<User>)userList, HttpStatus.OK);    
        }
}

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved user by id"),
        @ApiResponse(code = 404, message = "Invalid ID")
            }) 
    @ApiOperation(value = "Get a user by id", response = User.class, 
              responseContainer = "List")  
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>>  getUserById(@PathVariable(value="id") Long id){
        Optional<User> myUser = userRepository.findById(id);
        if(!myUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        return new ResponseEntity<>(myUser, HttpStatus.OK);
    }
    

}
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Successfully created User"),
        @ApiResponse(code = 400, message = "The server could not understand the request due to invalid syntax.")
        }) 
    @ApiOperation(value = "Create User") 
    @PostMapping("/")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
           userRepository.save(user);
           return new ResponseEntity<>(HttpStatus.CREATED);
        } 
}
        
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "User successfully updated"),
        @ApiResponse(code = 400, message = "The server could not understand the request due to invalid syntax."),
        @ApiResponse(code = 404, message = "User not found")
    }) 
    @ApiOperation(value = "Update User") 
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable(value="id") Long id, @RequestBody @Valid User user, BindingResult bindingResult){
    Optional<User> myUser = userRepository.findById(id);
    if(!myUser.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else if (bindingResult.hasErrors()) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "User deleted"),
        @ApiResponse(code = 404, message = "User not found")
}) 
    @ApiOperation(value = "Delete User") 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value="id") Long id){
    Optional<User> myUser = userRepository.findById(id);
    if(!myUser.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
}
}
