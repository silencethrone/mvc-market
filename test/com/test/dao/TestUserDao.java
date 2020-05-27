package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xx.model.User;
import com.xx.util.DBUtil;

public class TestUserDao {
	@Test
	public void TestRegister01() {
		register("2333", "111", 12, "男");//正确的用户信息
	}
	@Test
	public void TestRegister02() {
		register("111", "123", 12, "男");//错误的用户信息：用户名重复
		register("张三", "123", 12, "男");//错误的用户信息：密码错误
		register("张三", "123", -1, "女");//错误的用户信息：年龄错误
	}
	@Test
	public void TestLogin() {
		login("111", "111");
	}
	@Test
	public void TestIsExist01() {
		isExist("111");//数据库存在的用户
	}
	@Test
	public void TestIsExist02() {
		isExist("00000");//数据库不存在的用户
	}
	@Test
	public void TestIsExistPersonInfo() {
		isExist_personInfo("111", "helloworld");
	}
	
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
				System.out.println("用户名重复");
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
					System.out.println("添加成功");
					return true;
				}else {
					System.out.println("添加失败");
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("数据库出现错误");
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
				System.out.println("登录成功");
				return information;
			}else {
				System.out.println("登录成功");
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
					System.out.println("上传成功");
					return true;
				}else {
					System.out.println("上传失败");
					return false;
				}
			}else{
				System.out.println("未找到此数据");
				return false;
			}
		} catch (Exception e) {
			return false;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, pstmt1, con);
		}
	}
	
}
