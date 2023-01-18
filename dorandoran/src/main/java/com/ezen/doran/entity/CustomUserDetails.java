package com.ezen.doran.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails, OAuth2User {
	private User user;
	
	//소셜로그인에서 사용자 정보를 담아줄 맵
	Map<String, Object> attributes;
	
	//권한정보 제공
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		auths.add(
				new GrantedAuthority() {
					@Override
					public String getAuthority() {
						return user.getUserRole();
					}
				}
		);
		return auths;
	}
	
	//비밀번호 제공
	@Override
	public String getPassword() {
		return user.getUserPw();
	}
	
	//아이디 제공
	@Override
	public String getUsername() {
		return user.getUserId();
	}
	
	//계정 만료 여부
	//true:만료 안 됨
	//false:만료
	//사용 안할 때는 항상 true 반환되도록 처리
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	//계정 잠김 여부
	//true: 잠기지 않음
	//false: 잠김
	//사용 안할 때는 항상 true 반환되도록 처리
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//비밀번호 만료 여부
	//계정 인증정보를 항상 저장할 지 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	//계정(사용자) 활성화 여부
	@Override
	public boolean isEnabled() {
		//이메일이 인증되어 있고, 계정이 잠겨있지 않을 경우 true
		return true;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
