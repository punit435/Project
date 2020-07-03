package com.enterio.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CryptographyUtil 
{
	public static String generateDigest(String input) 
	{
		String message = "-18A15B917C427D7633~61@59#7" + input;
		String privateKey = "5645309227122662717";
		String algorithm = "HmacSHA256";
		String digest = null;
		try 
		{
			SecretKeySpec key = new SecretKeySpec((privateKey).getBytes("UTF-8"), algorithm);
			Mac mac = Mac.getInstance(algorithm);
			mac.init(key);
			
			byte[] bytes = mac.doFinal(message.getBytes("ASCII"));
			
			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) 
			{
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) 
				{
					hash.append('0');
				}
				hash.append(hex);
			}
					  
			digest = hash.toString();
		}
		catch (UnsupportedEncodingException e) { }
		catch (InvalidKeyException e) { }
		catch (NoSuchAlgorithmException e) { }

		return digest;
	}
}