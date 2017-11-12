$(document).ready(function(){
	/*换肤*/
	$(".dropdown .changecolor li").click(function(){
		var style = $(this).attr("id");
	    $("link[title!=''][role='skin']").attr("disabled","disabled"); 
		$("link[title='"+style+"']").removeAttr("disabled"); 

		$.cookie('mystyle', style, { expires: 7 }); // 存储一个带7天期限的 cookie 
	})
	var cookie_style = $.cookie("mystyle"); 
	if(cookie_style!=null){ 
	    $("link[title!=''][role='skin']").attr("disabled","disabled"); 
		$("link[title='"+cookie_style+"']").removeAttr("disabled"); 
	} 
	/*左侧导航栏显示隐藏功能*/
	$(".subNav").click(function(){				
		/*显示*/
		if($(this).find("span:first-child").attr('class')=="title-icon glyphicon glyphicon-chevron-down"){
			$(this).find("span:first-child").removeClass("glyphicon-chevron-down");
		    $(this).find("span:first-child").addClass("glyphicon-chevron-up");
		    $(this).removeClass("sublist-down");
			$(this).addClass("sublist-up");
		}else{/*隐藏*/
			$(this).find("span:first-child").removeClass("glyphicon-chevron-up");
			$(this).find("span:first-child").addClass("glyphicon-chevron-down");
			$(this).removeClass("sublist-up");
			$(this).addClass("sublist-down");
		}	
		// 修改数字控制速度， slideUp(500)控制卷起速度
	    $(this).next(".navContent").slideToggle(300).siblings(".navContent").slideUp(300);
	});
	/*左侧导航栏缩进功能*/
	$(".left-main .sidebar-fold").click(function(){
		if($(this).parent().attr('class')=="left-main left-full"){
			$(this).parent().removeClass("left-full");
			$(this).parent().addClass("left-off");
			$(this).parent().parent().find(".right-product").removeClass("right-full");
			$(this).parent().parent().find(".right-product").addClass("right-off");
		}else{
			$(this).parent().removeClass("left-off");
			$(this).parent().addClass("left-full");
			$(this).parent().parent().find(".right-product").removeClass("right-off");
			$(this).parent().parent().find(".right-product").addClass("right-full");
		}
	});	
	 
	/*左侧鼠标移入提示功能*/
	$(".sBox ul li").mouseenter(function(){
		if($(this).find("span:last-child").css("display")=="none"){
			$(this).find("div").show();
		}
	}).mouseleave(function(){$(this).find("div").hide();})	
});



$(function() {


	
	//1.初始化数据获取
	noteBlogAjax("/master/allinfo",{},function(data){
		$("#useremail").text(data.attributes.userinfo.email).
			attr("lasteditornoteid",$.trim(data.attributes.userinfo.lastEditorNoteid));
		
		//2.加载左侧菜单树
		var leftside = new LeftSide();
		leftside.init("../../tree/lefttree");
		
	},"获取全局信息失败");
	$("#masterlaylout").click(function(){
		simpleAjax("/master/layout",function(data){
			if(data.ok){
				window.location.href="/html/auth/login.html";
			}
			
		});
	});
	
	//2.左侧菜单树展开

	//3.默认目录区域选项
	//4.内容区域初始化
	var noteEditor;
	$.getScript("../../editormd/editormd.js", function() {
		$("#editormd-view").html("<div id='editormd'></div>");
		noteEditor = editormd("editormd", {
			markdown : "### 动态创建 Editor.md\r\n\r\nDynamic create Editor.md",
			watch:false,
			toolbarIcons : function() {
                return [
                    "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|", 
                    "h1", "h2", "h3", "h4", "h5", "h6", "|", 
                    "list-ul", "list-ol", "hr", "|",
                    "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak"]
            },
			path : '../../editormd/lib/',
			onload: function() {
			}
		});
		$("#previewingMode").click(function(){
			noteEditor.previewing();
		});
		$("#fullscreenMode").click(function(){
			noteEditor.fullscreen();
		});
		$("#watchingMode").click(function(){
			if(noteEditor.settings.watch){
				noteEditor.unwatch();
			}else{
				noteEditor.watch();
			}
			
		});
		$("#undoButton").click(function(){
			noteEditor.cm.undo();
		});
		$("#redoButton").click(function(){
			noteEditor.cm.redo();
		});
		$("#saveButton").click(function(){
			console.log(noteEditor.getMarkdown());
		});
		
		//显示工具栏
		//$("#buttion").showToolbar();
		//隐藏工具栏
		//$("#buttion").hideToolbar();
		//$("#buttion").getMarkdown();
		//$("#buttion").getHTML();
		
	});
	
    //1.
	function expandleft(treeid){
	    var zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");  
	    var node = zTree_Menu.getNodeByParam("id",pid);  
	    zTree_Menu.selectNode(node,true);//指定选中ID的节点  
	    zTree_Menu.expandNode(node, true, false);//指定选中ID节点展开  
	}
});  
