package com.enterio.util;

import java.io.Serializable;

public class StringUtil implements Serializable 
{
	private static final long serialVersionUID = 4096965872655950214L;
	
	public static String checkNull(String inputString)
	{
		String outputString = "";
		
		if(inputString != null)
		{
			inputString = inputString.trim();

			if(!inputString.toLowerCase().equals("null") && inputString.length() > 0)
			{
				outputString = inputString;
			}
			else
			{
				outputString = "";
			}
		}
		else
		{
			outputString = "";
		}
		
		return outputString;
	}
}