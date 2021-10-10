package com.backend.test.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.test.config.JwtTokenUtil;
import com.backend.test.model.AuthenRequest;
import com.backend.test.model.AuthenResponse;
import com.backend.test.services.ValidateService;
import com.backend.test.utility.CRC32;


@RestController
@RequestMapping("/api")
public class UserJWTController {
	
	@Autowired
	private ValidateService validateService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Value("${application.keyAuthen}")
	public String keyAuthen;
	@Value("${application.userAuthen}")
	public String userAuthen;
	@Value("${application.passAuthen}")
	public String passAuthen;
	
	private final Logger log = LoggerFactory.getLogger(UserJWTController.class);

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenResponse> authorize(@RequestBody AuthenRequest authenRequest) {
		log.info(authenRequest.toString());
		List<String> validateMessages = validateService.validate(authenRequest);
		if (validateMessages.size() > 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	Long input_long = Long.valueOf(authenRequest.getKey());
    	String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	String valueCRC = String.format("%s%s%s", keyAuthen, currentDate, authenRequest.getSalt());
    	String genCRC = CRC32.getCRC(valueCRC.getBytes(),  authenRequest.getKey().length());
    	if (String.valueOf(input_long).equals(genCRC)) {
    		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(userAuthen);
    		final String token = jwtTokenUtil.generateToken(userDetails);
    		return new ResponseEntity<>(new AuthenResponse(token), HttpStatus.OK);
    	}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
}
