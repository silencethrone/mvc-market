package com.xx.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*c3p0需要依赖jar包
 * c3p0-xx.jar
 * mchange-commons-java-xx.jar
 * mysql-connector-java-xx.jar（尽量高版本）
 */

public class DBUtil {
	private static final String connectedURL = "jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static final String username = "root";
	private static final String password = "123";
	private static ComboPooledDataSource ds = null;
	/* 单例模式 */
	public static DBUtil instance = new DBUtil();
	
	private DBUtil() {
		/* 初始化c3p0数据源 */
		try {
			ds = new ComboPooledDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
			ds.setJdbcUrl(connectedURL);
			ds.setUser(username);
			ds.setPassword(password);
			
			ds.setInitialPoolSize(5);//初始化连接个数
			ds.setMaxPoolSize(20);//最大连接个数
			ds.setMinPoolSize(3);
			
		} catch (Exception e) {
			System.out.println("c3p0初始化发生错误");
		}
	}

	/* 获取数据库连接 */
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			System.out.println("获取连接失败");
		}
		return null;
	}
	
	private static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void closePreparedStatement1(PreparedStatement pstmt1) {
		try {
			if (pstmt1 != null)
				pstmt1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//close方法一
	public static void closeJDBC(ResultSet rs,PreparedStatement pstmt,Connection con) {
		closeResultSet(rs);
		closePreparedStatement(pstmt);
		closeConnection(con);
	}
	//close方法二（转账事务）
	public static void closeJDBC(PreparedStatement pstmt,PreparedStatement pstmt1,Connection con) {
		closePreparedStatement(pstmt);
		closePreparedStatement1(pstmt1);
		closeConnection(con);
	}
	//close方法三（注册）
	public static void closeJDBC(ResultSet rs,PreparedStatement pstmt,PreparedStatement pstmt1,Connection con) {
		closeResultSet(rs);
		closePreparedStatement(pstmt);
		closePreparedStatement1(pstmt1);
		closeConnection(con);
	}
	//close方法四（头像上传）
	public static void closeJDBC(ResultSet rs,PreparedStatement pstmt,PreparedStatement pstmt1,PreparedStatement pstmt2,Connection con) {
		closeResultSet(rs);
		closePreparedStatement(pstmt);
		closePreparedStatement1(pstmt1);
		closePreparedStatement1(pstmt2);
		closeConnection(con);
	}
}