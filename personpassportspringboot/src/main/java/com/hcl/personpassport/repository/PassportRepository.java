package com.hcl.personadhar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.personadhar.dto.Passport;

public interface PassportRepository extends JpaRepository<Passport, Integer> {

}
