
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - VIEW管理</title>
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
                	<form id="listForm" action="${base}/admin/hour_view/list/${orgId}/${teaId}.jhtml"  method="get">
                    <div class="ibox-title">
                    	 <h5>授课信息</h5>
                    	 <div class="ibox-tools">
                    	 <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程名称" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
                            <div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入上课时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入上课时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            
                            
                            </div>
					        <div class="col-sm-3 m-b-xs">
					         	            <select class="form-control" name="filter_classStatus">
							  <option value="" >请选择状态</option>
							  <option [#if  paramter.classStatus==1] selected='selected'[/#if]  value ="1">已完成</option>
							  <option [#if  paramter.classStatus==2] selected='selected'[/#if]  value ="2">旷工</option>
							    <option [#if  paramter.classStatus==3] selected='selected'[/#if]  value ="3">迟到</option>
							    <option [#if  paramter.classStatus==3] selected='selected'[/#if]  value ="4">请假</option>
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
                                <thead>
                                    <tr>
                                        <th>课程名称</th>
                                        <th>签到时间</th>
                                        <th>上课时间</th>
                                        <th>昵称</th>
                                        <th>状态</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as hourView] 
							        	 <tr>
					                        <td>${(hourView.name)!''}</td>
					                        <td>${(hourView.markTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(hourView.coursetime)!''}</td>
					                        <td>${(hourView.nickname)!''}</td>
					                        <td>
					                        [#if hourView.classStatus==1]已完成[#elseif  hourView.classStatus==2]旷工[#elseif  hourView.classStatus==3]迟到[#else]请假[/#if]
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
        		//时间控件
    		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
        });
    </script>
</body>

</html>
