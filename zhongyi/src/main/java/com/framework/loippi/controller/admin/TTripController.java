package com.framework.loippi.controller.admin;
import java.text.ParseException;
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

import com.framework.loippi.entity.TTrip;
import com.framework.loippi.service.TTripService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;

/**
 * Controller - 行程
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminTTripController")
@RequestMapping({ "/admin/ttrip" })
public class TTripController extends GenericController {

	@Resource
	private TTripService tTripService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/ttrip/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TTrip tTrip, RedirectAttributes redirectAttributes) {
		tTripService.save(tTrip);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		TTrip tTrip = tTripService.find(id);
		model.addAttribute("tTrip", tTrip);
		return "/admin/ttrip/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		TTrip tTrip = tTripService.find(id);
		model.addAttribute("tTrip", tTrip);
		return "/admin/ttrip/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TTrip tTrip, RedirectAttributes redirectAttributes) {
		tTripService.update(tTrip);
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
		String filter_eDate=request.getParameter("filter_eDate");
		String filter_sDate=request.getParameter("filter_sDate");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        } 
			if(!StringUtil.isEmpty(filter_eDate)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = format.parse(filter_eDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				filter_eDate = (new SimpleDateFormat("yyyyMMdd")).format(date);
				
				map.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
			}
			
			
			if(!StringUtil.isEmpty(filter_sDate)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = format.parse(filter_sDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				filter_sDate = (new SimpleDateFormat("yyyyMMdd")).format(date);
				
				map.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
			}
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.tTripService.findByPage(pageable));
		return "/admin/ttrip/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.tTripService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
}
