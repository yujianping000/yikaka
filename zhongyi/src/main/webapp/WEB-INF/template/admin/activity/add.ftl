<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增活动</title>
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
                    	 <h5>新增活动</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/activity/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="post">
                  		  		<input type="hidden" name="locLng" id="locLng"/>
                  		  		<input type="hidden" name="locLat" id="locLat"/>
                                 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动名称<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动名额</label>
	                                <div class="col-sm-9">
	                                  <input type="text" name="totalSum" class="form-control" maxlength="8" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">费用（元）<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="fee" class="form-control" maxlength="8" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">年龄范围<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="ageRank" class="form-control" maxlength="50" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">主办单位<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="company" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">封面图<span style="color:#F00">(*)</label>
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
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">报名时间<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入时间" name="startTime" id="startTime"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入时间" name="endTime" id="endTime" class="input-sm form-control  "> 
                            	</div>	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                             <select name="status" id="status" data-placeholder="状态..." class="chosen-select"  style="width:350px;" >
                                  <option   value="0">未发布</option>
                                  <option   value="1">报名中</option>
                                  <option   value="2">进行中</option> 
                                  <option   value="3">未开始</option>
                                  <option   value="4">已结束</option>  
						   </select>
                       			</div>
                       			</div>
							</div>
                        </div>
                        <!--/row-->
							
							
					   <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动地点<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                    <input type="text" id="addlocation"  name="location" class="form-control" maxlength="200" />
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
                        
                        
							<!--/row-->
						 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>活动介绍</h3>
						
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
		 <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=1d44abe3e9b2069616df93fab7f6d4e2&plugin=AMap.Autocomplete"></script>
    	<script type="text/javascript" src="http://webapi.amap.com/demos/js/liteToolbar.js"></script>
	<script src="${base}/resources/plugins/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/uploadify/uploadify.css">
		<style type="text/css">
	      #container{
	        height:         600px;
	        width:          630px;
	        margin-right:  auto;
	        margin-left:   auto;
	      }
	      
    </style>
    
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
                   name: {
						required: true
					},
                totalSum: {
						maxlength: 8,
						digits: true
					},
					
					
				     fee: {
						maxlength: 8,
						required: true,
						number: true
					},
					 ageRank: {
						required: true
					},	
					 company: {
						required: true
					},	
					 location: {
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
