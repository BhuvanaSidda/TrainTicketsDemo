package com.proj.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.user.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

	UserDetails findByUserName(String username);

}
