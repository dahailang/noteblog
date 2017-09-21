var LeftSide = function(){
	this.setting={
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false,
				drag: {
					isCopy: true,
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
					if (treeNode.isParent) {
						//取消已选中节点样式
						$(".treeBox>.ztree li a.curSelectedNode").removeClass("curSelectedNode");
						//展开 闭合 目录节点
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						zTree.expandNode(treeNode);
						return false;
					}
					return true;
				},
				onRightClick: function(event, treeId, treeNode) {
					var x= event.clientX;
					var y= event.clientY;
					var rmenu = $("#rightMenu ul");
					if(treeNode.level > 1){
						rmenu.html("<li id='addNote'>增加笔记</li>"+
								"<li id='renameNode'>重命名</li>"+
								"<li id='delNode'>删除节点</li>");
					}else{
						rmenu.html("<li id='addNote'>增加笔记本</li>"+
								"<li id='renameNode'>重命名</li>"+
						"<li id='delNode'>删除节点</li>");
					}
					$("#addNote").click(function(){
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
				beforeDrop: beforeDrop
			}
	};
	this.init=function(url){
		var self =this;
		noteBlogAjax(url,{},function(data){
			$.fn.zTree.init($("#treeDemo"), self.setting, data);
		});
	};
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
	hideRMenu();
	var treeNode = treeNode;
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
			var newNode = {pid:treeNode.id, name:newBookName,isParent:"true"};
			
			noteBlogAjax("/tree/addnotebook",newNode,function(data){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var idc = treeNode.id;
				var level = treeNode.level;
				zTree.addNodes(treeNode,data);
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
				zTree_Menu.removeNode(treeNode);
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
$(document).ready(function(){
	var leftside = new LeftSide();
	leftside.init("../../tree/lefttree");

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



