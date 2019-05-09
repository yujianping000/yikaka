<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 教师详情</title>
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
                    	 <h5>查看教师</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>  
                              	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${teacher.id}" name="id"/>
                        	
                        	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本资料</h3>
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">账号</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static"></p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">用户评分</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(teacher.userEvaluation)!''}</p>
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">姓名</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(teacher.nickname)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">机构评分</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(teacher.orgEvaluation)!''}</p>
		                                </div>
	                       			</div>
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">出生日期</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(teacher.birthday?string('yyyy-MM-dd'))!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">性别</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(teacher.sex)!''}</p>
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">头像</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">
		                                		<img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(teacher.avatar)!''}">
		                                	</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">个人简介</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(teacher.description)!''}</p>
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">状态</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">
		                                		[#if teacher.status = 1]
						                        		<a class="btn btn-primary btn-xs">启用</a>
						                        [/#if] 
						                        [#if teacher.status = 2]
						                        		<a class="btn btn-danger btn-xs">禁用</a>
						                        [/#if] 
		                                	</p>
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							
							<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>任教经历</h3>
							<div class="ibox-content">
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th></th>
			                                    <th>开始时间</th>
			                                    <th>结束时间</th>
			                                    <th>任教经历</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                            [#list teacherExperenceList as teacherExperence]
									        	 <tr>
							                        <td>${(teacherExperence.id)!''}</td>
						                       		<td>${(teacherExperence.start?string('yyyy-MM-dd'))!'-'}</td>
							                        <td>${(teacherExperence.end?string('yyyy-MM-dd'))!'-'}</td>
							                        <td>${(teacherExperence.content)!'-'}</td>
							                    </tr>
							            [/#list]        
			                            </tbody>
			                        </table>
			                       </div>
								</div>
							
							
								<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>用户评价</h3>
									<div class="ibox-tools">
										<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/org_teacher_talent/userRate/${teacher.id}.jhtml"><i class="fa fa-plus"></i>更多用户评价</a>
									</div>
							<div class="ibox-content">
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                             <thead>
			                                <tr>
			                                    <th>姓名</th>
			                                    <th>头像</th>
			                                    <th>评价分数</th>
			                                    <th>评价标签</th>
			                                	<th style="width:19%">评价内容</th>
			                                    <th>评价时间</th>
			                                </tr>
	                            		</thead>
			                            <tbody>
		                    	 	[#list CourseTimeCommentList as courseTimeComment] 
							        	 <tr>
					                        <td>${(courseTimeComment.name)!''}</td>
					                        <td> <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(courseTimeComment.avatar)!''}"> </td>
					                        <td>
				                        		[#list 1..courseTimeComment.favStar as favStar ]
					                        		<span style="font-size:20px; color:#F00">★</span>
					                        	[/#list]	
					                        </td>
				                         	<td>${(courseTimeComment.mark)!''}</td>
				                         	<td>${(courseTimeComment.content)!''}</td>
					                        <td>${(courseTimeComment.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                    </tr>
							        [/#list] 
		                        </tbody>
			                        </table>
			                       </div>
								</div>
							
							
							<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>机构评价</h3>
								<div class="ibox-tools">
									<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/org_teacher_talent/orgnizationRate/${teacher.id}.jhtml"><i class="fa fa-plus"></i>更多机构评价</a>
								</div>
							<div class="ibox-content">
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                             <thead>
			                                <tr>
			                                    <th>姓名</th>
			                                    <th>头像</th>
			                                    <th>评价分数</th>
			                                    <th>评价标签</th>
			                                	<th style="width:19%">评价内容</th>
			                                    <th>评价时间</th>
			                                </tr>
	                            		</thead>
			                            <tbody>
	                    	 		[#list teacherCommentList as teacherComment] 
							        	 <tr>
					                        <td>${(teacherComment.orgName)!''}</td>
				                         	<td> <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(teacherComment.avatar)!''}"> </td>
				                         	<td>
					                        	[#if teacherComment.status = 1]
				                        		[#list 1..teacherComment.favStar as favStar ]
					                        		<span style="font-size:20px; color:#F00">★</span>
					                        	[/#list]	
					                        	[/#if]	
					                        	[#if teacherComment.status = 2]-[/#if]
					                        </td>
					                        <td>${(teacherComment.mark)!''}</td>
					                        <td>${(teacherComment.content)!''}</td>
					                        <td>${(teacherComment.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                    </tr>
							        [/#list] 
		                        </tbody>
			                        </table>
			                       </div>
								</div>
							
							
							<!--/row-->
							<div class="row">
                       		
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
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
</body>
</html>
