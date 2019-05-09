<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑老师认证</title>
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
                    	 <h5>编辑老师认证</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/teacher_auth/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/teacher_auth/update.jhtml" method="post">
                  		<input type="hidden" value="${teacherAuth.id}" name="id"/>
                       
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="orgId" class="form-control" maxlength="200" value="${(teacherAuth.orgId)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="200" value="${(teacherAuth.name)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="birthday" class="form-control" maxlength="200" value="${(teacherAuth.birthday?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">1-全职   2-兼职</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="type" class="form-control" maxlength="200" value="${(teacherAuth.type)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="sex" class="form-control" maxlength="200" value="${(teacherAuth.sex)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">省份证</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="idCard" class="form-control" maxlength="200" value="${(teacherAuth.idCard)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">教师证</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="teacherCard" class="form-control" maxlength="200" value="${(teacherAuth.teacherCard)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">学历证</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="educationCard" class="form-control" maxlength="200" value="${(teacherAuth.educationCard)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">资历证</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="qlCard" class="form-control" maxlength="200" value="${(teacherAuth.qlCard)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createTime" class="form-control" maxlength="200" value="${(teacherAuth.createTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="updateTime" class="form-control" maxlength="200" value="${(teacherAuth.updateTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">对应老师id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="teacherId" class="form-control" maxlength="200" value="${(teacherAuth.teacherId)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">1-通过  2-申请中  3-被拒绝</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="status" class="form-control" maxlength="200" value="${(teacherAuth.status)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">相关理由</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="reason" class="form-control" maxlength="200" value="${(teacherAuth.reason)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">1-已读   2-未读</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="isRead" class="form-control" maxlength="200" value="${(teacherAuth.isRead)!''}"/>
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
