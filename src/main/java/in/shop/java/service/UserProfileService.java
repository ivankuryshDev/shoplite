package in.shop.java.service;

import java.util.List;

import in.shop.java.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
}
