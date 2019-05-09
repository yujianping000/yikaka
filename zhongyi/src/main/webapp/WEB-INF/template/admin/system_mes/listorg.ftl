
<!DOCTYPE html>
<html>

<head>
﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 消息推送管理</title>
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
                        <h5>消息推送管理 </h5>

                        <div class="ibox-tools">
                        
                        
                        	[@shiro.hasPermission name ='admin:orgmes:edit']
		                          
		                          
                            <a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="addorg.jhtml"><i
                                    class="fa fa-plus"></i> 新增</a>
                            <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i
                                    class="fa fa-trash"></i> 删除</a>
                                    
                              [/@shiro.hasPermission]        
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                             <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入标题" name="filter_title" value="${(paramter.title)!''}" class="input-sm form-control">
                            </div>
                            
                             <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入推送时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入推送时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>  
                            
                            <div class="col-sm-1">
                                <div class="input-group">
                                    <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                <tr>

                                    <th><input type="checkbox" class="i-checks" name="checkAll"></th>
                                    <th>ID</th>
                                    <th>标题</th>
                                    <th>内容</th>
                                    <th>图片</th>
                                    <th>创建时间</th>
                                    <th>推送对象</th>
                                      [@shiro.hasPermission name ='admin:orgmes:edit']
                                    <th>操作</th>
                                       [/@shiro.hasPermission]   
                                </tr>
                                </thead>
                                <tbody>
                                [#list page.content as systemMes]
                                <tr>
                                    <td>
                                        <input type="checkbox" class="i-checks" name="ids" value="${systemMes.id}">
                                    </td>
                                    <td>
                                        ${systemMes.id}
                                    </td>
                                    <td>
                                        ${systemMes.title}
                                    </td>
                                    <td>
                                        ${systemMes.content}
                                    </td>
                                    <td>
                                        <image src="${(systemMes.image)!''}" width="150px" height="75px">
                                    </td>
                                     <td>
                                        ${systemMes.createDate?string('yyyy-MM-dd HH:mm')!''}
                                    </td>
                                    
                                    <td>
                                    	[#if systemMes.type==0]
                                                                                                     全体
						                [/#if]
						                [#if systemMes.type==1]
                                                                                                     教师
						                [/#if]
						                [#if systemMes.type==2]
                                                                                                    家长
						                [/#if]
                                    </td>
                                      [@shiro.hasPermission name ='admin:orgmes:edit']

                                    <td>
                                      
                                                <a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${systemMes.id}"><i
                                                class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
         		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
    
    });
</script>
</body>

</html>
