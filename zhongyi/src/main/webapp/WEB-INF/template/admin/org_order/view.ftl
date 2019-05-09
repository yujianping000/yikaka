<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 订单表详情</title>
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
                    	 <h5>订单详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/org_order/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${order.id}" name="id"/>
                        	
                        	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">订单编号</label>
		                                	<div class="col-sm-9">
		                                		<p class="form-control-static">${(order.bn)!''}</p>
		                                	</div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">下单时间</label>
		                                	<div class="col-sm-9">
		                                		<p class="form-control-static">${(order.createdTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
		                                	</div>
	                       			</div>
	                       			
								</div>
							</div>
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">下单账号</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(order.phone)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">支付时间</label>
		                                <div class="col-sm-9">
		                                 [#if order.status = 1 || order.status = 4]
		                                 	<p class="form-control-static">-</p>
		                                [#else]
		                                	<p class="form-control-static">${(order.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</p>
		                                [/#if]
		                                </div>
                       				</div>
                       				
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">课程名称</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(order.courseName)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">交易流水号</label>
		                                <div class="col-sm-9">
		                                [#if order.status = 1 || order.status = 4]
		                                		<p class="form-control-static">-</p>
		                                [#else]
		                                	<p class="form-control-static">${(order.serialNumber)!''}</p>
		                                [/#if]
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">订单金额（元）</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(order.totalFee)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">联系电话</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(order.phone)!''}</p>
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">状态</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">
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
						                        [#if order.status = 8]
						                        		已完成
						                        [/#if]  
		                                	
		                                	</p>
		                                </div>
	                       			</div>
	                       			
								</div>
							</div>
							
							<div class="ibox-content">
	                            <div class="form-group">
	                            <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>孩子信息</h3>
		                       		<div class="row">
		                       			<div class="col-sm-6">
		                       			 	<label class="col-sm-3 control-label">孩子姓名</label>
			                                <div class="col-sm-9">
			                                	<p class="form-control-static">${(order.stuName)!''}</p>
			                                </div>
		                       			</div>
		                       			
			                   			<div class="col-sm-6">
			                   			 	<label class="col-sm-3 control-label">出生日期</label>
			                                <div class="col-sm-9">
			                                	<p class="form-control-static">${(order.stuBirthday?string('yyyy-MM-dd'))!''}</p>
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
			                                	<p class="form-control-static">${(order.stuSex)!''}</p>
			                                </div>
		                       			</div>
		                       			
		                       			<div class="col-sm-6">
		                       			 	<label class="col-sm-3 control-label">爱好</label>
			                                <div class="col-sm-9">
			                                	<p class="form-control-static">${(order.stuHobby)!''}</p>
			                                </div>
		                       			</div>
									</div>
								</div>
								
								<!--/row-->
		                        <div class="form-group">
		                       		<div class="row">
		                       			<div class="col-sm-6">
		                       			 	<label class="col-sm-3 control-label">孩子头像</label>
			                                <div class="col-sm-9">
			                                	<p class="form-control-static">
			                                	<img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(order.stuAvatar)!''}">
			                                	</p>
			                                </div>
		                       			</div>
									</div>
								</div>
								
							</div>
							
						[#if order.status = 8]
							<div class="ibox-content">
							 	<div class="form-group">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>评价信息</h3>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>账号</th>
			                                    <th>昵称</th>
			                                    <th>评价内容</th>
			                                    <th>评价分数</th>
			                                    <th>评价时间</th>
			                                </tr>
			                            </thead>
			                            <tbody>
									        	 <tr>
							                        <td>${(order.phone)!''}</td>
						                       		<td>${(order.unickname)!''}</td>
							                        <td>${(order.rateContent)!''}</td>
							                        <td>${(order.rateScore)!''}</td>
							                        <td>${(order.rateTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
							                    </tr>
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								</div>
							 [/#if]
							
							<!--/row-->
							<div class="row">
                       		
                         <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                        </div>
	                    </div>
	                    </form>
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
	    	
	    	
	     });
	</script>
	
</body>
</html>
