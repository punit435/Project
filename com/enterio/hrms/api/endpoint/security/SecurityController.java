package com.enterio.hrms.api.endpoint.security;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterio.foundation.domain.User;
import com.enterio.user.service.UserService;
import com.enterio.util.StringUtil;

public class SecurityController implements Serializable
{
	private static final long serialVersionUID = 5440862338801185564L;
	@Autowired
	private UserService userService;
	
	protected String getAuthTokenFromHeader(HttpHeaders httpheaders) throws JSONException
	{
		String authToken = "";
		if(httpheaders != null)
		{
			authToken = StringUtil.checkNull(httpheaders.getHeaderString("Authorization").substring("Bearer".length()));
		}
		
		return authToken;
	}
	
	protected User getUser(HttpHeaders httpheaders) throws JSONException
	{
		User user = null;
		
		if(httpheaders != null)
		{
			user = userService.getUserByAuthToken(getAuthTokenFromHeader(httpheaders));
		}
		
		return user;
	}
	
	protected JSONObject getError(Response.Status responseStatus, String errorMessage) throws JSONException
	{
		JSONObject errorObj = null;
		errorMessage = StringUtil.checkNull(errorMessage);
		
		if(responseStatus != null && errorMessage.length() > 0)
		{
			errorObj = new JSONObject();
			errorObj.put("status_code", responseStatus.getStatusCode());
			errorObj.put("status_name", responseStatus);
			errorObj.put("message", errorMessage);
		}
		
		return errorObj;
	}
	
	protected JSONObject getResponse(Response.Status responseStatus, String successMessage, Map<String, String> data) throws JSONException
	{
		JSONObject responseObj = null;
		successMessage = StringUtil.checkNull(successMessage);
		
		if(responseStatus != null && successMessage.length() > 0)
		{
			responseObj = new JSONObject();
			responseObj.put("status_code", responseStatus.getStatusCode());
			responseObj.put("status_name", responseStatus);
			responseObj.put("message", successMessage);
			if(data != null && data.size() > 0)
			{
				JSONObject dataObj = new JSONObject();
				String key = null;
				Iterator<String> itr = data.keySet().iterator();
				while(itr.hasNext())
				{
					key = itr.next();
					if(key != null)
					{
						if(data.get(key).indexOf("{") == 0)
						{
							dataObj.put(key, new JSONObject(data.get(key)));
						}
						else if(data.get(key).indexOf("[") == 0)
						{
							dataObj.put(key, new JSONArray(data.get(key)));
						}
					}
				}
				responseObj.put("data", dataObj);
			}
		}
		
		return responseObj;
	}
}