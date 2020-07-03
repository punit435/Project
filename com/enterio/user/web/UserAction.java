package com.enterio.user.web;

import java.io.Serializable;

import com.enterio.foundation.domain.User;
import com.enterio.init.StrutsActionSupport;
import com.enterio.user.service.UserService;
import com.enterio.util.StringUtil;

public class UserAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = 2658887973920977814L;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String passwordChangeMessage;
	private User user;
	private UserService userService;

	public String dashboard()
	{
		return SUCCESS;
	}
	
	public String changePassword()
	{
		return SUCCESS;
	}
	
	public String savePassword()
	{
		oldPassword = StringUtil.checkNull(oldPassword);
		newPassword = StringUtil.checkNull(newPassword);
		confirmPassword = StringUtil.checkNull(confirmPassword);
		
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			if(newPassword.equals(confirmPassword)) 
			{
				user = getUserService().changePassword(sessionUser, oldPassword, newPassword);
				if(user != null)
				{
					session.remove("USER_OBJECT");
					passwordChangeMessage = "Your password has been changed successfully. Please Re-login using new password.";
				}
			}
			else
			{	
				passwordChangeMessage = "Password cannot be changed. Please re-validate and enter correct information.";
			}
		}

		return SUCCESS;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPasswordChangeMessage() {
		return passwordChangeMessage;
	}
	public User getUser() {
		return user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}