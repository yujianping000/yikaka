package com.framework.loippi.utils.qiniu;



import java.util.UUID;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.awt.image.*;
import javax.imageio.ImageIO;


@Service("fileServiceImpl4Qiniu")
public class FileServiceImpl4Qiniu implements FileService4Qiniu {
	
	private Logger log = Logger.getLogger(getClass());
	public static final String uploadImageExtension = "jpg,jpeg,bmp,gif,png";
	public static final String uploadFlashExtension = "swf,flv";
	public static final String uploadMediaExtension = "swf,flv,mp3,wav,mp4,avi,rm,rmvb";
	public static final String uploadFileExtension = "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx";
	/** 分隔符 */
	
	private static final String SEPARATOR = ",";
	
	public static final String QINIU_TOKEN = "zhongyi3";
	
	
	public static final String QINIU_AK = "0sJ4Dh7qcbkh_VpAxG5CrIZmYeGUwv0rAag25nIK";
	
	public static final String QINIU_SK = "JaOMpQNonLjVelz9w364jeds02t2hgfl4L1pELdY";
	
	public static final String QINIU_PREFIX = "http://omwkp25mr.bkt.clouddn.com/";
	/**
	 * 文件类型
	 */
	public enum FileType {

		/** 图片 */
		image,

		/** Flash */
		flash,

		/** 媒体 */
		media,

		/** 文件 */
		file
	}
	

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;	
	
	UploadManager uploadManager = new UploadManager();
    Auth auth = Auth.create(QINIU_AK, QINIU_SK);
    
//    String token = auth.uploadToken(QINIU_TOKEN);
	

	@Override
	public boolean isValid(FileType fileType, MultipartFile multipartFile) {
		if (multipartFile == null) {
			return false;
		}
		String[] uploadExtensions;
		if (fileType == FileType.flash) {
			uploadExtensions = getUploadFlashExtensions();
		} else if (fileType == FileType.media) {
			uploadExtensions = getUploadMediaExtensions();
		} else if (fileType == FileType.file) {
			uploadExtensions = getUploadFileExtensions();
		} else {
			uploadExtensions = getUploadImageExtensions();
		}
		if (ArrayUtils.isNotEmpty(uploadExtensions)) {
			return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), uploadExtensions);
		}
		return false;
	}

	@Override
	public String upload(MultipartFile multipartFile, boolean async) {
		try {
			 String filename = UUID.randomUUID().toString().concat("."+FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
			if(async){
				addTask(multipartFile,filename);
			}else{
				uploadManager.put(multipartFile.getBytes(), filename , auth.uploadToken(QINIU_TOKEN));
			
			}
		    return QINIU_PREFIX.concat(filename);
		} catch (QiniuException e) {
			 Response r = e.response;
		        // 请求失败时简单状态信息
		        log.error(r.toString());
		        try {
		            // 响应的文本信息
		            log.error(r.bodyString());
		        } catch (QiniuException e1) {
		            //ignore
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public String upload(File file, boolean async) {
		try {
			 String filename = UUID.randomUUID().toString()+".jpg";
			uploadManager.put(file, filename , auth.uploadToken(QINIU_TOKEN));
		    return QINIU_PREFIX.concat(filename);
		} catch (QiniuException e) {
			 Response r = e.response;
		        // 请求失败时简单状态信息
		        log.error(r.toString());
		        try {
		            // 响应的文本信息
		            log.error(r.bodyString());
		        } catch (QiniuException e1) {
		            //ignore
		        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加上传任务
	 * 
	 * @param storagePlugin
	 *            存储插件
	 * @param path
	 *            上传路径
	 * @param tempFile
	 *            临时文件
	 * @param contentType
	 *            文件类型
	 */
	private void addTask(final MultipartFile multipartFile,final String filename) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					 uploadManager.put(multipartFile.getBytes(), filename , auth.uploadToken(QINIU_TOKEN));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String[] getUploadImageExtensions() {
		return StringUtils.split(uploadImageExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传Flash扩展名
	 * 
	 * @return 允许上传Flash扩展名
	 */
	public String[] getUploadFlashExtensions() {
		return StringUtils.split(uploadFlashExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	public String[] getUploadMediaExtensions() {
		return StringUtils.split(uploadMediaExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String[] getUploadFileExtensions() {
		return StringUtils.split(uploadFileExtension, SEPARATOR);
	}
	
	public static final String getToken(){
		
		UploadManager uploadManager = new UploadManager();
	    Auth auth = Auth.create(QINIU_AK, QINIU_SK);
	    String token = auth.uploadToken(QINIU_TOKEN);
	    return token;
	}

	
	
	/**
	   * 下载文件到本地
	   *
	   * @param urlString
	   *          被下载的文件地址
	   * @param filename
	   *          本地文件名
	   * @throws Exception
	   *           各种异常
	   */
	public  void download(String urlString, String filename) throws Exception {
	    // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    InputStream is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    OutputStream os = new FileOutputStream(filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	}   
	
public static void main(String[] args) throws QiniuException {
	}
}
