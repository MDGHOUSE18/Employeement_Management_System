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

import com.company.cms.DTO.EmployeeDTO;
import com.company.cms.entity.Department;
import com.company.cms.entity.Employee;
import com.company.cms.repository.DepartmentRepository;
import com.company.cms.repository.EmployeeRepository;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	 
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<EmployeeDTO> getAllEmployees() {
			
			List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
			
			List<Employee> employees = (List<Employee>)employeeRepository.findAll();
			
			for(Employee employee: employees) {
				employeeDTOs.add(getDTOfromDomainForEmployee(employee));
			}
			
			return employeeDTOs;
			
	}
	public EmployeeDTO getEmployeeById(Long id) {
		
		Optional<Employee> employee =  employeeRepository.findById(id);
		
		EmployeeDTO empDTO = getDTOfromDomainForEmployee(employee.get());
		
	    return empDTO;
	}
	public List<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO) throws Exception {
			
			logger.info("Inside addEmployee");
			
			Employee employee = getDomainfromDTOForEmployee(employeeDTO);
			
			employeeRepository.save(employee);
			
			
			return getAllEmployees();
		}


	
private EmployeeDTO getDTOfromDomainForEmployee(Employee employee) {
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		employeeDTO.setAddress(employee.getAddress());
		employeeDTO.setDepartmentName(employee.getDepartment().getName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setGender(employee.getGender());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setPhoneNumber(employee.getPhoneNumber());
		employeeDTO.setSalary(employee.getSalary());
		
		
		return employeeDTO;
		
	}
	
	
	private Employee getDomainfromDTOForEmployee(EmployeeDTO employeeDTO) throws Exception {
	
		Employee employee = new Employee();
		
		Department department = departmentRepository.findByName(employeeDTO.getDepartmentName());
		
		if(department != null) {
			employee.setDepartment(department);
		}else {
			throw new Exception("Department not found");
		}
		
		employee.setAddress(employeeDTO.getAddress());
		employee.setEmail(employeeDTO.getEmail());
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setGender(employeeDTO.getGender());
		employee.setLastName(employeeDTO.getLastName());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setSalary(employeeDTO.getSalary());
		
		
		return employee;
	
	}
public List<EmployeeDTO> getAllEmployeesByDepartment(String departmentName) {
		
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		
		Department department = departmentRepository.findByName(departmentName);
		
		List<Employee> employees = employeeRepository.findAllByDepartment(department);
		
		for(Employee employee: employees) {
			employeeDTOs.add(getDTOfromDomainForEmployee(employee));
		}
		
		return employeeDTOs;
		
	}
	
	public List<EmployeeDTO> updateEmployee(EmployeeDTO employeeDTO) throws Exception {
	
		
		logger.info("Inside update Employee");
		
		Optional<Employee> savedEmp = employeeRepository.findById(employeeDTO.getEmployeeId());
		
		
		if(!savedEmp.isPresent()) {
			throw new Exception("Employee not present");
		}
		
		Employee employee = getDomainfromDTOForEmployee(employeeDTO);
		
		employeeRepository.save(employee);
		
		
		return getAllEmployees();
	
	}
	public List<EmployeeDTO> deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
		return getAllEmployees();
	}


//	private EmployeeDTO getDTOfromSalaryForEmployee(Employee employee) {
//		
//		EmployeeDTO employeeDTO = new EmployeeDTO();
//		
//		
//		employeeDTO.setDepartmentName(employee.getDepartment().getName());
//		
//		employeeDTO.setEmployeeId(employee.getEmployeeId());
//		employeeDTO.setFirstName(employee.getFirstName());
//		
//		employeeDTO.setLastName(employee.getLastName());
//		
//		employeeDTO.setSalary(employee.getSalary());
//		
//		
//		return employeeDTO;
//		
//	}
	
	public List<EmployeeDTO> getAllEmployeesBySalary(Double Salary){
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		List<Employee> employees = (List<Employee>)employeeRepository.findAllBySalary(Salary);
		for(Employee employee:employees) {
//			if(employee.getSalary()>Salary) {
				employeeDTOs.add(getDTOfromDomainForEmployee(employee));
//			}
		}
		return employeeDTOs;
	}
	
	public void generateExcel(HttpServletResponse response) throws Exception{
    	List<Employee> employees=(List<Employee>) employeeRepository.findAll();
    	
    	HSSFWorkbook workbook =new HSSFWorkbook();
    	HSSFSheet sheet=workbook.createSheet();
    	HSSFRow row = sheet.createRow(0);
    	row.createCell(0).setCellValue("Employee_ID");
    	row.createCell(1).setCellValue("Employee_FIRST_NAME");
    	row.createCell(2).setCellValue("Employee_LAST_NAME");
    	row.createCell(3).setCellValue("GENDER");
    	row.createCell(4).setCellValue("EMAIL");
    	row.createCell(5).setCellValue("PHONE_NUMBER");
    	row.createCell(6).setCellValue("SALARY");
    	row.createCell(7).setCellValue("ADDRESS");
    	row.createCell(8).setCellValue("DEPARTMENT_ID");
    	
    	int index=1;
    	for (Employee employee : employees) {
			HSSFRow datarow=sheet.createRow(index);
			datarow.createCell(0).setCellValue(employee.getEmployeeId());
			datarow.createCell(1).setCellValue(employee.getFirstName());
			datarow.createCell(2).setCellValue(employee.getLastName());
			datarow.createCell(3).setCellValue(employee.getGender());
			datarow.createCell(4).setCellValue(employee.getEmail());
			datarow.createCell(5).setCellValue(employee.getPhoneNumber());
			datarow.createCell(6).setCellValue(employee.getSalary());
			datarow.createCell(7).setCellValue(employee.getAddress());
//			datarow.createCell(8).setCellValue(employee.getDepartment());
			index++;
			
		}
    	ServletOutputStream ops=response.getOutputStream();
    	workbook.write(ops);
    	workbook.close();
    	ops.close();
    }

}
