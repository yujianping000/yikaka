<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑教师</title>
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
                    	 <h5>编辑教师</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/org_teacher/update.jhtml" method="post">
                  		<input type="hidden" value="${teacher.id}" name="id"/>
                       
                       	  <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="account" class="form-control" value="${(teacher.account)!''}" maxlength="200" />
		                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">头像<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                   <table border="0">
											  <tr>
											  	<td>
											    	<div style="float: left;">
														<img style="height:150px;width: 150px;" class="img-circle" id="defaultAvatar" name="defaultAvatar" src="${(teacher.avatar)!''}" />
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
											<input type="hidden"  name="avatar" id="uploadAvatar" value="${(teacher.avatar)!''}" />
		                             </div>
                       			</div>
                       			
							</div>
                        </div>
                        
                        
							<!--/row-->
							
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">性别<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                    <select class="form-control m-b " name="sex">
	       								 		<option  value="1" [#if teacher.sex == '1'] selected="selected"[/#if]>男</option>
	      								 		<option  value="2" [#if teacher.sex == '2'] selected="selected"[/#if]>女</option>
		                                 	</select>
		                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <select class="form-control m-b " name="status">
       								 		<option  value="1" [#if teacher.status == '1'] selected="selected"[/#if]>启用</option>
      								 		<option  value="2" [#if teacher.status == '2'] selected="selected"[/#if]>禁用</option>
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
       								 		<option  value="1" [#if teacher.teacherType == '1'] selected="selected"[/#if]>全职教师</option>
      								 		<option  value="2" [#if teacher.teacherType == '2'] selected="selected"[/#if]>兼职教师</option>
	                                 	</select>
	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">出生日期</label>
	                                <div class="col-sm-9">
	                                    <div class="input-daterange input-group col-sm-12" id="datepicker">
					        				<input type="text" placeholder="年/月/日" name="birthday" id="birthday" value="${(teacher.birthday?string('yyyy-MM-dd'))!''}" class="input-sm form-control  ">
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
	                                    <input type="text" name="nickname" class="form-control" value="${(teacher.nickname)!''}" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">个人简介</label>
	                                <div class="col-sm-9">
	                                    <textarea name="description"  class="input-sm form-control ">${(teacher.description)!''}</textarea>
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
							
						
						<div class="ibox-content">
							<br/>
              				<br/>
              				
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>标签信息</h3>
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签一</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark1" class="form-control" value="${(teacher.mark1)!''}" maxlength="200" />
		                                		</div>
	                       				</div>
	                       				
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签二</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark2" value="${(teacher.mark2)!''}" class="form-control" maxlength="200" />
		                                		</div>
	                       				</div>
	                       				
	                       			</div>
	                        	</div>
	                        	
	                        	<div class="form-group">
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签三</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark3"  value="${(teacher.mark3)!''}" class="form-control" maxlength="200" />
		                                		</div>
	                       				</div>
	                       			</div>
	                        	</div>
	                        	
              			</div>
              			
              			<div class="ibox-content">
              				<br/>
              				<div class="form-group">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>任教经历</h3>
						 	 	<div class="ibox-tools">
	                    	 		<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/org_teacher/addTeacherExperience/${teacher.id}.jhtml"><i class="fa fa-plus"></i> 新增任教经历</a>
	                    		</div>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	<br/><br/>
			                       <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>序号</th>
			                                    <th>开始时间</th>
			                                    <th>结束时间</th>
			                                    <th style="width:19%">任教经历</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                							[#list teacherExperienceList as teacherExperience]
				                            	<tr>
				                            		
				                            		<td>${teacherExperience_index+1}</td>
				                            		<td>${(teacherExperience.start?string('yyyy-MM-dd'))!''}</td>
				                            		<td>${(teacherExperience.end?string('yyyy-MM-dd'))!''}</td>
				                            		<td>${(teacherExperience.content)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${teacherExperience.id}" href="${base}/admin/org_teacher/editTeacherExperience/${teacherExperience.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs  deleteTeacherExperience"id="deleteTeacherExperience" data-id="${teacherExperience.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
				                            		</td>
				                            	</tr>
			                            	[/#list]
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								</div>
							
							<!--/row-->
													
						<div class="ibox-content">
							<br/>
              				<br/>
              				
	              				 <div class="form-group">
	              				 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>薪资设置</h3>
	                       			<div class="row">
	                                      <table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td >底薪（元）</td>
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
              				<div class="form-group">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>用户评价</h3>
						 	 	<div class="ibox-tools">
	                    	 		<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="javascript:showUserComment('1')"><i class="fa fa-plus"></i> 新增用户评价数值</a>
	                    		</div>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	<br/><br/>
			                       <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>序号</th>
			                                    <th>范围(小)</th>
			                                    <th>范围(大)</th>
			                                    <th>金额（元）</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                							[#list tcommentScopes as tcommentScope]
				                            	<tr>
				                            		
				                            		<td>${tcommentScope_index+1}</td>
				                            		<td>${(tcommentScope.mix)!''}</td>
				                            		<td>${(tcommentScope.max)!''}</td>
				                            		<td>${(tcommentScope.account)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tcommentScope.id}" href="${base}/admin/org_teacher/editTeacherExperience/${tcommentScope.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs  deleteTeacherExperience"id="deleteTeacherExperience" data-id="${tcommentScope.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
				                            		</td>
				                            	</tr>
			                            	[/#list]
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								</div>
								
									<div class="ibox-content">
              				<br/>
              				<div class="form-group">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>续班率</h3>
						 	 	<div class="ibox-tools">
	                    	 		<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/org_teacher/addTeacherExperience/${teacher.id}.jhtml"><i class="fa fa-plus"></i> 新增续班率</a>
	                    		</div>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	<br/><br/>
			                       <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                       <th>序号</th>
			                                    <th>范围(小)</th>
			                                    <th>范围(大)</th>
			                                    <th>金额（元）</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                							[#list tcontinueClasss as tcontinueClass]
				                            	<tr>
				                            		
				                            		<td>${tcontinueClass_index+1}</td>
				                            		<td>${(tcontinueClass.mix)!''}</td>
				                            		<td>${(tcontinueClass.max)!''}</td>
				                            		<td>${(tcontinueClass.account)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tcontinueClass.id}" href="${base}/admin/org_teacher/editTeacherExperience/${tcontinueClass.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs  deleteTeacherExperience"id="deleteTeacherExperience" data-id="${tcontinueClass.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
				                            		</td>
				                            	</tr>
			                            	[/#list]
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								</div>
								
									<div class="ibox-content">
              				<br/>
              				<div class="form-group">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>点评率</h3>
						 	 	<div class="ibox-tools">
	                    	 		<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/org_teacher/addTeacherExperience/${teacher.id}.jhtml"><i class="fa fa-plus"></i> 新增点评率</a>
	                    		</div>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	<br/><br/>
			                       <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                     <th>序号</th>
			                                    <th>范围(小)</th>
			                                    <th>范围(大)</th>
			                                    <th>金额（元）</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                										[#list tremarkOns as tremarkOn]
				                            	<tr>
				                            		
				                            		<td>${tremarkOn_index+1}</td>
				                            		<td>${(tremarkOn.mix)!''}</td>
				                            		<td>${(tremarkOn.max)!''}</td>
				                            		<td>${(tremarkOn.account)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tremarkOn.id}" href="${base}/admin/org_teacher/editTeacherExperience/${tremarkOn.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs  deleteTeacherExperience"id="deleteTeacherExperience" data-id="${tremarkOn.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
				                            		</td>
				                            	</tr>
			                            	[/#list]
			                            </tbody>
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
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
															<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
															</tr>
															<tr>
																<td>超过天数扣除金额</td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
															<td><input type="text" name="dayNum" class="form-control" /></td>
																<td><input type="text" name="dayNum" class="form-control" /></td>
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
	                                      <table class="table table-striped table-bordered table-hover dataTables-example dataTable" style="border:0;width:50%" >
														<tbody>
															<tr>
																<td    style=" text-align: left"><input type="radio" name="optionsRadiosinline" id="optionsRadios4" value="option2">课时<input type="text" name="dayNum" />节，多上一节，奖励<input type="text" name="dayNum" />元，少上一节，扣<input type="text" name="dayNum"  />元</td>
															</tr>
																<tr>
																<td     style=" text-align: left"><input type="radio" name="optionsRadiosinline" id="optionsRadios4" value="option2">上一节课<input type="text" name="dayNum"  />元 </td>
															</tr>
															<tr>
																<td     style=" text-align: left"><input type="radio" name="optionsRadiosinline" id="optionsRadios4" value="option2">按照课程金额的<input type="text" name="dayNum"  />%</td>
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
																<td style=" text-align: left">提前<input type="text" name="dayNum" />分钟打卡</td>
															</tr>
																<tr>
																<td style=" text-align: left">每迟到<input type="text" name="dayNum" />分钟，扣<input type="text" name="dayNum" />元，封顶<input type="text" name="dayNum" />元</td>
															</tr>
															<tr>
																<td style=" text-align: left">迟到<input type="text" name="dayNum" />分钟当旷工，扣<input type="text" name="dayNum" />元</td>
															</tr>
													  <tbody>
										 </table>		
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
    
     <div class="modal inmodal" id="backReason" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">填写范围与金额</p>
            </div>
            <div class="modal-body">
               <input type="hidden" id="performanceType" name="performanceType">
                        <div class="dataTables_wrapper form-inline ">
                                  <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static">拒绝原因:</p><textarea rows="8" id="reasonaaa" name="reasonaaa" class="form-control" cols="40"></textarea>
	                                </div>

                       			  </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="affirmRefuse()">确定</button>
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
                rules: {
                	 account: {
                 		required: true,
                 		maxlength: 20
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
					 
                },
                messages: {
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
                	
                } 
				
			});
			
			
			 //删除教师任教经历
			$(".deleteTeacherExperience").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定删除?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/org_teacher/deleteTeacherExperience.jhtml",
							type: "POST",
							data: {id:$this.attr('data-id')},
							dataType: "json",
							cache: false,
							success: function(message) {
								window.setTimeout(function() {
									window.location.reload();
								}, 1000);
							}
						});
				 	}
			    }); 
			});
					});
			
			
			       function showUserComment(performanceType){
                    $("#performanceType").val(performanceType);
                    $('#backReason').modal({
                    keyboard: true
                 });
       }         
      
        
        
          function affirmUserComment(){
         var performanceType=$("#performanceType").val();
         var mix=$("#mix").val();
          var max=$("#max").val();
          var account=$("#account").val();
         $.ajax({
                url: "affirmRefuse.jhtml",
                type: "GET",
                data: {performanceType:performanceType,account:account,max:max,mix:mix},
                cache: false,
                async: false,
                success: function (message) {
                   		if (message.type == "success") {
							window.setTimeout(function() {
								window.location.reload();
							}, 1000);
						}
                }
            });
       }
    </script>
</body>
</html>
