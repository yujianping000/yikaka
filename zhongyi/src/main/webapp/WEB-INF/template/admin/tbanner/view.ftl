<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 横幅表详情</title>
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
                    	 <h5>横幅表详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/tbanner/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  			<form id="inputForm" class="form-horizontal" action="save.jhtml" method="post">
                        
                        	
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">广告名称</label>
	                                <div class="col-sm-9">
	                                <p class="form-control-static">${tBanner.title}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
                        
                       
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">链接</label>
	                                <div class="col-sm-9">
	                  
	                                          <p class="form-control-static">${tBanner.linkUrl}</p>
	                                </div>
                       			</div>
                       		
							</div>
                        </div>
							
							   <div class="form-group">
                       		<div class="row">
                       				<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">轮播次序</label>
	                                <div class="col-sm-9">
	                                  <p class="form-control-static">${tBanner.sort}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							
						
                        
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">图片</label>
	                                <div class="col-sm-9">
	                                	<table border="0">
										  <tr>
										  	<td>
										    	<div style="float: left;">
													<img style="height:150px;width: 150px;" class="img-circle" id="defaultImage" name="defaultImage" src="${tBanner.imgUrl}" />
												</div>
										 	<td>
										 </tr>

										</table>
										
										<input type="hidden"  name="imgUrl" id="imgUrl" />
	                                </div>
                       		  </div>
                       			      </div>
                       		  </div>
						</div>
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
</body>
</html>
