
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 机构订单管理</title>
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
                    	 <h5>订单管理 </h5>
                    	 <div class="ibox-tools">
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入订单编号" name="filter_bn" id="filter_bn" value="${(paramter.bn)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入下单账号" name="filter_phone" id="filter_phone" value="${(paramter.phone)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        	<div class="input-daterange input-group" id="datepicker">  
					        		<input type="text" placeholder="请输入下单时间" name="filter_sDate" id="filter_sDate" value="${(paramter.sDate)!''}"  class="input-sm form-control  ">
					        		<span class="input-group-addon">——</span>
					        		<input type="text" placeholder="请输入下单时间" name="filter_eDate" id="filter_eDate" value="${(paramter.eDate)!''}" class="input-sm form-control  "> 
                            	</div>
                            </div>        
					        <div class="col-sm-3 m-b-xs">
                               <select class="form-control m-b" name="filter_status" id="filter_status">
                                    <option value="">状态</option>
                        			<option value="1"  [#if paramter.filter_status == '1'] selected="selected"[/#if]>待付款</option>
                                	<option  value="2" [#if paramter.filter_status == '2'] selected="selected"[/#if]>已付款</option>
                                	<option  value="3" [#if paramter.filter_status == '3'] selected="selected"[/#if]>待评价</option>
                                	<option  value="4" [#if paramter.filter_status == '4'] selected="selected"[/#if]>已取消</option>
                                	<option  value="8" [#if paramter.filter_status == '8'] selected="selected"[/#if]>已完成</option>
                               </select>
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

                                        <th>订单编号</th>
                                        <th>下单账号</th>
                                        <th>课程名称</th>
                                        <th>订单金额（元）</th>
                                        <th>下单时间</th>
                                        <th>支付时间</th>
                                        <th>联系电话</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as order] 
							        	 <tr>
					                        <td>${(order.bn)!''}</td>
					                        <td>${(order.phone)!''}</td>
					                        <td>${(order.courseName)!''}</td>
					                        <td>${(order.totalFee)!''}</td>
					                        <td>${(order.createdTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>${(order.payTime?string('yyyy-MM-dd HH:mm:ss'))!''}</td>
					                        <td>${(order.phone)!''}</td>
					                        <td>
					                        	[#if order.status = 1]
					                        		待付款
					                        	[/#if] 
					                        	[#if order.status = 2]
					                        		已付款
					                        	[/#if] 
					                        	[#if order.status = 3]
					                        		待评价
					                        	[/#if] 
					                        	[#if order.status = 4]
					                        		已取消
					                        	[/#if] 
					                        	[#if order.status = 8]
					                        		已完成
					                        	[/#if] 
					                        </td>
					                        <td>
					                        	<a href="${base}/admin/org_order/view/${order.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
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
        	
        	//时间控件
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
