
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 任务等级管理(等级序号必须要123..递增)</title>
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
                    	 <h5>任务等级管理(等级序号必须要123..递增) </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					       
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入等级" name="filter_level" value="${(paramter.level)!''}" class="input-sm form-control">
                            </div>
					       
                           
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>等级</th>
                                        <th>等级积分值</th>
                                        <th>图片1</th>
                                        <th>图片2</th>
                                        <th>图片3</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as taskLevel] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${taskLevel.id}">
					                        </td>
					                      
					                        <td>${(taskLevel.level)!''}</td>
					                        <td>${(taskLevel.levelValue)!''}</td>
					                        <td>
					                        <img alt="image"  style="height:100px;width:100px;" 
					                        src="${(taskLevel.levelImage1)!''}"></td>
					                          <td>
					                        <img alt="image" style="height:100px;width:100px;" 
					                        src="${(taskLevel.levelImage2)!''}"></td>
					                           <td>
					                        <img alt="image"  style="height:100px;width:100px;" 
					                        src="${(taskLevel.levelImage3)!''}"></td>
					                        <td>
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${taskLevel.id}" href="edit/${taskLevel.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${taskLevel.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
