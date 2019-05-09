
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
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
                    	 <h5>编辑角色</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='listorg.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content" >

                  	
                  		<form id="inputForm" class="form-horizontal" action="saveorg.jhtml" method="post">
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>名称</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="name" id="name" class="form-control">
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">描述</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="description" id="description" class="form-control">
	                                </div>
                       			</div>
                       		</div>
                        </div>
                        
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>权限设置</label>
	                                <div class="col-sm-9">
	                                  <table class="table table-bordered table-hover dataTables-example" style="width:300px">
							        	 <tr>
						                       	<td></td>
						                        <td>查看</td>
						                        <td>编辑</td>
						                    </tr>
						                    
						                <tr>
						                 <td>管理员管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="202" id="user11" ></td>
						                        <td><input type="checkbox" class="i-checks" name="ids"  value="203" id="user21" ></td>
						                </tr>  
						                 <tr> 
						                   <td>角色管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="204" id="role1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="205" id="role2" ></td>
						                </tr> 
						                
						                
						                
						                  <tr>
						                        <td>意见反馈管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="206" id="feedback1" ></td>
						                        <td>-</td>
						           .
						                </tr>
						                    
							            <tr>
						                        <td>消息推送</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="207" id="manager1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="208" id="manager2" ></td>
						                </tr>
						                
						                
						               
						                
						                 <tr>
						                        <td>教师管理</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="212" id="teacher1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="213" id="teacher2" ></td>
						                 </tr>
						                 
						               
						                 
						                 <tr>
						                        <td>课程管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="214" id="course1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="215" id="course2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>课室管理</td>
						                        <td><input type="checkbox" class="i-checks" name="ids"  value="216" id="classroom1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="217" id="classroom2" ></td>
						                 </tr>
						                   
						                     <tr>
						                        <td>试课申请管理</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="218" id="book1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="219" id="book2" ></td>
						                 </tr>    
						                 
						                    <tr>
						                        <td>调班申请管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="220" id="change1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="221" id="change2" ></td>
						                 </tr>    
						                    
						                   <tr>
						                        <td>补课申请管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="222" id="makeup1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="223" id="makeup2" ></td>
						                 </tr>  
						                    
						                  <tr>
						                        <td>请假申请管理</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="224" id="leave1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="225" id="leave2" ></td>
						                 </tr>  
						                      
						                    
						                 <tr>
						                        <td>订单管理</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="226" id="order1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="227" id="order2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>离职审核管理</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="228" id="leavejob1" ></td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="229" id="leavejob2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>入职审核管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="230" id="entry1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="231" id="entry2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>教室人才库管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="232" id="talentpool1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="233" id="talentpool2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>收入管理</td>
						                        <td><input type="checkbox" class="i-checks"  name="ids"  value="234" id="in1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="235" id="in2" ></td>
						                 </tr>
						                 
						               <tr>
						                        <td>支出管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="236" id="out1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="237" id="out2" ></td>
						                 </tr>
						                 
						                 
						                   <tr>
						                        <td>提现管理</td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="238" id="cash1" ></td>
						                        <td><input type="checkbox" class="i-checks"   name="ids"  value="239" id="cash2" ></td>
						                 </tr>  
						                 
						                  
						                 
                                     </table>
                  	
	                                </div>
                       			</div>
                       		
                       		</div>
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
    <script>
    
        $(document).ready(function () {
        	[@flash_message /]
        	
        	$('.i-checks').iCheck({ checkboxClass: 'icheckbox_square-green',  radioClass: 'iradio_square-green',  });
        	
        		//管理员管理
        	$("#user21").on('ifChanged',function(){
        	 $("#user11").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#user11").on('ifUnchecked',function(){
        	 $("#user21").iCheck('uncheck');  
           });
           
           
           //角色管理
        	$("#role2").on('ifChanged',function(){
        	 $("#role1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#role1").on('ifUnchecked',function(){
        	 $("#role2").iCheck('uncheck');  
           });
        	
        	
        	//消息推送
        	$("#manager2").on('ifChanged',function(){
        	 $("#manager1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#manager1").on('ifUnchecked',function(){
        	 $("#manager2").iCheck('uncheck');  
           });
           
          
           	$("#teacher2").on('ifChanged',function(){
        	 $("#teacher1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#teacher1").on('ifUnchecked',function(){
        	 $("#teacher2").iCheck('uncheck');  
           });
           
           	$("#course2").on('ifChanged',function(){
        	 $("#course1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#course1").on('ifUnchecked',function(){
        	 $("#course2").iCheck('uncheck');  
           });
        	

        	$("#classroom2").on('ifChanged',function(){
        	 $("#classroom1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#classroom1").on('ifUnchecked',function(){
        	 $("#classroom2").iCheck('uncheck');  
           });
           
               	$("#book2").on('ifChanged',function(){
        	 $("#book1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#book1").on('ifUnchecked',function(){
        	 $("#book2").iCheck('uncheck');  
           });
           
                    
               	$("#change2").on('ifChanged',function(){
        	 $("#change1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#change1").on('ifUnchecked',function(){
        	 $("#change2").iCheck('uncheck');  
           });  
           
              	$("#leave2").on('ifChanged',function(){
        	 $("#leave1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#leave1").on('ifUnchecked',function(){
        	 $("#leave2").iCheck('uncheck');  
           });  
           
             	$("#makeup2").on('ifChanged',function(){
        	 $("#makeup1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#makeup1").on('ifUnchecked',function(){
        	 $("#makeup2").iCheck('uncheck');  
           }); 
           
                	$("#order2").on('ifChanged',function(){
        	 $("#order1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#order1").on('ifUnchecked',function(){
        	 $("#order2").iCheck('uncheck');  
           }); 
           
           
                	$("#leavejob2").on('ifChanged',function(){
        	 $("#leavejob1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#leavejob1").on('ifUnchecked',function(){
        	 $("#leavejob2").iCheck('uncheck');  
           }); 
           
               	$("#entry2").on('ifChanged',function(){
        	 $("#entry1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#entry1").on('ifUnchecked',function(){
        	 $("#entry2").iCheck('uncheck');  
           }); 
           
            	$("#talentpool2").on('ifChanged',function(){
        	 $("#talentpool1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#talentpool1").on('ifUnchecked',function(){
        	 $("#talentpool2").iCheck('uncheck');  
           }); 
           
           
           
           
        	   $("#in2").on('ifChanged',function(){
        	 $("#in1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#in1").on('ifUnchecked',function(){
        	 $("#in2").iCheck('uncheck');  
           });	
        	
        	
        		   $("#out2").on('ifChanged',function(){
        	 $("#out1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#out1").on('ifUnchecked',function(){
        	 $("#out2").iCheck('uncheck');  
           });	
           

            		   $("#cash2").on('ifChanged',function(){
        	 $("#cash1").iCheck($(this).is(":checked") == true ? 'check':'uncheck');  
           });
        	 
           $("#cash1").on('ifUnchecked',function(){
        	 $("#cash2").iCheck('uncheck');  
           });
           
           

        	jQuery("#inputForm").validate({
                rules: {
                   name: {
						required: true,
						pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
						minlength: 2,
						maxlength: 20
					}
                },
                messages: {
				}
            });
        });
        
    </script>
</body>

</html>
