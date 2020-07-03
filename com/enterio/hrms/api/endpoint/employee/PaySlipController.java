package com.enterio.hrms.api.endpoint.employee;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterio.foundation.domain.FinancialYear;
import com.enterio.foundation.domain.User;
import com.enterio.hrms.api.endpoint.security.Secured;
import com.enterio.hrms.api.endpoint.security.SecurityController;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.employee.service.EmployeeService;
import com.enterio.hrms.foundation.domain.Employee;
import com.enterio.hrms.foundation.domain.PaySlip;
import com.enterio.util.FileEncryptionUtil;
import com.enterio.util.PropertyLoader;
import com.fasterxml.jackson.databind.ObjectMapper;

@Secured
@Path("employeePayslip")
public class PaySlipController extends SecurityController implements Serializable{

	private static final long serialVersionUID = 7762920940798391076L;

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CommonServiceImpl hrmsCommonService;
	String FILE_PATH = PropertyLoader.getProperty("PAYSLIP_UPLOAD") + File.separator; 

	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public CommonServiceImpl getHrmsCommonService() {
		return hrmsCommonService;
	}
	public void setHrmsCommonService(CommonServiceImpl hrmsCommonService) {
		this.hrmsCommonService = hrmsCommonService;
	}

	@Secured
	@Path("/paySlipList/{yearId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAttendanceByMonth(@Context HttpHeaders httpheaders, @PathParam("yearId") int yearId) throws JSONException, IOException
	{
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		Employee employee = null;
		FinancialYear financialYear1 = null;
		FinancialYear financialYear2 = null;
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
			financialYear1 = getHrmsCommonService().getYearById(yearId);
			financialYear2 = getHrmsCommonService().getYearById(yearId+1);

			List<PaySlip> paySlips = null;
			paySlips = employeeService.getPaySlipByEmployeeId(employee.getId(), financialYear1, financialYear2);
			ObjectMapper mapper = new ObjectMapper();

			if (paySlips == null)
			{
				responseStatus = Response.Status.NOT_FOUND;
				responseObj = getError(responseStatus, "No Data Found");

				return Response.status(responseStatus).entity(responseObj.toString()).build();
			}
			else
			{
				data.put("paySlip", mapper.writeValueAsString(paySlips));
			}
		}

		responseStatus = Response.Status.OK;
		responseObj = getResponse(responseStatus, "success", data);

		return Response.status(responseStatus).entity(responseObj.toString()).build();
	}

	@Secured
	@GET
	@Path("/download/{paySlipId}")
	@Produces("image/png")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getFile(@Context HttpHeaders httpheaders, @PathParam("paySlipId") int paySlipId) throws Exception 
	{  
		JSONObject responseObj = null;
		Response.Status responseStatus = null;
		Employee employee = null; 
		PaySlip paySlip = null;
		User user = getUser(httpheaders);

		if (paySlipId > 0 && user != null)
		{
			employee =  user.getEmployee();
			if (employee != null) 
			{
				paySlip = employeeService.getPaySlipDetails(paySlipId, employee.getId());
			}
		}
		
		if (paySlip == null)
		{
			responseStatus = Response.Status.NOT_FOUND;
			responseObj = getError(responseStatus, "No Data Found");

			return Response.status(responseStatus).entity(responseObj.toString()).build();
		}
		
		File file = new File(FILE_PATH +paySlip.getId()+".png");
		ResponseBuilder response = Response.ok((Object) FileEncryptionUtil.decryptFile(file));

		return response.build();
	}  
}  