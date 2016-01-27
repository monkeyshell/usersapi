package com.users.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.users.mongo.User;

@Repository
public interface UserRepository  extends MongoRepository<User, Long> {
	
	List<User> findByClientId(String clientId);
	User findByIdAndClientId(Long id, String clientId);
	
	@Query("'$or':[{'firstName':{'$regex':?0,'$options':'i'}},{'lastName':{'$regex':?0,'$options':'i'}},{'email':{'$regex':?0,'$options':'i'}},{'address':{'$regex':?0,'$options':'i'}},{'state':{'$regex':?0,'$options':'i'}},{'city':{'$regex':?0,'$options':'i'}},{'street':{'$regex':?0,'$options':'i'}},{'phoneNumer':{'$regex':?0,'$options':'i'}},{'alternatePhoneNumber':{'$regex':?0,'$options':'i'}}]")
    String findByEmailOrFirstnameOrLastnameLike(String searchText, String clientId);
	
	@Query("'$or':[{'firstName':{'$regex':?0,'$options':'i'}},{'lastName':{'$regex':?0,'$options':'i'}}]")
    List<User> findByFirstnameOrLastnameLike(String searchText);
}
