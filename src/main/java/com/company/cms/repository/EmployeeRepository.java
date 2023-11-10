package com.company.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.cms.entity.Department;
import com.company.cms.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAllByDepartment(Department deparment);
	
	@Query(value="select * from employee where salary>?1",nativeQuery = true)
	List<Employee> findAllBySalary(Double salary);

	

}
