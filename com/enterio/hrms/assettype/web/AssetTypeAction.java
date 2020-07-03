package com.enterio.hrms.assettype.web;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.assettype.service.AssetTypeService;
import com.enterio.hrms.foundation.domain.AssetType;
import com.enterio.init.StrutsActionSupport;
import com.enterio.util.StringUtil;

public class AssetTypeAction extends StrutsActionSupport implements Serializable
{
	private static final long serialVersionUID = 4129907589537491302L;
	private String assetTypeUUID;
	private AssetType assetType;
	private List<AssetType> assetTypes;
	private AssetTypeService assetTypeService;
	
	public String assetTypeList() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null && sessionUser.getCompany().getId() > 0)
		{
			assetTypes = getAssetTypeService().getAssetTypes(sessionUser.getCompany().getUuid());
		}

		return SUCCESS;
	}

	public String addAssetType() 
	{
		return SUCCESS;
	}
	
	public String saveAssetType() 
	{
		User sessionUser = super.getSessionUser();
		if(sessionUser != null)
		{
			assetType = getAssetTypeService().saveAssetType(assetType, sessionUser);
			redirectURL = "AssetTypes";
		}
		
		return SUCCESS;
	}
	
	public String editAssetType()
	{
		User sessionUser = super.getSessionUser();
		assetTypeUUID = StringUtil.checkNull(assetTypeUUID);
		
		if(sessionUser != null && assetTypeUUID.length() > 0)
		{
			assetType = getAssetTypeService().getAssetType(assetTypeUUID);
		}
		
		return SUCCESS;
	}
	
	public String deleteAssetType() 
	{
		if (assetTypeUUID.length() > 0) 
		{
			getAssetTypeService().deleteAssetType(assetTypeUUID);
			redirectURL = "AssetTypes";
		}
		
		return SUCCESS;
	}
	
	public void setAssetTypeUUID(String assetTypeUUID) {
		this.assetTypeUUID = assetTypeUUID;
	}
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	public List<AssetType> getAssetTypes() {
		return assetTypes;
	}
	public AssetTypeService getAssetTypeService() {
		return assetTypeService;
	}
	public void setAssetTypeService(AssetTypeService assetTypeService) {
		this.assetTypeService = assetTypeService;
	}
}