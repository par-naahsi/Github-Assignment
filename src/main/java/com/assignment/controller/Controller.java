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
import com.assignment.util.RestClient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
@RestController
@RequestMapping(value = "/fqiapp/service")
public class Controller {

	
	  @Autowired 
	  private UserRepository userRepository;
	  
	  @Autowired 
	  private SearchRepository searchRepository;
	  
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
		//RestClient client = new RestClient();
		Search search = new Search();
		search.setName(name);
		List<Search> search1 = new ArrayList<Search>();
		 search1 = searchRepository.findByName(name);
		if(search1.size()>0) {
			System.out.println("foundddddddd");
		}
		else
		{
		searchRepository.save(search);
		}
		return RestClient.get("https://api.github.com/users/" + name);
	}
	
	@Transactional
	@RequestMapping(value = "/apiController2", method = RequestMethod.GET)
	public @ResponseBody String testApi2() throws Exception {
		    GitObject object = new GitObject();
		   // object.setId(id);
		    object.setName("test123");
		    
		    userRepository.save(object);
		    return "Saved";
	}
	
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<GitObject> testApi3() throws Exception {
		   // GitObject object = new GitObject();
		    return (List<GitObject>) userRepository.findAll();

	}
	
	
	@RequestMapping(value = "/getSearchedNames", method = RequestMethod.GET)
	public @ResponseBody List<Search> getSearchedNames() throws Exception {
		   // GitObject object = new GitObject();
		    return (List<Search>) searchRepository.findAll();

	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody String testApi3(@PathVariable(value = "id") Integer id) throws Exception {
		  
		
	   GitObject object = userRepository.findById(id)
       .orElseThrow(() -> new Exception("not found"));

	    
	    userRepository.delete(object);

	    return  "delete successful";

		
		

	}
	
}
