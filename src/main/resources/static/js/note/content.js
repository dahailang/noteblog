$(function() {
	//1.初始化数据获取
	noteBlogAjax("/master/allinfo",{},function(data){
		$("#useremail").text(data.attributes.userinfo.email);
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
