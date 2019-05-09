<!DOCTYPE html>
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
	                	 	onclick="location.href='${base}/admin/org_teacher/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>            	
	              	<div class="ibox-content">
	              		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="get">
	                  	  <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
	                      <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">手机号码<span style="color:#F00">(*)</span></label>
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
														<img style="height:150px;width:150px;" class="img-circle" id="defaultAvatar" name="defaultAvatar" src="${(appConfig.data)!''}" />
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
	                                    <select class="form-control m-b " id="teacherType" name="teacherType">
	   								 		<option  value="1">全职教师</option>
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
	                   			 	<label class="col-sm-3 control-label">职教科目</label>
	                                <div class="col-sm-9">
	                                    <select class="form-control m-b " name="subject">
	   								 		<option  value="钢琴">钢琴</option>
	  								 		<option  value="声乐">声乐</option>
	  								 		<option  value="舞蹈">舞蹈</option>
	                                 	</select>
	                                </div>
	                   			</div>
	                   			
	                    	</div>
	                    </div>
	                    
						<div class="ibox-content">
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>标签信息</h3>
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
	          			
	          			
	          			<div class="ibox-content">
							<br/>
              				<br/>
              				
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>薪资设置</h3>
	                       			<div class="row">
	                                      <table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td >底薪（元）</th>
																<td>课时（元）</td>
																<td>绩效（元）</td>
														     	<td>全勤（元）</td>
																<td>奖金（元）</td>
																<td>提成（元）</td>
																<td>报销（元）</td>
															   <td><input type="text" placeholder="自定义1"  name="dayNum" class="form-control" /></td>
																<td><input type="text" placeholder="自定义2"  name="dayNum" class="form-control" /></td>
																<td><input type="text" placeholder="自定义3"  name="dayNum" class="form-control" /></td>
															</tr>
																<tr>
																<td><input type="text" name="basicSalary" class="form-control" /></td>
																<td><input type="text" name="courseSalary" readonly="readonly" class="form-control" /></td>
																<td><input type="text" name="performanceSalary" readonly="readonly" class="form-control" /></td>
																<td><input type="text" name="allTsutomu" class="form-control" /></td>
																<td><input type="text" name="bonus" class="form-control" /></td>
																<td><input type="text" name="pushMoney" class="form-control" /></td>
																<td><input type="text" name="reimburse" class="form-control" /></td>
																<td><input type="text" name="edit1" class="form-control" /></td>
															   <td><input type="text" name="edit2" class="form-control" /></td>
																<td><input type="text" name="edit3" class="form-control" /></td>
															</tr>
													  <tbody>
										 </table>		
	                       			</div>
	                        	</div>
              			</div>
                       		
                       		  <div class="ibox-content">
							<br/>
              				<br/>
              				
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>请假管理</h3>
	                       			<div class="row">
	                                      <table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td >请假类型</td>
																<td>事假</td>
																<td>病假</td>
														     	<td>调休</td>
																<td>年假</td>
																<td>婚假</td>
																<td>产假</td>
															    <td>陪产假</td>
																<td>丧假</td>
																<td>其他</td>
															</tr>
																<tr>
																<td>限制天数</td>
																<td><input type="text" name="day1" class="form-control" /></td>
																<td><input type="text" name="day2" class="form-control" /></td>
																<td><input type="text" name="day3" class="form-control" /></td>
																<td><input type="text" name="day4" class="form-control" /></td>
																<td><input type="text" name="day5" class="form-control" /></td>
																<td><input type="text" name="day6" class="form-control" /></td>
																<td><input type="text" name="day7" class="form-control" /></td>
															    <td><input type="text" name="day8" class="form-control" /></td>
																<td><input type="text" name="day9" class="form-control" /></td>
															</tr>
															<tr>
																<td>超过天数扣除金额</td>
																<td><input type="text" name="deduct1" class="form-control" /></td>
																<td><input type="text" name="deduct2" class="form-control" /></td>
																<td><input type="text" name="deduct3" class="form-control" /></td>
																<td><input type="text" name="deduct4" class="form-control" /></td>
																<td><input type="text" name="deduct5" class="form-control" /></td>
																<td><input type="text" name="deduct6" class="form-control" /></td>
																<td><input type="text" name="deduct7" class="form-control" /></td>
															    <td><input type="text" name="deduct8" class="form-control" /></td>
																<td><input type="text" name="deduct9" class="form-control" /></td>
															</tr>
													  <tbody>
										 </table>		
	                       			</div>
	                        	</div>
              			</div>
              			
              			         <div class="ibox-content">
							<br/>
              				<br/> 
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>课时费用</h3>
	                       			<div class="row">
	                                      <table class="table table-striped table-bordered table-hover dataTables-example dataTable" style="border:0;width:70%" >
														<tbody>
															<tr>
																<td  id="teacher1"  style="text-align: left"><input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="1">课时<input type="text" name="hour" />节，多上一节，奖励<input type="text" name="award" />元，少上一节，扣<input type="text" name="deduct"  />元</td>
															</tr>
																<tr>
																<td    id="teacher2"  style=" text-align: left;display:none"><input type="radio"   name="optionsRadiosinline" id="optionsRadios2" value="2">上一节课<input type="text" name="oneClass"  />元 </td>
															</tr>
															<tr>
																<td  id="teacher3"  style=" text-align: left;display:none"><input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="3">按照课程金额的<input type="text" name="moneyRate"  />%</td>
															</tr>
													  <tbody>
										 </table>		
	                       			</div>
              			</div>
              			</div>
              			
              			      <div class="ibox-content">
							<br/>
              				<br/> 
              				
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>考勤计算</h3>
	                       			<div class="row">
	                                      <table class="table table-striped table-bordered table-hover dataTables-example dataTable" style="border:0;width:50%" >
														<tbody>
															<tr>
																<td style=" text-align: left">提前<input type="text" name="advanceMinute" />分钟打卡</td>
															</tr>
																<tr>
																<td style=" text-align: left">每迟到<input type="text" name="lateMinute1" />分钟，扣<input type="text" name="deducta" />元，封顶<input type="text" name="maxDeduct1" />元</td>
															</tr>
															<tr>
																<td style=" text-align: left">迟到<input type="text" name="lateMinute2" />分钟当旷工，扣<input type="text" name="deductb" />元</td>
															</tr>
													  <tbody>
										 </table>		
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
	
		jQuery.validator.addMethod("isPhone", function(value, element) {
          var length = value.length;
          var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
          return this.optional(element) || (length == 11 && mobile.test(value));
         }, "请填写正确的手机号码");
	
		$("#inputForm").validate({
 
                rules: {
                	 account: {
                 		minlength:11,
	                    required: true,
	                    isPhone:true
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
					 
						    basicSalary: {
                 		number:true,
	                    min:0
					 },
					 
					   courseSalary: {
                 		number:true,
	                    min:0
					 },
					    performanceSalary: {
                 		number:true,
	                    min:0
					 },
					    allTsutomu: {
                 		number:true,
	                    min:0
					 },
					    bonus: {
                 		number:true,
	                    min:0
					 },
					    pushMoney: {
                 		number:true,
	                    min:0
					 },
					    reimburse: {
                 		number:true,
	                    min:0
					 },
					    edit1: {
                 		number:true,
	                    min:0
					 },
					    edit2: {
                 		number:true,
	                    min:0
					 },
					  edit3: {
                 		number:true,
	                    min:0
					 },
					 
					   day1: {
                 		number:true,
	                    min:0
					 },
					 
					   day2: {
                 		number:true,
	                    min:0
					 },
					 
					   day3: {
                 		number:true,
	                    min:0
					 },
					 
					   day4: {
                 		number:true,
	                    min:0
					 },
					 
					   day5: {
                 		number:true,
	                    min:0
					 },
					 
					   day6: {
                 		number:true,
	                    min:0
					 },
					 
					   day7: {
                 		number:true,
	                    min:0
					 },
					 
					   day8: {
                 		number:true,
	                    min:0
					 },
					 
					   day9: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct1: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct2: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct3: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct4: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct5: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct6: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct7: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct8: {
                 		number:true,
	                    min:0
					 },
					 
					   deduct9: {
                 		number:true,
	                    min:0
					 },
					 
					  hour: {
                 		number:true,
	                    min:0
					 },
					 
					  award: {
                 		number:true,
	                    min:0
					 },
					 
					  deduct: {
                 		number:true,
	                    min:0
					 },
					 
					  oneClass: {
                 		number:true,
	                    min:0
					 },
					 
					  moneyRate: {
                 		number:true,
	                    min:0
					 },
					 
					   advanceMinute: {
                 		number:true,
	                    min:0
					 },
					 
					   lateMinute1: {
                 		number:true,
	                    min:0
					 },
					 
					   deducta: {
                 		number:true,
	                    min:0
					 },
					 
					   maxDeduct1: {
                 		number:true,
	                    min:0
					 },
					 
					   lateMinute2: {
                 		number:true,
	                    min:0
					 },
					 
					   deductb: {
                 		number:true,
	                    min:0
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
	    	     $("#teacher2").hide();
                 $("#teacher3").hide();
                $("#optionsRadios1").attr("checked","checked");
		
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
	            	
	            } 
	            
	            
	               
				
			});
	        
	           $("#teacherType").change(function () {  
               var type= $("#teacherType  option:selected").val();  
               if(type==1){
              
                $("#optionsRadios1").attr("checked","checked");
               $("#teacher1").show();
                 $("#teacher2").hide();
                 $("#teacher3").hide(); 
               }else{
                  $("#teacher1").hide();
                  $("#optionsRadios2").attr("checked","checked");
                 $("#teacher2").show();
                 $("#teacher3").show();
              
               } 
              });  
	    });
	</script>
	</body>
	</html>
