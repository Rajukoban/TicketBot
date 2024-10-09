package com.server.controller;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.AuthenticationRequest;
import com.server.dto.SignupRequest;
import com.server.dto.SignupRequestUser;
import com.server.dto.UserDto;
import com.server.model.User;
import com.server.repository.UserRepository;
import com.server.service.EmailService;
import com.server.service.UserService;
import com.server.service.UserServiceDao;
import com.server.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/jwt")
public class UserController {
	
	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsService userDetailsService;
	
	private final UserRepository userRepository;
	
	private final JwtUtil jwtUtil;
	
	public static final String TOKEN_PREFIX="Bearer";
	public static final String HEADER_STRING="Authorization";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceDao userDao;
	
	@Autowired
	private EmailService emailService;
	
	//Authenticate User name and password
	
	@PostMapping("/authenticate")
	public void addUser(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response)throws IOException, JSONException{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails.getUsername());
		
		if(optionalUser.isPresent()) {
			response.getWriter().write(new JSONObject()
					.put("userId",optionalUser.get().getUserId())
					.put("role",optionalUser.get().getRole())
					.toString());
			
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-Control-Allow-Headers", "Authorization , X-PINGOTHER, Origin, "+ "X-Requested-With, Content-Type, Accept, X-Custom-header");
		response.addHeader(HEADER_STRING,TOKEN_PREFIX+ jwt);
		}
		
	}
	
//	@PostMapping("/sign-up")
//	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
//		
//		if(userDao.hasUserWithEmail(signupRequest.getEmail())) {
//			return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
//		}
//		
//		UserDto userDto=userDao.createUser(signupRequest);
//		return new ResponseEntity<>(userDto,HttpStatus.OK);
//		
//	}
	
	//save user Details
	
	@PostMapping("/sign-up1")
	public ResponseEntity<?> signupUser1(@RequestBody SignupRequestUser signupRequest){
		
		if(userDao.hasUserWithEmail(signupRequest.getEmail())) {
			return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto userDto=userDao.createUser1(signupRequest);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{id}")
	public UserDto getUserById(@PathVariable Long id){
		try {
			UserDto userDto=userService.getUserDetailsById(id);
			return userDto;
		}catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	@GetMapping("/hii/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUserDetails(@PathVariable Long userId) {
		userService.deleteUserById(userId);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUserD(@PathVariable Long userId,@RequestBody SignupRequestUser signupRequest){
		try {
			UserDto updateUserDto=userService.updateDetails(userId, signupRequest);
			return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	//forgot password
	
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody Map<String,String> request){
		String email=request.get("email");
		Optional<User> userOptional=userService.findByEmail(email);
		
		if(userOptional.isPresent()) {
			String token=jwtUtil.generateToken(email);
			emailService.sendEmail("no-reply@example.com", email, "Password Reset Request", "To reset your password, please click the link below:\n"+"http://localhost:4200/reset-password?token="+token);
			return ResponseEntity.ok("Password reset link has been sent to your email.");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email address not found");
		}
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String,String> request){
		String token=request.get("token");
		String newPassword=request.get("password");
		
		String email=jwtUtil.extractUsername(token);
		Optional<User> userOptional=userService.findByEmail(email);
		
		if(userOptional.isPresent()) {
			User user=userOptional.get();
			user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			userRepository.save(user);
			emailService.sendEmail("${spring.mail.username}", email, "Password Changed Successfull", "Your Password will be changed successfully.");
			return ResponseEntity.ok("Password reset successfully.");
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Token");
		}
	}
	
	
}
