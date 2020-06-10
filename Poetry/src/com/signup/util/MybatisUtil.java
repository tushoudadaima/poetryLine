package com.signup.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory sessionFactory;
	static {
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		return sessionFactory.openSession();
	}
}
