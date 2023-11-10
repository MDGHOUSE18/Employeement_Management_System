package com.company.cms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.cms.DTO.EmployeeDTO;
import com.company.cms.entity.Employee;
import com.company.cms.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/employees")
	public List<EmployeeDTO> getAllEmployees() {
		
		return employeeService.getAllEmployees();
		
	}
	
	@GetMapping("/employees/bydeparment/{departmentname}")
	public List<EmployeeDTO> getAllEmployeesByDepartment(@PathVariable(name="departmentname") String departmentName) {
		
		return employeeService.getAllEmployeesByDepartment(departmentName);
		
	}
	
	@GetMapping("/employess/bysalary/{salary}")
	public List<EmployeeDTO> getAllEmployeesBySalary(@PathVariable(name="salary") Double Salary){
		return employeeService.getAllEmployeesBySalary(Salary);
	}
//	@GetMapping("/employess/Bysalary/{salary1}")
//	public List<EmployeeDTO> getAllEmployeesbySalary(@PathVariable(name="salary1") Double Salary){
//		return employeeService.getAllEmployeesbySalary(Salary);
//	}
	
	 @GetMapping("/{id}")
	    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
	        return employeeService.getEmployeeById(id);
	    }
	
	@PostMapping("/employees")
	public List<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employee) throws Exception {
		
		return employeeService.addEmployee(employee);
		
	}
	@GetMapping("/employees/excel")
	public void generateExcelResponse(HttpServletResponse response) throws Exception {
response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Employees.xls";

		response.setHeader(headerKey, headerValue);
		
		employeeService.generateExcel(response);
		
		response.flushBuffer();
	}
	


}
