
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 学员列表</title>
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
                	<form id="listForm" action="${base}/admin/orgnization/studentlist/${orgnization.id}.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>学员列表 </h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/orgnization/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
	                    </div>
                    </div>
                    <div class="ibox-content">
                 	<h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>学员列表</h3>
                    	<div class="row">
					        <p><h3>&nbsp;&nbsp;&nbsp;学员总数：<font color="orange" size="3">${(orgnization.studentCount)!''}</font></h3><p>
                        </div>
                    	
                    	<div class="row">
					        <div class="col-sm-2 m-b-xs">
					        	<input type="text" placeholder="请输入账号" name="filter_account" id="filter_account" value="${(paramter.filter_account)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-2 m-b-xs">
					        	<input type="text" placeholder="请输入孩子姓名" name="filter_nickname" id="filter_nickname" value="${(paramter.filter_nickname)!''}" class="input-sm form-control">
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
                                        <th>ID</th>
                                        <th>账号</th>
                                        <th>昵称</th>
                                        <th>联系电话</th>
                                        <th>孩子姓名</th>
                                        <th>孩子头像</th>
                                        <th>性别</th>
                                        <th>出生日期</th>
                                        <th>爱好</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as student] 
							        	 <tr>
					                        <td>${(student_index+1)!''}</td>
					                        <td>${(student.account)!''}</td>
					                        <td>${(student.pnickname)!''}</td>
					                        <td>${(student.account)!''}</td>
					                        <td>${(student.nickname)!''}</td>
					                        <td>
					                        	<img alt="image" class="img-circle"  style="height:50px;width:50px;" src="${(student.avatar)!''}">
					                        </td>
					                        <td>${(student.sex)!''}</td>
					                        <td>${(student.birtchday?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(student.hobby)!''}</td>
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
                        		<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
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
			$("#forbidusing").click(function(){
			
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
			
        });
    </script>
</body>

</html>
