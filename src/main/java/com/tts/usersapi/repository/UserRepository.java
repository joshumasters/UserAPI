package com.tts.usersapi.repository;

import com.tts.usersapi.model.User;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    List<User> findByState(String state);
}
