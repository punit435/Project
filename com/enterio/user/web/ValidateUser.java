package com.enterio.user.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.enterio.foundation.domain.User;
import com.enterio.foundation.domain.UserModule;
import com.enterio.init.StrutsActionSupport;
import com.enterio.user.service.UserService;
import com.enterio.util.StringUtil;

public class ValidateUser extends StrutsActionSupport implements Serializable, SessionAware
{
	private static final long serialVersionUID = 1L;
	private String email;
    private String password;
    private UserService userService;
    
    public String login()
    {
    	return SUCCESS;
    }
    
    public String validateUser()
    {
    	User user = getUserService().authenticateUser(email, password);
    	if(user == null)
    	{
    		addActionError("User email / Password does not match");
    		redirectURL= "Login";
    	}
    	else
    	{
    		if(user.getLastLoggedIn() == null)
    		{
    			redirectURL = "User/ChangePassword";
    		}
    		else 
    		{
    			redirectURL = "User/Dashboard";
    		}
    		
			Map<String, String> modulesMap = new HashMap<String, String>();
			List<UserModule> modules = user.getModules();
			if(modules != null && modules.size() > 0)
			{
				for(int i=0; i < modules.size(); i++)
				{
					modulesMap.put(modules.get(i).getModule().getName().toUpperCase(), modules.get(i).getUserType().getCode().toUpperCase());
				}
				
				user.setModuleUserType(modulesMap);
			}
        	session.put("USER_OBJECT", user);
    	}
    	
    	return SUCCESS;
    }

    private void destroySession()
  	{
  		if(session instanceof SessionMap)
  		{
  			((org.apache.struts2.dispatcher.SessionMap<String, Object>) session).invalidate();
  		}
  	}
    
    public String logout()
	{
    	destroySession();
    	
    	return SUCCESS;
	}
    
	public String forgotPassword()
	{
		String returnType = "";
		email = StringUtil.checkNull(email);

		if(email.length() > 0)
		{
			boolean isEmailExists = getUserService().forgotPassword(email);
			if(isEmailExists)
			{
				returnType = "resetsuccess";
			}
			else
			{
				returnType = "reseterror";
			}
		}
		
		return returnType;
	}
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}