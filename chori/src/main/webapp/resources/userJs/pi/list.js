$(document).ready(function(){	

function loadData(){
	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: "/Chori/pi/list",
		contentType: "application/json",
		success: function(data){
			if(data.list.length==0){
			//	alert("Table have no data. list pi");
			}
			var count=1;
			$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(count++),
							$('<td>').text(value.lotnumber),	
							$('<td>').text(value.customer1),
							$('<td>').text(value.pireceiveddate==null?"Not Yet":value.pireceiveddate),		
							$('<td>').text(value.piestshipdate),
							$('<td>').text(value.destinationcode),
							$('<td>').text(value.status),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'
									+value.lotnumber
									+'"><option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option>'
									+'<option value="FPI">FPI</option>'
									+'<option value="RFPI">RFPI</option></select>')
					).appendTo('#listPi');
			});
			action();
			$('#listPi').DataTable({
				"pagingType": "full_numbers"
		    });
		},
		error: function(){
//			alert("Can not get list pi!");
		}
	});
};
	
	
	
//	$("#listPi").find("#btnEdit").click(function(e){
//		alert("Can not get data!");
//		
//	});	
	//bảng warning và user
	function action(){	
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var lotnumber= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	
		    	showListPiAssignExternalAcc(lotnumber);
		    	
//		    	var lotNumber= $(this).data('id');
		    	
		    	//xóa tạo lại table
				$("#PiGridDetailTableCover").html('');
				$("#PiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
							'id="listPiGridDetail">'+
							'<thead>'+
							'<tr>'+
							'<th rowspan="2">Total Pcs</th>'+
							'<th rowspan="2">Color</th>'+
							'<th colspan="5">Garment Style</th>'+
							'<th rowspan="2">Size</th>'+
							'<th rowspan="2">Pcs</th>'+
							'</tr>'+
							'<tr>'+
							'<th>Name</th>'+
							'<th>Image</th>'+
							'<th>Type</th>'+
							'<th>Sewing Guide</th>'+
							'<th>Manufacture Guide</th>'+
							'</tr>'+
							'</thead>'+
							'</table>');
				
				///gọi ajax lấy detail cho lot no
				$.ajax({
					dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						lotnumber: lotnumber
					}),
					url: getAbsolutePath() +  "pi/detail",
					contentType: "application/json",
					success: function(data){
						
						//load for download file
						var hostname = window.location.hostname;
					    var port = window.location.port;
					    var pathname = window.location.pathname;
					    var protocol = window.location.protocol;
//					    $("#editPIdialog").find("#btnAdd").disabled= true;
//					    $("#editPIdialog").find("#btnAdd").attr("disabled",true);
					    if(data.currentpi.piattachedfilename!=null){
					    	$("#linkpiattached").attr("href", protocol + "//"+ hostname + ":" + port + "/Chori/choriPiattachFile/" + data.currentpi.piattachedfilename );
					    	$("#linkpiattached").text(""+data.currentpi.piattachedfilename);
					    }else
					    	$("#linkpiattached").text(" ");
					    if(data.currentpi.sewingguidename!=null){
					    	$("#linksewingguide").attr("href", protocol + "//"+ hostname + ":" + port + "/Chori/choriManufactureGuide/" + data.currentpi.sewingguidename );
					    	$("#linksewingguide").text(""+data.currentpi.sewingguidename);
					    }else
					    	$("#linksewingguide").text(" ");
					    if(data.currentpi.packingguidename!=null){
					    $("#linkpackingguide").attr("href", protocol + "//"+ hostname + ":" + port + "/Chori/choriPackingGuide/" + data.currentpi.packingguidename );
					    $("#linkpackingguide").text(""+data.currentpi.packingguidename);
					    }else
					    	$("#linkpackingguide").text(" ");
//						$("#linkpiattached").attr("href", "/download/a");
//						$("#linkpiattached").attr("href", "/download/a");
						
						//load vào edit dialog phần order or non-order
						if(data.currentpi.noneorderaccessories==1){
							$('#editPIdialog').find('#noneorderaccessories').prop('checked', true);
						}else if(data.currentpi.noneorderaccessories==0){
							$('#editPIdialog').find('#noneorderaccessories').prop('checked', false);
						}
						
							$('#editPIdialog').find('#lotnumber').val(data.currentpi.lotnumber);
							$('#sendmailDialog').find('#txtLotNumberSendMail').val(data.currentpi.lotnumber);
							
							$('#editPIdialog').find('#destinationcode').val(data.currentpi.destinationcode);
							$('#editPIdialog').find('#packingguidecode').val(data.currentpi.packingguidecode);
							$('#editPIdialog').find('#pireceiveddateEdit').val(data.currentpi.pireceiveddate);
							$('#editPIdialog').find('#piestshipdateEdit').val(data.currentpi.piestshipdate);
//							alert(data.currentpi.piestshipdate);
							$('#editPIdialog').find('#mfgstarteddateEdit').val(data.currentpi.mfgstarteddate);
							$('#editPIdialog').find('#mfgfinisheddateEdit').val(data.currentpi.mfgfinisheddate);
							$('#editPIdialog').find('#factorycode').val(data.currentpi.factorycode);
							$('#editPIdialog').find('#brandcode').val(data.currentpi.brandcode);
							$('#editPIdialog').find('#customer1').val(data.currentpi.customer1);
							$('#editPIdialog').find('#shipmentstatus').val(data.currentpi.shipmentstatus);
							var mfgstarteddate = $("#editPIdialog").find("#mfgstarteddate2").val();
							var mfgfinisheddate = $("#editPIdialog").find("#mfgfinisheddate2").val();
							
							
							
							AssignInternalAccessoriesMainFunction();
//						if(Date.parse(mfgfinisheddate) < Date.parse(mfgstarteddate))
//							alert(mfgfinisheddate);
					
//						var cus = $('#editPIdialog').find('#customer1').val();
//						alert(cus+ "hi");
						
//						alert($('#editPIdialog').find('#customer1').val(data.currentpi.customer1));
//						if(data.currentpi.customer1=="Unknown"){
//							$('#editPIdialog').find('#customer1').val("Unknown");
//						$('#editPIdialog').find('#brandcode').val("Unknown");
//						}	
//						else{
//							$('#editPIdialog').find('#customer1').val(data.currentpi.customer1);
//							$('#editPIdialog').find('#brandcode').val(data.currentpi.brandcode);
//						}
							$('#editPIdialog').find('#consignee').val(data.currentpi.consignee);
							$('#editPIdialog').find('#status').val(data.currentpi.status);
							$('#editPIdialog').find('#remark').val(data.currentpi.remark);
						
							//call ajax get name for brandname with customer1						
							var customer1 = $('#editPIdialog').find('#customer1').val();
							var drpBrandname = $("#editPIdialog").find("#brandcode");
							drpBrandname.empty();
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/brand/list/",
								contentType: "application/json",
								success: function(data1){
									if(data1.list.length==0){
//										alert("Table have no data. brand list");
									}
									$.each(data1.list,function(key,value){
									//	alert(value.customerCode);
										if(value.customerCode == customer1){
											drpBrandname.append($('<option>',
													{
														value : value.brandcode,
														text : value.brandname
													}));
										}
									});
								}
							})
							
							var destination = $('#editPIdialog').find('#destinationcode');
							destination.val(data.currentpi.destinationcode);
							var country = data.currentpi.country;
							var drpContry = $("#editPIdialog").find("#country");
	//						$("#editPIdialog").find("#country").val(data.currentpi.country);
							drpContry.empty();
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/destination/list/",
								contentType: "application/json",
								success: function(data2){							
									if(data2.list.length==0){
										alert("Table have no data. destination list");
									}
									$.each(data2.list,function(key,value){
										drpContry.append($('<option>',
												{
													value : value.description,
													text : value.description
												}));
//										if(value.destinationcode == data.currentpi.destinationcode){
//											$("#editPIdialog").find("#country").val(destination.val());
////											$("#editPIdialog").find("#country").val(destination.val());
////											$("#drpContry option[text=" + destination.val() +"]").prop("selected",true) ;
////											$("#editPIdialog").find("#country").val().prop("selected",true);
//										}
									});
//									
									$("#editPIdialog").find("#country").val(country);
									
								}
							})
						//alert(data.currentpi.country);
					//	drpContry.val(data.currentpi.country);
//						$("#editPIdialog").find("#country").val(country);
						$("#editAccessoryForm").attr("action", "accessory/edit");
						},
						error: function(){
							alert("Can not get data list destination!");
						}
					});
				///end gọi ajax lấy detail cho lot no
				
				

			
				
