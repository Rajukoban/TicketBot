package com.server.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.dto.SignupRequest;
import com.server.dto.SignupRequestUser;
import com.server.dto.UserDto;
import com.server.enums.Role;
import com.server.model.User;
import com.server.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService implements UserServiceDao{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	public UserDto createUser(SignupRequest signupRequest) {
//		List<String> message=new ArrayList<String>();
//		User user=new User();
//		
//		user.setEmail(signupRequest.getEmail());
//		user.setName(signupRequest.getName());
//		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
//		user.setRole(Role.PROJECT_TEAM_MEMBER);
//		//user.setRole(signupRequest.getRole());
//		
//		User userdata=userRepository.save(user);
//		UserDto userDto=new UserDto();
//		userDto.setId(userdata.getUserId());
//		if(userdata!=null) {
//			message.add("User details saved!");
//			emailService.sendEmail("${spring.mail.username}", user.getEmail(), "Account Created", "Your Account has been created successflly.");
//		}else {
//			message.add("Please enter the user details");
//		}
//		
//		return userDto;
//	}
	
	//Fetch All the Users from User Table
	
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	//Check Email Id is present or not
	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
    //fetch User Details based on user Id
    public UserDto getUserDetailsById(Long id) {
    	Optional<User> useropt=userRepository.findById(id);
    	if(useropt.isPresent()) {
    		User user=useropt.get();
    		UserDto userDto=new UserDto();
    		userDto.setId(user.getUserId());
    		userDto.setEmail(user.getEmail());
    		userDto.setName(user.getName());
    		userDto.setUserRole(user.getRole());
    		return userDto;
    	}else {
    		throw new IllegalArgumentException("User not found");
    	}
    }
    
    //fetch user Details
    public Optional<User> getUserById(Long id) {
    	return userRepository.findById(id);
    }
    
//    Update The User Details based on userId
//    public User updateUserDetails(Long userId,User user) {
//    	User userInfo=userRepository.findById(userId).get();
//    		userInfo.setName(user.getName());
//    		userInfo.setEmail(user.getEmail());
//    		userInfo.setRole(user.getRole());
//    		User save=userRepository.save(userInfo);
//    		return save;
//    }
    
    //Deleting User Details based on userId
    public void deleteUserById(Long userId) {
    	User user=userRepository.findById(userId).get();
    	userRepository.deleteById(userId);
    	emailService.sendEmail("${spring.mail.username}", user.getEmail(), "Account Delete", "Your Account has been Deleted.");
    }
    
    
    //Create New Employee Detail
    public UserDto createUser1(SignupRequestUser signupRequest) {
		List<String> message=new ArrayList<String>();
		User user=new User();
		
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(signupRequest.getRole());
		
		
		User userdata=userRepository.save(user);
		UserDto userDto=new UserDto();
		userDto.setId(userdata.getUserId());
		if(userdata!=null) {
			message.add("User details saved!");
			emailService.sendEmail("${spring.mail.username}", user.getEmail(), "Account Created", "Your Account has been created successflly.");
		}else {
			message.add("Please enter the user details");
		}
		
		return userDto;
	}
    
    public UserDto updateDetails(Long userId,SignupRequestUser signupRequest) {
    	Optional<User> userData=userRepository.findById(userId);
    	if(userData.isPresent()) {
    		User user=userData.get();
    		
    		user.setName(signupRequest.getName());
    		user.setEmail(signupRequest.getEmail());
    		
    		if(signupRequest.getPassword() !=null && !signupRequest.getPassword().isEmpty()) {
    			user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
    		}
    		
    		user.setRole(signupRequest.getRole());
    		
    		User updateUser=userRepository.save(user);
    		
    		UserDto userDto=new UserDto();
    		userDto.setId(updateUser.getUserId());
    		userDto.setName(updateUser.getName());
    		userDto.setEmail(updateUser.getEmail());
    		userDto.setUserRole(updateUser.getRole());
    		return userDto;
    	}else {
    		throw new IllegalArgumentException("User not found"+userId);
    	}
    }
	
	@PostConstruct
	public void createReportingManager() {
		List<User> reportingManager=userRepository.findByRole(Role.REPORTING_MANAGER);
		if(null==reportingManager) {
			User user=new User();
			user.setEmail("RManager@gmail.com");
			user.setName("Abhisheck Kumar");
			user.setRole(Role.REPORTING_MANAGER);
			user.setPassword(new BCryptPasswordEncoder().encode("reporting"));
			userRepository.save(user);
		}
	}
	
	@PostConstruct
	public void createITSupportTeam() {
		List<User> itsupport=userRepository.findByRole(Role.IT_SUPPORT_TEAM);
		if(null==itsupport) {
			User user=new User();
			user.setEmail("IT@gmail.com");
			user.setName("Afzal Md");
			user.setRole(Role.IT_SUPPORT_TEAM);
			user.setPassword(new BCryptPasswordEncoder().encode("itsupport"));
			userRepository.save(user);
		}
	}
	
	@PostConstruct
	public void createSystemAdmin() {
		List<User> itsupport=userRepository.findByRole(Role.ADMIN);
		if(null==itsupport) {
			User user=new User();
			user.setEmail("admin@gmail.com");
			user.setName("Admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
			userRepository.save(user);
		}
	}
	
	//forgot password
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
    

}
