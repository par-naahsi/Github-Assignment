package com.assignment.access;

import org.springframework.data.repository.CrudRepository;

import com.assignment.entity.GitObject;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<GitObject, Integer> {

}

