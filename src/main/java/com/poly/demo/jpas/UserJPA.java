package com.poly.demo.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.UserEntity;



public interface UserJPA extends JpaRepository<UserEntity, Integer> {
	@Query(name = "SELECT * FROM users WHERE username=?1 OR email=?2", nativeQuery = true)
	  public List<UserEntity> findByUsernameAndEmail(String username, String email);
}
