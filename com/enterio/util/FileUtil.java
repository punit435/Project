package com.enterio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class FileUtil implements Serializable 
{
	private static final long serialVersionUID = -2621185185494915195L;
    private File file; 
	private String filePath;
    private File existingFile;
	
	private boolean checkFile()
	{
		boolean fileExists = false;

		if(StringUtil.checkNull(filePath).length() > 0)
		{
			existingFile = new File(filePath);
			if(existingFile.exists())
			{
				fileExists = true;
			}
		}
		
		return fileExists;
	}
	
	public void renameFile(File sourceFile, String destinationFilePath)
	{
		this.file = sourceFile;
		this.filePath = destinationFilePath;

		String directoryPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
		if(StringUtil.checkNull(directoryPath).length() > 0)
		{
			if(checkFile())
			{
				existingFile.delete();
			}
		
			if(!new File(directoryPath).exists())
			{
				new File(directoryPath).mkdirs();
			}
			
			File newFile = new File(filePath);
			file.renameTo(newFile);
		}
	}
	
	public void deleteFile(String filePath)
	{
		this.filePath = filePath;
		if(checkFile())
		{
			existingFile.delete();
		}
	}
	
	public void copyFile(File sourceFile, String destinationFilePath) throws IOException
	{
		this.file = sourceFile;
		this.filePath = destinationFilePath;

		String directoryPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
		if(StringUtil.checkNull(directoryPath).length() > 0)
		{
			if(checkFile())
			{
				existingFile.delete();
			}
		
			if(!new File(directoryPath).exists())
			{
				new File(directoryPath).mkdirs();
			}

			File destinationFile = new File(filePath);
			
		    InputStream inputStream = new FileInputStream(sourceFile);
		    OutputStream outputStream = new FileOutputStream(destinationFile);
		    
		    byte[] buffer = new byte[1024];
		    int length;
		    while ((length = inputStream.read(buffer)) > 0) 
		    {
		    	outputStream.write(buffer, 0, length);
		    }

		    inputStream.close();
		    outputStream.close();
		}
	}	
}