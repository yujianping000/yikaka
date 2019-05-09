
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 主页</title>
    <meta name="keywords" content="${setting.siteName}">
  	<meta name="description" content="${setting.siteName}">

  	<!-- BEGIN HEADER -->
		[#include "/admin/include/style.ftl"]
		<script src="${base}/resources/js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- END HEADER -->
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	 <h3 style="color:#3C763D"><i class="fa fa fa-th-list fa-1x"></i>修改密码</h3>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputModalForm" class="form-horizontal" action="updatePwd.jhtml" method="post">
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
	                               <label class="col-sm-3 control-label">新密码<span class="required"> * </span></label>
	                                <div class="col-sm-9">
											<input type="password" class="form-control" name="password" id="password" placeholder="请输入新密码" >
	                                </div>
                       			</div>
                       		</div>
                        </div>
                        
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
	                               <label class="col-sm-3 control-label">确认密码<span class="required"> * </span></label>
	                                <div class="col-sm-9">
											<input type="password" class="form-control" name="repassword" id="repassword" placeholder="请再次输入新密码" >
	                                </div>
                       			</div>
                       		</div>
                        </div>
                       
                        <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-3">
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
    
    	 //修改密码校验
        	jQuery("#inputModalForm").validate({
                rules: {
                   password: {
	                    minlength: 6,
	                    required: true
	                },
                   repassword: {
	                    equalTo:'#password',
	                    minlength: 6,
	                    required: true
	                }
	              },
    			  messages: {
	                  password:{
	                       equalTo:"两次密码输入不一致"
	                   }      
				}
            });
        	 
    	
    
        $(document).ready(function () {
        	[@flash_message /]
        	
        	
        });
        
    </script>
</body>

</html>
