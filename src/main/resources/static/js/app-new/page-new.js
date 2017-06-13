//----------------------
// 编辑器模式
function editorMode() {
	this.writingHash = "writing";
	this.normalHash = "normal";
	this.isWritingMode = location.hash.indexOf(this.writingHash) >= 0;
	this.toggleA = null;
}

editorMode.prototype.toggleAText = function(isWriting) {
	var self = this;
	setTimeout(function() {
		var toggleA = $(".toggle-editor-mode a");
		var toggleSpan = $(".toggle-editor-mode span");
		if(isWriting) {
			toggleA.attr("href", "#" + self.normalHash);
			toggleSpan.text(getMsg("normalMode"));
		} else {
			toggleA.attr("href", "#" + self.writingHash);
			toggleSpan.text(getMsg("writingMode"));
		}	
	}, 0);
}
editorMode.prototype.isWriting = function(hash) {
	if(!hash) {
		hash = location.hash;
	}
	return hash.indexOf(this.writingHash) >= 0
}
editorMode.prototype.init = function() {
	this.$themeLink = $("#themeLink");
	this.changeMode(this.isWritingMode);
	var self = this;
	$(".toggle-editor-mode").click(function(e) {
		e.preventDefault();
		saveBookmark();
		var $a = $(this).find("a");
		var isWriting = self.isWriting($a.attr("href"));
		self.changeMode(isWriting);
		// 
		if(isWriting) {
			setHash("m", self.writingHash);
		} else {
			setHash("m", self.normalHash);
		}
		
		restoreBookmark();
	});
}
// 改变模式
editorMode.prototype.changeMode = function(isWritingMode) {
	this.toggleAText(isWritingMode);
	if(isWritingMode) {
		this.writtingMode();
	} else {
		this.normalMode();
	}
};

editorMode.prototype.resizeEditor = function() {
	// css还没渲染完
	setTimeout(function() {
		resizeEditor();
	}, 10);
	setTimeout(function() {
		resizeEditor();
	}, 20);
	setTimeout(function() {
		resizeEditor();
	}, 500);
}
editorMode.prototype.normalMode = function() {
	// 最开始的时候就调用?
	/*
	var $c = $("#editorContent_ifr").contents();
	$c.contents().find("#writtingMode").remove();
	$c.contents().find('link[href$="editor-writting-mode.css"]').remove();
	*/

	$("#noteItemListWrap, #notesAndSort").show();
	$("#noteList").unbind("mouseenter").unbind("mouseleave"); 
	
	var theme = UserInfo.Theme || "default";
	theme += ".css";
	var $themeLink = $("#themeLink");
	// 如果之前不是normal才换
	if(this.$themeLink.attr('href').indexOf('writting-overwrite.css') != -1) {
		this.$themeLink.attr("href", LEA.sPath + "/css/theme/" + theme);
	}
	
	$("#noteList").width(UserInfo.NoteListWidth);
	$("#note").css("left", UserInfo.NoteListWidth);

	this.isWritingMode = false;
	this.resizeEditor();
};

editorMode.prototype.writtingMode = function() {
	if (Note.inBatch) {
		return;
	}
	if(this.$themeLink.attr('href').indexOf('writting-overwrite.css') == -1) {
		this.$themeLink.attr("href", LEA.sPath + "/css/theme/writting-overwrite.css");
	}

	/*
	setTimeout(function() {
		var $c = $("#editorContent_ifr").contents();
		$c.contents().find("head").append('<link type="text/css" rel="stylesheet" href="/css/editor/editor-writting-mode.css" id="writtingMode">');
	}, 0);
	*/
		
	$("#noteItemListWrap, #notesAndSort").fadeOut();
	$("#noteList").hover(function() {
		$("#noteItemListWrap, #notesAndSort").fadeIn();
	}, function() {
		$("#noteItemListWrap, #notesAndSort").fadeOut();
	});
	
	// 点击扩展会使html的height生成, 切换后会覆盖css文件的
	// $("#mceToolbar").css("height", "40px");
	
	//$("#pageInner").addClass("animated fadeInUp");

	this.resizeEditor();
	
	$("#noteList").width(250);
	$("#note").css("left", 0);
	
	// 切换到写模式
	Note.toggleWriteable();

	this.isWritingMode = true;
};

editorMode.prototype.getWritingCss = function() {
	if(this.isWritingMode) {
		return ["/css/editor/editor-writting-mode.css"];
	}
	return [];
}
var em = new editorMode();
LEA.em = em;

