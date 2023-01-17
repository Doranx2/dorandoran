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

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.doran.entity.User;
import com.ezen.doran.repository.UserRepository;
import com.ezen.doran.service.UserService;
import com.ezen.doran.service.mail.MailService;

@Service 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailService mailService;
	
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
		if(!userRepository.findByUserId(user.getUserId()).isEmpty()) {
			return userRepository.findByUserId(user.getUserId()).get();
		}else {
			return null;
		}
	}
	@Override
	public User editPw(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateTempPw(User user) {
		// TODO Auto-generated method stub
	      
      char[] charSet = new char[] 
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
             'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
            'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', 
            '$', '%', '^', '&' }; 
	      
	      StringBuffer sb = new StringBuffer();
	      SecureRandom sr = new SecureRandom(); 
	      sr.setSeed(new Date().getTime()); 
	      int idx = 0; int len = charSet.length; 
	      for (int i=0; i<10; i++) { 
	         idx = sr.nextInt(len); // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다. 
	         sb.append(charSet[idx]); 
	      }

	      String tempLoginPasswd = sb.toString();
	      
	      String mailTitle = user.getUserNm() + "님, 당신의 계정(" + user.getUserId() + ")의 임시 패스워드 입니다.";
	      String mailBody = "임시 패스워드 : " + tempLoginPasswd;
	      mailService.send(user.getUserEmail(), mailTitle, mailBody);


	      // 비밀번호 암호화해주는 메서드
	      tempLoginPasswd = passwordEncoder.encode(tempLoginPasswd);
	      // 데이터 베이스 값은 암호한 값으로 저장시킨다.
	      userRepository.updateTempPw(user.getUserId(), tempLoginPasswd);
	}

}
