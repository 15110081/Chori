$(document).ready(function(){
//	alert("External");
	$('#btnShowAssignExAccDialog').on('click',function(){
		//xóa tạo lại table
		$("#AssignExternalAccessoryTableCover").html('');
		$("#AssignExternalAccessoryTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listAssignExternalAccessory">'+
					'<thead>'+
						'<tr>'+
							'<th>No</th>'+
							'<th>Accessory Name</th>'+
							'<th>Accessory For</th>'+
							'<th>Image</th>'+
							'<th>Description</th>'+
							'<th></th>'+
						'</tr>'+
					'</thead>'+
				'</table>');
		
		//lấy giá trị của txtLotNumber
		var lotNumber = $("#txtLotNumber").val();
		
		/**
		 * gọi ajax xem đã đc assign external chưa?
		 * chưa thì mới add mới, còn r thì edit
		 */
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			contentType: "application/json",
			url: "/Chori/assignExternalAccessory/isAssigned/"+lotNumber,
			success: function(data){
				//chưa assign thì mới add mới
				if(data.isAssigned == false){
					
					///gọi ajax lấy list
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/listAssignExternalAccessory1stTime/"+lotNumber,
						contentType: "application/json",
						success: function(data){
							if(data.listAssignExternalAccessory1stTime.length==0){
//								alert("Table have no data.");
							}
							var i=0;
							var preAccessoryName ="";
							$.each(data.listAssignExternalAccessory1stTime,function(key,value){
								$('<tr>').append(
//										$('<td>').text(i++),
										$('<td>').text(preAccessoryName.localeCompare(value.accessoryName)==0?i:++i),
										$('<td>').text(value.accessoryName),
										$('<td>').text(value.mode),
										$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl+'" data-id="choriAccessoryImage/'+value.imgurl+'" /></a>')),
										$('<td>').text(value.accessoryName),
										$('<td>').html('<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" checked /> '+value.colorName+", "+value.garmentstylecode+", "+value.sizename)
								).appendTo('#listAssignExternalAccessory');
								
								preAccessoryName = value.accessoryName;
							});
//							action();
							
							$('#listAssignExternalAccessory').DataTable( {
								"sPaginationType": "full_numbers",
								paging: true,
						        rowsGroup: [0,1,2,3,4]
						    } );
						},
						error: function(){
							alert("Can not get data!");
						}
					});
					///end
					
				}else if(data.isAssigned == true){//này là edit
					
//					alert("load khi edit");
					///gọi ajax lấy list
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/listPiassignexternalaccessoryForEditByLotNumber/"+lotNumber,
						contentType: "application/json",
						success: function(data){
							if(data.listPiassignexternalaccessoryForEditByLotNumber.length==0){
//								alert("Table have no data.");
							}
							var i=0;
							var preAccessoryName ="";
							$.each(data.listPiassignexternalaccessoryForEditByLotNumber,function(key,value){
								$('<tr>').append(
//										$('<td>').text(i++),
										$('<td>').text(preAccessoryName.localeCompare(value.accessoryName)==0?i:++i),
										$('<td>').text(value.accessoryName),
										$('<td>').text(value.mode),
										$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl+'" data-id="choriAccessoryImage/'+value.imgurl+'" /></a>')),
										$('<td>').text(value.accessoryName),
										$('<td>').html((value.estimateqty>0?'<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" checked /> ':'<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" /> ')+value.colorName+", "+value.garmentstylecode+", "+value.sizename)
								).appendTo('#listAssignExternalAccessory');
								
								preAccessoryName = value.accessoryName;
							});
//							action();
							
							$('#listAssignExternalAccessory').DataTable( {
								"sPaginationType": "full_numbers",
								paging: true,
						        rowsGroup: [0,1,2,3,4]
						    } );
						},
						error: function(){
							alert("Can not get data!");
						}
					});
					///end
					
				}
			},error: function(){
				alert("error 91!");
			}
		});
		//end gọi ajax xem đã đc assign external chưa?
		
