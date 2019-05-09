
<!DOCTYPE html>
<html>

<head>
﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 教室管理</title>
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
                    	 <h5>教室管理 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:orgclassroom:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                        [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入ID" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入教室名称" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
                             <select name="filter_type" id="type" data-placeholder="类型..." class="chosen-select"  style="width:350px;" >
                                  <option  [#if paramter.type == ""]selected[/#if] value="">全部类型</option>
                                  <option  [#if paramter.type == "1"]selected[/#if]  value="1">一对一</option>
                                  <option  [#if paramter.type == "2"]selected[/#if]  value="2">集体课</option>   		
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
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>ID</th>
                                        <th>教室名称</th>
                                        <th>类型</th>
                                        <th>容量（人）</th>
                                        <th>面积（㎡）</th>
                                        <th>备注</th>
                                        [@shiro.hasPermission name ='admin:orgclassroom:edit']
                                        <th>操作</th>
                                           [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courseRoom] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${courseRoom.id}">
					                        </td>
					                        <td>${(courseRoom.id)!''}</td>
					                        <td>${(courseRoom.name)!''}</td>
					                        <td>
					                        [#if courseRoom.type == "1"]一对一[/#if]
					                        [#if courseRoom.type == "2"]集体课[/#if]
					                        </td>
					                        <td>${(courseRoom.peoples)!''}</td>
					                        <td>${(courseRoom.area)!''}</td>
					                        <td>${(courseRoom.remark)!''}</td>
					                         [@shiro.hasPermission name ='admin:orgclassroom:edit']
					                        <td>					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courseRoom.id}" href="edit/${courseRoom.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${courseRoom.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
        	        	$(".chosen-select").chosen({width:"100%"}); 
        });
    </script>
</body>

</html>
