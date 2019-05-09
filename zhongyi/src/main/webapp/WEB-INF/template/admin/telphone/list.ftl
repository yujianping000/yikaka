
<!DOCTYPE html>
<html>

<head>
   ﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 电话管理</title>
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
                    	 <h5>电话管理 </h5>
                    </div>
                    <div class="ibox-content">
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>电话</th>
                                        <th>平台</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as telphone] 
							        	 <tr>

					                        <td>${(telphone.phone)!''}</td>
					                        <td>[#if telphone.id==1]家长端[#else]教师端[/#if]</td>
					                        <td>
					                        	 [@shiro.hasPermission name ='admin:systemtelphone:edit']
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${telphone.id}" href="edit/${telphone.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
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
