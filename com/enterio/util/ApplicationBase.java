package com.enterio.util;

import java.io.Serializable;

public class ApplicationBase implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static String basePath = "http://local.enterio.in";
	
	public static String getBasePath() {
		return basePath;
	}
}