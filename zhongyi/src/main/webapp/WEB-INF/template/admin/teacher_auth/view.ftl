<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 老师认证详情</title>
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
                    	 <h5>老师认证详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/teacher_auth/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${teacherAuth.id}" name="id"/>
                  		  <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><font color="orange" size="5px">基本资料</font></label>
	                                <div class="col-sm-9">
	                                </div>
                       			</div>
                     
							</div>
							</div>
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">帐号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${teacherAuth.account}</p>
	                                </div>
                       			</div>
                       				<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">类型</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">[#if teacherAuth.type==1]全职[#else]兼职[/#if]</p>
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
	                                	<p class="form-control-static">${teacherAuth.name}</p>
	                                </div>
                       			</div>
                       				<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">申请时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(teacherAuth.createTime?string('yyyy-MM-dd HH:mm'))!''}</p>
	                                </div>
                       			</div>
							</div>
							</div>
							<!--/row-->
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">性别</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${teacherAuth.sex}</p>
	                                </div>
                       			</div>
                       				<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">[#if teacherAuth.status==1]<font color="green">已同意</font>[#elseif teacherAuth.status==3]已拒绝[#elseif teacherAuth.status==4]已离职[#else]<font color="red">未审核</font>[/#if]</p>
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
	                                	<p class="form-control-static">	<img src="${teacherAuth.avatar}" style="width:100px;height:100px"></p>
	                                </div>
                       			</div>
                       			[#if teacherAuth.status==3]
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">拒绝理由</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${teacherAuth.reason}</p>
	                                </div>
                       			</div>
                       			[/#if]
							</div>
							</div>
							
							<div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><font color="orange" size="5px">证件资料</font></label>
	                                <div class="col-sm-9">
	                                </div>
                       			</div>
                     
							</div>
							</div>
							<!--/row-->
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">身份证</label>
	                                <div class="col-sm-9">
	                                	<table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td>
																[#if teacherAuth.idCard??]
																 [#list teacherAuth.idCard?split(",") as s]
																<img src="${s}"  style="width:100px;height:100px">
																[/#list]
																[/#if]
																</td>
															</tr>
														</tbody>
											</table>	
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">教师证</label>
	                                <div class="col-sm-9">
	                                	<table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td>
																[#if teacherAuth.teacherCard??]
																 [#list teacherAuth.teacherCard?split(",") as s]
																<img src="${s}" style="width:100px;height:100px">
																[/#list]
																[/#if]
																</td>
															</tr>
														</tbody>
											</table>	
	                                </div>
                       			</div>
							</div>
							</div>
				
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学历证</label>
	                                <div class="col-sm-9">
	                                <table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td>
																[#if teacherAuth.educationCard??]
																 [#list teacherAuth.educationCard?split(",") as s]
																<img src="${s}" style="width:100px;height:100px">
																[/#list]
																[/#if]
																</td>
															</tr>
														</tbody>
											</table>	
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">资历证</label>
	                                <div class="col-sm-9">
	                                              <table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td>
																[#if teacherAuth.qlCard??]
																 [#list teacherAuth.qlCard?split(",") as s]
																<img src="${s}" style="width:100px;height:100px">
																[/#list]
																[/#if]
																</td>
															</tr>
														</tbody>
											</table>	
	                                </div>
                       			</div>
							</div>
	</div>
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
