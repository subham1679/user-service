package com.shubham.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
