<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - VIEW详情</title>
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
                    	 <h5>VIEW详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/activity_stu_view/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${activityStuView.}" name=""/>
                        
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.account}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">昵称</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.nickName}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">type=1课程id   type=2活动id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.targetId}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">集体课，第几期</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.term}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span></label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.id}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.stuId}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">家长id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.uid}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系电话</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.phone}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">备注</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.remark}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.stuName}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生性别</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.stuSex}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生头像</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.stuAvatar}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生生日</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.stuBirthday}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.createdTime}</p>
	                                </div>
                       			</div>
							</div></div>
	<!--/row-->
						
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动参赛号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.activityNumber}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动获奖情况</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${activityStuView.activityPrize}</p>
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
