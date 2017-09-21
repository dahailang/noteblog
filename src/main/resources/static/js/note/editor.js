$(function() {
	var noteEditor;
	$.get('test.md', function(md) {
		noteEditor = editormd("editormd", {
			width : "100%",
			height : "100%",
			path : "../../editormd/lib/",
			// theme : "dark",//标题题头
			// previewTheme : "dark",//预览效果
			// editorTheme : "pastel-on-dark",//编辑区主题样式
			markdown : md,// '文档初始化内容'
			toolbarIcons : toolbar,
			codeFold : true,
			syncScrolling : "single",
			saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
			searchReplace : true,
			// watch : false, // 关闭实时预览
			htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
			// toolbar : false, //关闭工具栏
			// previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
			emoji : true,
			taskList : true,
			tocm : true, // Using [TOCM]
			tex : true, // 开启科学公式TeX语言支持，默认关闭
			flowChart : true, // 开启流程图支持，默认关闭
			sequenceDiagram : true, // 开启时序/序列图支持，默认关闭,
			// dialogLockScreen : false, // 设置弹出层对话框不锁屏，全局通用，默认为true
			// dialogShowMask : false, // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
			// dialogDraggable : false, // 设置弹出层对话框不可拖动，全局通用，默认为true
			// dialogMaskOpacity : 0.4, // 设置透明遮罩层的透明度，全局通用，默认值为0.1
			// dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
			imageUpload : true,
			imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
			imageUploadURL : "./php/upload.php",
			onload : function() {
				// console.log('onload', this);
			}
		});
	});

	function toolbar() {
		return [ "bold", "del", "italic", "quote", "ucwords", "uppercase",
				"lowercase", "|", "h1", "h2", "h3", "h4", "h5", "h6", "|",
				"list-ul", "list-ol", "hr", "|", "link", "reference-link",
				"image", "code", "preformatted-text", "code-block", "table",
				"datetime", "emoji", "html-entities", "pagebreak" ];
	}
});

$("#goto-line-btn").bind("click", function() {
	var linevalue = $(this).siblings(".line").val();
	alert(linevalue)
	if (linevalue == "") {
		linevalue = 0;
		noteEditor.gotoLine(linevalue);
	} else {
		linevalue = parseInt(linevalue);
		noteEditor.gotoLine(linevalue);
	}

});
$("#noteEditor_btn").click(function() {
	showHide("noteEditor_btn", "隐藏编辑器", "显示编辑器");
})

$("#get-md-btn").bind('click', function() {
	alert(noteEditor.getMarkdown());
});

$(window).keydown(function(e) {
	if (e.keyCode == 83 && e.ctrlKey) {
		e.preventDefault();
		alert(noteEditor.getMarkdown())
	}
});
$("#get-html-btn").bind('click', function() {
	alert(noteEditor.getHTML());
});
$("#watch-btn").click(function() {
	showHide("watch-btn", "隐藏效果", "显示效果");
})

var showHideVar = 0;
function showHide(watch, btnhtml, btnhtmlun) {
	if (showHideVar == 0) {
		$("#" + watch).html(btnhtmlun);
		if (btnhtml == "隐藏效果") {
			noteEditor.unwatch();
		} else if (btnhtml == "隐藏编辑器") {
			noteEditor.hide();
		} else if (btnhtml == '隐藏工具栏') {
			noteEditor.hideToolbar();
		}

		showHideVar = 1;
	} else if (showHideVar == 1) {
		$("#" + watch).html(btnhtml);
		if (btnhtml == "隐藏效果") {
			noteEditor.watch();
		} else if (btnhtml == "隐藏编辑器") {
			noteEditor.show();
		} else if (btnhtml == "隐藏工具栏") {
			noteEditor.showToolbar();
		}
		showHideVar = 0;
	}
}
$("#preview-btn").bind('click', function() {
	noteEditor.previewing();
});

$("#fullscreen-btn").bind('click', function() {
	$("nav").hide();
	noteEditor.fullscreen();
});
$("#close-toolbar-btn").bind('click', function() {
	// noteEditor.hideToolbar();
	showHide("close-toolbar-btn", "隐藏工具栏", "显示工具栏");

});

$("#toc-menu-btn").click(function() {
	noteEditor.config({
		tocDropdown : true,
		tocTitle : "目录 Table of Contents",
	});
});

$("#toc-default-btn").click(function() {
	noteEditor.config("tocDropdown", false);
});

function toolbutton() {
	return [ "undo", "redo", "link", "reference-link", "image", "code",
			"preformatted-text", "code-block", "table", "datetime", "emoji",
			"html-entities", "pagebreak", "|", "goto-line", "watch", "preview", // 跳到第几行
																				// 编辑模式
																				// 预览
			"fullscreen", "clear", "search", "|",// 全屏 ，清除 ，搜索
			"help", "info" ];
}
