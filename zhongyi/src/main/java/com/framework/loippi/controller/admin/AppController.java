package com.framework.loippi.controller.admin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.App;
import com.framework.loippi.entity.SystemAppversion;
import com.framework.loippi.entity.App.Device;
import com.framework.loippi.entity.User;
import com.framework.loippi.service.AppService;
import com.framework.loippi.service.FileService;
import com.framework.loippi.service.SystemAppversionService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.support.FileInfo.FileType;
/**
 * Controller - 应用版本
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Controller("adminAppController")
@RequestMapping("/admin/app")
public class AppController extends GenericController {
	
	@Resource
	private AppService appService;
	
	@Resource
	private FileService fileService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private SystemAppversionService systemAppversionService;

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("app", systemAppversionService.find(id));
		return "/admin/app/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SystemAppversion app,MultipartFile file, RedirectAttributes redirectAttributes) {
		SystemAppversion persistent = systemAppversionService.find(app.getId());
		if(persistent == null) return ERROR_VIEW;
		
		if(persistent.getType().equals("android")){
			String appapk="";
			if(1l==app.getId()){
				appapk="pzy.apk";
			}else{
				appapk="tzy.apk";
			}
			
			
		
			String url = fileService.uploadAppWebLocal(FileType.file, file);
			System.out.println("文件复制路径："+url);	
			//复制文件到官网目录下
			try{
				InputStream inputStream =new FileInputStream(url);
				String fileUrl="/usr/webdata/tomcat/apache-tomcat4/webapps/ROOT/"+appapk;

				 OutputStream outputStream = new FileOutputStream(fileUrl);
			        int x;
			        while ((x = inputStream.read()) != -1) {
			                outputStream.write(x);
			        }
			        inputStream.close();
			        outputStream.close();
			        System.out.println("文件复制成功！");
			        persistent.setUrl("http://mj.gdxinji.com/"+appapk);
				}catch (IOException e){
				System.out.println("文件复制失败！"+e.toString());
				}
		}
		
		persistent.setVersion(app.getVersion());
		persistent.setContent(app.getContent());
		systemAppversionService.update(persistent);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequiresPermissions("admin:app:list")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( HttpServletRequest request, ModelMap model) {
		model.addAttribute("apps", systemAppversionService.findAll());
		return "/admin/app/list";
	}


}
