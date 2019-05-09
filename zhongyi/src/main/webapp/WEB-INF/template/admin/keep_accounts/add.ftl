<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增手机记账表</title>
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
                    	 <h5>新增手机记账表</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/keep_accounts/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="post">
                      
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">机构id</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="orgId" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">记账时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="tallyTime" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">数量</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="number" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">支付时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="payTime" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">单价</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="unit-price" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">部门</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="department" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">金额</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="amountMoney" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">申请人</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="applyUser" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">交易方式</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="dealWay" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">项目</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="project" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">明细</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="detail" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">备注</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="note" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">创建时间</label>
	                                <div class="col-sm-9">
	                                    <input type="text" name="crateTime" class="form-control" maxlength="200" />
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
