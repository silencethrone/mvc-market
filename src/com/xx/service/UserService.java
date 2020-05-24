package com.xx.service;

import com.xx.dao.UserDao;
import com.xx.model.User;

public class UserService{
/* 实例化对象 */
	public static UserService instance = new UserService();
	
/* 注册 */
	public boolean register(String username,String password,int age,String sex) {
		return UserDao.register(username, password, age, sex);
	}

/* 登录 */
	public User login(String username,String password) {
		return UserDao.login(username, password);
	}
/*	Ajax_判断用户名是否重复	*/
	public boolean isExist(String username) {
		return UserDao.isExist(username);
	}
/*	个人信息上传	*/
	public boolean isExist_personInfo(String username,String introduction) {
		return UserDao.isExist_personInfo(username, introduction);
	}
	
}
