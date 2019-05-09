
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 教师管理</title>
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
                	<form id="listForm" action="list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>教师管理 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:systemteacher:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
                    	 	
                    	 	<a class="btn btn-outline btn-success btn-xs teacherExport"><i class="fa fa-file-excel-o"></i>导出教师列表</a>
                    	 	
                        	<a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                     [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" id="filter_account" value="${(paramter.filter_account)!''}" class="input-sm form-control">
                            </div>
                            
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入姓名" name="filter_nickname" id="filter_nickname" value="${(paramter.filter_nickname)!''}" class="input-sm form-control">
                            </div>
                            
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入所属机构名称" name="filter_orgName" id="filter_orgName" value="${(paramter.filter_orgName)!''}" class="input-sm form-control">
                            </div>
                            
					       	<div class="col-sm-3 m-b-xs">
                               <select class="form-control m-b" name="filter_teacherType" id="filter_teacherType">
                                    <option value="">类型</option>
                                	<option value="3" [#if paramter.filter_teacherType == '3'] selected="selected"[/#if]>公海教师</option>
                        			<option value="1" [#if paramter.filter_teacherType == '1'] selected="selected"[/#if]>全职教师</option>
                                	<option value="2" [#if paramter.filter_teacherType == '2'] selected="selected"[/#if]>兼职教师</option>
                               </select>
                            </div>
                            
                         	<div class="col-sm-3 m-b-xs">
	                           <select class="form-control m-b" name="filter_status" id="filter_status">
	                                <option value="">状态</option>
	                    			<option value="1" [#if paramter.filter_status == '1'] selected="selected"[/#if]>启用</option>
	                            	<option value="2" [#if paramter.filter_status == '2'] selected="selected"[/#if]>禁用</option>
	                           </select>
                        	</div>
                           
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                            
                            	<style>
									th,td {text-align:center}
								</style>
                            	
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                    	<th>ID</th>
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>头像</th>
                                        <th>性别</th>
                                        <th>类型</th>
                                        <th style="width:15%">所属机构</th>
                                        <th style="width:12%">加入时间</th>
                                        <th>用户评价</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as teacher] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${teacher.id}">
					                        </td>
					                        <td>${(teacher.id)!''}</td>
					                        <td>${(teacher.account)!''}</td>
					                        <td>${(teacher.nickname)!''}</td>
					                        <td> <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(teacher.avatar)!''}"> </td>
					                        <td>${(teacher.sex)!''}</td>
					                        <td>
					                        	[#if teacher.teacherType = 1]
					                        		全职教师
					                        	[/#if] 
					                        	[#if teacher.teacherType = 2]
					                        		兼职教师
					                        	[/#if]
				                        		[#if teacher.teacherType = 3]
					                        		公海教师
					                        	[/#if]
					                        </td>
					                        <td>${(teacher.orgName)!''}</td>
					                        <td>${(teacher.joinTimeList)!''}</td>
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
					                        [@shiro.hasPermission name ='admin:systemteacher:edit']
					                        	[#if teacher.status = 1]
					                        		<a class="btn btn-xs btn-danger forbidTeacher" data-id="${teacher.id}" id="forbidusing" ><i class="fa fa-paste"></i> 禁用</a>
					                        	[/#if] 
					                        	[#if teacher.status = 2]
					                        		<a class="btn btn-xs btn-primary startTeacher" data-id="${teacher.id}" id="startusing"><i class="fa fa-paste"></i>启用</a>
					                        	[/#if] 
					                        
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${teacher.id}" href="${base}/admin/teacher/edit/${teacher.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        [/@shiro.hasPermission]
					                        
					                        	<a href="${base}/admin/teacher/view/${teacher.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
					                        
					                        	<a href="${base}/admin/teacher/userRate/${teacher.id}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>用户评价 </a>
					                        	
					                        	<a href="${base}/admin/teacher/orgnizationRate/${teacher.id}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>机构评价 </a>
					                        [@shiro.hasPermission name ='admin:systemteacher:edit']	
					                        	<a class="btn btn-xs btn-success btn-edit-loippi btn-edit-loippi-pwd" data-toggle="modal" data-id="${teacher.id}" data-target="#myModal"><i class="fa fa-paste"></i>修改密码</a>
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
                    </div>
                    </form>
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
                	
                	<form id="inputModalForm" class="form-horizontal" action="${base}/admin/teacher/updatePwd.jhtml" method="post" >
		                <div class="modal-body">
		                    <div class="form-group">
		                    	<input type="hidden" value="" id="teacherId" name="teacherId"/>
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
    
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    
    	//修改密码-传入teacherId
    	 $(".btn-edit-loippi-pwd").click(function(){
			var id=$(this).attr('data-id');
			$("#teacherId").val(id);
		
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
    
        $(document).ready(function () {
        	[@flash_message /]
        	
        	//启用
			$(".startTeacher").click(function(){
		    	var $this = $(this);
				 		$.ajax({
							url: "startTeacher.jhtml",
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
							url: "forbidTeacher.jhtml",
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
			
			
			//导出课程列表
			 $(".teacherExport").click(function(){
			    var filter_account = $("#filter_account").val();
			    var filter_nickname = $("#filter_nickname").val();
			    var filter_orgName = $("#filter_orgName").val();
			    var filter_teacherType = $("#filter_teacherType").val();
			    var filter_status = $("#filter_status").val();

			 	window.open('teacherExport.jhtml?filter_account='+filter_account
			 			+'&filter_nickname='+filter_nickname
			 			+'&filter_orgName='+filter_orgName
			 			+'&filter_teacherType='+filter_teacherType
			 			+'&filter_status='+filter_status
						,'_blank');
		    });   
		    
        	
        });
    </script>
</body>

</html>
