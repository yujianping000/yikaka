<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑家长</title>
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
                    	 <h5>编辑家长</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/parent/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/parent/update.jhtml" method="post">
                  		<input type="hidden" value="${parent.id}" name="id"/>
                       
               		 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="account" class="form-control" maxlength="200" value="${(parent.account)!''}" readonly />
	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">真实姓名</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="relName" class="form-control" maxlength="200" value="${(parent.relName)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">性别</label>
	                                <div class="col-sm-9">
	                                      <select class="form-control" maxlength="200" name="sex">
			                        			<option value="男" [#if parent.sex == '男'] selected="selected"[/#if]>男</option>
			                                	<option  value="女" [#if parent.sex == '女'] selected="selected"[/#if]>女</option>
			                              </select>
	                                </div>
                       			</div>
                       		
                       		<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">昵称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nickName" class="form-control" maxlength="200" value="${(parent.nickName)!''}"/>
	                                </div>
                       			</div>	
							</div>
                        </div>
                        
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态</label>
	                                <div class="col-sm-9">
		                                  <select class="form-control" maxlength="200" name="status">
			                        			<option value="1" [#if parent.status == '1'] selected="selected"[/#if]>启用</option>
			                                	<option  value="2" [#if parent.status == '2'] selected="selected"[/#if]>禁用</option>
			                              </select>
	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">注册时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createDate" class="form-control" maxlength="200" value="${(parent.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly />
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">头像</label>
	                                <div class="col-sm-9">
	                                	<table border="0">
 						 					<tr>
    											<td>
    												<div style="float: left;">
														<!--<img style="height:50px;width: 50px;"  class="img-circle"  id="defaultAvatar" name='defaultAvatar' src="${(parent.avatar!'')}"/>-->
														<img style="height:150px;width:150px;" class="img-circle"  id="defaultAvatar" name='defaultAvatar' src="${(parent.avatar!'')}"/>
													</div>
 												</td>
											</tr>
											
     										<tr>
       											<td>
													<div style="float: left;margin-top:5px;margin-left:20px">
														<input type="file"  id="imageFile"/>
													</div>
												</td>
  											</tr>
										</table>
										<input type="hidden"  name="avatar" id="uploadAvatar" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	
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
    	<input type="hidden" id="token" value="${token}"/>
		<input type="hidden" id="PREFIX" value="${PREFIX}"/>
    		
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
	
	
	<script src="${base}/resources/plugins/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/uploadify/uploadify.css">
	
	
	
    <script>
        $(document).ready(function () {
        	[@flash_message /]
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
            	
            } 
				
			});
            
            
            
        });
    </script>
</body>
</html>
