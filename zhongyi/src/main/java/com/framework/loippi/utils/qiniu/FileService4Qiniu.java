package com.framework.loippi.utils.qiniu;


import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.framework.loippi.utils.qiniu.FileServiceImpl4Qiniu.FileType;





public interface FileService4Qiniu {

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
	 * 文件上传
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @param async
	 *            是否异步
	 * @return 访问URL
	 */
	String upload(MultipartFile multipartFile, boolean async);

	String upload(File file, boolean async);



	void download(String urlString, String filename) throws Exception;
}
