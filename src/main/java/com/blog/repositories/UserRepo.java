package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entites.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
