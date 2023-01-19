package com.ezen.doran.handler;

import java.io.IOException;

//import javax.security.auth.login.AccountExpiredException;
//import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ezen.doran.entity.CustomUserDetails;
import com.ezen.doran.entity.User;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", customUser.getUser());
		
		User loginUser = (User)session.getAttribute("loginUser");
		
		if(loginUser.getUserRole().equals("SUSPENDED")) {

			// 현재 세션 얻어와서 없애고
			session = request.getSession(false);   

			session.invalidate();

			// 시큐리티 인증정보 없애기
			SecurityContextHolder.getContext().setAuthentication(null);
			
			// 계정정지 예외 발생
			throw new LockedException("계정이 정지되었습니다. 관리자에게 문의하세요.");
		}
		response.sendRedirect("/");
	}
}
