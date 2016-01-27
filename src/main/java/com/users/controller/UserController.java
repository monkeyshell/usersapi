package com.users.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.users.jpa.Account;
import com.users.mongo.User;
import com.users.pojo.UserResponse;
import com.users.pojo.UsersResponse;
import com.users.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	@Autowired private UserService userService;
	

	@RequestMapping(method = RequestMethod.POST)
	public UserResponse createUser(@RequestBody User user){
		LOGGER.info("Creating a new user request " + user.getFirstName());
		UserResponse ur = new UserResponse();
		ur.setStartTime(sdf.format(Calendar.getInstance().getTime()));
		long start = System.currentTimeMillis();
		ur.setAnyError(false);
		ur.setStatus("OK");
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setClientId(account.getClientId());
		ur.setUser(userService.addUser(user));
		ur.setReponseTime(System.currentTimeMillis()-start);
		ur.setEndTime(sdf.format(Calendar.getInstance().getTime()));
		return ur;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public UserResponse createUser(@RequestBody User user, @PathVariable Long id){
		LOGGER.info("Creating a new user request " + user.getFirstName());
		UserResponse ur = new UserResponse();
		ur.setStartTime(sdf.format(Calendar.getInstance().getTime()));
		long start = System.currentTimeMillis();
		ur.setAnyError(false);
		ur.setStatus("OK");
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setClientId(account.getClientId());
		ur.setUser(userService.updateUser(user));
		ur.setReponseTime(System.currentTimeMillis()-start);
		ur.setEndTime(sdf.format(Calendar.getInstance().getTime()));
		return ur;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public UsersResponse getUsers(){
		LOGGER.info("Fetching a list of users request");
		UsersResponse ur = new UsersResponse();
		ur.setStartTime(sdf.format(Calendar.getInstance().getTime()));
		long start = System.currentTimeMillis();
		ur.setAnyError(false);
		ur.setStatus("OK");
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<User> users = userService.getUsers(account.getClientId());
		ur.setUsers(users);
		ur.setCount(users.size());
		ur.setReponseTime(System.currentTimeMillis()-start);
		ur.setEndTime(sdf.format(Calendar.getInstance().getTime()));
		return ur;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public UserResponse getUser(@PathVariable Long id){
		LOGGER.info("Fetching a list of users request");
		UserResponse ur = new UserResponse();
		ur.setStartTime(sdf.format(Calendar.getInstance().getTime()));
		long start = System.currentTimeMillis();
		ur.setAnyError(false);
		ur.setStatus("OK");
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUser(id,account.getClientId());
		ur.setUser(user);
		ur.setReponseTime(System.currentTimeMillis()-start);
		ur.setEndTime(sdf.format(Calendar.getInstance().getTime()));
		return ur;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public UsersResponse searchUsers(@RequestParam String searchText){
		LOGGER.info("Searching a list of users request");
		UsersResponse ur = new UsersResponse();
		ur.setStartTime(sdf.format(Calendar.getInstance().getTime()));
		long start = System.currentTimeMillis();
		ur.setAnyError(false);
		ur.setStatus("OK");
		Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userService.searchUsers(searchText, account.getClientId());
//		ur.setUsers(users);
//		ur.setCount(users.size());
		ur.setReponseTime(System.currentTimeMillis()-start);
		ur.setEndTime(sdf.format(Calendar.getInstance().getTime()));
		return ur;
	}

}
