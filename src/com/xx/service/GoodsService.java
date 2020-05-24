package com.xx.service;

import java.util.List;

import com.xx.dao.GoodsDao;
import com.xx.model.Goods;

public class GoodsService { 
/* 实例化对象 */	
	public static GoodsService instance = new GoodsService();
	
	public List<Goods> selectAllgoods(){
		return GoodsDao.selectAll();
	}
/* 添加商品 */
	public boolean insertgoods(String goodsname,String description,float price,int stock) {
		return GoodsDao.insert(goodsname, description, price, stock);
	}
/* 删除商品 */
	public boolean deletegoods(int id) {
		return GoodsDao.delete(id);
	}
/* 更新商品 */
	public boolean updatagoods(int id,String goodsname,String description,float price,int stock) {
		return GoodsDao.updata(id, goodsname, description, price, stock);
	}
/* 模糊查询 */
	public List<Goods> vagueRead(String goodsname){
		return GoodsDao.vagueRead(goodsname);
	}

}
