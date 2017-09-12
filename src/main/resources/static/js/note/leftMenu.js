
var curMenu = null, zTree_Menu = null,rMenu=null;
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
			enable: true
		}
	},
	callback: {
		beforeClick: beforeClick,
		onRightClick: OnRightClick
	}
};

var zNodes =[
	{ id:1, pId:0, name:"文件夹", open:true},
	{ id:11, pId:1, name:"收件箱"},
	{ id:111, pId:11, name:"收件箱1"},
	{ id:112, pId:111, name:"收件箱2"},
	{ id:113, pId:112, name:"收件箱3"},
	{ id:114, pId:113, name:"收件箱4"},
	{ id:12, pId:1, name:"垃圾邮件"},
	{ id:13, pId:1, name:"草稿"},
	{ id:14, pId:1, name:"已发送邮件"},
	{ id:15, pId:1, name:"已删除邮件"},
	{ id:3, pId:0, name:"快速视图"},
	{ id:31, pId:3, name:"文档"},
	{ id:32, pId:3, name:"照片"}
];

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
		zTree_Menu.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_del").hide();
		$("#m_check").hide();
		$("#m_unCheck").hide();
	} else {
		$("#m_del").show();
		$("#m_check").show();
		$("#m_unCheck").show();
	}
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
var addCount = 1;
function addTreeNode() {
	hideRMenu();
	var newNode = { name:"增加" + (addCount++)};
	if (zTree_Menu.getSelectedNodes()[0]) {
		newNode.checked = zTree_Menu.getSelectedNodes()[0].checked;
		zTree_Menu.addNodes(zTree_Menu.getSelectedNodes()[0], newNode);
	} else {
		zTree_Menu.addNodes(null, newNode);
	}
}
function removeTreeNode() {
	hideRMenu();
	var nodes = zTree_Menu.getSelectedNodes();
	if (nodes && nodes.length>0) {
		if (nodes[0].children && nodes[0].children.length > 0) {
			var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
			if (confirm(msg)==true){
				zTree_Menu.removeNode(nodes[0]);
			}
		} else {
			zTree_Menu.removeNode(nodes[0]);
		}
	}
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
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}
function sendAjax(url){
	$.ajax({
		type : 'GET',
		url: url,
		async : false,
		dataType : 'json',
		data:{},
		success: function(data, textStatus, jqXHR){
			var zNodes = data.attributes.lefttree;
			var treeObj = $("#treeDemo");
			$.fn.zTree.init(treeObj, setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
			zTree_Menu.selectNode(curMenu);
			treeObj.hover(function () {
				if (!treeObj.hasClass("showIcon")) {
					treeObj.addClass("showIcon");
				}
			}, function() {
				treeObj.removeClass("showIcon");
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest);
		}
	});
}



$(document).ready(function(){
	var treeObj = $("#treeDemo");
	rMenu = $("#rMenu");
	//sendAjax("./zNodes.json");
	sendAjax("../../tree/lefttree");
});