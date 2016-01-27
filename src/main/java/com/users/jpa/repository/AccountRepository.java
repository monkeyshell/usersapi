package com.users.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.users.jpa.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

	Account findByUsername(final String username);

	List<Account> findByClientId(final String clientId);

	
	@Modifying
	@Transactional
	@Query("update Account a set a.password = ?1 where a.username = ?2")
	int setFixedPasswordFor(String password, String username);

	@Modifying
	@Transactional
	@Query("delete from Account a where a.username = ?1")
	int deleteAccount(String username);
	
	@Query(value = "select * from account a where a.clientId = ?1 order by creationTime ASC LIMIT 1", nativeQuery = true)
	Account findUsersOrderByCreationTime(String clientId);
}
