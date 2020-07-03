package com.enterio.hrms.assettype.dao;

import java.io.Serializable;
import java.util.List;

import com.enterio.hrms.foundation.domain.AssetType;

public interface AssetTypeDAO extends Serializable
{
	public List<AssetType> getAssetTypes(String companyUUID);

	public AssetType getAssetTypeByUUID(String assetTypeUUID);

	public void insertAssetType(AssetType assetType);

	public void updateAssettype(AssetType assetType);

	public void deleteAssetType(AssetType assetType);
}