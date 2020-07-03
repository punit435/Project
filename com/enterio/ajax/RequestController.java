package com.enterio.ajax;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.department.service.DepartmentService;
import com.enterio.hrms.department.service.DepartmentServiceImpl;
import com.enterio.hrms.foundation.domain.Department;
import com.enterio.hrms.foundation.domain.Designation;
import com.enterio.user.service.UserService;
import com.enterio.user.service.UserServiceImpl;
import com.enterio.util.StringUtil;

public class RequestController extends HttpServlet implements Serializable
{
	private static final long serialVersionUID = -4727481684758348559L;
	private DepartmentService departmentService;
	private UserService userService;
	private JSONObject jsonResponse;

	public RequestController() 
    {
        super();
        departmentService = new DepartmentServiceImpl();
        userService = new UserServiceImpl();
    }
    
    public void init(ServletConfig servletConfig) throws ServletException
    {
    	super.init(servletConfig);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		try 
		{
			processRequestErrors(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try 
		{
			processRequestErrors(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}
	
	protected void processRequestErrors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			processRequest(request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void sendResponseToAjax(JSONObject jsonObject, HttpServletResponse response) throws JSONException, IOException
	{
		 response.setContentType("text/json");
		 response.setHeader("PRAGMA:", "NO-CACHE");
		 response.setHeader("EXPIRES", "0");
		 response.setHeader("CACHE-CONTROL", "NO-CACHE");
		 if(jsonObject == null)
		 {
			 jsonObject = new JSONObject();
			 jsonObject.put("ERROR", "Data not found");
		 }
		 response.getWriter().write(jsonObject.toString());
	}
	
	private void processRequest(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException, JSONException 
	{
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("USER_OBJECT") != null)
		{
			User sessionUser = (User) session.getAttribute("USER_OBJECT");
			String requestURI = StringUtil.checkNull(request.getRequestURI()).toLowerCase();
			String actionName = requestURI.length() > 0 ? requestURI.substring(requestURI.lastIndexOf("/")+1).toLowerCase() : "";
			
			if(sessionUser!= null && request != null && actionName.length() > 0)
			{
				if(actionName.indexOf("getdesignations.ajax") != -1)
				{
					String departmentUUID = request.getParameter("DepartmentUUID");
					
					getDesignations(departmentUUID, sessionUser);
				}
				else if(actionName.indexOf("getdepartmentusers.ajax") != -1)
				{
					String departmentUUID = request.getParameter("DepartmentUUID");
					
					getDepartmentUsers(departmentUUID, sessionUser);
				}				
			}
		}
		
		sendResponseToAjax(jsonResponse, response);
	}
	
	private void getDesignations(String departmentUUID, User sessionUser) throws JSONException 
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		JSONArray designationArray = new JSONArray();
		
		if(departmentUUID.length() > 0 && sessionUser != null)
		{
			Department department = departmentService.getDepartment(departmentUUID);
			if(department != null && department.getDesignations() != null && department.getDesignations().size() > 0)
			{
				List<Designation> designationsList = department.getDesignations(); 
				if(designationsList != null && designationsList.size() > 0)
				{
					Designation designation = null;
					for(int i=0; i < designationsList.size(); i++)
					{
						designation = designationsList.get(i);
						if(designation != null)
						{
							JSONObject designationObj = new JSONObject();
							designationObj.put("id", designation.getUuid());
							designationObj.put("name", designation.getName());
							designationArray.put(designationObj);
						}
					}
				}
			}
		}
		
        jsonResponse = new JSONObject();
        jsonResponse.put("designations", designationArray.length() > 0 ? designationArray.toString() : null);
	}
	
	private void getDepartmentUsers(String departmentUUID, User sessionUser) throws JSONException 
	{
		departmentUUID = StringUtil.checkNull(departmentUUID);
		JSONArray userArray = new JSONArray();
		
		if(departmentUUID.length() > 0 && sessionUser != null)
		{
			List<User> departmentUserList = userService.getUsersByDepartment(departmentUUID); 
			if(departmentUserList != null && departmentUserList.size() > 0)
			{
				User user = null;
				for(int i=0; i < departmentUserList.size(); i++)
				{
					user = departmentUserList.get(i);
					if(user != null)
					{
						JSONObject userObj = new JSONObject();
						userObj.put("id", user.getUuid());
						userObj.put("name", user.getName());
						userArray.put(userObj);
					}
				}
			}
		}
		
        jsonResponse = new JSONObject();
        jsonResponse.put("users", userArray.length() > 0 ? userArray.toString() : null);
	}
}