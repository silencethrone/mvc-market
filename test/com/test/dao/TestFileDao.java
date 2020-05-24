package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xx.model.Files;
import com.xx.util.DBUtil;

public class TestFileDao {
	public void Test() {
		
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
				return FileURL;
			}else {
				return null;
			}
		} catch (Exception e) {
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
					return true;
				}else {
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
					return true;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		} finally {
			DBUtil.closeJDBC(rs, pstmt, pstmt1, pstmt2, con);
		}
	}
}
