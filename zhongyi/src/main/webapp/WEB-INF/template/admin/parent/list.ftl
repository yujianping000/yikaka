
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 家长管理</title>
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
                    	 <h5>家长管理 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:systemparent:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs doExport"><i class="fa fa-file-excel-o"></i>导出家长列表</a>
	                     [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
                    	
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" id="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
                            
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入姓名" name="filter_relName" id="filter_relName" value="${(paramter.relName)!''}" class="input-sm form-control">
                            </div>
                            
                            <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入注册时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入注册时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>           
                           
                           <div class="col-sm-3 m-b-xs">
                               <select class="form-control m-b" name="filter_status" id="filter_status">
                                    <option value="">全部</option>
                        			<option value="1" [#if paramter.filter_status == '1'] selected="selected"[/#if]>启用</option>
                                	<option  value="2" [#if paramter.filter_status == '2'] selected="selected"[/#if]>禁用</option>
                               </select>
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
                                        <th>ID</th>
                                        <th>账号</th>
                                        <th>昵称</th>
                                    	<th>头像</th>
                                    	<th>性别</th>
                                        <th>真实姓名</th>
                                        <th>注册时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as parent] 
							        	 <tr>
					                        <td>${(parent.id)!''}</td>
					                        <td>${(parent.account)!''}</td>
					                        <td>${(parent.nickName)!''}</td>
					                        <td> <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(parent.avatar)!''}"> </td>
					                        <td>${(parent.sex)!''}</td>
					                        <td>${(parent.relName)!''}</td>
					                        <td>${(parent.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>
					                        	[#if parent.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        	[/#if] 
					                        	[#if parent.status = 2]
					                        		<a class="btn btn-danger btn-xs">禁用</a>
					                        	[/#if] 
					                        </td>
					                        <td>
					                        [@shiro.hasPermission name ='admin:systemparent:edit']
					                        	[#if parent.status = 1]
					                        		<a class="btn btn-xs btn-danger forbidParent" data-id="${parent.id}" id="forbidParent" ><i class="fa fa-paste"></i> 禁用</a>
					                        	[/#if] 
					                        	[#if parent.status = 2]
					                        		<a class="btn btn-xs btn-primary startParent" data-id="${parent.id}" id="startParent"><i class="fa fa-paste"></i>启用</a>
					                        	[/#if] 
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${parent.id}" href="edit/${parent.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        [/@shiro.hasPermission]
					                        	
					                        	<a href="${base}/admin/parent/view/${parent.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
					                        	
					                         [@shiro.hasPermission name ='admin:systemparent:edit']
			                        			<a class="btn btn-xs btn-success btn-edit-loippi btn-edit-loippi-pwd" data-toggle="modal" data-id="${parent.id}" data-target="#myModal"><i class="fa fa-paste"></i>修改密码</a>
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
	
	
	<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
            	<div class="modal-content animated bounceInRight">
            	
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    	<h4 class="modal-title" id="myModalLabel">修改密码</h4>
                	</div>
                	
                	<form id="inputModalForm" class="form-horizontal" action="${base}/admin/parent/updatePwd.jhtml" method="post" >
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<input type="hidden" value="" id="parentId" name="parentId" />
			                    	<label>新密码</label><span style="color:#F00">(*)</span>
			                        <input type="password" name="filter_password" id="filter_password" placeholder="请输入新密码" class="form-control" >
			                        </br>
			                        <label>确认密码</label><span style="color:#F00">(*)</span>
			                        <input type="password" name="filter_repassword" id="filter_repassword" placeholder="请再次输入新密码" class="form-control"  >
			                        </br>  
			                    </div>
		                </div>
                
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-success">保存</button>
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
    	 //修改密码-传入parentId
    	 $(".btn-edit-loippi-pwd").click(function(){
			var id=$(this).attr('data-id');
			$("#parentId").val(id);
		
    });
    
    		//修改密码校验
    		$("#inputModalForm").validate({
                rules: {
                   filter_password: {
	                    minlength: 6,
	                    required: true
	                },
                   filter_repassword: {
	                    equalTo:'#filter_password',
	                    minlength: 6,
	                    required: true
	                }
	              },
    			  messages: {
                  filter_password:{
                       equalTo:"两次密码输入不一致"
                   }      
				}
    		});
    		
    		
    
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
			
         	//导出
			 $(".doExport").click(function(){
			    var filter_account=	$("#filter_account").val();
			    var filter_relName=	$("#filter_relName").val();
			    var filter_sDate=	$("#filter_sDate").val();
			    var filter_eDate=	$("#filter_eDate").val();
			    var filter_status=	$("#filter_status").val();

			 	window.open('exports.jhtml?filter_account='+filter_account
			 			+'&filter_relName='+filter_relName
			 			+'&filter_sDate='+filter_sDate
			 			+'&filter_eDate='+filter_eDate
			 			+'&filter_status='+filter_status
						,'_blank');
		    });   
		
			//启用
			$(".startParent").click(function(){
		    	var $this = $(this);
				 		$.ajax({
							url: "startParent.jhtml",
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
			$(".forbidParent").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定禁用?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "forbidParent.jhtml",
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
