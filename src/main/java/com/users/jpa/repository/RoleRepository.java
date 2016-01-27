package com.users.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.users.jpa.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
