package com.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.access.SearchRepository;
import com.assignment.access.UserRepository;
import com.assignment.entity.GitObject;
import com.assignment.entity.Search;
import com.assignment.service.AppServices;
import com.assignment.util.RestClient;
import com.google.gson.Gson;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
@RestController
@RequestMapping(value = "/app/service")
public class Controller {

	  @Autowired 
	  private AppServices appServices;
	  
	  private SessionFactory sessionFactory;
	
	  public void setSessionFactory(SessionFactory sf){
			this.sessionFactory = sf;
		}
	  
	@RequestMapping(value = "/testController", method = RequestMethod.GET)
	public @ResponseBody String test() {
		return "test";
	}
	
	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
	public @ResponseBody String searchApi(@PathVariable(value = "name") String name) throws Exception {
		return appServices.searchApi(name);
	}
	/*
	@Transactional
	@RequestMapping(value = "/apiController2", method = RequestMethod.GET)
	public @ResponseBody String testApi2() throws Exception {
		    GitObject object = new GitObject();
		   // object.setId(id);
		    object.setName("test123");
		    
		    userRepository.save(object);
		    return "Saved";
	}
	*/
	/*
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<GitObject> testApi3() throws Exception {
		   // GitObject object = new GitObject();
		    return (List<GitObject>) userRepository.findAll();

	}
	*/
	
	@RequestMapping(value = "/getSearchedNames", method = RequestMethod.GET)
	public @ResponseBody String getSearchedNames() throws Exception {
		   // GitObject object = new GitObject();
		    //return (List<Search>) searchRepository.findAll();
		    
		    return appServices.getSearchedNames();

	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody String testApi3(@PathVariable(value = "id") Integer id) throws Exception {
	
		return appServices.deleteApi(id);

		
		

	}
	
}
