
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 支出统计管理</title>
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
                    	 <h5>支出统计管理 </h5>
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
					        <input type="text" placeholder="请输入系统工资" name="filter_onlineSalary" value="${(paramter.onlineSalary)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入线上提现" name="filter_onlineWithdraw" value="${(paramter.onlineWithdraw)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入手动记账" name="filter_offlineAccount" value="${(paramter.offlineAccount)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入" name="filter_orgId" value="${(paramter.orgId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入" name="filter_createTime" value="${(paramter.createTime)!''}" class="input-sm form-control">
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
                                        <th>系统工资</th>
                                        <th>线上提现</th>
                                        <th>手动记账</th>
                                        <th></th>
                                        <th></th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as paymentStatistics] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${paymentStatistics.}">
					                        </td>
					                        <td>${(paymentStatistics.id)!''}</td>
					                        <td>${(paymentStatistics.onlineSalary)!''}</td>
					                        <td>${(paymentStatistics.onlineWithdraw)!''}</td>
					                        <td>${(paymentStatistics.offlineAccount)!''}</td>
					                        <td>${(paymentStatistics.orgId)!''}</td>
					                        <td>${(paymentStatistics.createTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>
					                        	<a href="${base}/admin/payment_statistics/view/${paymentStatistics.}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${paymentStatistics.}" href="edit/${paymentStatistics.}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${paymentStatistics.}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
