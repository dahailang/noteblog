$(function() {                
	var noteEditor;
    $("#noteblog").click(function(){                    
		$.getScript("../../editormd/editormd.js", function() {
	        $("#layout").html("<div id='editormd'></div>");
	        noteEditor = editormd("editormd", {
	            markdown : "### 动态创建 Editor.md\r\n\r\nDynamic create Editor.md",
	            //readOnly:true,
	            watch:false,
	            path : '../../editormd/lib/',
	            onload: function() {
	            	this.previewing();
	            }
	        });
		});
    });  
});  