//				///gọi ajax lấy PI grid detail
//				$.ajax({
//					dataType: "json",
//					type: 'GET',
//					data: {},
//					url: "/Chori/pi/piGridDetail/"+lotnumber,
//					contentType: "application/json",
//					success: function(data){
//						
//						if(data.piGridDetail.length==0){
//							alert("Table have no data.");
//						}
//						
//						$.each(data.piGridDetail,function(key,value){
//							$('<tr>').append(
//									$('<td>').text(value.totalPcs),
//									$('<td>').html(value.colorName+'<br>'+value.colorPcs),
//									$('<td>').html(value.garmentstyle+'<br>'+value.garmentstylePcs),
//									$('<td>').html(((value.imgUrl1==null)||
//											(value.imgUrl1.trim()=='')?'':
//												'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl1+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl1+'" data-id="choriGarmentStyleImage/'+value.imgUrl1+'" /></a>'
//												+'<a class="fancybox" rel="group" hidden  href="choriGarmentStyleImage/'+value.imgUrl2+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl2+'" data-id="choriGarmentStyleImage/'+value.imgUrl2+'" /></a>'
//												+'<a class="fancybox" rel="group" hidden  href="choriGarmentStyleImage/'+value.imgUrl3+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl3+'" data-id="choriGarmentStyleImage/'+value.imgUrl3+'" /></a>'
//												+'<a class="fancybox" rel="group" hidden href="choriGarmentStyleImage/'+value.imgUrl4+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl4+'" data-id="choriGarmentStyleImage/'+value.imgUrl4+'" /></a>'
//												+'<a class="fancybox" rel="group" hidden href="choriGarmentStyleImage/'+value.imgUrl5+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl5+'" data-id="choriGarmentStyleImage/'+value.imgUrl5+'" /></a>'
//									)),
//									$('<td>').html(value.typecode+'<br>'+value.typePcs),
//									$('<td>').html("Sewing guide"),
//									$('<td>').html("Packing guide"),
//									$('<td>').text(value.sizename),
//									$('<td>').text("Pieces")
//							).appendTo('#listPiGridDetail');	
//							
//							preAccessoryName = value.accessoryName;
//						});
//						
//						$('#listPiGridDetail').DataTable( {
//							"sPaginationType": "full_numbers",
//							paging: true,
//					        rowsGroup: [0,1,2,3,4]
//					    } );
//					},
//					error: function(){
////						alert("Can not get data!");
////						callMessageDialog("Message", "Something wrong with pi grid detail! 147");
//						callMessageDialog("Message", "Not Assign Pi Grid Yet !");
//					}
//				});
//				///end gọi ajax lấy PI grid detail
				
				//lấy pi grid detail phân theo màu
				//xóa tạo lại table
				$("#PiGridDetailTableCover").html('');
				$("#PiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
							'id="listPiGridDetail">'+
							'<thead>'+
							'<tr>'+
							'<th rowspan="2">Total Pcs</th>'+
							'<th rowspan="2">Color</th>'+
							'<th colspan="3">Garment Style</th>'+
							'<th rowspan="2">Size</th>'+
							'<th rowspan="2">Pcs</th>'+
							'<th rowspan="2">FA</th>'+
							'</tr>'+
							'<tr>'+
							'<th>Name</th>'+
							'<th>Image</th>'+
							'<th>Type</th>'+
							'</tr>'+
							'</thead>'+
							'</table>');
				
				///gọi ajax lấy PI grid detail
				$.ajax({
					dataType: "json",
					type: 'POST',
					data:
						JSON.stringify({
							lotnumber: lotnumber,										
						}),
					url: "/Chori/pi/piGridDetailGroupedByColor",
					contentType: "application/json",
					success: function(data){
						//nếu đã assign fabric rồi
						if(data.assignedFabric===true){
							$.each(data.piGridDetail,function(key,value){
								
								var garmentStyleString = value.garmentstyle.split("@@@",1);
								
								$('<tr>').append(
										$('<td>').html(value.totalPcs+"/"+value.totalFaPcs),
										$('<td>').html(value.colorName+'<br>'+value.colorPcs+"/"+value.colorFaPcs),
										$('<td>').html(garmentStyleString+'<br>'+value.garmentstylePcs+"/"+value.garmentstyleFaPcs),
										$('<td>').html(((value.imgUrl1==null)||(value.imgUrl1.trim()==''))?'':
													'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl1+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl1+'" data-id="choriGarmentStyleImage/'+value.imgUrl1+'" /></a>'),
										$('<td>').html(value.typecode+'<br>'+value.typePcs+"/"+value.typeFaPcs),
										$('<td>').html(value.sizename),
										$('<td>').html(value.pcs),
										$('<td>').html(value.faValue)
								).appendTo('#listPiGridDetail');	
								
//								preAccessoryName = value.accessoryName;
							});
							
							$('#listPiGridDetail').DataTable( {
								"sPaginationType": "full_numbers",
								paging: true,
						        rowsGroup: [0,1,2,3,4,5]
						    } );
						}else if(data.assignedFabric===false){//chưa assign fabric
							$("#PiGridDetailTableCover").html('');
							$("#PiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
										'id="listPiGridDetail">'+
										'<thead>'+
										'<tr>'+
										'<th rowspan="2">Total Pcs</th>'+
										'<th rowspan="2">Color</th>'+
										'<th colspan="3">Garment Style</th>'+
										'<th rowspan="2">Size</th>'+
										'<th rowspan="2">Pcs</th>'+
										'</tr>'+
										'<tr>'+
										'<th>Name</th>'+
										'<th>Image</th>'+
										'<th>Type</th>'+
										'</tr>'+
										'</thead>'+
										'</table>');
							
							$.each(data.piGridDetail,function(key,value){
								
								var garmentStyleString = value.garmentstyle.split("@@@",1);
								
								$('<tr>').append(
										$('<td>').html(value.totalPcs),
										$('<td>').html(value.colorName+'<br>'+value.colorPcs),
										$('<td>').html(garmentStyleString+'<br>'+value.garmentstylePcs),
										$('<td>').html(((value.imgUrl1==null)||(value.imgUrl1.trim()==''))?'':
													'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl1+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl1+'" data-id="choriGarmentStyleImage/'+value.imgUrl1+'" /></a>'),
										$('<td>').html(value.typecode+'<br>'+value.typePcs),
										$('<td>').html(value.sizename),
										$('<td>').html(value.pcs)
								).appendTo('#listPiGridDetail');	
								
//								preAccessoryName = value.accessoryName;
							});
							
							$('#listPiGridDetail').DataTable( {
								"sPaginationType": "full_numbers",
								paging: true,
						        rowsGroup: [0,1,2,3,4,5]
						    } );
						}
					},
					error: function(){
//						alert("Can not get data!");
//						callMessageDialog("Message", "Something wrong with pi grid detail! 147");
						callMessageDialog("Message", "Not Assign Pi Grid Yet !");
					}
				});
				///end gọi ajax lấy PI grid detail
				//end lấy PI grid detail theo màu
		    	
		    	$("#editPIdialog").dialog({
					modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit PI",
					height: 900,
					width: 1400,
					hide: {
						effect: "blind",
						duration: 300
					},
//					buttons:{
//						"Cancel": function(){
//							$("#btnAssignInternalAccessories").unbind("click");
//							$(".btnChooseOrder").unbind("click");
//							$("#editPIdialog").dialog("close");
//							$("#editPIdialog").find('#piattached').html('');
//							$("#editPIdialog").find('#manufactureguide').html('');
//							$("#editPIdialog").find('#packingguide').html('');
//						}
//					},
					close: function(){
						$("#btnAssignInternalAccessories").unbind("click");
						$(".btnChooseOrder").unbind("click");
						$("#editPIdialog").find('#piattached').html('');
						$("#editPIdialog").find('#manufactureguide').html('');
						$("#editPIdialog").find('#packingguide').html('');
						clearAfterCloseEditDialog();
					}
				});
		    };
		    
		    $("#editPIdialog").find('#btnCancelEditPIDialog').on('click',function(){
				$("#btnAssignInternalAccessories").unbind("click");
				$(".btnChooseOrder").unbind("click");
				$("#editPIdialog").find('#piattached').html('');
				$("#editPIdialog").find('#manufactureguide').html('');
				$("#editPIdialog").find('#packingguide').html('');
				clearAfterCloseEditDialog();
				$("#editPIdialog").dialog("close");
		    });
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	$("#deletePiDialog").find("#messageContent").text('Are you sure you want to delete Pi "' + lotnumber+'"?');
				
				$("#deletePiDialog").dialog({
					modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									lotnumber: lotnumber
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "pi/delete/",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete Pi "'+ lotnumber+ '" successfully!');
											//close dialog
											$("#deletePiDialog").dialog("close");
											//reload table
//											$("#listPi").dataTable().fnDestroy();
//											$('#listPi tbody').empty();
//											loadData();
											reloadTableWithStatus();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t Delete Pi "'+ lotnumber+ '"!');
											$("#deletePiDialog").dialog("close");
										}else{
//											alert("this cant be show, 243!");
										}
									}else{
//										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
									callMessageDialog("Message", 'Can\'t Delete Pi "'+ lotnumber+ '"!');
									$("#deletePiDialog").dialog("close");
								}
							});
						},
						"Cancel": function(){
							$("#deletePiDialog").dialog("close");
						}
					}
				});
		    };
		    //end if user choose delete option
		    //Selected FPI
		    if(valueSelected=="FPI"){
		    	
		    	showListPiAssignExternalAccFpiVer(lotnumber);
		    	
		    	$("#viewFPIdialog").find("#FpiGridDetailTableCover").html('');
		    	
		    	$("#viewFPIdialog").dialog({
					show : {
						effect : "blind",
						duration : 500
					},
					title : "FPI Details",
					height : 700,
					width : 1300,
					modal : true,
					close : function() {
						$("#viewFPIdialog").find("#FpiGridDetailTableCover").html('');
					}
				});
		    	///gọi ajax lấy detail cho lot no
				$.ajax({
					dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						lotnumber: lotnumber
					}),
					url: getAbsolutePath() +  "pi/detail",
					contentType: "application/json",
					success: function(data){
						
						//load for download file
						var hostname = window.location.hostname;
					    var port = window.location.port;
					    var pathname = window.location.pathname;
					    var protocol = window.location.protocol;
					   // $("#editPIdialog").find("#btnAdd").disabled= true;
//					    $("#editPIdialog").find("#btnAdd").attr("disabled",true);
					    if(data.currentpi.piattachedfilename!=null){
					    	$("#linkpiattached").attr("href", protocol + "//"+ hostname + ":" + port + "/Chori/choriPiattachFile/" + data.currentpi.piattachedfilename );
					    	$("#linkpiattached").text(""+data.currentpi.piattachedfilename);
					    }else
					    	$("#linkpiattached").text(" ");
				    
//						$("#linkpiattached").attr("href", "/download/a");
//						$("#linkpiattached").attr("href", "/download/a");
						
						//load vào edit dialog phần order or non-order
						if(data.currentpi.noneorderaccessories==1){
							$('#viewFPIdialog').find('#noneorderaccessories').prop('checked', true);
						}else if(data.currentpi.noneorderaccessories==0){
							$('#viewFPIdialog').find('#noneorderaccessories').prop('checked', false);
						}
						
							$('#viewFPIdialog').find('#txtLotNumber').val(data.currentpi.lotnumber);							
							$('#viewFPIdialog').find('#txtDestination').val(data.currentpi.destinationcode);
							$('#viewFPIdialog').find('#packingguide').val(data.currentpi.packingguidecode);
							$('#viewFPIdialog').find('#txtPIReceivedDate').val(data.currentpi.pireceiveddate);
							$('#viewFPIdialog').find('#txtPIEstShipDate').val(data.currentpi.piestshipdate);
							$('#viewFPIdialog').find('#txtMFGStartedDate').val(data.currentpi.mfgstarteddate);
							$('#viewFPIdialog').find('#txtMFGFinishedDate').val(data.currentpi.mfgfinisheddate);
							$('#viewFPIdialog').find('#txtFactory').val(data.currentpi.factorycode);
							//$('#viewRFPIdialog').find('#txtBrandName').val(data.currentpi.brandcode);
							$('#viewFPIdialog').find('#txtCustomerName').val(data.currentpi.customer1);
							
							
							
							var mfgstarteddate = $("#viewFPIdialog").find("#mfgstarteddate2").val();
							var mfgfinisheddate = $("#viewFPIdialog").find("#mfgfinisheddate2").val();
							
							AssignInternalAccessoriesMainFunction();
//	
							$('#viewFPIdialog').find('#txtConsigneeName').val(data.currentpi.consignee);
							$('#viewFPIdialog').find('#txtStatus').val(data.currentpi.status);
							$('#viewFPIdialog').find('#remark').val(data.currentpi.remark);
						
							//call ajax get name for brandname with customer1						
							var customer1 = $('#viewFPIdialog').find('#txtCustomerName').val();
							var brandName = $('#viewFPIdialog').find('#txtBrandName');
							
//							drpBrandname.empty();
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/brand/list/",
								contentType: "application/json",
								success: function(data){
									if(data.list.length==0){
//										alert("Table have no data brand list");
									}
									$.each(data.list,function(key,value){
									//	alert(value.customerCode);
										if(value.customerCode == customer1){
											brandName.text(value.brandname);
											brandName.val(value.brandname);
										}
									});
								}
							})
							
							
							var destination = $('#viewFPIdialog').find('#txtDestination').val(data.currentpi.destinationcode);
							var country = $("#viewFPIdialog").find("#txtCountry").val(data.currentpi.country);
							
							//drpContry.empty();
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/destination/list/",
								contentType: "application/json",
								success: function(data){							
									if(data.list.length==0){
//										alert("Table have no data destination list");
									}
									$.each(data.list,function(key,value){
										if(value.destinationcode == destination){
											country.text(value.description);
											country.val(value.description);
										}
										
									});
								}
							})
						
						$("#editAccessoryForm").attr("action", "accessory/edit");
						},
						error: function(){
//							alert("Can not get data!");
						}
					});
				
				//clear dropdownlist fpi, add please select
				$('#viewFPIdialog').find('#fpiVersion').empty();
				$('#viewFPIdialog').find('#fpiVersion').append('<option disabled selected>Select Version</option>');
				//load version fpi
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: "/Chori/fpi/getListFpiVersionByLotNumber/"+lotnumber,
					contentType: "application/json",
					success: function(data){
						$.each( data.listFpiVersionByLotNumber, function( key, value ) {
	                        $('#viewFPIdialog').find('#fpiVersion').append($('<option>', {
	                            value: value,
	                            text: value
	                        }));
	                    });
						
						$('#viewFPIdialog').find('#fpiVersion').off('change');
						
						$('#viewFPIdialog').find('#fpiVersion').on('change', function (e) {
						    var optionSelected = $("option:selected", this);
						    var valueSelected = this.value;
						    
						    $("#viewFPIdialog").find("#FpiGridDetailTableCover").html('');
//						    alert("XDDDD");
						    
						    //gọi hàm lấy fpi grid detail
						    loadFpiGridDetailByLotNumberAndVersion(lotnumber,valueSelected);
						});
						
					},error: function(){
						
					}
				});
				
//				$('#viewFPIdialog').find('#fpiVersion').on('change', function (e) {
//				    var optionSelected = $("option:selected", this);
//				    var valueSelected = this.value;
//				    
//				    $("#viewFPIdialog").find("#FpiGridDetailTableCover").html('');
//				    alert("XDDDD");
//				    
//				    //gọi hàm lấy fpi grid detail
//				    loadFpiGridDetailByLotNumberAndVersion(lotnumber,valueSelected);
//				});
				
				//gọi hàm lấy fpi grid detail
//				loadFpiGridDetailByLotNumberAndVersion(lotnumber,1);
		    }
		    
		    if(valueSelected=="RFPI"){
		    	
		    	showListPiAssignExternalAccRfpiVer(lotnumber);
		    	
		    	$("#RfpiGridDetailTableCover").html('');
		    	
		    	$("#viewRFPIdialog").dialog({
					show : {
						effect : "blind",
						duration : 500
					},
					title : "RFPI Details",
					height : 700,
					width : 1300,
					modal : true,
					close : function() {

					}
				});
		    	///gọi ajax lấy detail cho lot no
		    	$.ajax({
					dataType: "json",
					type: 'POST',
					data:JSON.stringify({
						lotnumber: lotnumber
					}),
					url: getAbsolutePath() +  "pi/detail",
					contentType: "application/json",
					success: function(data){
						
						//load for download file
						var hostname = window.location.hostname;
					    var port = window.location.port;
					    var pathname = window.location.pathname;
					    var protocol = window.location.protocol;
					   // $("#editPIdialog").find("#btnAdd").disabled= true;
//					    $("#editPIdialog").find("#btnAdd").attr("disabled",true);
					    if(data.currentpi.piattachedfilename!=null){
					    	$("#linkpiattached").attr("href", protocol + "//"+ hostname + ":" + port + "/Chori/choriPiattachFile/" + data.currentpi.piattachedfilename );
					    	$("#linkpiattached").text(""+data.currentpi.piattachedfilename);
					    }else
					    	$("#linkpiattached").text(" ");
				    
//						$("#linkpiattached").attr("href", "/download/a");
//						$("#linkpiattached").attr("href", "/download/a");
						
						//load vào edit dialog phần order or non-order
						if(data.currentpi.noneorderaccessories==1){
							$('#viewRFPIdialog').find('#noneorderaccessories').prop('checked', true);
						}else if(data.currentpi.noneorderaccessories==0){
							$('#viewRFPIdialog').find('#noneorderaccessories').prop('checked', false);
						}
						
						$('#viewRFPIdialog').find('#lotnumber').val(data.currentpi.lotnumber);						
						$('#viewRFPIdialog').find('#destinationcode').val(data.currentpi.destinationcode);
						$('#viewRFPIdialog').find('#packingguidecode').val(data.currentpi.packingguidecode);
						$('#viewRFPIdialog').find('#pireceiveddate2').val(data.currentpi.pireceiveddate);
						$('#viewRFPIdialog').find('#piestshipdate2').val(data.currentpi.piestshipdate);
						$('#viewRFPIdialog').find('#mfgstarteddate2').val(data.currentpi.mfgstarteddate);
						$('#viewRFPIdialog').find('#mfgfinisheddate2').val(data.currentpi.mfgfinisheddate);
						$('#viewRFPIdialog').find('#factorycode').val(data.currentpi.factorycode);
						$('#viewRFPIdialog').find('#brandcode').val(data.currentpi.brandcode);
						$('#viewRFPIdialog').find('#customer1').val(data.currentpi.customer1);
							
							
							
							var mfgstarteddate = $("#viewRFPIdialog").find("#mfgstarteddate2").val();
							var mfgfinisheddate = $("#viewRFPIdialog").find("#mfgfinisheddate2").val();
							
				//			AssignInternalAccessoriesMainFunction();
//	
							$('#viewRFPIdialog').find('#consignee').val(data.currentpi.consignee);
							$('#viewRFPIdialog').find('#status').val(data.currentpi.status);
							$('#viewRFPIdialog').find('#remark').val(data.currentpi.remark);
						
							//call ajax get name for brandname with customer1						
							var customer1 = $('#viewRFPIdialog').find('#customer1').val();
							var drpBrandname = $("#viewRFPIdialog").find("#brandcode");
						//	drpBrandname.empty();
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/brand/list/",
								contentType: "application/json",
								success: function(data){
									if(data.list.length==0){
//										alert("Table have no data. xxx");
									}
									$.each(data.list,function(key,value){
									//	alert(value.customerCode);
										if(value.customerCode == customer1){
											drpBrandname.append($('<option>',
													{
														value : value.brandcode,
														text : value.brandname
													}));
										}
									});
								}
							})
							
						
							var destination = $('#viewRFPIdialog').find('#destinationcode');
							destination.val(data.currentpi.destinationcode);
							var country = data.currentpi.country;
							var drpContry = $("#viewRFPIdialog").find("#country");
							
		//					drpContry.empty();
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/destination/list/",
								contentType: "application/json",
								success: function(data){							
									if(data.list.length==0){
//										alert("Table have no data. xxx");
									}
									$.each(data.list,function(key,value){
										drpContry.append($('<option>',
												{
													value : value.description,
													text : value.description
												}));										
									});
									$("#viewRFPIdialog").find("#country").val(country);
								}
							})	
							
							loadFabricassignedtoPI(lotnumber);
