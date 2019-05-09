
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 离职审核表管理</title>
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
                    	 <h5>离职审核表管理 </h5>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入帐号" name="filter_account" value="${(paramter.account)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入教师名称" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					             <select class="form-control" name="filter_type">
							  <option value="">请选择教师类型</option>
							   <option [#if  paramter.type==1] selected='selected'[/#if]  value ="1">全职教师</option>
							   <option [#if  paramter.type==2] selected='selected'[/#if]  value ="2">兼职教师</option>
						</select>
                            </div>
					        <div class="col-sm-3 m-b-xs">
					         <select class="form-control" name="filter_status">
							  <option value="">请选择状态</option>
							   <option [#if paramter.status==1] selected='selected'[/#if]  value ="1">已同意</option>
							   <option [#if  paramter.status==3] selected='selected'[/#if]  value ="3">未审核</option>
							   <option [#if  paramter.status==2] selected='selected'[/#if]  value ="2">已拒绝</option>
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
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>头像</th>
                                        <th>教师类型</th>
                                        <th>离职原因</th>
                                        <th>班级情况</th>
                                        <th>申请时间</th>
                                        <th>状态</th>
                                        <th>离职时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as leaveOffice] 
							        	 <tr>
					                        <td>${(leaveOffice.id)!''}</td>
					                        <td>${(leaveOffice.account)!''}</td>
					                         <td>${(leaveOffice.name)!''}</td>
					                        <td>${(leaveOffice.sex)!''}</td>
					                        <td><img src="${(leaveOffice.avatar)!''}" width="50px" height="50px"></td>
					                        <td>[#if leaveOffice.type==1]全职教师[#else]兼职教师[/#if]</td>
					                        <td>${(leaveOffice.reason)!''}</td>
					                        <td>${(leaveOffice.classCondition)!''}</td>
					                        <td>${(leaveOffice.applyTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>[#if leaveOffice.status==1]<font color="green">已同意</font>[#elseif leaveOffice.status==2]已拒绝[#else]<font color="red">未审核</font>[/#if]</td>
					                        <td>${(leaveOffice.leaveTime?string('yyyy-MM-dd HH:mm'))!''}</td>
					                        <td>
					                        [#if leaveOffice.status==3]
					                        <a href="javascript:appraise(${(leaveOffice.id)!''})"  class="btn btn-xs btn-default">同意</a>
					                         <a href="javascript:showRefuse(${(leaveOffice.id)!''})"  class="btn btn-xs btn-default">拒绝 </a>
					                        [#elseif leaveOffice.status==2]
					                          <a href="javascript:showRefuseReason(${(leaveOffice.id)!''})"class="btn btn-xs btn-default">拒绝原因</a>
					                          [#else]
					                          <a href="${base}/admin/leave_office/view/${(leaveOffice.teacherId)!''}/${(leaveOffice.orgId)!''}.jhtml" class="btn btn-xs btn-default">查看评论</a>
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

  <div class="modal inmodal" id="ssreason" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">拒绝原因</p>
            </div>
            <div class="modal-body">
                       <input type="hidden" id="reasonId" name="reasonId">
                        <div class="dataTables_wrapper form-inline dialog-table" id="sreason">
                       
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="affirmRefuse()">确定</button>
            </div>
        </div>
    </div>
</div>


 <div class="modal inmodal" id="appraise" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog" style="width: 500px">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="width:500px;height:50px">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <p  style="width:500px;height:50px">评价教师</p>
            </div>
            <div class="modal-body">
                       <input type="hidden" id="appraiseId" name="appraiseId">
                        <div class="dataTables_wrapper form-inline dialog-table" id="sreason">
                        	<table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td style="width:20%">评价星数</td>
																<td>  <select class="form-control" name="stars" id="stars">
																	   <option value ="1">一星</option>
																	   <option value ="2">二星</option>
																	   <option  value ="3">三星</option>
																	   <option value ="4">四星</option>
																	   <option   value ="5">五星</option>
																</select>
																</td>
															</tr>
															<tr>
																<td>评论内容</td>
																<td> 	<textarea rows="8" id="commen" name="commen" class="form-control" cols="40"></textarea></td>
															</tr>
															<tr>
																<td>选择标签</td>
																<td >
																[#list teacherCommentRemarks as teacherCommentRemark]
																		<input type="checkbox" name="remarks" value="${teacherCommentRemark.id}"><span>${teacherCommentRemark.name} </span>
																	[/#list]
																	</td>
															</tr>
														</tbody>
													</table>
                        
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary ok-set-user" onclick="affirmAppraise()">确定</button>
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
       
            function showRefuseReason(id){
           $.ajax({
                url: "showRefuseReason.jhtml",
                type: "GET",
                data: {id:id},
                cache: false,
                async: false,
                success: function (data) {
                     var tableCon = $("#sreason");
                    tableCon.empty();
                    tableCon.append(data);
                    $('#ssreason').modal({
                    keyboard: true
                 })
                }
            });
       }
       
         function appraise(id){
          $("#appraiseId").val(id);
           $('#appraise').modal({
                    keyboard: true
                 })
              }   
         function affirmAppraise(){
          var id=   $("#appraiseId").val();
          var commen=$("#commen").val();
            var stars=$("#stars").val();
        /*     var chenked = $("input[name='remarks']:checked");
                    var remarks = "";
                    for (var i = 0; i < chenked.length; i++) {
                        remarks += $(chenked[i]).val()+ ",";
                    }
                    */
                    var reg=new RegExp(" ","g"); //创建正则RegExp对象 
                  var  str = $("input:checkbox[name='remarks']:checked").next().text();
                  var newstr=str.replace(reg,"##"); 
         $.ajax({
                url: "affirmAppraise.jhtml",
                type: "GET",
                data: {id:id,commen:commen,remarks:newstr,stars:stars},
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
