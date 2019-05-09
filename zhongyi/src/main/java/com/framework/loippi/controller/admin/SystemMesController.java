package com.framework.loippi.controller.admin;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import com.framework.loippi.utils.JpushRunnable;
import com.framework.loippi.utils.JpushRunnable2;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.qiniu.FileServiceImpl4Qiniu;
import com.ibm.icu.text.SimpleDateFormat;

import org.jdom.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.app.common.Constants;
import com.framework.loippi.entity.Order;
import com.framework.loippi.entity.OrgDynamic;
import com.framework.loippi.entity.SystemMes;
import com.framework.loippi.entity.TParent;
import com.framework.loippi.entity.Teacher;
import com.framework.loippi.entity.TeacherAuth;
import com.framework.loippi.entity.User;
import com.framework.loippi.mybatis.paginator.domain.Order.Direction;
import com.framework.loippi.service.OrderService;
import com.framework.loippi.service.OrgDynamicService;
import com.framework.loippi.service.SystemMesService;
import com.framework.loippi.service.TParentService;
import com.framework.loippi.service.TeacherAuthService;
import com.framework.loippi.service.TeacherService;
import com.framework.loippi.service.UserService;
import com.framework.loippi.service.UserUnreadService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;
import java.util.HashMap;
import java.util.List;

/**
 * Controller - 系统消息
 * 
 * @author wgb
 * @version 2.0
 */
@Controller("adminSystemMesController")
@RequestMapping({ "/admin/system_mes" })
public class SystemMesController extends GenericController {

	@Resource
	private SystemMesService systemMesService;

	
	@Resource
	private UserService userService;
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TParentService parentService;
	
	@Resource
	private OrgDynamicService orgDynamicService;
	
	@Resource
	private TeacherAuthService teacherAuthService;
	
	
	@Resource
	private  OrderService orderService;
	
	@Resource
	private  UserUnreadService userUnreadService;
	
