
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 课程评论管理</title>
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
                	<form id="listForm" action="${base}/admin/course_comment/listorg/${(id)!''}.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>课程评论管理 </h5>
                    	 <div class="ibox-tools">
<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/course/listorg.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入评价内容" name="filter_content" value="${(paramter.content)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入评价时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入评价时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
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
                                        <th>账号</th>
                                        <th>昵称</th>
                                        <th>评价内容</th>
                                        <th>评价标签</th>
                                        <th>评价分数</th>
                                        <th>评价时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courseComment] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${courseComment.id}">
					                        </td>
					                        <td>${(courseComment.account)!''}</td>
					                        <td>${(courseComment.name)!''}</td>
					                        <td>${(courseComment.content)!''}</td>
					                        <td>${(courseComment.mark)!''}</td>
					                        <td>${(courseComment.favStar)!''}</td>
					                        <td>${(courseComment.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>
					                        [#if courseComment.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        	[/#if] 
					                        	[#if courseComment.status = 2]
					                        			<a class="btn btn-danger btn-xs">禁用</a>
					                        	[/#if]
					                        	</td>
					                        <td>
					                        	 [#if courseComment.status = 1]
					                              <a class="btn btn-outline btn-white btn-xs btn-deluse-loippi"  data-id="${courseComment.id}">禁用</a>
					                        		
					                        	[/#if] 
					                        	[#if courseComment.status = 2]
					                        			<a class="btn btn-outline btn-white btn-xs btn-use-loippi"  data-id="${courseComment.id}">启用</a>
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
        	   	//时间控件
    		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			
			      	
    // 启用单条记录
    $(".btn-use-loippi").click(function(){
    	var $this = $(this);

		 		$.ajax({
					url: "${base}/admin/course_comment/use.jhtml",
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
    
        // 禁用单条记录
    $(".btn-deluse-loippi").click(function(){
    	var $this = $(this);
		 		$.ajax({
					url: "${base}/admin/course_comment/deluse.jhtml",
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
