
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
                  		<form id="inputForm" class="form-horizontal" action="updateorg.jhtml" method="post">
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
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>权限设置</label>
	                                <div class="col-sm-9">
	                                  <table class="table table-bordered table-hover dataTables-example" style="width:300px">
							        	 <tr>
						                       	<td></td>
						                        <td>查看</td>
						                        <td>编辑</td>
						                    </tr>
							            <td>管理员管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(202)]checked[/#if] name="ids"  value="202" id="user11" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(203)]checked[/#if] name="ids"  value="203" id="user21" ></td>
						                </tr>  
						                 <tr> 
						                   <td>角色管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(204)]checked[/#if] name="ids"  value="204" id="role1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(205)]checked[/#if] name="ids"  value="205" id="role2" ></td>
						                </tr> 
						                
						                
						                
						                  <tr>
						                        <td>意见反馈管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(206)]checked[/#if] name="ids"  value="206" id="feedback1" ></td>
						                        <td>-</td>
						           .
						                </tr>
						                    
							            <tr>
						                        <td>消息推送</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(207)]checked[/#if] name="ids"  value="207" id="manager1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled"  [#if role.aclIds?seq_contains(208)]checked[/#if] name="ids"  value="208" id="manager2" ></td>
						                </tr>
						                
						                
						               
						                
						                 <tr>
						                        <td>教师管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(212)]checked[/#if] name="ids"  value="212" id="teacher1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(213)]checked[/#if] name="ids"  value="213" id="teacher2" ></td>
						                 </tr>
						                 
						               
						                 
						                 <tr>
						                        <td>课程管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(214)]checked[/#if] name="ids"  value="214" id="course1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(215)]checked[/#if] name="ids"  value="215" id="course2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>课室管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(216)]checked[/#if] name="ids"  value="216" id="classroom1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(217)]checked[/#if] name="ids"  value="217" id="classroom2" ></td>
						                 </tr>
						                   
						                     <tr>
						                        <td>试课申请管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(218)]checked[/#if] name="ids"  value="218" id="book1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(219)]checked[/#if] name="ids"  value="219" id="book2" ></td>
						                 </tr>    
						                 
						                    <tr>
						                        <td>调班申请管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(220)]checked[/#if] name="ids"  value="220" id="change1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(221)]checked[/#if] name="ids"  value="221" id="change2" ></td>
						                 </tr>    
						                    
						                   <tr>
						                        <td>补课申请管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(222)]checked[/#if] name="ids"  value="222" id="makeup1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(223)]checked[/#if] name="ids"  value="223" id="makeup2" ></td>
						                 </tr>  
						                    
						                  <tr>
						                        <td>请假申请管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(224)]checked[/#if] name="ids"  value="224" id="leave1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(225)]checked[/#if] name="ids"  value="225" id="leave2" ></td>
						                 </tr>  
						                      
						                    
						                 <tr>
						                        <td>订单管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(226)]checked[/#if] name="ids"  value="226" id="order1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(227)]checked[/#if] name="ids"  value="227" id="order2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>离职审核管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(228)]checked[/#if] name="ids"  value="228" id="leavejob1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(229)]checked[/#if] name="ids"  value="229" id="leavejob2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>入职审核管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(230)]checked[/#if] name="ids"  value="230" id="entry1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(231)]checked[/#if] name="ids"  value="231" id="entry2" ></td>
						                 </tr>
						                 
						                 <tr>
						                        <td>教室人才库管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(232)]checked[/#if] name="ids"  value="232" id="talentpool1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(233)]checked[/#if] name="ids"  value="233" id="talentpool2" ></td>
						                 </tr>
						                 
						                  <tr>
						                        <td>收入管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(234)]checked[/#if] name="ids"  value="234" id="in1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(235)]checked[/#if] name="ids"  value="235" id="in2" ></td>
						                 </tr>
						                 
						               <tr>
						                        <td>支出管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(236)]checked[/#if] name="ids"  value="236" id="out1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(237)]checked[/#if] name="ids"  value="237" id="out2" ></td>
						                 </tr>
						                 
						                 
						                   <tr>
						                        <td>提现管理</td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(238)]checked[/#if] name="ids"  value="238" id="cash1" ></td>
						                        <td><input type="checkbox" class="i-checks" disabled="disabled" [#if role.aclIds?seq_contains(239)]checked[/#if] name="ids"  value="239" id="cash2" ></td>
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
        	
        });
        
    </script>
</body>

</html>
