	﻿<!DOCTYPE html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 编辑调班申请</title>
	<meta name="keywords" content="${setting.siteName}">
	<meta name="description" content="${setting.siteName}">
	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
	<!-- END HEADER -->
	</head>
	<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
	    <div class="row">
	        <div class="col-sm-12">
	            <div class="ibox float-e-margins">
	                <div class="ibox-title">
	                	 <h5>编辑调班申请</h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="location.href='${base}/admin/course_change/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>            	
	              	<div class="ibox-content">
	              		<form id="inputForm" class="form-horizontal" action="${base}/admin/course_change/update.jhtml" method="post">
	                  	<input type="hidden" value="${courseChange.id}" name="id"/>
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">账号</label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="account" class="form-control" value="${(courseChange.account)!''}" maxlength="200" />
		                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">孩子性别</label>
	                                <div class="col-sm-9">
	                                     <select class="form-control m-b" name="sex" id="sex">
	                                     [#if courseChange.sex =="男"]
			                    			<option value="男" selected="selected">男孩</option>
			                    			<option value="女" >女孩</option>
			                    		 [/#if]
			                    		 [#if courseChange.sex =="女"]
			                    		 	<option value="男" >男孩</option>
			                    			<option value="女" selected="selected">女孩</option>
			                    		 [/#if]
	                           			</select>
	                                </div>
	                          
	                        </div>
	                   			
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">联系方式</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="phone" class="form-control" value="${(courseChange.phone)!''}" maxlength="200" />
	                                </div>
	                   			</div>
	                   		
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">出生日期</label>
		                               <div class="input-daterange col-sm-9 input-group" id="datepicker">
		                               		<input type="text" placeholder="年/月/日" name="birtchday" id="birtchday" value="${(courseChange.birtchday?string("yyyy-MM-dd"))!''}" class="input-sm form-control  ">
		                               </div>
	                   			</div>
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">姓名</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" id="name" class="form-control" value="${(courseChange.name)!''}" maxlength="200" />
	                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">课程名称</label>
	                                <div class="col-sm-9">
	                                	<select name="courseId"  id="courseId" class="chosen-select"  >
	                                	 [#list courseList as course]
	                                	 <option value="${course.id }"  [#if course.id == courseChange.courseId ]selected[/#if] 
	                                      > ${course.name}</option>
	                                    [/#list]
	                                    </select>
	                                </div>
	                   			</div>
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">孩子姓名</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="studentName" id="studentName" value="${(courseChange.studentName)!''}" class="form-control" maxlength="200" />
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">原因</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="content" id="content" value="${(courseChange.content)!''}" class="form-control" maxlength="200" />
	                                </div>
	                   			</div>
	                   			</div>
							</div>
	                   
							<!--/row-->
						
	                    <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                            <button class="btn btn-success" type="submit">保存内容</button>
	                        </div>
	                    </div>
	                    </form>
	              	</div>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
	<script>
	
	    $(document).ready(function () {
	    	[@flash_message /]
	    	
	    	
	    	//时间控件
			$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
	    	
	    	
	    	$(".chosen-select").chosen({width:"100%"}); 
	    	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
	    	
	    	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
	    	
			$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
	    	$("#inputForm").validate({
	            rules: {
	            },
	            messages: {
				}
	        });
	    });
	</script>
	</body>
	</html>
