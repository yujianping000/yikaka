
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
	<!-- END HEADER -->
  	
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                    	 <!--【改标题：描述—】-->
                    	 <h5>新增内容</h5>
                    	 <div class="ibox-tools">
                    	 	<button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi" 
                    	 	onclick="location.href='list.jhtml'"><i class="fa fa-reply-all"></i> 返回列表</button>
                        </div>
                    </div>            	
                  	<div class="ibox-content">
                  		<form id="saveAclForm" class="form-horizontal" action="save.jhtml" method="post">
                  		<!--【改实体名：描述—】-->
                  		<input type="hidden" value="" name="id"/>
                  		<div class="form-group">
                  		 <!--【改控件：描述—】-->
                       		<div class="row">
                       			 <div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>标题</label>
	                                <div class="col-sm-9">
	                                	 <!--【改控件：描述—】-->
	                                	 <input type="text" name="title" id="title" class="form-control" value=""> 	                                  
	                                </div>
                       			</div>
                       		</div>
                        </div>
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>分类</label>
	                           	    <div class="col-sm-9">
	                                     <select name="categoryId" id="categoryId" data-placeholder="选择类型..." class="chosen-select"  style="width:350px;" >
                                     		 [#list articleCategorys as articleCategory]
       						 	<option value="${articleCategory.id }">${articleCategory.name}</option>
    						  [/#list]
						</select>
	                                </div>
                       			</div>
                       		</div>
                        </div>
                       
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>封面图片</label>
	                           	    <div class="col-sm-9">
	                                    	       <div class="input-group">
											<input type="text" class="form-control" name="image" id="avatar" value="" >
											<span class="input-group-btn">
												<button class="btn btn-white" type="button" id="browserButton"><i class="fa fa-send-o"></i>&nbsp;选择</button>
											</span>
							</div>
	                                    	
	                                </div>
                       			</div>
                       		</div>
                        </div>
                       
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label"><span class="required"> * </span>封面图片预览</label>
	                           	       <div class="col-sm-9">
	                                   		 <div class="img-preview-style"> <img id="preview_img"  src="" /></div>
	                               	  	 </div>
                       			</div>
                       		</div>
                        </div>
                        
                        <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">是否立刻发布</label>
	                                <div class="col-sm-9">	                                	
	                           		 <input type="checkbox"   class="js-switch js-check-change"  checked/> 
	                              		<input type="hidden"  name="show" id="isShow" value="" /> 
	                               </span>
	                                </div>
                       			</div>
                       		</div>
                        </div>
                  <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">发布时间</label>
	                                <div class="col-sm-9">	                                	
						<div class="input-group date" >
                            				    	<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                           				     	<input type="text" class="form-control"  name="publishDate" value="[#if publishDate?exists]${publishDate?string('yyyy-MM-dd ')}[/#if]" />
                         		        </div>
	                               </div>
	                                </div>
                       			</div>
                       		</div>
                         <div class="form-group">
                       		<div class="row">
                       			<div class="col-sm-6">
                       			 	<label class="col-sm-3 control-label">介绍内容</label>
	                                <div class="col-sm-9">
					    <textarea id="editor" name="content" class="editor"></textarea>
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
    
        $(document).ready(function () {
        	[@flash_message /]
			$("#browserButton").browser({callback:function(url){
				$("#avatar").val(url);
				$(".img-preview-style img").attr('src',url);
			}});
        	$(".chosen-select").chosen({width:"100%"}); 
        	
    		 $(".input-group.date").datepicker({todayBtn:"linked",keyboardNavigation:false,forceParse:false,calendarWeeks:true,autoclose:true});
        	 
        	  //CHECKBOX
        	$("#isShow").val(1);
        	var changeCheckbox = document.querySelector('.js-check-change');
			changeCheckbox.onchange = function() {
 				if(changeCheckbox.checked == true) {
 					$("#isShow").val(1);
 				}else{
 					$("#isShow").val(0);
 				}
			};
        	var elem = document.querySelector('.js-switch');
			var init = new Switchery(elem);
		
		
        	jQuery("#saveAclForm").validate({
                rules: {
                   title: {
						required: true,
						minlength: 2,
						maxlength: 50
					},
					categoryId: {
						required: true
					},
					image: {
						required: true
					},
					publishDate: {
						required: true
					}
                },
                messages: {
				}
            });
        });
        
    </script>
</body>

</html>
