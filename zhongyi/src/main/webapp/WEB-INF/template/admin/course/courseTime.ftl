	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 查看课时</title>
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
	                	 <h5>查看课时</h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div> 
	                           	
	              	<div class="ibox-content">
	              		<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>未开始课时</h3>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>上课时间</th>
			                                    <th>调课时间</th>
			                                    <th>原因</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                [#list prepareCourseTimeList as courseTeaTime] 
								        	 <tr>
							                        <td>
							                        	${(courseTeaTime.courseTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(courseTeaTime.courseTime1)!''}~${(courseTeaTime.courseTime2)!''}
							                        </td>
							                        <td>
							                        	[#if courseTeaTime.type=2]
							                        		${(courseTeaTime.oldTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(courseTeaTime.oldTime1)!''}~${(courseTeaTime.oldTime2)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                        </td>
							                         <td>
							                        	[#if courseTeaTime.type=2]
							                        		${(courseTeaTime.reason)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                         	
							                         </td>
							                      <td>
							                        	<a href="${base}/admin/course/courseChangeTime/${courseTeaTime.courseId}/${courseTeaTime.id}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>调课 </a>
							                        	
							                        </td>
						                    	<tr>
					                        [/#list]
			                            </tbody>
			                        </table>
			                       </div>
	              	</div>
	              	
	              	
	              	<div class="ibox-content">
	              		<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>已结束课时</h3>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>上课时间</th>
			                                    <th>调课时间</th>
			                                    <th>原因</th>
			                                </tr>
			                            </thead>
			                            <tbody>
		                                		[#list finishCourseTimeList as courseTeaTime] 
								        	 <tr>
							                        <td>
							                        	${(courseTeaTime.courseTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(courseTeaTime.courseTime1)!''}~${(courseTeaTime.courseTime2)!''}
							                        </td>
							                        <td>
							                        	[#if courseTeaTime.type=2]
							                        		${(courseTeaTime.oldTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(courseTeaTime.oldTime1)!''}~${(courseTeaTime.oldTime2)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                        </td>
							                         <td>
							                        	[#if courseTeaTime.type=2]
							                        		${(courseTeaTime.reason)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                         	
							                         </td>
						                    	<tr>
					                        [/#list]
			                            </tbody>
			                        </table>
			                       </div>
	              	</div>
	              	
	            </div>
	        </div>
	    </div>
	</div>
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
	
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/uploadify/uploadify.css">
	<script src="${base}/resources/js/plugins/clockpicker/clockpicker.js"></script>
			<script src="${base}/resources/js/plugins/clockpicker/clockpicker.js"></script>
			<link  rel="stylesheet" type="text/css"  href="${base}/resources/css/plugins/clockpicker/clockpicker.css">
	
	<script>
		
		$("#inputForm").validate({
	            rules: {
	            	 changeTime: {
	             		required: true,
					 },
					 
					  changeStart: {
	             		required: true
					 },
					 
					  changeEnd: {
	             		required: true
					 },
					 
					  reason: {
	             		required: true
					 },
					 
	            },
	            messages: {
				}
	        });
	
	
	    $(document).ready(function () {
	    	[@flash_message /]
	    	
	    	$(".clockpicker").clockpicker({
 				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			
			$(".clockpicker2").clockpicker({
 				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
	    	
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
	    	
	        
	    });
	</script>
	</body>
	</html>
