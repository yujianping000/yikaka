
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 横幅表管理</title>
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
                    	 <h5>横幅表管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>ID </th>
                                         <th>广告名称</th>
                                         <th>跳转链接</th>
                                        <th>图片</th>
                                        <th>轮播次序</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as tBanner] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${tBanner.id}">
					                        </td>
					                        <td>${(tBanner.id)!''}</td>
					                         <td>${(tBanner.title)!''}</td>
					                        <td>${(tBanner.linkUrl)!''}</td>
					                         <td><img src="${(tBanner.imgUrl)!''}" width="100px" height="100px"></td>
					                        <td>${(tBanner.sort)!''}</td>
					                     
					                        <td>
					                        	<a href="${base}/admin/tbanner/view/${tBanner.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tBanner.id}" href="edit/${tBanner.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${tBanner.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
