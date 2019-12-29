package com.assignment.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.entity.GitObject;

@Component
@Resource(name="test1")
public interface AppServices {
	public String searchApi(String name) throws Exception  ;
	
	public List<GitObject> testApi3() throws Exception;
	
	
	public String getSearchedNames() throws Exception;
	
	
	public @ResponseBody String deleteApi(Integer id) throws Exception;
}
