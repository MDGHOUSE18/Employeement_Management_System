package com.company.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.cms.DTO.DepartmentDTO;
import com.company.cms.DTO.EmployeeDTO;
import com.company.cms.entity.Department;
import com.company.cms.entity.Employee;
import com.company.cms.service.DepartmentService;

@RestController
public class DepartmentController {
	@Autowired  
	private DepartmentService departmentService; 
	
	//mapping the getProduct() method to /product  
	@GetMapping(value = "/departments")  
	public List<DepartmentDTO> getAllDepartments()   
	{  
	  
	return departmentService.getAllDepartments();  
	}
	@GetMapping(value = "/departments/{id}") 
	public DepartmentDTO getDepartmentById(@PathVariable Long id)   
	{  
	  
	return departmentService.getDepartmrntById(id);  
	}
	
	@PostMapping("/departments")
	public List<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO department) {
		
		return departmentService.addDepartment(department);
		
	}
	@PutMapping("/departments")
	public List<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO department) throws Exception {
		
		return departmentService.updateDepartment(department);
		
	}
	
	@DeleteMapping("departments/{id}")
    public List<DepartmentDTO> deleteDepartmentById(@PathVariable Long id) {
        return departmentService.deleteDepartmentById(id);
    }
	@GetMapping("/department/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Departments.xls";

		response.setHeader(headerKey, headerValue);
		
		departmentService.generateExcel(response);
		
		response.flushBuffer();
	}
	
	
}
