	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 代课</title>
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
	                	 <h5>代课</h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>            	
	              	<div class="ibox-content">
	              		<form id="inputForm" class="form-horizontal" action="${base}/admin/common/updateSubstitute.jhtml" method="post">
	              		<input type="hidden" value="${courseId}" name="courseId"/>
	              		<input type="hidden" value="${courseTimeId}" name="courseTimeId"/>
	                   	   <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>代课</h3>
	                       <div class="form-group">
		                   		<div class="row">
		                   			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">主讲教师</label>
	                                <div class="col-sm-9">
                                        <select name="teacherId"  id="teacherId"  class="chosen-select"  >
					                                           
                                        [#list appTeacherInfoList as appTeacherInfo]
	                                      <option value="${appTeacherInfo.id }"   [#if appTeacherInfo.id =teacherId ]selected[/#if] 
	                                      > ${appTeacherInfo.name}(${appTeacherInfo.phone})</option>
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
                       			 	<label class="col-sm-3 control-label">代课教师</label>
	                                <div class="col-sm-9">
                                        <select name="substituteId"  id="substituteId"  class="chosen-select"  >
						                  [#if substitute !='' ]         
			                                     [#list appTeacherInfoList as appTeacherInfo]
				                                      <option value="${appTeacherInfo.id }"   [#if appTeacherInfo.id ==substituteId ]selected[/#if] 
				                                      > ${appTeacherInfo.name}(${appTeacherInfo.phone})</option>
								                 [/#list] 
						                  [/#if] 
					                  
			                           	  [#if substitute =='' ]  
			                           		<option value="" >请选择教师</option>
			                              [/#if]  
										</select>
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
