/*/register 用户注册 */
$(function() {
	$("#email").focus();
	
	function showMsg(msg, id) {
		$("#loginMsg").html(msg).show();
		if(id) {
			$("#" + id).focus();
		}
	}
	function hideMsg() {
		$("#loginMsg").hide();
	}
	$("#registerBtn").click(function(e){
		e.preventDefault();
		var email = $("#email").val();
		var pwd = $("#pwd").val();
		var pwd2 = $("#pwd2").val();
		if(!email) {
			showMsg("请输入Email", "email");
			return;
		} else {
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.|\-]?)*[a-zA-Z0-9\-_]+@([a-zA-Z0-9\-]+[_|\_|\.|\-]?)*[a-zA-Z0-9\-]+\.[0-9a-zA-Z]{2,6}$/;
			if(!myreg.test(email)) {
				showMsg("Email格式有误", "email");
				return;
			}
		}
		if(!pwd) {
			showMsg("请输入密码", "pwd");
			return;
		} else {
			if(pwd.length < 6) {
				showMsg("密码至少6位", "pwd");
				return;
			}
		}
		if(!pwd2) {
			showMsg("请输入确认密码", "pwd2");
			return;
		} else {
			if(pwd != pwd2) {
				showMsg("两次密码输入不正确", "pwd2");
				return;
			}
		}
		
		$("#registerBtn").html("正在处理...").addClass("disabled");
		
		
		var iu = "";
		
		$.post("/auth/doRegister", {email: email, pwd: pwd, iu: iu}, function(e) {
			$("#registerBtn").html("注册").removeClass("disabled");
			if(e.Ok) {
				$("#registerBtn").html("注册成功, 正在跳转...");
				var from = $("#from").val() || "\/note" || "/note";
				location.href = from;
			} else {
				showMsg(e.Msg, "email");
			}
		});
		
	});
});