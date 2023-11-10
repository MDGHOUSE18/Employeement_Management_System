package com.company.cms.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.cms.entity.User;

public interface UserRepo extends CrudRepository<User, Integer> {

	

}
