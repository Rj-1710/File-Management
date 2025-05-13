package com.mphasis.parent.service;

import com.mphasis.parent.entity.UserDetails;


public interface UserDetailService {
	UserDetails registerUser(String userName, String password);
	String getUser(String userName, String password);
	//void updateUserToken(String userName, String token);
	//void deleteUser(String username);
	void validateUser(String userName, String password);
}
