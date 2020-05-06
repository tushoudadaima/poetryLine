package com.signup.ui;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.signup.entity.User;
import com.signup.mapper.UserMapper;
import com.signup.util.MybatisUtil;

public class Test {
//查询所有用户数据
	public static List selectAll() {
		SqlSession session = MybatisUtil.getSession();
		List<User> users = session.selectList("com.signup.mapper.UserMapper.selectAll");
		System.out.println("所有新闻如下:");
		for (User user : users) {
			System.out.println(user);
		}
		session.commit();
		session.close();
		return users;
	}
	public static void showPhone(String string) {
		System.out.println(string);

	}
	//添加用户
	public static int addUser(User user) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.addUser(user);	
		
		try {
			session.commit();
			System.out.println("插入成功");
			session.close();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入失败");
			return 0;
		}
		
	}
	//检查用户是否已经注册
	public String check(String phone) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		List<User> users = userMapper.check(phone);
		if (users == null||users.size()==0) {
			System.out.println("没有数据"+users);
			return "0";
		}else {
			System.out.println("有数据 "+users);
			return "1";
		}	
	}
	//上传头像
	public static int imgUp(String phone, String pathString) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.imgUp(phone,pathString);	
		try {
			session.commit();		
			session.close();			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("上穿失败");
		}
		if (a==0) {
			System.out.println("上穿失败");
			return 0;
		} else {
			System.out.println("上传成功");
			
			return 1;
		}
	}
	//更改密码
	public static int updatePassword(String phone,String password) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.updatePassword(phone, password);
		try {
			session.commit();
		 	session.close();
		 	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误");
			
		}
		if (a==0) {
			System.out.println("修改密码失败");
			return 0;
		}else {
			System.out.println(a+"修改密码成功");
			return 1;
		}
	}
	//登录
	public static String checkUser(String phone, String password) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		List<User> users = userMapper.userLogin(phone,password);
		if (users == null||users.size()==0) {
			System.out.println("没有数据"+users);
			return "0";
		}else {
			System.out.println("有数据 "+users);
			return "1";
		}	
	}
	
	public static String findName(String phone) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		User name = userMapper.findName(phone);
		return name.getName();
	}
	public static int updateName(String phone, String name) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.updateName(phone, name);
		try {
			session.commit();
		 	session.close();
		 	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误");
			
		}
		if (a==0) {
			System.out.println("修改name失败");
			return 0;
		}else {
			System.out.println(a+"修改name成功");
			return 1;
		}
	}
	//修改简介
	public static int updateIntroduction(String phone, String introduction) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.updateIntroduction(phone, introduction);
		try {
			session.commit();
		 	session.close();
		 	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误");
			
		}
		if (a==0) {
			System.out.println("修改introduction失败");
			return 0;
		}else {
			System.out.println(a+"修改introduction成功");
			return 1;
		}
	}
}
