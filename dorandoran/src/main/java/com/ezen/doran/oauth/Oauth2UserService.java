package com.ezen.doran.oauth;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ezen.doran.entity.CustomUserDetails;
import com.ezen.doran.entity.User;
import com.ezen.doran.oauth.provider.OAuth2UserInfo;
import com.ezen.doran.repository.UserRepository;

@Service
public class Oauth2UserService extends DefaultOAuth2UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	//�넗�겙 諛쒗뻾 �썑 �궗�슜�옄 �젙蹂댁뿉 ���븳 泥섎━
	//�냼�뀥 濡쒓렇�씤 踰꾪듉 �겢由� -> �궗�슜�옄 �룞�쓽 李� -> �궗�슜�옄 �룞�쓽 �썑 濡쒓렇�씤 �셿猷� -> code 媛� 由ы꽩 ->
	//�넗�겙�닔�졊 -> �넗�겙�쓣 �넻�빐 �궗�슜�옄 �젙蹂� 痍⑤뱷 -> loadUser 硫붿냼�뱶 �옄�룞 �샇異� -> �궗�슜�옄 �젙蹂대�� 而ㅼ뒪�꽣留덉씠吏�
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) 
			throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> temp = oAuth2User.getAttributes();
		
		Iterator<String> iter = temp.keySet().iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
			System.out.println(userRequest.getAccessToken().getTokenValue());
		}
		
		String userName= "";
		String providerId = "";
		
		OAuth2UserInfo oAuth2UserInfo = null;
		
//		//�냼�뀥移댄뀒怨좊━ 寃�利�
//		if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
//			oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
//			providerId = oAuth2UserInfo.getProviderId();
//			userName = oAuth2UserInfo.getName();
//		} else {
//			System.out.println("移댁뭅�삤 濡쒓렇�씤留� 吏��썝�빀�땲�떎.");
//		}
		
		String provider = oAuth2UserInfo.getProvider();
		//userId = kakao_1234212
		String userId = provider + "_" + providerId;
		String password = passwordEncoder.encode(oAuth2UserInfo.getName());
		String email = oAuth2UserInfo.getEmail();
		String role = "ROLE_USER";
		
		//�궗�슜�옄媛� �씠誘� �냼�뀥濡쒓렇�씤�븳 湲곕줉�씠 �엳�뒗吏� 寃��궗�븷 媛앹껜
		User user;
		
		if(userRepository.findByUserId(userId).isPresent()) {
			//�씠誘� �냼�뀥 濡쒓렇�씤�븳 湲곕줉�씠 議댁옱�븯硫�
			//�젙蹂대�� user �뿏�떚�떚�뿉 �떞�븘以�
			user = userRepository.findByUserId(userId).get();
		} else {
			//�냼�뀥 濡쒓렇�씤�븳 湲곕줉�씠 �뾾�쑝硫�
			//null濡� 由ы꽩�븯�뿬 �쉶�썝媛��엯 泥섎━
			user = null;
		}
		
		//�쉶�썝媛��엯 泥섎━
		if(user == null) {
			user = User.builder()
					   .userId(userId)
					   .userPw(password)
					   .userNm(userName)
					//   .userMail(userEmail)
					   .userRole(role)
					   .build();
			
			//異붽� �엯�젰�궗�빆�씠 �엳�쑝硫� User �뿏�떚�떚瑜� 媛�吏�怨�
			//異붽� �엯�젰 �럹�씠吏�濡� �씠�룞
			//異붽� �엯�젰 �궗�빆�씠 �엯�젰�맂 �썑 �쉶�썝媛��엯 泥섎━
			userRepository.save(user);
		}
		
		
		//SecurityContext�뿉 �씤利� �젙蹂� ���옣
		return CustomUserDetails.builder()
								.user(user)
								.attributes(oAuth2User.getAttributes())
								.build();
	}
	
}
