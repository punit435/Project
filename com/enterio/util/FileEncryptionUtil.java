package com.enterio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptionUtil 
{
	private static String encodekey = "Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1Mary has one cat1";
	
	public static File encryptFile(File inputFile, String destinationPath) throws Exception 
	{
		File outputFile = null;
		
		try 
		{
			destinationPath = StringUtil.checkNull(destinationPath);
			if(destinationPath.length() > 0)
			{
				outputFile = new File(destinationPath);
				byte[] key = encodekey.getBytes("UTF-8");
		        MessageDigest sha = MessageDigest.getInstance("SHA-1");
		        key = sha.digest(key);
		        key = Arrays.copyOf(key, 16); // use only first 128 bit				
				
				Key secretKey = new SecretKeySpec(key, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	         
				FileInputStream inputStream = new FileInputStream(inputFile);
				byte[] inputBytes = new byte[(int) inputFile.length()];
				inputStream.read(inputBytes);
	             
				byte[] outputBytes = cipher.doFinal(inputBytes);
				FileOutputStream outputStream = new FileOutputStream(outputFile);
				outputStream.write(outputBytes);
	             
				inputStream.close();
				outputStream.close();
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return outputFile;
	}
 
	public static byte[] decryptFile(File inputFile) throws Exception 
	{
		byte[] outputBytes =  null;
				
		try 
		{
			byte[] key = encodekey.getBytes("UTF-8");
	        MessageDigest sha = MessageDigest.getInstance("SHA-1");
	        key = sha.digest(key);
	        key = Arrays.copyOf(key, 16); // use only first 128 bit				
			
			Key secretKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
         
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes);
             
			outputBytes = cipher.doFinal(inputBytes);
             
			inputStream.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return outputBytes;
	}
}