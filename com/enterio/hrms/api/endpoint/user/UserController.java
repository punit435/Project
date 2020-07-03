package com.enterio.hrms.api.endpoint.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.api.endpoint.security.Secured;
import com.enterio.hrms.api.endpoint.security.SecurityController;
import com.enterio.user.service.UserService;
import com.enterio.util.CryptographyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Secured
@Path("/user")
public class UserController extends SecurityController implements Serializable
{
	private static final long serialVersionUID = 5440862338801185564L;
	@Autowired
	private UserService userService;

	@Secured
	@Path("/change-password")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(@Context HttpHeaders httpheaders, @RequestParam String payload) throws JSONException, IOException
	{
		JSONObject requestJson = new JSONObject(payload);
		String newPassword =	requestJson.get("newPassword").toString();
		String confirmPassword =	requestJson.get("confirmPassword").toString();
		String password =	requestJson.get("password").toString();

		requestJson.get("newPassword");
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		JSONObject errorObj = null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> data = new HashMap<String, String>();
		data.put("Payload", payload);

		String encryptedPassword = CryptographyUtil.generateDigest(password);

		User sesssionUser = getUser(httpheaders);
		if(sesssionUser == null ) 
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "user not found"); 

			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		if (!newPassword.equals(confirmPassword))
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "Password did't matched"); 

			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		if(password == null || newPassword  == null || confirmPassword == null)
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "Password not changed");

			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		if (!encryptedPassword.equals(sesssionUser.getPassword()))
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "You entered wrong password");

			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		else
		{
			userService.changePassword(sesssionUser, password, newPassword);
			/*data.put("user", new JSONObject().put("uuid", user.getUuid()).toString());*/
		}

		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);

		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}
}