	/**
	 * 跳转添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		String token = FileServiceImpl4Qiniu.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl4Qiniu.QINIU_PREFIX);
		return "/admin/system_mes/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SystemMes systemMes,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		
		
	User user=	userService.getCurrent();
	
	    systemMes.setUserId(user.getId());
	    systemMes.setUserName(user.getNickname());
		systemMes.setCreateDate(new Date());
		systemMesService.save(systemMes);
		//全体
       if(0==systemMes.getType()){

    	   
    	   
    	 //全部老师
    	   List<Teacher>   teachers= teacherService.findAll();
    	   for (Teacher teacher : teachers) {
    		  OrgDynamic  orgDynamic=new OrgDynamic();
    		  orgDynamic.setImgUrl(systemMes.getImage());
    		  orgDynamic.setTextContent(systemMes.getContent());
    		  orgDynamic.setPrompt(systemMes.getTitle());
    		  orgDynamic.setTargetId(systemMes.getId());
    		  orgDynamic.setStarts(2);
    		  orgDynamic.setUserType(2);
    			 orgDynamic.setType(5);
    			 orgDynamic.setCreateDate(new Date());
    		  orgDynamic.setUserId(teacher.getId());
    		  orgDynamicService.save(orgDynamic);
    		  userUnreadService.setUserUnread(2, teacher.getId(), 5);
    		   JpushRunnable2 t1 = new JpushRunnable2(systemMes.getTitle(), "t"+teacher.getId(), "type",
   					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
    		   t1.start();
		    }
    	    
    	   //全部家长
    	   List<TParent>   parents=  parentService.findAll();
    	   for (TParent tParent : parents) {
    		   OrgDynamic  orgDynamic=new OrgDynamic();
     		  orgDynamic.setImgUrl(systemMes.getImage());
     		  orgDynamic.setTextContent(systemMes.getContent());
     		  orgDynamic.setPrompt(systemMes.getTitle());
     		  orgDynamic.setTargetId(systemMes.getId());
     		  orgDynamic.setStarts(1);
 			 orgDynamic.setCreateDate(new Date());

     		  orgDynamic.setUserType(1);
     		 orgDynamic.setType(5);
     		  orgDynamic.setUserId(tParent.getId());
     		  orgDynamicService.save(orgDynamic);
     			userUnreadService.setUserUnread(1, tParent.getId(), 5);
     		   JpushRunnable t1 = new JpushRunnable(systemMes.getTitle(), "p"+tParent.getId(), "type",
    					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
     		   t1.start();
		   }
    	   
       }else
    		//全体
           if(1==systemMes.getType()){
        	 //全部老师
        	   List<Teacher>   teachers= teacherService.findAll();
        	   for (Teacher teacher : teachers) {
        		  OrgDynamic  orgDynamic=new OrgDynamic();
        		  orgDynamic.setImgUrl(systemMes.getImage());
        		  orgDynamic.setTextContent(systemMes.getContent());
        		  orgDynamic.setPrompt(systemMes.getTitle());
        		  orgDynamic.setTargetId(systemMes.getId());
        		  orgDynamic.setStarts(2);
        		  orgDynamic.setUserType(2);
     			 orgDynamic.setCreateDate(new Date());

        			 orgDynamic.setType(5);
        		  orgDynamic.setUserId(teacher.getId());
        		  orgDynamicService.save(orgDynamic);
        		  userUnreadService.setUserUnread(2, teacher.getId(), 5);
        		   JpushRunnable2 t1 = new JpushRunnable2(systemMes.getTitle(), "t"+teacher.getId(), "type",
       					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
        		   t1.start();
    		    }
        	    
        	
           }
           else
       		//全体
              if(2==systemMes.getType()){
            	  //全部家长
           	   List<TParent>   parents=  parentService.findAll();
           	   for (TParent tParent : parents) {
           		   OrgDynamic  orgDynamic=new OrgDynamic();
            		  orgDynamic.setImgUrl(systemMes.getImage());
            		  orgDynamic.setTextContent(systemMes.getContent());
            		  orgDynamic.setPrompt(systemMes.getTitle());
            		  orgDynamic.setTargetId(systemMes.getId());
            		  orgDynamic.setStarts(1);
            		  orgDynamic.setUserType(1);
         			 orgDynamic.setCreateDate(new Date());

            		 orgDynamic.setType(5);
            		  orgDynamic.setUserId(tParent.getId());
            		  orgDynamicService.save(orgDynamic);
            			userUnreadService.setUserUnread(1, tParent.getId(), 5);
            		   JpushRunnable t1 = new JpushRunnable(systemMes.getTitle(), "p"+tParent.getId(), "type",
           					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
            		   t1.start();
       		   }
              }
   	
	
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	private Map mapService() {
		Map map = new HashMap();

		return map;

	}
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		SystemMes systemMes = systemMesService.find(id);
		model.addAttribute("systemMes", systemMes);
		return "/admin/system_mes/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		SystemMes systemMes = systemMesService.find(id);
		model.addAttribute("systemMes", systemMes);
		return "/admin/system_mes/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SystemMes systemMes, RedirectAttributes redirectAttributes) {
		systemMesService.update(systemMes);
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
		  
		  String filter_eDate=request.getParameter("filter_eDate");
			String filter_sDate=request.getParameter("filter_sDate");
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

		  
		  
			pageable.setOrderDirection(Direction.DESC);
			pageable.setOrderProperty("create_date");
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.systemMesService.findByPage(pageable));
		return "/admin/system_mes/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.systemMesService.deleteAll(ids);
		
		for (int i = 0; i < ids.length; i++) {
			
			Map map=new HashMap();
			map.put("targetId", ids[i]);
			map.put("type", 5);
			orgDynamicService.deleteByParams(map);
		
		}
		return SUCCESS_MESSAGE;
	}
	
	
	//机构
	/**
	 * 跳转添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/addorg" }, method = { RequestMethod.GET })
	public String addorg(ModelMap model) {
		String token = FileServiceImpl4Qiniu.getToken();
		model.addAttribute("token", token);
		model.addAttribute("PREFIX", FileServiceImpl4Qiniu.QINIU_PREFIX);
		return "/admin/system_mes/addorg";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveorg", method = RequestMethod.POST)
	public String saveorg(SystemMes systemMes,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		
		
	User user=	userService.getCurrent();
	systemMes.setMessageId(user.getOrgId());
	    systemMes.setUserId(user.getId());
	    systemMes.setUserName(user.getNickname());
		systemMes.setCreateDate(new Date());
		systemMesService.save(systemMes);
		//全体
       if(0==systemMes.getType()){
    	   //全部老师
    	   //机构老师
    	   Map params=new HashMap();
    	   params.put("orgId", user.getOrgId());
    	   params.put("status", 1);
    	   List<TeacherAuth>  teacherAuths= teacherAuthService.findList(params);
    	   Map<Long,Long> map=new HashMap();
    	   for (TeacherAuth teacherAuth : teacherAuths) {
    		   map.put(teacherAuth.getTeacherId(), teacherAuth.getTeacherId());
    		   
    	   }
    	   
    	   for (Long id : map.values()) {
             
    		  OrgDynamic  orgDynamic=new OrgDynamic();
    		  orgDynamic.setImgUrl(systemMes.getImage());
    		  orgDynamic.setTextContent(systemMes.getContent());
    		  orgDynamic.setPrompt(systemMes.getTitle());
    		  orgDynamic.setTargetId(systemMes.getId());
    		  orgDynamic.setStarts(2);
    		  orgDynamic.setUserType(2);
 			 orgDynamic.setCreateDate(new Date());

    			 orgDynamic.setType(5);
    		  orgDynamic.setUserId(id);
    		  orgDynamicService.save(orgDynamic);
    		   
    		   JpushRunnable2 t1 = new JpushRunnable2(systemMes.getTitle(), "t"+id, "type",
   					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
    		   t1.start();
		    }
    	    
    	   //机构家长
    	   Map parameter=new HashMap();
    	   parameter.put("orgId", user.getOrgId());
    	   List<Order> orders = orderService.findParnetIdByOrgId(parameter);

    	   for (Order order : orders) {
    		   OrgDynamic  orgDynamic=new OrgDynamic();
     		  orgDynamic.setImgUrl(systemMes.getImage());
     		  orgDynamic.setTextContent(systemMes.getContent());
     		  orgDynamic.setPrompt(systemMes.getTitle());
     		  orgDynamic.setTargetId(systemMes.getId());
     		  orgDynamic.setStarts(1);
     		  orgDynamic.setUserType(1);
 			 orgDynamic.setCreateDate(new Date());

     		 orgDynamic.setType(5);
     		  orgDynamic.setUserId(order.getUid());
     		  orgDynamicService.save(orgDynamic);
     		   
     		   JpushRunnable t1 = new JpushRunnable(systemMes.getTitle(), "p"+order.getUid(), "type",
    					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
     		   t1.start();
		   }
    	   
       }else
    		//全体
           if(1==systemMes.getType()){
        	   //机构老师
        	   Map params=new HashMap();
        	   params.put("orgId", user.getOrgId());
        	   params.put("status", 1);
        	   List<TeacherAuth>  teacherAuths= teacherAuthService.findList(params);
        	   Map<Long,Long> map=new HashMap();
        	   for (TeacherAuth teacherAuth : teacherAuths) {
        		   map.put(teacherAuth.getTeacherId(), teacherAuth.getTeacherId());
        		   
        	   }
        	   
        	   for (Long id : map.values()) {
                 
        		  OrgDynamic  orgDynamic=new OrgDynamic();
        		  orgDynamic.setImgUrl(systemMes.getImage());
        		  orgDynamic.setTextContent(systemMes.getContent());
        		  orgDynamic.setPrompt(systemMes.getTitle());
        		  orgDynamic.setTargetId(systemMes.getId());
        		  orgDynamic.setStarts(2);
        		  orgDynamic.setUserType(2);
        			 orgDynamic.setType(5);
        			 orgDynamic.setCreateDate(new Date());

        		  orgDynamic.setUserId(id);
        		  orgDynamicService.save(orgDynamic);
        		   
        		   JpushRunnable2 t1 = new JpushRunnable2(systemMes.getTitle(), "t"+id, "type",
       					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
        		   t1.start();
    		    }
        	    
        	
           }
           else
       		//全体
              if(2==systemMes.getType()){
            	  //全部家长
            	//机构家长
           	   Map parameter=new HashMap();
           	   parameter.put("orgId", user.getOrgId());
           	   List<Order> orders = orderService.findParnetIdByOrgId(parameter);

           	   for (Order order : orders) {
           		   OrgDynamic  orgDynamic=new OrgDynamic();
            		  orgDynamic.setImgUrl(systemMes.getImage());
            		  orgDynamic.setTextContent(systemMes.getContent());
            		  orgDynamic.setPrompt(systemMes.getTitle());
            		  orgDynamic.setTargetId(systemMes.getId());
            		  orgDynamic.setStarts(1);
         			 orgDynamic.setCreateDate(new Date());

            		  orgDynamic.setUserType(1);
            		 orgDynamic.setType(5);
            		  orgDynamic.setUserId(order.getUid());
            		  orgDynamicService.save(orgDynamic);
            		   
            		   JpushRunnable t1 = new JpushRunnable(systemMes.getTitle(), "p"+order.getUid(), "type",
           					Constants.system+"_"+orgDynamic.getId() + "", this.mapService());
            		   t1.start();
       		   }
              }
   	
	
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}


	/**
	 * 编辑
	 */
	@RequestMapping(value = "/editorg/{id}", method = RequestMethod.GET)
	public String editorg(@PathVariable Long id, ModelMap model) {
		SystemMes systemMes = systemMesService.find(id);
		model.addAttribute("systemMes", systemMes);
		return "/admin/system_mes/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/vieworg/{id}", method = RequestMethod.GET)
	public String vieworg(@PathVariable Long id, ModelMap model) {
		SystemMes systemMes = systemMesService.find(id);
		model.addAttribute("systemMes", systemMes);
		return "/admin/system_mes/vieworg";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/updateorg", method = RequestMethod.POST)
	public String updateorg(SystemMes systemMes, RedirectAttributes redirectAttributes) {
		systemMesService.update(systemMes);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:listorg.jhtml";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/listorg" }, method = { RequestMethod.GET })
	public String listorg(Pageable pageable,HttpServletRequest request, ModelMap model) {
		User user=	userService.getCurrent();

		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		  
		  String filter_eDate=request.getParameter("filter_eDate");
			String filter_sDate=request.getParameter("filter_sDate");
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

		  
			pageable.setOrderDirection(Direction.DESC);
			pageable.setOrderProperty("create_date");
			map.put("messageId", user.getOrgId());
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.systemMesService.findByPage(pageable));
		return "/admin/system_mes/listorg";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/deleteorg" }, method = { RequestMethod.POST })
	public @ResponseBody Message deleteorg(Long[] ids) {
		this.systemMesService.deleteAll(ids);
		
		for (int i = 0; i < ids.length; i++) {
			
			Map map=new HashMap();
			map.put("targetId", ids[i]);
			map.put("type", 5);
			orgDynamicService.deleteByParams(map);
		
		}
		return SUCCESS_MESSAGE;
	}
	
	
}
