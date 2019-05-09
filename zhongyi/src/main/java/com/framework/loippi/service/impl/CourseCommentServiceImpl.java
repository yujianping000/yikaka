package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.CourseCommentDao;
import com.framework.loippi.dao.CourseDao;
import com.framework.loippi.dao.OrgnizationDao;
import com.framework.loippi.entity.Course;
import com.framework.loippi.entity.CourseComment;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.service.CourseCommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SERVICE - CourseComment(课程评论)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class CourseCommentServiceImpl extends GenericServiceImpl<CourseComment, Long> implements CourseCommentService {
	
	@Autowired
	private CourseCommentDao courseCommentDao;
	@Autowired
	private CourseDao courseDao;
	
	
	@Autowired
	private OrgnizationDao orgnizationDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(courseCommentDao);
	}

	public List<CourseComment> findListByPage(Object parameter){
		return courseCommentDao.findListByPage(parameter);
	}
	
	public void updateCourseCommentCount(Long courseId){
		
		//update 课程星星数
		Course course	=(Course) courseDao.find(courseId);
		if(course==null){
			return;
		}
		Map map=new HashMap();
		map.put("courseId",courseId);
		List<Course>  courses= courseDao.findAvg(map);
		if(courses!=null&&courses.size()>0&&courses.get(0)!=null&&courses.get(0).getName()!=null){
			Course course2=	courses.get(0);
			Integer commentCount=course2.getFavCount();
			course.setReplyCount(commentCount);
			String avg=course2.getName();
		    Double davg=	Double.parseDouble(avg);
		    if(davg>4){
		    	course.setFavCount(5);
		    }else if(davg>3){
		    	course.setFavCount(4);
		    }else if(davg>2){
		    	course.setFavCount(3);
		    }else if(davg>1){
		    	course.setFavCount(2);
		    }else {
		    	course.setFavCount(1);
		    }
			courseDao.update(course);
		}
	

		
		//update 课程所属机构星星数
		Orgnization orgnization	= orgnizationDao.find(course.getOrgId());
		 map=new HashMap();
		 if(orgnization==null){
				return;
			}
		map.put("orgId",orgnization.getId());
		  courses= courseDao.findAvg(map);
		  
		  System.err.println("====================================");
		  System.err.println(courses);
		  
		if(courses!=null&&courses.size()>0&&courses.get(0)!=null&&courses.get(0).getName()!=null){
			Course course2=	courses.get(0);
			Integer commentCount=course2.getFavCount();
			
			System.err.println(commentCount);
			
			orgnization.setReplyCount(commentCount);
			
			System.err.println(orgnization.getReplyCount());
			
			String avg=course2.getName();
		    Double davg=	Double.parseDouble(avg);
		    if(davg>4){
		    	orgnization.setFavStar(5);
		    }else if(davg>3){
		    	orgnization.setFavStar(4);
		    }else if(davg>2){
		    	orgnization.setFavStar(3);
		    }else if(davg>1){
		    	orgnization.setFavStar(2);
		    }else {
		    	orgnization.setFavStar(1);
		    }
			orgnizationDao.update(orgnization);
		}
	
	}
	
public void updateCourseCommentCountAddCount(Long courseId){
		
		//update 课程星星数
		Course course	=(Course) courseDao.find(courseId);
		if(course==null){
			return;
		}
		Map map=new HashMap();
		map.put("courseId",courseId);
		List<Course>  courses= courseDao.findAvg(map);
		
		System.err.println("====================================");
		  System.err.println(courses);
		  
		if(courses!=null&&courses.size()>0&&courses.get(0)!=null&&courses.get(0).getName()!=null){
			Course course2=	courses.get(0);
			Integer commentCount=course2.getFavStar();
			
			System.err.println(commentCount);
			
			course.setReplyCount(commentCount);
			courseDao.update(course);
			String avg=course2.getName();
			
			
		    Double davg=	Double.parseDouble(avg);
		    if(davg>4){
		    	course.setFavCount(5);
		    }else if(davg>3){
		    	course.setFavCount(4);
		    }else if(davg>2){
		    	course.setFavCount(3);
		    }else if(davg>1){
		    	course.setFavCount(2);
		    }else {
		    	course.setFavCount(1);
		    }
		    if(course.getReplyCount()==null){
		    	course.setReplyCount(0);
		    }
		    
			courseDao.update(course);
		}
	

		
		//update 课程所属机构星星数
		Orgnization orgnization	= orgnizationDao.find(course.getOrgId());
		 map=new HashMap();
		 if(orgnization==null){
				return;
			}
		map.put("orgId",orgnization.getId());
		  courses= courseDao.findAvg(map);
		if(courses!=null&&courses.size()>0&&courses.get(0)!=null&&courses.get(0).getName()!=null){
			Course course2=	courses.get(0);
			Integer commentCount=course2.getFavStar();
			orgnization.setReplyCount(commentCount);
			
			courseDao.update(course);
			String avg=course2.getName();
		    Double davg=	Double.parseDouble(avg);
		    if(davg>4){
		    	orgnization.setFavStar(5);
		    }else if(davg>3){
		    	orgnization.setFavStar(4);
		    }else if(davg>2){
		    	orgnization.setFavStar(3);
		    }else if(davg>1){
		    	orgnization.setFavStar(2);
		    }else {
		    	orgnization.setFavStar(1);
		    }
		    if(orgnization.getReplyCount()==null){
		    	orgnization.setReplyCount(0);
		    }
		    
			orgnizationDao.update(orgnization);
		}
	
	}
}
