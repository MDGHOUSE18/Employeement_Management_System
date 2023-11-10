package com.company.cms.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.cms.DTO.RoleDTO;
import com.company.cms.entity.Role;
import com.company.cms.entity.Employee;
import com.company.cms.repository.RoleRepo;

@Service
public class RoleService {
	Logger logger = LoggerFactory.getLogger(RoleService.class);
	@Autowired
	private RoleRepo roleRepo;

	public List<RoleDTO> getAllRoles() {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		List<Role> roles = (List<Role>)roleRepo.findAll();
		
		for(Role role: roles) {
			roleDTOs.add(getDTOFromDAOForRole(role));
		}
		
		return roleDTOs;
	}
	
	public List<RoleDTO> addRole(RoleDTO roleDTO) {
		
		
		logger.info("Inside addRole");
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		Role role = getDAOFromDTOForRole(roleDTO);
		
		roleRepo.save(role);
		
		List<Role> roles = (List<Role>)roleRepo.findAll();
		
		for(Role role1: roles) {
			roleDTOs.add(getDTOFromDAOForRole(role1));
		}
		
		return roleDTOs;
	}
	
	private Role getDAOFromDTOForRole(RoleDTO RoleDTO) {
		Role role = new Role();
		
		role.setHead(RoleDTO.getHead());
		role.setId(RoleDTO.getId());
		role.setName(RoleDTO.getName());
		
		
		return role;
	}

	private RoleDTO getDTOFromDAOForRole(Role Role) {
		
		RoleDTO RoleDTO = new RoleDTO();
		
		RoleDTO.setHead(Role.getHead());
		RoleDTO.setId(Role.getId());
		RoleDTO.setName(Role.getName());
		
		
		return RoleDTO;
	}
	
	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Role> Roles = (List<Role>) roleRepo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Roles Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("Role_ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("HEAD");

		int dataRowIndex = 1;

		for (Role role : Roles) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(role.getId());
			dataRow.createCell(1).setCellValue(role.getName());
			dataRow.createCell(2).setCellValue(role.getHead());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}
}

