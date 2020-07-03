package com.enterio.hrms.api.endpoint.employee;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.api.endpoint.security.Secured;
import com.enterio.hrms.api.endpoint.security.SecurityController;
import com.enterio.hrms.employee.service.EmployeeService;
import com.enterio.hrms.foundation.domain.AttendanceType;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.EmployeeAttendance;
import com.fasterxml.jackson.databind.ObjectMapper;

@Secured
@Path("employeeAttendance")
public class AttendanceController extends SecurityController implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmployeeService employeeService;

	@SuppressWarnings("unused")
	@Secured
	@Path("/save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAttendance(@Context HttpHeaders httpheaders, String requestBody) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		JSONObject errorObj = null;

		Employee employee = null;
		AttendanceType attendanceType = null;
		User sesssionUser = getUser(httpheaders);

		if(sesssionUser == null )
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "user not found");

			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		ObjectMapper mapper = new ObjectMapper();
		EmployeeAttendance employeeAttendance= mapper.readValue(requestBody, EmployeeAttendance.class);

		Map<String, String> data = new HashMap<String, String>();

		if(employeeAttendance == null )
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			errorObj = getError(responseStatus, "employeeAttendance not saved");

			return Response.status(responseStatus).entity(errorObj.toString()).build();
		}
		else
		{
			employeeService.saveEmployeeAttendance(employee, sesssionUser, employeeAttendance);
			data.put("employeeAttendance", new JSONObject().put("uuid", employeeAttendance.getUuid()).toString());
		}

		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);

		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}

	@Secured
	@Path("/list")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAttendanceByMonth(@Context HttpHeaders httpheaders, @RequestParam String calender) throws JSONException, IOException
	{
		JSONObject requestJson = new JSONObject(calender);
		String year = requestJson.get("year").toString();
		int month =	Integer.parseInt(requestJson.get("month").toString());
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		Employee employee = null;
		User user = getUser(httpheaders);
		Map<String, String> data = new HashMap<String, String>();

		if(user != null && user.getEmployee() != null)
		{
			if (user != null && user.getEmployee() != null) 
			{
				employee = user.getEmployee();
			}
		}
		if(employee == null)
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			responseObj = getError(responseStatus, "user not found");

			return Response.status(responseStatus).entity(responseObj.toString()).build();
		}
		else
		{
			List<EmployeeAttendance> employeeAttendances = null;
			employeeAttendances = employeeService.getAttendanceForEmployeeByMonth(month, year, employee);
			ObjectMapper mapper = new ObjectMapper();

			if (employeeAttendances.size() <= 0)
			{
				responseStatus = Response.Status.NOT_FOUND;
				responseObj = getError(responseStatus, "No Record found this Month");

				return Response.status(responseStatus).entity(responseObj.toString()).build();
			}
			else
			{
				data.put("employeeAttendance", mapper.writeValueAsString(employeeAttendances));
			}
		}

		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);

		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}

	@Secured
	@Path("/getEmployeeAttendanceByLatestDate")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeAttendanceByLatestDate(@Context HttpHeaders httpheaders) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		JSONObject errorObj = null;
		Response.Status responseStatus = null;
		EmployeeAttendance employeeAttendance = null; 

		User user = getUser(httpheaders);
		if(user != null && user.getEmployee() != null)
		{
			int employeeId = user.getEmployee().getId();

			if(employeeId > 0)
			{
				employeeAttendance = employeeService.getAttendanceByLatestDate(employeeId);
			}
			else
			{
				responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
				responseObj = getError(responseStatus, "Employee not found");

				return Response.status(responseStatus).entity(responseObj.toString()).build();
			}
		}

		Map<String, String> data = new HashMap<String, String>();

		if(employeeAttendance == null )
		{
			responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
			responseObj = getError(responseStatus, "Not Record found");

			return Response.status(responseStatus).entity(responseObj.toString()).build();
		}
		else
		{
			Date date = new Date();
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(date);
			cal2.setTime(employeeAttendance.getAttendanceDate());
			boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
					cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

			if (!sameDay) {
				responseStatus = Response.Status.ACCEPTED;
				errorObj = getError(responseStatus, "Enable");

				return Response.status(responseStatus).entity(errorObj.toString()).build();
			}
			else
			{
				errorObj = getError(responseStatus, "Disable");
			}
		}

		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "Disable", data);

		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}

}