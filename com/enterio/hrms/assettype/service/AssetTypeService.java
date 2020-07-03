package com.enterio.hrms.assettype.service;

import java.io.Serializable;
import java.util.List;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.foundation.domain.AssetType;

public interface AssetTypeService extends Serializable
{
	public List<AssetType> getAssetTypes(String companyUUID);

	public AssetType getAssetType(String assetTypeUUID);

	public AssetType saveAssetType(AssetType assetType, User createdBy);

	public boolean deleteAssetType(String assetTypeUUID);
}
