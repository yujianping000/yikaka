<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 机构二维码</title>
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
                    	 <h5>机构二维码</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='${base}/admin/common/index.jhtml'"><i class="fa fa-reply-all"></i> 返回首页</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="inputForm" class="form-horizontal" action="${base}/admin/common/downloadOrgQrcode.jhtml" method="get" >
                  		<input type="hidden" value="${(orgnization.id)!''}" name="id"/>
                  		<input type="hidden" value="${(orgnization.orgImage)!''}" id="orgImage" name="orgImage"/>
                      	<div class="ibox-content">
	                  	  	 <img id="image" name="image" alt="image" class="img-circle" style="height:250px;width:250px;" src="${(orgnization.orgImage)!''}">
	              		</div>
	              		
						
							<!--/row-->
						
                        <div class="hr-line-dashed"></div>
	                    <div class="form-group">
	                        <div class="col-sm-12 col-sm-offset-10">
	                        	<button type="button" class="btn btn-default" onclick="location.href='${base}/admin/common/index.jhtml'">返回</button>
                        		<button class="btn btn-success" type="sumbit">下载二维码</button>
	                        </div>
	                    </div>
	                    </form>
                  	</div>
                </div>
            </div>
        </div>
    </div>
    
    <iframe name="I1" style="display:none"></iframe> 
    
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

           
        });
	</script>
	
</body>
</html>
