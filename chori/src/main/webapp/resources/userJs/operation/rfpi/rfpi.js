$(document).ready(function() {
//	$('#btnViewRFPI').on('click', function(e) {
//		$("#viewRFPIdialog").dialog({
//			show : {
//				effect : "blind",
//				duration : 500
//			},
//			title : "RFPI Details",
//			height : 700,
//			width : 1300,
//			modal : true,
//			close : function() {
//
//			}
//		});
//	});
	
	function getPIdetail(){
		
	}

	function callErrorMessageDialog(title, messageContent) {
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			show : {
				effect : "slide",
				duration : 500
			},
			title : title,
			height : 150,
			width : 300,
			hide : {
				effect : "slide",
				duration : 500
			},
			buttons : {
				"OK" : function() {
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color", "#f5321c");
	}
});