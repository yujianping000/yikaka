<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 机构信息管理</title>
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
                    	 <h5>机构信息管理</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/common/index.jhtml'"><i class="fa fa-reply-all"></i> 返回首页</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/common/updateOrgManager.jhtml" method="get" >
                  		<input type="hidden" value="${orgnization.id}" name="id" id="orgId"/>
                      	<div class="ibox-content">
	                  	  	  <font color="orange" size="3">后台设置</font>
	                          <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">后台名称</label>
			                                <div class="col-sm-9">
			                                	<input type="text" name="backgroundName" id="backgroundName" class="form-control" value="${(orgnization.backgroundName)!''}" maxlength="200" />
			                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">后台logo</label>
		                                <div class="col-sm-9">
		                                	<table border="0">
											  <tr>
											  	<td>
											    	<div style="float: left;">
														<img style="height:150px;width: 150px;" class="img-circle" id="defaultImage1" name="defaultImage1" src="${(orgnization.backgroundLogo)!''}" />
													</div>
											 	<td>
											 </tr>
											  <tr>
											  	<td>
													<div style="float: left;margin-top:5px;margin-left:20px">
													<input type="file"  id="imageFile1"/>
													</div>
												</td>
											 </tr>
											</table>
											
											<input type="hidden"  name="backgroundLogo" id="uploadImage1" />
		                                </div>
	                       		  </div>
	                       		</div>
							</div>
                        </div>
                        
                        <div class="ibox-content">
			                 <font color="orange" size="3">签到设置</font>
			                  <div class="form-group">
			               		<div class="row">
			               			<div class="col-sm-6">
			               			 	<label class="col-sm-3 control-label">签到范围（公里）</label>
			                                <div class="col-sm-9">
			                                	<input type="text" name="distance" id="distance" class="form-control" value="${(orgnization.distance)!''}" maxlength="20" />
			                                </div>
			               			</div>
								</div>
			                </div>
						</div>
						
						<div class="ibox-content">
							 <font color="orange" size="3">机构信息</font>
			                  <div class="form-group">
			               		<div class="row">
			               			<div class="col-sm-6">
		                   			 	<label class="col-sm-3 control-label">机构名称</label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="name" id="name" class="form-control" value="${(orgnization.name)!''}" maxlength="100" />
		                                </div>
		                   			</div>
		                   			<div class="col-sm-6">
		                   			 	<label class="col-sm-3 control-label">机构电话</label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="telPhone" id="telPhone" class="form-control" value="${(orgnization.telPhone)!''}" maxlength="200" />
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
															<img style="height:150px;width: 150px;" class="img-circle" id="defaultImage2" name="defaultImage2" src="${(orgnization.image)!''}" />
														</div>
												 	<td>
												 </tr>
												  <tr>
												  	<td>
														<div style="float: left;margin-top:5px;margin-left:20px">
														<input type="file"  id="imageFile2"/>
														</div>
													</td>
												 </tr>
												</table>
												
												<input type="hidden"  name="image" id="uploadImage2" />
			                                </div>
		                       		  </div>
		                   			  
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
								</div>
		                    </div>
							
								<!--/row-->
							
	                          <div class="form-group">
	                       		<div class="row">
		                           <div class="col-sm-6">
	               			 	   		<label class="col-sm-3 control-label">机构地址</label>
	                                		<div class="col-sm-9">
	                                   		 <input type="text" id = "addlocation" name="location" onfocus = 'this.value=""' value="${(orgnization.location)!''}" class="form-control" maxlength="200" />
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
		                                    <input type="text" name="locLng" id="locLng" class="form-control" value="${(orgnization.locLng)!''}" maxlength="200"  readonly />
		                                </div>
	                       			</div>
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">纬度</label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="locLat" id="locLat" class="form-control" value="${(orgnization.locLat)!''}" maxlength="200"  readonly />
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
                   				<div class="col-sm-9">
                   						<div id="container" tabindex="0"></div>
                   						<div id = "message"></div>
                   					
                   				</div>
                   			</div>
                   		</div>
                   	  </div>
							
							
              				 <div class="form-group">
                       			<div class="row">
                       				<div class="col-sm-6">
                       			 		<br/><label class="col-sm-3 control-label">机构描述</label>
	                                		<div class="col-sm-9">
					    						<textarea id="editor" name="description" class="editor">${(orgnization.description)!''}</textarea>
	                                		</div>
                       				</div>
                       			</div>
                        	</div>
	              		
	              		
						
							<!--/row-->
						
                        <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                           <button class="btn btn-success updateOrgManager" type="button">保存内容</button>
	                           <!-- <a class="btn btn-xs btn-success updateOrgManager" id="updateOrgManager"></i>保存内容</a>-->
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
		<script src="${base}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- END Script -->
	
	 <input type="hidden" id="token1" value="${token1}"/>
	 <input type="hidden" id="PREFIX1" value="${PREFIX1}"/>
	 
	 <input type="hidden" id="token2" value="${token2}"/>
	<input type="hidden" id="PREFIX2" value="${PREFIX2}"/>
	
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
	<script>
		
	</script>
	
	
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
                             distance: {
							required: true,
							number:true
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

            
            //后台logo
              $('#imageFile1').uploadify({
				       
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
                            ('#imageFile1').uploadify('settings', 'queueSizeLimit') + "个文件！");  
                            break;  
                        case -110:  
                            alert("文件 [" + file.name + "] 大小超出系统限制的" + jQuery
                            ('#imageFile1').uploadify('settings', 'fileSizeLimit') + "大小！");  
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
	                    $("#imageFile1").uploadify(  "settings", "formData",  {'token' : $("#token1").val(), 'key' :timestamp+file.type});  
	                    //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
                } ,   
                onUploadSuccess: function(file,data, response) {
                data = jQuery.parseJSON(data);
                	$("#uploadImage1").val($("#PREFIX1").val()+data.key);
                	$("#defaultImage1").attr('src',$("#PREFIX1").val()+data.key);
                	
                } 
				
			});
			
			//封面图
			  $('#imageFile2').uploadify({
				       
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
                            ('#imageFile2').uploadify('settings', 'queueSizeLimit') + "个文件！");  
                            break;  
                        case -110:  
                            alert("文件 [" + file.name + "] 大小超出系统限制的" + jQuery
                            ('#imageFile2').uploadify('settings', 'fileSizeLimit') + "大小！");  
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
	                    $("#imageFile2").uploadify(  "settings", "formData",  {'token' : $("#token2").val(), 'key' :timestamp+file.type});  
	                    //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。  
                } ,   
                onUploadSuccess: function(file,data, response) {
                data = jQuery.parseJSON(data);
                	$("#uploadImage2").val($("#PREFIX2").val()+data.key);
                	$("#defaultImage2").attr('src',$("#PREFIX2").val()+data.key);
                	
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
    <script>
    	//保存
			$(".updateOrgManager").click(function(){
		    	 var id = $('#orgId').val();
		    	 var backgroundName = $("#backgroundName").val();
		    	 var backgroundLogo = $("#uploadImage1").val();
		    	 var distance = $("#distance").val();
		    	 var name = $("#name").val();
		    	 var telPhone = $("#telPhone").val();
		    	 var image = $("#uploadImage2").val();
		    	 var provinceId = $("#province_id").val();
		    	 var cityId = $("#city_id").val();
		    	 var areaId = $("#area_id").val();
		    	 var location = $("#addlocation").val();
		    	 var locLng = $("#locLng").val();
		    	 var locLat = $("#locLat").val();
		    	 var description = $("#editor").val();
		    	 
				 		$.ajax({
							url: "updateOrgManager.jhtml",
							type: "POST",
							data: {id:id,
									backgroundName:backgroundName,
									backgroundLogo:backgroundLogo,
									distance:distance,
									name:name,
									telPhone:telPhone,
									image:image,
									provinceId:provinceId,
									cityId:cityId,
									areaId:areaId,
									location:location,
									locLng:locLng,
									locLat:locLat,
									description:description,
								  },
							dataType: "json",
							cache: false,
							success: function(message) {
								window.setTimeout(function() {
									window.parent.location.reload();
								}, 1000);
							}
						});
		    	});
			
    </script>
</body>
</html>