//		///gọi ajax lấy list
//		$.ajax({
//			dataType: "json",
//			type: 'GET',
//			data: {},
//			url: "/Chori/listAssignExternalAccessory1stTime/"+lotNumber,
//			contentType: "application/json",
//			success: function(data){
//				if(data.listAssignExternalAccessory1stTime.length==0){
//					alert("Table have no data.");
//				}
//				var i=0;
//				var preAccessoryName ="";
//				$.each(data.listAssignExternalAccessory1stTime,function(key,value){
//					$('<tr>').append(
////							$('<td>').text(i++),
//							$('<td>').text(preAccessoryName.localeCompare(value.accessoryName)==0?i:++i),
//							$('<td>').text(value.accessoryName),
//							$('<td>').text(value.mode),
//							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl+'" data-id="choriAccessoryImage/'+value.imgurl+'" /></a>')),
//							$('<td>').text(value.accessoryName),
//							$('<td>').html('<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" checked /> '+value.colorName+", "+value.garmentstylecode+", "+value.sizename)
//					).appendTo('#listAssignExternalAccessory');
//					
//					preAccessoryName = value.accessoryName;
//				});
////				action();
//				
//				$('#listAssignExternalAccessory').DataTable( {
//					"sPaginationType": "full_numbers",
//					paging: true,
//			        rowsGroup: [0,1,2,3,4]
//			    } );
//			},
//			error: function(){
//				alert("Can not get data!");
//			}
//		});
//		///end 
		
		$("#assignExternalAccessoryForPiDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "External Accessory Checklist",
			height: 600,
			width: 1280,
			buttons:{
				"Assign": function(){
					/**
					 * gọi ajax xem đã đc assign external chưa?
					 * chưa thì mới add mới, còn r thì edit
					 */
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						contentType: "application/json",
						url: "/Chori/assignExternalAccessory/isAssigned/"+lotNumber,
						success: function(data){
							//chưa assign thì mới add mới
							if(data.isAssigned == false){
//								callMessageDialog("Test", "Chưa assign!");
//								alert("XXX");
								/**
								 * Bắt đầu add
								 */
								//tạo ra mảng để lưu các external mà ko assign
								var lstNotAssign =[];
								//duyệt qua r đẩy vô hết trong array
								$('input[name="lstExAccAssign"]:not(:checked)').each(function () {
									lstNotAssign.push({ 'piassignexternalaccessorycode': $(this).val() });
								});
								console.log(JSON.stringify(lstNotAssign));
								
								//h gọi ajax để add
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify(lstNotAssign),
									contentType: "application/json",
									url: "/Chori/assignExternalAccessory/add1stTime",
									success: function(data){
										if(data.status=="ok"){
											if(data.add1stTimeStatus==true){
												callMessageDialog("Message", "Assign External Accessory successfully!");
												//load lại list bên pi
												showListPiAssignExternalAcc();
											}else if(data.add1stTimeStatus==false){
												callMessageDialog("Warning Message", "Assign External Accessory failed!");
											}else{
												alert("never show!");
											}
										}else{
											alert("vấn đề code, 356");
										}
									},
									error: function(){
										alert("failed on /assignExternalAccessory/add1stTime");
									}
								});
								//end gọi ajax để add
							}else if(data.isAssigned == true){//này là edit
//								callMessageDialog("Test", "Assign rồi, này là edit!");
								
								/**
								 * Bắt đầu edit
								 */
								//tạo ra mảng để lưu các external mà ko assign
								var lstAssignOrNot =[];
								//duyệt qua r đẩy vô hết trong array
								$('input[name="lstExAccAssign"]').each(function () {
									//lstAssignOrNot.push({ 'piassignexternalaccessorycode': $(this).val() });
									if($(this).is(':checked')){
										lstAssignOrNot.push({ 
												'piassignexternalaccessorycode': $(this).val(),
												'assign': true
											});
									}else if($(this).is(':checked')== false){
										lstAssignOrNot.push({ 
												'piassignexternalaccessorycode': $(this).val(),
												'assign': false
											});
									}
								});
								console.log(JSON.stringify(lstAssignOrNot));
								
								//h gọi ajax để edit
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify(lstAssignOrNot),
									contentType: "application/json",
									url: "/Chori/assignExternalAccessory/editPiAssignExternalAccessory",
									success: function(data){
										if(data.status=="ok"){
											if(data.editStatus==true){
												callMessageDialog("Message", "Assign External Accessory successfully!");
												//load lại list bên pi
												showListPiAssignExternalAcc();
											}else if(data.editStatus==false){
												callMessageDialog("Warning Message", "Assign External Accessory failed!");
											}else{
												alert("never show!");
											}
										}else{
											alert("vấn đề code, 356");
										}
									},
									error: function(){
										alert("failed on /assignExternalAccessory/editPiAssignExternalAccessory");
									}
								});
								//end gọi ajax để add
								
							}
						},error: function(){
							alert("error 91!");
						}
					});
					//end gọi ajax xem đã đc assign external chưa?
				},
				"Cancel": function(){
					$("#assignExternalAccessoryForPiDialog").dialog("close");
				}
			},
			close: function(){
				
			}
		});
	});
	
	/**
	 * This function is used to call and set params for message dialog.
	 */
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
					$(this).dialog("close");
				}
			}
		});
	};
	
	function showListPiAssignExternalAcc(){
		//xóa tạo lại table
		$("#AssignExternalAccessoryDetailTableCover").html('');
		$("#AssignExternalAccessoryDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listAssignExternalAccessoryDetail">'+
					'<thead>'+
						'<tr>'+
							'<th>No</th>'+
							'<th>Supplier</th>'+
							'<th>Factory Name</th>'+
							'<th>Accessory Name</th>'+
							'<th>Accessory For</th>'+
							'<th>Image</th>'+
							'<th>Assign For</th>'+
							'<th>Est. quantity pcs</th>'+
							'<th>Unit pcs</th>'+
							'<th>Est. quantity gross</th>'+
							'<th>Unit gross</th>'+
							'<th>Used inventory qt</th>'+
							'<th>Order qty</th>'+
							'<th>Order date</th>'+
							'<th>Est. delv date</th>'+
							'<th>Actual Quantity</th>'+
							'<th>Status</th>'+
							'<th>Payment</th>'+
							'<th></th>'+
						'</tr>'+
					'</thead>'+
				'</table>');
		
		//lấy giá trị của txtLotNumber
		var lotNumber = $("#txtLotNumber").val();
		//đổi
//		var lotNumber = $("#editPIdialog").find("#lotnumber").val();
		
		///gọi ajax lấy list
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/listPiassignexternalaccessoryByLotNumber/"+lotNumber,
			contentType: "application/json",
			success: function(data){
				if(data.listPiassignexternalaccessoryByLotNumber.length==0){
//					alert("Table have no data.");
				}
				var i=0;
				var preAccessoryName ="";
				$.each(data.listPiassignexternalaccessoryByLotNumber,function(key,value){
					$('<tr>').append(
							$('<td>').text(preAccessoryName.localeCompare(value.accessoryName)==0?i:++i),
							$('<td>').text(value.accsupplierShortname==null?'':value.accsupplierShortname),
							$('<td>').text(value.factoryShortname==null?'':value.factoryShortname),
							$('<td>').text(value.accessoryName),
							$('<td>').text(value.mode),
							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl+'" data-id="choriAccessoryImage/'+value.imgurl+'" /></a>')),
							$('<td>').text(value.colorName+", "+value.garmentstylecode+", "+value.sizename),
							$('<td>').text(value.estimateqty),
							$('<td>').text(value.unitPcs),
							$('<td>').text(value.estimateqtyGross==null?'':value.estimateqtyGross),
							$('<td>').text(value.unitGross==null?'':value.unitGross),
							$('<td>').text(value.usedInventoryQty==null?'':value.usedInventoryQty),
							$('<td>').text(value.orderQty==null?'':value.orderQty),
							$('<td>').text(value.orderDate==null?'':value.orderDate),
							$('<td>').text(value.estimateDeliveryDate==null?'':value.estimateDeliveryDate),
							$('<td>').text(value.actualQty==null?'':value.actualQty),
							$('<td>').text(value.status==null?'':value.status),
							$('<td>').text(value.paymentStatus==null?'':value.paymentStatus),
//							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.piassignexternalaccessorycode+'">'
//									+'<option value="Options" disabled selected>Options</option>'
//									+'<option value="AccessoryList">Equivalent</option>'
//									+'<option value="Delete">Order External Accessory</option></select>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessoryCode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="AccessoryList">Equivalent</option>'
									+'<option value="Delete">Order External Accessory</option></select>')
					).appendTo('#listAssignExternalAccessoryDetail');
					
					preAccessoryName = value.accessoryName;
				});
//				action();
				
				$('#listAssignExternalAccessoryDetail').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5,18]
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
		///end
	}
	
	showListPiAssignExternalAcc();
	$(".fancybox").fancybox();
});