//-----------
//初始化编辑器
function initEditor() {
	// editor
	// toolbar 下拉扩展, 也要resizeEditor
	var mceToobarEverHeight = 0;
	$("#moreBtn").click(function() {
		saveBookmark();
		var $editor = $('#editor');
		if($editor.hasClass('all-tool')) {
			$editor.removeClass('all-tool');
		} else {
			$editor.addClass('all-tool');
		}

		restoreBookmark();
	});

	// 初始化编辑器
	tinymce.init({
		inline: true,
		theme: 'leanote',
		valid_children: "+pre[div|#text|p|span|textarea|i|b|strong]", // ace
		/*
		protect: [
	        /\<\/?(if|endif)\>/g, // Protect <if> & </endif>
	        /\<xsl\:[^>]+\>/g, // Protect <xsl:...>
	        // /<pre.*?>.*?<\/pre>/g, // Protect <pre ></pre>
	        // /<p.*?>.*?<\/p>/g, // Protect <pre ></pre>
	        // /<\?php.*?\?>/g // Protect php code
	    ],
	    */
		setup: function(ed) {
			ed.on('keydown', function(e) {
				// 如果是readony, 则不能做任何操作
				var num = e.which ? e.which : e.keyCode;
				// 如果是readony, 则不能做任何操作, 除了复制
				if(Note.readOnly && !((e.ctrlKey || e.metaKey) && num == 67)) {
					e.preventDefault();
					return;
				}

				// 当输入的时候, 把当前raw删除掉
				LeaAce.removeCurToggleRaw();
			});
			
			// 为了把下拉菜单关闭
			/*
	        ed.on("click", function(e) {
	          // $("body").trigger("click");
	          // console.log(tinymce.activeEditor.selection.getNode());
	        });
	        */
	        
	        // electron下有问题, Ace剪切导致行数减少, #16
			ed.on('cut', function(e) {
				if($(e.target).hasClass('ace_text-input')) {
					e.preventDefault();
					return;
				}
			});
		},
		
		// fix TinyMCE Removes site base url
		// http://stackoverflow.com/questions/3360084/tinymce-removes-site-base-urls
		convert_urls: false, // true会将url变成../api/
		relative_urls: true,
		remove_script_host:false,
		
		selector : "#editorContent",
		
		// content_css 不再需要
		// content_css : [LEA.sPath + "/css/editor/editor.css"], // .concat(em.getWritingCss()),
		skin : "custom",
		language: LEA.locale, // 语言
		plugins : [
				"autolink link leaui_image leaui_mindmap lists hr", "paste",
				"searchreplace leanote_nav leanote_code tabfocus",
				"table textcolor" ], // nonbreaking directionality charmap
		toolbar1 : "formatselect | forecolor backcolor | bold italic underline strikethrough | leaui_image leaui_mindmap | leanote_code leanote_inline_code | bullist numlist | alignleft aligncenter alignright alignjustify",
		toolbar2 : "outdent indent blockquote | link unlink | table | hr removeformat | subscript superscript |searchreplace | pastetext | leanote_ace_pre | fontselect fontsizeselect",

		// 使用tab键: http://www.tinymce.com/wiki.php/Plugin3x:nonbreaking
		// http://stackoverflow.com/questions/13543220/tiny-mce-how-to-allow-people-to-indent
		// nonbreaking_force_tab : true,
		
		menubar : false,
		toolbar_items_size : 'small',
		statusbar : false,
		url_converter: false,
		font_formats : "Arial=arial,helvetica,sans-serif;"
				+ "Arial Black=arial black,avant garde;"
				+ "Times New Roman=times new roman,times;"
				+ "Courier New=courier new,courier;"
				+ "Tahoma=tahoma,arial,helvetica,sans-serif;"
				+ "Verdana=verdana,geneva;" + "宋体=SimSun;"
				+ "新宋体=NSimSun;" + "黑体=SimHei;"
				+ "微软雅黑=Microsoft YaHei",
		block_formats : "Header 1=h1;Header 2=h2;Header 3=h3;Header 4=h4;Paragraph=p",
		/*
		codemirror: {
		    indentOnInit: true, // Whether or not to indent code on init. 
		    path: 'CodeMirror', // Path to CodeMirror distribution
		    config: {           // CodeMirror config object
		       //mode: 'application/x-httpd-php',
		       lineNumbers: true
		    },
		    jsFiles: [          // Additional JS files to load
		       // 'mode/clike/clike.js',
		       //'mode/php/php.js'
		    ]
		  },
		  */
		  // This option specifies whether data:url images (inline images) should be removed or not from the pasted contents. 
		  // Setting this to "true" will allow the pasted images, and setting this to "false" will disallow pasted images.  
		  // For example, Firefox enables you to paste images directly into any contentEditable field. This is normally not something people want, so this option is "false" by default.
		  paste_data_images: true
	});
	
	// 刷新时保存 参考autosave插件
	window.onbeforeunload = function(e) {
		if (LEA.isLogout) {
			return;
		}
 	Note.curChangedSaveIt(true, null, {refresh: true});
	}

	// 全局快捷键
	// ctrl + s 保存
	// ctrl+e 切换只读与可写
	$('body').on('keydown', function (e) {
		var num = e.which ? e.which : e.keyCode;
		var ctrlOrMetaKey = e.ctrlKey || e.metaKey;
	    if(ctrlOrMetaKey) {
			// 保存
		    if (num == 83 ) { // ctrl + s or command + s
		    	Note.curChangedSaveIt(true, null, {ctrls: true});
		    	e.preventDefault();
		    	return false;
		    }
		    else if (num == 69) { // e
		    	Note.toggleWriteableAndReadOnly();
		    	e.preventDefault();
		    	return false;
		    }
	    }
	});
}

function hideMask () {
	$("#mainMask").html("");
	$("#mainMask").hide(100);
}

