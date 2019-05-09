<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>${setting.siteName} - 新增系统消息</title>
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
                    <h5>新增系统消息</h5>

                    <div class="ibox-tools">
                        <button type="button" class="btn btn-outline btn-white btn-xs" id="btn-return-loippi"
                                onclick="location.href='${base}/admin/system_mes/list.jhtml'"><i
                                class="fa fa-reply-all"></i> 返回列表
                        </button>
                    </div>
                </div>
                <div class="ibox-content">
                    <form id="inputForm" class="form-horizontal" action="save.jhtml" method="post">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">消息标题<span class="required"> * </span></label>

                                    <div class="col-sm-9">
                                        <!--【改控件：描述—】-->
                                        <input type="text" name="title" id="title"  maxlength="100"  class="form-control">
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">消息内容<span class="required"> * </span></label>

                                    <div class="col-sm-8 form-inline">
                                        <textarea rows="8" cols="60" id="content" name="content"
                                                  onkeyup="words_deal()"></textarea>
                                        <span id="number-toast">0/500</span>
                                    </div>
                                </div>
                            </div>
                            <!--/row-->
                        </div>
                        <!--/row-->




                        <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">图片</label>

                                    <div class="col-sm-9">
                                        <input type="hidden" id="image" name="image">
                                        <div id="imgId12">
                                            <input type="file" id="stuImg"/>
                                        </div>
                                        <img id="stuImgView" name="stuImgView" src="" height="100"
                                             width="100"/>
                                    </div>
                                </div>
                            </div>
                            <!--/row-->
                        </div>

                     <div class="form-group">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label">推送对象<span class="required"> * </span></label>
<div class="col-sm-9">
    <select class="form-control" name="type">
        <option value="0" >全体</option>
        <option value="1" >教师</option>
        <option value="2" >家长</option>
    </select>
</div>
                                </div>
                            </div>
                            <!--/row-->
                        </div>
                        <!--/row-->

                       

                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-12 col-sm-offset-10">
                                <button type="button" class="btn btn-default" onclick="window.history.back();">返回
                                </button>
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
	<script src="${base}/resources/plugins/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${base}/resources/plugins/uploadify/uploadify.css">
	
	
<script>



    	$("#inputForm").validate({
        rules: {
          title: {
                required: true
            },
           content: {
                required: true
            },
           type: {
                required: true
            }
        },
        messages: {
		}
    });


    $(document).ready(function () {
        [@flash_message /]
        $(".chosen-select").chosen({width: "100%"});
        $(".input-daterange").datepicker({keyboardNavigation: false, forceParse: false, autoclose: true});
        var token = '${token}',prefix = '${PREFIX}';

        $(".browserButton").browser({
            callback: function (url) {
                $("#" + $(this).attr('for')).val(url);
            }
        });

        $("#inputForm").validate({
            rules: {},
            messages: {}
        });

        $('#stuImg').uploadify(
                {
                    height: 30,
                    swf: '${base}/resources/plugins/uploadify/uploadify.swf',
                    uploader: 'http://upload.qiniu.com/',
                    width: 120,
                    fileObjName: 'file',
                    'fileTypeExts': '*.gif; *.jpeg; *.jpg; *.png',
                    buttonText: '文件上传',
                    'fileSizeLimit': '30000KB',
                    method: 'Post',
                    'multi': false, //是否允许多文件上传。默认为false
                    'overrideEvents': ['onSelectError', 'onDialogClose'],
                    'onSelectError': function (file, errorCode, errorMsg) {

                        switch (errorCode) {
                            case  -100:
                                alert("上传的文件数量已经超出系统限制的" + jQuery
                                        ('#stuImg').uploadify('settings', 'queueSizeLimit') + "个文件！");
                                break;
                            case  -110:
                                alert("文件 [" + file.name + "] 大小超出系统限制的" + jQuery
                                        ('#stuImg').uploadify('settings', 'fileSizeLimit') + "大小！");
                                break;
                            case  -120:
                                alert("文件 [" + file.name + "] 大小异常！");
                                break;
                            case  -130:
                                alert("文件 [" + file.name + "] 类型不正确！");
                                break;
                        }
                    },
                    'onClearQueue': function (queueItemCount) {
                        alert("取消上传");
                        return;
                    },
                    'onUploadStart': function (file) {
                        var timestamp = new Date().getTime();
                        // 如想取1000~10000的随机数则：
                        var randoms = Math.floor(Math.random() * 100) + 9000;
                        timestamp = timestamp + 'K' + randoms;
                        $("#stuImg").uploadify("settings", "formData",
                                {
                                    'token': token,
                                    'key': timestamp + file.type
                                }
                        );
                        //在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。
                    },
                    onUploadSuccess: function (file, data, response) {
                        data = jQuery.parseJSON(data);
                        $("#image").val(prefix + data.key);
                        $("#stuImgView").attr('src', prefix + data.key);
                    }
                });
    });

    function words_deal() {
        var curLength = $("#content").val().length;
        if (curLength > 500) {
            var num = $("#content").val().substr(0, 500);
            $("#content").val(num);
        } else {
            $("#number-toast").text($("#content").val().length + "/500");
        }
    }

</script>
</body>
</html>
