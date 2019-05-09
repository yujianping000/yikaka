<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑活动</title>
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
                    	 <h5>编辑活动</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/activity/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal"  action="${base}/admin/activity/update.jhtml" method="post">
                  		
                                 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>基本信息</h3>
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动名称<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                <p class="form-control-static">${activity.name}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动名额</label>
	                                <div class="col-sm-9">
	                                 <p class="form-control-static">${activity.totalSum}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">费用（元）<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                <p class="form-control-static">${activity.fee}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">年龄范围<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                <p class="form-control-static">${activity.ageRank}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">主办单位<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                 <p class="form-control-static">${activity.company}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">封面图<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                  
		<img style="height:150px;width: 300px; "  id="imageImg" name='imageImg' src="${activity.image}"/>


	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">报名时间<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">

	<p class="form-control-static">${(activity.startTime?string('yyyy-MM-dd'))!''}~${(activity.endTime?string('yyyy-MM-dd'))!''}</p>
                            	</div>	                          
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                             <p class="form-control-static">
	                             [#if activity.status == "0"]未发布[/#if]
                                  [#if activity.status == "1"]报名中[/#if] 
                                   [#if activity.status == "2"]进行中[/#if]  
                                    [#if activity.status == "3"]未开始[/#if] 
                                   [#if activity.status == "3"]未开始[/#if]
                                   </p> 
						   
                       			</div>
                       			</div>
							</div>
                        </div>
                        <!--/row-->
						 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">显示报名人数<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                <p class="form-control-static">${activity.already}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">实际报名人数</label>
	                                <div class="col-sm-9">
	                                 <p class="form-control-static">${activity.already2}</p>
	                               
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->	
							
					   <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">活动地点<span style="color:#F00">(*)</label>
	                                <div class="col-sm-9">
	                                 <p class="form-control-static">${activity.location}</p>
	                                </div>
                       			</div>
							</div>
                        </div>
                        <!--/row-->
	
						 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>活动介绍</h3>
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
 <textarea id="editor" name="description" class="editor">${activity.description}</textarea>
 </div>
                       			</div>
                       		
							</div>
                        </div>
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

    
    <script>
    
                
    
    
    
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
