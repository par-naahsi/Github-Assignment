package com.assignment.access;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assignment.entity.Search;



public interface SearchRepository extends CrudRepository<Search, Integer> {
	
	List<Search> findByName(String name);
	
	 

}

