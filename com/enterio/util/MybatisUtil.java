package com.enterio.util;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisUtil implements Serializable
{
	private static final long serialVersionUID = 5978024472539835853L;
	private static SqlSessionFactory sessionFactory;
	
	public MybatisUtil(SqlSessionFactory sessionFactory)
	{
		MybatisUtil.sessionFactory = sessionFactory;
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return MybatisUtil.sessionFactory;
	}
}