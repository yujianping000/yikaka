<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - tWithdraw详情</title>
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
                    	 <h5>tWithdraw详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/twithdraw/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${tWithdraw.id}" name="id"/>
                        
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">提现时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.withdrawTime}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">提现现金</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.withdrawTotal}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">手续费</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.procedureTotal}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">金额</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.toAccountTotal}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">银行名称</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.blankName}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">银行卡号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.blankNum}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">持卡人</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.blankUserName}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">备注</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.note}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态 1已完成 2待审批 3已拒绝</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tWithdraw.status}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                       		
                         <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="window.history.back();">返回</button>
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
</body>
</html>
