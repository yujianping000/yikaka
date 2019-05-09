
<!DOCTYPE html>
<html>

<head>
﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
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
                    	 <h5>管理员列表 </h5>
                    	 <div class="ibox-tools">
                    	   [@shiro.hasPermission name ='admin:user:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                        [/@shiro.hasPermission]   
	                    </div>
                    </div>
                    <div class="ibox-content">
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>昵称</th>
                                        <th>账号</th>
                                        <th>角色</th>
                                        <th>最后登录时间</th>
                                        <th>创建时间</th>
                                          [@shiro.hasPermission name ='admin:user:edit']
                                         <th>状态</th> 
                                        <th>操作</th>
                                        [/@shiro.hasPermission]   
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as user] 
							        	 <tr>
						                        <td>
						                            <input type="checkbox" class="i-checks" name="ids" value="${user.id}">
						                        </td>
						                        <td>${user.nickname}</td>
						                        <td>${user.username}</td>
						                        <td>${user.role.name}</td>
						                        <td>${(user.loginDate?string('yyyy-MM-dd hh:mm:ss'))!''}</td>
						                        <td>${(user.createDate?string('yyyy-MM-dd hh:mm:ss'))!''}</td>
						                        
						                         [@shiro.hasPermission name ='admin:user:edit']
						                            <td>
						                              [#if user.isEnabled == "true"]
						                        	<a class="btn btn-info btn-xs btn-deluse-loippi" data-id="${user.id}"  >启用</a>
						                           [/#if]
						                           
						                            [#if user.isEnabled == ""]
						                        	<a class="btn btn-danger btn-xs btn-use-loippi" data-id="${user.id}"  >禁用</a>
						                           [/#if]
						                           
						                            </td>
						                        <td>
						                         
						                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${user.id}" href="edit.jhtml?id=${user.id}" ><i class="fa fa-paste"></i> 编辑</a>
						                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${user.id}" ><i class="fa fa-trash"></i> 删除</a>
						                        </td>
						                          [/@shiro.hasPermission]   
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
	
   <!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    <script>
        $(document).ready(function () {
        	[@flash_message /]
        	
        	
    // 启用单条记录
    $(".btn-use-loippi").click(function(){
    	var $this = $(this);

		 		$.ajax({
					url: "use.jhtml",
					type: "POST",
					data: {ids:$this.attr('data-id')},
					dataType: "json",
					cache: false,
					success: function(message) {
						window.setTimeout(function() {
							window.location.reload();
						}, 1000);
					}
				});
    });
    
        // 禁用单条记录
    $(".btn-deluse-loippi").click(function(){
    	var $this = $(this);
		 		$.ajax({
					url: "deluse.jhtml",
					type: "POST",
					data: {ids:$this.attr('data-id')},
					dataType: "json",
					cache: false,
					success: function(message) {
						window.setTimeout(function() {
							window.location.reload();
						}, 1000);
					}
				});
    });
    
        	
        	
        });
    </script>
</body>

</html>
