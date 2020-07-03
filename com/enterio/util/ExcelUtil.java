package com.enterio.util;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtil implements Serializable 
{
	private static final long serialVersionUID = 4282900720507298092L;

	public String getCellValue(HSSFCell cell)
	{
		if(cell == null) return "ERROR: Cell is null.";

		String value = "";
		switch(cell.getCellType())
		{
			case HSSFCell.CELL_TYPE_STRING:
			{
				value = cell.getStringCellValue();
				break;
			}
			case HSSFCell.CELL_TYPE_NUMERIC:
			{
				if(HSSFDateUtil.isCellDateFormatted(cell))
				{
					java.util.Date dateValue = cell.getDateCellValue();
					try
					{
						value = DateUtil.convertToDateString(dateValue);
					}
					catch(Exception e)
					{
						value = "ERROR: Cannot convert to date.";
					}
				}
				else
				{
					value = "" + cell.getNumericCellValue();
					if(value.indexOf("E") > 0)
					{
						NumberFormat f = NumberFormat.getInstance();
						f.setGroupingUsed(false);
						value = f.format(cell.getNumericCellValue());
					}
					
					if(value.indexOf(".") >= 0)
					{
						int decimalIndex = value.indexOf(".");
						String remainingString = value.substring(decimalIndex+1);
						int decimalNumber = Integer.parseInt(remainingString);
						if(decimalNumber == 0)
						{
							value = "" + (long) cell.getNumericCellValue();
						}
					}
				}
				break;
			}
			case HSSFCell.CELL_TYPE_BLANK:
			{
				value = cell.getStringCellValue();
				break;
			}
			default:
			{
				value = "ERROR: Undefined cell type.";
			}
		}//switch ends

		return value;
	}
	
	public String getCellValue(XSSFCell cell)
	{
		if(cell == null) return "ERROR: Cell is null.";

		String value = "";
		switch(cell.getCellType())
		{
			case XSSFCell.CELL_TYPE_STRING:
			{
				value = cell.getStringCellValue();
				break;
			}
			case HSSFCell.CELL_TYPE_NUMERIC:
			{
				if(HSSFDateUtil.isCellDateFormatted(cell))
				{
					java.util.Date dateValue = cell.getDateCellValue();
					try
					{
						value = DateUtil.convertToDateString(dateValue);
					}
					catch(Exception e)
					{
						value = "ERROR: Cannot convert to date.";
					}
				}
				else
				{
					value = "" + cell.getNumericCellValue();
					if(value.indexOf("E") > 0)
					{
						NumberFormat f = NumberFormat.getInstance();
						f.setGroupingUsed(false);
						value = f.format(cell.getNumericCellValue());
					}
					
					if(value.indexOf(".") >= 0)
					{
						int decimalIndex = value.indexOf(".");
						String remainingString = value.substring(decimalIndex+1);
						if(remainingString.length() > 2)
						{
							remainingString = remainingString.substring(0, 2);
						}
						int decimalNumber = Integer.parseInt(remainingString);
						if(decimalNumber == 0)
						{
							value = "" + (long) cell.getNumericCellValue();
						}
					}
				}
				break;
			}
			case HSSFCell.CELL_TYPE_BLANK:
			{
				value = cell.getStringCellValue();
				break;
			}
			default:
			{
				value = "ERROR: Undefined cell type.";
			}
		}//switch ends

		return value;
	}
}