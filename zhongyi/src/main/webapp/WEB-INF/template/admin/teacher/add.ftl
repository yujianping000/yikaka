	﻿<!DOCTYPE html>
	<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 新增教师</title>
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
	                	 <h5>添加教师</h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="location.href='${base}/admin/teacher/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>            	
	              	<div class="ibox-content">
	              		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="get">
	                  	  
	                  	  <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">账号<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="account" class="form-control" maxlength="20" />
		                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">头像<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                   <table border="0">
											  <tr>
											  	<td>
											    	<div style="float: left;">
														<img style="height:150px;width:150px;" class="img-circle" id="defaultAvatar" name="defaultAvatar" src="" />
													</div>
											 	<td>
											 </tr>
											  <tr>
											  	<td>
													<div style="float: left;margin-top:5px;margin-left:20px">
														<input type="file"  id="imageFile" name="imageFile" />
														<span style="display:none; color:#F00" id="span"><i class='fa' ></i>必填</span>
														
													</div>
												</td>
											 </tr>
										</table>
											<input type="hidden"  name="avatar" id="uploadAvatar" />
		                             </div>
	                   			</div>
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">密码<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="password" name="password" id="password" class="form-control" maxlength="20" />
	                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">性别<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <select class="form-control m-b " name="sex">
	   								 		<option  value="男">男</option>
	  								 		<option  value="女">女</option>
	                                 	</select>
	                                </div>
	                   			</div>
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">确认密码<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="password" name="password1" class="form-control" maxlength="20" />
	                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">状态<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <select class="form-control m-b " name="status">
	                                    	<option  value="">状态</option>
	   								 		<option  value="1">启用</option>
	  								 		<option  value="2">禁用</option>
	                                 	</select>
	                                </div>
	                   			</div>
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">类型<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <select class="form-control m-b " name="teacherType">
	                                    	<option  value="">类型</option>
	                                    	<option  value="3">公海教师</option>
	  								 		<option  value="2">兼职教师</option>
	                                 	</select>
	                                </div>
	                   			</div>
	                   			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">出生日期</label>
	                                 <div class="col-sm-9">
	                                    <div class="input-daterange input-group col-sm-12" id="datepicker">
					        				<input type="text" placeholder="年/月/日" name="birthday" id="birthday"  class="input-sm form-control  ">
					        			</div>
	                                </div>
	                   			</div>
	                   			
							</div>
	                    </div>
							<!--/row-->
						
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">姓名<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nickname" class="form-control" maxlength="20" />
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">个人简介</label>
	                                  <div class="col-sm-9">
	                                    <textarea name="description" class="input-sm form-control "></textarea>
	                                </div>
	                   			</div>
	                   			
							</div>
	                    </div>
	                    
						<div class="ibox-content">
							<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>标签信息</h3>
	              				 <div class="form-group">
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签一</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark1" class="form-control" maxlength="8" />
		                                		</div>
	                       				</div>
	                       				
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签二</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark2" class="form-control" maxlength="8" />
		                                		</div>
	                       				</div>
	                       				
	                       			</div>
	                        	</div>
	                        	
	                        	<div class="form-group">
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签三</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark3" class="form-control" maxlength="8" />
		                                		</div>
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
	
		<input type="hidden" id="token" value="${token}"/>
		<input type="hidden" id="PREFIX" value="${PREFIX}"/>
	
	<script src="${base}/resources/plugins/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/uploadify/uploadify.css">
	
	<script>
	
		$("#inputForm").validate({
		              submitHandler: function (form) {
		              if($("#uploadAvatar").val() == "") {
		              	  $("#span").css("display","block");
		              	  return;
		              }else {
		              	  form.submit();
		              }
                  },
		
                rules: {
                	 account: {
                 		minlength: 6,
	                    required: true
					 },
					 
					  avatar: {
                 		required: true
					 },
					 
					  sex: {
                 		required: true
					 },
					 
					  status: {
                 		required: true
					 },
					 
					  teacherType: {
                 		required: true
					 },
					 
					  nickname: {
                 		required: true,
                 		maxlength: 20
					 },
					 
					  password: {
                 		minlength: 6,
	                    required: true
					 },
					 
					  password1: {
                 		required: true,
                 		 minlength: 6,
                 		equalTo:'#password'
					 },
                },
                messages: {
                	password1:{
                        equalTo:"两次密码输入不一致"
                    } 
				}
            });
	
	
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
	    		
	        
	        //图片上传
	         $('#imageFile').uploadify({
				       
				        height  : 30,
				        swf      :'${base}/resources/plugins/uploadify/uploadify.swf',
				        uploader : 'http://upload.qiniu.com/',
				        
				        width   : 120,
				        fileObjName : 'file',
				        'fileTypeExts': '*.gif; *.jpeg; *.jpg; *.png',  
				        buttonText:'选择文件',
				        'fileSizeLimit' : '3000KB',
				        method  : 'Post',
				         'multi': false,//是否允许多文件上传。默认为false
				        'overrideEvents': ['onSelectError', 'onDialogClose'], 
				       'onSelectError': function (file, errorCode, errorMsg) {  
	                     switch (errorCode) {  
	                    case -100:  
	                        alert("上传的文件数量已经超出系统限制的" + jQuery
	                        ('#imageFile').uploadify('settings', 'queueSizeLimit') + "个文件！");  
	                        break;  
	                    case -110:  
	                        alert("文件 [" + file.name + "] 大小超出系统限制的" + jQuery
	                        ('#imageFile').uploadify('settings', 'fileSizeLimit') + "大小！");  
	                        break;  
	                    case -120:  
	                        alert("文件 [" + file.name + "] 大小异常！");  
	                        break;  
	                    case -130:  
	                        alert("文件 [" + file.name + "] 类型不正确！");  
	                        break;  
	                }  
	            },  
	            'onClearQueue': function (queueItemCount) {  
	                alert("取消上传");  
	                return;  
	            }, 
	             'onUploadStart': function (file) {
			           var timestamp=new Date().getTime();
			          // 如想取1000~10000的随机数则：
			            var randoms= Math.floor(Math.random()*100)+9000;
			           timestamp= timestamp+'K'+randoms;			          
	                    $("#imageFile").uploadify(  "settings", "formData",  {'token' : $("#token").val(), 'key' :timestamp+file.type});  
	                    //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
	            } ,   
	            onUploadSuccess: function(file,data, response) {
	            data = jQuery.parseJSON(data);
	            $("#imageImgHref").attr('href',$("#PREFIX").val()+data.key);
	            	$("#uploadAvatar").val($("#PREFIX").val()+data.key);
	            	$("#defaultAvatar").attr('src',$("#PREFIX").val()+data.key);
	            	
	            	$("#span").css("display","none");
	            } 
				
			});
	        
	    });
	</script>
	</body>
	</html>
