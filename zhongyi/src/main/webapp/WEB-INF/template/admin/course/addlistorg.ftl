<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增课程</title>
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
                    	 <h5>新增课程</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/course/listorg.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="savelistorg.jhtml" method="post">
                                                
                          <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                           
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程名称<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">类型<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <select name="type" id="type" data-placeholder="类型..." class="chosen-select"  style="width:350px;" >
                                             <option   value="2">集体课</option>	
						               </select>
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
	                                    <input type="text" name="totalSum" class="form-control" maxlength="3" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">总价格<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="price" class="form-control" maxlength="8" />
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
	                                    <select name="classId"  id="classId"  class="chosen-select"  >
					                             <option value="" >请选择课室</option>
					                                [#list courseRooms as courseRoom]
       						 	<option value="${courseRoom.id }" >${courseRoom.name}</option>
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
                       			 	<label class="col-sm-3 control-label">主讲教师<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
                                        <select name="teacherId"  id="teacherId"  class="chosen-select"  >
					                             <option value="" >请选择教师</option>
					                                [#list teachers as teacher]
       						 	<option value="${teacher.id }" >${teacher.name}</option>
    						  [/#list] 
										</select>
										
									 </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">封面图</label>
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
<input type="hidden"  name="image" id="image" />
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
                                        <select name="assistant"  id="assistant"  class="chosen-select"  >
					                             <option value="" >请选择教师</option>
					                                [#list teachers as teacher]
       						 	<option value="${teacher.id }" >${teacher.name}</option>
    						  [/#list] 
										</select>
										
									 </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">预约试课</label>
	                                <div class="col-sm-9">
                                       <select name="praper"  id="praper"  class="chosen-select"  >
					                             <option value="2" >关闭</option>
					                             <option value="1" >开启</option>
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
                                        <select name="substitute"  id="substitute"  class="chosen-select"  >
					                             <option value="" >请选择教师</option>
					                                [#list teachers as teacher]
       						 	<option value="${teacher.id }" >${teacher.name}</option>
    						  [/#list] 
										</select>
										
									 </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态</label>
	                                <div class="col-sm-9">
                                       <select name="status"  id="status"  class="chosen-select"  >
					                             <option value="1" >上架</option>
					                             <option value="2" >下架</option>
										</select>

	                                </div>
                       			</div>
							</div>
                        </div>
                        
                        
                        
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">课程类型</label>
	                                <div class="col-sm-9">
	                             	<select name="courseType"  id="courseType"  class="chosen-select"  >
	                             		<option value="" >请选择课程类型</option>
	                                   	[#list courseTypes as courseType1]
	                               				<option value="${courseType1.name}" 
	                               				> ${courseType1.name}</option>
			                     	   	[/#list]
			                     	  </select>  
										
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
	                                	<textarea id="editor" name="description" class="editor"></textarea>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
									    <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>课时安排</h3>  
                               <hr>
                       
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课时介绍<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
								      <input type="text" name="periodsDesc" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程节数<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
								      <input type="text" name="periods" class="form-control" maxlength="3" />
	                                </div>
                       			</div>
							</div>
                        </div>
                        
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课日期<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
                                <div class="input-daterange input-group" id="datepicker">
                                <input type="text" class="input-sm form-control" name="start" id="start"  />
                                <span class="input-group-addon">到</span>
                                <input type="text" class="input-sm form-control" name="end"  id="end"/>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                        </div>
                        
                        
                        
							<!--/row-->
							<div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">选择日期<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week1" id="week1"    value="1" >周一</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week2"  id="week2"   value="2" >周二</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week3"  id="week3"   value="3" >周三</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week4"   id="week4"  value="4" >周四</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week5"   id="week5"  value="5" >周五</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week6"  id="week6"   value="6" >周六</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week7"  id="week7"   value="7" >周日</label>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
							
								<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间<span style="color:#F00">(*)</span></label>
	                            <div class="col-sm-1">
                                    <label class="control-label"> <input class="i-checks"  type="checkbox" name="timeRedio1" id="timeRedio1"    value="1" ></label>　
                                  </div>	
                                  
                                   <div class="col-sm-5">	                                
                       <div class="input-group">
                           <span class="input-group clockpicker">
                                <input type="text" class="input-sm form-control" id="time1" name="time1"/></span>
                              <span class="input-group-addon">到</span>
                           <span class="input-group clockpicker2">
                               <input type="text" class="input-sm form-control" id="time2" name="time2" />
                            </span>
                        </div>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                     
                        	<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                            <div class="col-sm-1">
                                    <label class="control-label"> <input class="i-checks"  type="checkbox"  id="timeRedio2"     name="timeRedio2"   value="1" ></label>　
                                  </div>	
                                  
                                   <div class="col-sm-5">	                                
                       <div class="input-group">
                           <span class="input-group clockpicker">
                                <input type="text" class="input-sm form-control" id="time3" name="time3"/></span>
                              <span class="input-group-addon">到</span>
                           <span class="input-group clockpicker2">
                               <input type="text" class="input-sm form-control"  id="time4"  name="time4" />
                            </span>
                        </div>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                     	<!--/row-->
                     					 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                            <div class="col-sm-1">
                                    <label class="control-label"> <input class="i-checks"  type="checkbox"  name="timeRedio3"  id="timeRedio3"    value="1" ></label>　
                                  </div>	
                                  
                                   <div class="col-sm-5">	                                
                       <div class="input-group">
                           <span class="input-group clockpicker">
                                <input type="text" class="input-sm form-control" name="time5"  id="time5" /></span>
                              <span class="input-group-addon">到</span>
                           <span class="input-group clockpicker2">
                               <input type="text" class="input-sm form-control"  name="time6"  id="time6"   />
                            </span>
                        </div>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                     	<!--/row-->
                     	        <div class="form-group">
                       		<div class="row">
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">节假日排课</label>
	                                <div class="col-sm-9">
                                       <select name="holiday"  id="holiday"  class="chosen-select"  >
					                             <option value="1" >关闭</option>
					                             <option value="2" >开启</option>
										</select>

	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                     	
							
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
		<script src="${base}/resources/js/plugins/clockpicker/clockpicker.js"></script>
			<script src="${base}/resources/js/plugins/clockpicker/clockpicker.js"></script>
			<link  rel="stylesheet" type="text/css"  href="${base}/resources/css/plugins/clockpicker/clockpicker.css">

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
              
  var i=0;

    $.ajax({
        url: "${base}/admin/course/checkTime.jhtml",
        data:$('#inputForm').serialize(),
        type: 'get',
        dataType: 'json',
        success: function(message) {
            console.log(message.status);
          console.log(message);
          if(message.status==1){
            i=1;
             art.warn('主讲教师在'+message.message+"时间有课上，请重新选择上课时间！");
          }
          
          if(message.status==3){
            i=1;
             art.warn('课室在'+message.message+"时间有课上，请重新选择上课时间！");
          }
        },
            async: false
    });
      if(i!=0){
      return ;
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
							  periodsDesc: {
						required: true
					},
					  start: {
						required: true
					}, end: {
						required: true
					},
					  periods: {
						required: true,
						digits: true
					}
                },
                messages: {
				}
            });
    
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
        	        	$('.i-checks').iCheck({ checkboxClass: 'icheckbox_square-green',  radioClass: 'iradio_square-green',  });
        	
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        		$(".clockpicker").clockpicker({
    placement: 'top'
});
        			$(".clockpicker2").clockpicker({
    placement: 'top'
});
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
				        method  : 'get',
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
