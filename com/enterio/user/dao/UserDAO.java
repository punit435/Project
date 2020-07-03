package com.enterio.user.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.enterio.foundation.domain.User;

public interface UserDAO extends Serializable
{
	public User authenticateUser(Map<Object, String> parameters);
	
	public void updateAuthToken(User user);
	
	public User getUser(String userUUID);
	
	public User getUserByEmployeeUUID(String employeeUUID);

	public User getUserByAuthToken(String authToken);
	
	public User getUserByEmail(String email);

	public void insertUser(User user);

	public void updateEmployeeUser(User user);

	public void updatePassword(User user);
	
	public void resetPassword(User user);
	
	public List<User> getUsersByDepartment(String departmentUUID);
}