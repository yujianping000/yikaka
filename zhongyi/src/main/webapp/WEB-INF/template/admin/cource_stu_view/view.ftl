<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 学员详情</title>
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
                    	 <h5>学员详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	 onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                         <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courceStuView.account}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">孩子头像</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">
	                                	<img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(courceStuView.stuAvatar)!''}">
	                                	</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">昵称</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courceStuView.nickName}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">出生日期</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(courceStuView.stuBirthday?string('yyyy-MM-dd'))!''}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系电话</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(courceStuView.phone)!''}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">报名时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(courceStuView.createdTime?string('yyyy-MM-dd'))!''}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">孩子姓名</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(courceStuView.stuName)!''}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">剩余课时</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courceStuView.classtimes}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       		
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">第几期</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courceStuView.term}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>课时信息</h3>
		
                            <div class="form-group">
                       		<div class="row">
                       		
                       			<div class="col-sm-6">
                       			<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                	
	                                			<table width="650" border="1">
  <tr>
    <td width="55">未开始</td>
    <td>
    
    [#list courseTime1 as appCourseTime]<span>${(appCourseTime.courseTimeString)!''}<span>　[/#list]
    
    </td>
  </tr>
  <tr>
    <td>待评价</td>
    <td>[#list courseTime2 as appCourseTime]<span>${(appCourseTime.courseTimeString)!''}<span>　[/#list]</td>
  </tr>
   <tr>
    <td>已结束</td>
    <td>[#list courseTime3 as appCourseTime]<span>${(appCourseTime.courseTimeString)!''}<span>　[/#list]</td>
  </tr>
  
   <tr>
    <td>请假</td>
    <td>[#list courseTime4 as appCourseTime]<span>${(appCourseTime.courseTimeString)!''}<span>　[/#list]</td>
  </tr>
   <tr>
    <td>旷课</td>
    <td>[#list courseTime5 as appCourseTime]<span>${(appCourseTime.courseTimeString)!''}<span>　[/#list]</td>
  </tr>
</table>	
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
			

						
                       		
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
