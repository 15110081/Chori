$(document).ready(function(){	

function loadData(){
	//show estimatetime
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "estimatetime/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					$("#frmEstimatetime").find("#txtPiconpletion").val(value.piconpletion);
					$("#frmEstimatetime").find("#txtPackingaccdelv").val(value.packingaccdelv);
					$("#frmEstimatetime").find("#txtManuaccdelv").val(value.manuaccdelv);
					$("#frmEstimatetime").find("#txtEstimatetimeCode").val(value.estimateCode);

				});
			},
			error: function(){
				alert("Can not get data!");
			}
		});
		
	};
	function action(){	
		// When click select
		$("#frmEstimatetime").find('#btnSave').on('click', function (e) {	
			var estCode= $("#frmEstimatetime").find("#txtEstimatetimeCode").val();
	
					var piconpletion=$("#frmEstimatetime").find("#txtPiconpletion").val();
					var packingaccdelv= $("#frmEstimatetime").find("#txtPackingaccdelv").val();
					var manuaccdelv= $("#frmEstimatetime").find("#txtManuaccdelv").val();

					
				
					//ajax call to edit 
					$.ajax({
			    		dataType: "json",
						type: 'POST',
						data:
							JSON.stringify({
								estimateCode :estCode,
								piconpletion: piconpletion,
								packingaccdelv: packingaccdelv,
								manuaccdelv:manuaccdelv,
							}),
						contentType: "application/json",
						url: getAbsolutePath() +  "estimatetime/edit",
						success: function(data){
						//	alert('edit success');
							
							callMessageDialog("Message", "Edit estimatetime successfully!");
							
						
							loadData();
						
						},
						error: function(){
							callMessageDialog("Message", "Edit estimatetime unsuccessfully!");
						}
			    	});
			
		
	    	});
		$("#frmEstimatetime").find('#btnCancel').on('click', function (e) {	
			window.location.href = "/Chori";
		});
	}
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: title,
			height: 200,
			width: 350,
			hide: {
				effect: "explode",
				duration: 500
			},
			buttons:{
				"OK": function(){
					$("#messageDialog").dialog("close");
				}
			}
		});
}
	$("#frmEstimatetime").on('keydown', '#txtPiconpletion,#txtPackingaccdelv,#txtManuaccdelv' ,
			function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		||35<=e.keyCode&&40>=e.keyCode
		||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});

	
	/**
	 * 
	 */
	var inputQuantity = [];
	
//  function maxlengthForNumber() {
    $(".quantity").each(function(i) {
      inputQuantity[i]=this.defaultValue;
       $(this).data("idx",i); // save this field's index to access later
    });
    $(".quantity").on("keyup", function (e) {
      var $field = $(this),
          val=this.value,
          $thisIndex=parseInt($field.data("idx"),10); // retrieve the index
//      window.console && console.log($field.is(":invalid"));
        //  $field.is(":invalid") is for Safari, it must be the last to not error in IE8
      if (this.validity && this.validity.badInput || isNaN(val) || $field.is(":invalid") ) {
          this.value = inputQuantity[$thisIndex];
          return;
      } 
      if (val.length > Number($field.attr("maxlength"))) {
        val=val.slice(0, 5);
        $field.val(val);
      }
      inputQuantity[$thisIndex]=val;
    });
	/**
	 * 
	 */
	
	loadData();
	action();
});
