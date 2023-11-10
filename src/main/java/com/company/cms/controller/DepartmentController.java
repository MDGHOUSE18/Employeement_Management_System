package com.company.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.cms.DTO.DepartmentDTO;
import com.company.cms.entity.Department;
import com.company.cms.entity.Employee;
import com.company.cms.service.DepartmentService;

@RestController
public class DepartmentController {
	@Autowired  
	private DepartmentService departmentService; 
	
	//mapping the getProduct() method to /product  
	@GetMapping(value = "/department")  
	public List<DepartmentDTO> getAllDepartments()   
	{  
	  
	return departmentService.getAllDepartments();  
	}
	
	@PostMapping("/department")
	public List<DepartmentDTO> addDepartment(@Valid @RequestBody DepartmentDTO department) {
		
		return departmentService.addDepartment(department);
		
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
