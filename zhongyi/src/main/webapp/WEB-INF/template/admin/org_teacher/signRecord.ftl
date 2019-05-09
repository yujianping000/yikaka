﻿﻿﻿[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 机构评价</title>


  	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
	<!-- END HEADER -->
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                	<form id="listForm" action="${base}/admin/org_teacher/orgnizationRate/${teacher.id}.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>签到 </h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/org_teacher/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
                    </div>
                    
                    <div class="ibox-content">
                    <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>签到</h3><br/>
                    	
                        </div>
                        
						   <div class="dataTables_wrapper form-inline">
						   
						   <style>
								th,td {text-align:center}
							</style>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                            
                                <thead>
                                    <tr>
                                        <th>签到地址</th>
                                        <th>签到时间</th>
                                    	
                                      
                                    </tr>
                                </thead>
                                <tbody>
                            	 	[#list page.content as tSign] 
							        	 <tr>
					                        <td>${(tSign.location)!''}</td>
					                        <td>${(tSign.markTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                       
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                       		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
								[#include "/admin/include/pagination.ftl"]
							[/@pagination]
                        </div>
                        
	                     <!--/row-->
						<div class="row">
	                   		
	                    	<div class="hr-line-dashed"></div>
	                   			 <div class="form-group">
		                       		 <div class="col-sm-12 col-sm-offset-10">
		                        		<button type="button" class="btn btn-default" onclick="location.href='${base}/admin/org_teacher/list.jhtml'">返回</button>
		                       		 </div>
	                    		</div>
	                		</div>
	                	</div>
                        
                    </div>
                   
                    </form>
                </div>
            </div>
        </div>
    </div>
   
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
		<script src="${base}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
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
			
		
     });
        
    </script>
</body>

</html>
