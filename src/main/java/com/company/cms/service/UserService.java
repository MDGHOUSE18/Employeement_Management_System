//package com.company.cms.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.company.cms.DTO.UserDTO;
//import com.company.cms.entity.Role;
//import com.company.cms.entity.User;
//import com.company.cms.repository.RoleRepo;
//import com.company.cms.repository.UserRepo;
//
//@Service
//public class UserService {
//
//	Logger logger = LoggerFactory.getLogger(UserService.class);
//	 
//	@Autowired
//	private UserRepo userRepo;
//	
//	@Autowired
//	private RoleRepo roleRepo;
//	
//	public List<UserDTO> getAllUsers() {
//		
//		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
//		
//		List<User> users = (List<User>)userRepo.findAll();
//		
//		for(User user: users) {
//			userDTOs.add(getDTOfromDomainForUser(user));
//		}
//		
//		return userDTOs;
//		
//	}
//	public java.util.Optional<User> getUserById(Integer id) {
//        return userRepo.findById(id);
//    }
//	public List<UserDTO> addUser(UserDTO userDTO) throws Exception {
//		
//		logger.info("Inside addUser");
//		
//		User user = getDomainfromDTOForUser(userDTO);
//		
//		userRepo.save(user);
//		
//		
//		return getAllUsers();
//	}
//
//	
//	private UserDTO getDTOfromDomainForUser(User User) {
//		
//		UserDTO userDTO = new UserDTO();
//		
//		userDTO.setEmail(User.getEmail());
//		userDTO.setFullname(User.getFullname());
//		userDTO.setId(User.getId());
//		userDTO.setPassword(User.getPassword());
//		userDTO.setRoleName(User.getRole().getName());
//		
//		
//		
//		return userDTO;
//		
//	}
//	
//	private User getDomainfromDTOForUser(UserDTO userDTO) throws Exception {
//		
//		User user = new User();
//		
//		Role role = roleRepo.findByName(userDTO.getRoleName());
//		
//		if(role != null) {
//			user.setRole(role);
//		}else {
//			throw new Exception("Role not found");
//		}
//		user.setEmail(userDTO.getEmail());
//		user.setFullname(userDTO.getFullname());
//		user.setId(userDTO.getId());
//		user.setPassword(userDTO.getPassword());
//		
//		return user;
//		
//	}
//	public List<UserDTO> getAllUsersByRole(String RoleName) {
//		
//		List<UserDTO> UserDTOs = new ArrayList<UserDTO>();
//		
//		Role Role = RoleRepository.findByName(RoleName);
//		
//		List<User> Users = UserRepository.findAllByRole(Role);
//		
//		for(User User: Users) {
//			UserDTOs.add(getDTOfromDomainForUser(User));
//		}
//		
//		return UserDTOs;
//		
//	}
//	private UserDTO getDTOfromSalaryForUser(User User) {
//		
//		UserDTO UserDTO = new UserDTO();
//		
//		
//		UserDTO.setRoleName(User.getRole().getName());
//		
//		UserDTO.setUserId(User.getUserId());
//		UserDTO.setFirstName(User.getFirstName());
//		
//		UserDTO.setLastName(User.getLastName());
//		
//		UserDTO.setSalary(User.getSalary());
//		
//		
//		return UserDTO;
//		
//	}
//	
//	public List<UserDTO> getAllUsersBySalary(Double Salary){
//		List<UserDTO> UserDTOs = new ArrayList<UserDTO>();
//		List<User> Users = (List<User>)userRepo.findAllBySalary(Salary);
//		for(User User:Users) {
////			if(User.getSalary()>Salary) {
//				UserDTOs.add(getDTOfromDomainForUser(User));
////			}
//		}
//		return UserDTOs;
//	}
//	
//	public void generateExcel(HttpServletResponse response) throws Exception{
//    	List<User> Users=(List<User>) userRepo.findAll();
//    	
//    	HSSFWorkbook workbook =new HSSFWorkbook();
//    	HSSFSheet sheet=workbook.createSheet();
//    	HSSFRow row = sheet.createRow(0);
//    	row.createCell(0).setCellValue("User_ID");
//    	row.createCell(1).setCellValue("User_FIRST_NAME");
//    	row.createCell(2).setCellValue("User_LAST_NAME");
//    	row.createCell(3).setCellValue("GENDER");
//    	row.createCell(4).setCellValue("EMAIL");
//    	row.createCell(5).setCellValue("PHONE_NUMBER");
//    	row.createCell(6).setCellValue("SALARY");
//    	row.createCell(7).setCellValue("ADDRESS");
//    	row.createCell(8).setCellValue("Role_ID");
//    	
//    	int index=1;
//    	for (User User : Users) {
//			HSSFRow datarow=sheet.createRow(index);
//			datarow.createCell(0).setCellValue(User.getUserId());
//			datarow.createCell(1).setCellValue(User.getFirstName());
//			datarow.createCell(2).setCellValue(User.getLastName());
//			datarow.createCell(3).setCellValue(User.getGender());
//			datarow.createCell(4).setCellValue(User.getEmail());
//			datarow.createCell(5).setCellValue(User.getPhoneNumber());
//			datarow.createCell(6).setCellValue(User.getSalary());
//			datarow.createCell(7).setCellValue(User.getAddress());
////			datarow.createCell(8).setCellValue(User.getRole());
//			index++;
//			
//		}
//    	ServletOutputStream ops=response.getOutputStream();
//    	workbook.write(ops);
//    	workbook.close();
//    	ops.close();
//    }
//	
//	
//
//}
