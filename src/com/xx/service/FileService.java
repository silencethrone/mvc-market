package com.xx.service;

import com.xx.dao.FileDao;
import com.xx.model.Files;

public class FileService {
/*实例化对象*/	
	public static FileService instance = new FileService();
	
/* 返回文件地址 */
	public Files FileURL(String owner) {
		return FileDao.FileURL(owner);
	}
/* 上传文件信息 */
	public boolean InsertFileInformation(String filename,String settime,String url,String owner) {
		return FileDao.InsertFileInformation(filename, settime, url, owner);
	}
}
