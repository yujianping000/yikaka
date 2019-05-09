<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 编辑支出统计</title>
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
                    <div class="ibox-title">
                    	 <h5>编辑支出统计</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/payment_statistics/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/payment_statistics/update.jhtml" method="post">
                  		<input type="hidden" value="${paymentStatistics.}" name=""/>
                       
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="id" class="form-control" maxlength="200" value="${(paymentStatistics.id)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">系统工资</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="onlineSalary" class="form-control" maxlength="200" value="${(paymentStatistics.onlineSalary)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">线上提现</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="onlineWithdraw" class="form-control" maxlength="200" value="${(paymentStatistics.onlineWithdraw)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">手动记账</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="offlineAccount" class="form-control" maxlength="200" value="${(paymentStatistics.offlineAccount)!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="orgId" class="form-control" maxlength="200" value="${(paymentStatistics.orgId)!''}"/>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"></label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="createTime" class="form-control" maxlength="200" value="${(paymentStatistics.createTime?string('yyyy-MM-dd'))!''}"/>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							
                       		
                       
                         <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
	                            <button class="btn btn-success" type="submit">保存内容</button>
	                        </div>
	                    </div>
	                    </form>
                  	</div>
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
        	$(".chosen-select").chosen({width:"100%"}); 
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        	$("#inputForm").validate({
               rules: {
                },
                messages: {
				}
            });
        });
    </script>
</body>
</html>
