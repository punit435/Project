package com.enterio.user.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.enterio.common.service.CommonServiceImpl;
import com.enterio.foundation.domain.User;
import com.enterio.user.dao.UserDAO;
import com.enterio.util.CryptographyUtil;
import com.enterio.util.DateUtil;
import com.enterio.util.HMAC;
import com.enterio.util.PasswordGenerator;
import com.enterio.util.StringUtil;

public class UserServiceImpl extends CommonServiceImpl implements Serializable,UserService 
{
	private static final long serialVersionUID = 7013165453408779685L;
	
	public User authenticateUser(String email, String password)
	{
		User user = null;
		email = StringUtil.checkNull(email);
		password = StringUtil.checkNull(password);

		if(email.length() > 0 && password.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				Map<Object, String> parameterMap = new HashMap<Object, String>();
				parameterMap.put("Email", email);
				parameterMap.put("Password", CryptographyUtil.generateDigest(password));

				UserDAO userMapper = session.getMapper(UserDAO.class);
				user = userMapper.authenticateUser(parameterMap);

				if(user != null && user.getUuid().length() > 0)
				{
					String authToken = HMAC.generateToken(user.getEmail()+"#$%777()^&^%^%");
					if(authToken != null && authToken.length() > 0)
					{
						user.setAuthToken(authToken);
						user.setTokenExpiresOn(DateUtil.addDays(DateUtil.getCurrentDateTime(), 5));
						userMapper.updateAuthToken(user);
						session.commit();
					}
					
					user = userMapper.getUser(user.getUuid());
				}
			}
			closeDBSession(session);
		}

		return user;
	}
	
	public User getUser(String userUUID) 
	{
		User user = null;
		userUUID = StringUtil.checkNull(userUUID);

		if(userUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				UserDAO userMapper = session.getMapper(UserDAO.class);
				user = userMapper.getUser(userUUID);
			} 
			closeDBSession(session);
		}

		return user;
	}
	
	public User getUserByEmployeeUUID(String employeeUUID)
	{
		User user = null;
		employeeUUID = StringUtil.checkNull(employeeUUID);

		if(employeeUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				UserDAO userMapper = session.getMapper(UserDAO.class);
				user = userMapper.getUserByEmployeeUUID(employeeUUID);
			} 
			closeDBSession(session);
		}

		return user;
	}
	
	public User getUserByAuthToken(String authToken)
	{
		User user = null;
		authToken = StringUtil.checkNull(authToken);

		if(authToken.length() > 0)
		{
			SqlSession session = super.getDBSession();
			if(session != null)
			{
				UserDAO userMapper = session.getMapper(UserDAO.class);
				user = userMapper.getUserByAuthToken(authToken);
			}
			super.closeDBSession(session);
		}

		return user;
	}
	
	public boolean forgotPassword(String email) 
	{
		boolean isEmailExists = false;
		email = StringUtil.checkNull(email);

		if(email.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				UserDAO userMapper = session.getMapper(UserDAO.class);
				User user = userMapper.getUserByEmail(email);
				if(user != null)
				{
					String password = PasswordGenerator.generatePassword();
					user.setPassword(CryptographyUtil.generateDigest(password));
					userMapper.resetPassword(user);
					session.commit();
					isEmailExists = true;
				}
			}
			closeDBSession(session);
		}

		return isEmailExists;
	}
	
	public User changePassword(User user, String oldPassword, String newPassword) 
	{
		oldPassword = StringUtil.checkNull(oldPassword);
		newPassword = StringUtil.checkNull(newPassword);
		
		if(user != null && oldPassword.length() > 0 && newPassword.length() > 0)
		{
			user = getUser(user.getUuid());
			if(user != null)
			{
				String dbCryptedPassword = user.getPassword();
				String enteredOldCryptedPassword = CryptographyUtil.generateDigest(oldPassword);

				dbCryptedPassword = StringUtil.checkNull(dbCryptedPassword);
				if(dbCryptedPassword.equals(enteredOldCryptedPassword))
				{
					SqlSession session = getDBSession();
					if(session != null)
					{
						String newCryptedPassword = CryptographyUtil.generateDigest(newPassword);
						user.setPassword(newCryptedPassword);
						UserDAO userMapper = session.getMapper(UserDAO.class);
						userMapper.updatePassword(user);
						session.commit();
					}
					closeDBSession(session);
				}
				else 
				{
					user = null;
				}
			}
		}		

		return user;
	}
	
	public List<User> getUsersByDepartment(String departmentUUID)
	{
		List<User> userList = null;
		departmentUUID = StringUtil.checkNull(departmentUUID);
		
		if(departmentUUID.length() > 0)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				UserDAO userMapper = session.getMapper(UserDAO.class);
				userList = userMapper.getUsersByDepartment(departmentUUID);
			}
			closeDBSession(session);
		}
		
		return userList;
	}
}