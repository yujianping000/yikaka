
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
                	<form id="listForm" action="${base}/admin/cource_stu_view/listorgss/${id}.jhtml" method="get">
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
					        <input type="text" placeholder="请输入姓名" name="filter_nickName" value="${(paramter.nickName)!''}" class="input-sm form-control">
                            </div>
					       
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入孩子姓名" name="filter_stuName" value="${(paramter.stuName)!''}" class="input-sm form-control">
                            </div>
				
					       <!-- 
	                         <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入报名时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入报名时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
	                        	</div>
	                        </div>  
	                        -->
					       
					          <div class="col-sm-3 m-b-xs">
					         <select name="filter_openclass" id="openclass" data-placeholder="类型..." class="chosen-select"  style="width:350px;" >
                                  <option  [#if paramter.openclass == ""]selected[/#if] value="">全部状态</option>
                                  <option  [#if paramter.openclass == "1"]selected[/#if]  value="1">已报名</option>
                                  <option  [#if paramter.openclass == "2"]selected[/#if]  value="2">上课中</option>   		
						   </select>
						   
                            </div>
					       
                            <div class="col-sm-2">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                     　　<button type="button" class="btn  btn-sm btn-white" 
                    	 	onclick="location.href='${base}/admin/course/listorg.jhtml'"><i class="fa fa-reply-all"></i> 返回课程列表</button>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th>账号</th>
                                        <th>学员姓名</th>
                                        <th>性别</th>
                                        <th>学员头像</th>
                                        <th>联系电话</th>
                                        <th>联系人</th>
                                        <th>出生日期</th>
                                        <th>加入时间</th>
                                        
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courceStuView] 
							        	 <tr>
					                       
					                        <td>${(courceStuView.account)!''}</td>
					                         <td>${(courceStuView.stuName)!''}</td>
					                         <td>${(courceStuView.stuSex)!''}</td>
					                         <td>
					                        <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(courceStuView.stuAvatar)!''}"></td>
					                         
					                         <td>${(courceStuView.phone)!''}</td>
					                        <td>${(courceStuView.nickName)!''}</td>
					                        <td>${(courceStuView.stuBirthday?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(courceStuView.createdTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        
					                        <td>
					                       
					                        	<a href="${base}/admin/cource_stu_view/vieworgss/${courceStuView.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	[@shiro.hasPermission name ='admin:orgcourse:edit']
					                        	 [#if courceStuView.openclass == ""]
					                        	<a class="btn btn-outline btn-success btn-xs btn-open-loippi" data-id="${courceStuView.id}" >开课</a>
					                        [/#if]
					                            
					                            <a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courceStuView.id}" href="${base}/admin/course_stu_time/listorg/${courceStuView.id}.jhtml" ><i class="fa fa-paste"></i>编辑</a>
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courceStuView.id}" href="${base}/admin/course/viewlistorg/353.jhtml" ><i class="fa fa-paste"></i>课程管理</a>
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courceStuView.id}" href="${base}/admin/course_stu_time/lisa/${courceStuView.id}.jhtml" ><i class="fa fa-paste"></i>考勤</a>
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courceStuView.id}" href="${base}/admin/course_comment/listorg/353.jhtml" ><i class="fa fa-paste"></i>评价</a>

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
	
	
	
	
	
	 <div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <small class="font-bold">导入学员文件
            </div>
            <div class="modal-body">
                <div class="form-group">
                <form id="importForm" action="/zhongyi/admin/activity_stu_view/activityPrize/353.jhtml" method="post" enctype="multipart/form-data">
                <input type="file" id="batchFileMessage" name="batchFileMessage" />
                <input name="imp_clazz" type="hidden" id="imp_clazz">
            </form>
    
                </div>
                </br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-white" onclick="saveInfo(0)">保存
                </button>
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
        		      		$(".chosen-select").chosen({width:"100%"}); 
    		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			
			  //开课
    $(".btn-open-loippi").click(function(){
    	var $this = $(this);
		 		$.ajax({
					url: "${base}/admin/cource_stu_view/open.jhtml",
					type: "POST",
					data: {ids:$this.attr('data-id')},
					dataType: "json",
					cache: false,
					success: function(message) {
						window.setTimeout(function() {
							window.location.reload();
						}, 1000);
					}
				});
    });		
			
			
        });
    </script>
</body>

</html>
