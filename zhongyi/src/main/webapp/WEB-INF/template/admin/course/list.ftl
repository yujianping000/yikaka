
<!DOCTYPE html>
<html>

<head>
﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 课程表管理</title>
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
                    	 <h5>课程表管理 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:systemcourse:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="addone.jhtml"><i class="fa fa-plus"></i> 新增一对一</a>
	                        <a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="addlist.jhtml"><i class="fa fa-plus"></i> 新增集体课</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                     [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程ID" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程名称" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构名称" name="filter_orgname" value="${(paramter.orgname)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入教师名称" name="filter_teachername" value="${(paramter.teachername)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					         <select name="filter_type" id="type" data-placeholder="类型..." class="chosen-select"  style="width:350px;" >
                                  <option  [#if paramter.type == ""]selected[/#if] value="">全部类型</option>
                                  <option  [#if paramter.type == "1"]selected[/#if]  value="1">一对一</option>
                                  <option  [#if paramter.type == "2"]selected[/#if]  value="2">集体课</option>   		
						   </select>
                            </div>
					        <div class="col-sm-3 m-b-xs">
                           <select name="filter_status" id="status" data-placeholder="状态..." class="chosen-select"  style="width:350px;" >
                                  <option  [#if paramter.status == ""]selected[/#if] value="">全部状态</option>
                                  <option  [#if paramter.status == "1"]selected[/#if]  value="1">上架中</option>
                                  <option  [#if paramter.status == "2"]selected[/#if]  value="2">已下架</option> 
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
                                        <th>课程名称</th>
                                        <th>类型</th>
                                        <th>所属机构</th>
                                        <th>主讲教师</th>
                                        <th>教师账号</th>
                                        <th>价格（元）</th>
                                        <th>学期</th>
                                        <th>开课时间</th>
                                        <th>状态</th>
                                       
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
           
                                	 [#list page.content as course] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${course.id}">
					                        </td>
					                        <td>${(course.id)!''}</td>
					                        <td>${(course.name)!''}</td>
					                        <td>
					                        [#if course.type == "1"]一对一[/#if]
					                        [#if course.type == "2"]集体课[/#if]
					                        </td>
					                        <td>${(course.orgname)!''}</td>
					                        <td>${(course.description)!''}</td>
					                        <td>${(course.image)!''}</td>
					                        <td>${(course.price)!''}</td>
					                         <td>${(course.term)!''}</td>
					                         <td>${(course.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>
					                        [#if course.status == "1"]已上架[/#if]
					                        [#if course.status == "2"] <span style="color:#F00">已下架</span>[/#if]
					                        [#if course.status == "3"]未上架[/#if]
					                        </td>

					                        <td>
					                        
					                        [@shiro.hasPermission name ='admin:systemcourse:edit']
					                        
					                         
					                         [#if course.status == "1"]
						                        	<a class="btn btn-danger btn-xs btn-dowm-loippi" data-id="${course.id}"  >下架</a>
						                           [/#if]
						                           
						                            [#if course.status== "2"]
						                        	<a class="btn btn-info btn-xs btn-up-loippi" data-id="${course.id}"  >上架</a>
						                           [/#if]
					                        
					                              [#if course.status== "3"]
						                        	<a class="btn btn-info  btn-xs btn-up-loippi" data-id="${course.id}"  >上架</a>
						                           [/#if]
					                        
					                       <a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${course.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
					                        
					                         [#if course.type == "1"]
					                             <a class="btn btn-info btn-xs btn-edit-loippi" data-id="${course.id}" href="editone/${course.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
                                              [/#if]
					                           [#if course.type == "2"]
					                             <a class="btn btn-info btn-xs btn-edit-loippi" data-id="${course.id}" href="editlist/${course.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
                                              [/#if]
                                              
                                              
                                                <a class="btn btn-info btn-xs btn-opencoursetime-loippi" data-id="${course.id}"  ><i class="fa fa-paste"></i>发送开课时间</a>
                                              
                                              
                                               [/@shiro.hasPermission]
                                              
                                              
					                         <a href="${base}/admin/cource_stu_view/list/${course.id}.jhtml" class="btn btn-xs btn-default">学员</a>
					                     	 <a href="${base}/admin/course_comment/list/${course.id}.jhtml" class="btn btn-xs btn-default">评价</a>
                                           [#if course.type == "1"]
					                         <a href="${base}/admin/course/viewone/${course.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                         [/#if]
					                        [#if course.type == "2"]
					                        <a href="${base}/admin/course/viewlist/${course.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        [/#if]
					                        	
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
        
         //
    $(".btn-dowm-loippi").click(function(){
    	var $this = $(this);

		 		$.ajax({
					url: "dowm.jhtml",
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
    
        //上架
    $(".btn-up-loippi").click(function(){
    	var $this = $(this);
		 		$.ajax({
					url: "up.jhtml",
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
        	
        	        //发送开课时间
    $(".btn-opencoursetime-loippi").click(function(){
   var $this = $(this);
    	 bootbox.confirm(message("上课时间将会重新发送学生，老师，您确定发送？"), function(result) {
		 	if(result){
            $.loading.init('数据处理中...');
            var id=$this.attr('data-id');
		 		$.ajax({
					url:"opencoursetime.jhtml",
					type:"POST",
					data:{id:id},
					dataType:"json",
				
					success: function(message) {
						window.setTimeout(function() {
							window.location.reload();
						}, 1000);
					}
				});
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
