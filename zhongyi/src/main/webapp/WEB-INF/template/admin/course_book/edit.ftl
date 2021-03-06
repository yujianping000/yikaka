	﻿<!DOCTYPE html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 编辑试课申请</title>
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
	                	 <h5>编辑试课申请</h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="location.href='${base}/admin/course_book/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>            	
	              	<div class="ibox-content">
	              		<form id="inputForm" class="form-horizontal" action="${base}/admin/course_book/update.jhtml" method="post">
	                  	<input type="hidden" value="${courseBook.id}" name="id"/>
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">账号</label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="parentAccount"  value="${(courseBook.parentAccount)!''}" class="form-control" maxlength="200"  />
		                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">出生日期</label>
		                               <div class="input-daterange col-sm-9 input-group" id="datepicker">
		                               		<input type="text" placeholder="年/月/日" name="birthday" id="birthday" value="${(courseBook.birthday?string("yyyy-MM-dd"))!''}"  class="input-sm form-control  ">
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
	                                    <input type="text" name="phone" value="${(courseBook.phone)!''}" class="form-control" maxlength="200"  />
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">课程名称</label>
	                                <div class="col-sm-9">
	                                	<select name="courseId"  id="courseId"  class="chosen-select"  >
	                                	 [#list courseList as course]
	                                	 <option value="${course.id }"  [#if course.id ==  courseBook.courseId ]selected[/#if] 
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
	                                    <input type="text" name="childName" value="${(courseBook.childName)!''}" class="form-control" maxlength="200"  />
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">报班目的</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="remark" value="${(courseBook.remark)!''}" class="form-control" maxlength="200" />
	                                </div>
	                   			</div>
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">孩子性别</label>
	                                <div class="col-sm-9">
	                                     <select class="form-control m-b" name="sex" id="sex">
	                                     [#if courseBook.sex =="男"]
			                    			<option value="男" selected="selected">男孩</option>
			                    			<option value="女" >女孩</option>
			                    		 [/#if]
			                    		 [#if courseBook.sex =="女"]
			                    		 	<option value="男" >男孩</option>
			                    			<option value="女" selected="selected">女孩</option>
			                    		 [/#if]
	                           			</select>
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
