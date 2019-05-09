<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑课程教学时间评论</title>
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
                    	 <h5>编辑课程教学时间评论</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/course_time_comment/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/course_time_comment/update.jhtml" method="post">
                  		<input type="hidden" value="${courseTimeComment.id}" name="id"/>
                       
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">评论用户</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="uid" class="form-control" maxlength="200" value="${(courseTimeComment.uid)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">星星</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="favStar" class="form-control" maxlength="200" value="${(courseTimeComment.favStar)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">评论时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createDate" class="form-control" maxlength="200" value="${(courseTimeComment.createDate?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">评价内容</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="content" class="form-control" maxlength="200" value="${(courseTimeComment.content)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">标签</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="mark" class="form-control" maxlength="200" value="${(courseTimeComment.mark)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="courseId" class="form-control" maxlength="200" value="${(courseTimeComment.courseId)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">机构id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="orgId" class="form-control" maxlength="200" value="${(courseTimeComment.orgId)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="teacherId" class="form-control" maxlength="200" value="${(courseTimeComment.teacherId)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">头像</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="studentId" class="form-control" maxlength="200" value="${(courseTimeComment.studentId)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">头像</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="avatar" class="form-control" maxlength="200" value="${(courseTimeComment.avatar)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">评论人</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="200" value="${(courseTimeComment.name)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程教学时间id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="timeId" class="form-control" maxlength="200" value="${(courseTimeComment.timeId)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程上课时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="courseTime" class="form-control" maxlength="200" value="${(courseTimeComment.courseTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间1</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="courseTime1" class="form-control" maxlength="200" value="${(courseTimeComment.courseTime1)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间2</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="courseTime2" class="form-control" maxlength="200" value="${(courseTimeComment.courseTime2)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课时间-用于排序</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="courseTime3" class="form-control" maxlength="200" value="${(courseTimeComment.courseTime3)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                       		
                       
                         <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                            <button class="btn btn-success" type="submit">保存内容</button>
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
        	$(".chosen-select").chosen({width:"100%"}); 
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        	$("#inputForm").validate({
               rules: {
                },
                messages: {
				}
            });
        });
    </script>
</body>
</html>
