	﻿
	<!DOCTYPE html>
	<html>
	
	<head>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	
	<title>${setting.siteName} - 机构评价</title>
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
	                	 <h5>查看评价 </h5>
	                	 <div class="ibox-tools">
	                	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
	                	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
	                </div>
	                
	                <div class="ibox-content">
	                <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>机构评价</h3><br/>
	                	<div class="dataTables_wrapper form-inline">
					   		<style>
								th,td {text-align:center}
						  	</style>
	                	
	            	 		  <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                             <thead>
			                                <tr>
			                                    <th>姓名</th>
			                                    <th>头像</th>
			                                    <th>评价分数</th>
			                                    <th>评价标签</th>
			                                	<th style="width:19%">评价内容</th>
			                                    <th>评价时间</th>
			                                </tr>
	                            		</thead>
			                            <tbody>
	                    	 		[#list page.content as teacherComment] 
							        	 <tr>
					                        <td>${(teacherComment.teacherName)!''}</td>
				                         	<td> <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(teacherComment.avatar)!''}"> </td>
				                         	<td>
					                        	[#if teacherComment.status = 1]
					                        	[#list 0..teacherComment.favStar as favStar ]
					                        		<span style="font-size:20px; color:#F00">★</span>
					                        	[/#list]	
					                        	[/#if]	
					                        	[#if teacherComment.status = 2]-[/#if]
					                        </td>
					                        <td>${(teacherComment.mark)!''}</td>
					                        <td>${(teacherComment.content)!''}</td>
					                        <td>${(teacherComment.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
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
	                        		<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                       		 </div>
	                		</div>
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
	
	<script>
	    $(document).ready(function () {
	    	[@flash_message /]
	 });
	    
	</script>
	</body>
	
	</html>
