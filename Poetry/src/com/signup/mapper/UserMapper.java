package com.signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.signup.entity.User;

public interface UserMapper {
	public List<User> selectAll();
	public int addUser(User user);
	public List<User> check(@Param("phone")String phone);
	public int imgUp(@Param("phone")String phone,@Param("pathString")String pathString);
	public int updatePassword(@Param("phone")String phone,@Param("password")String password);
	public List<User> userLogin(@Param("phone")String phone,@Param("password")String password);
	public User findName(String phone);
	public int updateName(@Param("phone")String phone,@Param("name")String name);
	public int updateIntroduction(@Param("phone")String phone,@Param("introduction")String introduction);
	public User findIntroduction(String phone);
	
}
