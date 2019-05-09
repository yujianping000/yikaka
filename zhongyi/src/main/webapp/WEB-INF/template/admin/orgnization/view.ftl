<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 机构详情</title>
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
                    	 <h5>查看机构</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/orgnization/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${orgnization.id}" name="id"/>
                        	
                        	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">账号</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.account)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">封面图</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">
		                                		<img alt="image" class="img-circle"  style="height:50px;width:50px;" src="${(orgnization.image)!''}">
		                                	</p>
		                                </div>
	                       			</div>
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">机构名称</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.name)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">联系人</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.serviceName)!''}</p>
		                                </div>
	                       			</div>
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">用户评价</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.favStar)!''}</p>
		                                </div>
	                       			</div>
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">联系电话</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.servicePhone)!''}</p>
		                                </div>
	                       			</div>
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">学员/教师数量</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.studentCount)!''}/${(orgnization.teacherCount)!''}</p>
		                                </div>
	                       			</div>
	                       			
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">状态</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">
		                                		[#if orgnization.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        	[/#if] 
					                        	[#if orgnization.status = 2]
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
	                       			 	<label class="col-sm-3 control-label">机构电话</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${orgnization.telPhone}</p>
		                                </div>
	                       			</div>
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">手续费（%）</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${(orgnization.fee)!''}</p>
		                                </div>
	                       			</div>
								</div>
							</div>
							
							<!--/row-->
                            <div class="form-group">
                       			<div class="row">
                       				<div class="col-sm-6">
                       			 		<label class="col-sm-3 control-label">地区</label>
	                               	 	<div class="col-sm-9">
	                                		<p class="form-control-static">${(orgnization.province)!''}${(orgnization.city)!''}${(orgnization.area)!''}</p>
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
							<!--/row-->
                            <div class="form-group">
	                       		<div class="row">
	                       			<div class="col-sm-6">
	                       			 	<label class="col-sm-3 control-label">机构地址</label>
		                                <div class="col-sm-9">
		                                	<p class="form-control-static">${orgnization.location}</p>
		                                </div>
	                       			</div>
								</div>
							</div>
							
							<div class="ibox-content">
                  				<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>机构介绍</h3>
                  				 <div class="form-group">
	                       			<div class="row">
	                       				<div class="col-sm-6">
	                       			 		<label class="col-sm-3 control-label">机构描述</label>
		                                		<div class="col-sm-9">
						    						<textarea id="editor" name="description" class="editor">${orgnization.description}</textarea>
		                                		</div>
	                       				</div>
	                       			</div>
	                        	</div>
                  			</div>
							
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
	
	<script src="${base}/resources/js/plugins/jquery-qrcode/jquery.qrcode.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/plugins/jquery-qrcode/jquery.min.js"></script>
	
	<script>
	    jQuery(function(){
	    	jQuery('#qrcode').qrcode({
	    		 width   : 100,
    			 height  : 100,
    			  text   : "${(orgnization.uuid)!''}"
	    		});
		})
	</script>
	
</body>
</html>
