	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 调课</title>
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
	                	 <h5>调课</h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>            	
	              	<div class="ibox-content">
	              		<form id="inputForm" class="form-horizontal" action="${base}/admin/common/updateCourseTime.jhtml" method="post">
	              		<input type="hidden" value="${courseTeaTime.id}" name="courseTeaTimeId"/>
	              		<input type="hidden" value="${courseId}" name="courseId"/>
	                   	   <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>调课</h3>
	                       <div class="form-group">
		                   		<div class="row">
		                   			<div class="col-sm-6">
		                   			 	<label class="col-sm-3 control-label">上课时间</label>
			                                <div class="col-sm-9">
			                                    <p class="form-control-static">
	                                					${(courseTeaTime.courseTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(courseTeaTime.courseTime1)!''}~${(courseTeaTime.courseTime2)!''}
	                                			</p>
			                               </div>
		                   			</div>
								</div>
	                   		</div>
	                    
	                  
							<!--/row-->
							
	                     <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">调课至  <span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <div class="input-daterange input-group col-sm-3" id="datepicker">
                                    		[#if courseTeaTime.type != 2]
					        					<input type="text" placeholder="年/月/日" name="changeTime" id="changeTime" class="input-sm form-control  ">
					        				[/#if]
					        				[#if courseTeaTime.type == 2]
					        					<input type="text" placeholder="年/月/日" name="changeTime" id="changeTime" value="${(courseTeaTime.oldTime?string('yyyy-MM-dd'))!''}" class="input-sm form-control  ">
					        				[/#if]
					        			</div>
	                                </div>
                       			</div>
							</div>
	                    </div>
						
						<!--/row-->
						
                       <div class="form-group">
                   			<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">调课时间<span style="color:#F00">(*)</span></label>
	                   			 	 <div class="col-sm-3">
		                                 <div class="input-group "> 
	                                 		[#if courseTeaTime.type != 2]
	                                			<input type="text" class="input-sm  form-control clockpicker" name="changeStart" placeholder="开始"/>
	                              				<span class="input-group-addon">到</span>
	                               				<input type="text" class="input-sm  form-control clockpicker2 "  name="changeEnd" placeholder="结束" />
	                               			[/#if]
	                               			[#if courseTeaTime.type == 2]
	                                			<input type="text" class="input-sm  form-control clockpicker" name="changeStart" placeholder="开始" value="${(courseTeaTime.oldTime1)!''}"/>
	                              				<span class="input-group-addon">到</span>
	                               				<input type="text" class="input-sm  form-control clockpicker2 "  name="changeEnd" placeholder="结束" value="${(courseTeaTime.oldTime2)!''}" />
	                               			[/#if]
										</div>
									</div>
                    			</div>
                    		</div>
                    	</div>
						
							<!--/row-->
							
	                       <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">原因<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-5">
	                                    <textarea name="reason" id="reason"  class="input-sm form-control ">${(courseTeaTime.reason)!''}</textarea>
	                                </div>
	                   			</div>
							</div>
	                    </div>
							
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
