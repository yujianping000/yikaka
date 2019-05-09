
	<!DOCTYPE html>
	<html>
	<head>
		﻿﻿﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<title>${setting.siteName} - 查看调班跟进记录</title>
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
	            	<div class="ibox-title">
	                	 <h5>查看调班跟进记录</h5>
	                	 <div class="ibox-tools">
	                	 [@shiro.hasPermission name ='admin:orgchange:edit']
	                	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/course_change/addCourseChangeRecord/${courseChangeId}.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                	 [/@shiro.hasPermission]
	                    </div>
	                </div>    
	                
	              	<div class="ibox-content">
	              		  
	              		  <div class="dataTables_wrapper form-inline">
	                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
	                        <style>
						   		th,td {text-align:center}
						   </style>
	                            <thead>
	                                <tr>
	                                	<th>序号</th>
	                                    <th>跟进日期</th>
	                                    <th>跟进情况记录</th>
	                                    <th>跟进人姓名</th>
	                                    <th>备注</th>
	                                    [@shiro.hasPermission name ='admin:orgchange:edit']
	                                    	<th>操作</th>
	                                    [/@shiro.hasPermission]
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	 [#list page.content as courseChangeRecord] 
							        	 <tr>
					                        <td>${(courseChangeRecord_index+1)!''}</td>
					                        <td>${(courseChangeRecord.recordTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(courseChangeRecord.record)!''}</td>
					                        <td>${(courseChangeRecord.name)!''}</td>
					                        <td>${(courseChangeRecord.remark)!''}</td>
					                        [@shiro.hasPermission name ='admin:orgchange:edit']
						                        <td>
						                        	<a class="btn btn-danger btn-xs deleteCourseChangeRecord" id="deleteCourseChangeRecord" data-id="${courseChangeRecord.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
						                        	
						                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courseChangeRecord.id}" href="${base}/admin/course_change/editCourseChangeRecord/${courseChangeRecord.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
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
	              		  
	              		  <!--/row-->
					<div class="row">
	                 	<div class="hr-line-dashed"></div>
	                 		<div class="form-group">
	                   		<div class="col-sm-12 col-sm-offset-10">
	                    		<button type="button" class="btn btn-default" onclick="location.href='${base}/admin/course_change/list.jhtml'">返回</button>
	                    	</div>
	                </div>
	              		  
	              	</div>
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
        	
			 //删除调班跟进记录
			$(".deleteCourseChangeRecord").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定删除?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/course_change/deleteCourseChangeRecord.jhtml",
							type: "POST",
							data: {id:$this.attr('data-id')},
							dataType: "json",
							cache: false,
							success: function(message) {
								window.setTimeout(function() {
									window.location.reload();
								}, 1000);
							}
						});
				 	}
			    }); 
			});
			
			
        	
        });
    </script>
	
	</body>
	</html>
