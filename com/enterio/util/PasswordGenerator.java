package com.enterio.util;

import java.io.Serializable;

public class PasswordGenerator implements Serializable
{
	private static final long serialVersionUID = -996529293911131906L;
    public static final int MAX_LENGTH = 10;
    private static java.util.Random r = new java.util.Random();
    
    protected static char[] acceptableCharcters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
        'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
        'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '2', '3', '4', '5', '6', '7', '8', '9', '!', '@', '#', '$' }; 
    
    public static String generatePassword()
    {
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < MAX_LENGTH; i++) 
    	{
    		sb.append(acceptableCharcters[r.nextInt(acceptableCharcters.length)]);
    	}
    	
    	return sb.toString();
    }
}