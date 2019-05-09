
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
                    	 	onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content" >
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                        <input type="hidden" value="${role.id}" name="id" />
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">名称</label>
	                                <div class="col-sm-9">
	                                <p class="form-control-static">${(role.name)!''}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">描述</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${(role.description)!''}</p>
	                                </div>
                       			</div>
                       		</div>
                        </div>
                        
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">权限设置</label>
	                                <div class="col-sm-9">
	                                  <table class="table table-bordered table-hover dataTables-example" style="width:300px">
							        	 <tr>
						                       	<td></td>
						                        <td>查看</td>
						                        <td>编辑</td>
						                    </tr>
						                     <tr>
						                 <td>管理员管理</td>
						                        <td ><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(5)]checked[/#if] name="ids"  value="5" id="user11" onclick="return false"  ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(58)]checked[/#if] name="ids"  value="59" id="user21" onclick="return false" ></td>
						                </tr>  
						                 <tr> 
						                   <td>角色管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"   [#if role.aclIds?seq_contains(4)]checked[/#if] name="ids"  value="4" id="role1" ></td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" [#if role.aclIds?seq_contains(59)]checked[/#if] name="ids"  value="58" id="role2" ></td>
						                </tr> 
						                  
						                 <tr>
						               <td>APP版本管理</td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" [#if role.aclIds?seq_contains(19)]checked[/#if] name="ids"  value="19" id="app1" ></td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" [#if role.aclIds?seq_contains(57)]checked[/#if] name="ids"  value="57" id="app2" ></td>
						                </tr>    
						                
						                
						                  <tr>
						                        <td>意见反馈管理</td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" [#if role.aclIds?seq_contains(43)]checked[/#if] name="ids"  value="43" id="feedback1" ></td>
						                        <td>-</td>
						           .
						                </tr>
						                    
							            <tr>
						                        <td>平台消息推送</td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" [#if role.aclIds?seq_contains(55)]checked[/#if] name="ids"  value="55" id="manager1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(56)]checked[/#if] name="ids"  value="56" id="manager2" ></td>
						                </tr>
						                
						                
						                <tr>
						                        <td>家长管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(160)]checked[/#if] name="ids"  value="160" id="parent1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(161)]checked[/#if] name="ids"  value="161" id="parent2" ></td>
						                </tr>
						                
						                 <tr>
						                        <td>教师管理</td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" [#if role.aclIds?seq_contains(162)]checked[/#if] name="ids"  value="162" id="teacher1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(163)]checked[/#if] name="ids"  value="163" id="teacher2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>机构管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(164)]checked[/#if] name="ids"  value="164" id="org1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(165)]checked[/#if] name="ids"  value="165" id="org2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>课程管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(166)]checked[/#if] name="ids"  value="166" id="course1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(167)]checked[/#if] name="ids"  value="167" id="course2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>课程类别管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(250)]checked[/#if] name="ids"  value="250" id="coursetype1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(251)]checked[/#if] name="ids"  value="251" id="course2type2" ></td>
						                 </tr>
						                     
						                    
						                 <tr>
						                        <td>课程订单管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(168)]checked[/#if] name="ids"  value="168" id="courseorder1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(169)]checked[/#if] name="ids"  value="169" id="courseorder2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>活动订单管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(170)]checked[/#if] name="ids"  value="170" id="activityorder1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(171)]checked[/#if] name="ids"  value="171" id="activityorder2" ></td>
						                 </tr>
						                 
						                <tr>
						                        <td>活动管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(172)]checked[/#if] name="ids"  value="172" id="activity1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(173)]checked[/#if] name="ids"  value="173" id="activity2" ></td>
						                 </tr>
						                     
						                <tr>
						                        <td>收入管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(176)]checked[/#if] name="ids"  value="176" id="in1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(177)]checked[/#if] name="ids"  value="177" id="in2" ></td>
						                 </tr>  
						                 
						                  <tr>
						                        <td>收入管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(178)]checked[/#if] name="ids"  value="178" id="out1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(179)]checked[/#if] name="ids"  value="179" id="out2" ></td>
						                 </tr>   
						                 
						                   <tr>
						                        <td>提现管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(180)]checked[/#if] name="ids"  value="180" id="cash1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(181)]checked[/#if] name="ids"  value="181" id="cash2" ></td>
						                 </tr>  
						                 
						                    <tr>
						                        <td>广告管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(182)]checked[/#if] name="ids"  value="182" id="ad1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(183)]checked[/#if] name="ids"  value="183" id="ad2" ></td>
						                 </tr>  
						                   <tr>
						                        <td>文章管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(184)]checked[/#if] name="ids"  value="184" id="articl1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(185)]checked[/#if] name="ids"  value="185" id="articl2" ></td>
						                 </tr>  
						                   <tr>
						                        <td>评价标签管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(186)]checked[/#if] name="ids"  value="186" id="remark1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(187)]checked[/#if] name="ids"  value="187" id="remark2" ></td>
						                 </tr>
						                 
						                   <tr>
						                        <td>节假日管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  name="ids"   [#if role.aclIds?seq_contains(240)]checked[/#if]  value="240" id="holiday1" ></td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" name="ids"  [#if role.aclIds?seq_contains(241)]checked[/#if]   value="241" id="holiday2" ></td>
						                 </tr>
						                 
						                   <tr>
						                        <td>成长等级管理</td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" name="ids" [#if role.aclIds?seq_contains(244)]checked[/#if]  value="244" id="systemtasklevel1" ></td>
						                        <td><input type="checkbox" class="i-checks"  disabled="disabled" name="ids" [#if role.aclIds?seq_contains(245)]checked[/#if]  value="245" id="systemtasklevel2" ></td>
						                 </tr>
						                 <tr>
						                        <td>成长任务管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  name="ids" [#if role.aclIds?seq_contains(246)]checked[/#if]  value="246" id="systemtask1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  name="ids" [#if role.aclIds?seq_contains(247)]checked[/#if]  value="247" id="systemtask2" ></td>
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
        	
        });
        
    </script>
</body>

</html>
