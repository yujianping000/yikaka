package com.framework.loippi.controller.admin;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.TBanner;
import com.framework.loippi.mybatis.paginator.domain.Order.Direction;
import com.framework.loippi.service.TBannerService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.qiniu.FileServiceImpl;

import java.util.HashMap;

/**
 * Controller - 横幅表
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminTBannerController")
@RequestMapping({ "/admin/tbanner" })
public class TBannerController extends GenericController {

	@Resource
	private TBannerService tBannerService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
	    
		String token = FileServiceImpl.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		return "/admin/tbanner/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TBanner tBanner, RedirectAttributes redirectAttributes) {
		tBannerService.save(tBanner);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		String token = FileServiceImpl.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		TBanner tBanner = tBannerService.find(id);
		model.addAttribute("tBanner", tBanner);
		return "/admin/tbanner/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		TBanner tBanner = tBannerService.find(id);
		model.addAttribute("tBanner", tBanner);
		return "/admin/tbanner/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TBanner tBanner, RedirectAttributes redirectAttributes) {
		tBannerService.update(tBanner);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		  
			pageable.setOrderDirection(Direction.ASC);
			pageable.setOrderProperty("sort");
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.tBannerService.findByPage(pageable));
		return "/admin/tbanner/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.tBannerService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
