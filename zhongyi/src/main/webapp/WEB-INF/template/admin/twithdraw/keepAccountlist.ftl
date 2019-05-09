
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 工资记录表管理</title>
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
                    	 <h5>工资记录表管理 </h5>
                    	 	 <div class="ibox-tools">
                    	 <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" onclick="window.history.back();"><i class="fa fa-reply-all"></i> 返回列表</button>
                    </div>
                     </div>
                    <div class="ibox-content">

                    	<div class="row">
					         <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入支付时间" name="filter_sDate" id="filter_sDate" value="${(paramter.filter_sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入支付时间" name="filter_eDate" id="filter_eDate" value="${(paramter.filter_eDate)!''}" class="input-sm form-control  "> 
                            	</div>
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
                                        <th>记账时间</th>
                                         <th>付款时间</th>
                                         <th>部门</th>
                                          <th>申请人</th>
                                           <th>项目</th>
                                        <th>数量</th>
                                          <th>单价</th>
                                        <th>金额</th>
                                        <th>交易方式</th>
                                        <th>明细</th>
                                        <th>备注</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as keepAccounts] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${keepAccounts.id}">
					                        </td>
					                        <td>${(keepAccounts.tallyTime?string('yyyy-MM-dd'))!''}</td>
					                         <td>${(keepAccounts.payTime?string('yyyy-MM-dd'))!''}</td>
					                       <td>${(keepAccounts.department)!''}</td>
					                        <td>${(keepAccounts.applyUser)!''}</td>
					                       <td>${(keepAccounts.project)!''}</td>
					                        <td>${(keepAccounts.number)!''}</td>
					                        <td>${(keepAccounts.unitPrice)!''}</td>
					                        <td>${(keepAccounts.amountMoney)!''}</td>
					                        <td>${(keepAccounts.dealWay)!''}</td>
					                        <td>${(keepAccounts.detail)!''}</td>
					                        <td>${(keepAccounts.note)!''}</td>

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
