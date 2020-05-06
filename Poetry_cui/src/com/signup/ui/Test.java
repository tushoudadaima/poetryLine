package com.signup.ui;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.signup.entity.User;
import com.signup.mapper.UserMapper;
import com.signup.util.MybatisUtil;

public class Test {
//��ѯ�����û�����
	public static List selectAll() {
		SqlSession session = MybatisUtil.getSession();
		List<User> users = session.selectList("com.signup.mapper.UserMapper.selectAll");
		System.out.println("������������:");
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
	//����û�
	public static int addUser(User user) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.addUser(user);	
		
		try {
			session.commit();
			System.out.println("����ɹ�");
			session.close();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����ʧ��");
			return 0;
		}
		
	}
	//����û��Ƿ��Ѿ�ע��
	public String check(String phone) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		List<User> users = userMapper.check(phone);
		if (users == null||users.size()==0) {
			System.out.println("û������"+users);
			return "0";
		}else {
			System.out.println("������ "+users);
			return "1";
		}	
	}
	//�ϴ�ͷ��
	public static int imgUp(String phone, String pathString) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.imgUp(phone,pathString);	
		try {
			session.commit();		
			session.close();			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�ϴ�ʧ��");
		}
		if (a==0) {
			System.out.println("�ϴ�ʧ��");
			return 0;
		} else {
			System.out.println("�ϴ��ɹ�");
			
			return 1;
		}
	}
	//��������
	public static int updatePassword(String phone,String password) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.updatePassword(phone, password);
		try {
			session.commit();
		 	session.close();
		 	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����");
			
		}
		if (a==0) {
			System.out.println("�޸�����ʧ��");
			return 0;
		}else {
			System.out.println(a+"�޸�����ɹ�");
			return 1;
		}
	}
	//��¼
	public static String checkUser(String phone, String password) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		List<User> users = userMapper.userLogin(phone,password);
		if (users == null||users.size()==0) {
			System.out.println("û������"+users);
			return "0";
		}else {
			System.out.println("������ "+users);
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
			System.out.println("����");
			
		}
		if (a==0) {
			System.out.println("�޸�nameʧ��");
			return 0;
		}else {
			System.out.println(a+"�޸�name�ɹ�");
			return 1;
		}
	}
	//�޸ļ��
	public static int updateIntroduction(String phone, String introduction) {
		SqlSession session = MybatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		int a = userMapper.updateIntroduction(phone, introduction);
		try {
			session.commit();
		 	session.close();
		 	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����");
			
		}
		if (a==0) {
			System.out.println("�޸�introductionʧ��");
			return 0;
		}else {
			System.out.println(a+"�޸�introduction�ɹ�");
			return 1;
		}
	}
}
