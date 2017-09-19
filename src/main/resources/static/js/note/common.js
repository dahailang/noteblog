function showDialog(title,content,okValue,cancelValue,successFun,failFun){
	dialog({
		title: title,
		content: content,
		okValue: okValue,
		ok: function () {
			successFun();
		},
		cancelValue: '取消',
		cancel: function () {
			failFun();
		}
	}).show();
}
function noteBlogAjax(url,param,sucessFun,failFun){
	$.ajax({
		type : 'POST',
		url: url,
		async : true,
		dataType : 'json',
		data:param,
		success: function(data, textStatus, jqXHR){
			sucessFun(data)
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			showDialog("错误",'服务请求失败','确 定','取消',function(){
				return true;
			});
		}
	});
}