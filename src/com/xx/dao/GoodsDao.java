package com.xx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xx.model.Goods;
import com.xx.util.DBUtil;

/* 输出全部商品列表 */
public class GoodsDao {
	
	static List<Goods> goodslist = new ArrayList<Goods>();
	
	public static List<Goods> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		goodslist.clear();
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from goods";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String goodsname = rs.getString("goodsname");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				int stock = rs.getInt("stock");
				goodslist.add(new Goods(id, goodsname, description, price, stock));
			}
			return goodslist;
		} catch (Exception e) {
			return null;
		}finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}		
	}
	
/* 添加商品 */
	public static boolean insert(String goodsname,String description,float price,int stock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from goods where goodsname = ? and description = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsname);
			pstmt.setString(2, description);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}else {
				String sql1 = "insert into goods (goodsname,description,price,stock) values (?,?,?,?)";
				pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, goodsname);
				pstmt1.setString(2, description);
				pstmt1.setFloat(3, price);
				pstmt1.setInt(4, stock);
				
				int result = pstmt1.executeUpdate();
				if(result > 0) {
					return true;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		} finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
	
/* 删除商品 */
	public static boolean delete(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "delete from goods where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
	
/* 修改商品 */
	public static boolean updata(int id,String goodsname,String description,float price,int stock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.instance.getConnection();
			String sql = "update goods set goodsname = ?,description = ?,price = ?,stock = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goodsname);
			pstmt.setString(2, description);
			pstmt.setFloat(3, price);
			pstmt.setInt(4, stock);
			pstmt.setInt(5, id);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
/* 模糊查找 */
	public static List<Goods> vagueRead(String goodsname) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		goodslist.clear();
		try {
			con = DBUtil.instance.getConnection();
			String sql = "select * from goods where goodsname like '%"+goodsname+"%'";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					int id = rs.getInt("id");
					goodsname = rs.getString("goodsname");
					String description = rs.getString("description");
					float price = rs.getFloat("price");
					int stock = rs.getInt("stock");
					goodslist.add(new Goods(id, goodsname, description, price, stock));
				}
				return goodslist;
			}else {
				return null;
			}
		} catch (Exception e) {
			return null;
		} finally {
			DBUtil.closeJDBC(rs, pstmt, con);
		}
	}
	
}
