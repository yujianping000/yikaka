<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增VIEW</title>
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
                    	 <h5>新增VIEW</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/activity_stu_view/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="post">
                      
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">账号</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="account" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">昵称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="nickName" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">type=1课程id   type=2活动id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="targetId" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">集体课，第几期</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="term" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="id" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="stuId" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">家长id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="uid" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系电话</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="phone" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">备注</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="remark" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="stuName" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生性别</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="stuSex" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生头像</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="stuAvatar" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学生生日</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="stuBirthday" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createdTime" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动参赛号</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="activityNumber" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动获奖情况</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="activityPrize" class="form-control" maxlength="200" />
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
                	 id: {
						required: true
					},
                },
                messages: {
				}
            });
        });
    </script>
</body>
</html>
