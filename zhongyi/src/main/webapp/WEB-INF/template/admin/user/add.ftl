
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
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
                    	 <h5>添加管理员</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="saveAclForm" class="form-horizontal" action="save.jhtml" method="post">
                  		<div class="form-group">
                       		<div class="row">
                       			 <div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">头像</label>
	                                <div class="col-sm-9">
<table border="0">
  <tr>
    <td><div style="height:150px;width: 300px;float: left;">
		<img style="height:150px;width: 300px; "  id="imageImg" name='imageImg' src=""/>
</div>
 <td>
</tr>
     <tr>
       <td>
<div style="float: left;margin-top:5px;margin-left:20px">
	<input type="file"  id="imageFile"/>
</div></td>
  </tr>
</table>
<input type="hidden"  name="avatar" id="image" value="" />
										
	                                </div>
                       			 </div>
                       			 <div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>角色</label>
	                                <div class="col-sm-9">
	                                    <select name="roleId" id="roleId" data-placeholder="选择角色..." class="chosen-select"  style="width:350px;" >
                                     		[#list roles as role]
                                     		<option  hassubinfo="true" value="${role.id}">${role.name}</option>
                                     		[/#list]
										</select>
	                                </div>
                       			 </div>
                       		</div>
                        </div>
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">昵称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nickname" id="nickname" class="form-control">
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>用户名</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="username" id="username" class="form-control">
	                                </div>
                       			</div>
                       		</div>
                        </div>
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>密码</label>
	                                <div class="col-sm-9">
	                                    <input type="password" name="password" id="password" class="form-control">
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>重复密码</label>
	                                <div class="col-sm-9">
	                                    <input type="password" name="repassword" id="repassword" class="form-control">
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
        	var $browserButton = $("#browserButton");
			$browserButton.browser({input:$("#avatar")});
        	$(".chosen-select").chosen({width:"100%"}); 
        	
        	$('.i-checks').iCheck({ checkboxClass: 'icheckbox_square-green',  radioClass: 'iradio_square-green',  });
        	jQuery("#inputForm").validate({
			                rules: {
			                   username: {
									required: true,
									pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
									minlength: 2,
									maxlength: 20,
									remote: {
										url: "check_username.jhtml",
										cache: false
									}
								},
								password: {
									required: true,
									pattern: /^[^\s&\"<>]+$/,
									minlength: 4,
									maxlength: 20
								},
								repassword: {
									required: true,
									equalTo: "#password"
								},
								email:{
									required: true,
									email :true
								},
								roleId: "required"
			                },
			                messages: {
								username: {
									pattern: "${message("admin.validate.illegal")}",
									remote: "${message("admin.validate.exist")}"
								},
								password: {
									pattern: "${message("admin.validate.illegal")}"
								}
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
                	$("#image").val($("#PREFIX").val()+data.key);
                	$("#imageImg").attr('src',$("#PREFIX").val()+data.key);
                	
                } 
				
			});         
			            
        });
        
    </script>
</body>

</html>
