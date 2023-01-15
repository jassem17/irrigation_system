package com.mycompany.smart_irrigation.repositories;

import com.mycompany.smart_irrigation.entities.User;
import jakarta.nosql.mapping.Page;
import jakarta.nosql.mapping.Pagination;
import jakarta.nosql.mapping.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends Repository<User, String> {

    Stream<User> findAll();

    Optional<User> findByUsername(String username);



}
