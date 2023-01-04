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

import org.springframework.stereotype.Service;

import com.ezen.doran.entity.User;
import com.ezen.doran.service.UserService;

@Service 
public class UserServiceImpl implements UserService {

	@Override
	public void join(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User idCheck(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}