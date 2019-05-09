
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 教师列表</title>
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
                	<form id="listForm" action="${base}/admin/orgnization/teacherlist/${orgnization.id}.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>教师列表 </h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/orgnization/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>教师列表</h3>
                    	<div class="row">
					        	<p><h3>&nbsp;&nbsp;&nbsp;<font color="orange" size="3">教师总数：	${(orgnization.teacherCount)!''}</font></h3><p>
                        </div>
                    	
                    	<div class="row">
					        <div class="col-sm-2 m-b-xs">
					        	<input type="text" placeholder="请输入账号" name="filter_account" value="${(paramter.filter_account)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-2 m-b-xs">
					        	<input type="text" placeholder="请输入姓名" name="filter_nickname" value="${(paramter.filter_nickname)!''}" class="input-sm form-control">
					        </div>
					        
				        	<div class="col-sm-2 m-b-xs">
	                           <select class="form-control m-b" name="filter_teacherType" >
	                                <option value="">类型</option>
	                    			<option value="1" [#if filter_teacherType == '1'] selected="selected"[/#if]>全职教师</option>
	                            	<option value="2" [#if filter_teacherType == '2'] selected="selected"[/#if]>兼职教师</option>
	                           </select>
                        	</div>
                        	
	                    	<div class="col-sm-2 m-b-xs">
	                           <select class="form-control m-b" name="filter_status">
	                                <option value="">状态</option>
	                    			<option value="1" [#if filter_status == '1'] selected="selected"[/#if]>启用</option>
	                            	<option value="2" [#if filter_status == '2'] selected="selected"[/#if]>禁用</option>
	                           </select>
	                        </div>
                          
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        
						   <div class="dataTables_wrapper form-inline">
						    <style>
								th,td {text-align:center}
							</style>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>头像</th>
                                        <th>性别</th>
                                        <th>类型</th>
                                        <th>加入时间</th>
                                        <th>用户评价</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as teacher] 
							        	 <tr>
					                        <td>${(teacher_index+1)!''}</td>
					                        <td>${(teacher.account)!''}</td>
					                        <td>${(teacher.nickname)!''}</td>
					                        <td>
					                        	<img alt="image" class="img-circle"  style="height:50px;width:50px;" src="${(teacher.avatar)!''}">
					                        </td>
				                         	<td>${(teacher.sex)!''}</td>
					                        <td>
					                        	[#if teacher.teacherType = 1]
					                        		全职教师
					                        	[/#if] 
					                        	[#if teacher.teacherType = 2]
					                        		兼职教师
					                        	[/#if] 
					                        </td>
				                          	<td>${(teacher.joinTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
				                          	<td>${(teacher.userRate)!''}</td>
					                        <td>
					                        	[#if teacher.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        	[/#if] 
					                        	[#if teacher.status = 2]
					                        		<a class="btn btn-danger btn-xs">禁用</a>
					                        	[/#if] 
					                        </td>
					                        <td>
					                        [@shiro.hasPermission name ='admin:systemorg:edit']
			                        			[#if teacher.status = 1]
				                        			<a class="btn btn-xs btn-danger forbidTeacher" data-id="${teacher.teacherId}" id="forbidTeacher" ><i class="fa fa-paste"></i> 禁用</a>
				                        		[/#if] 
				                        		[#if teacher.status = 2]
				                        			<a class="btn btn-xs btn-primary startTeacher" data-id="${teacher.teacherId}" id="startTeacher"><i class="fa fa-paste"></i>启用</a>
				                        		[/#if]
				                        	
				                        		<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${teacher.teacherId}" href="${base}/admin/teacher/edit/${teacher.teacherId}/${teacher.orgId}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
				                        	[/@shiro.hasPermission] 
				                        	
				                        		<a href="${base}/admin/teacher/view/${teacher.teacherId}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
				                        	
			                        			<a href="${base}/admin/teacher/userRate/${teacher.teacherId}/${teacher.orgId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>用户评价 </a>
				                        	
				                        		<a href="${base}/admin/teacher/orgnizationRate/${teacher.teacherId}/${teacher.orgId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>机构评价 </a>
				                        		
				                        	[@shiro.hasPermission name ='admin:systemorg:edit']
				                        		<a href="${base}/admin/hour_view/listorg/${(teacher.orgId)!''}/${teacher.teacherId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>授课信息 </a>
				                        	[/@shiro.hasPermission] 
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                           [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
								[#include "/admin/include/pagination.ftl"]
						   [/@pagination]
                    </div>
                    
					<!--/row-->
					<div class="row">
                     	<div class="hr-line-dashed"></div>
                     		<div class="form-group">
                       		<div class="col-sm-12 col-sm-offset-10">
                        		<button type="button" class="btn btn-default" onclick="location.href='${base}/admin/orgnization/list.jhtml'">返回</button>
                        	</div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    
        $(document).ready(function () {
        	[@flash_message /]
        	
			//启用
			$(".startTeacher").click(function(){
		    	var $this = $(this);
				 		$.ajax({
							url: "${base}/admin/orgnization/startTeacher.jhtml",
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
		    	});
			//禁用
			$(".forbidTeacher").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定禁用?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/orgnization/forbidTeacher.jhtml",
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
    </script>
</body>

</html>
