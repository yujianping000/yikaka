
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 系统信息</title>
    <meta name="keywords" content="${setting.siteName}">
  <meta name="description" content="${setting.siteName}">

    <link href="${base}/resources/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${base}/resources/css/font-awesome.min.css?v=4.3.0" rel="stylesheet">
    <link href="${base}/resources/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="${base}/resources/css/animate.min.css" rel="stylesheet">
    <link href="${base}/resources/css/style.min.css?v=3.0.0" rel="stylesheet">
    <!-- iCheck -->
    <link href="${base}/resources/css/plugins/iCheck/custom.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="${base}/resources/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${base}/resources/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    
	<style>
		html, body, div, span, input, p,h1, h2, h3, h4, h5, h6, blockquote, a, abbr, acronym, address, strong, b, u, i, center,dl, dt, dd, ol, ul,td,tr {
		    font-family:"Microsoft Yahei","微软雅黑","宋体", "Simsun", "Open Sans";
		}
	</style>
</head>

<body class="gray-bg">
 	 <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                	
                    <div class="ibox-title">
                    	 <h5>首页 </h5>
	                	 <div class="ibox-tools">
                	 		[#if user.type = 2 || user.type = 3] 
		                	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="orgManager.jhtml"><i class="fa fa-paste"></i> 机构信息管理</a>
		                	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="orgQrcode.jhtml"><i class="fa fa-paste"></i> 机构二维码</a>
		                	[/#if]
	                     </div>
                    </div>
                    <div class="ibox-content">
                   		<div class="row">
                   			[#if user.type = 1]
	                   			<div class="col-sm-6">
	                       			 	<li class="adminid">${(user.username)!''}</li><br/>
	                       			 	您好，欢迎来到中艺平台管理中心
	                       		</div>
                   			[/#if]
               				[#if user.type = 2 || user.type = 3] 
               				
               					<div class="ibox-content">
					 	 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>今天课时</h3>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>课程名称</th>
			                                    <th>主讲教师</th>
			                                    <th>代课教师</th>
			                                    <th>上课时间</th>
			                                    <th>调课时间</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                [#list todaycourseTimeList as todaycourseTime] 
								        	 <tr>
							                        <td>${(todaycourseTime.courseName)!''}</td>
							                        <td>${(todaycourseTime.teacherName)!''}</td>
							                        <td>${(todaycourseTime.substituteName)!'-'}</td>
							                        
							                        <td>
							                        	${(todaycourseTime.courseTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(todaycourseTime.courseTime1)!''}~${(todaycourseTime.courseTime2)!''}
							                        </td>
							                        <td>
							                        	[#if todaycourseTime.changetimeType=2]
							                        		${(todaycourseTime.oldTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(todaycourseTime.oldTime1)!''}~${(todaycourseTime.oldTime2)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                        </td>
							                      <td>
							                        	<a href="${base}/admin/common/substitute/${todaycourseTime.courseId}/${todaycourseTime.courseTimeId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>代课 </a>
							                        	
							                        	<a href="${base}/admin/common/courseChangeTime/${todaycourseTime.courseId}/${todaycourseTime.courseTimeId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>调课 </a>
							                        	
							                        </td>
						                    	<tr>
					                        [/#list]
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								
								
								<div class="ibox-content">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>明天课时</h3>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>课程名称</th>
			                                    <th>主讲教师</th>
			                                    <th>代课教师</th>
			                                    <th>上课时间</th>
			                                    <th>调课时间</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                  [#list tomorrowcourseTimeList as tomorrowcourseTime] 
								        	 <tr>
							                        <td>${(tomorrowcourseTime.courseName)!''}</td>
							                        <td>${(tomorrowcourseTime.teacherName)!''}</td>
							                        <td>${(tomorrowcourseTime.substituteName)!'-'}</td>
							                        
							                        <td>
							                        	${(tomorrowcourseTime.courseTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(tomorrowcourseTime.courseTime1)!''}~${(tomorrowcourseTime.courseTime2)!''}
							                        </td>
							                        <td>
						                        		[#if tomorrowcourseTime.changetimeType=2]
							                        		${(tomorrowcourseTime.oldTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(tomorrowcourseTime.oldTime1)!''}~${(tomorrowcourseTime.oldTime2)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                        </td>
							                        <td>
							                        	<a href="${base}/admin/common/substitute/${tomorrowcourseTime.courseId}/${tomorrowcourseTime.courseTimeId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>代课 </a>
							                        	
							                        	<a href="${base}/admin/common/courseChangeTime/${tomorrowcourseTime.courseId}/${tomorrowcourseTime.courseTimeId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>调课  </a>
							                        	
							                        </td>
						                    	<tr>
					                        [/#list]
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								
								<div class="ibox-content">
						 	 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>后天课时</h3>
								 <div class="dataTables_wrapper form-inline">
							   		<style>
										th,td {text-align:center}
								  	</style>
								  	
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                    <th>课程名称</th>
			                                    <th>主讲教师</th>
			                                    <th>代课教师</th>
			                                    <th>上课时间</th>
			                                    <th>调课时间</th>
			                                    <th>操作</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                 [#list aftertomorrowcourseTimeList as aftertomorrowcourseTime] 
								        	 <tr>
							                        <td>${(aftertomorrowcourseTime.courseName)!''}</td>
							                        <td>${(aftertomorrowcourseTime.teacherName)!''}</td>
							                        <td>${(aftertomorrowcourseTime.substituteName)!'-'}</td>
							                        
							                        <td>
							                        	${(aftertomorrowcourseTime.courseTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(aftertomorrowcourseTime.courseTime1)!''}~${(aftertomorrowcourseTime.courseTime2)!''}
							                        </td>
							                        <td>
							                        	[#if aftertomorrowcourseTime.changetimeType=2]
							                        		${(aftertomorrowcourseTime.oldTime?string('yyyy年MM月dd日'))!''}&nbsp;&nbsp;${(aftertomorrowcourseTime.oldTime1)!''}~${(aftertomorrowcourseTime.oldTime2)!''}
							                        	[#else]
							                        		-
							                        	[/#if]
							                        </td>
							                       <td>
							                        	<a href="${base}/admin/common/substitute/${aftertomorrowcourseTime.courseId}/${aftertomorrowcourseTime.courseTimeId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>代课 </a>
							                        	
							                        	<a href="${base}/admin/common/courseChangeTime/${aftertomorrowcourseTime.courseId}/${aftertomorrowcourseTime.courseTimeId}.jhtml" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>调课  </a>
							                        	
							                        </td>
						                    	<tr>
					                        [/#list]
			                            </tbody>
			                        </table>
			                       </div>
								</div>
								
                   			[/#if]
                		</div>
            		</div>
       			</div>
    		</div>
    	</div>
    </div>
	
    <!-- 全局js -->
    <script src="${base}/resources/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/resources/js/bootstrap.min.js?v=3.4.0"></script>
     <!-- Peity -->
    <script src="${base}/resources/js/plugins/peity/jquery.peity.min.js"></script>
    <!-- 自定义js -->
    <script src="${base}/resources/js/content.min.js?v=1.0.0"></script>
    <!-- iCheck -->
    <script src="${base}/resources/js/plugins/iCheck/icheck.min.js"></script>
    <!-- layer javascript -->
    <script src="${base}/resources/js/plugins/layer/layer.min.js"></script>
    <script src="${base}/resources/js/plugins/bootbox/bootbox.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/admin/common.js"></script>
    
</body>

</html>
