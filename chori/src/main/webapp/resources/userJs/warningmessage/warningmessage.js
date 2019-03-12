$(document).ready(function(){
	/**
	 * function to show warning message for PI
	 * **/
	function ShowWarningMessageForPI(){
		//ajax to call method get list pi that is warned
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() + "warningmessage/piwarningmessage",
			contentType: "application/json",
			success: function(data){
				var i = 1;
//				if(data.piwarningmessage.length==0){
//					alert("Table have no data.");
//				}
				$.each(data.piwarningmessage,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.lotnumber),						
							$('<td>').text(value.customer1),
							$('<td>').text(value.piestshipdate)									
					).appendTo('#tblWarningMessageForPI');
				});
				$('#tblWarningMessageForPI').DataTable({
					"pagingType": "full_numbers"
			    });
				$("#txtTotalPI").text(i-1);
			},
			error: function(){
				alert("Can not get data!");
			}
		});	
	};
	
	/**
	 * function to show warning message for packing guide order external accessory
	 * **/
	function ShowWarningMessageForPackingGuideOrderExternalAccessory(){
		//ajax to call method get list pi that is warned
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() + "warningmessage/packingguideorderextaccwarningmessage",
			contentType: "application/json",
			success: function(data){
				var i = 1;
//				if(data.packingguideorderextaccwarningmessage.length==0){
//					alert("Table have no data.");
//				}
				$.each(data.packingguideorderextaccwarningmessage,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.orderSheetNo),
							$('<td>').text(value.accessoryCode),
							$('<td>').text(value.factoryCode),
							$('<td>').text(value.orderDate),
							$('<td>').text(value.orderQuantity),
							$('<td>').text(value.estimatedevlDate)
					).appendTo('#tblWarningMessageForPackingGuideOrderExtAcc');
				});
				$('#tblWarningMessageForPackingGuideOrderExtAcc').DataTable({
					"pagingType": "full_numbers"
			    });
				$("#txtTotalPackingGuideOrderExtAcc").text(i-1);
			},
			error: function(){
				alert("Can not get data!");
			}
		});	
	};
	
	ShowWarningMessageForPI();
	ShowWarningMessageForPackingGuideOrderExternalAccessory();

});