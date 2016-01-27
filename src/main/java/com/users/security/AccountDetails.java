package com.users.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.users.jpa.Account;
import com.users.jpa.repository.AccountRepository;

@Service
public class AccountDetails implements UserDetailsService{	
	
	@Autowired
	public AccountRepository userDaoSerObj;
	private static Logger LOGGER = LoggerFactory.getLogger(AccountDetails.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Account user = userDaoSerObj.findByUsername(username);
		LOGGER.info("loaded username : {} with clientId : {} ", username, user.getClientId());
		return user;
	}
}