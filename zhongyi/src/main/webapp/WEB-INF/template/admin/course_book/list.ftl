
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 试课申请</title>
    <meta name="keywords" content="${setting.siteName}">
  	<meta name="description" content="${setting.siteName}">

  	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
	<!-- END HEADER -->
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
		 <style>
			th,td {text-align:center}
		</style>
		
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                	<form id="listForm" action="list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>试课申请 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:orgbook:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
                    	 	<a class="btn btn-outline btn-success btn-xs tCourseBookExport"><i class="fa fa-file-excel-o"></i>导出</a>	
                    	 [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
	                		 <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_parentAccount" id="filter_parentAccount" value="${(paramter.parentAccount)!''}" class="input-sm form-control">
	                        </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程名称" name="filter_courseName" id="filter_courseName" value="${(paramter.courseName)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入申请时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入申请时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
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
                                        <th>申请时间</th>
                                        <th>账号</th>
                                        <th>联系方式</th>
                                        <th>孩子姓名</th>
                                        <th>孩子性别</th>
                                        <th>出生日期</th>
                                        <th>课程ID</th>
                                        <th>课程名称</th>
                                        <th style="width:15%">报班目的</th>
                                        <th>跟进记录</th>
                                        [@shiro.hasPermission name ='admin:orgbook:edit']
                                        	<th>操作</th>
                                        [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courseBook] 
							        	 <tr>
					                        <td>${(courseBook.createDate?string("yyyy-MM-dd"))!''}</td>
					                        <td>${(courseBook.parentAccount)!''}</td>
					                        <td>${(courseBook.phone)!''}</td>
					                        <td>${(courseBook.childName)!''}</td>
					                        <td>${(courseBook.sex)!''}</td>
					                        <td>${(courseBook.birthday?string("yyyy-MM-dd"))!''}</td>
					                        <td>${(courseBook.courseId)!''}</td>
					                        <td>${(courseBook.courseName)!''}</td>
					                        <td>${(courseBook.remark)!''}</td>
					                        <td>
					                        	<a href="${base}/admin/course_book/view/${courseBook.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        </td>
					                        [@shiro.hasPermission name ='admin:orgbook:edit']
						                        <td>
						                        	<a class="btn btn-danger btn-xs deleteCourseBook" id="deleteCourseBook" data-id="${courseBook.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
						                        
						                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courseBook.id}" href="edit/${courseBook.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
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
        	
        	//时间控件
    		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			//导出试课申请表
			 $(".tCourseBookExport").click(function(){
			
			    var filter_parentAccount = $("#filter_parentAccount").val();
			    var filter_sDate = $("#filter_sDate").val();
			    var filter_eDate = $("#filter_eDate").val();
			    var filter_courseName = $("#filter_courseName").val();

			 	window.open('tCourseBookExport.jhtml?filter_parentAccount='+filter_parentAccount
			 			+'&filter_sDate='+filter_sDate
			 			+'&filter_eDate='+filter_eDate
			 			+'&filter_courseName='+filter_courseName
						,'_blank');
		    }); 
			
			
			 //删除试课记录
			$(".deleteCourseBook").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定删除?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/course_book/deleteCourseBook.jhtml",
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
