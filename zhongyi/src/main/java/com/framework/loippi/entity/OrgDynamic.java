package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 机构动态
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ORG_DYNAMIC")
public class OrgDynamic implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 图片路径 */
	@Column(name = "img_url" )
	private String imgUrl;
	
	/** 文字内容 */
	@Column(name = "text_content" )
	private String textContent;
	
	/** 提示 */
	@Column(name = "prompt" )
	private String prompt;
	
	/** 星星数 */
	@Column(name = "starts" )
	private Integer starts;
	
	/** 标签 */
	@Column(name = "mark" )
	private String mark;
	
	/** 点名状态 1确定 2未确定 */
	@Column(name = "callroll_status" )
	private Integer callrollStatus;
	
	/** 点名id */
	@Column(name = "callroll_id" )
	private Long callrollId;
	
	/** 教师id */
	@Column(name = "teacher_id" )
	private Long teacherId;
	
	/** 课程id */
	@Column(name = "course_id" )
	private Long courseId;
	
	/** 课程名称 */
	@Column(name = "course_name" )
	private String courseName;
	
	/** 教师名称 */
	@Column(name = "teacher_name" )
	private String teacherName;
	
	/** 教师头像 */
	@Column(name = "avatar" )
	private String avatar;
	
	/** 创建时间 */
	@Column(name = "create_date" )
	private java.util.Date createDate;
	
	/** 家长id */
	@Column(name = "user_id" )
	private Long userId;
	
	/** 学生id */
	@Column(name = "stu_id" )
	private Long stuId;
	
	/** 邀请id */
	@Column(name = "user_type" )
	private Integer userType;
	
	/** 邀请状态 */
	@Column(name = "invite_status" )
	private Integer inviteStatus;
	
	/** 学生id */
	@Column(name = "invite_id" )
	private Long inviteId;
	
	/** 动态类型 1课程总结 2老师对学生评价 3点名 4迟到提醒 5系统更新 6换课通知*/
	@Column(name = "type" )
	private Integer type;
	
	/**邀请原因*/
	@Column(name = "invite_reason" )
	private String inviteReason;
	
	/**拒绝原因*/
	@Column(name = "refuse_reason" )
	private String refuseReason;
	

	@Column(name = "old_course_name" )
	private String oldCourseName;
	

	@Column(name = "new_course_name" )
	private String newCourseName;
	
	@Column(name = "remain_time" )
	private Integer remainTime;
	//目标id 消息id
	@Column(name = "target_id" )
	private Long targetId;
	//消息状态 1 未读 2已读
	@Column(name = "status" )
	private Integer status;
	
	/** 机构id */
	@Column(name = "org_id" )
	private Long orgId;
	
}
