
<!DOCTYPE html>
<html>

<head>
[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 机构管理 </title>
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
                    	 <h5>机构管理 </h5>
                    	 <div class="ibox-tools">
                    	 [@shiro.hasPermission name ='admin:systemorg:edit']
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
                        	<a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                     [/@shiro.hasPermission]
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构ID" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构名称" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
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
					       	<div class="col-sm-3 m-b-xs">
                               <select class="form-control m-b" name="filter_status" id="filter_status">
                                    <option value="">全部</option>
                        			<option value="1" [#if paramter.filter_status == '1'] selected="selected"[/#if]>启用</option>
                                	<option value="2" [#if paramter.filter_status == '2'] selected="selected"[/#if]>禁用</option>
                               </select>
                            </div>
                           
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        
						   <div class="dataTables_wrapper form-inline">
						    <style>
								th,td {text-align:center}
							</style>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>ID</th>
                                        <th>账号</th>
                                        <th>机构名称</th>
                                        <th>地区</th>
                                        <th>联系人</th>
                                        <th>联系电话</th>
                                        <th>用户评价</th>
                                        <th>学员数量</th>
                                        <th>教师数量</th>
                                        <th>机构入驻时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                	 [#list page.content as orgnization] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${orgnization.id}">
					                        </td>
					                        <td>${(orgnization.id)!''}</td>
					                        <td>${(orgnization.account)!''}</td>
					                        <td>${(orgnization.name)!''}</td>
					                        <td>${(orgnization.location)!''}</td>
					                        <td>${(orgnization.serviceName)!''}</td>
					                        <td>${(orgnization.servicePhone)!''}</td>
					                        <td>${(orgnization.userRate)!''}</td>
					                        <td>${(orgnization.studentCount)!''}</td>
					                        <td>${(orgnization.teacherCount)!''}</td>
					                        <td>${(orgnization.createDate?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>
					                        	[#if orgnization.status = 1]
					                        		<a class="btn btn-primary btn-xs">启用</a>
					                        	[/#if] 
					                        	[#if orgnization.status = 2]
					                        		<a class="btn btn-danger btn-xs">禁用</a>
					                        	[/#if] 
					                        </td>
					                        
					                       
					                        <td>
					                        [@shiro.hasPermission name ='admin:systemorg:edit']
			                        			[#if orgnization.status = 1]
					                        		<a class="btn btn-xs btn-danger forbidusing" data-id="${orgnization.id}" id="forbidusing" ><i class="fa fa-paste"></i> 禁用</a>
					                        	[/#if] 
					                        	[#if orgnization.status = 2]
					                        		<a class="btn btn-xs btn-primary startusing" data-id="${orgnization.id}" id="startusing"><i class="fa fa-paste"></i>启用</a>
					                        	[/#if] 
					                        
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${orgnization.id}" href="edit/${orgnization.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        [/@shiro.hasPermission]
					                        
					                        	<a href="${base}/admin/orgnization/view/${orgnization.id}.jhtml" class="btn btn-xs btn-default"><i class="fa fa-paste"></i> ${message("admin.common.view")} </a>
					                        	
					                        	<a href="${base}/admin/orgnization/studentlist/${orgnization.id}.jhtml"  class="btn btn-xs btn-default"><i class="fa fa-paste"></i>学员</a>
					                        	
					                        	<a href="${base}/admin/orgnization/teacherlist/${orgnization.id}.jhtml"  class="btn btn-xs btn-default"><i class="fa fa-paste"></i>教师</a>
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
    
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    
        $(document).ready(function () {
        	[@flash_message /]
        	
			//启用
			$(".startusing").click(function(){
		    	var $this = $(this);
				 		$.ajax({
							url: "startusing.jhtml",
							type: "POST",
							data: {id:$this.attr('data-id')},
							dataType: "json",
							cache: false,
							success: function(message) {
								window.setTimeout(function() {
									window.location.reload();
								}, 1000);
							}
						});
		    	});
			//禁用
			$(".forbidusing").click(function(){
			
				var $this = $(this);
				 bootbox.confirm("确定禁用?", function(result) {
				 	if(result){
				 		$.ajax({
							url: "forbidusing.jhtml",
							type: "POST",
							data: {id:$this.attr('data-id')},
							dataType: "json",
							cache: false,
							success: function(message) {
								window.setTimeout(function() {
									window.location.reload();
								}, 1000);
							}
						});
				 	}
			    }); 
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
			
        });
    </script>
</body>

</html>
