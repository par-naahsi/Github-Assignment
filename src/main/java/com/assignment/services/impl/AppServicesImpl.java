package com.assignment.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.assignment.access.SearchRepository;
import com.assignment.access.UserRepository;
import com.assignment.entity.GitObject;
import com.assignment.entity.Search;
import com.assignment.service.AppServices;
import com.assignment.util.RestClient;
import com.google.gson.Gson;


@Component
@Repository
@Service
public class AppServicesImpl implements AppServices {
	
	@Autowired 
	private UserRepository userRepository;

	@Autowired 
	private SearchRepository searchRepository;

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
			this.sessionFactory = sf;
		}
	
	public String searchApi(String name) throws Exception  {
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
	public  String testApi2() throws Exception {
		    GitObject object = new GitObject();
		   // object.setId(id);
		    object.setName("test123");
		    
		    userRepository.save(object);
		    return "Saved";
	}
	
	
	
	public  List<GitObject> testApi3() throws Exception {
		   // GitObject object = new GitObject();
		    return (List<GitObject>) userRepository.findAll();

	}
	
	
	public String getSearchedNames() throws Exception {
		   // GitObject object = new GitObject();
		    //return (List<Search>) searchRepository.findAll();
		    
		    Gson gson = new Gson();
		    String jsonList = gson.toJson(searchRepository.findAll());
		    return jsonList;

	}
	
	
	public  String deleteApi( Integer id) throws Exception {
	
	   Search object = searchRepository.findById(id)
       .orElseThrow(() -> new Exception("not found"));	    
	   searchRepository.delete(object);

	    return  "delete successful";

		
		

	}

	
}
