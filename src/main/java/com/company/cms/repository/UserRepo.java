package com.company.cms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.company.cms.entity.User;

public interface UserRepo extends CrudRepository<User, Integer> {

	Optional<User> findById(Long id);

	

}
