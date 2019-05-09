
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 节假日管理</title>
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
                    	 <h5>节假日管理 </h5>
                    	 <div class="ibox-tools">
                    	  [@shiro.hasPermission name ='admin:systemholiday:edit']
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
					        	<div class="input-daterange" id="datepicker">
					        		<input type="text" placeholder="请输入时间" name="filter_holiday" id="holiday" value="${(paramter.holiday)!''}" class="input-sm form-control  "> 
                            	</div>
                         
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
                                        <th>节假日</th>
                                        [@shiro.hasPermission name ='admin:systemholiday:edit']
                                        <th>操作</th>
                                           [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as holiday] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${holiday.id}">
					                        </td>
					                        <td>${(holiday.id)!''}</td>
					                        <td>${(holiday.holiday)!''}</td>
					                        [@shiro.hasPermission name ='admin:systemholiday:edit']
					                        <td>
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${holiday.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
        	    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        	        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        });
    </script>
</body>

</html>
