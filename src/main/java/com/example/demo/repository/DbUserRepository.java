package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.DbUser;

public interface DbUserRepository extends CrudRepository<DbUser, Long> {

}
