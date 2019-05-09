
<!DOCTYPE html>
<html>
<head>
﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 家长详情</title>
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
                    	 <h5>查看家长</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/parent/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>  
                    
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${parent.id}" name="id" id="id"/>
                        
                        	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.account)!''}</p>
	                                </div>
                       			</div>
                       			
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">等级</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.level)!''}</p>
	                                </div>
	                   			</div>
							</div>
						</div>
						
						<!--/row-->
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">昵称</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.nickName)!''}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">成长值</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.growUpValue)!''}</p>
	                                </div>
                       			</div>
							</div>
						</div>
						
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">头像</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">
	                                		<img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(parent.avatar)!''}">
	                                	</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">
	                                		[#if parent.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        [/#if] 
					                        [#if parent.status = 2]
					                        		<a class="btn btn-danger btn-xs">禁用</a>
					                        [/#if] 
	                                	</p>
	                                </div>
                       			</div>
							</div>
						</div>
						
							<!--/row-->
                        <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">真实姓名</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.relName)!''}</p>
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">注册时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
	                                </div>
	                   			</div>
							</div>
						</div>
						
						<!--/row-->
                        <div class="form-group">
	                   		<div class="row">
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">性别</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(parent.sex)!''}</p>
	                                </div>
	                   			</div>
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">二维码</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static" id="qrcode"></p>
	                                	
	                                </div>
	                   			</div>
							</div>
						</div>
						
						<div class="ibox-content">
					 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>孩子资料</h3><br/>
							 <div class="dataTables_wrapper form-inline">
						   		<style>
									th,td {text-align:center}
							  	</style>
							  	
		                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
		                            <thead>
		                                <tr>
		                                    <th>姓名</th>
		                                    <th>孩子头像</th>
		                                    <th>性别</th>
		                                    <th>出生日期</th>
		                                    <th>爱好</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            	 [#list studentList as student] 
								        	 <tr>
						                        <td>${(student.nickname)!''}</td>
						                        <td>
						                        	<img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(student.avatar)!''}">
						                        </td>
						                        <td>${(student.sex)!''}</td>
						                        <td>${(student.birtchday?string('yyyy-MM-dd'))!''}</td>
						                       	<td>${(student.hobby)!''}</td>
						                    </tr>
								        [/#list] 
		                            </tbody>
		                        </table>
		                       </div>
							</div>
						
						
						<div class="ibox-content">
					 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>订单信息</h3>
					 	 	<div class="ibox-tools">
					 	 	[@shiro.hasPermission name ='admin:systemparent:edit']
                        		<a class="btn btn-outline btn-success btn-xs orderExports"><i class="fa fa-file-excel-o"></i>导出订单</a>
                        	 [/@shiro.hasPermission]
	                    	</div>
							 <div class="dataTables_wrapper form-inline">
						   		<style>
									th,td {text-align:center}
							  	</style>
							  	
		                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
		                            <thead>
		                                <tr>
		                                    <th>订单编号</th>
		                                    <th>账号</th>
		                                    <th>姓名</th>
		                                    <th>课程名称</th>
		                                    <th>订单金额（元）</th>
		                                    <th>下单时间</th>
		                                    <th>支付时间</th>
		                                    <th>联系电话</th>
		                                    <th>状态</th>
		                                    <th>操作</th>
		                                </tr>
		                            </thead>
		                            <tbody>
		                            	 [#list page.content as order] 
								        	 <tr>
						                        <td>${(order.bn)!''}</td>
						                        <td>${(order.phone)!''}</td>
						                        <td>${(order.unickname)!''}</td>
						                        <td>${(order.courseName)!''}</td>
						                        <td>${(order.totalFee)!''}</td>
						                        <td>${(order.createdTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
						                       	<td>${(order.payTime?string('yyyy-MM-dd HH:mm:ss'))!'-'}</td>
					                       		<td>${(order.phone)!''}</td>
				                       			<td>
				                       				[#if order.status = 1]
					                        			待付款
					                       	 		[/#if] 
					                        		[#if order.status = 2]
					                        			已付款
					                        		[/#if] 
					                        		[#if order.status = 3]
					                        			待评价
					                        		[/#if] 
					                        		[#if order.status = 4]
					                        			已取消
					                        		[/#if] 
					                        		[#if order.status = 5]
					                        			客户端删除
					                        		[/#if] 
					                        		[#if order.status = 8]
					                        			已完成
					                        		[/#if] 
				                       			</td>
			                       				<td>
			                       					[#if order.type = 1]
			                       						<a href="${base}/admin/course_order/view/${order.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
			                       					[/#if]
			                       					
			                       					[#if order.type = 2]
			                       						<a href="${base}/admin/activity_order/view/${order.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
			                       					[/#if]
			                       				</td>
						                    </tr>
								        [/#list] 
		                            </tbody>
		                        </table>
		                        
		                       </div>
							</div>
							
	                    </form>
	                    <!--/row-->
						<div class="row">
                       		
                         <div class="hr-line-dashed"></div>
	                     <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                        </div>
	                    </div>
	                    
                  	</div>
                </div>
            </div>
        </div>
    </div>
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
		<script src="${base}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- END Script -->
	
	<script src="${base}/resources/js/plugins/jquery-qrcode/jquery.qrcode.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/plugins/jquery-qrcode/jquery.min.js"></script>
	
	<script>
	    jQuery(function(){
	    	jQuery('#qrcode').qrcode({
	    		 width   : 100,
    			 height  : 100,
    			  text   : "${(parent.uuid)!''}"
	    		});
		})
	</script>
	
	<script>
		 $(document).ready(function () {
	    	[@flash_message /]
	    
		    //导出订单列表
			 $(".orderExports").click(function(){
			 	var id =$("#id").val();
			 	
			 	window.open('orderExports.jhtml?id='+id
			 			,'_blank');
		    });   
	    	
	   	});
			
	</script>
</body>
</html>
