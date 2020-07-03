package com.enterio.hrms.api.endpoint.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.api.endpoint.security.Secured;
import com.enterio.hrms.api.endpoint.security.SecurityController;
import com.enterio.user.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/user")
public class AuthController extends SecurityController implements Serializable
{
	private static final long serialVersionUID = 5440862338801185564L;
	@Autowired
	private UserService userService;
	
	@Path("/authenticate")
	@POST    
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(String requestBody) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		JSONObject errorObj = null;

		ObjectMapper mapper = new ObjectMapper();
		JsonNode authObj = mapper.readTree(requestBody);
		
		if(authObj == null || !authObj.has("email") || !authObj.has("password"))
		{
			responseStatus = Response.Status.BAD_REQUEST;
			errorObj = getError(responseStatus, "email and password are mandatory");
			
			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		
		User user = userService.authenticateUser(authObj.get("email").asText(), authObj.get("password").asText());
		if(user == null)
		{
			responseStatus = Response.Status.NOT_FOUND;
			errorObj = getError(responseStatus, "email or password does not match");
			
			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("user", mapper.writeValueAsString(user));
		
		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);
		
		return Response.status(responseStatus).entity(responseObj.toString()).build();		
	}
	
	@Path("/forgotpassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response forgotPassword(String requestBody) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		JSONObject errorObj = null;

		ObjectMapper mapper = new ObjectMapper();
		JsonNode authObj = mapper.readTree(requestBody);
		
		if(authObj == null || !authObj.has("email"))
		{
			responseStatus = Response.Status.BAD_REQUEST;
			errorObj = getError(responseStatus, "email is mandatory");
			
			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		
		boolean isPasswordChanged = userService.forgotPassword(authObj.get("email").asText());
		if(!isPasswordChanged)
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "password cannot be changed");
			
			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
			
		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", null);
		
		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}	
	
	@Secured
	@Path("/logout")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@Context HttpHeaders httpheaders) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		JSONObject errorObj = null;
		
		User user = getUser(httpheaders);
/*		
		boolean isTokenReset = userService.resetUserToken(user);
		if(!isTokenReset)
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "user cannot be logged out");
			
			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
*/
		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", null);
		
		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}
}