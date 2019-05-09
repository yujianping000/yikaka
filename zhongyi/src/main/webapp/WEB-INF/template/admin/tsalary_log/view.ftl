<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 工资记录表详情</title>
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
                    	 <h5>工资记录表详情</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/tsalary_log/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="update.jhtml" method="post">
                  		<input type="hidden" value="${tSalaryLog.id}" name="id"/>
                        
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">用户id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.uid}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">机构id</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.orgId}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">底薪</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.basicSalary}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">课时</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.courseSalary}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">绩效</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.performanceSalary}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">全勤</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.allTsutomu}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">奖金</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.bonus}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">提成</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.pushMoney}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">报销</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.reimburse}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">其他</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.other}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.createTime}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">发放时间</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.payTime}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">状态 1已发放 2没发放</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.status}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">帐号</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.account}</p>
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">姓名</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.userName}</p>
	                                </div>
                       			</div>
							</div></div>
							<!--/row-->
							<div class="row">
                            <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">合计</label>
	                                <div class="col-sm-9">
	                                	<p class="form-control-static">${tSalaryLog.totalSalary}</p>
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
