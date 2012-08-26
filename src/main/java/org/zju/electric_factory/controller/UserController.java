package org.zju.electric_factory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zju.electric_factory.entity.User;
import org.zju.electric_factory.service.UserManager;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
	public @ResponseBody List<User> getUsers() {
		return userManager.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody User updateUser(@PathVariable String id,@RequestBody User user){
        userManager.updateUser(user);
        return user;
    }
	
	@RequestMapping(method=RequestMethod.GET,value="/list/{id}",headers="Accept=application/json")
    public @ResponseBody User getUser(@PathVariable String id,@RequestBody User user){
		return userManager.getUser(Long.parseLong(id));
    }
	
	

}
