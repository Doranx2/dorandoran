/*
 * package com.ezen.doran.service.impl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.ezen.doran.entity.User; import
 * com.ezen.doran.repository.UserRepository; import
 * com.ezen.doran.service.UserService;
 * 
 * @Service public class UserServiceImpl implements UserService {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Override public void join(User user) { userRepository.save(user); }
 * 
 * @Override public User idCheck(User user) {
 * if(!userRepository.findById(user.getUserId()).isEmpty()) { return
 * userRepository.findById(user.getUserId()).get(); } else { return null; } }
 * 
 * }
 */

package com.ezen.doran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.entity.User;
import com.ezen.doran.repository.UserRepository;
import com.ezen.doran.service.UserService;

@Service 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	//회원가입
	@Override
	public void join(User user) {
		int userNo = userRepository.getNextUserNo();
		
		user.setUserNo(userNo);
		// TODO Auto-generated method stub
		userRepository.save(user);
	}
	//아이디 중복체크
	@Override
	public User idCheck(User user) {
		if(!userRepository.findByUserId(user.getUserId()).isEmpty()) {
			return userRepository.findByUserId(user.getUserId()).get();
		}else {
			return null;			
		}
	}  
	//닉네임 중복체크
	@Override
	public User nickCheck(User user) {
		if(!userRepository.findByUserNick(user.getUserNick()).isEmpty()) {
			return userRepository.findByUserNick(user.getUserNick()).get();
		}else {
			return null;			
		}
	}

	//아이디 찾기
	@Override
	public User idfCheck(User user) {
		if(!userRepository.findByUserNmAndUserEmail(user.getUserNm(), user.getUserEmail()).isEmpty()) {
			return userRepository.findByUserNmAndUserEmail(
					user.getUserNm(), user.getUserEmail()).get();
		}else {
			return null;
		}
	}
	//비밀번호 찾기
	@Override
	public User pwfCheck(User user) {
		if(!userRepository.findByUserIdAndUserEmail(user.getUserId(), user.getUserEmail()).isEmpty()) {
			return userRepository.findByUserIdAndUserEmail(user.getUserId(), user.getUserEmail()).get();
		}else {
			return null;
		}
	}

}
