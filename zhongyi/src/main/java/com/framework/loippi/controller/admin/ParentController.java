package com.framework.loippi.controller.admin;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.util.Map;
import com.framework.loippi.utils.ParameterUtils;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.CourseRoom;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.Parent;
import com.framework.loippi.entity.Student;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.ParentService;
import com.framework.loippi.service.StudentService;
import com.framework.loippi.support.ExcelView;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Page;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.support.Message.Type;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.XDigestUtils;
import com.framework.loippi.utils.qiniu.FileServiceImpl;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.redis.JedisStringCache;
import com.ibm.icu.text.SimpleDateFormat;

import ch.qos.logback.core.pattern.ConverterUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Controller - 家长
 * 
 * @author wyr
 * @version 2.0
 */
@Controller("adminParentController")
@RequestMapping({ "/admin/parent" })
public class ParentController extends GenericController {

	@Resource
	private ParentService parentService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private OrderService orderService;

    @Autowired
    protected JedisCache jedisCache;
	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/parent/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Parent parent, RedirectAttributes redirectAttributes) {
		parentService.save(parent);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		Parent parent = parentService.find(id);
		model.addAttribute("parent", parent);
		
		String token = FileServiceImpl.getToken();
		
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl.QINIU_PREFIX);
		
		return "/admin/parent/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(Pageable pageable,@PathVariable Long id, ModelMap model) {
		Parent parent = parentService.find(id);
		List<Student> studentList = studentService.findList("uid", id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("uid", id);
		pageable.setParameter(map);
		
		Page<Order> pages = this.orderService.findByPage(pageable);
		
		model.addAttribute("parent", parent);
		model.addAttribute("studentList",studentList);
		model.addAttribute("page", pages);
		return "/admin/parent/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Parent parent, RedirectAttributes redirectAttributes) {
		parentService.update(parent);
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
		
		Map paramter2 = RequestMap(request);
		pageable.setParameter(paramter2);
		
		model.addAttribute("paramter", paramter);
		
		Page<Parent> pages = this.parentService.findByPage(pageable);
		
		
		model.addAttribute("page",pages);
		
		return "/admin/parent/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.parentService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 *过滤 
	 */
	private Map RequestMap(HttpServletRequest request) {
		Map<String, Object> paramter = new HashMap<>();
		
		String filter_account = request.getParameter("filter_account");
		String filter_relName = request.getParameter("filter_relName");
		String filter_status = request.getParameter("filter_status");
		String filter_sDate = request.getParameter("filter_sDate");
		String filter_eDate = request.getParameter("filter_eDate");
		
		if(!StringUtil.isEmpty(filter_account)) {
			paramter.put("filter_account", StringUtil.filterSQL(filter_account));
		}
		
		if(!StringUtil.isEmpty(filter_relName)) {
			paramter.put("filter_relName", StringUtil.filterSQL(filter_relName));
		}
		
		if(!StringUtil.isEmpty(filter_status)) {
			paramter.put("filter_status", Integer.parseInt(StringUtil.filterSQL(filter_status)));
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
			
			paramter.put("filter_sDate", StringUtil.filterSQL(filter_sDate));
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
			
			paramter.put("filter_eDate", StringUtil.filterSQL(filter_eDate));
		}

		return paramter;
	}
	
	/**
	 * 导出家长列表
	 */
	@RequestMapping(value="exports",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView exports(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		Map paramter = RequestMap(request);
		
		List<Parent> parentList = parentService.findList(paramter);
		
		String fileName = "家长列表" + ".xls";
		
 		return new ModelAndView(new ExcelView(fileName, fileName,
 				new String[] {"account","nickName","sex","relName","createDateString","statusString"}, 
 				new String[] {"账号","昵称","性别","真实姓名","注册时间","状态"},
 				new Integer[] {5000,5000,5000,5000,5000,5000}, 
 				null,parentList, null), model);
		
	}
	
	/**
	 * 导出订单列表
	 */
	@RequestMapping(value="/view/orderExports",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView orderExports(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> paramter = new HashMap<>();
		
		String id = request.getParameter("id");
		
		paramter.put("uid",Integer.parseInt(id));
		
		List<Order> orderList = orderService.findList(paramter);
		
		String fileName = "订单列表" + ".xls";
		
		return new ModelAndView(new ExcelView(fileName, fileName,
 				new String[] {"bn","phone","unickname","courseName","totalFee","createdTimeString","payTimeString","phone","statusString"}, 
 				new String[] {"订单编号","账号","姓名","课程名称","订单金额（元）","下单时间","支付时间","联系电话","状态"},
 				new Integer[] {5000,5000,5000,5000,5000,5000,5000,5000,5000}, 
 				null,orderList, null), model);
		
	}
	
	/**
	 * 启用家长
	 */
	@RequestMapping(value={"/startParent"},method={RequestMethod.POST})
	@ResponseBody
	public Message startParent(Long id) {
		Parent parent = this.parentService.find(id);
		parent.setStatus(1);
		parentService.update(parent);
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 禁用家长
	 */
	@RequestMapping(value={"/forbidParent"},method={RequestMethod.POST})
	@ResponseBody
	public Message forbidParent(Long id) {
		Parent parent = this.parentService.find(id);
		parent.setStatus(2);
		parentService.update(parent);
		
	    String user_id="user_parent_"+id;
			JedisStringCache user_idStringCache=	jedisCache.getJedisStringCache(user_id);
			 if(user_idStringCache.get()!=null){
				 //如果存在则修改其状态为已经在其他手机登陆了
				 JedisStringCache oldUserjedisStringCache=	jedisCache.getJedisStringCache(user_idStringCache.get());
				 if(oldUserjedisStringCache.get()!=null){
					 oldUserjedisStringCache.remove(user_idStringCache.get());
				 }
			 }
		
		
		return SUCCESS_MESSAGE;
		
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String parentId = request .getParameter("parentId");
		String password = request.getParameter("filter_password");
		
		if(!StringUtil.isEmpty(parentId)) {
			Parent parent = parentService.find("id", Integer.parseInt(parentId));
			if(parent != null) {
				parent.setPassword(XDigestUtils.md5Hex(password));
				
				parentService.update(parent);
			}
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		}
		
		return "redirect:list.jhtml";
	}
	
	
	/**
	 * 家长获取小孩
	 */
	@RequestMapping(value = { "/getstuIdByUid" }, method = { RequestMethod.GET })
	public  @ResponseBody List<Student> getstuIdByUid(Long id) {
		
		List<Student> studentList = studentService.findList("uid", id);
		
		return studentList;
	}
	
	
}
