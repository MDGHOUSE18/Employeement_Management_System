package com.company.cms.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.cms.entity.Role;

public interface RoleRepo extends CrudRepository<Role, Integer> {

	Role findByName(String roleName);

}
