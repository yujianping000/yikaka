<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑教室</title>
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
                    	 <h5>编辑教室</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/course_room/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/course_room/update.jhtml" method="post">
                  		<input type="hidden" value="${courseRoom.id}" name="id"/>
                       
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">教室名称<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" class="form-control" maxlength="200" value="${(courseRoom.name)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">类型 <span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
	                                    <select name="type" id="type" data-placeholder="类型..." class="chosen-select"  style="width:350px;" >
                                  <option   value="1">一对一</option>
                                  <option   value="2">集体课</option>   		
						              </select>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">容量人数</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="peoples" class="form-control" maxlength="2" value="${(courseRoom.peoples)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">面积</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="area" class="form-control" maxlength="8" value="${(courseRoom.area)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">备注</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="remark" class="form-control" maxlength="200" value="${(courseRoom.remark)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                        
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
    
            	$("#inputForm").validate({
                rules: {
                   name: {
						required: true
					},
                peoples: {
						maxlength: 3,
						digits: true
					},
								area: {
										maxlength: 8,
						number: true
								}
                },
                messages: {
				}
            });
    
    
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        
        });
    </script>
</body>
</html>
