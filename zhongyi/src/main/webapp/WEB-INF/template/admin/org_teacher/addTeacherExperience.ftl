<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增任教经历</title>
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
                    	 <h5>新增任教经历</h5>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/org_teacher/saveTeacherExperience.jhtml" method="get">
                      	<input type="hidden" value="${teacherId}" name="teacherId"/>
                      	
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 		<label class="col-sm-3 control-label ">开始时间</label>
	                                 <div class="col-sm-9">
	                                    <div class="input-daterange input-group col-sm-12" id="datepicker">
					        				<input type="text" placeholder="年/月/日" name="start" id="start"  class="input-sm form-control  ">
					        			</div>
	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 		<label class="col-sm-3 control-label">结束时间</label>
	                                 <div class="col-sm-9">
	                                    <div class="input-daterange input-group col-sm-12" id="datepicker">
					        				<input type="text" placeholder="年/月/日" name="end" id="end"  class="input-sm form-control  ">
					        			</div>
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">任教经历</label>
	                                <div class="col-sm-9">
	                                    <textarea name="content" class="input-sm form-control "></textarea>
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
        	
        	//时间控件
    		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
        	
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