//							loadRfpiGridDetail(lotnumber, version);
				
						},
						error: function(){
//							alert("Can not get data!");
						}
					});
				///end gọi ajax lấy detail cho lot no
		    	
		    	//clear dropdownlist rfpi, add please select
					$('#viewRFPIdialog').find('#rfpiVersion').empty();
					$('#viewRFPIdialog').find('#rfpiVersion').append('<option disabled selected>Select Version</option>');
					//load version fpi
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/rfpi/getListRfpiVersionByLotNumber/"+lotnumber,
						contentType: "application/json",
						success: function(data){
							$.each( data.listRfpiVersionByLotNumber, function( key, value ) {
		                        $('#viewRFPIdialog').find('#rfpiVersion').append($('<option>', {
		                            value: value,
		                            text: value
		                        }));
		                    });
							
							$('#viewRFPIdialog').find('#rfpiVersion').off('change');
							
							$('#viewRFPIdialog').find('#rfpiVersion').on('change', function (e) {
							    var optionSelected = $("option:selected", this);
							    var valueSelected = this.value;
							    
							    $("#viewRFPIdialog").find("#RfpiGridDetailTableCover").html('');
							    
							    //gọi hàm lấy rfpi grid detail
								loadRfpiGridDetail(lotnumber,valueSelected);
							    
							});
							
						},error: function(){
							
						}
					});
		    					
					$("#viewRFPIdialog").find('#btnCancel').on('click',function(){						
						$("#viewRFPIdialog").dialog("close");
				 });
		    }		    		    
		});
	};
	
	//function load grid FPI detail
	function loadFpiGridDetailByLotNumberAndVersion(lotnumber, version){
		
//		$("#listFpiGridDetail").dataTable().fnDestroy();
//		$('#listFpiGridDetail tbody').empty();
		
		//xóa tạo lại table
		$("#viewFPIdialog").find("#FpiGridDetailTableCover").html('');
		$("#viewFPIdialog").find("#FpiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFpiGridDetail">'+
					'<thead>'+
					'<tr>'+
					'<th rowspan="2">Total Pcs</th>'+
					'<th rowspan="2">Color</th>'+
					'<th colspan="3">Garment Style</th>'+
					'<th rowspan="2">Size</th>'+
					'<th rowspan="2">FA</th>'+
					'<th rowspan="2">FPI</th>'+
					'</tr>'+
					'<tr>'+
					'<th>Name</th>'+
					'<th>Image</th>'+
					'<th>Type</th>'+
					'</tr>'+
					'</thead>'+
					'</table>');
//		$("#listFpiGridDetail").dataTable().fnDestroy();
//		$('#listFpiGridDetail tbody').empty();
		
		///g?i ajax l?y FPI grid detail
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fpi/fpiGridDetail/"+lotnumber+"/"+version,
//			url: "/Chori/fpi/fpiGridDetail/"+"Lot1/"+1,
			contentType: "application/json",
			success: function(data){
				
				if(data.fpiGridDetail.length==0){
					//alert("Table have no data.");
				}
				
				$.each(data.fpiGridDetail,function(key,value){
					$('<tr>').append(
							$('<td>').html("<span "+((value.totalFpiPcs<=value.totalFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.totalFaPcs+"/"+value.totalFpiPcs+"/"+(value.totalFpiPcs-value.totalFaPcs)+"<span>"),
							$('<td>').html("<span "+((value.colorFpiPcs<=value.colorFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.colorName+'<br>'+value.colorFaPcs+"/"+value.colorFpiPcs+"/"+(value.colorFpiPcs-value.colorFaPcs)+"<span>"),
							$('<td>').html("<span "+((value.garmentstyleFpiPcs<=value.garmentstyleFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.garmentstyle+'<br>'+value.garmentstyleFaPcs+"/"+value.garmentstyleFpiPcs+"/"+(value.garmentstyleFpiPcs-value.garmentstyleFaPcs)+"<span>"),
							$('<td>').html(((value.imgUrl==null)||(value.imgUrl.trim()==''))?'':
										'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl+'" data-id="choriGarmentStyleImage/'+value.imgUrl+'" /></a>'),
							$('<td>').html("<span "+((value.typeFpiPcs<=value.typeFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.typecode+'<br>'+value.typeFaPcs+"/"+value.typeFpiPcs+"/"+(value.typeFpiPcs-value.typeFaPcs)+"<span>"),
							$('<td>').html(value.sizename),
							$('<td>').html(value.faPcs),
							$('<td>').html(value.fpiPcs)
					).appendTo('#listFpiGridDetail');	
					
					preAccessoryName = value.accessoryName;
				});
				
				$('#listFpiGridDetail').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5]
			    } );
			},
			error: function(){
//				alert("Can not get data!");
//				callMessageDialog("Message", "Something wrong with pi grid detail! 147");
				callMessageDialog("Message", "Not Assign Pi Grid Yet !");
			}
		});
		///end g?i ajax l?y FPI grid detail	
	}
	//end function load grid FPI detail
	
	function loadRfpiGridDetail(lotnumber, version){
		//xóa tạo lại table
		$("#RfpiGridDetailTableCover").html('');
		$("#RfpiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listRfpiGridDetail">'+
					'<thead>'+
					'<tr>'+
					'<th rowspan="2">Total Pcs<br/>(FPI/RFPI/RFPI-FPI)</th>'+
					'<th rowspan="2">Color</th>'+
					'<th colspan="3">Garment Style</th>'+
					'<th rowspan="2">Size</th>'+
					'<th rowspan="2">FPI</th>'+
					'<th rowspan="2">RFPI</th>'+
					'</tr>'+
					'<tr>'+
					'<th>Name</th>'+
					'<th>Image</th>'+
					'<th>Type</th>'+
					'</tr>'+
					'</thead>'+
					'</table>');
		
		///gọi ajax lấy RFPI grid detail
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/rfpi/rfpiGridDetail/"+lotnumber+"/"+version,
			contentType: "application/json",
			success: function(data){
				
				if(data.rfpiGridDetail.length==0){
				//	alert("Table have no data.");
				}
				
				$.each(data.rfpiGridDetail,function(key,value){
					$('<tr>').append(
							$('<td>').html("<span "+((value.totalRfpiPcs<=value.totalFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.totalFpiPcs+"/"+value.totalRfpiPcs+"/"+(value.totalRfpiPcs-value.totalFpiPcs)+"<span>"),
							$('<td>').html("<span "+((value.colorRfpiPcs<=value.colorFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.colorName+'<br>'+value.colorFpiPcs+"/"+value.colorRfpiPcs+"/"+(value.colorRfpiPcs-value.colorFpiPcs)+"<span>"),
							$('<td>').html("<span "+((value.garmentstyleRfpiPcs<=value.garmentstyleFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.garmentstyle+'<br>'+value.garmentstyleFpiPcs+"/"+value.garmentstyleRfpiPcs+"/"+(value.garmentstyleRfpiPcs-value.garmentstyleFpiPcs)+"<span>"),
							$('<td>').html(((value.imgUrl==null)||(value.imgUrl.trim()==''))?'':
										'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl+'" data-id="choriGarmentStyleImage/'+value.imgUrl+'" /></a>'),
							$('<td>').html("<span "+((value.typeRfpiPcs<=value.typeFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.typecode+'<br>'+value.typeFpiPcs+"/"+value.typeRfpiPcs+"/"+(value.typeRfpiPcs-value.typeFpiPcs)+"<span>"),
							$('<td>').html(value.sizename),
							$('<td>').html(value.fpiPcs),
							$('<td>').html(value.rfpiPcs)
					).appendTo('#listRfpiGridDetail');	
					
					preAccessoryName = value.accessoryName;
				});
				
				$('#listRfpiGridDetail').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5]
			    } );
			},
			error: function(){
	//			alert("Can not get data!");
	//			callMessageDialog("Message", "Something wrong with pi grid detail! 147");
	//			callMessageDialog("Message", "Not Assign Pi Grid Yet !");
			}
		});
		///end gọi ajax lấy RFPI grid detail	
	}
	
	
	//load list fabric assigned to PI in RFPI
	
	function loadFabricassignedtoPI(lotnumber){
		$("#fabricAssignedDetailTableCover").html('');
		$("#fabricAssignedDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFabricAssignedDetail">'+
					'<thead>'+
					'<tr>'+
					'<th>Fabric Indent</th>'+
					'<th>Color</th>'+
					'<th>Garment Style</th>'+
					'<th>Assign Quantity</th>'+
					'</tr>'+					
					'</thead>'+
					'</table>');
		///gọi ajax lấy piassign fabric detail
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/listfabricassignedtopi/"+lotnumber ,
			contentType: "application/json",
			success: function(data){
				
				if(data.list.length==0){
				//	alert("Table have no data.");
				}
				
				$.each(data.list,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.fabricno),
							$('<td>').text(value.color),
							$('<td>').text(value.garmentstyle),
							$('<td>').text(value.assignquantity)
					).appendTo('#listFabricAssignedDetail');	
					
					
				});
				$('#listFabricAssignedDetail').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2]
				});
								
			},
			error: function(){
	//			alert("Can not get data!");
	//			callMessageDialog("Message", "Something wrong with pi grid detail! 147");
	//			callMessageDialog("Message", "Not Assign Pi Grid Yet !");
			}
		});
		///end gọi ajax lấy RFPI grid detail	
	}
	
	//function load data theo shipment status
	
	/**
	 * This function is used to change table data when choose status
	 */
	$('#ddlShipmentStatus').on('change',function(){
		//lấy ra value đc chọn
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
//		alert(valueSelected);
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "pi/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listPi").dataTable().fnDestroy();
				$('#listPi tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					//nếu value là all thì gọi lại loadData()
					if(valueSelected=='All'){
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.lotnumber),	
								$('<td>').text(value.customer1),
								$('<td>').text(value.pireceiveddate==null?"Not Yet":value.pireceiveddate),		
								$('<td>').text(value.piestshipdate),
								$('<td>').text(value.destinationcode),
								$('<td>').text(value.status),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'
										+value.lotnumber
										+'"><option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option>'
										+'<option value="FPI">FPI</option>'
										+'<option value="RFPI">RFPI</option></select>')
						).appendTo('#listPi');
						
					}else{//không thì xuất ra theo cái status
						
						if(valueSelected==value.shipmentstatus){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.lotnumber),	
									$('<td>').text(value.customer1),
									$('<td>').text(value.pireceiveddate==null?"Not Yet":value.pireceiveddate),		
									$('<td>').text(value.piestshipdate),
									$('<td>').text(value.destinationcode),
									$('<td>').text(value.status),
									$('<td>').html('<select class="selectpicker selectOption" data-id="'
											+value.lotnumber
											+'"><option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option>'
											+'<option value="FPI">FPI</option>'
											+'<option value="RFPI">RFPI</option></select>')
							).appendTo('#listPi');
						}
					}
				});
				//console.log(data);
				action();

				$('#listPi').DataTable( {
					"pagingType": "full_numbers"
			    } );
				//alert("Thành công");
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	});
	
	function reloadTableWithStatus(){
		//lấy ra value đc chọn
		var optionSelected = $('#ddlShipmentStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "pi/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listPi").dataTable().fnDestroy();
				$('#listPi tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					//nếu value là all thì gọi lại loadData()
					if(valueSelected=='All'){

						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.lotnumber),	
								$('<td>').text(value.customer1),
								$('<td>').text(value.pireceiveddate==null?"Not Yet":value.pireceiveddate),		
								$('<td>').text(value.piestshipdate),
								$('<td>').text(value.destinationcode),
								$('<td>').text(value.status),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'
										+value.lotnumber
										+'"><option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option>'
										+'<option value="FPI">FPI</option>'
										+'<option value="RFPI">RFPI</option></select>')
						).appendTo('#listPi');
						
					}else{
						
						if(valueSelected==value.shipmentstatus){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.lotnumber),	
									$('<td>').text(value.customer1),
									$('<td>').text(value.pireceiveddate==null?"Not Yet":value.pireceiveddate),		
									$('<td>').text(value.piestshipdate),
									$('<td>').text(value.destinationcode),
									$('<td>').text(value.status),
									$('<td>').html('<select class="selectpicker selectOption" data-id="'
											+value.lotnumber
											+'"><option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option>'
											+'<option value="FPI">FPI</option>'
											+'<option value="RFPI">RFPI</option></select>')
							).appendTo('#listPi');
						}
					}
				});
				//console.log(data);
				action();

				$('#listPi').DataTable( {
					"pagingType": "full_numbers"
			    } );
				//alert("Thành công");
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	};
	
	
	$('#btnAddPi').click(function(){
		$("#dialogAddPi").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Create New Pi",
			height: 650,
			width: 1280,
			buttons:{
				"Cancel": function(){
					$(this).dialog("close");
					//clear all input, css after close dialog
					clearAfterCloseAddDialog();
				}
			},
			close: function(){
				$(this).dialog("close");
				clearAfterCloseAddDialog();
			}
		});
	});
	
	$('#btnLogofChange').click(function(){
		$("#dialogLogOfChange").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Log of Change",
			height: 650,
			width: 1280,
			buttons:{
				
				"Cancel": function(){
					$(this).dialog("close");
					//clear all input, css after close dialog
					clearAfterCloseAddDialog();
				}
			},
			close: function(){
				$(this).dialog("close");
				clearAfterCloseAddDialog();
			}
		});
	});
	
	$('#btnAddLogOfChange').click(function(){
		$("#sendmailDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Send Mail",
			height: 600,
			width: 1200,
			buttons:{
				"Cancel": function(){
					$("#sendmailDialog").dialog("close");
				}
				
			},
			close: function(){
				$(this).dialog("close");
				clearAfterCloseAddDialog();
			},
		});
//		$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
//			$("#sendMailDialog").dialog("close");
//		});
	});


	/**
	 * clear all input + css after close add dialog
	 */
	function clearAfterCloseAddDialog(){
		$("#dialogAddPi").find("#lotnumber").val("");
		$("#dialogAddPi").find("#errorLotnumber").text("");
		$("#dialogAddPi").find("#lotnumber").css("border-color", "");
		$("#dialogAddPi").find("#lotnumber").css("background-color", "white");
		
		$("#dialogAddPi").find("#pireceiveddate").val("");
		
		$("#dialogAddPi").find("#piestshipdate").val("");
		$("#dialogAddPi").find("#errorPiestshipdate").text("");
		$("#dialogAddPi").find("#piestshipdate").css("background-color", "white");
		
		$("#dialogAddPi").find("#mfgfinisheddate").val("");
		
		$("#dialogAddPi").find("#mfgstarteddate").val("");
		$("#dialogAddPi").find("#errorMfgstarteddate").text("");
		$("#dialogAddPi").find("#mfgstarteddate").css("background-color", "white");
		
		$("#dialogAddPi").find("#destination").val("");
		
		$("#dialogAddPi").find("#factory").val("");
		
		$("#dialogAddPi").find("#customer1").val("");
		$("#dialogAddPi").find("#errorCustomer1").text("");
		$("#dialogAddPi").find("#customer1").css("background-color", "white");
		
		$("#dialogAddPi").find("#consignee").val("");
		
		$("#dialogAddPi").find("#status").val("");
		
		$("#dialogAddPi").find("#remark").val("");
		
		document.getElementById('btnFabricRemainAdd').style.visibility = 'visible';
	}
	
	function clearAfterCloseEditDialog(){
		$("#editPIdialog").find("#lotnumber").val("");
		$("#editPIdialog").find("#errorLotnumber").text("");
		$("#editPIdialog").find("#lotnumber").css("border-color", "");
		$("#editPIdialog").find("#lotnumber").css("background-color", "white");
		
		$("#editPIdialog").find("#pireceiveddate2").val("");
		
		$("#editPIdialog").find("#piestshipdate2").val("");
		
		$("#editPIdialog").find("#mfgstarteddate2").val("");
		
		$("#editPIdialog").find("#mfgfinisheddate2").val("");
		
		$("#editPIdialog").find("#destination").val("");
		
		$("#editPIdialog").find("#factory").val("");
		
		$("#editPIdialog").find("#customer1").val("");
		
		$("#editPIdialog").find("#consignee").val("");
		
		$("#editPIdialog").find("#status").val("");
		
		$("#editPIdialog").find("#remark").val("");
		
		document.getElementById('btnFabricRemainEdit').style.visibility = 'visible';
		
		$('#editPIdialog').find('#noneorderaccessories').prop('checked', false);
	}
	
	//load Dropdown list function
	function loadDropDown(){
    	//load Dropdown Destination
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/destination/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
						 $('#dialogAddPi').find('#country').append($('<option>', {
	                            value: value.description,
	                            text: value.description
	                        }));
						
//						$('#dialogAddPi').find('#destinationcode').append($('<option>', {
//                            value: value.destinationcode,
//                            text: value.destinationcode
//                        }));
                        
                        //load bên edit
                        $('#editPIdialog').find('#destinationcode').append($('<option>', {
                            value: value.destinationcode,
                            text: value.destinationcode
                        }));
                        
                      //load view RFPI
                        $('#viewRFPIdialog').find('#destinationcode').append($('<option>', {
                            value: value.destinationcode,
                            text: value.destinationcode
                        }));
                        
                        
                    });
				}else{
//					alert('This alert should never show!');
				}
			},
			error: function(){
//				alert('Error !!');
			}
    	});
		
		//load Dropdown Factory
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/factory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddPi').find('#factory').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        //load edit
                        $('#editPIdialog').find('#factory').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        //load view RFPI
                        $('#viewRFPIdialog').find('#factory').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                    });
				}else{
//					alert('This alert should never show!');
				}
			},
			error: function(){
//				alert('Error !!');
			}
    	});

		
		//load Shipment Status
		
		 $('#dialogAddPi').find('#shipmentstatus').append(
				 $('<option>', {
			            value: "No",
			            text: "No"
					 }),
				 $('<option>', {
		            value: "Yes",
		            text: "Yes"
				 })
        );
        
        //load edit
        $('#editPIdialog').find('#shipmentstatus').append(
        		$('<option>', {
        			value: "No",
  		            text: "No"
        		}),
        		$('<option>', {
        			value: "Yes",
 		            text: "Yes"
        		})
        		
        );
		
		
		//load packing guide dropdown box
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/packingguide/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddPi').find('#packingguidecode').append($('<option>', {
                            value: value.packingguidecode,
                            text: value.packingguidecode
                        }));
                        
                        //load edit
                        $('#editPIdialog').find('#packingguidecode').append($('<option>', {
                            value: value.packingguidecode,
                            text: value.packingguidecode
                        }));
                    });
				}else{
//					alert('This alert should never show!');
				}
			},
			error: function(){
//				alert('Error !!');
			}
    	});
		
		
		
		$("#dialogAddPi").find('#noneorderaccessories').change(function(){
		     if($(this).is(":checked")){
		          $(this).val(true);
		          
		     }else{
		          $(this).val(false);
		     }
		});	
		/**
		 * function when press add button
		 */
		$("#dialogAddPi").find("#btnAdd").click(function(e){
			if(!validateRequiredFieldForAdd()){
				callMessageDialog("Warning message", "Please enter required fields!");
				
				//get value of lot number and PI Est Recive date
				var lotnumber= $("#dialogAddPi").find("#lotnumber").val();
				var piestshipdate = $("#dialogAddPi").find("#piestshipdate").val();
				var customer1 = $("#dialogAddPi").find("#customer1").val();
				
				//if user dont enter code or enter just space
				if(lotnumber.trim() === '' || lotnumber == null){
					$("#dialogAddPi").find("#errorLotnumber").text("Please enter lot number!");
					$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
					$("#dialogAddPi").find("#lotnumber").css("background-color", "red");
				}
				else{
					$("#dialogAddPi").find("#errorLotnumber").text("");
					$("#dialogAddPi").find("#errorLotnumber").css("color", "white");
					$("#dialogAddPi").find("#lotnumber").css("background-color", "white");
				}
				if(customer1=="none"){
					$("#dialogAddPi").find("#errorCustomer1").text("Please select customer!");
					$("#dialogAddPi").find("#errorCustomer1").css("color", "red");
					$("#dialogAddPi").find("#customer1").css("background-color", "red");
				}
				else{
					$("#dialogAddPi").find("#errorCustomer1").text("");
					$("#dialogAddPi").find("#errorCustomer1").css("color", "white");
					$("#dialogAddPi").find("#customer1").css("background-color", "white");
				}
				if(piestshipdate.trim() === '' || piestshipdate == null){
					$("#dialogAddPi").find("#errorPiestshipdate").text("Please select Date!");
					$("#dialogAddPi").find("#errorPiestshipdate").css("color", "red");
					$("#dialogAddPi").find("#piestshipdate").css("background-color", "red");
				}
				else{
					$("#dialogAddPi").find("#errorPiestshipdate").text("");
					$("#dialogAddPi").find("#errorPiestshipdate").css("color", "white");
					$("#dialogAddPi").find("#piestshipdate").css("background-color", "white");
				}
				
				
				//if user dont enter name or enter just space
				return false;
			}
			if(!isValidationDateAdd()){
				callMessageDialog("Warning message", "Check Est Date and received date!");
				return false;
			}
			if(!isValidationDateAddMFG()){
				callMessageDialog("Warning message", "Check started date and finished date!");
				return false;
			}
			
		});
		
		
		/**
		 * function when press add button
		 */
		$("#editPIdialog").find("#btnAdd").click(function(e){
			if(!isValidationDateEdit()){
				callMessageDialog("Warning message", "Check Est Date and received date!");
				return false;
			}
			if(!isValidationDateEditMFG()){
				callMessageDialog("Warning message", "Check started date and finished date!");
				return false;
			}
			
		});
		/**
		 * prevent submit form when press Add new fabric infomation
		 */

		/**
		 * This function is used to validate required fields before adding
		 */
		function validateRequiredFieldForAdd(){
			var lotnumber= $("#dialogAddPi").find("#lotnumber").val();
			if(lotnumber.trim() === '' || lotnumber == null)
				return false;
			
			var piestshipdate= $("#dialogAddPi").find("#piestshipdate").val();
			if(piestshipdate.trim() === '' || piestshipdate == null)
				return false;
			
			var customer1 = $("#dialogAddPi").find("#customer1").val();
			if(customer1 == "none" || customer1 == null){
				return false;
			}
			var brandname = $("#dialogAddPi").find("#brandcode").val();
			if(brandname == "none" || brandname == null){
				return false;
			}
			var factory = $("#dialogAddPi").find("#factorycode").val();
			if(factory == "none" || factory == null){
				return false;
			}
			var country = $("#dialogAddPi").find("#country").val();
			if(country == "none" || country == null){
				return false;
			}
			return true;
		}
		
		/**
		 * This function validate user input if it's over range before add new pi
		 */
		function validateOverRangeWhenAdd(){
			if($("#dialogAddPi").find("#lotnumber").val().length>20 )
				return false;
			return true;
		}
		
		
