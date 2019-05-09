<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增订单表</title>
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
                    	 <h5>新增订单表</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/order/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="get">
                       <input type="hidden" name="tallyType" value="2">
                            
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">帐号</label>
	                                <div class="col-sm-9">
	                                     <select class="chosen-select" name="uid" id="uid" >
							  <option   value="">请选择帐号</option>
							  	 [#list tParents as tParent] 
							  	  <option   value ="${tParent.id}">${tParent.nickName}(${tParent.account})</option>
							  	 [/#list]
							  	 </select>
	                                </div>
                       			</div>
                       			
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">孩子</label>
	                                <div class="col-sm-9">
	                                    <select name="stuId"  id="stuId"  class="chosen-select"  >
					                             <option value="" >请选择孩子</option>
										</select>
	                                </div>
                       			</div>
						
                        </div>
                         </div>
							<!--/row-->

                          <div class="form-group">
                       		<div class="row">
                       		
                       			<div class="col-sm-6">
                       			 		<label class="col-sm-3 control-label">支付金额</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="totalFee" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       		
                       		   	<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">联系人</label>
	                                <div class="col-sm-9">
	                                  <input type="text" name="unickname" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       		
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">支付方式</label>
	                                <div class="col-sm-9">
	                                  		            <select class="form-control" name="payType">
							  <option value="">请选择支付方式</option>
							  <option  value ="1">支付宝</option>
							   <option  value ="2">微信</option>
							   <option  value ="3">银联</option>
							   <option  value ="4">现金</option>
							   </select>
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
                       			 	<label class="col-sm-3 control-label">支付时间</label>
	                                <div class="col-sm-9">
	                                   	<div class="input-daterange input-group col-sm-12" >
					        		<input type="text" placeholder="请输入支付时间" name="payTime1" id="payTime1"  value=""  class="form-control datepicker1">
                            	</div>
	                                </div>
                       			</div>
                       		
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课程名称</label>
	                                <div class="col-sm-9">
	                                       <select class="chosen-select" name="courseId" >
							  <option   value="">请选择课程</option>
							  	 [#list courses as course] 
							  	  <option   value ="${course.id}">${course.name}</option>
							  	 [/#list]
							  	 </select>
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
                       			
                       			<!--
	                   			<div class="col-sm-6">
	                   			 	<label class="col-sm-3 control-label">交易流水号</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="serialNumber" class="form-control" maxlength="200" />
	                                </div>
	                   			</div>
	                   			-->
                       	
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
    
    	
    	$("#inputForm").validate({
    		submitHandler:function(form){
	    		var stuId = $("#stuId").val();
	              if(stuId==""){
	              art.warn('请先添加一个小孩！');
	              return;
	              }
              
              	form.submit();
            },
            
                rules: {
                },
                messages: {
				}
            });
    
    
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
       // 	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	
        	 $(".datepicker1").datetimepicker({  
                defaultDate: $('.datepicker1').val(),  
                dateFormat: "yy-mm-dd",  
                showSecond: true,  
                timeFormat: 'hh:mm',  
                stepHour: 1,  
                stepMinute: 1,  
                stepSecond: 1  
            });  
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    	//	$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
    	
    	
    	
    	//获取家长
    	 $("#uid").change(function () {
                var data = $(this).val();
                var stuId=$("#stuId");
                stuId.empty();
                  stuId.html("");
                  stuId.val('');
                  stuId.text('');
                if (data == null || data == '') {
                    return;
                }
    	
    	 //获取家长对应的小孩
	            $.get("${base}/admin/parent/getstuIdByUid.json?id=" + data, function (result) {
	         
	                if (result == null || result == ''|| result == []) {
	                 stuId.empty();
	              	stuId.html("");
	                stuId.append("<option  value=''>" + '请选择孩子' + "</option>");
	                }
	
	                if(result.length>0){
	                 stuId.append("<option  value=''>" + '请选择孩子' + "</option>");
	                for (var i = 0; i < result.length; i++) {
	                    stuId.append("<option  value=\"" + result[i].id + "\">" + result[i].nickname + "</option>");
	                }
	                }
	                $(".chosen-select").trigger("chosen:updated")
	            });
	      }); 
        	
        });
    </script>
</body>
</html>
