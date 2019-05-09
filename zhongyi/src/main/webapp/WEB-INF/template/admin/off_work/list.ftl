
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 请假管理</title>
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
                    	 <h5>请假管理 </h5>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
                               <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="帐号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
                               <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="昵称" name="filter_nickName" value="${(paramter.nickName)!''}" class="input-sm form-control">
                            </div>
                            	        <div class="col-sm-3 m-b-xs">
					            <select class="form-control" name="filter_userType">
							  <option value="">请选择人员类型</option>
							  <option [#if  paramter.userType==2] selected='selected'[/#if]  value ="2">家长</option>
							   <option [#if  paramter.userType==1] selected='selected'[/#if]  value ="1">教师</option>
						</select>
                            </div>
                            
                             <div class="col-sm-3 m-b-xs">
					            <select class="form-control" name="filter_status">
							  <option value="" >请选择请假状态</option>
							  <option [#if  paramter.status==1] selected='selected'[/#if]  value ="1">已通过</option>
							   <option [#if  paramter.status==2] selected='selected'[/#if]  value ="2">未通过</option>
							      <option [#if  paramter.status==3] selected='selected'[/#if]  value ="3">已拒绝</option>
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
                                        <th>ID</th>
                                        <th>帐号</th>
                                        <th>昵称</th>
                                        <th>身份</th>
                                        <th>请假时间</th>
                                        <th style="width:19%">请假事由</th>
                                        <th>提交时间</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as offWork] 
							        	 <tr>
					                        <td>${(offWork.id)!''}</td>
					                        <td>${(offWork.account)!''}</td>
					                         <td>${(offWork.nickName)!''}</td>
					                        <td>[#if offWork.userType==1]教师[#elseif offWork.userType==2]家长[/#if]</td>
					                           <td>${(offWork.start?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(offWork.description)!''}</td>
					                         <td>${(offWork.createTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>[#if offWork.status==1]<font color="green">已同意</font>[#elseif offWork.status==2 ]<font color="red">未通过</font>[#else]已拒绝[/#if]</td>
					                        <td>
					                        [#if offWork.status==2]
					                        	    <a href="javascript:showPass(${(offWork.id)!''})" class="btn btn-xs btn-default">通过 </a>
					                        	
					                        		<a href="javascript:showRefuse(${(offWork.id)!''})" class="btn btn-xs btn-default"> 拒绝</a>
					                        	
					                        		<a  href="javascript:showView(${(offWork.id)!''})" class="btn btn-xs btn-default"> 查看 </a>
					                        [#else]
					                             <a  href="javascript:showView(${(offWork.id)!''})" class="btn btn-xs btn-default"> 查看 </a>
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
	    <div class="modal inmodal" id="confirmLeave" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">确认请假天数</p>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="row">
                        <div class="dataTables_wrapper form-inline dialog-table">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user"  onclick="affirmPass()">确定</button>
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
                        <div class="dataTables_wrapper form-inline ">
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
    <div class="modal inmodal" id="detail" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
      <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">查看详情</p>
            </div>
            <div class="modal-body">

                        <div class="dataTables_wrapper form-inline dialog-table">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
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
        
        
        function showPass(id){
               $.ajax({
                url: "showPass.jhtml",
                type: "GET",
                data: {id:id},
                cache: false,
                async: false,
                success: function (data) {
                     var tableCon = $(".dialog-table");
                    tableCon.empty();
                    tableCon.append(data);
                    $('#confirmLeave').modal({
                    keyboard: true
                 })
                }
            });
        }
       function showRefuse(id){
                    $("#reasonId").val(id);
                    $('#backReason').modal({
                    keyboard: true
                 })
       }
       function showView(id){
           $.ajax({
                url: "viewDetails.jhtml",
                type: "GET",
                data: {id:id},
                cache: false,
                async: false,
                success: function (data) {
                     var tableCon = $(".dialog-table");
                    tableCon.empty();
                    tableCon.append(data);
                    $('#detail').modal({
                    keyboard: true
                 })
                }
            });
       }
       
        function affirmPass(){
            var id=$("#passId").val();
                 var dayNum=$("#dayNum").val();
               $.ajax({
                url: "affirmPass.jhtml",
                type: "GET",
                data: {id:id,dayNum:dayNum},
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
       function affirmRefuse(){
         var id=$("#reasonId").val();
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
       
    </script>
</body>

</html>
