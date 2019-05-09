
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 手机记账表管理</title>
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
                    	 <h5>手机记账表管理 </h5>
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
					        <input type="text" placeholder="请输入机构id" name="filter_orgId" value="${(paramter.orgId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入记账时间" name="filter_tallyTime" value="${(paramter.tallyTime)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入数量" name="filter_number" value="${(paramter.number)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入支付时间" name="filter_payTime" value="${(paramter.payTime)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入单价" name="filter_unit-price" value="${(paramter.unit-price)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入部门" name="filter_department" value="${(paramter.department)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入金额" name="filter_amountMoney" value="${(paramter.amountMoney)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入申请人" name="filter_applyUser" value="${(paramter.applyUser)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入交易方式" name="filter_dealWay" value="${(paramter.dealWay)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入项目" name="filter_project" value="${(paramter.project)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入明细" name="filter_detail" value="${(paramter.detail)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入备注" name="filter_note" value="${(paramter.note)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入创建时间" name="filter_crateTime" value="${(paramter.crateTime)!''}" class="input-sm form-control">
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
                                        <th>机构id</th>
                                        <th>记账时间</th>
                                        <th>数量</th>
                                        <th>支付时间</th>
                                        <th>单价</th>
                                        <th>部门</th>
                                        <th>金额</th>
                                        <th>申请人</th>
                                        <th>交易方式</th>
                                        <th>项目</th>
                                        <th>明细</th>
                                        <th>备注</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as keepAccounts] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${keepAccounts.id}">
					                        </td>
					                        <td>${(keepAccounts.id)!''}</td>
					                        <td>${(keepAccounts.orgId)!''}</td>
					                        <td>${(keepAccounts.tallyTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(keepAccounts.number)!''}</td>
					                        <td>${(keepAccounts.payTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(keepAccounts.unit-price)!''}</td>
					                        <td>${(keepAccounts.department)!''}</td>
					                        <td>${(keepAccounts.amountMoney)!''}</td>
					                        <td>${(keepAccounts.applyUser)!''}</td>
					                        <td>${(keepAccounts.dealWay)!''}</td>
					                        <td>${(keepAccounts.project)!''}</td>
					                        <td>${(keepAccounts.detail)!''}</td>
					                        <td>${(keepAccounts.note)!''}</td>
					                        <td>${(keepAccounts.crateTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>
					                        	<a href="${base}/admin/keep_accounts/view/${keepAccounts.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${keepAccounts.id}" href="edit/${keepAccounts.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${keepAccounts.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
