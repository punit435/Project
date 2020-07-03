package com.enterio.hrms.api.endpoint.employee;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import com.enterio.hrms.employee.service.EmployeeService;
import com.enterio.hrms.foundation.domain.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Secured
@Path("employee")
public class EmployeeController extends SecurityController implements Serializable
{
	private static final long serialVersionUID = 999263147266097727L;
	@Autowired
	private EmployeeService employeeService;
	
	@Secured
	@Path("/get")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserDetails(@Context HttpHeaders httpheaders) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		Employee employee = null; 
		
		User user = getUser(httpheaders);
		if(user != null && user.getEmployee() != null)
		{
			employee = employeeService.getEmployee(user.getEmployee().getUuid());
		}
		
		Map<String, String> data = new HashMap<String, String>();
		if(employee == null )
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			responseObj = getError(responseStatus, "user not found");

			return Response.status(responseStatus).entity(responseObj.toString()).build();
		}
		else
		{
			ObjectMapper mapper = new ObjectMapper();
			data.put("employee", mapper.writeValueAsString(employee));
		}

		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);

		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}
}