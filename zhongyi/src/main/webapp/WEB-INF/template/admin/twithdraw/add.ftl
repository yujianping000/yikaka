<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增tWithdraw</title>
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
                    	 <h5>新增提现</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/twithdraw/list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="save.jhtml" method="get">
                        <input type="hidden" name="status" value="2" class="form-control" maxlength="200" />
                         <input type="hidden" id="validTotal" name="validTotal" value="${validTotal.total}" class="form-control" maxlength="200" />
                         
                           <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">可提现金额</label>
	                                <div class="col-sm-9">
	                                 	 <font color="orange" size="4">  [#if validTotal??]${validTotal.total}[#else]0[/#if]元</font>
	                                </div>
                       			</div>
							</div>
                        </div>
                         
					
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">提现金额</label>
	                                <div class="col-sm-9">
	                                    <input type="text" id="withdrawTotal" name="withdrawTotal" class="form-control" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">提现到</label>
	                                <div class="col-sm-9">
	                                      <select class="form-control"  id="tBankId" name="tBankId">
							  <option value="" >请选择银行帐号</option>
					 [#list lists as tBank] 
							  <option  value ="${tBank.id}">${tBank.bankName}(${tBank.number})</option>
					
						[/#list]
							</select>
	                                </div>
                       			</div>
							</div>
                        </div>
							<!--/row-->
							<div style="border: 1px dashed #eb604b;width: 490px;padding: 15px 30px 15px;margin: 10px 45px;">1.提现金额不能超过可提现金额，最少提现金额100元</div>

						
                          <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">手续费%</label>
	                                <div class="col-sm-9">
	                                	<input type="text" readonly="readonly" id="procedureTotal" name="procedureTotal" class="form-control" value="${orgnization.fee}" maxlength="200" />
	                                </div>
                       			</div>
							</div>
                        </div>
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
            	$("#inputForm").validate({
        	  submitHandler: function (form) {
        	    var validTotal=$('#validTotal').val();
        	    var withdrawTotal=$('#withdrawTotal').val();
        	     if(parseInt(validTotal,10) < parseInt(withdrawTotal,10)){
        	         alert("提现金额不能大于可提金额");
        	         return;
        	     }
        	     form.submit();
        	     },
                rules: {
                  withdrawTotal: {
						maxlength: 10,
						number: true
					},
					 procedureTotal: {
						required: true
					},
					 tBankId: {
						required: true
					},
					
                },
                messages: {
				}
            });
        $(document).ready(function () {
        	[@flash_message /]
        	$(".chosen-select").chosen({width:"100%"}); 
        	$(".input-daterange").datepicker({keyboardNavigation:false,forceParse:false,autoclose:true});
        	
        	$(".browserButton").browser({callback:function(url){
				$("#"+$(this).attr('for')).val(url);
			}});
        	
    		$(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});

        });
    </script>
</body>
</html>
