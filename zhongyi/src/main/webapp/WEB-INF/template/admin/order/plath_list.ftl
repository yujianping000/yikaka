
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 订单表管理</title>
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
                	<form id="listForm" action="plath_list.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>订单表管理 </h5>
                    	 <div class="ibox-tools">
                    	 <a class="btn btn-outline btn-success btn-xs tOrderExport"><i class="fa fa-file-excel-o"></i>导出</a>	
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					          	<table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td >总收入（元）<br><font color="orange" size="6">${totalfee}</font></td>
																<td>线上总收入（元）<br><font color="orange" size="6">${totalOn}</font></td>
																<td>线下总收入（元）<br><font color="orange" size="6">${totalOff}</font></td>
																<td>总订单数<br><font color="orange" size="6">${totalNum}</font></td>
																<td>线上总订单数<br><font color="orange" size="6">${totalNumOn}</font></td>
																<td>线下总订单数<br><font color="orange" size="6">${totalNumOff}</font></td>
															</tr>
															    <td >今日收入（元）<br><font color="orange" size="6">${totalfeeday}</font></td>
																<td>今日线上收入（元）<br><font color="orange" size="6">${totalOnday}</font></td>
																<td>今日线下收入（元）<br><font color="orange" size="6">${totalOffday}</font></td>
																<td>今日订单数<br><font color="orange" size="6">${totalNumday}</font></td>
																<td>今日线上订单数<br><font color="orange" size="6">${totalNumOnday}</font></td>
																<td>今日线下订单数<br><font color="orange" size="6">${totalNumOffday}</font></td>
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
					            <select class="form-control" name="filter_payType" id="filter_payType">
							  <option value="">请选择支付方式</option>
							  <option [#if  paramter.payType==1] selected='selected'[/#if]  value ="1">支付宝</option>
							   <option [#if  paramter.payType==2] selected='selected'[/#if]  value ="2">微信</option>
							   <option [#if  paramter.payType==3] selected='selected'[/#if]  value ="3">银联</option>
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
						        [#if tallyType==1]
						                   <table class="table table-striped" style="border:0;width:30%" >
														<tbody>
														<tr>
																<td >总金额：<font color="orange" size="3">${orderInfo.totalFeeInfo}</font></td>
																</tr>
														</tbody>
													</table>
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>类型</th>
                                        <th>机构名称</th>
                                        <th>订单编号</th>
                                         <th>帐号</th>
                                        <th>姓名</th>
                                        <th>课程/活动名称</th>
                                        <th>订单金额</th>
                                        <th>支付方式</th>
                                        <th>交易流水号</th>
                                         <th>支付时间</th>
                                        <th>状态</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as order] 
							        	 <tr>
							        	     <td>[#if order.type==1]课程订单[#elseif order.type==2]活动订单[/#if]</td>
					                        <td>${(order.orgName)!''}</td>
					                        <td>${(order.bn)!''}</td>
					                        <td>${(order.phone)!''}</td>
					                        <td>${(order.unickname)!''}</td>
                                            <td>${(order.courseName)!''}</td>
                                            <td>${(order.totalFee)!''}</td>
                                             <td>[#if order.payType==1]支付宝[#elseif order.payType==2]微信[#elseif order.payType==3]银联[/#if]</td>
					                        <td>${(order.serialNumber)!''}</td>
					                        <td>${(order.payTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>${(order.statusString)!''}</td>
					                    </tr>
							        [/#list] 
                                </tbody>
                            </table>
                            [#elseif tallyType==2]
                            <table class="table table-striped" style="border:0;width:30%" >
														<tbody>
														<tr>
																<td >总金额：<font color="orange" size="3">${orderInfo.totalFeeInfo}</font></td>
																</tr>
														</tbody>
													</table>
                                 <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                         <th>机构名称</th>
                                         <th>帐号</th>
                                        <th>姓名</th>
                                         <th>联系电话</th>
                                        <th>课程名称</th>
                                        <th>订单金额</th>
                                        <th>支付方式</th>
                                        <th>交易流水号</th>
                                            <th>支付时间</th>
                                           <th>备注</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as order] 
							        	 <tr>
							        	   <td>${(order.orgName)!''}</td>
					                        <td>${(order.phone)!''}</td>
					                        <td>${(order.unickname)!''}</td>
					                           <td>${(order.phone)!''}</td>
                                            <td>${(order.courseName)!''}</td>
                                            <td>${(order.totalFee)!''}</td>
                                             <td>[#if order.payType==1]支付宝[#elseif order.payType==2]微信[#else]银联[/#if]</td>
					                        <td>${(order.serialNumber)!''}</td>
					                             <td>${(order.payTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                       <td>${(order.remark)!''}</td>
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
                <p  style="width:500px;height:50px">修改备注</p>
            </div>
            <div class="modal-body">
               <input type="hidden" id="orderId" name="orderId">
                        <div class="dataTables_wrapper form-inline ">
                                  <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static">备注:</p><textarea rows="8" id="remark" name="remark" class="form-control" cols="40"></textarea>
	                                </div>

                       			  </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="editRemark()">确定</button>
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
			
			//导出收入管理表
			 $(".tOrderExport").click(function(){
			
			    var filter_payType = $("#filter_payType").val();
			    var filter_sDate = $("#filter_sDate").val();
			    var filter_eDate = $("#filter_eDate").val();
			    var filter_tallyType = $("#filter_tallyType").val();

			 	window.open('tOrderExport2.jhtml?filter_payType='+filter_payType
			 			+'&filter_sDate='+filter_sDate
			 			+'&filter_eDate='+filter_eDate
			 			+'&filter_tallyType='+filter_tallyType
						,'_blank');
		    }); 
			
			
			
			
        });
        
            function showRemark(id){
                      $("#orderId").val(id);
                    $('#backReason').modal({
                    keyboard: true
                 })
       }
        
        function editRemark(){
        var id= $("#orderId").val();
        var remark=$("#remark").val();
               $.ajax({
                url: "editRemark.jhtml",
                type: "GET",
                data: {id:id,remark:remark},
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
