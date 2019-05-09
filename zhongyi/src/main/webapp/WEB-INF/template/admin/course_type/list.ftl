
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿﻿﻿﻿	[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]	
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 课程类型管理</title>
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
                    	 <h5>课程类型管理 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:systemcoursetype:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                     [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
						   <div class="dataTables_wrapper form-inline">
						   <style>
								th,td {text-align:center}
						   </style>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>ID</th>
                                        <th>课程类型名称</th>
                                     	[@shiro.hasPermission name ='admin:systemcoursetype:edit']
                                        	<th>操作</th>
                                        [/@shiro.hasPermission]	
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courseType] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${courseType.id}">
					                        </td>
				                        	<td>${(courseType_index+1)}</td>
					                        <td>${(courseType.name)!''}</td>
					                        [@shiro.hasPermission name ='admin:systemcoursetype:edit']
						                        <td>
						                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courseType.id}" href="edit/${courseType.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
						                        	
						                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${courseType.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
        });
    </script>
</body>

</html>
