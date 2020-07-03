package com.enterio.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class PropertyLoader implements Serializable 
{
    private static final long serialVersionUID = -5889661790088277944L;
    private static Properties dbPropertyFile = new Properties();

    static
    {
    	try
		{
		    InputStream inputStream = PropertyLoader.class.getResourceAsStream("/Enterio.properties");
		    dbPropertyFile.load(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    
    public static String getProperty(String key)
    {
    	String value = ""; 
	
		if(key != null)
		{
		    value = dbPropertyFile.getProperty(key.toUpperCase());
		}
		
		return value; 
    }
}