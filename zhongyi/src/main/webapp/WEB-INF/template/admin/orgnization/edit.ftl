
<!DOCTYPE html>
<html>
<head>
﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑机构</title>
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
                    	 <h5>编辑机构</h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:systemorg:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs btn-edit-loippi-pwd" data-toggle="modal" data-id="${(orgnization.id)!''}"    
                 			data-account="${(orgnization.account)!''}" data-target="#myModal"><i class="fa fa-plus"></i>修改密码</a>
                 		 [/@shiro.hasPermission]
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" autocomplete="off"  class="form-horizontal" action="${base}/admin/orgnization/update.jhtml" method="get">
                  		<input style="display:none">
                  		<input type="password"  style="display:none">
                  		<input type="hidden" id="oldaccount"  value="${(orgnization.account)!''}" name="oldaccount" />
                  		<input type="hidden" value="${orgnization.id}" name="id"/>
                       	  <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                	<input type="text" name="account" id="account" class="form-control" value="${(orgnization.account)!''}"  maxlength="200" />
		                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">机构名称<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" value="${(orgnization.name)!''}" maxlength="200" />
	                                </div>
	                   			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">机构电话<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="telPhone" class="form-control" value="${(orgnization.telPhone)!''}"  maxlength="200" />
	                                </div>
	                   			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                 <select class="form-control m-b " name="status">
       								 	<option  value="1" [#if orgnization.status == '1'] selected="selected"[/#if]>启用</option>
      								 	<option  value="2" [#if orgnization.status == '2'] selected="selected"[/#if]>禁用</option>
	                                 </select>
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
                        
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系人<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="serviceName" class="form-control" value="${(orgnization.serviceName)!''}"  maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系电话<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="servicePhone" class="form-control" value="${(orgnization.servicePhone)!''}" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
						
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">封面图</label>
	                                <div class="col-sm-9">
	                                	<table border="0">
										  <tr>
										  	<td>
										    	<div style="float: left;">
													<img style="height:150px;width: 150px; " class="img-circle" id="defaultImage" name='defaultImage' src="${(orgnization.image)!''}"/>
												</div>
										 	<td>
										 </tr>
										  <tr>
										  	<td>
												<div style="float: left;margin-top:5px;margin-left:20px">
												<input type="file"  id="imageFile"/>
												</div>
											</td>
										 </tr>
										</table>
										
										<input type="hidden"  name="image" id="uploadImage" />
	                                </div>
                       		  </div>
                       			
                       			<div class="col-sm-6">
                       				<label class="col-sm-3 control-label">手续费（%）<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="fee" class="form-control"  value="${(orgnization.fee)!''}" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       		
                       		<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">地区<span class="required">* </span></label>
	                               
					             	 <div class="col-sm-3">
                                        <select name="provinceId" id="province_id" data-placeholder="省..."
                                                class="chosen-select">
                                            <option hassubinfo="true" value="">请选择省</option>
                                            [#list provinces as province]
                                            <option hassubinfo="true" value="${province.id}" [#if province.id == orgnization.provinceId ]selected[/#if]>${province.name}</option>
                                            [/#list]
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="cityId" id="city_id" data-placeholder="市..."
                                                class="chosen-select" style="width:350px;">
                                                    [#list citys as city]
                                            <option hassubinfo="true" value="${city.id}"  [#if city.id == orgnization.cityId ]selected[/#if] >${city.name}</option>
                                              [/#list]
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="areaId" id="area_id" data-placeholder="县区..."
                                                class="chosen-select" style="width:350px;">
                                                    [#list areas as area]
                                            <option hassubinfo="true" value="${area.id}"   [#if area.id == orgnization.areaId ]selected[/#if]  >${area.name}</option>
                                             [/#list]
                                        </select>
                                    </div>
					    
                       			</div>
                       		
                       		
                       		<!--
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">地区</label>
	                                <div class="col-sm-2">
	                                 	<select class="form-control m-b chosen-select" name="province" id="province">
	                                 	 	[#if orgnization.province !='']<option  value="${orgnization.province}">${orgnization.province}</option>[/#if] 
		                                 	[#if orgnization.province ='']<option  value="">省份</option>[/#if] 
	       				 				 	[#list provinces as area]
	       								 		<option  value="${area.id}"> ${area.name}</option>
	      								 	[/#list]
	                                 	</select>
	                                 
	                                </div>
	                                
	                                <div class="col-sm-2">
                                 				[#if orgnization.province !='']<select class="form-control m-b " name="city" id="city" style="display: block;">[/#if] 
                                 				[#if orgnization.province ='']<select class="form-control m-b chosen-select " name="city" id="city" style="display: block;">[/#if] 
                                 				[#if orgnization.province !='']<option  value="${orgnization.city}">${orgnization.city}</option>[/#if] 
	                                 			[#if orgnization.province ='']<option  value="">城市</option>[/#if]
   								 	
                                 			</select>
                                 
                                	</div>
                                	
	                            	 <div class="col-sm-2">
		                                 		[#if orgnization.province !='']<select class="form-control m-b " name="area" id="area" style="display: block;">[/#if] 
                                 				[#if orgnization.province ='']<select class="form-control m-b chosen-select " name="area" id="area" style="display: block;">[/#if] 
		                                 		[#if orgnization.province !='']<option  value="${orgnization.area}">${orgnization.area}</option>[/#if] 
	                                 			[#if orgnization.province ='']<option  value="">区县</option>[/#if]
	       								 	
		                                 	</select>
		                                 
		                             </div>
                                	
	                             </div>   
	                             -->
	                           <div class="col-sm-6">
	                           		
               			 	   			<label class="col-sm-3 control-label">机构地址<span style="color:#F00">(*)</span></label>
               			 	   			
                                			<div class="col-sm-9">
                                					
                                   		 			<input type="text" id = "addlocation" name="location" onfocus = 'this.value=""' class="form-control" value="${(orgnization.location)!''}" maxlength="200" />
                                   		 			
                                   		 		
                               				</div>
                             		</div>   
	                           
							</div>
                        </div>
                        
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">经度</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="locLng" id="locLng" class="form-control" value="${(orgnization.locLng)!''}" maxlength="200" readonly />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">纬度</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="locLat" id="locLat" class="form-control" value="${(orgnization.locLat)!''}" maxlength="200" readonly />
	                                </div>
                       			</div>
							</div>
                        </div>
						
						<!--/row-->
					
                      <div class="form-group">
                   		<div class="row">
                   			<div class="col-sm-6">
                   			<label class="col-sm-3 control-label"></label>
                   				<div class="col-sm-9">
                   						<div id="container" tabindex="0"></div>
                   						<div id = "message"></div>
                   					
                   				</div>
                   			</div>
                   		</div>
                   	  </div>
						
						<div class="ibox-content">
	          				 <div class="form-group">
	          				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>机构介绍</h3>
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">机构描述</label>
		                                		<div class="col-sm-9">
						    						<textarea id="editor" name="description" class="editor">${(orgnization.description)!''}</textarea>
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
    
    
	 <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	        	<div class="modal-content animated bounceInRight">
	        	
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                	<h4 class="modal-title" id="myModalLabel">修改密码</h4>
	            	</div>
	            	
	            	<form id="inputModalForm" class="form-horizontal" action="${base}/admin/orgnization/updatePwd.jhtml" method="post" >
		                <div class="modal-body">
		                    <div class="form-group">
		                    	<input type="hidden" value="" id="orgId" name="orgId"/>
		                    	<input type="hidden" value="" id="account1" name="account1"/>
		                    	
		                    	<label>新密码</label><span style="color:#F00">(*)</span>
		                        <input type="password" name="filter_password" id="filter_password" placeholder="请输入新密码" class="form-control" >
		                        </br>
		                        <label>确认密码</label><span style="color:#F00">(*)</span>
		                        <input type="password" name="filter_repassword" id="filter_repassword" placeholder="请再次输入新密码" class="form-control"  >
		                        </br>  
		                    </div>
		                </div>
	            
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                   <button type="submit" class="btn btn-success">保存</button>
		                </div>
	              </form> 
	               
	            </div>
	        </div>
	     </div>
	</div>
    
    
    <input type="hidden" id="token" value="${token}"/>
	<input type="hidden" id="PREFIX" value="${PREFIX}"/>
    
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
	
	<style type="text/css">
	      #container{
	        height:         600px;
	        width:         630px;
	        margin-right:  auto;
	        margin-left:   auto;
	      }
    </style>
	
	<script src="${base}/resources/plugins/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/uploadify/uploadify.css">
	
	
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=1d44abe3e9b2069616df93fab7f6d4e2&plugin=AMap.Autocomplete"></script>
    	<script type="text/javascript" src="http://webapi.amap.com/demos/js/liteToolbar.js"></script>
    		<script type="text/javascript">
			    var map = new AMap.Map('container',{
			    		 zoom: 14,
			            resizeEnable: true,
			           
			    });
			    
			    //为地图注册click事件获取鼠标点击出的经纬度坐标
	    		var clickEventListener = map.on('click', function(e) {
	       	 		document.getElementById("locLng").value = e.lnglat.getLng();
	       	 		document.getElementById("locLat").value = e.lnglat.getLat();
	    		});
			    
			    AMap.plugin('AMap.Geocoder',function(){
			        var geocoder = new AMap.Geocoder({
			            city: "010"//城市，默认：“全国”
			        });
			        var marker = new AMap.Marker({
			            map:map,
			            bubble:true
			        })
			        var addlocation = document.getElementById('addlocation');
			        var message = document.getElementById('message');
			        map.on('click',function(e){
			            marker.setPosition(e.lnglat);
			            geocoder.getAddress(e.lnglat,function(status,result){
			              if(status=='complete'){
			                 addlocation.value = result.regeocode.formattedAddress
			                 message.innerHTML = ''
			              }else{
			                 message.innerHTML = '无法获取地址'
			              }
			            })
			        })
			        
			        location.onchange = function(e){
			            var address = location.value;
			            geocoder.getLocation(address,function(status,result){
			              if(status=='complete'&&result.geocodes.length){
			                marker.setPosition(result.geocodes[0].location);
			                map.setCenter(marker.getPosition())
			                message.innerHTML = ''
			              }else{
			                message.innerHTML = '无法获取位置'
			              }
			            })
			        }
					
				  var auto = new AMap.Autocomplete({
        		  	input: "addlocation"
    			  });
    			  AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    			  function select(e) {
        			if (e.poi && e.poi.location) {
            			map.setZoom(15);
            			map.setCenter(e.poi.location);
            			
            			$("#locLat").val(e.poi.location.lat);
            		    $("#locLng").val(e.poi.location.lng);
            		
            		 //添加marker
         			marker = new AMap.Marker({
            			map: map,
            			position: e.poi.location
        			});
        			map.setCenter(marker.getPosition());  
        			
        			
        			}
    			  }
					
			    });
   			</script>
   
	
    <script>
    
    	//修改密码-传入orgId
    	 $(".btn-edit-loippi-pwd").click(function(){
			var id=$(this).attr('data-id');
			var account=$(this).attr('data-account');
			
			$("#orgId").val(id);
			$("#account1").val(account);
		
    	});
    	
    	
    	//修改密码校验
    		$("#inputModalForm").validate({
                rules: {
                   filter_password: {
	                    minlength: 6,
	                    required: true
	                },
                   filter_repassword: {
	                    equalTo:'#filter_password',
	                    minlength: 6,
	                    required: true
	                }
	              },
    			  messages: {
                  filter_password:{
                       equalTo:"两次密码输入不一致"
                   }      
				}
    		});
    	
    
    	$("#inputForm").validate({
    		  submitHandler:function(form){
    		  var b=1;
    		  var oldaccount=$("#oldaccount").val();
    		  var account=$("#account").val();
    		  var password3=$("#password3").val();
    		  if(oldaccount!=account){
    		     b=2;
    		     if(password3==''){
    		        art.warn('请填写密码！');
    		        return;
    		     }
    		  };
    	
    	if(     b==2){
    	 bootbox.confirm("账号与旧账号不一样，旧账户数据就被删除，你确定修改吗", function(result) {
		 	if(result){
            form.submit();
            }
          
        }); 
        }else{
          form.submit();
        }
           
          
        },
    	
    	
                rules: {
                  account: {
	                    minlength: 6,
	                    required: true
	                },
                  
                   password1: {
	                        equalTo:'#password3'
	                },
	                  status: {
	                 		required: true
						 },
						 
					  name: {
                 			required: true,
                 			maxlength: 100
					 },
					   telPhone: {
                 			required: true,
                 			maxlength: 20
					 },
					 
					  serviceName: {
                 			required: true,
                 			maxlength: 20
					 },
					 
					  servicePhone: {
                 			required: true,
                 			maxlength: 20
					 },
					 
					  account: {
                 			required: true,
                 			maxlength: 20
					 },
					 
					  fee: {
                 			required: true,
                 			maxlength: 20
					 },
					 
					  location: {
                 			required: true,
                 			maxlength: 100
					 }
                },
                messages: {
                 password1:{
                        equalTo:"两次密码输入不一致"
                    }      
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
    
            
				 //省选择
	    $("#province_id").change(function () {
	        var id = $(this).val();
	        var area = $("#city_id");
	        area.empty();
	        area.append("<option hassubinfo=\"true\" value=\"\">请选择市</option>");
	        $("#area_id").empty();
	        $("#area_id").append("<option hassubinfo=\"true\" value=\"\">请选择县区</option>");
	        if (id == null || id == '') {
	            return;
	        }
	         $.ajax( {
				    url:'${base}/admin/orgnization/city.jhtml',// 跳转到 action  
				    data:{
				       id : id,  
				    },  
				    type:'post',  
				    cache:false,  
				    dataType:'json',  
				    success:function(result) {
				    	var city=result.data;
				     	if (result == null || result == '') {
	                	return;
	            		}
		            	for (var i = 0; i < city.length; i++) {
		                	area.append("<option hassubinfo=\"true\" value=\"" + city[i].id + "\">" + city[i].name + "</option>");
		            	}
		            	$(".chosen-select").trigger("chosen:updated")
		            	},
		           		error : function() {  
				     
				     	}   
			});
		})
	    
	
	    //城市选择
	    $("#city_id").change(function () {
	        var id = $(this).val();
	        var area = $("#area_id");
	        area.empty();
	        area.append("<option hassubinfo=\"true\" value=\"\">请选择县区</option>");
	        if (id == null || id == '') {
	            return;
	        }
	        $.ajax( {
				    url:'${base}/admin/orgnization/area.jhtml',// 跳转到 action  
				    data:{
				       id : id,  
				    },  
				    type:'post',  
				    cache:false,  
				    dataType:'json',  
				    success:function(result) {
				    	var area2=result.data;
				     	if (result == null || result == '') {
	                	return;
	            		}
		            	for (var i = 0; i < area2.length; i++) {
		                	area.append("<option hassubinfo=\"true\" value=\"" + area2[i].id + "\">" + area2[i].name + "</option>");
		            	}
		            	$(".chosen-select").trigger("chosen:updated")
		            	},
		           		error : function() {  
				     
				     	}   
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
            	$("#uploadImage").val($("#PREFIX").val()+data.key);
            	$("#defaultImage").attr('src',$("#PREFIX").val()+data.key);
            	
            } 
			
		});
            
				
        });
        
        
    </script>
</body>
</html>
