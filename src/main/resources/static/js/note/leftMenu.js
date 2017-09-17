var curMenu = null, zTree_Menu = null,rMenu=null;


//树配置
var setting = {
	view: {
		showLine: false,
		showIcon: false,
		selectedMulti: false,
		dblClickExpand: false,
		addDiyDom: addDiyDom
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
		beforeClick: beforeClick,
		onRightClick: OnRightClick
	}
};

function addDiyDom(treeId, treeNode) {
	var spaceWidth = 5;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 1) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
}

function beforeClick(treeId, treeNode) {
	if (treeNode.level == 0 ) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(treeNode);
		return false;
	}
	return true;
}
function OnRightClick(event, treeId, treeNode) {
	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		zTree_Menu.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if (treeNode && !treeNode.noR) {
		//zTree_Menu.selectNode(treeNode);
		showRMenu("node",treeNode,event.clientX, event.clientY);
	}
}
function showRMenu(type,treeNode,x, y) {
	var ulDiv =$("#rMenu ul").html("");
	var addTreeNodeBook="<li id='m_add'>增加子笔记本</li>";
	var addTreeNode="<li id='m_add' onclick='addTreeNode();'>增加笔记</li>";
	var renameTreeNode="<li id='m_rename'>重命名</li>";
	var removeTreeNode="<li id='m_del'>删除节点</li>";
	var checkTreeNode ="<li id='m_check' onclick=’checkTreeNode(true);'>Check节点</li>";
	ulDiv.append(addTreeNodeBook);
	ulDiv.append(addTreeNode);
	ulDiv.append(renameTreeNode);
	ulDiv.append(removeTreeNode);
	ulDiv.append(checkTreeNode);
	
	$("#m_add").click(function(){
		addTreeNodeFun(treeNode);
	});
	$("#m_del").click(function(){
		removeTreeNodeFun(treeNode);
	});
	$("#m_rename").click(function(){
		renameTreeNodeFun(treeNode);
	});
	
	$("#rMenu ul").show();
	rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

	$("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
	if (rMenu) rMenu.css({"visibility": "hidden"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"visibility" : "hidden"});
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

$(document).ready(function(){
	var treeObj = $("#treeDemo");
	rMenu = $("#rMenu");
	noteBlogAjax("../../tree/lefttree",{},function(data){
		var zNodes = data;
		var treeObj = $("#treeDemo");
		$.fn.zTree.init(treeObj, setting, zNodes);
		zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.hover(function () {
			if (!treeObj.hasClass("showIcon")) {
				treeObj.addClass("showIcon");
			}
		}, function() {
			treeObj.removeClass("showIcon");
		});
	});
});