package com.clinic.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.service.AuthoritieService;

@RestController
@RequestMapping(value = "/auth")
public class RefreshToken{

	@Autowired
	private JWTUtil jwtUtil;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		userDetails user = (userDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String token = jwtUtil.tokenGenerator(user.getUsername());

		String authorities = String.valueOf(((userDetails)user).getAuthorities());
        String role = AuthoritieService.getAuthorities(authorities);

        System.out.println("ROLE no ref=>" + role);
        response.addHeader("ROLE", role);
        response.addHeader("access-control-expose-headers", "ROLE");

		response.addHeader("Refresh", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Refresh");
        
		return ResponseEntity.ok().build();
	}
}
                                                                                                                    