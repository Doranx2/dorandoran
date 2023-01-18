package com.ezen.doran.oauth.provider;

public interface OAuth2UserInfo {
	//user 정보 뭐 할 것인지 물어보고 추가하기
	String getProviderId();
	String getProvider();
	String getEmail();
	String getName();

}
