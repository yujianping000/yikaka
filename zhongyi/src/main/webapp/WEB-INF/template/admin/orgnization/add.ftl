<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增机构</title>
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
                    	 <h5>添加机构</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/orgnization/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="get" >
                      		
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
                       			 	<label class="col-sm-3 control-label">密码<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="password" name="password1" id="password1" class="form-control" maxlength="20" />
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
	                                    <input type="password" name="password2" id="password2" class="form-control" maxlength="20" />
	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                 <select class="form-control m-b " name="status">
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
	                   			 	<label class="col-sm-3 control-label">机构名称<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="100" />
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">机构电话<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="telPhone" class="form-control" maxlength="200" />
	                                </div>
	                   			</div>
							</div>
	                    </div>
                        
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系人<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="serviceName" class="form-control" maxlength="20" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系电话<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="servicePhone" class="form-control" maxlength="20" />
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
													<img style="height:150px;width: 150px;" class="img-circle" id="defaultImage" name="defaultImage" src="${(appConfig.data)!''}" />
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
	                                    <input type="text" name="fee" class="form-control" maxlength="20" />
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
                                            <option hassubinfo="true" value="${province.id}">${province.name}</option>
                                            [/#list]
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="cityId" id="city_id" data-placeholder="市..."
                                                class="chosen-select" style="width:350px;">
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <select name="areaId" id="area_id" data-placeholder="县区..."
                                                class="chosen-select" style="width:350px;">
                                        </select>
                                    </div>
					    
                       			</div>
	                             
	                           <div class="col-sm-6">
               			 	   		<label class="col-sm-3 control-label">机构地址<span style="color:#F00">(*)</span></label>
                                		<div class="col-sm-9">
                                   		 	<input type="text" id = "addlocation" name="location" onfocus = 'this.value=""' class="form-control" maxlength="200" />
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
	                                    <input type="text" name="locLng" id="locLng" class="form-control" maxlength="200"  readonly />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">纬度</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="locLat" id="locLat" class="form-control" maxlength="200"  readonly />
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
						    						<textarea id="editor" name="description" class="editor"></textarea>
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
	
	<style type="text/css">
	      #container{
	        height:         600px;
	        width:         630px;
	        margin-right:  auto;
	        margin-left:   auto;
	      }
    </style>
	
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
		        
		        addlocation.onchange = function(e){
		            var address = addlocation.value;
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
    
            	$("#inputForm").validate({
                rules: {
                  account: {
	                    minlength: 6,
	                    required: true
	                },
                   password1: {
	                    minlength: 6,
	                    required: true
	                },
                   password2: {
	                        equalTo:'#password1',
	                         minlength: 6,
	                    required: true
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
			
			
        });
    </script>
</body>
</html>
