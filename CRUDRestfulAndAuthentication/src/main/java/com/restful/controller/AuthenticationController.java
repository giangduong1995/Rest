package com.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restful.pojo.AuthenticationResponse;
import com.restful.pojo.PojoUser;
import com.restful.service.CustomUserDetailsService;
import com.restful.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/")
	public ResponseEntity<?> test() {
		try {
			return ResponseEntity.ok("Hello Restful API");

		} catch (Exception ex) {

			log.error("test - error: " + ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody PojoUser user) {
		try {
			return ResponseEntity.ok(userDetailsService.register(user));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestParam String username, @RequestParam String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					username, password));
			UserDetails userdetails = userDetailsService.loadUserByUsername(username);
			String accessToken = jwtUtil.generateAccessToken(userdetails);
			String refreshToken = jwtUtil.doGenerateRefreshToken(jwtUtil.getClaimsFromToken(accessToken),
					username);
			return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
}
