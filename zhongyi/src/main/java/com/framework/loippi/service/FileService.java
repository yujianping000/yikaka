package com.framework.loippi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.framework.loippi.support.FileInfo;
import com.framework.loippi.support.FileInfo.FileType;
import com.framework.loippi.support.FileInfo.OrderType;

/**
 * Service - 文件
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
public interface FileService {

	/**
	 * 文件验证
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @return 文件验证是否通过
	 */
	boolean isValid(FileType fileType, MultipartFile multipartFile);

	/**
	 * 文件上传至本地
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @return 路径
	 */
	String uploadLocal(FileType fileType, MultipartFile multipartFile);

	/**
	 * 文件浏览
	 * 
	 * @param path
	 *            浏览路径
	 * @param fileType
	 *            文件类型
	 * @param orderType
	 *            排序类型
	 * @return 文件信息
	 */
	List<FileInfo> browser(String path, FileType fileType, OrderType orderType);
	
	

	/**
	 * 文件上传至本地
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @return 路径
	 */
	String uploadAppWebLocal(FileType fileType, MultipartFile multipartFile);
	
	/**
	 * 
	 * @param fileType  文件类型
	 * @param multipartFile 上传的文件
	 * @param url  保存文件的路径
	 * @param filename 保存的文件名
	 * @return
	 */
	public String uploadFile(FileType fileType, MultipartFile multipartFile,String url,String filename) ;

}