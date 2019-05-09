
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 活动管理</title>
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
                    	 <h5>活动管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入活动ID" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入活动名称" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
                            <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入主办单位" name="filter_company" value="${(paramter.company)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-2 m-b-xs">
                                  <select name="filter_status" id="status" data-placeholder="状态..." class="chosen-select"  style="width:350px;" >
                                  <option  [#if paramter.status == ""]selected[/#if] value="">全部状态</option>
                                  <option  [#if paramter.status == "1"]selected[/#if]  value="1">报名中</option>
                                  <option  [#if paramter.status == "2"]selected[/#if]  value="2">进行中</option> 
                                  <option  [#if paramter.status == "3"]selected[/#if]  value="3">未开始</option>
                                  <option  [#if paramter.status == "4"]selected[/#if]  value="3">已结束</option>  
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
                                        <th>活动名称</th>
                                        <th>报名时间</th>
                                        <th>活动地点</th>
                                        <th>主办单位</th>
                                        <th>费用（元）</th>
                                        <th>活动名额</th>
                                        <th>报名人数</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as activity] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${activity.id}">
					                        </td>
					                        <td>${(activity.id)!''}</td>
					                        <td>${(activity.name)!''}</td>
					                        <td>${(activity.startTime?string('yyyy-MM-dd'))!''}~${(activity.endTime?string('yyyy-MM-dd'))!''}</td>
					                         <td>${(activity.location)!''}</td>
					                          <td>${(activity.company)!''}</td>
					                          <td>${(activity.fee)!''}</td>
					                          <td>${(activity.totalSum)!''}</td>
					                          <td>${(activity.already2)!''} [#if activity.already2 == ""]0[/#if]</td>
					                        <td>
                                   [#if activity.status == "1"]报名中[/#if]
                                   [#if activity.status == "2"]进行中[/#if]
                                   [#if activity.status == "3"]未开始[/#if]
                                   [#if activity.status == "4"]已结束[/#if]    
					                        </td>
					                        <td>
					                        	<a href="${base}/admin/activity/view/${activity.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	[@shiro.hasPermission name ='admin:systemactivity:edit']
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${activity.id}" href="edit/${activity.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${activity.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
					                        	[/@shiro.hasPermission]
					                        	<a href="${base}/admin/activity_stu_view/list/${activity.id}.jhtml" class="btn btn-xs btn-default"> 报名情况</a>
					                        
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
        	$(".chosen-select").chosen({width:"100%"}); 
        });
    </script>
</body>

</html>
