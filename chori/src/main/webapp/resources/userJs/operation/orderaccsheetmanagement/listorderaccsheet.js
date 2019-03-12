$(document).ready(function(){	
	
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "orderaccsheetmanagement/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.listExternal.length==0){
					$('#listOrderAccSheetManagement tbody').empty();
				}
				$.each(data.listExternal,function(key,value){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.orderSheetNo),
							$('<td>').text("Accessories"),							
							$('<td>').text(value.accsuplierName),
							$('<td>').text(""),
							$('<td>').text(value.paymentStatus),
							$('<td>').text(value.createDate),
							$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.orderSheetNo+'">View</button>')
							
					).appendTo('#listOrderAccSheetManagement');
					
					getOrderSheetDetails();
				});
				
				$.each(data.listInternal,function(key,value){
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.orderSheetNo),
							$('<td>').text("General Accessories"),							
							$('<td>').text(value.accsuplierName),
							$('<td>').text(value.price),
							$('<td>').text(""),
							$('<td>').text(value.createDate),
							$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.orderSheetNo+'">View</button>')
							
					).appendTo('#listOrderAccSheetManagement');
					
					getOrderSheetDetails();
				});
				
				$('#listOrderAccSheetManagement').DataTable( {
					"pagingType": "full_numbers"
			    } );
			}
		});
		loadData();
	}	
	
	/**
	 * This function is used to change table data when choose status
	 */
	$('#ddlStatus').on('change',function(){
		//lấy ra value đc chọn
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "orderaccsheetmanagement/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listOrderAccSheetManagement").dataTable().fnDestroy();
				$('#listOrderAccSheetManagement tbody').empty();
				
				var i = 1;				
				$.each(data.listExternal,function(key,value){	
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.orderSheetNo),
								$('<td>').text("Accessories"),							
								$('<td>').text(value.accsuplierName),
								$('<td>').text(""),
								$('<td>').text(value.paymentStatus),
								$('<td>').text(value.createDate),
								$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.orderSheetNo+'">View</button>')
								
						).appendTo('#listOrderAccSheetManagement');
						getOrderSheetDetails();
						
					}else{//không thì xuất ra theo cái status						
						
						if(valueSelected==value.paymentStatus){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.orderSheetNo),
									$('<td>').text("Accessories"),							
									$('<td>').text(value.accsuplierName),
									$('<td>').text(""),
									$('<td>').text(value.paymentStatus),
									$('<td>').text(value.createDate),
									$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.orderSheetNo+'">View</button>')
									
							).appendTo('#listOrderAccSheetManagement');
							getOrderSheetDetails();
						}
					}
				});
				
				
				$.each(data.listInternal,function(key,value){	
		
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.orderSheetNo),
								$('<td>').text("General Accessories"),							
								$('<td>').text(value.accsuplierName),
								$('<td>').text(value.price),
								$('<td>').text(""),
								$('<td>').text(value.createDate),
								$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.orderSheetNo+'">View</button>')
								
						).appendTo('#listOrderAccSheetManagement');
						getOrderSheetDetails();
						
					}else{//không thì xuất ra theo cái status						
						
						if(valueSelected==value.paymentStatus){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.orderSheetNo),
									$('<td>').text("General Accessories"),							
									$('<td>').text(value.accsuplierName),
									$('<td>').text(value.price),
									$('<td>').text(""),
									$('<td>').text(value.createDate),
									$('<td>').html('<button class="btn btn-primary btnView" data-id="'+value.orderSheetNo+'">View</button>')
									
							).appendTo('#listOrderAccSheetManagement');
							getOrderSheetDetails();
						}
					}
				});				
				
				$('#listOrderAccSheetManagement').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	});
	
	function getOrderSheetDetails(){
			
		$('.btnView').on('click', function (e){			
					
			$("#listOrderAccSheetDetailCover").html("");
			$("#listOrderAccSheetDetailCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listOrderAccSheetDetail">'+
					'<thead>'+
						'<tr>'+
							'<th>No</th>' +
							'<th>Name</th>' +
							'<th>Order Quantity</th>' +
							'<th>Actual Delivered Quantity</th>' +
							'<th>Price</th>' +		
							'<th>Created Date</th>' +	
						'</tr>'+
					'</thead>'+
				'</table>');
			
//			//lấy ra data-id
			var orderSheetNo = $(this).data('id');
					
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/orderaccsheetmanagement/detail/" + orderSheetNo,
			contentType: "application/json",
			success: function(data){
				
				$("#listOrderAccSheetDetail").dataTable().fnDestroy();
				$('#listOrderAccSheetDetail tbody').empty();
				
				if(data.isOrderInternal == true){				
	            		
	    			//to fix dataTable error (cause by init DataTable in loop)
						var listLength = data.OrderInternalAccessory.length;
						var i =1;
						$.each(data.lstOrderInternalAccessoryDetailModel,function(key,value){
						
							$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.accessoryname),
								$('<td>').text(value.orderquantity),							
								$('<td>').text(value.actualdelvquantity),
								$('<td>').text(value.price),
								$('<td>').text(value.createdate)
															
							).appendTo('#listOrderAccSheetDetail');
						});	        							
				}
				else if(data.isOrderInternal == false){

						//to fix dataTable error (cause by init DataTable in loop)
						var i =1;
		//				var listLength = data.OrderExternalAccessory.length;
//						$.each(data.OrderExternalAccessory,function(key,value){
							$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(data.OrderExternalAccessory.accessoryName),
								$('<td>').text(data.OrderExternalAccessory.orderQuantity),							
								$('<td>').text(data.OrderExternalAccessory.actualdevlQuantity),
								$('<td>').text(data.OrderExternalAccessory.price),
								$('<td>').text(data.OrderExternalAccessory.createDate)
															
							).appendTo('#listOrderAccSheetDetail');

//						});
	        		
				}
			}
		});
		$("#orderAccSheetDetailDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Order Accessories Sheet Detail",
			height: 500,
			width: 700,
			modal: true,
			buttons:{
				"Exit": function(){
					$("#orderAccSheetDetailDialog").dialog("close");						
					
				}
			},
			close: function(){
				
			}
		});
		
		});
	}
		
});