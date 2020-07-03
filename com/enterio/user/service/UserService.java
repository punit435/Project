package com.enterio.user.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;


public interface UserService extends Serializable
{
	public User authenticateUser(String email, String password);

	public User getUser(String userUUID);
	
	public User getUserByEmployeeUUID(String employeeUUID);

	public User getUserByAuthToken(String authToken);
	
	public boolean forgotPassword(String email);
	
	public User changePassword(User user, String oldPassword, String newPassword);
	
	public List<User> getUsersByDepartment(String departmentUUID);
}