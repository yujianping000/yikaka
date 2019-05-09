
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
                	<form id="listForm" action="platform_list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>提现管理 </h5>
                    	  <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs tWithdrawExport"><i class="fa fa-file-excel-o"></i>导出</a>	
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
                    	 <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入帐号" name="filter_userAccnout" id="filter_userAccnout" value="${(paramter.userAccnout)!''}" class="input-sm form-control">
                            </div>
                    	                          <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">
					        		<input type="text" placeholder="请输入提现时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入提现时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>    
					        <div class="col-sm-3 m-b-xs">
					         <select class="form-control" name="filter_status" id="filter_status">
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
						   	<table class="table table-striped" style="border:0;width:50%" >
														<tbody>
															<tr>
																<td >提现金额(元)：<font color="orange" size="3">${tWithdraw.withdrawTotalInfo}</font></td>
																<td>手续费(%)：<font color="orange" size="3">${tWithdraw.procedureTotalInfo}</font></td>
																<td>到账金额(元)：<font color="orange" size="3">${tWithdraw.toAccountTotal}</font></td>
															</tr>
														</tbody>
													</table>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                         <th>帐号</th>
                                        <th>提现时间</th>
                                        <th>提现金额(元)</th>
                                        <th>手续费(%)</th>
                                        <th>到账金额(元)</th>
                                        <th>银行名称</th>
                                        <th>银行卡号</th>
                                        <th>持卡人</th>
                                        <th>备注</th>
                                        <th>状态</th>
                                           <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as tWithdraw] 
							        	 <tr>
					                        <td>${(tWithdraw.id)!''}</td>
					                        <td>${(tWithdraw.userAccnout)!''}</td>
					                        <td>${(tWithdraw.withdrawTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>${(tWithdraw.withdrawTotal)!''}</td>
					                        <td>${(tWithdraw.procedureTotal)!''}</td>
					                        <td>${(tWithdraw.toAccountTotal)!''}</td>
					                        <td>${(tWithdraw.bankName)!''}</td>
					                        <td>${(tWithdraw.bankNum)!''}</td>
					                        <td>${(tWithdraw.bankUserName)!''}</td>
					                        <td>${(tWithdraw.note)!''}</td>
					                        <td>[#if tWithdraw.status=1]已完成[#elseif tWithdraw.status=2]待审批[#else]已拒绝[/#if]</td>
					                         <td>
					                           [#if tWithdraw.status==2]
					                             <a href="javascript:affirmPass(${(tWithdraw.id)!''})" class="btn btn-xs btn-default">通过 </a>
					                        	
					                        		<a href="javascript:showRefuse(${(tWithdraw.id)!''})" class="btn btn-xs btn-default"> 拒绝</a>
					                        		[#else]
					                        		---
					                        		[/#if]	
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
    
     <div class="modal inmodal" id="backReason" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">填写拒绝原因</p>
            </div>
            <div class="modal-body">
               <input type="hidden" id="reasonId" name="reasonId">
                        <div class="dataTables_wrapper form-inline ">
                                  <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static">拒绝原因:</p><textarea rows="8" id="reasonaaa" name="reasonaaa" class="form-control" cols="40"></textarea>
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
			       		//导出提现管理表
			 $(".tWithdrawExport").click(function(){
			
			    var filter_userAccnout = $("#filter_userAccnout").val();
			    var filter_sDate = $("#filter_sDate").val();
			    var filter_eDate = $("#filter_eDate").val();
			    var filter_status = $("#filter_status").val();

			 	window.open('tWithdrawExport.jhtml?filter_userAccnout='+filter_userAccnout
			 			+'&filter_sDate='+filter_sDate
			 			+'&filter_eDate='+filter_eDate
			 			+'&filter_status='+filter_status
						,'_blank');
		    }); 
			
        });
                function affirmPass(id){
               $.ajax({
                url: "affirmPass.jhtml",
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
       

  
    </script>
</body>

</html>
