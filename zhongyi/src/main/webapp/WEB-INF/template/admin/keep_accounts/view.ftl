<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 手机记账表详情</title>
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
                    	 <h5>手机记账表详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/keep_accounts/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${keepAccounts.id}" name="id"/>
                        
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">机构id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.orgId}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">记账时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.tallyTime}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">数量</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.number}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">支付时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.payTime}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">单价</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.unit-price}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">部门</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.department}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">金额</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.amountMoney}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">申请人</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.applyUser}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">交易方式</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.dealWay}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">项目</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.project}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">明细</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.detail}</p>
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
	                                	<p class="form-control-static">${keepAccounts.note}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${keepAccounts.crateTime}</p>
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
