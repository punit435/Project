package com.enterio.hrms.api.endpoint.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterio.foundation.domain.User;
import com.enterio.user.service.UserService;
import com.enterio.util.StringUtil;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter extends SecurityController implements ContainerRequestFilter
{
	private static final long serialVersionUID = 8977397435746515677L;
	@Autowired
	private UserService userService;
	
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
    	Response.Status responseStatus = null;
    	JSONObject errorObj = null;
    	
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer"))
        {
			try
			{
	        	responseStatus = Response.Status.UNAUTHORIZED;
				errorObj = getError(responseStatus, "Authorization Header With Bearer Token Is Required");
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			
        	requestContext.abortWith(Response.status(responseStatus).entity(errorObj.toString()).build());
        	return;
        }

        String authToken = StringUtil.checkNull(authorizationHeader.substring("Bearer".length()));
        if(authToken.length() <= 0)
        {
        	responseStatus = Response.Status.UNAUTHORIZED;
			try
			{
				errorObj = getError(responseStatus, "Authorization Header With Bearer Token Is Required");
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			
        	requestContext.abortWith(Response.status(responseStatus).entity(errorObj.toString()).build());
        	return;
        }
        
        User user = userService.getUserByAuthToken(authToken);
        if(user == null)
        {
			try
			{
	        	responseStatus = Response.Status.UNAUTHORIZED;
				errorObj = getError(responseStatus, "Invalid Bearer Token");
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			
        	requestContext.abortWith(Response.status(responseStatus).entity(errorObj.toString()).build());
        	return;
        }
    }
}