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
			showMsg("Email is required", "email");
			return;
		} else {
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.|\-]?)*[a-zA-Z0-9\-_]+@([a-zA-Z0-9\-]+[_|\_|\.|\-]?)*[a-zA-Z0-9\-]+\.[0-9a-zA-Z]{2,6}$/;
			if(!myreg.test(email)) {
				showMsg("Wrong email", "email");
				return;
			}
		}
		if(!pwd) {
			showMsg("Password is required", "pwd");
			return;
		} else {
			if(pwd.length < 6) {
				showMsg("Tt\x27s not a good password, the length is at least 6", "pwd");
				return;
			}
		}
		if(!pwd2) {
			showMsg("Please input the new password again", "pwd2");
			return;
		} else {
			if(pwd != pwd2) {
				showMsg("Password not matched", "pwd2");
				return;
			}
		}
		
		$("#registerBtn").html("processing...").addClass("disabled");
		
		
		var iu = "";
		
		$.post("/doRegister", {email: email, pwd: pwd, iu: iu}, function(e) {
			$("#registerBtn").html("Sign up").removeClass("disabled");
			if(e.Ok) {
				$("#registerBtn").html("Register success, redirecting...");
				var from = $("#from").val() || "\/note" || "/note";
				location.href = from;
			} else {
				showMsg(e.Msg, "email");
			}
		});
		
	});
});