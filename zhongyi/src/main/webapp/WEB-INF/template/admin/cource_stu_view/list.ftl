
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 查询学员</title>
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
                	<form id="listForm" action="${base}/admin/cource_stu_view/list/${id}.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>查询学员 </h5>
                    	 <div class="ibox-tools">
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
					       
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入孩子姓名" name="filter_stuName" value="${(paramter.stuName)!''}" class="input-sm form-control">
                            </div>
				
					        
                             <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入报名时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入报名时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>  
					       
                            <div class="col-sm-2">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                     　　<button type="button" class="btn  btn-sm btn-white" 
                    	 	onclick="location.href='${base}/admin/course/list.jhtml'"><i class="fa fa-reply-all"></i> 返回课程列表</button>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                      <th>账号</th>
                                        <th>昵称</th>
                                        
                                        <th>联系电话</th>
                                        <th>孩子姓名</th>
                                        <th>性别</th>
                                        <th>孩子头像</th>
                                        <th>出生日期</th>
                                        <th>报名时间</th>
                                        <th>剩余课时</th>
                                        <th>第几期</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courceStuView] 
							        	 <tr>
					                       
					                        <td>${(courceStuView.account)!''}</td>
					                        <td>${(courceStuView.nickName)!''}</td>
					                    
					                        <td>${(courceStuView.phone)!''}</td>
					                        <td>${(courceStuView.stuName)!''}</td>
					                        <td>${(courceStuView.stuSex)!''}</td>
					                        <td>
					                        <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(courceStuView.stuAvatar)!''}"></td>
					                        <td>${(courceStuView.stuBirthday?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(courceStuView.createdTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>${(courceStuView.classtimes)!''}</td>
					                            <td>${(courceStuView.term)!''}</td>
					                        <td>
					                        	<a href="${base}/admin/cource_stu_view/view/${courceStuView.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	 [@shiro.hasPermission name ='admin:systemcourse:edit']
					                        	 
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courceStuView.id}" href="${base}/admin/course_stu_time/list/${courceStuView.id}.jhtml" ><i class="fa fa-paste"></i>课时管理</a>
					                        	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/course_stu_time/add.jhtml?id=${courceStuView.id}"><i class="fa fa-plus"></i>安排课时</a>
					                        	                     [/@shiro.hasPermission]
					                        
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
        		//时间控件
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
