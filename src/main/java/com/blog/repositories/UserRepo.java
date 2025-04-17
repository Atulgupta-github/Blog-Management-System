package com.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entites.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByname(String username);
}
