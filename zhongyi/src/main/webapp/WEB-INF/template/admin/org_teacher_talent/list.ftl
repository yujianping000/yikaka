
<!DOCTYPE html>
<html>

<head>
﻿﻿﻿﻿﻿﻿﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]	
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 教师人才库</title>
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
                	<form id="listForm" action="list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>教师人才库 </h5>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-2 m-b-xs">
					        	<input type="text" placeholder="请输入账号" name="filter_account" value="${(paramter.filter_account)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-2 m-b-xs">
					        	<input type="text" placeholder="请输入姓名" name="filter_nickname" value="${(paramter.filter_nickname)!''}" class="input-sm form-control">
					        </div>
					       <!-- 
				        	<div class="col-sm-2 m-b-xs">
	                           <select class="form-control m-b" name="filter_type" >
	                                <option value="">类型</option>
	                    			<option value="1" [#if paramter.filter_type == '1'] selected="selected"[/#if]>全职教师</option>
	                            	<option value="2" [#if paramter.filter_type == '2'] selected="selected"[/#if]>兼职教师</option>
	                           </select>
                        	</div>
                        	-->
	                    	<div class="col-sm-2 m-b-xs">
	                           <select class="form-control m-b" name="filter_inviteType">
	                                <option value="">状态</option>
	                    			<option value="1" [#if paramter.filter_inviteType == '1'] selected="selected"[/#if]>未邀请</option>
	                            	<option value="2" [#if paramter.filter_inviteType == '2'] selected="selected"[/#if]>已邀请</option>
                            		<option value="3" [#if paramter.filter_inviteType == '3'] selected="selected"[/#if]>已拒绝</option>
	                           </select>
	                        </div>
                           <div class="col-sm-2 m-b-xs">
	                           <select class="form-control m-b" name="filter_subject">
	                                <option value="">艺术分类</option>
	                    			<option value="钢琴" [#if paramter.filter_subject == '钢琴'] selected="selected"[/#if]>钢琴</option>
	                            	<option value="声乐" [#if paramter.filter_subject == '声乐'] selected="selected"[/#if]>声乐</option>
                            		<option value="舞蹈" [#if paramter.filter_subject == '舞蹈'] selected="selected"[/#if]>舞蹈</option>
                            		<option value="武术" [#if paramter.filter_subject == '武术'] selected="selected"[/#if]>武术</option>
                            		<option value="器乐" [#if paramter.filter_subject == '器乐'] selected="selected"[/#if]>器乐</option>
                            		<option value="语言" [#if paramter.filter_subject == '语言'] selected="selected"[/#if]>语言</option>
                            		<option value="其它" [#if paramter.filter_subject == '其它'] selected="selected"[/#if]>其它</option>
	                           </select>
	                        </div>
	                         <div class="col-sm-3 m-b-xs">
						        <!--
						         <select class="form-control m-b chosen-select" name="filter_province" id="province" >
		                        	<option  value="">省份</option>
	   								[#list provinces as area]
						 					<option  value="${area.id}"> ${area.name}</option>
									[/#list]
								</select>
								-->
								<!--
								 <input type="text" placeholder="请输入省份" name="filter_province" value="${(paramter.province)!''}" class="input-sm form-control">
		                        -->
		                        <select name="filter_provinceId" id="province_id" data-placeholder="省..."
                                                class="chosen-select form-control m-b">
                                            <option hassubinfo="true" value="">请选择省</option>
                                            [#list provinces as province]
                                            <option hassubinfo="true" value="${province.id}">${province.name}</option>
                                            [/#list]
                                </select>
		                        
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        	<!--
					        		<select class="form-control m-b chosen-select" name="filter_city" id="city" >
                                		<option  value="">城市</option>
   								 	
                                	</select>
                                -->
                                <!--
                                 <input type="text" placeholder="请输入城市" name="filter_city" value="${(paramter.city)!''}" class="input-sm form-control">
                                 -->
	                              <select name="filter_cityId" id="city_id" data-placeholder="市..."
	                                            class="chosen-select form-control m-b" style="width:350px;">
                                             	<option hassubinfo="true" value="">请选择市</option>
	                              </select>
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        	<!--
						        <select class="form-control m-b chosen-select" name="filter_area" id="area" >
			                    	<option  value="">区域</option>
		       								 	
			                    </select>
			                    -->
			                    <!--
			                     <input type="text" placeholder="请输入区县" name="filter_area" value="${(paramter.area)!''}" class="input-sm form-control">
			                     -->
			                     <select name="filter_areaId" id="area_id" data-placeholder="县区..."
                                                class="chosen-select form-control m-b" style="width:350px;">
                                                <option hassubinfo="true" value="">请选择县区</option>
                                 </select>
                            </div>
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                            
                            	<style>
									th,td {text-align:center}
								</style>
                            	
                                <thead>
                                    <tr>

                                    	<th>ID</th>
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>头像</th>
                                        <th>性别</th>
                                        <th>出生日期</th>
                                        <th>职教科目</th>
                                        <th  style="width:30%">个人简介</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as teacher] 
							        	 <tr>
					                        <td>${(teacher_index+1)!''}</td>
					                        <td><a class="btn btn-primary btn-xs">获取</a></td>
					                        <td>${(teacher.nickname)!''}</td>
					                        <td> <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(teacher.avatar)!''}"> </td>
					                        <td>${(teacher.sex)!''}</td>
					                        <td>${(teacher.birthday?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(teacher.subject)!''}</td>
					                        <td>${(teacher.description)!''}</td>
					                        <td>
		                        				[#if teacher.invitetype != 2 && teacher.invitetype != 3 ]
					                        		<a class="btn btn-default btn-xs">未邀请</a>
					                        	[/#if] 
			                        			[#if teacher.invitetype = 2]
					                        		<a class="btn btn-primary btn-xs">已邀请</a>
					                        	[/#if] 
					                        	[#if teacher.invitetype = 3]
					                        		<a class="btn btn-danger btn-xs">已拒绝</a>
					                        	[/#if] 
					                        </td>
					                        <td>
					                        	<a href="${base}/admin/org_teacher_talent/view/${teacher.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
					                        	[@shiro.hasPermission name ='admin:orgtalentpool:edit']
					                        		[#if teacher.invitetype != 2 && teacher.invitetype != 3   && teacher.joinType != 1]
						                        		<a class="btn btn-xs btn-default inviteTeacher" data-toggle="modal" data-id="${(teacher.id)!''}" data-target="#myModal1" > <i class="fa fa-paste"></i>邀请 </a>
						                        	[/#if]
						                        [/@shiro.hasPermission]
					                        	
				                        		<a  href="javascript:weekCourse(${(teacher.id)!''})" class="btn btn-xs btn-default"> <i class="fa fa-paste"></i>周课程表 </a>
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                       		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
								[#include "/admin/include/pagination.ftl"]
							[/@pagination]
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal inmodal" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
            	<div class="modal-content animated bounceInRight">
            	
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    	<h4 class="modal-title" id="myModalLabel">邀请加入</h4>
                	</div>
                	
                	<form id="inputModalForm1" class="form-horizontal" action="${base}/admin/org_teacher_talent/inviteTeacher.jhtml" method="post" >
		                <div class="modal-body">
		                
			                    <div class="form-group">
			                    <input type="hidden" value="" id="teacherId" name="teacherId" />
			                        <label>类型<span style="color:#F00">(*)</span></label>
                                          <label class="radio-inline"><input type="radio"  checked="checked" value="1" id="teacherType1" name="teacherType" readonly>全职教师</label>
	                                     <label class="radio-inline"><input type="radio"  value="2" id="teacherType2" name="teacherType" readonly>兼职教师</label>
			                    </br> </br> 
			                        <label>邀请原因</label><span style="color:#F00">(*)</span>
			                        <textarea type="reason" name="reason" id="reason"  class="form-control input-sm"  ></textarea>
			                        </br>  
			                    </div>
		                </div>
                
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-success">确定</button>
		                </div>
	                </form>
	                
                </div>
            </div>
         </div>
	</div>
    
    
    <div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
            	<div class="modal-content animated bounceInRight">
            	
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    	<h4 class="modal-title" id="myModalLabel">查看周课程表</h4>
                	</div>
                	
		                <div class="modal-body">
		                	<div class="dataTables_wrapper form-inline dialog-table">
		                	
		                	</div>
			                   
		                </div>
                
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
		                </div>
	                
                </div>
            </div>
         </div>
	</div>
	
	</div>
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    	
	    	 //邀请——传入teacherId以及教师类型
	    	 $(".inviteTeacher").click(function(){
				var id=$(this).attr('data-id');
				$("#teacherId").val(id);
			
	    	});
    
    
    		//邀请加入校验
    		$("#inputModalForm1").validate({
                rules: {
                   teacherType: {
	                    required: true
	                },
                   reason: {
	                    required: true
	                }
	              },
    			  messages: {
				}
    		});
    		
    		 function weekCourse(id){
           $.ajax({
                url: "weekCourse.jhtml",
                type: "GET",
                data: {id:id},
                cache: false,
                async: false,
                success: function (data) {
                     var tableCon = $(".dialog-table");
                    tableCon.empty();
                    tableCon.append(data);
                    $('#myModal2').modal({
                    keyboard: true
                 })
                }
            });
       }
    	
        $(document).ready(function () {
        	[@flash_message /]
        	
        	
        });
        	//省选择
	    $("#province_id").change(function () {
	        var id = $(this).val();
	        var area = $("#city_id");
	        area.empty();
	        area.append("<option hassubinfo=\"true\" value=\"\">请选择市</option>");
	        $("#area_id").empty();
	        $("#area_id").append("<option hassubinfo=\"true\" value=\"\">请选择县区</option>");
	        if (id == null || id == '') {
	            return;
	        }
	         $.ajax( {
				    url:'${base}/admin/orgnization/city.jhtml',// 跳转到 action  
				    data:{
				       id : id,  
				    },  
				    type:'post',  
				    cache:false,  
				    dataType:'json',  
				    success:function(result) {
				    	var city=result.data;
				     	if (result == null || result == '') {
	                	return;
	            		}
		            	for (var i = 0; i < city.length; i++) {
		                	area.append("<option hassubinfo=\"true\" value=\"" + city[i].id + "\">" + city[i].name + "</option>");
		            	}
		            	$(".chosen-select").trigger("chosen:updated")
		            	},
		           		error : function() {  
				     
				     	}   
			});
		})
	    
	
	    //城市选择
	    $("#city_id").change(function () {
	        var id = $(this).val();
	        var area = $("#area_id");
	        area.empty();
	        area.append("<option hassubinfo=\"true\" value=\"\">请选择县区</option>");
	        if (id == null || id == '') {
	            return;
	        }
	        $.ajax( {
				    url:'${base}/admin/orgnization/area.jhtml',// 跳转到 action  
				    data:{
				       id : id,  
				    },  
				    type:'post',  
				    cache:false,  
				    dataType:'json',  
				    success:function(result) {
				    	var area2=result.data;
				     	if (result == null || result == '') {
	                	return;
	            		}
		            	for (var i = 0; i < area2.length; i++) {
		                	area.append("<option hassubinfo=\"true\" value=\"" + area2[i].id + "\">" + area2[i].name + "</option>");
		            	}
		            	$(".chosen-select").trigger("chosen:updated")
		            	},
		           		error : function() {  
				     
				     	}   
			});
	
			
        });
    </script>
</body>

</html>
