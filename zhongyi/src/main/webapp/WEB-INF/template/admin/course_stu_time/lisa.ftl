
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 上课时间管理</title>
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
                    	 <h5>上课时间管理 </h5>
                    	 <div class="ibox-tools">
                    	 	 <button type="button" class="btn btn-xs btn-default" onclick="window.history.back();">返回</button>
                    	 
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="${base}/admin/course_stu_time/addorg.jhtml?id=${id}"><i class="fa fa-plus"></i>安排课时</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					       
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>ID</th>
                                        <th>上课时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courseStuTime] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${courseStuTime.id}">
					                        </td>
					                        <td>${(courseStuTime.id)!''}</td>
					                        <td>${(courseStuTime.courseTime?string('yyyy-MM-dd'))!''} ${(courseStuTime.courseTime1)!''}~${(courseStuTime.courseTime2)!''}</td>
					                     
					                        <td>
					                             [#if courseStuTime.status == ""]正常上课[/#if]
					                             [#if courseStuTime.status == 1]未上课[/#if]
					                             [#if courseStuTime.status == 2]请假[/#if]
					                             [#if courseStuTime.status == 3]旷课[/#if]
					                             [#if courseStuTime.status == 5]迟到[/#if]
					                            
					                            </td>
					                        <td>
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi2" data-id="${courseStuTime.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
        	
        	     $(".btn-delete-loippi2").click(function(){
    	var $this = $(this);
    	 bootbox.confirm(message("admin.dialog.deleteConfirm"), function(result) {
		 	if(result){
		 		$.ajax({
					url: "delete.jhtml",
					type: "POST",
					data: {ids:$this.attr('data-id')},
					dataType: "json",
					cache: false,
					success: function(message) {
					art.message(message);
					if (message.type == "success") {
						window.setTimeout(function() {
							window.location.reload();
						}, 1000);
					}
					}
				});
		 	}
        }); 
    });
        	
        });
    </script>
</body>

</html>
