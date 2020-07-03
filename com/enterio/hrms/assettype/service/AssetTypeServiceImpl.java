package com.enterio.hrms.assettype.service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;

import com.enterio.foundation.domain.User;
import com.enterio.hrms.assettype.dao.AssetTypeDAO;
import com.enterio.hrms.common.service.CommonServiceImpl;
import com.enterio.hrms.foundation.domain.AssetType;
import com.enterio.util.StringUtil;

public class AssetTypeServiceImpl extends CommonServiceImpl implements Serializable, AssetTypeService
{
	private static final long serialVersionUID = 8736691427116250733L;

	public List<AssetType> getAssetTypes(String companyUUID) 
	{
		List<AssetType> assetTypeList = null;
		companyUUID = StringUtil.checkNull(companyUUID);

		if(companyUUID.length() > 0)
		{
			SqlSession session = super.getDBSession();	
			if (session != null)
			{
				AssetTypeDAO assetTypeMapper= session.getMapper(AssetTypeDAO.class);
				assetTypeList = assetTypeMapper.getAssetTypes(companyUUID);
			}
			super.closeDBSession(session);
		}

		return assetTypeList;
	}

	public AssetType getAssetType(String assetTypeUUID) 
	{
		AssetType assetType = null;

		assetTypeUUID = StringUtil.checkNull(assetTypeUUID);

		if(assetTypeUUID.length() > 0)
		{
			SqlSession session = getDBSession();

			if(session != null)
			{
				AssetTypeDAO assetTypeMepper = session.getMapper(AssetTypeDAO.class);
				assetType = assetTypeMepper.getAssetTypeByUUID(assetTypeUUID);
			}
			closeDBSession(session);
		}

		return assetType;
	}

	public AssetType saveAssetType(AssetType assetType, User createdBy) 
	{
		if(assetType != null && createdBy != null && createdBy.getCompany() != null)
		{
			SqlSession session = getDBSession();
			if(session != null)
			{
				AssetTypeDAO assetTypeMapper = session.getMapper(AssetTypeDAO.class);

				if(StringUtil.checkNull(assetType.getUuid()).length() <= 0)
				{
					assetType.setUuid(UUID.randomUUID().toString());
					assetType.setCompany(createdBy.getCompany());
					assetTypeMapper.insertAssetType(assetType);
					session.commit();
				}
				else
				{	
					assetTypeMapper.updateAssettype(assetType);
					session.commit();
				}

			}
			closeDBSession(session);
		}
		
		return assetType;
	}

	public boolean deleteAssetType(String assetTypeUUID)
	{
		boolean isAssetTypeDeleted = false;
		assetTypeUUID = StringUtil.checkNull(assetTypeUUID);
		
		if(assetTypeUUID.length() > 0)
		{
			AssetType assetType = getAssetType(assetTypeUUID);
			if(assetType != null)
			{
				SqlSession session = super.getDBSession();
				if (session != null)
				{
					try
					{
						AssetTypeDAO assetTypeMapper = session.getMapper(AssetTypeDAO.class);
						assetTypeMapper.deleteAssetType(assetType);
						session.commit();
						isAssetTypeDeleted = true;
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				super.closeDBSession(session);
			}
		}
		
		return isAssetTypeDeleted;
	}
}