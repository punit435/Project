package com.enterio.util;

import java.io.Serializable;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class Authenticator implements Serializable, Interceptor
{
	private static final long serialVersionUID = 9045943014148364566L;
	public void destroy() {}
	public void init() {}

	public String intercept(ActionInvocation actionInvocation) throws Exception 
	{
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		Object userObject = session.get("USER_OBJECT");
		if(userObject == null)
		{
			ServletActionContext.getResponse().sendRedirect(ApplicationBase.getBasePath()+"/Login");
			return null;
		}
		
		return actionInvocation.invoke();
	}
}