package com.enterio.util;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil 
{
	private static String key = "Bar12345Bar12345"; // 128 bit key
//	private static String key = "Van31635Vanw9211"; // 128 bit key
	private static Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	
	public static String doEncrypt(String textToEncrypt)
	{
		StringBuilder sb = null;

		textToEncrypt = StringUtil.checkNull(textToEncrypt);
		if(textToEncrypt.length() > 0)
		{
			sb = new StringBuilder();
			try 
		    {
				Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
				byte[] encryptedBytes = cipher.doFinal(textToEncrypt.getBytes());
				
	            for (byte b: encryptedBytes) 
	            {
	                sb.append((char)b);
	            }
		    }
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static String doDecrypt(String textToDecrypt)
	{
		String decryptedValue = "";
		textToDecrypt = StringUtil.checkNull(textToDecrypt);
		if(textToDecrypt.length() > 0)
		{
			byte[] encryptedBytes = new byte[textToDecrypt.length()];
            for (int i=0; i < textToDecrypt.length(); i++) 
            {
            	encryptedBytes[i] = (byte) textToDecrypt.charAt(i);
            }
            try 
		    {
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, aesKey);
				decryptedValue = new String(cipher.doFinal(encryptedBytes));
		    }
			catch (Exception e) 
            {
				e.printStackTrace();
			}
		}
		return decryptedValue;
	}
	
	
}

