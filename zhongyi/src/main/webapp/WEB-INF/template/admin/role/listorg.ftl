
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
                	<form id="listForm" action="listorg.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>角色列表 </h5>
                    	 <div class="ibox-tools">
                    	                     	  [@shiro.hasPermission name ='admin:orgrole:edit']
                    	 
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="addorg.jhtml"><i class="fa fa-plus"></i> 新增</a>
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
                                        <th>名称</th>
                                        <th>描述</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as role] 
							        	 <tr>
						                        <td>
						                            <input type="checkbox" class="i-checks" name="ids" value="${role.id}">
						                        </td>
						                        <td>${role.name}</td>
						                        <td>${role.description}</td>
						                        <td>${(role.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
						                        <td>
						                        	 [@shiro.hasPermission name ='admin:orgrole:edit']
							                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${role.id}" href="editorg.jhtml?id=${role.id}" ><i class="fa fa-paste"></i> 编辑</a>
							                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${role.id}" ><i class="fa fa-trash"></i> 删除</a>
							                         [/@shiro.hasPermission] 
							                         <a href="${base}/admin/role/viewOrg/${role.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
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
	
  	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
	<script>
        $(document).ready(function () {
        	[@flash_message /]
        });
    </script>
</body>

</html>
