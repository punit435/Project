package com.enterio.hrms.api.endpoint.common;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.enterio.common.service.CommonService;
import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.Month;
import com.enterio.foundation.domain.User;
import com.enterio.hrms.api.endpoint.security.Secured;
import com.enterio.hrms.api.endpoint.security.SecurityController;
import com.enterio.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Secured
@Path("common")
public class CommonController extends SecurityController implements Serializable
{
	private static final long serialVersionUID = 5440862338801185564L;
	
	@Autowired
	private CommonService commonService;
	
	protected String getAuthTokenFromHeader(String authorizationheader) throws JSONException
	{
		String authToken = "";
		authorizationheader = StringUtil.checkNull(authorizationheader);
		
		if(authorizationheader.length() > 0)
		{
			authToken = StringUtil.checkNull(authorizationheader.substring("Bearer".length()));
		}
		
		return authToken;
	}
	
	protected JSONObject getError(HttpStatus responseStatus, String errorMessage) throws JSONException
	{
		JSONObject errorObj = null;
		errorMessage = StringUtil.checkNull(errorMessage);
		
		if(responseStatus != null && errorMessage.length() > 0)
		{
			errorObj = new JSONObject();
			errorObj.put("status_name", responseStatus);
			errorObj.put("message", errorMessage);
		}
		
		return errorObj;
	}
	
	protected JSONObject getResponse(HttpStatus responseStatus, String successMessage, Map<String, String> data) throws JSONException
	{
		JSONObject responseObj = null;
		successMessage = StringUtil.checkNull(successMessage);
		
		if(responseStatus != null && successMessage.length() > 0)
		{
			responseObj = new JSONObject();
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
	
	@Secured
	@Path("/getMonth")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMonths(@Context HttpHeaders httpheaders) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		Map<String, String> data = new HashMap<String, String>();
		
		User user = getUser(httpheaders);
		if(user != null)
		{
			List<Month> months = null;
			ObjectMapper mapper = new ObjectMapper();
			
			months = commonService.getMonth();

			if (months.size() <= 0) 
			{
				responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
				responseObj = getError(responseStatus, "not found");

				return Response.status(responseStatus).entity(responseObj.toString()).build();
			}
			else
			{
					data.put("month", mapper.writeValueAsString(months));
			}
			
		}
		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);
		
		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}
	
	@Secured
	@Path("/getYear")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getYears(@Context HttpHeaders httpheaders) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		Map<String, String> data = new HashMap<String, String>();
		
		User user = getUser(httpheaders);
		if(user != null)
		{
			List<FinancialYear> years = null;
			ObjectMapper mapper = new ObjectMapper();
			
			years = commonService.getYear();

			if (years.size() <= 0) 
			{
				responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
				responseObj = getError(responseStatus, "not found");

				return Response.status(responseStatus).entity(responseObj.toString()).build();
			}
			else
			{
					data.put("year", mapper.writeValueAsString(years));
			}
			
		}
		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);
		
		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}
}