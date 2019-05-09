
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 意见反馈管理</title>
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
                    	 <h5>意见反馈管理 </h5>
                    	 <div class="ibox-tools">
	                     </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
						        <input type="text" placeholder="请输入账号" name="filter_phone" value="${(paramter.phone)!''}" class="input-sm form-control">
						        </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入反馈内容" name="filter_content" value="${(paramter.content)!''}" class="input-sm form-control">
                            </div>
                            <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入反馈时间" name="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入反馈时间" name="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
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
                                    
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>反馈内容</th>
                                        <th>反馈时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as orgnizationSuggestion] 
							        	 <tr>
							        	 	
					                        <td>${(orgnizationSuggestion.phone)!''}</td>
					                        <td>${(orgnizationSuggestion.name)!''}</td>
					                        <td>${(orgnizationSuggestion.content)!''}</td>
					                        <td>${(orgnizationSuggestion.createDate?string('yyyy-MM-dd'))!''}</td>
					                        <td>
					                        	<a href="${base}/admin/orgnization_suggestion/view/${orgnizationSuggestion.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
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
