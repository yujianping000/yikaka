
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>${setting.siteName} - 课程教学时间评论管理</title>
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
                    	 <h5>课程教学时间评论管理 </h5>
                    	 <div class="ibox-tools">
                    	 	<a class="btn btn-outline btn-success btn-xs" id="btn-add-loippi" href="add.jhtml"><i class="fa fa-plus"></i> 新增</a>
	                        <a class="btn btn-outline btn-danger btn-xs btn-delete-loippi-group"><i class="fa fa-trash"></i> 删除</a>
	                    </div>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入" name="filter_id" value="${(paramter.id)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入评论用户" name="filter_uid" value="${(paramter.uid)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入星星" name="filter_favStar" value="${(paramter.favStar)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入评论时间" name="filter_createDate" value="${(paramter.createDate)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入评价内容" name="filter_content" value="${(paramter.content)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入标签" name="filter_mark" value="${(paramter.mark)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程id" name="filter_courseId" value="${(paramter.courseId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入机构id" name="filter_orgId" value="${(paramter.orgId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入学生id" name="filter_teacherId" value="${(paramter.teacherId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入头像" name="filter_studentId" value="${(paramter.studentId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入头像" name="filter_avatar" value="${(paramter.avatar)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入评论人" name="filter_name" value="${(paramter.name)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程教学时间id" name="filter_timeId" value="${(paramter.timeId)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入课程上课时间" name="filter_courseTime" value="${(paramter.courseTime)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入上课时间1" name="filter_courseTime1" value="${(paramter.courseTime1)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入上课时间2" name="filter_courseTime2" value="${(paramter.courseTime2)!''}" class="input-sm form-control">
                            </div>
					        <div class="col-sm-3 m-b-xs">
					        <input type="text" placeholder="请输入上课时间-用于排序" name="filter_courseTime3" value="${(paramter.courseTime3)!''}" class="input-sm form-control">
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

                                        <th><input type="checkbox"  class="i-checks" name="checkAll"></th>
                                        <th></th>
                                        <th>评论用户</th>
                                        <th>星星</th>
                                        <th>评论时间</th>
                                        <th>评价内容</th>
                                        <th>标签</th>
                                        <th>课程id</th>
                                        <th>机构id</th>
                                        <th>学生id</th>
                                        <th>头像</th>
                                        <th>头像</th>
                                        <th>评论人</th>
                                        <th>课程教学时间id</th>
                                        <th>课程上课时间</th>
                                        <th>上课时间1</th>
                                        <th>上课时间2</th>
                                        <th>上课时间-用于排序</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	 [#list page.content as courseTimeComment] 
							        	 <tr>
					                        <td>
					                            <input type="checkbox" class="i-checks" name="ids" value="${courseTimeComment.id}">
					                        </td>
					                        <td>${(courseTimeComment.id)!''}</td>
					                        <td>${(courseTimeComment.uid)!''}</td>
					                        <td>${(courseTimeComment.favStar)!''}</td>
					                        <td>${(courseTimeComment.createDate?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(courseTimeComment.content)!''}</td>
					                        <td>${(courseTimeComment.mark)!''}</td>
					                        <td>${(courseTimeComment.courseId)!''}</td>
					                        <td>${(courseTimeComment.orgId)!''}</td>
					                        <td>${(courseTimeComment.teacherId)!''}</td>
					                        <td>${(courseTimeComment.studentId)!''}</td>
					                        <td>${(courseTimeComment.avatar)!''}</td>
					                        <td>${(courseTimeComment.name)!''}</td>
					                        <td>${(courseTimeComment.timeId)!''}</td>
					                        <td>${(courseTimeComment.courseTime?string('yyyy-MM-dd'))!''}</td>
					                        <td>${(courseTimeComment.courseTime1)!''}</td>
					                        <td>${(courseTimeComment.courseTime2)!''}</td>
					                        <td>${(courseTimeComment.courseTime3)!''}</td>
					                        <td>
					                        	<a href="${base}/admin/course_time_comment/view/${courseTimeComment.id}.jhtml" class="btn btn-xs btn-default"> ${message("admin.common.view")} </a>
					                        	
					                        	<a class="btn btn-info btn-xs btn-edit-loippi" data-id="${courseTimeComment.id}" href="edit/${courseTimeComment.id}.jhtml" ><i class="fa fa-paste"></i> ${message("admin.common.edit")} </a>
					                        	
					                        	<a class="btn btn-danger btn-xs btn-delete-loippi" data-id="${courseTimeComment.id}" ><i class="fa fa-trash"></i> ${message("admin.common.delete")} </a>
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
	
	<!-- BEGIN Script -->
		[#include "/admin/include/script.ftl"]
	<!-- END Script -->
    
    <script>
    
        $(document).ready(function () {
        	[@flash_message /]
        });
    </script>
</body>

</html>
