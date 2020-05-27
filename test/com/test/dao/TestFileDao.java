package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.xx.model.Files;
import com.xx.util.DBUtil;

public class TestFileDao {
	@Test
	public void Test01() {
		FileURL("111"); //查找数据库中存在的用户
	}
	@Test
	public void Test02() {
		FileURL("wql"); //查找数据库中不存在的用户
	}
	
	
	
/* 通过用户名查询文件地址 */
	public static Files FileURL(String owner) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from file where owner = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, owner);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String filename = rs.getString("filename");
				Files FileURL = new Files(0,filename,null,null,null);
				System.out.println("已找到数据！");
				return FileURL;
			}else {
				System.out.println("未找到数据！");
				return null;
			}
		} catch (Exception e) {
			System.out.println("数据库出现错误！");
			return null;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
/* 上传文件信息 */
	public static boolean InsertFileInformation(String filename,String settime,String url,String owner) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from file where owner = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, owner);
			rs = pstmt.executeQuery();
			//查询到用户头像则修改头像信息
			if(rs.next()) {
				String sql1 = "update file set filename = ?,settime = ?,url = ? where owner = ?";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, filename);
				pstmt1.setString(2, settime);
				pstmt1.setString(3, url);
				pstmt1.setString(4, owner);
				int result = pstmt1.executeUpdate();
				if(result > 0) {
					System.out.println("已找到数据！");
					return true;
				}else {
					System.out.println("未找到数据！");
					return false;
				}
			}else {
				String sql2 = "insert into file (filename,settime,url,owner) values (?,?,?,?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, filename);
				pstmt2.setString(2, settime);
				pstmt2.setString(3, url);
				pstmt2.setString(4, owner);
				
				int result = pstmt2.executeUpdate();
				if(result > 0) {
					System.out.println("头像数据已更新");
					return true;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("数据库出现错误！");
			return false;
		} finally {
			DBUtil.closeJDBC(rs, pstmt, pstmt1, pstmt2, con);
		}
	}
}
