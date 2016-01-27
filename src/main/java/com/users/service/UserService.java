package com.users.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.users.mongo.User;
import com.users.mongo.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired private UserRepository userRepo;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User addUser(User user){
		LOGGER.info("Adding a new user in UserDB " + user.getId());
		return userRepo.save(user);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public User updateUser(User user){
		LOGGER.info("Updating a new user in UserDB " + user.getId());
		User u = userRepo.findByIdAndClientId(user.getId(), user.getClientId());
		if(u == null)
			return null;
		u.setId(user.getId());
		u.setFirstName(user.getFirstName() == null ? u.getFirstName() : user.getFirstName());
		u.setLastName(user.getLastName() == null ? u.getLastName() : user.getLastName());
		u.setEmail(user.getEmail() == null ? u.getEmail() : user.getEmail());
		u.setPhoneNumber(user.getPhoneNumber() == null ? u.getPhoneNumber() : user.getPhoneNumber());
		u.setAlternatePhoneNumber(user.getAlternatePhoneNumber() == null ? u.getAlternatePhoneNumber() : user.getAlternatePhoneNumber());
		u.setState(user.getState() == null ? u.getState() : user.getState());
		u.setCity(user.getCity() == null ? u.getState() : user.getState());
		u.setAddress(user.getAddress() == null ? u.getAddress() : user.getAddress());
		u.setStreet(user.getStreet() == null ? u.getStreet() : user.getStreet());
		return userRepo.save(u);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public List<User> getUsers(String clientId){
		LOGGER.info("Getting a list of users in UserDB for clientId " + clientId);
		return userRepo.findByClientId(clientId);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public User getUser(Long id, String clientId){
		LOGGER.info("Getting a list of users in UserDB for clientId " + clientId);
		return userRepo.findByIdAndClientId(id, clientId);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public void searchUsers(String searchText, String clientId){
		LOGGER.info("Searching list of users by term " + searchText);
		System.out.println(userRepo.findByFirstnameOrLastnameLike(searchText));
	}
	
}
