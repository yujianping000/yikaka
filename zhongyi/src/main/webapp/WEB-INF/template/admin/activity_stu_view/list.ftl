
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 报名情况</title>
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
                	<form id="listForm" action="${base}/admin/activity_stu_view/list/${id}.jhtml" method="get">
                    <div class="ibox-title">
                    	 <h5>报名情况</h5>
                    	 <div class="ibox-tools">
	                         <a class="btn btn-outline btn-success btn-xs" id="batchImport" onclick="batchImport()">成绩批量导入</a>
	                         <a class="btn btn-outline btn-success btn-xs" id="download"   href="${base}/resources/plugins/teacher.xlsx">模版下载</a>	                       
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入账号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
					      
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入孩子姓名" name="filter_stuName" value="${(paramter.stuName)!''}" class="input-sm form-control">
                            </div>
					        
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入参赛号" name="filter_activityNumber" value="${(paramter.activityNumber)!''}" class="input-sm form-control">
                            </div>
					        
                            <div class="col-sm-2">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                                                                          　　<button type="button" class="btn  btn-sm btn-white" 
                    	 	onclick="location.href='${base}/admin/activity/list.jhtml'"><i class="fa fa-reply-all"></i> 返回活动列表</button> </span>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th>账号</th>
                                        <th>参赛号</th>
                                        <th>联系电话</th>
                                        <th>备注</th>
                                        <th>孩子姓名</th>
                                        <th>性别</th>
                                        <th>孩子头像</th>
                                        <th>出生日期</th>
                                        <th>报名时间</th>
                                        <th>成绩</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as activityStuView] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${activityStuView.id}">
					                        </td>
					                        <td>${(activityStuView.account)!''}</td>
					                        <td>${(activityStuView.activityNumber)!''}</td>
					                        <td>${(activityStuView.phone)!''}</td>
					                        <td>${(activityStuView.remark)!''}</td>
					                        <td>${(activityStuView.stuName)!''}</td>
					                         <td>${(activityStuView.stuSex)!''}</td>
					                        <td>
					                        <img alt="image" class="img-circle" style="height:50px;width:50px;" src="${(activityStuView.stuAvatar)!''}"></td>
					                        <td>${(activityStuView.stuBirthday?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(activityStuView.createdTime?string('yyyy-MM-dd  HH:mm'))!''}</td>
					                   
					                        <td>${(activityStuView.activityPrize)!''}</td>
					                        <td>
					                        	
					                        	<a class="btn btn-xs btn-success btn-edit-loippi btn-edit-loippi-pwd" data-toggle="modal" data-id="${activityStuView.id}" data-target="#myModal"><i class="fa fa-paste"></i>录入成绩</a>

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
	
	<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
            	<div class="modal-content animated bounceInRight">
            	
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    	<h4 class="modal-title" id="myModalLabel">录入成绩</h4>
                	</div>
                	
                	<form id="inputModalForm" class="form-horizontal" action="${base}/admin/org_teacher/updatePwd.jhtml" method="post" >
		                <div class="modal-body">
		                    <div class="form-group">
		                    	<input type="hidden" value="" id="orderId" name="orderId"/>
		                    	<label>参赛号</label><span style="color:#F00">(*)</span>
		                        <input  type="text"" name="activityNumber" id="activityNumber" placeholder="请输入参赛号" class="form-control" >
		                        </br>
		                        <label>获奖情况</label><span style="color:#F00">(*)</span>
		                        <input  type="text" name="activityPrize" id="activityPrize" placeholder="请输入获奖情况" class="form-control"  >
		                        </br>  
		                    </div>
		                </div>
                
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                    <button type="button" class="btn btn-success updatePrize" id="updatePrize">保存</button>
		                </div>
	              </form> 
	               
                </div>
            </div>
         </div>
	</div>
	
	 <div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <small class="font-bold">导入文件
            </div>
            <div class="modal-body">
                <div class="form-group">
                <form id="importForm" action="${base}/admin/activity_stu_view/activityPrize/${id}.jhtml" method="post" enctype="multipart/form-data">
                <input type="file" id="batchFileMessage" name="batchFileMessage" />
                <input name="imp_clazz" type="hidden" id="imp_clazz">
            </form>
    
                </div>
                </br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-white" onclick="saveInfo(0)">保存
                </button>
            </div>
        </div>
    </div>
</div>
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    
     	             function batchImport(){
                $("#myModal2").modal('show');
             }
             
                                       

		    	
    
        $(document).ready(function () {
        	[@flash_message /]
        	
        	 $(".btn-edit-loippi-pwd").click(function(){
			var id=$(this).attr('data-id');
			$("#orderId").val(id);
		
             });
        	
        	
        				$(".updatePrize").click(function(){
        				
		    		var orderId=$("#orderId").val();
		    		var activityNumber=$("#activityNumber").val();
		    		var activityPrize=$("#activityPrize").val();
				 		$.ajax({
							url: "${base}/admin/activity_stu_view/updatePrize.jhtml",
							type: "POST",
							data: {id:orderId,activityNumber:activityNumber,activityPrize:activityPrize},
							dataType: "json",
							cache: false,
							success: function(message) {
								window.setTimeout(function() {
									window.location.reload();
								}, 1000);
							}
						});
		    	});
		    	
		    	
		   

		    	
        	
        });
        
                     function saveInfo(){
             
              $('#importForm').ajaxSubmit({
                beforeSubmit: function () {
                    var filepath = $("input[name='batchFileMessage']").val();
                    var extStart = filepath.lastIndexOf(".");

                    var ext = filepath.substring(extStart, filepath.length).toLowerCase();
                    if (ext != ".xls") {
                        var data = '{"type":"error","content":"文件格式仅限于xls"}'
                        art.message($.parseJSON(data));
                        return false;
                    }
                },
                success: function (message) {
                    alert("操作成功!");
                    window.setTimeout(function () {
                    window.location.reload();
                    }, 1000);
                }
                });
             
             }
    </script>
</body>

</html>
