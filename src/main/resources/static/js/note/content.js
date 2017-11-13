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
	function updatecontent(content){
		noteEditor.clear();
		noteEditor.appendMarkdown(content);
	}
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
	});
	
    //1.
	function expandleft(treeid){
	    var zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");  
	    var node = zTree_Menu.getNodeByParam("id",pid);  
	    zTree_Menu.selectNode(node,true);//指定选中ID的节点  
	    zTree_Menu.expandNode(node, true, false);//指定选中ID节点展开  
	}
	
	var LeftSide = function(){
		this.setting={
				edit: {
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false,
					drag: {
						isCopy: false,
						isMove: false
					}
				},		
				view: {
					showLine: false,
					showIcon: false,
					selectedMulti: false,//是否允许同时选中多个节点 CTRL+
					dblClickExpand: false,
					addDiyDom: function (treeId, treeNode) {
						var spaceWidth = 5;
						var switchObj = $("#" + treeNode.tId + "_switch"),
						icoObj = $("#" + treeNode.tId + "_ico");
						switchObj.remove();
						icoObj.before(switchObj);
						if (treeNode.level > 1) {
							var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
							switchObj.before(spaceStr);
						}
					},
					fontCss:{color:"#FFFFFF"}
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pid",
						rootPId: 0
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						if(treeNode.level > 1){
							$("#curNotebookForN ewNote").text(treeNode.name).attr("notebookId",treeNode.id);
						}else{
							$("#curNotebookForN ewNote").text(treeNode.name).attr("notebookId",treeNode.id);
						}
						if (treeNode.isParent) {
							//取消已选中节点样式
							$(".treeBox>.ztree li a.curSelectedNode").removeClass("curSelectedNode");
							//展开 闭合 目录节点
							var zTree = $.fn.zTree.getZTreeObj("treeDemo");
							zTree.expandNode(treeNode);
							return false;
						}else{
							//请求获取笔记文本内容
							noteBlogAjax("/note/content/"+treeNode.id,{},function(data){
								if("200"==data.status){
									updatecontent(data.info.content);
								}else{
									showDialog("错误提示",data.msg);
								}
								return true;
							},"加载笔记服务异常")
							return true;
						}
					},
					onRightClick: function(event, treeId, treeNode) {
						var x= event.clientX;
						var y= event.clientY;
						var rmenu = $("#rightMenu ul");
						if(treeNode.level > 0){
							rmenu.html("<li id='addNote'>增加笔记</li>"+
									"<li id='renameNode'>重命名</li>"+
									"<li id='delNode'>删除节点</li>");
						}else{
							rmenu.html("<li id='addNoteBook'>增加笔记本</li>"+
									"<li id='renameNode'>重命名</li>"+
									"<li id='delNode'>删除节点</li>");
						}
						$("#addNote").click(function(){
							addTreeNodeFun(treeNode);
						});
						$("#addNoteBook").click(function(){
							addTreeNodeFun(treeNode);
						});
						$("#renameNode").click(function(){
							removeTreeNodeFun(treeNode);
						});
						$("#delNode").click(function(){
							removeTreeNodeFun(treeNode);
						});
						
						$("#rightMenu").css({"top":(y-50)+"px", "left":x+"px", "visibility":"visible"});
						
						$("body").bind("mousedown", onBodyMouseDown);
					},
					beforeDrag: beforeDrag,
					beforeDrop: beforeDrop,
					onAsyncSuccess:function(event, treeId, treeNode, msg){

					}
				}
		};
		this.init=function(url){
			var self =this;
			noteBlogAjax(url,{},function(data){
				$.fn.zTree.init($("#treeDemo"), self.setting, data);
				
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node;
				if($("#curNotebookForNewNote").attr("notebookId")){
					node = zTree.getNodeByParam("id",$("#curNotebookForNewNote").attr("notebookId"));
				}else{
					note =getFirstLeafNode(zTree.getNodes()[0]);
				}
				zTree.expandNode(note, true, true,true);
				$("#curNotebookForNewNote").text(note.name).attr("notebookId",note.id);
				
				$("#newNoteButton").click(function(){
					var notebookId = $("#curNotebookForNewNote").attr("notebookId");
					var notebooknode = zTree.getNodeByParam("id",notebookId);
					addTreeNodeFun(notebooknode);
				});
				
				
			},"菜单树加载失败");
		};
	}
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				return false;
			}
		}
		return true;
	}
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		return targetNode ? targetNode.drop !== false : true;
	}
	function getFirstLeafNode(node){
		if(node.isParent&&node.children[0]&&node.children[0].isParent){
			return getFirstLeafNode(node.children[0]);
		}
		return node
	}
	function hideRMenu() {
		if ($(("#rightMenu"))){
			 $("#rightMenu").css({"visibility": "hidden"});
		}
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event){
		if (!(event.target.id == "rightMenu" || $(event.target).parents("#rightMenu").length>0)) {
			$("#rightMenu").css({"visibility" : "hidden"});
		}
	}
	function addTreeNodeFun(treeNode) {
		var treeNode = treeNode;
		var isParent;
		if(treeNode.level>0){
			isParent=false;
		}else{
			isParent=true;
		}
		hideRMenu();
		dialog({
			title: '消息',
			width:490,
			height:"auto",
			content: '<div id="titileDiv"><h4>请输入名称</h4><input id="newBook" width="450" /></div>',
			okValue: '确 定',
			ok: function () {
				var newBookName=$("#newBook").val();
				if(""==newBookName){
					$("#titileDiv").append("<font colar='red'>请输入笔记名称</font>");
					return false
				}
				var newNode = {pid:treeNode.id, name:newBookName,isParent:isParent};
				
				noteBlogAjax("/tree/addnotebook",newNode,function(data){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					var idc = treeNode.id;
					var level = treeNode.level;
					zTree.addNodes(treeNode,data);
					
					$("#curNotebookForNewNote").attr("curnoteid",data.id);
					
					updatecontent(data.content);
					
					return true;
				},function(){
					$("#titileDiv").append("<font colar='red'>服务异常</font>");
					return false;
				});

				
			},
			cancelValue: '取消',
			cancel: function () {}
		}).showModal();
	}
	function renameTreeNodeFun(treeNode) {
		hideRMenu();
		var treeNode = treeNode;
		dialog({
			title: '确认消息',
			width:490,
			height:"auto",
			content: '<div id="titileDiv"><h4>请输入重命名名称</h4><input id="rename" width="450" /></div>',
			okValue: '确 定',
			ok: function () {
				var newName = $("#rename").val();
				var newNode = {id:treeNode.id,name:newName};
				noteBlogAjax("/tree/renameNoteBook",newNode,function(data){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					treeNode.name=data.name;
					zTree.updateNode(treeNode);
					return true;
				},function(){
					$("#titileDiv").append("<font colar='red'>服务异常</font>");
					return false;
				});
				
			},
			cancelValue: '取消',
			cancel: function () {}
		}).showModal();
	}
	function removeTreeNodeFun(treeNode) {
		hideRMenu();
		var treeNode = treeNode;
		dialog({
			title: '重命名',
			width:490,
			height:"auto",
			content: '要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！',
			okValue: '确 定',
			ok: function () {
				
				var newNode = {id:treeNode.id};
				noteBlogAjax("/tree/deleteNoteBook",newNode,function(data){
					resetTree();
					//$.fn.zTree.getZTreeObj("treeDemo").removeNode(treeNode);
					return true;
				},function(){
					$("#titileDiv").append("<font colar='red'>服务异常</font>");
					return false;
				});
				
			},
			cancelValue: '取消',
			cancel: function () {}
		}).showModal();
	}
	function checkTreeNode(checked) {
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length>0) {
			zTree_Menu.checkNode(nodes[0], checked, true);
		}
		hideRMenu();
	}
	function resetTree() {
		hideRMenu();
		sendAjax("../../tree/lefttree");
	}

	
});  
