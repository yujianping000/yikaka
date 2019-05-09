<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增课时</title>
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
                    	 <h5>新增课时</h5>
                    	 <div class="ibox-tools">
                    	<button type="button" class="btn btn-xs btn-default" onclick="window.history.back();">返回</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/course_stu_time/save.jhtml" method="get">
                         <input type="hidden" value="${orderId}" name="orderId"/>
                        
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课日期<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
                                <div class="input-daterange input-group" id="datepicker">
                                <input type="text" class="input-sm form-control" name="start" />
                                <span class="input-group-addon">到</span>
                                <input type="text" class="input-sm form-control" name="end" />
                                  </div>	
                                   </div>
                       			</div>
							</div>
                        </div>
                        
                        
                        
							<!--/row-->
							<div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">选择日期<span style="color:#F00">(*)</span></label>
	                                <div class="col-sm-9">
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week1"   value="1" >周一</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week2"   value="2" >周二</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week3"   value="3" >周三</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week4"   value="4" >周四</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week5"   value="5" >周五</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week6"   value="6" >周六</label>　
<label class="control-label"> <input class="i-checks"  type="checkbox" name="week7"   value="7" >周日</label>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
							
								<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">上课日期<span style="color:#F00">(*)</span></label>
	                            <div class="col-sm-1">
                                    <label class="control-label"> <input class="i-checks"  type="checkbox" name="timeRedio1"   value="1" ></label>　
                                  </div>	
                                  
                                   <div class="col-sm-5">	                                
                       <div class="input-group">
                           <span class="input-group clockpicker">
                                <input type="text" class="input-sm form-control" name="time1"/></span>
                              <span class="input-group-addon">到</span>
                           <span class="input-group clockpicker2">
                               <input type="text" class="input-sm form-control" name="time2" />
                            </span>
                        </div>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                     
                        	<!--/row-->
							 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                            <div class="col-sm-1">
                                    <label class="control-label"> <input class="i-checks"  type="checkbox"  name="timeRedio2"   value="1" ></label>　
                                  </div>	
                                  
                                   <div class="col-sm-5">	                                
                       <div class="input-group">
                           <span class="input-group clockpicker">
                                <input type="text" class="input-sm form-control" name="time3"/></span>
                              <span class="input-group-addon">到</span>
                           <span class="input-group clockpicker2">
                               <input type="text" class="input-sm form-control"  name="time4" />
                            </span>
                        </div>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                     	<!--/row-->
                     					 <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                            <div class="col-sm-1">
                                    <label class="control-label"> <input class="i-checks"  type="checkbox"  name="timeRedio3"   value="1" ></label>　
                                  </div>	
                                  
                                   <div class="col-sm-5">	                                
                       <div class="input-group">
                           <span class="input-group clockpicker">
                                <input type="text" class="input-sm form-control" name="time5" /></span>
                              <span class="input-group-addon">到</span>
                           <span class="input-group clockpicker2">
                               <input type="text" class="input-sm form-control"  name="time6"  />
                            </span>
                        </div>
                                  </div>	
                                   </div>
                       			</div>
                       			
							</div>
                     	<!--/row-->
                     	        <div class="form-group">
                       		<div class="row">
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">节假日排课</label>
	                                <div class="col-sm-9">
                                       <select name="holiday"  id="holiday"  class="chosen-select"  >
					                             <option value="1" >关闭</option>
					                             <option value="2" >开启</option>
										</select>

	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                     	
							
                        </div>
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
	 

		<script src="${base}/resources/js/plugins/clockpicker/clockpicker.js"></script>
			<script src="${base}/resources/js/plugins/clockpicker/clockpicker.js"></script>
			<link  rel="stylesheet" type="text/css"  href="${base}/resources/css/plugins/clockpicker/clockpicker.css">

    <script>
    
              	$("#inputForm").validate({
              		  submitHandler:function(form){
	             
	           var i=0;

    $.ajax({
        url: "${base}/admin/course/checkTime3.jhtml",
        data:$('#inputForm').serialize(),
        type: 'get',
        dataType: 'json',
        success: function(message) {
            console.log(message.status);
          console.log(message);
          if(message.status==1){
            i=1;
             art.warn('主讲教师在'+message.message+"时间有课上，请重新选择上课时间！");
          }
          
          if(message.status==3){
            i=1;
             art.warn('课室在'+message.message+"时间有课上，请重新选择上课时间！");
          }
        },
            async: false
    });
      if(i!=0){
      return ;
      }
      
            form.submit();
        } ,
              	
              	
                rules: {
                   name: {
						required: true
					},
                price: {
						maxlength: 8,
						number: true
					},
							  periodsDesc: {
						required: true
					},
					  start: {
						required: true
					}, end: {
						required: true
					},
						  periods: {
						required: true
					}
                },
                messages: {
				}
            });
    
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
        	        	$('.i-checks').iCheck({ checkboxClass: 'icheckbox_square-green',  radioClass: 'iradio_square-green',  });
        	
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        		$(".clockpicker").clockpicker({
 
});
        			$(".clockpicker2").clockpicker({
  
});
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});

            
        });
    </script>
</body>
</html>
