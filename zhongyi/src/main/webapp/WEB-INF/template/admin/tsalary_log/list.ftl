
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
                    	 	<a class="btn btn-primary btn-xs" id="btn-add-loippi" href="add_keep_accounts.jhtml">手动记账</a>
	                   		<a class="btn btn-outline btn-success btn-xs salaryLogExport"><i class="fa fa-file-excel-o"></i>导出</a>	
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
					        <input type="text" placeholder="帐号" name="filter_account" id="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>    
                            <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="姓名" name="filter_user_name" id="filter_user_name" value="${(paramter.userName)!''}" class="input-sm form-control">
                            </div>
                                              	        <div class="col-sm-3 m-b-xs">
					            <select class="form-control" name="filter_status" id="filter_status">
							  <option value="">请选择状态</option>
							  <option [#if  paramter.status==1] selected='selected'[/#if]  value ="1">已发放</option>
							   <option [#if  paramter.status==2] selected='selected'[/#if]  value ="2">未发放</option>
						</select>
                            </div>
                            
                             <div class="col-sm-3 m-b-xs">
					            <select class="form-control" name="filter_tallyType" id="filter_tallyType">
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
                                        <th>工资日期</th>
                                          <th>帐号</th>
                                        <th>姓名</th>
                                        <th>底薪</th>
                                        <th>课时</th>
                                        <th>绩效</th>
                                        <th>全勤</th>
                                        <th>奖金</th>
                                        <th>提成</th>
                                        <th>报销</th>
                                        <th>其他</th>
                                             <th>合计</th>
                                        <th>发放时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as tSalaryLog] 
							        	 <tr>
							        	     <td>${(tSalaryLog.createTime?string('yyyy年MM月'))!''}</td>
							        	        <td>${(tSalaryLog.account)!''}</td>
					                        <td>${(tSalaryLog.userName)!''}</td>
					                        <td>${(tSalaryLog.basicSalary)!''}</td>
					                        <td>${(tSalaryLog.courseSalary)!''}</td>
					                        <td>${(tSalaryLog.performanceSalary)!''}</td>
					                        <td>${(tSalaryLog.allTsutomu)!''}</td>
					                        <td>${(tSalaryLog.bonus)!''}</td>
					                        <td>${(tSalaryLog.pushMoney)!''}</td>
					                        <td>${(tSalaryLog.reimburse)!''}</td>
					                        <td>${(tSalaryLog.other)!''}</td>
					                         <td>${(tSalaryLog.totalSalary)!''}</td>
					                        <td>${(tSalaryLog.payTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>[#if tSalaryLog.status==1]已发放[#else]未发放[/#if]</td>
					                        <td>
					                        [#if tSalaryLog.status==2]
					                        	<a href="javascript:model1(${(tSalaryLog.id)!''})"  class="btn btn-xs btn-default">标志为已发放</a>
					                        [#else]
					                        --
					                        [/#if]
					                        </td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                            [#else]
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
                                        <th>操作</th>
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
					                        <td>
					                        	<a href="javascript:showRefuse(${(keepAccounts.id)!''})" class="btn btn-xs btn-default">修改备注</a>
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
	   <div class="modal inmodal" id="backReason" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">请填写备注</p>
            </div>
            <div class="modal-body">
               <input type="hidden" id="reasonId" name="reasonId">
                        <div class="dataTables_wrapper form-inline ">
                                  <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static">备注:</p><textarea rows="8" id="reasonaaa" name="reasonaaa" class="form-control" cols="40"></textarea>
	                                </div>

                       			  </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="affirmRefuse()">确定</button>
            </div>
        </div>
    </div>
</div>

 <div class="modal inmodal" id="prompt" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">温馨提示</p>
            </div>
            <div class="modal-body">
            	<input type="hidden" id="key" name="key">
                        <div class="dataTables_wrapper form-inline dialog-table">
                           <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static" id="info"></p>
	                                </div>

                       			  </div>
                       			  
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="issue()">确定</button>
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
        	
        	//时间控件
    		$(".input-daterange").datepicker({
				todayBtn : "linked",
				keyboardNavigation : false,
				forceParse : false,
				calendarWeeks : true,
				autoclose : true
			});
			  //导出提现管理表
			 $(".salaryLogExport").click(function(){
			    var filter_sDate = $("#filter_sDate").val();
			    var filter_eDate = $("#filter_eDate").val();
			    var filter_account = $("#filter_account").val();
			    var filter_user_name = $("#filter_user_name").val();
			    var filter_status = $("#filter_status").val();
			    var filter_tallyType = $("#filter_tallyType").val();

			 	window.open('salaryLogExport.jhtml?filter_sDate='+filter_sDate
			 			+'&filter_eDate='+filter_eDate
			 			+'&filter_account='+filter_account
			 			+'&filter_user_name='+filter_user_name
			 			+'&filter_status='+filter_status
			 			+'&filter_tallyType='+filter_tallyType
						,'_blank');
		    }); 			
			
        	
        });
        
          function affirmRefuse(){
         var id=$("#reasonId").val();
         var reasonaaa=$("#reasonaaa").val();
         $.ajax({
                url: "affirmRefuse.jhtml",
                type: "GET",
                data: {id:id,reason:reasonaaa},
                cache: false,
                async: false,
                success: function (message) {
                   		if (message.type == "success") {
							window.setTimeout(function() {
								window.location.reload();
							}, 1000);
						}
                }
            });
       }
        
               function showRefuse(id){
                    $("#reasonId").val(id);
                    $('#backReason').modal({
                    keyboard: true
                 })
       }
       
       function model1(id){
       	$("#key").val(id);
              $("#info").html("是否确定标记为已发放?");
                $('#prompt').modal({
                    keyboard: true
                })
       }
       
 
            function issue(){
             var id=$("#key").val();
         $.ajax({
                url: "issue.jhtml",
                type: "GET",
                data: {id:id},
                cache: false,
                async: false,
                success: function (message) {
                   		if (message.type == "success") {
							window.setTimeout(function() {
								window.location.reload();
							}, 1000);
						}
                }
            });
       }
    </script>
</body>

</html>
