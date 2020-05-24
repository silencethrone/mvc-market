package com.xx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.xx.model.User;
import com.xx.util.DBUtil;

public class UserDao {
	private static Map<String, User> map = new HashMap<String, User>(); 
	
/* 用户注册 */
	public static boolean register(String username,String password,int age,String sex) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from user where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}else{
				String sql1 = "insert into user (username,password,age,sex) values (?,?,?,?)";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, username);
				pstmt1.setString(2, password);
				pstmt1.setInt(3, age);
				pstmt1.setString(4, sex);
				int result = pstmt1.executeUpdate();
				if(result > 0) {
					return true;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, pstmt1, con);
		}
	}
/* 用户登录 */
	public static User login(String username,String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from user where username = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String sex = rs.getString("sex");
				String introduction = rs.getString("introduction");
				int balance = rs.getInt("balance");
				Boolean isAdmin = rs.getBoolean("isAdmin");
				
				User information = new User(id, username, null, age, sex, balance, introduction, isAdmin);
				map.put("information", information);
				return information;
			}else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
/* Ajax_判断用户名是否存在 */
	public static boolean isExist(String username) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from user where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
/* 个人信息上传 */
	public static boolean isExist_personInfo(String username,String introduction) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from user where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String sql1 = "update user set introduction = ? where username = ?";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, introduction);
				pstmt1.setString(2, username);
				int result = pstmt1.executeUpdate();
				if(result > 0) {
					return true;
				}else {
					return false;
				}
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, pstmt1, con);
		}
	}
	
}
