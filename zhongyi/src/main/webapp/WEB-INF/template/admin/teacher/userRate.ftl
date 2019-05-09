
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 用户评价</title>
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
                [#if orgId??]
                	<form id="listForm" action="${base}/admin/teacher/userRate/${teacher.id}/${paramter.orgId}.jhtml" method="get">
                [#else]	
                	<form id="listForm" action="${base}/admin/teacher/userRate/${teacher.id}.jhtml" method="get">
                [/#if]	
                    <div class="ibox-title">
                    	 <h5>查看评价 </h5>
                    	 <div class="ibox-tools">
                    	 [#if orgId??]
                    	 <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
	                	 [#else]
	                	 	 <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="location.href='${base}/admin/teacher/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                	 [/#if]
	                    </div>
	                </div>
	                
	                <div class="ibox-content">
	                <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>用户评价</h3><br/>
	                	<div class="row">
                    	
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" id="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
                            
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入评价内容" name="filter_content" id="filter_content" value="${(paramter.content)!''}" class="input-sm form-control">
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
						   
						   <style>
								th,td {text-align:center}
							</style>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                            
                                <thead>
                                    <tr>
                                        <th>账号</th>
                                        <th>昵称</th>
                                    	<th style="width:19%">评价内容</th>
                                    	<th>评价标签</th>
                                        <th>评价分数</th>
                                        <th>评价时间</th>
                                        <th>状态</th>
                                        [@shiro.hasPermission name ='admin:systemteacher:edit']
                                        	<th>操作</th>
                                        [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                            	 	[#list page.content as courseTimeComment] 
							        	 <tr>
					                        <td>${(courseTimeComment.account)!''}</td>
					                        <td>${(courseTimeComment.name)!''}</td>
					                        <td>${(courseTimeComment.content)!''}</td>
					                        <td>${(courseTimeComment.mark)!''}</td>
					                        <td>
					                        	${(courseTimeComment.favStar)!''}
					                        </td>
					                        <td>${(courseTimeComment.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>
				                        		[#if courseTimeComment.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        	[/#if] 
					                        	[#if courseTimeComment.status = 2]
					                        		<a class="btn btn-danger btn-xs">禁用</a>
					                        	[/#if] 
					                        </td>
					                        [@shiro.hasPermission name ='admin:systemteacher:edit']
						                        <td>
					                        		[#if courseTimeComment.status = 1]
						                        		<a class="btn btn-xs btn-danger forbidUserRate" data-id="${(courseTimeComment.id)!''}" id="forbidUserRate" ><i class="fa fa-paste"></i> 禁用</a>
						                        	[/#if] 
					                        		[#if courseTimeComment.status = 2]
						                        		<a class="btn btn-xs btn-primary startUserRate" data-id="${(courseTimeComment.id)!''}" id="startUserRate"><i class="fa fa-paste"></i>启用</a>
						                        	[/#if] 
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
	                       		  [#if orgId??]
	                       		  	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                       		  [#else]
	                        		<button type="button" class="btn btn-default" onclick="location.href='${base}/admin/teacher/list.jhtml'">返回</button>
	                        	  [/#if]
	                       		 </div>
                    		</div>
                		</div>
                	</div>
                        
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
		<script src="${base}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
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
			
			//启用
			$(".startUserRate").click(function(){
		    	var $this = $(this);
				 		$.ajax({
							url: "${base}/admin/teacher/startUserRate.jhtml",
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
		    	});
		
			//禁用
			$(".forbidUserRate").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定禁用?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/teacher/forbidUserRate.jhtml",
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
