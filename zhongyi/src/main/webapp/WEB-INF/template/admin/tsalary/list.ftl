
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 工资表管理</title>
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
                    	 <h5>工资表管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入用户id" name="filter_uid" value="${(paramter.uid)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构id" name="filter_orgId" value="${(paramter.orgId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入底薪" name="filter_basicSalary" value="${(paramter.basicSalary)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课时" name="filter_courseSalary" value="${(paramter.courseSalary)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入绩效" name="filter_performanceSalary" value="${(paramter.performanceSalary)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入全勤" name="filter_allTsutomu" value="${(paramter.allTsutomu)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入奖金" name="filter_bonus" value="${(paramter.bonus)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入提成" name="filter_pushMoney" value="${(paramter.pushMoney)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入报销" name="filter_reimburse" value="${(paramter.reimburse)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入自定义1" name="filter_edit1" value="${(paramter.edit1)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入自定义2" name="filter_edit2" value="${(paramter.edit2)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入自定义3" name="filter_edit3" value="${(paramter.edit3)!''}" class="input-sm form-control">
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

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th></th>
                                        <th>用户id</th>
                                        <th>机构id</th>
                                        <th>底薪</th>
                                        <th>课时</th>
                                        <th>绩效</th>
                                        <th>全勤</th>
                                        <th>奖金</th>
                                        <th>提成</th>
                                        <th>报销</th>
                                        <th>自定义1</th>
                                        <th>自定义2</th>
                                        <th>自定义3</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as tSalary] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${tSalary.id}">
					                        </td>
					                        <td>${(tSalary.id)!''}</td>
					                        <td>${(tSalary.uid)!''}</td>
					                        <td>${(tSalary.orgId)!''}</td>
					                        <td>${(tSalary.basicSalary)!''}</td>
					                        <td>${(tSalary.courseSalary)!''}</td>
					                        <td>${(tSalary.performanceSalary)!''}</td>
					                        <td>${(tSalary.allTsutomu)!''}</td>
					                        <td>${(tSalary.bonus)!''}</td>
					                        <td>${(tSalary.pushMoney)!''}</td>
					                        <td>${(tSalary.reimburse)!''}</td>
					                        <td>${(tSalary.edit1)!''}</td>
					                        <td>${(tSalary.edit2)!''}</td>
					                        <td>${(tSalary.edit3)!''}</td>
					                        <td>
					                        	<a href="${base}/admin/tsalary/view/${tSalary.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${tSalary.id}" href="edit/${tSalary.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${tSalary.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
        });
    </script>
</body>

</html>
