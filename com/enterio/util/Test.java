package com.enterio.util;

import java.util.UUID;

public class Test
{
	public static void main(String[] args)
	{
		for(int i=1; i <= 4; i++)
		{
			System.out.println(UUID.randomUUID().toString());
		}
	}
}