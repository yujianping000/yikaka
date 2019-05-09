package com.framework.loippi.controller.admin;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.api.utils.ApiUtils;
import com.framework.loippi.controller.app.common.AppConstants;
import com.framework.loippi.entity.ActivityStuView;

import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.User;

import com.framework.loippi.mybatis.paginator.domain.Order.Direction;
import com.framework.loippi.service.ActivityStuViewService;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - VIEW
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminActivityStuViewController")
@RequestMapping({ "/admin/activity_stu_view" })
public class ActivityStuViewController extends GenericController {

	@Resource
	private ActivityStuViewService activityStuViewService;
	@Resource
	private OrderService orderService;
	
	@Resource
	private ParentService parentService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/activity_stu_view/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ActivityStuView activityStuView, RedirectAttributes redirectAttributes) {
		activityStuViewService.save(activityStuView);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		ActivityStuView activityStuView = activityStuViewService.find(id);
		model.addAttribute("activityStuView", activityStuView);
		return "/admin/activity_stu_view/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		ActivityStuView activityStuView = activityStuViewService.find(id);
		model.addAttribute("activityStuView", activityStuView);
		return "/admin/activity_stu_view/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ActivityStuView activityStuView, RedirectAttributes redirectAttributes) {
		activityStuViewService.update(activityStuView);
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
	@RequestMapping(value = { "/list/{id}" }, method = { RequestMethod.GET })
	public String list(@PathVariable Long id,Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  

		  Map<String, Object> paramter2 = new HashMap<>();
			
			String filter_account = request.getParameter("filter_account");
			String filter_stuName = request.getParameter("filter_stuName");
			String filter_activityNumber = request.getParameter("filter_activityNumber");
			
			if(!StringUtil.isEmpty(filter_account)) {
				paramter2.put("filter_account", StringUtil.filterSQL(filter_account));
			}
			
			if(!StringUtil.isEmpty(filter_stuName)) {
				paramter2.put("filter_stuName", StringUtil.filterSQL(filter_stuName));
			}

			if(!StringUtil.isEmpty(filter_activityNumber)) {
				paramter2.put("filter_activityNumber", StringUtil.filterSQL(filter_activityNumber));
			}
		

			pageable.setOrderDirection(Direction.DESC);
			pageable.setOrderProperty("created_time");
			paramter2.put("targetId", id);
		pageable.setParameter(paramter2);  
		model.addAttribute("id", id);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.activityStuViewService.findByPage(pageable));
		return "/admin/activity_stu_view/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.activityStuViewService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/updatePrize" }, method = { RequestMethod.POST })
	public @ResponseBody Message updatePrize(HttpServletRequest request) {
	String	id=request.getParameter("id");
	String	activityNumber=request.getParameter("activityNumber");
	String	activityPrize=request.getParameter("activityPrize");
	Order	order=new Order();
	order.setId(Long.parseLong(id));
	order.setActivityNumber(activityNumber);
	order.setActivityPrize(activityPrize);
	orderService.update(order);
		
		return SUCCESS_MESSAGE;
	}
	
	//教师导入excel
			@ResponseBody
			@RequestMapping(value = { "/activityPrize/{id}" }, method = { RequestMethod.POST })
			 public String  activityPrize(@PathVariable Long id,MultipartFile batchFileMessage) {
				List<Long> schoolIds=new ArrayList();
		        int count = 0;
		        HSSFWorkbook book;
				try {
					book = new HSSFWorkbook(batchFileMessage.getInputStream());
			
		        HSSFSheet sheet = book.getSheetAt(0);
		        int i = 0;
		        while (i < sheet.getPhysicalNumberOfRows()) {
		            if (i == 0) {
		                i++;
		                continue;
		            }
		            try {
		                HSSFRow row = sheet.getRow(i);
		                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
		                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING); 
		                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		                String account = row.getCell(0).getStringCellValue();
		                String childname = row.getCell(1).getStringCellValue();
		                String num = row.getCell(2).getStringCellValue();
		                String prize = row.getCell(3).getStringCellValue();
		               
		                Parent parent=    parentService.find("account", account);
		                if(parent!=null){
		                	Map map=new HashMap();
		                	map.put("uid", parent.getId());
		                	map.put("stuName", childname);
		                	map.put("targetId", id);
		                	map.put("type", 2);
		                	
		    	        	List list=new ArrayList();
		    
		    	        	list.add(2);
		    	        	list.add(3);
		    	    
		    	        	list.add(8);
		    	        	map.put("orderStatus", list);
		                	List<Order> orders=orderService.findListByPage(map);
		                	if(orders!=null&&orders.size()>0){
		                		for (Order order : orders) {
		                			
			                		Order order2=new Order();
			                		order2.setActivityNumber(num);
			                		order2.setActivityPrize(prize);
			                		order2.setId(order.getId());
			                		orderService.update(order2);
								}
		                
		                		
		                	}
		                }
		                
		            } catch (Exception e) {
		                e.printStackTrace();
		                i++;
		                continue;
		            }

		            i++;
		        }
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        return "Ok";
			}
}
