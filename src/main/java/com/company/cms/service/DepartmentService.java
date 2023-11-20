package com.company.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.cms.DTO.DepartmentDTO;
import com.company.cms.DTO.EmployeeDTO;
import com.company.cms.entity.Department;
import com.company.cms.entity.Employee;
import com.company.cms.repository.DepartmentRepository;

@Service
public class DepartmentService {
	Logger logger = LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<DepartmentDTO> getAllDepartments() {
		List<DepartmentDTO> departmentDTOs = new ArrayList<DepartmentDTO>();
		
		List<Department> departments = (List<Department>)departmentRepository.findAll();
		
		for(Department dept: departments) {
			departmentDTOs.add(getDTOFromDAOForDepartment(dept));
		}
		
		return departmentDTOs;
	}
	public DepartmentDTO getDepartmrntById(Long id) {
			
			Optional<Department> department =  departmentRepository.findById(id);
			
			DepartmentDTO depDTO = getDTOFromDAOForDepartment(department.get());
			
		    return depDTO;
		}
	public List<DepartmentDTO> addDepartment(DepartmentDTO departmentDTO) {
		
		
		logger.info("Inside adddepartment");
		
		Department department = getDAOFromDTOForDepartment(departmentDTO);
		
		departmentRepository.save(department);
		
		return getAllDepartments();
	}
	
	private Department getDAOFromDTOForDepartment(DepartmentDTO departmentDTO) {
		Department department = new Department();
		
		department.setHead(departmentDTO.getHead());
		department.setId(departmentDTO.getId());
		department.setName(departmentDTO.getName());
		
		
		return department;
	}

	private DepartmentDTO getDTOFromDAOForDepartment(Department department) {
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		
		departmentDTO.setHead(department.getHead());
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());
		
		
		return departmentDTO;
	}
	public List<DepartmentDTO> updateDepartment(DepartmentDTO departmentDTO) throws Exception {
		
			
			logger.info("Inside update department");
			
			Optional<Department> savedDep = departmentRepository.findById(departmentDTO.getId());
			
			
			if(!savedDep.isPresent()) {
				throw new Exception("Department not present");
			}
			
			Department department = getDAOFromDTOForDepartment(departmentDTO);
			
			departmentRepository.save(department);
			
			
			return getAllDepartments();
		
	}
	public List<DepartmentDTO> deleteDepartmentById(Long id) {
			departmentRepository.deleteById(id);
			return getAllDepartments();
	}
	public void generateExcel(HttpServletResponse response) throws Exception {

		List<Department> departments = (List<Department>) departmentRepository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Departments Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("DEPARTMENT_ID");
		row.createCell(1).setCellValue("DEPARTMENT_NAME");
		row.createCell(2).setCellValue("DEPARTMENT_HEAD");

		int dataRowIndex = 1;

		for (Department department : departments) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(department.getId());
			dataRow.createCell(1).setCellValue(department.getName());
			dataRow.createCell(2).setCellValue(department.getHead());
			dataRowIndex++;
		}

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}
}
