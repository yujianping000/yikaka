<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 查看课程</title>
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
                    	 <h5>查看课程</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/course/listorg.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="saveoneorg.jhtml" method="post">
                           <input type="hidden" value="${course.id}" name="id"/>
                                                
                          <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                          
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程名称<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <p class="form-control-static">${course.name}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">类型<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
						               <p class="form-control-static">一对一</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">剩余名额</label>
	                                <div class="col-sm-9">
	                                  <p class="form-control-static">${course.freez}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">总价格<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${course.price}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课室</label>
	                                <div class="col-sm-9">
										<p class="form-control-static">${class}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">主讲教师<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
										<p class="form-control-static">${teacher}</p>
									 </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">封面图</label>
	                                <div class="col-sm-9">

<table border="0">
  <tr>
    <td><div style="height:150px;width: 300px;float: left;">

		<img style="height:150px;width: 300px; "  id="imageImg" name='imageImg' src="${course.image}"/>


</div>
 <td>

</table>

	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
							
								<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">助教教师</label>
	                                <div class="col-sm-9">

									<p class="form-control-static">${assistant}</p>
										
									 </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">预约试课</label>
	                                <div class="col-sm-9">
<p class="form-control-static">[#if course.praper == "1"]开启[/#if]</p>
<p class="form-control-static">[#if course.praper == "2"]关闭[/#if]</p>
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
										<p class="form-control-static">${substitute}</p>
									 </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态</label>
	                                <div class="col-sm-9">
<p class="form-control-static">[#if course.status == "1"]上架中[/#if][#if course.status == "2"]下架[/#if]</p>

	                                </div>
                       			</div>
							</div>
                        </div>
                        
                        
                        <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">课程类型</label>
	                                <div class="col-sm-9">
										<p class="form-control-static">${(course.courseType)!''}</p>
									 </div>
	                   			</div>
	                   			
							</div>
	                    </div>
                        
                        
							<!--/row-->
						
						
                    
						
                        
						    <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>课程介绍</h3>  
                             
                       
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                	<textarea id="editor" name="description" class="editor">${course.description}</textarea>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
					<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>课时安排</h3>  
                       
                       
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课时介绍<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
								      <p class="form-control-static">${course.periodsDesc}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程节数<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
								        <p class="form-control-static">${course.periods}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
							<div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
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
    
                  	$("#inputForm").validate({
                  		  submitHandler:function(form){
	          var orgId = $("#orgId").val();
              if(orgId==""){
              art.warn('请选择机构！');
              return;
              }
             
              var classId = $("#classId").val();
              if(classId==""){
              art.warn('请选择课室！');
              return;
              }
              
                 var teacherId = $("#teacherId").val();
              if(teacherId==""){
              art.warn('请选择主讲教师！');
              return;
              }
      
            form.submit();
        } ,
              	
                rules: {
                   name: {
						required: true
					},
                price: {
						maxlength: 8,
						number: true
					},
							  orgId: {
						required: true
					},
					  periodsDesc: {
						required: true
					},
					
						  periods: {
						required: true
					}
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
  
            
                    
         //获取班级
             $("#orgId").change(function () {
                var data = $(this).val();
                var classId=$("#classId");
                classId.empty();
                  classId.html("");
                  classId.val('');
                  classId.text('');
                if (data == null || data == '') {
                    return;
                }
            
            
            
            
            
                 //获取对应的课室
                $.get("${base}/admin/course/getRoomByOrgIdorg.json?id=" + data, function (result) {
             
                    if (result == null || result == ''|| result == []) {
                     classId.empty();
                  classId.html("");
                    classId.append("<option  value=''>" + '请选择课室' + "</option>");
                    }
    
                    if(result.length>0){
                     classId.append("<option  value=''>" + '请选择课室' + "</option>");
                    for (var i = 0; i < result.length; i++) {
                        classId.append("<option  value=\"" + result[i].id + "\">" + result[i].name + "</option>");
                    }
                    }
                    $(".chosen-select").trigger("chosen:updated")
                });
                
                
                
                   //获取对应的教师
                $.get("${base}/admin/course/getTeachersByOrgIdorg.json?id=" + data, function (result) {
                
                //主讲教师
                  var teacherId=$("#teacherId");
                       teacherId.empty();
                  teacherId.html("");
                  teacherId.val('');
                  teacherId.text('');
                    if (result == null || result == ''|| result == []) {
                     teacherId.empty();
                  teacherId.html("");
                    teacherId.append("<option  value=''>" + '请选择教师' + "</option>");
                    }
    
                    if(result.length>0){
                     teacherId.append("<option  value=''>" + '请选择教师' + "</option>");
                    for (var i = 0; i < result.length; i++) {
                        teacherId.append("<option  value=\"" + result[i].id + "\">" + result[i].name+"("+result[i].phone+")"+ "</option>");
                    }
                    };
                    
                 //助教   
                    var assistant=$("#assistant");
                       assistant.empty();
                  assistant.html("");
                  assistant.val('');
                  assistant.text('');
                    if (result == null || result == ''|| result == []) {
                     assistant.empty();
                  assistant.html("");
                    assistant.append("<option  value=''>" + '请选择教师' + "</option>");
                    }
    
                    if(result.length>0){
                     assistant.append("<option  value=''>" + '请选择教师' + "</option>");
                    for (var i = 0; i < result.length; i++) {
                        assistant.append("<option  value=\"" + result[i].id + "\">" + result[i].name+"("+result[i].phone+")"+ "</option>");
                    }
                    };
                     
                    
                                  //助教  
                    var assistant=$("#assistant");
                       assistant.empty();
                  assistant.html("");
                  assistant.val('');
                  assistant.text('');
                    if (result == null || result == ''|| result == []) {
                     assistant.empty();
                  assistant.html("");
                    assistant.append("<option  value=''>" + '请选择教师' + "</option>");
                    }
    
                    if(result.length>0){
                     assistant.append("<option  value=''>" + '请选择教师' + "</option>");
                    for (var i = 0; i < result.length; i++) {
                        assistant.append("<option  value=\"" + result[i].id + "\">" + result[i].name+"("+result[i].phone+")"+ "</option>");
                    }
                    };
                       
                        //代课
                    
                     var substitute=$("#substitute");
                       substitute.empty();
                  substitute.html("");
                  substitute.val('');
                  substitute.text('');
                    if (result == null || result == ''|| result == []) {
                     substitute.empty();
                  substitute.html("");
                    substitute.append("<option  value=''>" + '请选择教师' + "</option>");
                    }
    
                    if(result.length>0){
                     substitute.append("<option  value=''>" + '请选择教师' + "</option>");
                    for (var i = 0; i < result.length; i++) {
                        substitute.append("<option  value=\"" + result[i].id + "\">" + result[i].name+"("+result[i].phone+")"+ "</option>");
                    }
                    };
                          
                       
                    
                    $(".chosen-select").trigger("chosen:updated")
                });
                
         
            
                
                
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