//		$('#dialogAddPi').find('#status').append($('<option>', {
//            value: "normal",
//            text: Normal
//		}));
//		$('#dialogAddPi').find('#status').append($('<option>', {
//            value: "urgent",
//            text: "Urgent"
//		}));
		
		//load Dropdown Customer1 and consignee
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/customer/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddPi').find('#customer1').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                        
                        $('#dialogAddPi').find('#consignee').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));  
                        
                        //load bên edit
                        $('#editPIdialog').find('#customer1').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                        
                        $('#editPIdialog').find('#consignee').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                        
                        //load view RFPI
                        $('#viewRFPIdialog').find('#customer1').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                        
                        $('#viewRFPIdialog').find('#consignee').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                    });
				}else{
//					alert('This alert should never show!');
				}
			},
			error: function(){
//				alert('Error !!');
			}
    	});
	
		
		$("#fabricAssignmentDialog").find('#btnAddNewAssignment').on('click', function (e){});
		
//		jQuery('#btnFabricRemain').on('click', function(event) {        
//            jQuery$("#dialogAddPi").find("#content").toggle('show');
//       });
		
		var buttonFabricRemainAdd = document.getElementById('btnFabricRemainAdd'); // Assumes element with id='button'

		buttonFabricRemainAdd.onclick = function() {
		   // var div = document.getElementById('content');
		   
			var buttonFabricRemainAdd = $("#dialogAddPi").find("#btnFabricRemainAdd").val();
			
			if(buttonFabricRemainAdd == "Show"){
				var buttonFabricRemainAdd = $("#dialogAddPi").find("#btnFabricRemainAdd").val("Hide");
				$("#dialogAddPi").find("#tableViewFabricRemain").prop('hidden', false );
				$("#dialogAddPi").find("#listViewFabricRemain").prop('hidden', false );
				 //refresh table
				 $("#listViewFabricRemain").dataTable().fnDestroy();
				 $('#listViewFabricRemain tbody').empty();
				 $("#listViewFabricRemain").html("");
					$("#listViewFabricRemain").append('<table class="table table-striped table-bordered display responsive"'+
							'id="listViewFabricRemain">'+
							'<thead>'+
								'<tr>'+
									'<th>Fabric No</th>' +
									'<th>Color Code</th>' +
									'<th>Total remained</th>' +									
									'<th>Inventory remained</th>' +								
								'</tr>'+
							'</thead>'+
						'</table>');
					
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/pi/fabric/list",
						contentType: "application/json",
						success: function(data){
								var i=0					
								$.each(data.list,function(key,value){	
									$('<tr>').append(   								
											$('<td>').text(value.fabricno),
											$('<td>').text(value.colorcode),
											$('<td>').text(value.totalremained),
											$('<td>').text(value.inventoryremained)
		    										    									    								
		    						).appendTo('#listViewFabricRemain');
								});
								$('#listViewFabricRemain').DataTable( {
									"sPaginationType": "full_numbers",
									paging: true,
							        rowsGroup: [0,2,3]
								});
						},
						error: function(){
//							alert("Can not get data!");
						}
					});
			}
			else if (buttonFabricRemainAdd == "Hide"){
				var buttonFabricRemainAdd = $("#dialogAddPi").find("#btnFabricRemainAdd").val("Show");
				document.getElementById('btnFabricRemainAdd').style.visibility = 'hidden';
				$("#dialogAddPi").find("#tableViewFabricRemain").prop('hidden', true );
				$("#dialogAddPi").find("#listViewFabricRemain").prop('hidden', true );
			}
		};	
		
		var buttonFabricRemainEdit = document.getElementById('btnFabricRemainEdit'); // Assumes element with id='button'

		buttonFabricRemainEdit.onclick = function() {
		   // var div = document.getElementById('content');
		   
			var buttonFabricRemainEdit = $("#editPIdialog").find("#btnFabricRemainEdit").val();
			
			if(buttonFabricRemainEdit == "Show"){
				var buttonFabricRemainEdit = $("#editPIdialog").find("#btnFabricRemainEdit").val("Hide");
				$("#editPIdialog").find("#tableViewFabricRemainEdit").prop('hidden', false );
				
				
				 //refresh table
				 $("#listViewFabricRemainEdit").dataTable().fnDestroy();
				 $('#listViewFabricRemainEdit tbody').empty();
				 $("#listViewFabricRemainEdit").html("");
					$("#listViewFabricRemainEdit").append('<table class="table table-striped table-bordered display responsive"'+
							'id="listViewFabricRemainEdit">'+
							'<thead>'+
								'<tr>'+
									'<th>Fabric No</th>' +
									'<th>Color Code</th>' +
									'<th>Total remained</th>' +									
									'<th>Inventory remained</th>' +								
								'</tr>'+
							'</thead>'+
						'</table>');
					
					$.ajax({
						dataType: "json",
						type: 'GET',
						data: {},
						url: "/Chori/pi/fabric/list",
						contentType: "application/json",
						success: function(data){
								var i=0					
								$.each(data.list,function(key,value){	
									$('<tr>').append(   								
											$('<td>').text(value.fabricno),
											$('<td>').text(value.colorcode),
											$('<td>').text(value.totalremained),
											$('<td>').text(value.inventoryremained)
		    										    									    								
		    						).appendTo('#listViewFabricRemainEdit');
								});
								$('#listViewFabricRemainEdit').DataTable( {
									"sPaginationType": "full_numbers",
									paging: true,
							        rowsGroup: [0,2,3]
								});
						},
						error: function(){
//							alert("Can not get data!");
						}
					});
			}
			else if(buttonFabricRemainEdit == "Hide"){
				var buttonFabricRemainEdit = $("#editPIdialog").find("#btnFabricRemainEdit").val("Show");
				$("#editPIdialog").find("#listViewFabricRemainEdit").prop('hidden', true );
				document.getElementById('btnFabricRemainEdit').style.visibility = 'hidden';
				$("#listViewFabricRemainEdit").dataTable().fnDestroy();
				 $('#listViewFabricRemainEdit tbody').empty();
				 $("#listViewFabricRemainEdit").html("");
			}
		};		
			
			
//			var div = $("#dialogAddPi").find("#content");
//		    if (div.style.display !== 'none') {
//		        div.style.display = 'none';
//		    }
//		    else {
//		        div.style.display = 'block';
//		    }
		
		
		// Validate Css Required Add Form	
		
		$("#dialogAddPi").find("#customer1").on('change keyup paste',function(){
			var customer1 = $("#dialogAddPi").find("#customer1").val();
			
			if(customer1=="none"){
				$("#dialogAddPi").find("#errorCustomer1").text("Please Select A Customer!");
				$("#dialogAddPi").find("#errorCustomer1").css("color", "red");
				$(this).css("background-color", "red");
			}
			else{
				$("#dialogAddPi").find("#errorCustomer1").text("");
				$("#dialogAddPi").find("#errorCustomer1").css("color", "green");
				$(this).css("background-color", "white");
			}
		})
		
		$("#dialogAddPi").find("#brandcode").on('change keyup paste',function(){
			var brand = $("#dialogAddPi").find("#brandcode").val();
			
			if(brand=="none"){
				$("#dialogAddPi").find("#errorBrandcode").text("Please Select A Brand!");
				$("#dialogAddPi").find("#errorBrandcode").css("color", "red");
				$(this).css("background-color", "red");
			}
			else{
				$("#dialogAddPi").find("#errorBrandcode").text("");
				$("#dialogAddPi").find("#errorBrandcode").css("color", "green");
				$(this).css("background-color", "white");
			}
		})
		
		$("#dialogAddPi").find("#piestshipdate").on('change keyup paste',function(){
			var piestshipdate = $("#dialogAddPi").find("#piestshipdate").val();
			
			if(piestshipdate.trim() === '' || piestshipdate == null){
				$("#dialogAddPi").find("#errorPiestshipdate").text("Please Select A Date!");
				$("#dialogAddPi").find("#errorPiestshipdate").css("color", "red");
				$(this).css("background-color", "red");
			}
			else{
				$("#dialogAddPi").find("#errorPiestshipdate").text("");
				$("#dialogAddPi").find("#errorPiestshipdate").css("color", "green");
				$(this).css("background-color", "white");
			}
		})
		
