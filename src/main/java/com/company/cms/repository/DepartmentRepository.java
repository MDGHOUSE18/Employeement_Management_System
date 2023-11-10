package com.company.cms.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.cms.entity.Department;




public interface DepartmentRepository extends CrudRepository<Department, Long>   {

	public Department findByName(String departmentName);

}
