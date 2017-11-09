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
			if(typeof failFun  == "function" ){
				failFun(XMLHttpRequest)
			}else{
				showDialog("错误",failFun,'确 定','取消',function(){
					return true;
				});
			}
		}
	});
}
function simpleAjax(url,sucessFun){
	$.ajax({
		type : 'POST',
		url: url,
		async : true,
		dataType : 'json',
		success: function(data, textStatus, jqXHR){
			sucessFun(data)
		}
	});
}