//		$("#dialogAddPi").find("#lotnumber").on('change keyup paste',function(){
		$("#dialogAddPi").find("#lotnumber").keyup(function(){
			var lotnumber = $(this).val();
			
			//if user dont enter or enter just space
//			if(lotnumber.trim() === '' || lotnumber == null){
//				$("#dialogAddPi").find("#errorLotnumber").text("Please enter lot number!");
//				$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
//				$(this).css("background-color", "red");
//			}
//			//if input of user is over range
//			if($("#dialogAddPi").find("#lotnumber").val().length>20){
//				$("#dialogAddPi").find("#errorLotnumber").text("The code field's length is 20. Your input is over range!");
//				$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
//				$(this).css("background-color", "red");
//			}
			
			//check if lot number is existed
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:JSON.stringify({
					lotnumber: lotnumber
				}),
				contentType: "application/json",
				url: getAbsolutePath() + "pi/isExist/",
				success: function(data){
//					if(data.isExisted==true){// if code is existed, prevent user add new accessory
//						//if code is existed
//						$("#dialogAddPi").find("#errorLotnumber").text("Lot Number you enter is existed, please choose different one!");
//						$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
//						$("#dialogAddPi").find("#lotnumber").css("background-color", "red");
//						$("#dialogAddPi").find("#btnAdd").prop('disabled', true);
//					}else if(data.isExisted==false&&$("#dialogAddPi").find("#lotnumber").val().length<21){
//						//if code is not existed and valid length
//						$("#dialogAddPi").find("#errorLotnumber").text("Lot Number is valid");
//						$("#dialogAddPi").find("#errorLotnumber").css("color", "green");
//						$("#dialogAddPi").find("#lotnumber").css("background-color", "white");
//						$("#dialogAddPi").find("#btnAdd").prop('disabled', false);
//					}
					
					if(data.isExisted==false){
						if(lotnumber.length>50||lotnumber.length<1){
							$("#dialogAddPi").find("#errorLotnumber").text("Your input between 1 - 50 characters");
							$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
							$("#dialogAddPi").find("#lotnumber").css("border-color", "red");
							$("#dialogAddPi").find("#lotnumber").css("background-color", "white");
//							$('#btnAdd').prop( "disabled", true );
						}
						else if($.trim(lotnumber) !== lotnumber){
							$("#dialogAddPi").find("#errorLotnumber").text("Not allow to type the space as a first/last character");
							$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
							$("#dialogAddPi").find("#lotnumber").css("border-color", "red");
							$("#dialogAddPi").find("#lotnumber").css("background-color", "white");
//							$('#btnAdd').prop( "disabled", true );
						}else{
							$("#dialogAddPi").find("#errorLotnumber").text("Lot number is valid!");
							$("#dialogAddPi").find("#errorLotnumber").css("color", "green");
							$("#dialogAddPi").find("#lotnumber").css("border-color", "green");
							$("#dialogAddPi").find("#lotnumber").css("background-color", "white");
//							$('#btnAdd').prop( "disabled", false );
						}
					}else{
						$("#dialogAddPi").find("#errorLotnumber").text("Lot number has been created!");
						$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
						$("#dialogAddPi").find("#lotnumber").css("border-color", "red");
						$("#dialogAddPi").find("#lotnumber").css("background-color", "red");
//						$('#btnAdd').prop( "disabled", true );
					}
				},
				error: function(){
					//when user clear the code
//					$("#dialogAddPi").find("#errorLotnumber").text("Please enter Lot Number !");
//					$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
//					$(this).css("background-color", "red");
					$("#dialogAddPi").find("#errorLotnumber").text("Please enter lot number!");
					$("#dialogAddPi").find("#errorLotnumber").css("color", "red");
					$("#dialogAddPi").find("#txtWidthCode").css("background-color", "red");
//					$('#btnAdd').prop( "disabled", true );
				}
			});
		});

		
		
		//load factory dropdown box
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/factory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#dialogAddPi').find('#factorycode').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        //load bên edit dialog
                        $('#editPIdialog').find('#factorycode').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        //load view RFPI
                        $('#viewRFPIdialog').find('#factorycode').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                    });
				}else{
//					alert('This alert should never show!');
				}
			},
			error: function(){
//				alert('Error !!');
			}
    	});
		
    }
	
	
	

	//function load brandname with customer 1 (Add dialog)
	$("#dialogAddPi").find("#customer1").on('change', function(){}).click(function(){
		var currentCustomer1 = $("#dialogAddPi").find("#customer1").val();
		var drpBrandname = $("#dialogAddPi").find("#brandcode");
		drpBrandname.empty();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/brand/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data. xxx");
				}
				$.each(data.list,function(key,value){
					if(value.customerCode == currentCustomer1){
						drpBrandname.append($('<option>',
								{
									value : value.brandcode,
									text : value.brandname
								}));
					}
				});
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
	})
	
	//function load destination with country (Add dialog)
	$("#dialogAddPi").find("#country").on('change', function(){}).click(function(){
		var currentCountry = $("#dialogAddPi").find("#country").val();
		var drpDestination = $("#dialogAddPi").find("#destinationcode");
		drpDestination.empty();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/destination/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data. xxx");
				}
				$.each(data.list,function(key,value){
					if(value.description == currentCountry){
						drpDestination.append($('<option>',
								{
									value : value.destinationcode,
									text : value.destinationcode
								}));
					}
				});
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
	})
	
	//function load destination with country (Edit dialog)
	$("#editPIdialog").find("#country").on('change', function(){}).click(function(){
		var currentCountry = $("#editPIdialog").find("#country").val();
		var drpDestination = $("#editPIdialog").find("#destinationcode");
		drpDestination.empty();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/destination/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data. country ( edit dialog )");
				}
				$.each(data.list,function(key,value){
					if(value.description == currentCountry){
						drpDestination.append($('<option>',
								{
									value : value.destinationcode,
									text : value.destinationcode
								}));
					}
				});
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
	})

	//function load destination with country (view RFPI dialog)
	$("#viewRFPIdialog").find("#country").on('change', function(){}).click(function(){
		var currentCountry = $("#viewRFPIdialog").find("#country").val();
		var drpDestination = $("#viewRFPIdialog").find("#destinationcode");
		drpDestination.empty();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/destination/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data. xxx");
				}
				$.each(data.list,function(key,value){
					if(value.description == currentCountry){
						drpDestination.append($('<option>',
								{
									value : value.destinationcode,
									text : value.destinationcode
								}));
					}
				});
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
	})
	
	//function load brandname with customer 1 (Edit dialog)
	$("#editPIdialog").find("#customer1").on('change', function(){}).click(function(){
		//$("#editPIdialog").find("#btnAdd").attr("disabled",false);
		var currentCustomer1 = $("#editPIdialog").find("#customer1").val();
		var drpBrandname = $("#editPIdialog").find("#brandcode");
		drpBrandname.empty();
		var flag = 1;
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/brand/list",
			contentType: "application/json",
			success: function(data){
				if(data.list.length==0){
//					alert("Table have no data. xxx");
				}
				$.each(data.list,function(key,value){
					if(value.customerCode == currentCustomer1){
						
						drpBrandname.append($('<option>',
								{
									value : value.brandcode,
									text : value.brandname
								}));
					}
				});
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
	})
	
	//loadDatepicker 
	
	$(function() {
		//add form
		$("#dialogAddPi").find("#piestshipdate").datepicker({
	        changeMonth: true,
	        dateFormat : 'dd/mm/yy',
	        numberOfMonths: 1
		});
		$("#pireceiveddate" ).datepicker({
	        changeMonth: true,
	        dateFormat : 'dd/mm/yy',
	        numberOfMonths: 1
		});
		$("#mfgstarteddate" ).datepicker({
	        changeMonth: true,
	        dateFormat : 'dd/mm/yy',
	        numberOfMonths: 1
		});
		$("#mfgfinisheddate" ).datepicker({
	        changeMonth: true,
	        dateFormat : 'dd/mm/yy',
	        numberOfMonths: 1
		});
		
		// Edit form	
		
	 	$("#editPIdialog").find("#pireceiveddateEdit").datepicker({
	    	defaultDate: "+1w",
	    	changeMonth: true,
	        dateFormat : 'dd/mm/yy',
	        numberOfMonths: 1
	      });
			
	     $("#editPIdialog").find("#piestshipdateEdit").datepicker({
	    	defaultDate: "+1w",
	    	changeMonth: true,
	        dateFormat : 'dd/mm/yy',
	        numberOfMonths: 1
	      });
	     
		 $("#editPIdialog").find("#mfgstarteddateEdit").datepicker({
			  defaultDate: "+1w", 
			  changeMonth: true,
		      dateFormat : 'dd/mm/yy',
		      numberOfMonths: 1
		  });
				
		  $("#editPIdialog").find("#mfgfinisheddateEdit").datepicker({
			   defaultDate: "+1w",
			   changeMonth: true,
		       dateFormat : 'dd/mm/yy',
		       numberOfMonths: 1
		    });
	});

	     
	     
	     
//	}
	
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
					$("#messageDialog").dialog("close");
				}
			}
		});
	}
	/**
	 * -- Hàm kiểm tra ngày khi add --
	 */
	function isValidationDateAdd() {
		var piestshipdate = $("#dialogAddPi").find("#piestshipdate").datepicker('getDate');
		var pireceiveddate = $("#dialogAddPi").find("#pireceiveddate").datepicker('getDate');
		if(Date.parse(piestshipdate) > Date.parse(pireceiveddate))
			return false;
		return true;
	}
	function isValidationDateAddMFG() {
		var mfgstarteddate = $("#dialogAddPi").find("#mfgstarteddate").datepicker('getDate');
		var mfgfinisheddate = $("#dialogAddPi").find("#mfgfinisheddate").datepicker('getDate');
		if(Date.parse(mfgstarteddate) > Date.parse(mfgfinisheddate))
			return false;
		return true;
	}
	
	/**
	 * -- Hàm kiểm tra ngày khi edit --
	 */
	function isValidationDateEdit() {
		var piestshipdateEdit = $("#editPIdialog").find("#piestshipdateEdit").datepicker('getDate');
		var pireceiveddateEdit = $("#editPIdialog").find("#pireceiveddateEdit").datepicker('getDate');
		if(Date.parse(piestshipdateEdit) > Date.parse(pireceiveddateEdit))
			return false;
		return true;
	}
	function isValidationDateEditMFG() {
		var mfgstarteddateEdit = $("#editPIdialog").find("#mfgstarteddateEdit").datepicker('getDate');
		var mfgfinisheddateEdit = $("#editPIdialog").find("#mfgfinisheddateEdit").datepicker('getDate');
		if(Date.parse(mfgstarteddateEdit) > Date.parse(mfgfinisheddateEdit))
			return false;
		return true;
	}

	function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	/**
	 * -- Hàm check status sau khi add và edit --
	 */
	function checkIfAddSuccessfully(){
		//message for add part
		if(getUrlParameter('addResultStatus')==='Success'){
			//callMessageDialog("Message", "Add new successfully!");
			$("#messageDialog").find("#messageContent").text("Add new successfully!");
			$("#messageDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Message",
				height: 200,
				width: 400,
				hide: {
					effect: "explode",
					duration: 500
				},
				buttons:{
					"OK": function(){
						window.location.replace(getAbsolutePath()+"listpi");
						$(this).dialog("close");
						reloadTableWithStatus();
					}
				}
			});
		}
		if(getUrlParameter('addResultStatus')==='Failed'){
			//callMessageDialog("Warning Message", "Add new failed!");
			$("#messageDialog").find("#messageContent").text("Add new failed!");
			$("#messageDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Message",
				height: 200,
				width: 400,
				hide: {
					effect: "explode",
					duration: 500
				},
				buttons:{
					"OK": function(){
						window.location.replace(getAbsolutePath()+"listpi");
						$(this).dialog("close");
					}
				}
			});
		}
		//message for edit part
		if(getUrlParameter('editResultStatus')==='Success'){
			//callMessageDialog("Message", "Edit successfully!");
			$("#messageDialog").find("#messageContent").text("Edit successfully!");
			$("#messageDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Message",
				height: 200,
				width: 400,
				hide: {
					effect: "explode",
					duration: 500
				},
				buttons:{
					"OK": function(){
						window.location.replace(getAbsolutePath()+"listpi");
						$(this).dialog("close");
//						reloadTableWithStatus();
					}
				}
			});
		}
		if(getUrlParameter('editResultStatus')==='Failed'){
			//callMessageDialog("Warning Message", "Edit failed!");
			$("#messageDialog").find("#messageContent").text("Edit failed!");
			$("#messageDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Message",
				height: 200,
				width: 400,
				hide: {
					effect: "explode",
					duration: 500
				},
				buttons:{
					"OK": function(){
						window.location.replace(getAbsolutePath()+"listpi");
						$(this).dialog("close");
					}
				}
			});
		}
	};

	/**
	 * ----------------------------------------------------------------------------------------
	 * 
	 */
	/**
	 * Assign External Accessory Part
	 */
	$('#btnShowAssignExAccDialog').on('click',function(e){
		//do ở trong form
		e.preventDefault();
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
//		var lotNumber = $("#txtLotNumber").val();
		var lotNumber = $("#editPIdialog").find("#lotnumber").val();
		//lấy giá trị gán cho dialog assign
		$("#assignExternalAccessoryForPiDialog").find("#txtLOTNo").val(lotNumber);
		
		/**
		 * gọi ajax xem đã đc assign external chưa?
		 * chưa thì mới add mới, còn r thì edit
		 */
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				lotnumber: lotNumber
			}),
			contentType: "application/json",
			url: "/Chori/assignExternalAccessory/isAssigned",
			success: function(data){
				//chưa assign thì mới add mới
				if(data.isAssigned == false){
					
					///gọi ajax lấy list
					$.ajax({
						dataType: "json",
						type: 'POST',
						data: JSON.stringify({
							lotnumber: lotNumber
						}),
						url: "/Chori/listAssignExternalAccessory1stTime",
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
										$('<td>').html('<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" checked /> '+value.colorName+", "+value.garmentStyleDisplayName+", "+value.sizename)
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
//							alert("Can not get data!");
						}
					});
					///end
					
				}else if(data.isAssigned == true){//này là edit
					
//					alert("load khi edit");
					///gọi ajax lấy list
					$.ajax({
						dataType: "json",
						type: 'POST',
						data: JSON.stringify({
							lotnumber: lotNumber,										
						}),
						url: "/Chori/listPiassignexternalaccessoryForEditByLotNumber",
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
										$('<td>').html((value.estimateqty>0?'<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" checked /> ':'<input type="checkbox" name="lstExAccAssign" value="'+value.piassignexternalaccessorycode+'" /> ')+value.colorName+", "+value.garmentStyleDisplayName+", "+value.sizename)
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
//							alert("Can not get data!");
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
			title: "Accessory Checklist",
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
						type: 'POST',
						data: JSON.stringify({
							lotnumber: lotNumber
						}),
						contentType: "application/json",
						url: "/Chori/assignExternalAccessory/isAssigned",
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
												showListPiAssignExternalAcc(lotNumber);
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
												showListPiAssignExternalAcc(lotNumber);
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
			width: 400,
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
	
	//hàm lấy view assign external accessory
	function showListPiAssignExternalAcc(lotNumber){
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
//		var lotNumber = $("#txtLotNumber").val();
		//đổi
//		var lotNumber = $("#editPIdialog").find("#lotnumber").val();
		
		///gọi ajax lấy list
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				lotnumber: lotNumber,										
			}),
			url: "/Chori/listPiassignexternalaccessoryByLotNumber",
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
							$('<td>').text(value.colorName+", "+value.garmentStyleDisplayName+", "+value.sizename),
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
							$('<td>').html('<select class="selectpicker selectOptionAssignExternalVer" data-id="'+value.accessoryCode+'" data-piexternalcode"'+value.piassignexternalaccessorycode+'" data-lotnumber="'+lotNumber+'" data-factorycode="'+value.factorycode+'" >'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="EditWastedPercentage">Edit Wasted Percentage</option>'
									+'<option value="OrderExternalAccessory">Order External Accessory</option></select>')
					).appendTo('#listAssignExternalAccessoryDetail');
					
					preAccessoryName = value.accessoryName;
				});
				actionForListAssignExternal();
				
				$('#listAssignExternalAccessoryDetail').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5,18]
			    } );
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
		///end
	}
	//end hàm lấy view assign external accessory
	
	//hàm lấy view assign external accessory fpi version
	function showListPiAssignExternalAccFpiVer(lotNumber){
		//xóa tạo lại table
		$("#AssignExternalAccessoryDetailTableCoverFpiVer").html('');
		$("#AssignExternalAccessoryDetailTableCoverFpiVer").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listAssignExternalAccessoryDetailFpiVer">'+
					'<thead>'+
						'<tr>'+
							'<th>No</th>'+
							'<th>Supplier</th>'+
							'<th>Factory Name</th>'+
							'<th>Accessory Name</th>'+
							'<th>Accessory For</th>'+
							'<th>Image</th>'+
							'<th>Order Sheet No/ Status</th>'+
							'<th>Assign For</th>'+
							'<th>Fa Est. quantity pcs</th>'+
							'<th>Fpi Est. quantity pcs</th>'+
							'<th>Fa - Fpi est. quantity pcs</th>'+
							'<th>Est. quantity gross</th>'+
							'<th>Unit pcs</th>'+
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
//		var lotNumber = $("#txtLotNumber").val();
		//đổi
//		var lotNumber = $("#editPIdialog").find("#lotnumber").val();
		
		///gọi ajax lấy list
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				lotnumber: lotNumber,										
			}),
			url: "/Chori/listPiassignexternalaccessoryByLotNumber",
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
							$('<td>').html(value.accessoryName+"<br/>"+value.totalEstimateFpiByAccessory),
							$('<td>').text(value.mode),
							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl+'" data-id="choriAccessoryImage/'+value.imgurl+'" /></a>')),
							$('<td>').text(value.orderSheetNoAndStatus==null?'':value.orderSheetNoAndStatus),//
							$('<td>').text(value.colorName+", "+value.garmentStyleDisplayName+", "+value.sizename),
							$('<td>').text(value.estimateqty),
							$('<td>').text(value.estimateFpiQty),
							$('<td>').text(value.estimateqty-value.estimateFpiQty),
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
							$('<td>').html('<select class="selectpicker selectOptionAssignExternalVer" data-id="'+value.accessoryCode+'" data-piexternalcode"'+value.piassignexternalaccessorycode+'" data-lotnumber="'+lotNumber+'" data-factorycode="'+value.factorycode+'" >'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="EditWastedPercentage">Edit Wasted Percentage</option>'
									+'<option value="OrderExternalAccessory">Order External Accessory</option></select>')
					).appendTo('#listAssignExternalAccessoryDetailFpiVer');
					
					preAccessoryName = value.accessoryName;
				});
				actionForListAssignExternal();
				
				$('#listAssignExternalAccessoryDetailFpiVer').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5,18]
			    } );
			},
			error: function(){
//				alert("Can not get data!");
			}
		});
		///end
	}
	//end hàm lấy view assign external accessory fpi version
	
	//hàm lấy view assign external accessory rfpi version
	function showListPiAssignExternalAccRfpiVer(lotNumber){
		//xóa tạo lại table
		$("#AssignExternalAccessoryDetailTableCoverRfpiVer").html('');
		$("#AssignExternalAccessoryDetailTableCoverRfpiVer").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listAssignExternalAccessoryDetailRfpiVer">'+
					'<thead>'+
						'<tr>'+
							'<th>No</th>'+
							'<th>Supplier</th>'+
							'<th>Factory Name</th>'+
							'<th>Accessory Name</th>'+
							'<th>Accessory For</th>'+
							'<th>Image</th>'+
							'<th>Order Sheet No/ Status</th>'+
							'<th>Assign For</th>'+
							'<th>Fa Est. quantity pcs</th>'+
							'<th>Fpi Est. quantity pcs</th>'+
							'<th>Rfpi Est. quantity pcs</th>'+
							'<th>Fpi - Rfpi est. quantity pcs</th>'+
							'<th>Est. quantity gross</th>'+
							'<th>Unit pcs</th>'+
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
//		var lotNumber = $("#txtLotNumber").val();
		//đổi
//		var lotNumber = $("#editPIdialog").find("#lotnumber").val();
		
		///gọi ajax lấy list
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				lotnumber: lotNumber,										
			}),
			url: "/Chori/listPiassignexternalaccessoryByLotNumber",
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
							$('<td>').html(value.accessoryName+"<br/>"+value.totalEstimateRfpiByAccessory),
							$('<td>').text(value.mode),
							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl+'" data-id="choriAccessoryImage/'+value.imgurl+'" /></a>')),
							$('<td>').text(value.orderSheetNoAndStatus==null?'':value.orderSheetNoAndStatus),//
							$('<td>').text(value.colorName+", "+value.garmentStyleDisplayName+", "+value.sizename),
							$('<td>').text(value.estimateqty),
							$('<td>').text(value.estimateFpiQty),
							$('<td>').text(value.estimateRfpiQty),
							$('<td>').text(value.estimateFpiQty-value.estimateRfpiQty),
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
							$('<td>').html('<select class="selectpicker selectOptionAssignExternalVer" data-id="'+value.accessoryCode+'" data-piexternalcode"'+value.piassignexternalaccessorycode+'" data-lotnumber="'+lotNumber+'" data-factorycode="'+value.factorycode+'" >'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="EditWastedPercentage">Edit Wasted Percentage</option>'
									+'<option value="OrderExternalAccessory">Order External Accessory</option></select>')
					).appendTo('#listAssignExternalAccessoryDetailRfpiVer');
					
					preAccessoryName = value.accessoryName;
				});
				actionForListAssignExternal();
				
				$('#listAssignExternalAccessoryDetailRfpiVer').DataTable( {
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
	//end hàm lấy view assign external accessory rfpi version
	
	function actionForListAssignExternal(){	
		$('.selectOptionAssignExternalVer').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var accessoryCode = $(this).data('id');//this is RoleID of each record.
		    var lotnumber = $(this).data('lotnumber');
	    	var factoryCode = $(this).data('factorycode');
	    	
//	    	alert(accessoryCode+" "+lotnumber+" "+factoryCode);
	    	
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOptionAssignExternalVer").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="EditWastedPercentage"){
		    	//gán giá trị cho lot no, factory
		    	$("#editWastedPercentageDialog").find("#lblLotNo").text($("#editPIdialog").find("#lotnumber").val());
		    	$("#editWastedPercentageDialog").find("#lblFactory").text($("#editPIdialog").find("#factorycode option:selected").text());
		    	$("#editWastedPercentageDialog").find("#txtFactory").val($("#editPIdialog").find("#factorycode").val());
//		    	var lotnumber = $("#editPIdialog").find("#lotnumber").val();
//		    	var factoryCode = $("#editPIdialog").find("#factorycode").val();
		    	
		    	//xóa tạo lại table
				$("#ExternalAccessoryDetailByLotNoAndAccCodeTableCover").html('');
				$("#ExternalAccessoryDetailByLotNoAndAccCodeTableCover").append('<table class="table table-striped table-bordered display responsive"'+
							'id="listExternalAccessoryDetailByLotNoAndAccCode">'+
							'<thead>'+
								'<tr>'+
									'<th>Accessory Name</th>'+
									'<th>Mode</th>'+
									'<th>Color</th>'+
									'<th>Garment Style</th>'+
									'<th>Type</th>'+
									'<th>Size</th>'+
									'<th>Supplier</th>'+
									'<th>Est. quantity pcs</th>'+
								'</tr>'+
							'</thead>'+
						'</table>');
				//end xóa tạo lại table
				
				//gọi ajax
				$.ajax({
					dataType: "json",
					type: 'POST',
					data: JSON.stringify({
						lotnumber: lotnumber,
						factorycode: factoryCode,
						accessoryCode: accessoryCode
					}),
					url: "/Chori/getAccessoryWastedPercentageAndListPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode",
					contentType: "application/json",
					success: function(data){
						//load comsumptation vô text box trc
						$("#editWastedPercentageDialog").find("#txtWastedPercentage").val(data.wastedPercentage);
						
						$.each(data.listPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode,function(key,value){
							$('<tr>').append(
									$('<td>').text(value.accessoryName),
									$('<td>').text(value.mode),
									$('<td>').text(value.colorName),
									$('<td>').text(value.garmentStyleDisplayName),
									$('<td>').text(value.typecode),
									$('<td>').text(value.sizename),
									$('<td>').text(value.accessorySupplierShortname),
									$('<td>').text(value.estimateqty)
							).appendTo('#listExternalAccessoryDetailByLotNoAndAccCode');
							
						});
						
						$('#listExternalAccessoryDetailByLotNoAndAccCode').DataTable( {
							"sPaginationType": "full_numbers",
							paging: true,
					        rowsGroup: [0,1,6]
					    } );
						
						/**
						 * 
						 */
						//hiển thị dialog
				    	$("#editWastedPercentageDialog").dialog({
							modal: true,
				    		show:{
								effect:"blind",
								duration: 500
							},
							title: "Update Wasted Percentage",
							height: 800,
							width: 1280,
							hide: {
								effect: "explode",
								duration: 500
							},
							buttons:{
								"Save": function(){
									//kiểm tra xem người dùng có để trống textbox wasted ko?
									var wastedPercentage= $("#editWastedPercentageDialog").find("#txtWastedPercentage").val();
									if(wastedPercentage.trim() === '' || wastedPercentage == null){
										callMessageDialog("Warning Message", "Please enter Wasted Percentage befor save!");
									}else if(wastedPercentage<0){
										callMessageDialog("Warning Message", "Please enter Wasted Percentage greater or equal 0!");
									}else{
//										callMessageDialog("Message", "OK!");
										//call ajax to edit
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify({
												specificconsumption: wastedPercentage,
												lotnumber: lotnumber,
												accessoryCode: accessoryCode
											}),
											url: "/Chori/assignExternalAccessory/editWastedPercentage",
											contentType: "application/json",
											success: function(data){
												//load comsumptation vô text box trc
//												$("#editWastedPercentageDialog").find("#txtWastedPercentage").val(data.wastedPercentage);
//												
//												$.each(data.listPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode,function(key,value){
//													$('<tr>').append(
//															$('<td>').text(value.accessoryName),
//															$('<td>').text(value.mode),
//															$('<td>').text(value.colorName),
//															$('<td>').text(value.garmentstylecode),
//															$('<td>').text(value.typecode),
//															$('<td>').text(value.sizename),
//															$('<td>').text(value.accessorySupplierShortname),
//															$('<td>').text(value.estimateqty)
//													).appendTo('#listExternalAccessoryDetailByLotNoAndAccCode');
//													
//												});
//												
//												$('#listExternalAccessoryDetailByLotNoAndAccCode').DataTable( {
//													"sPaginationType": "full_numbers",
//													paging: true,
//											        rowsGroup: [0,1,6]
//											    } );
//												callMessageDialog("Message", "Edit Wasted Percentage successfully!");
												
												$("#messageDialog").find("#messageContent").text("Edit Wasted Percentage successfully!");
												$("#messageDialog").dialog({
													modal: true,
													show:{
														effect:"blind",
														duration: 500
													},
													title: "Message",
													height: 200,
													width: 400,
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
												
											},
											error: function(){
//												alert("Can not get data!");
//												callMessageDialog("Warning Message", "Edit Wasted Percentage failed!");
												
												$("#messageDialog").find("#messageContent").text("Edit Wasted Percentage failed!");
												$("#messageDialog").dialog({
													modal: true,
													show:{
														effect:"blind",
														duration: 500
													},
													title: "Warning Message",
													height: 200,
													width: 400,
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
												
											}
										});
										//end call ajax to edit
									}
								},
								"Cancel": function(){
									$("#editWastedPercentageDialog").dialog("close");
								}
							},
							close: function(){
							}
						});
				    	//end hiển thị dialog
						/**
						 * 
						 */
					},
					error: function(){
						$("#messageDialog").find("#messageContent").text("Please config Wasted Percentage for accessory \""+accessoryCode+"\" of factory \""+factoryCode+"\" before do this function!");
						$("#messageDialog").dialog({
							modal: true,
							show:{
								effect:"blind",
								duration: 500
							},
							title: "Warning Message",
							height: 200,
							width: 400,
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
					}
				});
				//end gọi ajax
		    	
//		    	//hiển thị dialog
//		    	$("#editWastedPercentageDialog").dialog({
//					modal: true,
//		    		show:{
//						effect:"blind",
//						duration: 500
//					},
//					title: "Update Wasted Percentage",
//					height: 800,
//					width: 1280,
//					hide: {
//						effect: "explode",
//						duration: 500
//					},
//					buttons:{
//						"Save": function(){
//							//kiểm tra xem người dùng có để trống textbox wasted ko?
//							var wastedPercentage= $("#editWastedPercentageDialog").find("#txtWastedPercentage").val();
//							if(wastedPercentage.trim() === '' || wastedPercentage == null){
//								callMessageDialog("Warning Message", "Please enter Wasted Percentage befor save!");
//							}else if(wastedPercentage<0){
//								callMessageDialog("Warning Message", "Please enter Wasted Percentage greater or equal 0!");
//							}else{
////								callMessageDialog("Message", "OK!");
//								//call ajax to edit
//								$.ajax({
//									dataType: "json",
//									type: 'POST',
//									data: JSON.stringify({
//										specificconsumption: wastedPercentage
//										}),
//									url: "/Chori/assignExternalAccessory/editWastedPercentage/"+lotnumber+"/"+accessoryCode,
//									contentType: "application/json",
//									success: function(data){
//										//load comsumptation vô text box trc
////										$("#editWastedPercentageDialog").find("#txtWastedPercentage").val(data.wastedPercentage);
////										
////										$.each(data.listPiassignexternalaccessoryByLotNumberFactoryCodeAndAccessoryCode,function(key,value){
////											$('<tr>').append(
////													$('<td>').text(value.accessoryName),
////													$('<td>').text(value.mode),
////													$('<td>').text(value.colorName),
////													$('<td>').text(value.garmentstylecode),
////													$('<td>').text(value.typecode),
////													$('<td>').text(value.sizename),
////													$('<td>').text(value.accessorySupplierShortname),
////													$('<td>').text(value.estimateqty)
////											).appendTo('#listExternalAccessoryDetailByLotNoAndAccCode');
////											
////										});
////										
////										$('#listExternalAccessoryDetailByLotNoAndAccCode').DataTable( {
////											"sPaginationType": "full_numbers",
////											paging: true,
////									        rowsGroup: [0,1,6]
////									    } );
//										callMessageDialog("Message", "Edit Wasted Percentage successfully!");
//									},
//									error: function(){
////										alert("Can not get data!");
//										callMessageDialog("Warning Message", "Edit Wasted Percentage failed!");
//									}
//								});
//								//end call ajax to edit
//							}
//						},
//						"Cancel": function(){
//							$("#editWastedPercentageDialog").dialog("close");
//						}
//					},
//					close: function(){
//					}
//				});
//		    	//end hiển thị dialog
		    };
		    if(valueSelected=="OrderExternalAccessory"){
		    	var lotNumber = $("#editPIdialog").find("#lotnumber").val();
		    	var factoryCode = $("#editPIdialog").find("#factorycode").val();

		    	var piAssignExtCode=$(this).data('piexternalcode');	
		    	function showOrderextaccByStock(){
		    		$.ajax({
		        		dataType: "json",
		    			type: 'GET',
		    			data:{},
		    			contentType: "application/json",
		    			url: "/Chori/externalaccessorystock/isExist/" + accessoryCode,
		    			success: function(data){
		    				if(data.isExisted){
		    					//$('#dialogShowStock2').css('display', 'initial');
		    					
		    					$("#dialogShowStock2").dialog({
		    						show:{
		    							effect:"blind",
		    							duration: 500
		    						},
		    						height: 250,
		    						width: 350,
		    						title: "Order Accessories",
		    						modal :true,
		    						buttons:{
		    							"Cancel": function(){
		    								$("#dialogShowStock2").dialog("close");
		    							}
		    						}
		    					});
		    					
		                          
		    				}
		    				else{
		    					//$('#dialogShowStock1').css('display', 'initial');
		    					$("#dialogShowStock1").dialog({
		    						show:{
		    							effect:"blind",
		    							duration: 500
		    						},
		    						height: 250,
		    						width: 350,
		    						title: "Order Accessories",
		    						modal :true,
		    						buttons:{
		    							"Cancel": function(){
		    								$("#dialogShowStock1").dialog("close");
		    							}
		    						}
		    					});
		    				}
		    				
		    				
		    				$("#txtLotNumber").val(lotNumber);
		    				$("#txtAccessoryCode").val(accessoryCode);
		    			},
		    			error: function(){
		    				alert('error (2779)');
		    			}
		        	});
		    	}

		    		$("#dialogShowStock2").find("#btnNew").on('click', function (e){
		    			var lot=$("#txtLotNumber").val();
		    			var acc=$("#txtAccessoryCode").val();				
		    			$("#dialogOrderExtAccc").dialog({
		    				show:{
		    					effect:"blind",
		    					duration: 500
		    				},
		    				height: 600,
		    				width: 1366,
		    				title: "Order Accessories",
		    				modal :true,
		    			});
		    			 $.ajax({
		    		    		dataType: "json",
		    					type: 'GET',
		    					data:{},
		    					contentType: "application/json",
		    					url: "/Chori/externalaccessorytock/getEstimateQuantity/"+lotNumber+"/"+accessoryCode,
		    					success: function(data){
		    						$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val(data);
		    						},
		    					
		    					error: function(){
		    						alert('error (2808)');
		    					}
		    		    	});
		    		
		    			 $.ajax({
		    		    		dataType: "json",
		    					type: 'GET',
		    					data:{},
		    					contentType: "application/json",
		    					url: "/Chori/externalaccessorytock/getActualAssignQuantity/"+lotNumber+"/"+accessoryCode,
		    					success: function(data){
		    						$("#dialogOrderExtAccc").find("#txtActualAssignQuantity").val(data);
		    						},
		    					
		    					error: function(){
		    						alert('error (2823)');
		    					}
		    		    	});	
		    			 
		    			 	$.ajax({
		    		    		dataType: "json",
		    					type: 'GET',
		    					data:{},
		    					contentType: "application/json",
		    					url: "/Chori/externalaccessorystock/getShortageQuantity/"+lotNumber+"/"+accessoryCode,
		    					success: function(data){
		    						$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
		    						},
		    					
		    					error: function(){
		    						alert('error (2838)');
		    					}
		    		    	});	
		    			 	
		    				$("#dialogOrderExtAccc").find('#btnSendMail').on('click', function (e) {	
		    					$("#sendMailDialog").dialog({
		    						modal: true,
		    						show:{
		    							effect:"blind",
		    							duration: 500
		    						},
		    						title: "Send Mail",
		    						height: 600,
		    						width: 400
		    					});
		    				});
		    				$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
		    						$("#sendMailDialog").dialog("close");
		    				});
		    				$("#dialogOrderExtAccc").find('#btnCancel').on('click', function (e) {	
		    					$("#dialogOrderExtAccc").dialog("close");
		    				});
		    				$("#dialogOrderExtAccc").find('#btnSave').on('click', function (e) {	
		    					var accessorysuplier=$("#txtAccessorysuplier").val();
		    					var factory=$("#txtFactory").val();
		    					//var lotnumber="P001";
		    					var ordersheetno=$("#txtOrdersheetno").val();
		    					var accessory="BTNR";
		    					var estimatequantity=$("#txtEstimateQuantity").val();
		    					var orderqty = $("#txtOrderQuantity").val();
		    					var estimategrossquantity=$("#txtEstimateGrossQuantity").val();
		    					var actualdeliveriedgrossquantity=$("#txtActualDeliveriedGrossQuantity").val();
		    					var Ordate = $("#txtOrderDate").datepicker('getDate');
		    					var orderdate = ($.datepicker.formatDate('yy-mm-dd', Ordate));
		    					if(Ordate==null || Ordate =='' )  {
		    						var orderdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    						}
		    					var Estdate = $("#txtEstDeliveryDate").datepicker('getDate');
		    					var estdate = ($.datepicker.formatDate('yy-mm-dd', Estdate));
		    					if(Estdate==null || Estdate =='' )  {
		    						var estdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    						}
		    					var Actdate = $("#txtActualDeliveryDate").datepicker('getDate');
		    					var actdate = ($.datepicker.formatDate('yy-mm-dd', Actdate));
		    					var payment=$("#txtPaymentStatus").val();
		    					if(Actdate==null || Actdate =='' )  {
		    					var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    					}
		    						var remark=$("#txtRemark").val();
		    						var status=$("#txtStatus").val();
		    						var price=$("#txtPrice").val();
		    					
		    						if(orderqty>0)
		    						{
		    							/**ajax call method save from orderexternalaccessorycontroller **/ 
		    							$.ajax({
		    					    		dataType: "json",
		    								type: 'POST',
		    								data:
		    									JSON.stringify({
		    										orderSheetNo:ordersheetno,
		    										// lotNumber:lotnumber,
		    										 accsuplierCode:accessorysuplier,
		    										 factoryCode:factory,
		    										 accessoryCode:accessory,
		    										 //creator:,
		    										 orderQuantity:orderqty,
		    										 actualdevlQuantity:actualdeliveriedgrossquantity,
		    										 orderDate:orderdate,
		    										 price:price,
		    										 estimatedevlDate:estdate,
		    										 actualdevlDate:actdate,
		    										 status:status,
		    										 remark:remark,
		    										 paymentStatus:payment,
		    										 lotNumber: lotNumber,
		    										 //createDate,
		    									}),
		    								contentType: "application/json",
		    								url: "/Chori/orderexternalaccessory/save",
		    								success: function(data){
		    									$.ajax({
		    										dataType: "json",
		    										type: 'GET',
		    										data: {},
		    										url: "/Chori/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/"+accessoryCode + "/" +lotNumber + "/" +orderqty + "/" + ordersheetno,
		    										contentType: "application/json",
		    										success: function(data){	
		    											callMessageDialog("Message", "Save external accessory successfully!");
		    											$("#dialogOrderExtAccc").dialog("close");
		    										
		    										},
		    										error: function(){
		    											//alert("Không lấy đc dữ liệu!");
		    										}
		    									});
		    								},
		    								error: function(){
		    									callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
		    									
		    								}
		    					    	});				
		    						}
		    						else{
		    							alert("Order Quantity must be greater than 0!");
		    						}
		    		    	});
		    		});
		    		
		    		

		    			
		    			
//		    			$("#dialogShowStock2").find("#btnAssign").on('click',function (e){
		    //
//		    				$("#assignForm").dialog({
//		    					show:{
//		    						effect:"blind",
//		    						duration: 500
//		    					},
//		    					height: 600,
//		    					width: 1366,
//		    				});
//		    				$.ajax({
//		    		    		dataType: "json",
//		    					type: 'GET',
//		    					data:{},
//		    					contentType: "application/json",
//		    					url: "/Chori/accessory/detail/"+accessoryCode,
//		    					success: function(data){
//		    						$("#assignForm").find("#lblAccessoryName").text(data.accessory.name);
//		    						$("#assignForm").find("#lblUnit").text(data.accessory.unitcode);
		    //
//		    						},
//		    					
//		    					error: function(){
//		    						alert('error(140)');
//		    					}
//		    		    	});
//		    				$.ajax({
//		    		    		dataType: "json",
//		    					type: 'GET',
//		    					data:{},
//		    					contentType: "application/json",
//		    					url: "/Chori/externalaccessorytock/getInventoryQuantity/"+accessoryCode,
//		    					success: function(data){
//		    						$("#assignForm").find("#lblInventoryquantity").text(data);
//		    						
		    //
//		    						},
//		    					
//		    					error: function(){
//		    						alert('error(155)');
//		    					}
//		    		    	});		
//		    			});
		    			
		    		$("#dialogShowStock1").find("#btnNew").on('click', function (e){
		    			var lot=$("#txtLotNumber").val();
		    			var acc=$("#txtAccessoryCode").val();			
		    			$("#dialogOrderExtAccc").dialog({
		    				show:{
		    					effect:"blind",
		    					duration: 500
		    				},
		    				height: 600,
		    				width: 1366,
		    				title: "Order Accessories",
		    				modal :true,
		    			});
		    			
		    			 $.ajax({
		    	    		dataType: "json",
		    				type: 'GET',
		    				data:{},
		    				contentType: "application/json",
		    				url: "/Chori/externalaccessorytock/getEstimateQuantity/"+lotNumber+"/"+accessoryCode,
		    				success: function(data){
		    					$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val(data);
		    					},
		    				
		    				error: function(){
		    					alert('error (3020)');
		    				}
		    		    });
		    				
		    			 $.ajax({
		    	    		dataType: "json",
		    				type: 'GET',
		    				data:{},
		    				contentType: "application/json",
		    				url: "/Chori/externalaccessorytock/getActualAssignQuantity/"+lotNumber+"/"+accessoryCode,
		    				success: function(data){
		    					$("#dialogOrderExtAccc").find("#txtActualAssignQuantity").val(data);
		    					$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
		    					},
		    				
		    				error: function(){
		    					alert('error (3036)');
		    				}
		    		    });
		    			
		    		 	$.ajax({
		    	    		dataType: "json",
		    				type: 'GET',
		    				data:{},
		    				contentType: "application/json",
		    				url: "/Chori/externalaccessorystock/getShortageQuantity/"+lotNumber+"/"+accessoryCode,
		    				success: function(data){
		    					$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
		    					},
		    				
		    				error: function(){
		    					alert('error (3051)');
		    				}
		    	    	});	
		    		 	
		    			$("#dialogOrderExtAccc").find('#btnSendMail').on('click', function (e) {	
		    				$("#sendMailDialog").dialog({
		    					modal: true,
		    					show:{
		    						effect:"blind",
		    						duration: 500
		    					},
		    					title: "Send Mail",
		    					height: 600,
		    					width: 400
		    				});
		    			});
		    			$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
		    				$("#sendMailDialog").dialog("close");
		    			});
		    			
		    			$("#dialogOrderExtAccc").find('#btnCancel').on('click', function (e) {	
		    				$("#dialogOrderExtAccc").dialog("close");
		    			});
		    			
		    			$("#dialogOrderExtAccc").find('#btnSave').on('click', function (e) {	
		    				var accessorysuplier=$("#dialogOrderExtAccc").find("#txtAccessorysuplier").val();
		    				var factory=$("#dialogOrderExtAccc").find("#txtFactory").val();
		    				//var lotnumber="P001";
		    				var ordersheetno=$("#dialogOrderExtAccc").find("#txtOrdersheetno").val();
		    			
		    				var estimatequantity=$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val();
		    				var orderqty = $("#dialogOrderExtAccc").find("#txtOrderQuantity").val();
		    				var estimategrossquantity=$("#dialogOrderExtAccc").find("#txtEstimateGrossQuantity").val();
		    				var actualdeliveriedgrossquantity=$("#dialogOrderExtAccc").find("#txtActualDeliveriedGrossQuantity").val();
		    				var Ordate = $("#dialogOrderExtAccc").find("#txtOrderDate").datepicker('getDate');
		    				var orderdate = ($.datepicker.formatDate('yy-mm-dd', Ordate));
		    				if(Ordate==null || Ordate =='' )  {
		    					var orderdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    					}
		    				var Estdate = $("#dialogOrderExtAccc").find("#txtEstDeliveryDate").datepicker('getDate');
		    				var estdate = ($.datepicker.formatDate('yy-mm-dd', Estdate));
		    				if(Estdate==null || Estdate =='' )  {
		    					var estdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    					}
		    				var Actdate = $("#dialogOrderExtAccc").find("#txtActualDeliveryDate").datepicker('getDate');
		    				var actdate = ($.datepicker.formatDate('yy-mm-dd', Actdate));
		    				var payment=$("#dialogOrderExtAccc").find("#txtPaymentStatus").val();
		    				if(Actdate==null || Actdate =='' )  {
		    					var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    					}
		    				var remark=$("#dialogOrderExtAccc").find("#txtRemark").val();
		    				var status=$("#dialogOrderExtAccc").find("#txtStatus").val()==null?"Not Ordered":$("#dialogOrderExtAccc").find("#txtStatus").val();
		    				var price=$("#dialogOrderExtAccc").find("#txtPrice").val();
		    					alert(status);
		    				if(orderqty>0)
		    				{
		    					/**ajax call method save from orderexternalaccessorycontroller **/ 
		    					$.ajax({
		    			    		dataType: "json",
		    						type: 'POST',
		    						data:
		    							JSON.stringify({
		    								orderSheetNo:ordersheetno,
		    								// lotNumber:lotnumber,
		    								 accsuplierCode:accessorysuplier,
		    								 factoryCode:factory,
		    								 accessoryCode:accessory,
		    								 //creator:,
		    								 orderQuantity:orderqty,
		    								 actualdevlQuantity:actualdeliveriedgrossquantity,
		    								 orderDate:orderdate,
		    								 price:price,
		    								 estimatedevlDate:estdate,
		    								 actualdevlDate:actdate,
		    								 status:status,
		    								 remark:remark,
		    								 paymentStatus:payment,
		    								 lotNumber: lotNumber,
		    								 //createDate,
		    							}),
		    						contentType: "application/json",
		    						url: "/Chori/orderexternalaccessory/save",
		    						success: function(data){
		    							$.ajax({
		    								dataType: "json",
		    								type: 'GET',
		    								data: {},
		    								url: "/Chori/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/"+accessoryCode + "/" +lotNumber + "/" +orderqty + "/" + ordersheetno,
		    								contentType: "application/json",
		    								success: function(data){	
		    									callMessageDialog("Message", "Save external accessory successfully!");
		    									$("#dialogOrderExtAccc").dialog("close");
		    								
		    								},
		    								error: function(){
		    									//alert("Không lấy đc dữ liệu!");
		    								}
		    							});
		    						},
		    						error: function(){
		    							callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
		    							
		    						}
		    			    	});				
		    				}
		    				else{
		    					alert("Order Quantity must be greater than 0!");
		    				}
		    		    });			
		    		});		
		    	
		    	 function loadDropDownSuplier(){
		    		 /**ajax to call method get list accessorysuplier * **/
		        	$.ajax({
		        		dataType: "json",
		    			type: 'GET',
		    			data:{},
		    			contentType: "application/json",
		    			url: "/Chori/accessorysupplier/list",
		    			success: function(data){
		    				if(data.status == "ok"){
		    					$.each( data.list, function( key, value ) {
		                            $('#txtAccessorysuplier').append($('<option>', {
		                                value: value.accessorysuppliercode,
		                                text: value.shortname
		                            }));
		                            
		                            
		                        });
		    				}else{
		    					alert('This alert should never show!');
		    				}
		    			},
		    			error: function(){
		    				alert('error (3185)');
		    			}
		        	});
		        }
		    	/** function to show price and currency with param are accessorysuplier,accessorycode and orderdate  * **/
		    	 function loadPriceandCurency(){
		    		$("#txtAccessorysuplier")
		    		.on('change', function() {
		    			var accessoryCode= "BTNR";
		    			var accessorySuplier=$("#txtAccessorysuplier").val();
		    			var date = $("#txtOrderDate").datepicker('getDate');
		    			var parseDate = ($.datepicker.formatDate('yy-mm-dd', date));
		    			

		    			if(date==null || date =='' )  {
		    				var parseDate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    				}
		    			/** ajax call method get in orderexternalaccessoryController
		    			 * **/
		    			 	$.ajax({
		    		    		dataType: "json",
		    					type: 'GET',
		    					data:{},
		    					contentType: "application/json",
		    					url:"/Chori/orderexternalaccessory/get/"+accessoryCode+"/"+accessorySuplier+"/"+parseDate,
		    					success: function(data){
		    						if (data.status == "ok") {
		    							$.each(data.list,function(key,value){
		    								$("#lblCurrency").text(value.currencycode);
		    								$("#txtPrice").val(value.unitpriceperunit);
		    							

		    							});
		    								
		    				
		    						}
		    					},
		    					
		    					error: function(){
//		    						alert('error (3224)'+accessoryCode +" "+accessorySuplier+" "+parseDate);
		    					}
		    		    	});
		    			
		    		});
		    		/**
		    		 * when orderdate change 
		    		 * **/
		    		$("#txtOrderDate")
		    		.on('change', function() {
		    			var accessorySuplier=$("#txtAccessorysuplier").val();
		    		//	alert("ha" + accessorySuplier);
		    			var date = $("#txtOrderDate").datepicker('getDate');
		    			var parseDate = ($.datepicker.formatDate('yy-mm-dd', date));
		    			if(date==null || date =='' )  {
		    				var parseDate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    				}
		    			/**
		    			 * ajax to call method get from orderexternalaccessory controller 
		    			 * **/

		    			 	$.ajax({
		    		    		dataType: "json",
		    					type: 'GET',
		    					data:{},
		    					contentType: "application/json",
		    					url:"/Chori/orderexternalaccessory/get/"+accessoryCode+"/"+accessorySuplier+"/"+parseDate,
		    					success: function(data){
		    						if (data.status == "ok") {
		    							$.each(data.list,function(key,value){
		    								$("#lblCurrency").text(value.currencycode);
		    								$("#txtPrice").val(value.unitpriceperunit);

		    							});
		    						}
		    					},
		    					
		    					error: function(){
		    						alert('error (3264)');
		    					}
		    		    	});
		    			
		    		});	
		    	 }
		    	 /**
		    	  * function to show all factory in drop down box
		    	  * **/
		    	 function loadDropDownFactory(){
		    		 /** ajax call method list from factorycontroller
		    		  * **/
		    	    	$.ajax({
		    	    		
		    	    		dataType: "json",
		    				type: 'GET',
		    				data:{},
		    				contentType: "application/json",
		    				url: "/Chori/factory/list",
		    				success: function(data){
		    					if(data.status == "ok"){
		    						$.each( data.list, function( key, value ) {
		    	                        $('#dialogOrderExtAccc').find('#txtFactory').append($('<option>', {
		    	                            value: value.factorycode,
		    	                            text: value.shortname
		    	                        }));
		    	                        
		    	                        
		    	                    });
		    					}else{
		    						alert('This alert should never show!');
		    					}
		    				},
		    				error: function(){
		    					alert('error (3298)');
		    				}
		    	    	});
		    	    }
		    	 /**function to show accessoryname
		    	  * **/
		    	 function loadAccessoryName(){
		    			/**ajax call method detail from accessorycontroller
		    			 * **/
		    		 	$.ajax({
		    	    		dataType: "json",
		    				type: 'GET',
		    				data:{},
		    				contentType: "application/json",
		    				url: "/Chori/accessory/detail/"+accessoryCode,
		    				success: function(data){
		    					$("#txtAccessoryName").val(data.accessory.name);
		    					
		    					},
		    				
		    				error: function(){
		    					alert('error(3319)');
		    				}
		    	    	});
		    	    
		    	    }
		    	 
		    	
		    //function to genarate order sheet no 
		    	 function makeid(){
		    		
		    		 var count=1;
		    		
		    		 $.ajax({	    		
		        		dataType: "json",
		    			type: 'GET',
		    			data:{},
		    			contentType: "application/json",
		    			url: "/Chori/orderexternalaccessory/list",
		    			success: function(data){
		    				if(data.status == "ok"){
		    					$.each( data.list, function( key, value ) {					
		    						
		    							if(value.orderSheetNo.substring(5) >= count)
		    							{
		    								count = parseInt(value.orderSheetNo.substring(5)) +1;
		    							}							
		    						
		                        });
		    					//alert(count);
		    					var id=lotNumber+"_"+count;
//		    					alert(id);
		    					 $("#dialogOrderExtAccc").find("#txtOrdersheetno").val(id);
		    				}else{
		    					alert('This alert should never show!');
		    				}
		    			},
		    			error: function(){
		    				alert('error (3355)');
		    			}
		        	});
		    		
		    		 
		    	 }
		    	
		    //function show dialog to display alert message
		    		function callMessageDialog(title, messageContent){
		    			$("#messageDialog").find("#messageContent").text(messageContent);
		    			$("#messageDialog").dialog({
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
		    //function to add datetimepicker for date field
		    	 $( function() {
		    		    $( "#txtOrderDate" ).datepicker();
		    		    $( "#txtEstDeliveryDate" ).datepicker();
		    		    $( "#txtActualDeliveryDate" ).datepicker();
		    		  } );
		    	 //function to load factory name by Lotnumer from pi
		    	 function loadloadfactorybypi(){
		    		 var lotnumber = $("#editPiDialog").find("#lotnumber").val();
		    		 /** ajax call method detail from from picontroller **/
		    		 $.ajax({
		    	    		dataType: "json",
		    				type: 'POST',
		    				data:JSON.stringify({
		    					lotnumber: lotnumber
		    				}),
		    				contentType: "application/json",
		    				url: getAbsolutePath() +  "pi/detail",
		    				success: function(data){
		    					$("#txtFactory").val(data.currentpi.factorycode);
		    					},
		    				
		    				error: function(){
		    					alert('error (3406)');
		    				}
		    	    	});
		    	    }
		    	 /** function to show detail of accessory **/
		    	 function showAccessoryDetail(){
		    			/** ajax call method detail from accessorycontroller**/
		    			$.ajax({
		    				dataType: "json",
		    				type: 'GET',
		    				data: {},
		    				url: "/Chori/accessory/detail/"+accessoryCode,
		    				contentType: "application/json",
		    				success: function(data){
		    					var i = 1;							
		    						$('<tr>').append(
		    								$('<td>').text(i),
		    								$('<td>').text(data.accessory.accessorycode),
		    								$('<td>').text(data.accessory.name),
		    								$('<td>').text(data.accessory.kind),
		    								$('<td>').text(data.accessory.dimension),
		    								$('<td>').text(data.accessory.mode),
		    								$('<td>').text(data.accessory.unitcode),
		    								$('<td>').text(data.accessory.colorcode),
		    								$('<td>').html(((data.accessory.imgurl1==null)||(data.accessory.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+data.accessory.imgurl1+'" data-id="choriAccessoryImage/'+data.accessory.imgurl1+'" /></a>')+((data.accessory.imgurl2==null)||(data.accessory.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'
		    										+data.accessory.imgurl2+'" data-id="choriAccessoryImage/'+data.accessory.imgurl2+'" /></a>'))
		    				
		    						).appendTo('#listAccessory');
		    					
		    					//action();

		    					
		    				},
		    				error: function(){
		    					//alert("Không lấy đc dữ liệu!");
		    				}
		    			});
		    		};
		    			
		    		
		    		
		    		
		    		/**
		    		 * Allow input numeric only in order form
		    		 */
		    		$("#dialogOrderExtAccc").on('keydown','#txtEstimateQuantity',
		    				function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		    			||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		    			||35<=e.keyCode&&40>=e.keyCode
		    			||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		    			&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
		    		
		    		
		    		$("#dialogOrderExtAccc").on('keydown','#txtPrice',
		    				function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		    			||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		    			||35<=e.keyCode&&40>=e.keyCode
		    			||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		    			&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()}); 
		    		
		    		$("#dialogOrderExtAccc").on('keydown','#txtActualDeliveriedGrossQuantity',
		    				function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
		    			||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
		    			||35<=e.keyCode&&40>=e.keyCode
		    			||(e.shiftKey||48>e.keyCode||57<e.keyCode)
		    			&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
		    		
		    		
		    		
		    	//action();
		    	showOrderextaccByStock();
		    	 showAccessoryDetail();
		    	 loadDropDownSuplier();
		    	 makeid();
		    	 loadAccessoryName();
		    	 loadDropDownFactory();
		    	 loadloadfactorybypi();		    	
		    	 loadPriceandCurency();
		    	 
		    	 
		    	 $("#dialogShowStock2").find("#btnAssign").on('click',function (e){	 
		    		$.ajax({
		    			dataType: "json",
		    			type: 'GET',
		    			data: {},
		    			url: "/Chori/externalaccessorytock/getStockAvailableQuantityInformation/"+accessoryCode + "/" +factoryCode+ "/" +lotNumber,
		    			contentType: "application/json",
		    			success: function(data){	
		    				$("#dialogAssignFromStock").find("#lblAssignFromStockLotNumber").text(data.accessoryname);
		    				$("#dialogAssignFromStock").find("#lblAssignFromStockActualAssignQty").text(data.actualassignqty);
		    				$("#dialogAssignFromStock").find("#lblAssignFromStockShortageQty").text(data.shortageqty);
		    				$("#dialogAssignFromStock").find("#lblAssignFromStockAvailableStockQty").text(data.availableqtyfromstock);
		    				$("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").val(data.stockassignqty);
		    			},
		    			error: function(){
		    				//alert("Không lấy đc dữ liệu!");
		    			}
		    		});
		    			
//		    			//auto calculate stock available quantity when change stock assign quantity
//		    			$("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").on('input propertychange paste', function (e) {	
//		    				var stockAvailableQty = parseFloat($("#dialogAssignFromStock").find("#lblAssignFromStockAvailableStockQty").text());	
//		    				var stockAssignQty = parseFloat($("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").val());
//		    				var sum = stockAvailableQty + stockAssignQty
//		    				if(stockAssignQty>=0 && stockAssignQty<stockAvailableQty){
//		    				$("#dialogAssignFromStock").find("#lblAssignFromStockAvailableStockQty").text(sum);}
//		    			});
		    			
		    		//show edit dialog
		    		$("#dialogAssignFromStock").dialog({
		    			show:{
		    				effect:"blind",
		    				duration: 500
		    			},
		    			title: "Assign From Stock",
		    			height: 400,
		    			width: 500,
		    			modal :true,
		    			buttons:{
		    				"Save": function(){		
		    					var assignQty = $("#dialogAssignFromStock").find("#txtAssignFromStockAssignQty").val();
		    		
		    					//ajax to add availableqty in stock when edit
		    					$.ajax({
		    						dataType: "json",
		    						type: 'GET',
		    						data: {},
		    						url: "/Chori/externalaccessorytock/updateStockAvailableQtyWhenEdit/"+accessoryCode + "/" +lotNumber,
		    						contentType: "application/json",
		    						success: function(data){	
		    							//ajax to save assignqtyfromstock
		    							$.ajax({
		    								dataType: "json",
		    								type: 'GET',
		    								data: {},
		    								url: "/Chori/externalaccessorytock/saveAssignFromStock/"+accessoryCode + "/" +factoryCode + "/" +assignQty,
		    								contentType: "application/json",
		    								success: function(data){	
		    									var stockAssignQty = 0;
		    									//set stockAssignQty
		    									data==0?stockAssignQty=assignQty:stockAssignQty=assignQty-data;
		    									//ajax to update StockAssignQty field of an assign 
		    									$.ajax({
		    										dataType: "json",
		    										type: 'GET',
		    										data: {},
		    										url: "/Chori/externalaccessorytock/updateStockAssignQtyOfAnAssign/"+accessoryCode + "/" +lotNumber + "/" +stockAssignQty,
		    										contentType: "application/json",
		    										success: function(data){	
		    											//check shortageQty, if it > 0,then show dialog make new order
		    											$.ajax({
		    												dataType: "json",
		    												type: 'GET',
		    												data: {},
		    												url: "/Chori/externalaccessorytock/getStockAvailableQuantityInformation/"+accessoryCode + "/" +factoryCode+ "/" +lotNumber,
		    												contentType: "application/json",
		    												success: function(data){	
		    													var shortageQty = data.shortageqty;
		    													if(shortageQty >0)
		    													{
		    														 $("#dialogMessageNotEnoughQtyFromStock").find("#lblAssignFromStockShortageQty").text(data.shortageqty);														
		    														//show message suggest make new order dialog
		    														$("#dialogMessageNotEnoughQtyFromStock").dialog({
		    															show:{
		    																effect:"blind",
		    																duration: 500
		    															},
		    															title: "Assign From Stock",
		    															height: 350,
		    															width: 500,
		    															modal :true,
		    															buttons:{
		    																"Yes": function(){		
		    																	//show make new order dialog
		    																	$("#dialogOrderExtAccc").dialog({
		    																		show:{
		    																			effect:"blind",
		    																			duration: 500
		    																		},
		    																		height: 600,
		    																		width: 1366,
		    																		title: "Order Accessories",
		    																		modal :true,
		    																	});
		    																	
		    																	$("#txtOrderQuantity").val(shortageQty);
		    		
		    																	//set estimate qty for make new order 
		    																	 $.ajax({
		    																    		dataType: "json",
		    																			type: 'GET',
		    																			data:{},
		    																			contentType: "application/json",
		    																			url: "/Chori/externalaccessorytock/getEstimateQuantity/"+lotNumber+"/"+accessoryCode,
		    																			success: function(data){
		    																				$("#dialogOrderExtAccc").find("#txtEstimateQuantity").val(data);
		    																				},
		    																			
		    																			error: function(){
		    																				alert('error (3606)');
		    																			}
		    																    });
		    																	 
		    																	 $.ajax({
		    																    		dataType: "json",
		    																			type: 'GET',
		    																			data:{},
		    																			contentType: "application/json",
		    																			url: "/Chori/externalaccessorytock/getActualAssignQuantity/"+lotNumber+"/"+accessoryCode,
		    																			success: function(data){
		    																				$("#dialogOrderExtAccc").find("#txtActualAssignQuantity").val(data);
		    																				},
		    																			
		    																			error: function(){
		    																				alert('error (3621)');
		    																			}
		    																    });
		    																	 
		    																 	$.ajax({
		    															    		dataType: "json",
		    																		type: 'GET',
		    																		data:{},
		    																		contentType: "application/json",
		    																		url: "/Chori/externalaccessorystock/getShortageQuantity/"+lotNumber+"/"+accessoryCode,
		    																		success: function(data){
		    																			$("#dialogOrderExtAccc").find("#txtOrderQuantity").val(data);
		    																			},
		    																		
		    																		error: function(){
		    																			alert('error (54)');
		    																		}
		    															    	});	
		    																 	
		    																	$("#dialogOrderExtAccc").find('#btnSendMail').on('click', function (e) {	
		    																		$("#sendMailDialog").dialog({
		    																			modal: true,
		    																			show:{
		    																				effect:"blind",
		    																				duration: 500
		    																			},
		    																			title: "Send Mail",
		    																			height: 600,
		    																			width: 400
		    																		});
		    																	});
		    																	
		    																	$("#sendMailDialog").find('#btnCancelSendMail').on('click', function (e) {	
		    																		$("#sendMailDialog").dialog("close");
		    																	});
		    																	
		    																	$("#dialogOrderExtAccc").find('#btnCancel').on('click', function (e) {	
		    																		$("#dialogOrderExtAccc").dialog("close");
		    																	});
		    																	
		    																	$("#dialogOrderExtAccc").find('#btnSave').on('click', function (e) {	
		    																		var accessorysuplier=$("#txtAccessorysuplier").val();
		    																		var factory=$("#txtFactory").val();
		    																		//var lotnumber="P001";
		    																		var ordersheetno=$("#txtOrdersheetno").val();
		    																		var accessory="BTNR";
		    																		var estimatequantity=$("#txtEstimateQuantity").val();
		    																		var orderqty = $("#txtOrderQuantity").val();
		    																		var estimategrossquantity=$("#txtEstimateGrossQuantity").val();
		    																		var actualdeliveriedgrossquantity=$("#txtActualDeliveriedGrossQuantity").val();
		    																		var Ordate = $("#txtOrderDate").datepicker('getDate');
		    																		var orderdate = ($.datepicker.formatDate('yy-mm-dd', Ordate));
		    																		if(Ordate==null || Ordate =='' )  {
		    																			var orderdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    																			}
		    																		var Estdate = $("#txtEstDeliveryDate").datepicker('getDate');
		    																		var estdate = ($.datepicker.formatDate('yy-mm-dd', Estdate));
		    																		if(Estdate==null || Estdate =='' )  {
		    																			var estdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    																		}
		    																		var Actdate = $("#txtActualDeliveryDate").datepicker('getDate');
		    																		var actdate = ($.datepicker.formatDate('yy-mm-dd', Actdate));
		    																		var payment=$("#txtPaymentStatus").val();
		    																		if(Actdate==null || Actdate =='' )  {
		    																			var actdate = ($.datepicker.formatDate('yy-mm-dd', new Date()));
		    																		}
		    																		
		    																		var remark=$("#txtRemark").val();
		    																		var status=$("#txtStatus").val();
		    																		var price=$("#txtPrice").val();
		    																		if(orderqty>0)
		    																		{
		    																			/**ajax call method save from orderexternalaccessorycontroller **/ 
		    																			$.ajax({
		    																	    		dataType: "json",
		    																				type: 'POST',
		    																				data:
		    																					JSON.stringify({
		    																						orderSheetNo:ordersheetno,
		    																						// lotNumber:lotnumber,
		    																						 accsuplierCode:accessorysuplier,
		    																						 factoryCode:factory,
		    																						 accessoryCode:accessory,
		    																						 //creator:,
		    																						 orderQuantity:orderqty,
		    																						 actualdevlQuantity:actualdeliveriedgrossquantity,
		    																						 orderDate:orderdate,
		    																						 price:price,
		    																						 estimatedevlDate:estdate,
		    																						 actualdevlDate:actdate,
		    																						 status:status,
		    																						 remark:remark,
		    																						 paymentStatus:payment,
		    																						 lotNumber: lotNumber,
		    																						 //createDate,
		    																					}),
		    																				contentType: "application/json",
		    																				url: "/Chori/orderexternalaccessory/save",
		    																				success: function(data){
		    																					$.ajax({
		    																						dataType: "json",
		    																						type: 'GET',
		    																						data: {},
		    																						url: "/Chori/orderexternalaccessory/updateOrderQtyOfAPiAssignExternalAccessory/"+accessoryCode + "/" +lotNumber + "/" +orderqty + "/" + ordersheetno,
		    																						contentType: "application/json",
		    																						success: function(data){	
		    																							callMessageDialog("Message", "Save external accessory successfully!");
		    																							$("#dialogOrderExtAccc").dialog("close");
		    																						
		    																						},
		    																						error: function(){
		    																							//alert("Không lấy đc dữ liệu!");
		    																						}
		    																					});
		    																				},
		    																				error: function(){
		    																					callMessageDialog("Message", "Edit external accessory  unsuccessfully!");
		    																					$("#dialogOrderExtAccc").dialog("close");
		    																				}
		    																	    	});				
		    																		}
		    																		else{
		    																			alert("Order Quantity must be greater than 0!");
		    																		}
		    																	});	
		    																},
		    																"Cancel": function(){
		    																	
		    																	$("#dialogMessageNotEnoughQtyFromStock").dialog("close");
		    																}
		    															},
		    															close: function(){
		    															}
		    														});
		    													}															
		    												},
		    												error: function(){
		    													//alert("Không lấy đc dữ liệu!");
		    												}
		    											});
		    										},
		    										error: function(){
		    										}
		    									});
		    									
		    		
		    								},
		    								error: function(){
		    									//alert("Không lấy đc dữ liệu!");
		    								}
		    							});
		    						},
		    						error: function(){
		    						}
		    					});
		    					
		    		
		    				},
		    				"Cancel": function(){
		    					
		    					$("#dialogAssignFromStock").dialog("close");
		    				}
		    			},
		    			close: function(){
		    			}
		    		});
		    	 });
		    	
		    };
		    
		});
	};
	/**
	 * End Assign External Accessory Part
	 */

	loadDropDown();
//	loadData();
	checkIfAddSuccessfully();
//	loadBrandName();
	$(".fancybox").fancybox();
	
});