package com.company.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.cms.DTO.RoleDTO;
import com.company.cms.service.RoleService;
@RestController
public class RoleContr {
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/roles")  
	public List<RoleDTO> getAllRoles()   
	{  
	  
	return roleService.getAllRoles();  
	}
	
	@PostMapping("/role")
	public List<RoleDTO> addRole(@Valid @RequestBody RoleDTO role) {
		
		return roleService.addRole(role);
		
	}
	@GetMapping("/roles/excel")
	public void generateExcelReport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Departments.xls";

		response.setHeader(headerKey, headerValue);
		
		roleService.generateExcel(response);
		
		response.flushBuffer();
	}
}
