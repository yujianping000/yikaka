
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 评价标签管理</title>
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
                    	 <h5>评价标签管理 </h5>
                    </div>
                    
                    <div class="ibox-content">
                    	<br/>
                    	   <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>课程评价标签</h3>
	                	    <div class="ibox-tools">
	                	    [@shiro.hasPermission name ='admin:systemremark:edit']
	                	 		<a class="btn btn-outline btn-success btn-xs" data-toggle="modal" data-target="#myModal1"><i class="fa fa-plus"></i>新增</a>
	                	 	 [/@shiro.hasPermission]
	                    	</div>
						   <div class="dataTables_wrapper form-inline">
						   <style>
						   		th,td {text-align:center}
						   </style>
						   <br/><br/>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>标签名</th>
                                        [@shiro.hasPermission name ='admin:systemremark:edit']
                                        	<th>操作</th>
                                        [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list courseCommentRemarkList as courseCommentRemark] 
							        	 <tr>
					                        <td>${(courseCommentRemark_index+1)!''}</td>
					                        <td>${(courseCommentRemark.name)!''}</td>
					                        [@shiro.hasPermission name ='admin:systemremark:edit']
						                        <td>
						                        	<a class="btn btn-danger btn-xs  deleteCourseCommentRemark"id="deleteCourseCommentRemark" data-id="${courseCommentRemark.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
						                        	
						                        	<a class="btn btn-info btn-xs btn-edit-loippi editCourseCommentRemark" data-id="${courseCommentRemark.id}" data-name="${courseCommentRemark.name}" data-toggle="modal" data-target="#myModal4" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
						                        </td>
					                       [/@shiro.hasPermission]
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    
                    <div class="ibox-content">
                    	<br/>
                    	    <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>教师评价标签</h3>
	                	    <div class="ibox-tools">
	                	    [@shiro.hasPermission name ='admin:systemremark:edit']
	                	 		<a class="btn btn-outline btn-success btn-xs" data-toggle="modal" data-target="#myModal2"><i class="fa fa-plus"></i>新增</a>
	                	 	[/@shiro.hasPermission]
	                    	</div>
						   <div class="dataTables_wrapper form-inline">
						   <style>
						   		th,td {text-align:center}
						   </style>
						   <br/><br/>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>标签名</th>
                                        [@shiro.hasPermission name ='admin:systemremark:edit']
                                        	<th>操作</th>
                                        [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list teacherCommentRemarkList as teacherCommentRemark] 
							        	 <tr>
					                        <td>${(teacherCommentRemark_index+1)!''}</td>
					                        <td>${(teacherCommentRemark.name)!''}</td>
					                        [@shiro.hasPermission name ='admin:systemremark:edit']
						                        <td>
						                        	<a class="btn btn-danger btn-xs  deleteTeacherCommentRemark"id="deleteTeacherCommentRemark" data-id="${teacherCommentRemark.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
						                        	
						                        	<a class="btn btn-info btn-xs btn-edit-loippi editTeacherCommentRemark"  data-id="${teacherCommentRemark.id}" data-name="${teacherCommentRemark.name}" data-toggle="modal" data-target="#myModal5" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
						                        </td>
						                    [/@shiro.hasPermission]
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    
                    <div class="ibox-content">
                    	<br/>
                    	   <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>学生评价标签</h3>
	                	    <div class="ibox-tools">
	                	    [@shiro.hasPermission name ='admin:systemremark:edit']
	                	 		<a class="btn btn-outline btn-success btn-xs" data-toggle="modal" data-target="#myModal3"><i class="fa fa-plus"></i>新增</a>
	                	 	[/@shiro.hasPermission]
	                    	</div>
						   <div class="dataTables_wrapper form-inline">
						   <style>
						   		th,td {text-align:center}
						   </style>
						   <br/><br/>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>标签名</th>
                                        [@shiro.hasPermission name ='admin:systemremark:edit']
                                        	<th>操作</th>
                                        [/@shiro.hasPermission]
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list studentCommentRemarkList as studentCommentRemark] 
							        	 <tr>
					                        <td>${(studentCommentRemark_index+1)!''}</td>
					                        <td>${(studentCommentRemark.name)!''}</td>
					                        [@shiro.hasPermission name ='admin:systemremark:edit']
						                        <td>
						                        	<a class="btn btn-danger btn-xs  deleteStudentCommentRemark"id="deleteStudentCommentRemark" data-id="${studentCommentRemark.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
						                        	
						                        	<a class="btn btn-info btn-xs btn-edit-loippi editStudentCommentRemark" data-id="${studentCommentRemark.id}" data-name="${studentCommentRemark.name}" data-toggle="modal" data-target="#myModal6" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
						                        </td>
					                        [/@shiro.hasPermission]
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal inmodal" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
            	<div class="modal-content animated bounceInRight">
            	
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    	<h4 class="modal-title" id="myModalLabel">添加标签</h4>
                	</div>
                	
                	<form id="inputModalForm1" class="form-horizontal" action="${base}/admin/comment_remark/addCourseCommentRemark.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<label>标签名称<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="courseCommentRemark_name" id="courseCommentRemark_name" placeholder="请输入标签名称" class="form-control" >
			                    </div>
		                </div>
                
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
		                </div>
	                </form>
	                
                </div>
            </div>
         </div>
	</div>
	
	 <div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	        	<div class="modal-content animated bounceInRight">
	        	
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                	<h4 class="modal-title" id="myModalLabel">添加标签</h4>
	            	</div>
	            	
	            	<form id="inputModalForm2" class="form-horizontal" action="${base}/admin/comment_remark/addTeacherCommentRemark.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<label>标签名称<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="teacherCommentRemark_name" id="teacherCommentRemark_name" placeholder="请输入标签名称" class="form-control" >
			                    </div>
		                </div>
	            
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
		                </div>
	                </form>
	                
	            </div>
	        </div>
	     </div>
	</div>
	
	 <div class="modal inmodal" id="myModal3" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	        	<div class="modal-content animated bounceInRight">
	        	
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                	<h4 class="modal-title" id="myModalLabel">添加标签</h4>
	            	</div>
	            	
	            	<form id="inputModalForm3" class="form-horizontal" action="${base}/admin/comment_remark/addStudentCommentRemark.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<label>标签名称<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="studentCommentRemark_name" id="studentCommentRemark_name" placeholder="请输入标签名称" class="form-control" >
			                    </div>
		                </div>
	            
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
		                </div>
	                </form>
	                
	            </div>
	        </div>
	     </div>
	</div>
	
	
	 <div class="modal inmodal" id="myModal4" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	        	<div class="modal-content animated bounceInRight">
	        	
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                	<h4 class="modal-title" id="myModalLabel">编辑标签</h4>
	            	</div>
	            	
	            	<form id="inputModalForm4" class="form-horizontal" action="${base}/admin/comment_remark/editCourseCommentRemark.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<input type="hidden" value="" id="c_id" name="c_id" />
			                    	<label>标签名称<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="c_name" id="c_name" placeholder="请输入标签名称" class="form-control" >
			                    </div>
		                </div>
	            
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
		                </div>
	                </form>
	                
	            </div>
	        </div>
	     </div>
	</div>
	
	 <div class="modal inmodal" id="myModal5" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	        	<div class="modal-content animated bounceInRight">
	        	
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                	<h4 class="modal-title" id="myModalLabel">编辑标签</h4>
	            	</div>
	            	
	            	<form id="inputModalForm5" class="form-horizontal" action="${base}/admin/comment_remark/editTeacherCommentRemark.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<input type="hidden" value="" id="t_id" name="t_id" />
			                    	<label>标签名称<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="t_name" id="t_name" placeholder="请输入标签名称" class="form-control" >
			                    </div>
		                </div>
	            
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
		                </div>
	                </form>
	                
	            </div>
	        </div>
	     </div>
	</div>
	
	 <div class="modal inmodal" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true">
	        <div class="modal-dialog">
	        	<div class="modal-content animated bounceInRight">
	        	
	            	<div class="modal-header">
	                	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                	<h4 class="modal-title" id="myModalLabel">编辑标签</h4>
	            	</div>
	            	
	            	<form id="inputModalForm6" class="form-horizontal" action="${base}/admin/comment_remark/editStudentCommentRemark.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<input type="hidden" value="" id="s_id" name="s_id" />
			                    	<label>标签名称<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="s_name" id="s_name" placeholder="请输入标签名称" class="form-control" >
			                    </div>
		                </div>
	            
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
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
    
    	 //编辑-传入课程评价标签信息
    	 $(".editCourseCommentRemark").click(function(){
			var id=$(this).attr('data-id');
			var name=$(this).attr('data-name');
			$("#c_id").val(id);
			$("#c_name").val(name);
    	});
    	
    	 //编辑-传入教师评价标签信息
    	 $(".editTeacherCommentRemark").click(function(){
			var id=$(this).attr('data-id');
			var name=$(this).attr('data-name');
			$("#t_id").val(id);
			$("#t_name").val(name);
		
    	});
    	
    	 //编辑-传入学生评价标签信息
    	 $(".editStudentCommentRemark").click(function(){
			var id=$(this).attr('data-id');
			var name=$(this).attr('data-name');
			$("#s_id").val(id);
			$("#s_name").val(name);
		
    	});
    	
    
    	//课程评价——新增——校验
    		$("#inputModalForm1").validate({
                rules: {
                   courseCommentRemark_name: {
	                    required: true
	                },
	             },
    			  messages: {
                   }      
    		});
    		
		//教师评价校验——新增——校验
		$("#inputModalForm2").validate({
            rules: {
               teacherCommentRemark_name: {
                    required: true
                },
             },
			  messages: {
               }      
		});
		
		//教师评价校验——新增——校验
		$("#inputModalForm3").validate({
            rules: {
               studentCommentRemark_name: {
                    required: true
                },
             },
			  messages: {
               }      
		});
		
		//课程评价——编辑——校验
    		$("#inputModalForm4").validate({
                rules: {
                   c_name: {
	                    required: true
	                },
	             },
    			  messages: {
                   }      
    		});
    		
		//教师评价校验——编辑——校验
		$("#inputModalForm5").validate({
            rules: {
               t_name: {
                    required: true
                },
             },
			  messages: {
               }      
		});
		
		//教师评价校验——编辑——校验
		$("#inputModalForm6").validate({
            rules: {
               s_name: {
                    required: true
                },
             },
			  messages: {
               }      
		});
    	
    
        $(document).ready(function () {
        	[@flash_message /]
        	
        	//删除课程评价
			$(".deleteCourseCommentRemark").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定删除?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/comment_remark/deleteCourseCommentRemark.jhtml",
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
			
			 //删除教师评价
			$(".deleteTeacherCommentRemark").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定删除?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/comment_remark/deleteTeacherCommentRemark.jhtml",
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
			
			
			 //删除学生评价
			$(".deleteStudentCommentRemark").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定删除?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "${base}/admin/comment_remark/deleteStudentCommentRemark.jhtml",
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
