
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
                    	 <h5>版本管理 </h5>
                    	 <div class="ibox-tools">
	                    </div>
                    </div>
                    <div class="ibox-content">
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>设备类型</th>
                                        <th>版本号</th>
                                        <th>下载地址</th>
                                        <th>升级说明</th>
                                        [@shiro.hasPermission name ='admin:appversion:edit']
                                        <th>操作</th>
                                        [/@shiro.hasPermission]   
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list apps as app] 
							        	 <tr>
					                        <td>${app.type}</td>
					                        <td>${app.version}</td>
					                         <td>${app.url}</td>
					                        <td>${app.content}</td>
					                
					                      [@shiro.hasPermission name ='admin:appversion:edit']
					                        <td>
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${app.id}" href="edit.jhtml?id=${app.id}" ><i class="fa fa-paste"></i> 更新</a>
					                        </td>
					                      [/@shiro.hasPermission]   
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
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
