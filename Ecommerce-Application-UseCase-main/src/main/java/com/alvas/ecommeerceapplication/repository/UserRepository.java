package com.alvas.ecommeerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvas.ecommeerceapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
