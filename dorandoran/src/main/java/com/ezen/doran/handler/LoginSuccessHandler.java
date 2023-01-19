package com.ezen.doran.handler;

import java.io.IOException;

//import javax.security.auth.login.AccountExpiredException;
//import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ezen.doran.entity.CustomUserDetails;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", customUser.getUser());
		
		response.sendRedirect("/");
	}
}
