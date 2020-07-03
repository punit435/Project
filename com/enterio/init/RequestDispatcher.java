package com.enterio.init;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

public class RequestDispatcher extends StrutsPrepareAndExecuteFilter
{
	public void init(FilterConfig filterConfig) throws ServletException
	{
		super.init(filterConfig);
	}
}