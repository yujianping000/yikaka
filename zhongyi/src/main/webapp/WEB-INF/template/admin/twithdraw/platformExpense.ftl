
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 平台支出管理</title>
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
                	<form id="listForm" action="platform_expense.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>工资记录表管理 </h5>
                    	 <div class="ibox-tools">
                    	  	<a class="btn btn-outline btn-success btn-xs expenseExport"><i class="fa fa-file-excel-o"></i>导出</a>	
	                    </div>
                    </div>
                    <div class="ibox-content">
                        	<div class="row">
					          	<table class="table table-striped" style="border:0;width:50%">
														<tbody>
															<tr>
																<td >总支出（元）<br><font color="orange" size="6">${ototalSum}</font></td>
																<td>线上总支出（元）<br><font color="orange" size="6">${olSum}</font></td>
																<td>线下总支出（元）<br><font color="orange" size="6">${ofaum}</font></td>
															</tr>
															    <td >今日支出（元）<br><font color="orange" size="6">${ototalSumday}</font></td>
																<td>今日线上支出（元）<br><font color="orange" size="6">${olSumday}</font></td>
																<td>今日线下支出（元）<br><font color="orange" size="6">${ofaumday}</font></td>
															<tr>
															</tr>
														</tbody>
													</table>
                        </div>
                    	<div class="row">
					         <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入支付时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入支付时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>
                              <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构帐号" name="filter_account" id="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
                                      <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构名称" name="filter_name" id="filter_name"  value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>        
                            <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入联系人" name="filter_serviceName" id="filter_serviceName"  value="${(paramter.serviceName)!''}" class="input-sm form-control">
                            </div>
                                             
                             <div class="col-sm-3 m-b-xs">
					            <select class="form-control" name="filter_tallyType">
							  <option [#if  paramter.tallyType==1] selected='selected'[/#if]  value ="1">系统记账</option>
							  <option [#if  paramter.tallyType==2] selected='selected'[/#if]  value ="2">手动记账</option>
						</select>
                            </div> 
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
						           [#if  tallyType==1] 
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>机构账号</th>
                                          <th>机构名称</th>
                                        <th>联系人</th>
                                        <th>联系电话</th>
                                        <th>手续费（%）</th>
                                        <th>今日提现金额（元）</th>
                                        <th>累计提现金额（元）</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as withdrawView] 
							        	 <tr>
							        	     <td>${(withdrawView.account)!''}</td>
							        	        <td>${(withdrawView.name)!''}</td>
					                        <td>${(withdrawView.serviceName)!''}</td>
					                        <td>${(withdrawView.servicePhone)!''}</td>
					                        <td>${(withdrawView.fee)!''}</td>
					                        <td>${(withdrawView.totalday)!''}</td>
					                        <td>${(withdrawView.withdrawTotal)!''}</td>
					                        <td>
					                     
					                        	  <a href="${base}/admin/twithdraw/order_list.jhtml?orgId=${withdrawView.orgId}" class="btn btn-xs btn-default">订单</a>
					                        <a class="btn btn-xs btn-default updateProcedureTotal" data-toggle="modal" data-fee="${(withdrawView.fee)!''}" 
					                      data-id="${(withdrawView.orgId)!''}" data-target="#myModal">手续费</a>
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                            [#else]
                               <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>机构账号</th>
                                          <th>机构名称</th>
                                        <th>联系人</th>
                                        <th>联系电话</th>
                                        <th>今日支出金额（元）</th>
                                        <th>累计支出金额（元）</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as accountView] 
							        	 <tr>
							        	     <td>${(accountView.account)!''}</td>
							        	        <td>${(accountView.name)!''}</td>
					                        <td>${(accountView.serviceName)!''}</td>
					                        <td>${(accountView.servicePhone)!''}</td>
					                        <td>${(accountView.totalday)!''}</td>
					                        <td>${(accountView.amountMoney)!''}</td>
					                        <td>
					             
					                        <a href="${base}/admin/twithdraw/keep_accounts_list.jhtml?${accountView.orgId}.jhtml" class="btn btn-xs btn-default">查看详情</a>
					         
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                            [/#if]
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
    
    <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
            	<div class="modal-content animated bounceInRight">
            	
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    	<h4 class="modal-title" id="myModalLabel">设置手续费</h4>
                	</div>
                	
                	<form id="inputModalForm" class="form-horizontal" action="${base}/admin/twithdraw/updateProcedureTotal.jhtml" method="post" >
                		<input type="hidden" id="orgId"  value="" name="orgId" />
		                <div class="modal-body">
			                    <div class="form-group">
			                    	<label>手续费<span style="color:#F00">(*)</span></label>
			                        <input type="text" name="procedure_total" id="procedure_total" placeholder="请输入手续费" class="form-control">
			                    </div>
		                </div>
                
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="submit" class="btn btn-primary">确定</button>
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
    
    	 //修改手续费-传入orgId
    	 $(".updateProcedureTotal").click(function(){
			var id=$(this).attr('data-id');
			var procedure_total=$(this).attr('data-fee');
			$("#orgId").val(id);
			$("#procedure_total").val(procedure_total);
    });
    
    		//修改手续费校验
    		$("#inputModalForm").validate({
                rules: {
                   procedure_total: {
	                    required: true
	                },
                  
	              },
    			  messages: {
				}
    		});
    	
    	
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
			
			//导出支出管理表
			 $(".expenseExport").click(function(){
			    var filter_sDate = $("#filter_sDate").val();
			    var filter_eDate = $("#filter_eDate").val();
			    var filter_account = $("#filter_account").val();
			    var filter_name = $("#filter_name").val();
			    var filter_serviceName = $("#filter_serviceName").val();

			 	window.open('expenseExport.jhtml?filter_sDate='+filter_sDate
			 			+'&filter_eDate='+filter_eDate
			 			+'&filter_account='+filter_account
			 			+'&filter_name='+filter_name
			 			+'&filter_serviceName='+filter_serviceName
						,'_blank');
		    }); 
        });
    </script>
</body>

</html>
