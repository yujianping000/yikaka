<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 学生上课时间安排详情</title>
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
                    	 <h5>学生上课时间安排详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/course_stu_time/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${courseStuTime.id}" name="id"/>
                        
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程上课时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseTime}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.createTime}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseId}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间1</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseTime1}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间2</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseTime2}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间-用于排序</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseTime3}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">下课时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseTime4}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.studentId}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程时间id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.courseTimeId}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">订单id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.orderId}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">1 表示未上课  2 表示已上了课</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${courseStuTime.status}</p>
	                                </div>
                       			</div>
							</div></div>
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
</body>
</html>
