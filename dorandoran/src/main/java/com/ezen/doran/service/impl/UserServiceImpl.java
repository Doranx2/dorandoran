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
	
	@Override
	public void join(User user) {
		int userNo = userRepository.getNextUserNo();
		
		user.setUserNo(userNo);
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public User idCheck(User user) {
		// TODO Auto-generated method stub
		if(!userRepository.findByUserId(user.getUserId()).isEmpty()) {
			return userRepository.findByUserId(user.getUserId()).get();
		}else {
			return null;			
		}
	}  
	
	/*
	@Override
	public User idfCheck(User user) {
		if(!userRepository.findByUserNm(user.getUserNm()){
			return userRepository.findByUserNm(user.getUserNm()).get();
		}*/
	}
	
