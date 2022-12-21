package com.example.demo.api_controller;

import com.example.demo.model.GameUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<GameUser, Long> {

    boolean existsByUsername(String username);

    Optional<GameUser> findGameUserByUsername(String username);
}
