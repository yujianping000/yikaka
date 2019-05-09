
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - tWithdraw管理</title>
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
                    	 <h5>提现管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-primary btn-xs" id="btn-add-loippi" href="${base}/admin/tbank/list.jhtml">银行卡管理</a>
                    	 	<a class="btn btn-primary btn-xs" id="btn-add-loippi" href="add.jhtml"> 申请提现</a>
                          <font color="orange" size="4">  可提现金额:[#if validTotal??]${validTotal.total}[#else]0[/#if]元</font></label>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
                    	                          <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入提现时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入提现时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>    
					        <div class="col-sm-3 m-b-xs">
					         <select class="form-control" name="filter_status">
					          <option value ="">请选择状态</option>
							  <option [#if  paramter.status==1] selected='selected'[/#if]  value ="1">已完成</option>
							  <option [#if  paramter.status==2] selected='selected'[/#if]  value ="2">待审批</option>
							  <option [#if  paramter.status==3] selected='selected'[/#if]  value ="3">已拒绝</option>
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
                                        <th>ID</th>
                                        <th>提现时间</th>
                                        <th>提现现金(元)</th>
                                        <th>手续费(%)</th>
                                        <th>到账金额(元)</th>
                                        <th>银行名称</th>
                                        <th>银行卡号</th>
                                        <th>持卡人</th>
                                        <th>备注</th>
                                        <th>状态</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as tWithdraw] 
							        	 <tr>
					                        <td>${(tWithdraw.id)!''}</td>
					                        <td>${(tWithdraw.withdrawTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>${(tWithdraw.withdrawTotal)!''}</td>
					                        <td>${(tWithdraw.procedureTotal)!''}</td>
					                        <td>${(tWithdraw.toAccountTotal)!''}</td>
					                        <td>${(tWithdraw.bankName)!''}</td>
					                        <td>${(tWithdraw.bankNum)!''}</td>
					                        <td>${(tWithdraw.bankUserName)!''}</td>
					                        <td>${(tWithdraw.note)!''}</td>
					                        <td>[#if tWithdraw.status=1]已完成[#elseif tWithdraw.status=2]待审批[#else]已拒绝[/#if]</td>
					                        
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
