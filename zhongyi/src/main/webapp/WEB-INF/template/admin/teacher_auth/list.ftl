
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 老师认证管理</title>
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
                    	 <h5>老师认证管理 </h5>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入帐号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
                               <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入姓名" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
                              <div class="col-sm-3 m-b-xs">
					             <select class="form-control" name="filter_type">
							  <option selected='selected'  value ="">请选择教师类型</option>
							   <option [#if  paramter.type==1] selected='selected'[/#if]  value ="1">全职教师</option>
							   <option [#if  paramter.type==2] selected='selected'[/#if]  value ="2">兼职教师</option>
						</select>
                            </div>
					        <div class="col-sm-3 m-b-xs">
					         <select class="form-control" name="filter_status">
							  <option selected='selected' value ="">请选择状态</option>
							   <option [#if  paramter.status==1] selected='selected'[/#if]  value ="1">已同意</option>
							   <option [#if  paramter.status==2] selected='selected'[/#if]  value ="2">未审核</option>
							   <option [#if  paramter.status==3] selected='selected'[/#if]  value ="3">已拒绝</option>
					    	   <option [#if  paramter.status==4] selected='selected'[/#if]  value ="4">已离职</option>
						</select>
                            </div>
                            <div class="col-sm-1">
                                <div class="input-group">
                                     <button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
						   <div class="dataTables_wrapper form-inline">
                            <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
                                <thead>
                                    <tr>
                                        <th>帐号</th>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>头像</th>
                                        <th>类型</th>
                                        <th>申请时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as teacherAuth] 
							        	 <tr>
							        	   <td>${(teacherAuth.account)!''}</td>
					                        <td>${(teacherAuth.name)!''}</td>
					                         <td>${(teacherAuth.sex)!''}</td>
					                         <td><img src="${(teacherAuth.avatar)!''}" width="70px" height="50px"></td>
					                         <td>[#if teacherAuth.type==1]全职教师[#else]兼职教师[/#if]</td>
					                        <td>${(teacherAuth.createTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                             <td>[#if teacherAuth.status==1]<font color="green">已同意</font>[#elseif teacherAuth.status==3]已拒绝[#elseif teacherAuth.status==4]已离职[#else]<font color="red">未审核</font>[/#if]</td>
					                        <td>
					                            [#if teacherAuth.status==2]
					                        	    <a href="javascript:showPass(${(teacherAuth.id)!''},${(teacherAuth.account)!''},'${(teacherAuth.name)!''}')" class="btn btn-xs btn-default">通过 </a>
					                        	
					                        		<a href="javascript:showRefuse(${(teacherAuth.id)!''})" class="btn btn-xs btn-default"> 拒绝</a>
					                        	
					                        		  	<a href="${base}/admin/teacher_auth/view/${teacherAuth.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        [#else]
					                             	<a href="${base}/admin/teacher_auth/view/${teacherAuth.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        [/#if]				                       
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
    
      <div class="modal inmodal" id="backReason" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">填写拒绝原因</p>
            </div>
            <div class="modal-body">
                       <input type="hidden" id="reasonId" name="reasonId">
                        <div class="dataTables_wrapper form-inline dialog-table">
                           <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static">拒绝原因:</p><textarea rows="8" id="reasonaaa" name="reasonaaa" class="form-control" cols="40"></textarea>
	                                </div>

                       			  </div>
                       			  
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="affirmRefuse()">确定</button>
            </div>
        </div>
    </div>
</div>

 <div class="modal inmodal" id="prompt" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">温馨提示</p>
            </div>
            <div class="modal-body">
                       <input type="hidden" id="keyid" name="keyid">
                        <div class="dataTables_wrapper form-inline dialog-table">
                           <div class="row">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static" id="info"></p>
	                                </div>

                       			  </div>
                       			  
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="affirmPass()">确定</button>
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
        });
           function showRefuse(id){
          $("#reasonId").val(id);
           $('#backReason').modal({
                    keyboard: true
                 })
              }   
         function affirmRefuse(){
         var id=   $("#reasonId").val();
          var reasonaaa=$("#reasonaaa").val();
         $.ajax({
                url: "affirmRefuse.jhtml",
                type: "GET",
                data: {id:id,reason:reasonaaa},
                cache: false,
                async: false,
                       success: function (message) {
                   		if (message.type == "success") {
							window.setTimeout(function() {
								window.location.reload();
							}, 1000);
						}
                }
            });
       }
       
          function showPass(id,account,name){
              $("#keyid").val(id);
              $("#info").html("是否同意"+'<font color="orange">'+name+"("+account+")"+"</font>"+"加入机构?");
                $('#prompt').modal({
                    keyboard: true
                })
        }
        
           function showPass(id,account,name){
              $("#keyid").val(id);
              $("#info").html("是否同意"+'<font color="orange">'+name+"("+account+")"+"</font>"+"加入机构?");
                $('#prompt').modal({
                    keyboard: true
                })
        }
        
           function affirmPass(){
            var id=$("#keyid").val();
               $.ajax({
                url: "affirmPass.jhtml",
                type: "GET",
                data: {id:id},
                cache: false,
                async: false,
                success: function (message) {
                          if (message.type == "success") {
							window.setTimeout(function() {
								window.location.reload();
							}, 1000);
						}
                }
            });
        }
    </script>
</body>

</html>
