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
		zTree_Menu.selectNode(treeNode);
		showRMenu("node",treeNode,event.clientX, event.clientY);
	}
}
function showRMenu(type,treeNode,x, y) {
	
	var ulDiv =$("#rMenu ul").html("");
	var addTreeNodeBook="<li id='m_add'>增加子笔记本</li>";
	var addTreeNode="<li id='m_add' onclick='addTreeNode();'>增加笔记</li>";
	var removeTreeNode="<li id='m_del' onclick='removeTreeNode();'>删除节点</li>";
	var checkTreeNode ="<li id='m_check' onclick=’checkTreeNode(true);'>Check节点</li>";
	ulDiv.append(addTreeNodeBook);
	ulDiv.append(addTreeNode);
	ulDiv.append(removeTreeNode);
	ulDiv.append(checkTreeNode);
	
	$("#m_add").click(function(){
		addTreeNodeFun(treeNode);
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
				if (zTree_Menu.getSelectedNodes()[0]) {
					newNode.checked = zTree_Menu.getSelectedNodes()[0].checked;
					zTree_Menu.addNodes(zTree_Menu.getSelectedNodes()[0], newNode);
				} else {
					zTree_Menu.addNodes(null, newNode);
				}
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
	sendAjax("../../tree/lefttree");
}
function sendAjax(url){
	$.ajax({
		type : 'GET',
		url: url,
		async : false,
		dataType : 'json',
		data:{},
		success: function(data, textStatus, jqXHR){
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
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest);
		}
	});
}
function noteBlogAjax(url,param,sucessFun,failFun){
	$.ajax({
		type : 'POST',
		url: url,
		async : false,
		dataType : 'json',
		data:param,
		success: function(data, textStatus, jqXHR){
			//window.location.reload(); 
			sucessFun(data)
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			dialog({
				title: '错误',
				content: '服务请求失败',
				okValue: '确 定',
				ok: function () {
					return true;
				},
				cancelValue: '取消',
				cancel: function () {}
			}).show();
		}
	});
}

$(document).ready(function(){
	var treeObj = $("#treeDemo");
	rMenu = $("#rMenu");
	sendAjax("../../tree/lefttree");
});