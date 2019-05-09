
<!DOCTYPE html>
<html>
<head>
﻿﻿﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
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
                  		<input type="hidden" value="${teacher.id}" id="uid" name="uid"/>
                       <input type="hidden" value="${teacher.teacherType}" id="teacherType1" name="teacherType1"/>
                       	  <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号<span style="color:#F00">(*)</span></label>
		                                <div class="col-sm-9">
		                                    <input type="text" name="account" class="form-control" value="${(teacher.account)!''}" maxlength="200" readonly />
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
	       								 		<option  value="男" [#if teacher.sex == '男'] selected="selected"[/#if]>男</option>
	      								 		<option  value="女" [#if teacher.sex == '女'] selected="selected"[/#if]>女</option>
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
	                                <!--
	                                	[#if teacher.teacherType == '1']
	                                	<input type="text" name="teacherType" class="form-control" value="全职教师" maxlength="200" readonly/>
	                                	[/#if]
	                                	[#if teacher.teacherType == '2']
	                                	<input type="text" name="teacherType" class="form-control" value="兼职教师" maxlength="200" readonly/>
	                                	[/#if]
	                                -->
	                                    <select class="form-control m-b " name="teacherType" id="teacherType">
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
						    						<input type="text" name="mark1" class="form-control" value="${(teacher.mark1)!''}" maxlength="8" />
		                                		</div>
	                       				</div>
	                       				
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签二</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark2" value="${(teacher.mark2)!''}" class="form-control" maxlength="8" />
		                                		</div>
	                       				</div>
	                       				
	                       			</div>
	                        	</div>
	                        	
	                        	<div class="form-group">
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">标签三</label>
		                                		<div class="col-sm-9">
						    						<input type="text" name="mark3"  value="${(teacher.mark3)!0}" class="form-control" maxlength="8" />
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
			                                    [@shiro.hasPermission name ='admin:orgteacher:edit']
			                                    	<th>操作</th>
			                                    [/@shiro.hasPermission]
			                                </tr>
			                            </thead>
			                            <tbody>
                							[#list teacherExperienceList as teacherExperience]
				                            	<tr>
				                            		
				                            		<td>${teacherExperience_index+1}</td>
				                            		<td>${(teacherExperience.start?string('yyyy-MM-dd'))!''}</td>
				                            		<td>${(teacherExperience.end?string('yyyy-MM-dd'))!''}</td>
				                            		<td>${(teacherExperience.content)!''}</td>
				                            		[@shiro.hasPermission name ='admin:orgteacher:edit']
					                            		<td>
				                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${teacherExperience.id}" href="${base}/admin/org_teacher/editTeacherExperience/${teacherExperience.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                            			 
					                            			<a class="btn btn-danger btn-xs "id="deleteTeacherExperience" data-id="${teacherExperience.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
					                            		</td>
				                            		[/@shiro.hasPermission]
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
	                                     <input type="hidden" name="tSalaryId" value="${(tSalary.id)!''}" class="form-control" />
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
																<td><input type="text" name="basicSalary" value="${(tSalary.basicSalary)!''}" class="form-control" /></td>
																<td><input type="text" name="courseSalary" value="${(tSalary.courseSalary)!''}" readonly="readonly" class="form-control" /></td>
																<td><input type="text" name="performanceSalary" value="${(tSalary.performanceSalary)!''}" readonly="readonly" class="form-control" /></td>
																<td><input type="text" name="allTsutomu" value="${(tSalary.allTsutomu)!''}" class="form-control" /></td>
																<td><input type="text" name="bonus" value="${(tSalary.bonus)!''}" class="form-control" /></td>
																<td><input type="text" name="pushMoney" value="${(tSalary.pushMoney)!''}" class="form-control" /></td>
																<td><input type="text" name="reimburse" value="${(tSalary.reimburse)!''}" class="form-control" /></td>
																<td><input type="text" name="edit1" value="${(tSalary.edit1)!''}" class="form-control" /></td>
															   <td><input type="text" name="edit2" value="${(tSalary.edit2)!''}" class="form-control" /></td>
																<td><input type="text" name="edit3" value="${(tSalary.edit3)!''}" class="form-control" /></td>
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
			                                    <th>范围(小)</th>
			                                    <th>范围(大)</th>
			                                    <th>金额（元）</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                							[#list tcommentScopes as tcommentScope]
				                            	<tr>
				                            		<td>${(tcommentScope.mix)!''}</td>
				                            		<td>${(tcommentScope.max)!''}</td>
				                            		<td>${(tcommentScope.account)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tcommentScope.id}" href="javascript:editValue('1','${tcommentScope.mix}','${tcommentScope.max}','${tcommentScope.account}','${tcommentScope.id}')"><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs  " data-id="${tcommentScope.id}"   href="javascript:deleteValue('1',${tcommentScope.id})"><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
	                    	 		<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="javascript:showUserComment('2')"><i class="fa fa-plus"></i> 新增续班率</a>
	                    		</div>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	<br/><br/>
			                       <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>范围(小)</th>
			                                    <th>范围(大)</th>
			                                    <th>金额（元）</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                							[#list tcontinueClasss as tcontinueClass]
				                            	<tr>
				                            		
				                            		<td>${(tcontinueClass.mix)!''}</td>
				                            		<td>${(tcontinueClass.max)!''}</td>
				                            		<td>${(tcontinueClass.account)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tcontinueClass.id}" href="javascript:editValue('2','${tcontinueClass.mix}','${tcontinueClass.max}','${tcontinueClass.account}','${tcontinueClass.id}')"><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs  "  href="javascript:deleteValue('2',${tcontinueClass.id})" data-id="${tcontinueClass.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
	                    	 		<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="javascript:showUserComment('3')"><i class="fa fa-plus"></i> 新增点评率</a>
	                    		</div>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	<br/><br/>
			                       <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>范围(小)</th>
			                                    <th>范围(大)</th>
			                                    <th>金额（元）</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
                										[#list tremarkOns as tremarkOn]
				                            	<tr>
				                            		<td>${(tremarkOn.mix)!''}</td>
				                            		<td>${(tremarkOn.max)!''}</td>
				                            		<td>${(tremarkOn.account)!''}</td>
				                            		<td>
			                            			 	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tremarkOn.id}"  href="javascript:editValue('3',${tremarkOn.mix},${tremarkOn.max},${tremarkOn.account},${tremarkOn.id})"><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                            			 
				                            			<a class="btn btn-danger btn-xs"  href="javascript:deleteValue('3',${tremarkOn.id})" data-id="${tremarkOn.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
	                                        <input type="hidden" value="${(tleaveExplain.id)!''}" name="tleaveExplainId" />
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
																<td><input type="text" name="day1" value="${(tleaveExplain.day1)!''}" class="form-control" /></td>
																<td><input type="text" name="day2" value="${(tleaveExplain.day2)!''}" class="form-control" /></td>
																<td><input type="text" name="day3" value="${(tleaveExplain.day3)!''}" class="form-control" /></td>
																<td><input type="text" name="day4" value="${(tleaveExplain.day4)!''}" class="form-control" /></td>
																<td><input type="text" name="day5" value="${(tleaveExplain.day5)!''}" class="form-control" /></td>
																<td><input type="text" name="day6" value="${(tleaveExplain.day6)!''}" class="form-control" /></td>
																<td><input type="text" name="day7" value="${(tleaveExplain.day7)!''}" class="form-control" /></td>
															    <td><input type="text" name="day8" value="${(tleaveExplain.day8)!''}" class="form-control" /></td>
																<td><input type="text" name="day9" value="${(tleaveExplain.day9)!''}" class="form-control" /></td>
															</tr>
															<tr>
																<td>超过天数扣除金额</td>
																<td><input type="text" name="deduct1" value="${(tleaveExplain.deduct1)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct2" value="${(tleaveExplain.deduct2)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct3" value="${(tleaveExplain.deduct3)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct4" value="${(tleaveExplain.deduct4)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct5" value="${(tleaveExplain.deduct5)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct6" value="${(tleaveExplain.deduct6)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct7" value="${(tleaveExplain.deduct7)!''}" class="form-control" /></td>
															    <td><input type="text" name="deduct8" value="${(tleaveExplain.deduct8)!''}" class="form-control" /></td>
																<td><input type="text" name="deduct9"  value="${(tleaveExplain.deduct9)!''}" class="form-control" /></td>
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
														     <input type="hidden" value="${(thourExpense.id)!''}" name="thourExpenseId" />
														<tbody>
															<tr>
																<td  id="teacher1"  style="text-align: left"><input type="radio"  name="optionsRadiosinline" id="optionsRadios1" value="1">课时<input type="text" name="hour" value="${(thourExpense.hour)!''}" />节，多上一节，奖励<input type="text" name="award"  value="${(thourExpense.award)!''}"/>元，少上一节，扣<input type="text" name="deduct"  value="${(thourExpense.deduct)!''}"/>元</td>
															</tr>
																<tr>
																<td    id="teacher2"  style=" text-align: left;display:none"><input type="radio"   name="optionsRadiosinline" id="optionsRadios2" value="2">上一节课<input type="text" name="oneClass"  value="${(thourExpense.oneClass)!''}" />元 </td>
															</tr>
															<tr>
																<td  id="teacher3"  style=" text-align: left;display:none"><input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="3">按照课程金额的<input type="text" name="moneyRate"  value="${(thourExpense.moneyRate)!''}"/>%</td>
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
	                                      <input type="hidden" value="${(tattendaanceCount.id)!''}" name="tattendaanceCountId" />
														<tbody>
															<tr>
																<td style=" text-align: left">提前<input type="text" value="${(tattendaanceCount.advanceMinute)!''}" name="advanceMinute" />分钟打卡</td>
															</tr>
																<tr>
																<td style=" text-align: left">每迟到<input type="text"  value="${(tattendaanceCount.lateMinute1)!''}" name="lateMinute1" />分钟，扣<input type="text" name="deducta"  value="${(tattendaanceCount.deduct1)!''}" />元，封顶<input type="text" name="maxDeduct1"  value="${(tattendaanceCount.maxDeduct1)!''}"/>元</td>
															</tr>
															<tr>
																<td style=" text-align: left">迟到<input type="text" value="${(tattendaanceCount.lateMinute2)!''}" name="lateMinute2" />分钟当旷工，扣<input type="text" value="${(tattendaanceCount.deduct2)!''}" name="deductb" />元</td>
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
                   <input type="hidden" id="editType" name="editType">
                    <input type="hidden" id="id" >
                        <div class="dataTables_wrapper form-inline ">
                                  <div class="row">
	                                <div class="col-sm-12">
	                                		 <table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td style="width:20%">范围:</td>
																<td style="text-align:left"><input type="text" id="mix" name="mix"  />~<input type="text" id="max" name="max" /></td>
															</tr>
															<tr>
																<td style="width:20%" >金额:</td>
																<td style="text-align:left"><input type="text" id="account" name="account" /></td>
															</tr>
															</tbody>
											</table>					
	                                </div>

                       			  </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="addvalue()">确定</button>
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
			$("#deleteTeacherExperience").click(function(){
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
			
			         var typeselect= $("#teacherType1").val(); 
			            if(typeselect==1){
               $("#teacher1").show();
                 $("#teacher2").hide();
                 $("#teacher3").hide();
                $("#optionsRadios1").attr("checked","checked");
               }else{
                  $("#teacher1").hide();
                  $("#optionsRadios2").attr("checked","checked");
                 $("#teacher2").show();
                 $("#teacher3").show();
              
               } 
			        
					});
			     
  function editValue(type,mix,max,account,id){
          $("#editType").val("2");
		   var uid=$("#uid").val();
		  $("#id").val(id);
          $("#performanceType").val(type);
          $("#mix").val(mix);
          $("#max").val(max);
          $("#account").val(account);
		    $('#backReason').modal({
                    keyboard: true
                 });
		
		  }
			
			       function showUserComment(performanceType){
			          $("#editType").val("1");
                    $("#performanceType").val(performanceType);
                    $('#backReason').modal({
                    keyboard: true
                 });
       }         
      
        
        
          function addvalue(){
          var editType=$("#editType").val();
          var id=$("#id").val();
          var uid=$("#uid").val();
          var performanceType=$("#performanceType").val();
          var mix=$("#mix").val();
          var max=$("#max").val();
           var account=$("#account").val();
           if(isNaN(mix)||mix<0){
             alert("请输入大于0的数字");
             return;
           }
                 if(isNaN(max)||max<0){
             alert("请输入大于0的数字");
             return;
           }
                 if(isNaN(account)||account<0){
             alert("请输入大于0的数字");
             return;
           }
           $.ajax({
                url: "${base}/admin/org_teacher/addvalue.jhtml",
                type: "GET",
                data: {performanceType:performanceType,account:account,max:max,mix:mix,id:id,editType:editType,uid:uid},
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
       
               function deleteValue(type,id){
           $.ajax({
                url: "${base}/admin/org_teacher/deleteValue.jhtml",
                type: "GET",
                data: {type:type,id:id},
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
    </script>
</body>
</html>
