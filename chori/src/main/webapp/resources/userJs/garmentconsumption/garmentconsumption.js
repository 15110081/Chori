 //$(document).ready(function(){
//	/**
//	 * This function is used to load data into table
//	 */
//	function loadData(){
//		$.ajax({
//			dataType: "json",
//			type: 'GET',
//			data: {},
//			url: "/Chori/garmentconsumption/list",
//			contentType: "application/json",
//			success: function(data){
//				$("#listGarmentConsumption").dataTable().fnDestroy();
//				$('#listGarmentConsumption tbody').empty();
//				var i = 1;
//				if(data.list.length==0){
//					callMessageDialog("Message", 'Table have no data');
//				}
//				
//				$.each(data.list,function(key,value){
//					var tmp='';
//					if(value.garmentconsumptiondetails.length>0){
//						tmp+='<table border="0">';
//						$.each(value.garmentconsumptiondetails,function(key,value1){
//							tmp+='<tr><td>'+value1.width+'</td><td>'+value1.convalue+'</td></tr>';
//						});
//						tmp+='</table>';
//					}
//					
//					$('<tr>').append(
//							$('<td>').text(i++),
//							$('<td>').text(value.customer),
//							$('<td>').text(value.garmentstyle),
//							$('<td>').text(value.description),
//							$('<td>').text(value.kind),
//							$('<td>').text(value.sizename),
//							$('<td>').text(value.type),
//							$('<td>').html(value.garmentconsumptiondetails.length==0?'':tmp),
//							$('<td>').html('<button class="btn btn-info btnEdit" data-id="'+value.garmentconsumptioncode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.garmentconsumptioncode+'">Delete</button>')
//					).appendTo('#listGarmentConsumption');
//				});
//				//console.log(data);
//				action();
//
//				$('#listGarmentConsumption').DataTable( {
//					"pagingType": "full_numbers"
//			    } );
//			},
//			error: function(){
//				callMessageDialog("Message", 'Can not get data');
//			}
//		});
//	};
//	
//	//Load Customer Options
//	function loadCustomerForEdit()
//	{
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/customer/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#editGarmentConsumptionDialog").find("#sltCustomer").append($('<option>', {       	
//                        value: value.customercode,
//                        text: value.shortname
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//	};
//	
//	//Load GarmentStyle Options
//	function loadGarmentStyleForEdit()
//	{
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/garmentstyle/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#editGarmentConsumptionDialog").find("#sltGarmentStyle").append($('<option>', {       	
//                        value: value.garmentstylecode,
//                        text: value.garmentstylecode
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//	};
//	
//	//Load Size Options
//	function loadSizeForEdit()
//	{		
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/size/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#editGarmentConsumptionDialog").find("#sltSize").append($('<option>', {       	
//                        value: value.sizecode,
//                        text: value.sizename
//                    }));
//
//                });
//            },
//            error: function(){
//            	
//            }
//		});
//	};
//	loadCustomerForEdit();
//	loadGarmentStyleForEdit();
//	loadSizeForEdit();
//	function action(){
//		//button edit garment consumption
//		$('.btnEdit').click(function(){
//			loadCustomerForEdit();
//			loadGarmentStyleForEdit();
//			loadSizeForEdit();
//			//to clear tblGarmentConsumptionDetailList
//			$('#tblGarmentConsumptionDetailList > tbody').html("");
//			
//			//to get id of garconCode (selected)
//			var garconCode= $(this).data('id');
//			
//			//get data by id and set to dialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: "/Chori/garmentconsumption/detail/"+garconCode,
//				contentType: "application/json",
//				success: function(data){
//					//set garmentconsumption detail to dialog
//					var sltCustomerValue = data.garmentconsumption.customer;
//					$("#editGarmentConsumptionDialog").find("#sltCustomer option").each(function() { this.selected = (this.text == sltCustomerValue); });
//					$("#editGarmentConsumptionDialog").find('#sltCustomer').prop( "disabled", true );				
//					var sltGarmentStyleValue = data.garmentconsumption.garmentstyle;
//					$("#editGarmentConsumptionDialog").find("#sltGarmentStyle option").each(function() { this.selected = (this.text == sltGarmentStyleValue); });
//					$("#editGarmentConsumptionDialog").find('#sltGarmentStyle').prop( "disabled", true );
//					$('#editGarmentConsumptionDialog').find('#txtDescription').val(data.garmentconsumption.description);
//					$('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(data.garmentconsumption.garmentconsumptioncode);
//					var sltSizeValue = data.garmentconsumption.sizename;
//					$("#editGarmentConsumptionDialog").find("#sltSize option").each(function() { this.selected = (this.text == sltSizeValue); });
//					$("#editGarmentConsumptionDialog").find('#sltSize').prop( "disabled", true );
//					$('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(data.garmentconsumption.garmentconsumptioncode),
//					$.each(data.garmentconsumption.garmentconsumptiondetails,function(key,value){
//						
//						$("#tblGarmentConsumptionDetailList tbody").append('<tr data-id="'+value.garmentconsumptiondetailcode+'"><td class="gcWidth"><select><option value='+value.width+' selected>'+value.width+'</option></select></td><td class="gcConValue">'+value.convalue+'</td><td><button class="btn btn-danger btnDeleteGC">Delete</button><button class="btn btn-info btnEditGC">Edit</button></td></tr>');	
//						//$("#tblGarmentConsumptionDetailList tbody").append('<tr data-id="'+value.garmentconsumptiondetailcode+'"><td class="gcWidth">'+value.width+'</td><td class="gcConValue">'+value.convalue+'</td><td><button class="btn btn-danger btnDeleteGC">Delete</button><button class="btn btn-info btnEditGC">Edit</button></td></tr>');					
//						$(".btnEditGC").bind("click", Edit);
//						$(".btnDeleteGC").bind("click", Delete);
//					});
//				},
//				error: function(){
//					callMessageDialog("Message", 'Error when get garment consumption detail!');			
//				}
//			});
//			//end
//			
//			$("#editGarmentConsumptionDialog").dialog({
//				show:{
//					effect:"slide",
//					duration: 300
//				},
//				title: "Edit Garment Consumption",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Save": {
//	                	text: "Save",
//	                	id: "btnSaveNewGarmentConsumption",
//	                	click: function(){
//	                		var customerOptionSelected = $("#editGarmentConsumptionDialog").find("#sltCustomer").find("option:selected");
//							var customer  = customerOptionSelected.val();
//							var garmentStyleOptionSelected = $("#editGarmentConsumptionDialog").find("#sltGarmentStyle").find("option:selected");
//							var garmentstyle  = garmentStyleOptionSelected.val();
//							var sizeOptionSelected = $("#editGarmentConsumptionDialog").find("#sltSize").find("option:selected");
//							var size  = sizeOptionSelected.val();
//							var description = $('#editGarmentConsumptionDialog').find('#txtDescription').val();
//							var garmentconsumptioncode = $('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val();
//							var garmentconsumptiondetailModellist = [];
//						//lặp qua table contact
//					
//						$('#editGarmentConsumptionDialog').find('#tblGarmentConsumptionDetailList tr').each(function() {
//							var garmentconsumptiondetailcode = $(this).data('id');
//							//var gcWidth = $(this).find(".gcWidth").html();
//							
//							var gcWidthOptionSelected = $(this).find("#sltAddWidthCode").find("option:selected");
//							var gcWidth  = gcWidthOptionSelected.val();
//							var gcConValue = $(this).find(".gcConValue").html();
//							if(typeof gcWidth === "undefined"){
//								return true;//continue
//							}
//							garmentconsumptiondetailModellist.push({
//						          "garmentconsumptiondetailcode": garmentconsumptiondetailcode,
//						          "garmentconsumptioncode": $('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(),
//						          "creator": null,
//						          "width": gcWidth,
//						          "convalue": gcConValue,
//						          "createdate": null
//						        });
//						 });
//						//end lặp qua table detail
//						
//						var garmentconsumption = {
//								garmentconsumptioncode: garmentconsumptioncode,
//								customer: customer,
//								garmentstyle: garmentstyle,
//								size: size,
//								description: description,
//								garmentconsumptiondetails : garmentconsumptiondetailModellist
//						};
//						
//						//call ajax to edit
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data: JSON.stringify(garmentconsumption),
//							contentType: "application/json",
//							url: "/Chori/garmentconsumption/edit",
//							success: function(data){
//								if(data.editStatus==true){
//									callMessageDialog("Message", 'Edit Garment Consumption successful');
//									$("#editGarmentConsumptionDialog").dialog("close");
//									
//									loadData();
//								}else if(data.editStatus==false){
//									callMessageDialog("Message", 'Edit Garment Consumption successful');
//								}
//							},
//							error: function(){
//								callMessageDialog("Message", 'Error when edit Garment Consumption');
//							}
//						});		
//	                	}
//					},
//					"Cancel": function(){
//						$("#editGarmentConsumptionDialog").dialog("close");
//						//Xóa các thông tin trong bảng contact
//						$("#tblGarmentConsumptionDetailList > tbody").html("");
//					}
//				}
//			});
//		});
//		
//		$('.btnDelete').click(function(){
//			var gcCode= $(this).data('id');
//			$("#deleteGarmentConsumptionDialog").find("#messageContent").text('Are you sure you want to delete Garment Consumption "' + gcCode+'"?');
//			
//			$("#deleteGarmentConsumptionDialog").dialog({
//	    		show:{
//					effect:"slide",
//					duration: 300
//				},
//				title: "Delete Garment Consumption Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/garmentconsumption/delete/" + gcCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete Garment Consumption  "'+ gcCode+ '" successfully!');
//										//close dialog
//										$("#deleteGarmentConsumptionDialog").dialog("close");
//										//reload table
//										$("#listGarmentConsumption").dataTable().fnDestroy();
//										$('#listGarmentConsumption tbody').empty();
//										loadData();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t Delete Garment Consumption   "'+ gcCode+ '"!');
//										$("#deleteGarmentConsumptionDialog").dialog("close");
//									}else{
//										alert("This can not be shot, 243!");
//									}
//								}else{
//									alert("Unexpected error! (2), 404");
//								}
//							},
//							error: function(){
//								callMessageDialog("Message", 'Unexpected Error');
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteGarmentConsumptionDialog").dialog("close");
//					}
//				}
//			});
//		});
//	}
//	
//	/**
//	 * Function to add new data on table GarmentConsumptionDetail
//	 */
//	$(function(){
//		//Add, Save, Edit and Delete functions code
//		$(".btnEditGC").bind("click", Edit);
//		$(".btnDeleteGC").bind("click", Delete);
//		$("#btnAddNewGarmentConsumptionDetail").bind("click", Add);
//		$('#editGarmentConsumptionDialog').find('#btnAddNewGarmentConsumptionDetail').bind("click", Add);
//	});
//	
//	//Function add 1 row in GarmentConsumptionDetail table
//	function Add(){
//		$("#tblGarmentConsumptionDetailList tbody").append(
//				"<tr data-id='0'>"+				
//				"<td><select id='sltAddWidthCode' ><option value=-1 disabled selected>Please Choose Width</option></select></td>"+
//				//"<td><input type='text' id='txtDetailWidth'/></td>"+
//				"<td><input type='text' id='txtDetailConValue'/></td>"+
//				"<td><button class='btn btn-info btnSaveGC'>Save</button>" +
//				"<button class='btn btn-danger btnDeleteGC'>Delete</button></td>"+
//				"</tr>");
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/width/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#tblGarmentConsumptionDetailList tbody").find(this).append($('<option>', {       	
//                        value: value.widthcode,
//                        text: value.widthcode
//                    }));
//
//                });
//            },
//            error: function(){
//            }
//		});
//				
//				$(".btnSaveGC").bind("click", Save);		
//				$(".btnDeleteGC").bind("click", Delete);
//	};
//	
//	//Function add row which is inputed to table
//	function Save(){
//		var par = $(this).parent().parent(); //tr
//		var tdWidth = par.children("td:nth-child(1)");
//		var tdConValue = par.children("td:nth-child(2)");
//		var tdButtons = par.children("td:nth-child(3)");
//
//		tdWidth.html(tdWidth.children("option:selected").val());
//		tdWidth.addClass( "gcWidth" );
//		tdConValue.html(tdConValue.children("input[type=text]").val());
//		tdConValue.addClass( "gcConValue" );
//		tdButtons.html("<button class='btn btn-danger btnDeleteGC'>Delete</button><button class='btn btn-info btnEditGC'>Edit</button>");
//
//		$(".btnEditGC").bind("click", Edit);
//		$(".btnDeleteGC").bind("click", Delete);
//	};
//	//end hàm add dòng đã nhập vào table
//	
//	//
//	function Edit(){
//		var par = $(this).parent().parent(); //tr
//		var tdWidth = par.children("td:nth-child(1)");
//		var tdConValue = par.children("td:nth-child(2)");
//		var tdButtons = par.children("td:nth-child(3)");
//			
//		tdWidth.html("<select id='sltAddWidthCode' ><option value='"+tdWidth.text()+"' selected>Please Choose Width</option></select>");
//		//tdWidth.html("<input type='text' id='txtWidth' value='"+tdWidth.html()+"'/>");
//		tdConValue.html("<input type='text' id='txtConValue' value='"+tdConValue.html()+"'/>");
//		tdButtons.html("<button class='btn btn-info btnSaveGC'>Save</button>");
//		$.ajax({
//			dataType: "json",
//            type: 'GET',
//            data:{},
//            url: "/Chori/width/list",
//            success: function (data) {
//            	$.each(data.list, function( key, value ) {
//                    $("#tblGarmentConsumptionDetailList tbody").find("#sltAddWidthCode").append($('<option>', {       	
//                        value: value.widthcode,
//                        text: value.widthcode
//                    }));
//
//                });
//            },
//            error: function(){
//            }
//		});
//		$(".btnSaveGC").bind("click", Save);
//		$(".btnEditGC").bind("click", Edit);
//		$(".btnDeleteGC").bind("click", Delete);
//	};
//	//
//	
//	//
//	function Delete(){
//		var par = $(this).parent().parent(); //tr
//		par.remove();
//	}; 
//	//
//	
//	/**
//	 * This function is used to call and set params for message dialog.
//	 */
//	function callMessageDialog(title, messageContent){
//		$("#messageDialog").find("#messageContent").text(messageContent);
//		$("#messageDialog").dialog({
//			show:{
//				effect:"slide",
//				duration: 300
//			},
//			title: title,
//			height: 250,
//			width: 300,
//			hide: {
//				effect: "slide",
//				duration: 300
//			},
//			buttons:{
//				"OK": function(){
//					$(this).dialog("close");
//				}
//			}
//		});
//	}
//	
//	loadData();
//});

$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "garmentconsumption/list",
			contentType: "application/json",
			success: function(data){
				$("#listGarmentConsumptionCover").html("");	
				var htmlOfListGarmentConsumptionForMultiLanguage = $("#listGarmentConsumptionForMultiLanguage").html();
				//create list assign table
				$("#listGarmentConsumptionCover").append(htmlOfListGarmentConsumptionForMultiLanguage);
				
//				$("#listGarmentConsumption").dataTable().fnDestroy();
//				$('#listGarmentConsumption tbody').empty();
				//var i = 1;
				if(data.list.length==0){
					//callMessageDialog("Message", 'Table have no data');
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.garmentconsumptiondetails.length>0){
						tmp+='<table border="0">';
						$.each(value.garmentconsumptiondetails,function(key,value1){
							tmp+='<tr><td>'+value1.width+'</td><td>'+value1.convalue+'</td></tr>';
						});
						tmp+='</table>';
					}

					$('<tr>').append(
							//$('<td>').text(i++),
							$('<td>').text(value.customer),
							//$('<td>').text(value.garmentstyle),
							$('<td>').text(value.garmentstyledisplayname),
							$('<td>').text(value.kind),
							$('<td>').text(value.type==null?"":value.type),
							$('<td>').text(value.sizename),					
							$('<td>').text(value.description),
							$('<td>').html(value.garmentconsumptiondetails.length==0?'':tmp),
//							$('<td>').html('<button class="btn btn-info btnEdit" data-id="'+value.garmentconsumptioncode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.garmentconsumptioncode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.garmentconsumptioncode+'" data-customer="'+value.customer+'" data-garmentstyle="'+value.garmentstyle+'" data-sizename="'+value.sizename+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listGarmentConsumption');
				});
				action();
				$('#listGarmentConsumption').DataTable({
					"pagingType": "full_numbers",
				     rowsGroup: [0,1,2,3]
			    });
			},
			error: function(){
				CanNotGetDataDialogMessageDialog();
			}
		});
	};
	
//	$.ajax({
//		dataType: "json",
//	    type: 'GET',
//	    data:{},
//	    url: getAbsolutePath() +  "width/list",
//	    success: function (data) {
//	    	$.each(data.list, function( key, value ) {
//	        	//var sel = $('<input type='text' id='txtConValue' value='"+tdConValue.html()+"'/>"').appendTo('body');
//	//        	$(data.list).each(function() {
//	//        		sel.append($("<option>").attr('value',this.widthcode).text(this.widthcode));
//	//        	});
//	 //       	$('body').html("<input type='text' id='txtConValue' value='"+data.list.widthcode+"'/>");
//	            var div = $("<div />");
//	            div.html('<input  id="' + value.widthcode + '" type="text" value = "' + value.widthcode + '" disabled/>'
//	            		+'<input type="text" value="1" />');
//	            $("#TextBoxContainer").append(div);
//	        });
//	    },
//	    error: function(){
//	    }
//	});
	
	//Load Customer Options
	function loadCustomerForAdd()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "customer/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {   		
            		if(value.status == "Active")
            		{
		                $("#addGarmentConsumptionDialog").find("#sltCustomer").append($('<option>', {       	
		                    value: value.customercode,
		                    text: value.shortname
		                }));
            		}

                });
            },
            error: function(){
            	
            }
		});
	};
	
	//Load GarmentStyle Options by customer
	function loadGarmentStyleForAdd(customerCode)
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            async: false,
            url: getAbsolutePath() +  "garmentstyle/list",
            success: function (data) {
            	$("#addGarmentConsumptionDialog").find('#sltGarmentStyle').empty();
            	var countGarmentStyleNumber = 0;
            	$("#addGarmentConsumptionDialog").find("#errorAddSltGarmentConsumption").text("");
            	$.each(data.list, function( key, value ) {
        			$("#addGarmentConsumptionDialog").find("#errorAddSltGarmentConsumption").text("");
            		if(value.customercode == customerCode)
            		{
	                    $("#addGarmentConsumptionDialog").find("#sltGarmentStyle").append($('<option>', {       	
	                        value: value.garmentstylecode,
	                        text: value.displayName
	                    }));
	                    countGarmentStyleNumber++;
            		}
                });
        		if(countGarmentStyleNumber <= 0)
    			{
        			$("#addGarmentConsumptionDialog").find("#errorAddSltGarmentConsumption").text("This customer not have any garment style!" );
    			}
            },
            error: function(){
            	
            }
		});
	};
	
	//Load Size Options by customer
	function loadSizeForAdd(customerCode,garmentStyleCode)
	{		
		$.ajax({
			dataType: "json",
            type: 'POST',
            async: false,
            contentType: "application/json",
			data: 	
				JSON.stringify({									
					customer: customerCode,
					garmentstyle: garmentStyleCode
			}),
            url: getAbsolutePath() +  "garmentconsumption/getsizebycustomerandgarmentstyle",
            success: function (data) {
            	var countSizeNumber = 0;
            	$("#addGarmentConsumptionDialog").find("#errorAddSltSize").text("");
            	$("#addGarmentConsumptionDialog").find("#sltSize").empty();
            	$.each(data.list, function( key, value ) {
            		//check if garment consumption is existed, then disable this size
            		$.ajax({
            			dataType: "json",
                        type: 'POST',
                        async: false,
                        contentType: "application/json",
            			data: 	
            				JSON.stringify({									
            					customer: customerCode,
            					garmentstyle: garmentStyleCode,
            					size: value.sizecode
            			}),
                        url: getAbsolutePath() +  "garmentconsumption/isExist",
                        success: function (data1) {
                        	if(data1.isExisted == true){
            	                $("#addGarmentConsumptionDialog").find("#sltSize").append($('<option>', {       	
            	                    value: value.sizecode,
            	                    text: value.sizename,
            	                    disabled: 'disabled'
            	                }));
                        	}
                        	else{
            	                $("#addGarmentConsumptionDialog").find("#sltSize").append($('<option>', {       	
            	                    value: value.sizecode,
            	                    text: value.sizename
            	                }));
                        	}
                        },
                        error: function(){
                        	
                        }
            		});
            		countSizeNumber++;
//	                $("#addGarmentConsumptionDialog").find("#sltSize").append($('<option>', {       	
//	                    value: value.sizecode,
//	                    text: value.sizename
//	                }));
                });
        		if(countSizeNumber <= 0)
    			{
        			$("#addGarmentConsumptionDialog").find("#errorAddSltSize").text("This garment style not have any size!" );
    			}
            },
            error: function(){
            	
            }
		});
	};
	//Load Customer Options
	function loadCustomerForEdit()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "customer/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
            		if(value.status == "Active")
            		{
	                    $("#editGarmentConsumptionDialog").find("#sltCustomer").append($('<option>', {       	
	                        value: value.customercode,
	                        text: value.shortname
	                    }));
            		}
                });
            },
            error: function(){
            	
            }
		});
	};
	
	//Load GarmentStyle Options
	function loadGarmentStyleForEdit()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "garmentstyle/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#editGarmentConsumptionDialog").find("#sltGarmentStyle").append($('<option>', {       	
                        value: value.garmentstylecode,
                        text: value.displayName
                    }));

                });
            },
            error: function(){
            	
            }
		});
	};
	
	//Load Size Options
	function loadSizeForEdit()
	{		
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "size/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
                    $("#editGarmentConsumptionDialog").find("#sltSize").append($('<option>', {       	
                        value: value.sizecode,
                        text: value.sizename
                    }));

                });
            },
            error: function(){
            	
            }
		});
	};
	function getGarmentConsumptionCode(customer,garmentstyle,size) {
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "garmentconsumption/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					if(value.customer == customer 
							&& value.garmentstyle == garmentstyle
							&& value.size == size)
						{
							return value.garmentconsumptioncode;
						}
				});
			},
			error: function(){
				callMessageDialog("Message", 'Can not get garmentconsumptioncode');
			}
		});
	}
	
	function btnAddNewGarmentConsumption_Click() {	
		$("#addGarmentConsumptionDialog").find('#sltSize').prop( "disabled", true );
		$("#addGarmentConsumptionDialog").find('#sltGarmentStyle').prop( "disabled", true );
		
		//to get garment style by customer
		$("#addGarmentConsumptionDialog").find('#sltCustomer').on('change propertychange', function (e) {
			//enable when selected customer
			$("#addGarmentConsumptionDialog").find('#sltSize').prop( "disabled", false );
			$("#addGarmentConsumptionDialog").find('#sltGarmentStyle').prop( "disabled", false );
			$("#addGarmentConsumptionDialog").find('#sltSize').empty();
			$("#addGarmentConsumptionDialog").find('#sltGarmentStyle').empty();
			
			//get customer selected
    		var customerOptionSelected = $("#addGarmentConsumptionDialog").find("#sltCustomer").find("option:selected");
			var customerCode  = customerOptionSelected.val();
			
			//load garmentstyle by customer
			//loadSizeForAdd(customerCode);
			loadGarmentStyleForAdd(customerCode);
			
			//get garmentstyle selected
    		var garmentStyleCodeOptionSelected = $("#addGarmentConsumptionDialog").find("#sltGarmentStyle").find("option:selected");
			var garmentStyleCode  = garmentStyleCodeOptionSelected.val();
			
			//load size by garmentstyle
			loadSizeForAdd(customerCode,garmentStyleCode);
		});
		
		//to get size by garment style
		$("#addGarmentConsumptionDialog").find('#sltGarmentStyle').on('change propertychange', function (e) {		
			$("#addGarmentConsumptionDialog").find('#sltSize').empty();
			//get customer selected
    		var customerOptionSelected = $("#addGarmentConsumptionDialog").find("#sltCustomer").find("option:selected");
			var customerCode  = customerOptionSelected.val();
			//get garmentstyle selected
    		var garmentStyleCodeOptionSelected = $("#addGarmentConsumptionDialog").find("#sltGarmentStyle").find("option:selected");
			var garmentStyleCode  = garmentStyleCodeOptionSelected.val();
			
			//load size by garmentstyle
			loadSizeForAdd(customerCode,garmentStyleCode);
		});
		
		//when click add new button
		$('#btnAddNewGarmentConsumption').click(function(){
			$("#addGarmentConsumptionDialog").find("#widthValueContainer").empty();
			
			var widthCount = 1;
			$.ajax({
				dataType: "json",
			    type: 'GET',
			    data:{},
			    url: getAbsolutePath() +  "garmentconsumption/widthlist",
			    success: function (data) {
			    	$.each(data.list, function( key, value ) {
			            var div = $("<div/>");
			            div.html('<input  id="txtWidthName' + widthCount + '" type="text" value = "' + value.widthcode + '" disabled/>'
			            		+'<input type="number" step="any" min="0" value="0" id="txtConValue' + widthCount + '" />');
			            $("#addGarmentConsumptionDialog").find("#widthValueContainer").append(div);
			            //validate not input none numeric
			            $("#widthValueContainer").find("#widthValueContainer").on('keydown', "#txtConValue"+widthCount+"", function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
			            widthCount++;
			        });
			    },
			    error: function(){
			    	alert("Cannot get width");
			    }
			});
			
			$("#addGarmentConsumptionDialog").dialog({
				show:{
					effect:"slide",
					duration: 300
				},
				title: "Add New Garment Consumption",
				height: 550,
				width: 500,
				modal: true,
				buttons:{
					"Save": {
	                	text: "Save",
	                	id: "btnSaveNewGarmentConsumption",
	                	click: function(){
	                		var customerOptionSelected = $("#addGarmentConsumptionDialog").find("#sltCustomer").find("option:selected");
							var customer  = customerOptionSelected.val();
							var garmentStyleOptionSelected = $("#addGarmentConsumptionDialog").find("#sltGarmentStyle").find("option:selected");
							var garmentstyle  = garmentStyleOptionSelected.val();
							var sizeOptionSelected = $("#addGarmentConsumptionDialog").find("#sltSize").find("option:selected");
							var size  = sizeOptionSelected.val();
							var description = $('#addGarmentConsumptionDialog').find('#txtDescription').val();

							//check width data is existed in DB
							$.ajax({
								dataType: "json",
							    type: 'GET',
							    data:{},
							    url: getAbsolutePath() +  "garmentconsumption/isExistWidthData",
							    success: function (data) {
									if(data.isExistedWidthData==true)
									{
										//to validate con value
										var validateConValue = true;
										for(var i = 1; i < widthCount ; i++)
										{
											var convalue = $("#addGarmentConsumptionDialog").find("#txtConValue"+i+"").val();
											if(convalue=="" || convalue==null || convalue < 0 )
												{
													validateConValue = false;												
												}
										}
										
										if(validateConValue == true)
										{
											//validate description
											if(description.length < 150)
											{
												//validate dropdownbox
												if(customer != -1 && garmentstyle != -1 && size != -1  && garmentstyle != null && size != null )
												{
													$.ajax({
														dataType: "json",
														type: 'POST',
														data: 	
															JSON.stringify({									
																customer: customer,
																garmentstyle: garmentstyle,
																size : size
														}),
														contentType: "application/json",
														url: getAbsolutePath() +  "garmentconsumption/isExist/",
														success: function(data){
															if(data.isExisted == false){
																//call ajax to add garment consumption
																$.ajax({
																	dataType: "json",
																	type: 'POST',
																	data: 	
																		JSON.stringify({									
																			customer: customer,
																			garmentstyle: garmentstyle,
																			size: size,
																			description: description,
																	}),
																	contentType: "application/json",
																	url: getAbsolutePath() +  "garmentconsumption/add2",
																	success: function(data){
																		var garmentconsumptioncode;
																		$.ajax({
																			dataType: "json",
																			type: 'GET',
																			data: {},
																			url: getAbsolutePath() +  "garmentconsumption/list",
																			contentType: "application/json",
																			success: function(data){
																				$.each(data.list,function(key,value){
																					if(value.customer == customer 
																							&& value.garmentstyle == garmentstyle
																							&& value.size == size)
																						{
																							garmentconsumptioncode = value.garmentconsumptioncode;
																						}
																				});
																				

																				//loop width/convalue
																				for(var i = 1; i < widthCount ; i++)
																				{
																					//get id of width & convalue textbox
																					var widthcode = $("#addGarmentConsumptionDialog").find("#txtWidthName"+i+"").val();
																					var convalue = $("#addGarmentConsumptionDialog").find("#txtConValue"+i+"").val();
																					//call ajax to add garment consumption detail								
																					$.ajax({
																						dataType: "json",
																						type: 'POST',
																						data: 	
																							JSON.stringify({									
																								garmentconsumption: garmentconsumptioncode,
																								width : widthcode,
																								convalue: convalue
																						}),
																						contentType: "application/json",
																						url: getAbsolutePath() +  "garmentconsumptiondetail/add",
																						success: function(data){				
																								$("#addGarmentConsumptionDialog").dialog("close");
																								addSuccessMessageDialog();
																								loadData();																						
																						},
																						error: function(){
																							addFailedMessageDialog();
																						}
																					});
																				}

																			},
																			error: function(){
																				callMessageDialog("Message", 'Add failed. Please check data and try again');
																			}
																		});
																	

																	},
																	error: function(){
																		addFailedMessageDialog();
																	}
																});
															}
															else {
																IsExistedMessageDialog();
															}
														},
														error: function(){
															IsExistedMessageDialog();
														}
													});	
												}
												else {
													callMessageDialog("Message", 'Please select all require field!');
												}
											}
											else
											{
												callMessageDialog("Message", 'Description must be less than 150 characters!');
											}
										}
										else
										{
											callMessageDialog("Message", 'Add failed. Please check consumption value and try again!');
										}
									}
									else
									{
										callMessageDialog("Message", 'Please insert at least one width in Width Management!');
									}
							    },
							    error: function(){
							    	alert("Cannot get width");
							    }
							});				
	                	}
					},
					"Cancel": function(){
						$("#addGarmentConsumptionDialog").dialog("close");
					}
				}
			});
		});
	}
	
	//Load GarmentStyle Options by customer
	function loadGarmentStyleForAddForCopy(customerCode)
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            async: false,
            url: getAbsolutePath() +  "garmentstyle/list",
            success: function (data) {
            	$("#addGarmentConsumptionDialogForCopy").find('#sltGarmentStyleForCopy').empty();
            	var countGarmentStyleNumber =0;
            	$("#addGarmentConsumptionDialogForCopy").find("#errorCopySltGarmentConsumption").text("");
            	$.each(data.list, function( key, value ) {
            		if(value.customercode == customerCode)
            		{
	                    $("#addGarmentConsumptionDialogForCopy").find("#sltGarmentStyleForCopy").append($('<option>', {       	
	                        value: value.garmentstylecode,
	                        text: value.displayName
	                    }));
	                    countGarmentStyleNumber++;
            		}
                });
        		if(countGarmentStyleNumber <= 0)
    			{
        			$("#addGarmentConsumptionDialogForCopy").find("#errorCopySltGarmentConsumption").text("This customer not have any garment style!" );
    			}

            },
            error: function(){
            	
            }
		});
	};
	
	//Load Customer Options
	function loadCustomerForAddForCopy()
	{
		$.ajax({
			dataType: "json",
            type: 'GET',
            data:{},
            url: getAbsolutePath() +  "customer/list",
            success: function (data) {
            	$.each(data.list, function( key, value ) {
            		if(value.status == "Active")
            		{
		                $("#addGarmentConsumptionDialogForCopy").find("#sltCustomerForCopy").append($('<option>', {       	
		                    value: value.customercode,
		                    text: value.shortname
		                }));
            		}

                });
            },
            error: function(){
            	
            }
		});
	};
	
	function btnAddNewGarmentConsumptionForCopy_Click() {	
		
		$("#addGarmentConsumptionDialogForCopy").find('#sltGarmentStyleForCopy').prop( "disabled", true );
		
		//to get garment style by customer
		$("#addGarmentConsumptionDialogForCopy").find('#sltCustomerForCopy').on('change propertychange', function (e) {
			//enable when selected customer
			$("#addGarmentConsumptionDialogForCopy").find('#sltGarmentStyleForCopy').prop( "disabled", false );
			$("#addGarmentConsumptionDialogForCopy").find('#sltGarmentStyleForCopy').empty();
			
			//get customer selected
    		var customerOptionSelected = $("#addGarmentConsumptionDialogForCopy").find("#sltCustomerForCopy").find("option:selected");
			var customerCode  = customerOptionSelected.val();
			
			//load garmentstyle by customer
			//loadSizeForAdd(customerCode);
			loadGarmentStyleForAddForCopy(customerCode);
			
			//get garmentstyle selected
    		var garmentStyleCodeOptionSelected = $("#addGarmentConsumptionDialog").find("#sltGarmentStyleForCopy").find("option:selected");
			var garmentStyleCode  = garmentStyleCodeOptionSelected.val();
		});
		
		//when click add new button
		$('#btnAddNewGarmentConsumptionForCopy').click(function(){
			$("#addGarmentConsumptionDialogForCopy").find("#widthValueContainerForCopy").empty();
			
			var widthCount = 1;
			$.ajax({
				dataType: "json",
			    type: 'GET',
			    data:{},
			    url: getAbsolutePath() +  "garmentconsumption/widthlist",
			    success: function (data) {
			    	$.each(data.list, function( key, value ) {
			            var div = $("<div/>");
			            div.html('<input  id="txtWidthNameForCopy' + widthCount + '" type="text" value = "' + value.widthcode + '" disabled/>'
			            		+'<input type="number" step="any" min="0" value="0" id="txtConValueForCopy' + widthCount + '" />');
			            $("#addGarmentConsumptionDialogForCopy").find("#widthValueContainerForCopy").append(div);
			            //validate not input none numeric
			            $("#widthValueContainerForCopy").find("#widthValueContainerForCopy").on('keydown', "#txtConValueForCopy"+widthCount+"", function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
			            widthCount++;
			        });
			    },
			    error: function(){
			    	alert("Cannot get width");
			    }
			});
			
			$("#addGarmentConsumptionDialogForCopy").dialog({
				show:{
					effect:"slide",
					duration: 300
				},
				title: "Add New Garment Consumption by Garment Style",
				height: 550,
				width: 550,
				modal: true,
				buttons:{
					"Save": {
	                	text: "Save",
	                	id: "btnSaveNewGarmentConsumptionForCopy",
	                	click: function(){
	                		var customerOptionSelected = $("#addGarmentConsumptionDialogForCopy").find("#sltCustomerForCopy").find("option:selected");
							var customer  = customerOptionSelected.val();
							var garmentStyleOptionSelected = $("#addGarmentConsumptionDialogForCopy").find("#sltGarmentStyleForCopy").find("option:selected");
							var garmentstyle  = garmentStyleOptionSelected.val();

							//check width data is existed in DB
							$.ajax({
								dataType: "json",
							    type: 'GET',
							    data:{},
							    url: getAbsolutePath() +  "garmentconsumption/isExistWidthData",
							    success: function (data) {
									if(data.isExistedWidthData==true)
									{
										//to validate con value
										var validateConValue = true;
										for(var i = 1; i < widthCount ; i++)
										{
											var convalue = $("#addGarmentConsumptionDialogForCopy").find("#txtConValueForCopy"+i+"").val();
											if(convalue=="" || convalue==null || convalue < 0 )
												{
													validateConValue = false;												
												}
										}
										
										if(validateConValue == true)
										{
											//validate dropdownbox
											if(customer != -1 && garmentstyle != -1 && garmentstyle != null)
											{
													//call ajax to add garment consumption
													$.ajax({
														dataType: "json",
														type: 'POST',
														data: 	
															JSON.stringify({									
																customer: customer,
																garmentstyle: garmentstyle
														}),
														contentType: "application/json",
														url: getAbsolutePath() +  "garmentconsumption/addbygarmentstyle",
														success: function(data){
															if(data.addStatus==false){
																callMessageDialog("Message", 'Add garment consumption failed. Please check data and try again!');
															}
															else
															{
																var garmentconsumptioncode;
																//get garment consumption code by customer and garment style
																$.ajax({
																	dataType: "json",
																	type: 'POST',
																	data: 	
																	JSON.stringify({									
																		customer: customer,
																		garmentstyle: garmentstyle
																	}),
																	url: getAbsolutePath() +  "garmentconsumption/getgarconcodebycustomerandgarstyle",
																	contentType: "application/json",
																	success: function(data){
																		$.each(data.garconcodelist,function(key,value){
																			garmentconsumptioncode = value;
																			//loop width/convalue
																			for(var i = 1; i < widthCount ; i++)
																			{
																				//get id of width & convalue textbox
																				var widthcode = $("#addGarmentConsumptionDialogForCopy").find("#txtWidthNameForCopy"+i+"").val();
																				var convalue = $("#addGarmentConsumptionDialogForCopy").find("#txtConValueForCopy"+i+"").val();
																				//call ajax to add garment consumption detail								
																				$.ajax({
																					dataType: "json",
																					type: 'POST',
																					data: 	
																						JSON.stringify({									
																							garmentconsumption: garmentconsumptioncode,
																							width : widthcode,
																							convalue: convalue
																					}),
																					contentType: "application/json",
																					url: getAbsolutePath() +  "garmentconsumptiondetail/addbygarmentstyle",
																					success: function(data){				
																							$("#addGarmentConsumptionDialogForCopy").dialog("close");
																							addSuccessMessageDialog();
																							loadData();
																					},
																					error: function(){
																						addFailedMessageDialog();
																					}
																				});
																			}
																		});
																	},
																	error: function(){
																		callMessageDialog("Message", 'Add failed. Please check data and try again');
																	}
																});
															}
												
														},
														error: function(){
															addFailedMessageDialog();
														}
													});
											}
											else {
												callMessageDialog("Message", 'Please select all drop down box!');
											}
										}
										else
										{
											callMessageDialog("Message", 'Add failed. Please check consumption value and try again!');
										}
									}
									else
									{
										callMessageDialog("Message", 'Please insert at least one width in Width Management!');
									}
							    },
							    error: function(){
							    	alert("Cannot get width");
							    }
							});				
	                	}
					},
					"Cancel": function(){
						$("#addGarmentConsumptionDialogForCopy").dialog("close");
					}
				}
			});
		});
	}
	
	//action by dropdownbox
	function action(){	
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    $(".selectOption").val("Options");
		    
		    //delete
		    if(valueSelected=="Delete"){
		    	var gcCode= $(this).data('id');
		    	var customerForDelete = $(this).data('customer');
		    	var garmentstyleForDelete = $(this).data('garmentstyle');
		    	var sizeForDelete= $(this).data('sizename');
				$("#deleteGarmentConsumptionDialog").find("#messageContent").text('Are you sure you want to delete Garment Consumption of Size: "' 
						+ sizeForDelete+'" - Customer: "'+ customerForDelete+'"" - Garment Style: "'+ garmentstyleForDelete.split("@@@",1) +'"?');
				
				$("#deleteGarmentConsumptionDialog").dialog({
		    		show:{
						effect:"slide",
						duration: 300
					},
					title: "Delete Garment Consumption Confirm",
					height: 300,
					width: 500,
					modal: true,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:{},
								contentType: "application/json",
								url: getAbsolutePath() +  "garmentconsumption/delete/" + gcCode,
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											deleteSuccessMessageDialog();
											//close dialog
											$("#deleteGarmentConsumptionDialog").dialog("close");
											//reload table
//											$("#listGarmentConsumption").dataTable().fnDestroy();
//											$('#listGarmentConsumption tbody').empty();
											loadData();
										}else if(data.deleteStatus== false){
											deleteFailedMessageDialog();
											$("#deleteGarmentConsumptionDialog").dialog("close");
										}else{
											deleteFailedMessageDialog();
										}
									}else{
										deleteFailedMessageDialog();
									}
								},
								error: function(){
									deleteFailedMessageDialog();
								}
							});
						},
						"Cancel": function(){
							$("#deleteGarmentConsumptionDialog").dialog("close");
						}
					}
				});
			}
		
		/*
		 * -----------------EDIT------------------------
		 */
		if(valueSelected=="Edit"){

			//to clear tblGarmentConsumptionDetailList
			$('#tblGarmentConsumptionDetailList > tbody').html("");
			
			//to get id of garconCode (selected)
			var garconCode= $(this).data('id');
			
			$("#editGarmentConsumptionDialog").find("#widthValueEditContainer").empty();
			var widthCount = 1;
			//get data by id and set to dialog
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: getAbsolutePath() +  "garmentconsumption/detail/"+garconCode,
				contentType: "application/json",
				success: function(data){
					//set garmentconsumption detail to dialog
					var sltCustomerValue = data.garmentconsumption.customer;
					$("#editGarmentConsumptionDialog").find("#sltCustomer option").each(function() { this.selected = (this.text == sltCustomerValue); });
					$("#editGarmentConsumptionDialog").find('#sltCustomer').prop( "disabled", true );				
					var sltGarmentStyleValue = data.garmentconsumption.garmentstyle;
					$("#editGarmentConsumptionDialog").find("#sltGarmentStyle option").each(function() { this.selected = (this.text == sltGarmentStyleValue); });
					$("#editGarmentConsumptionDialog").find('#sltGarmentStyle').prop( "disabled", true );
					$('#editGarmentConsumptionDialog').find('#txtDescription').val(data.garmentconsumption.description);
					$('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(data.garmentconsumption.garmentconsumptioncode);
					var sltSizeValue = data.garmentconsumption.sizename;
					$("#editGarmentConsumptionDialog").find("#sltSize option").each(function() { this.selected = (this.text == sltSizeValue); });
					$("#editGarmentConsumptionDialog").find('#sltSize').prop( "disabled", true );
					$('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(data.garmentconsumption.garmentconsumptioncode),
					$.each(data.garmentconsumption.garmentconsumptiondetails,function(key,value){
			            var div = $("<div/>");
			            div.html('<input  id="txtWidthName' + widthCount + '" data-code="'+ value.garmentconsumptiondetailcode + '"  type="text" value = "' + value.width + '" disabled/>'
			            		+'<input type="number" step="any" min="0" value="' + value.convalue + '" id="txtConValue' + widthCount + '" />'
			            		+'<input style="display:none" type="text" value="' + value.garmentconsumptiondetailcode + '" id="txtDetailCode' + widthCount + '" />');
			            $("#editGarmentConsumptionDialog").find("#widthValueEditContainer").append(div);
			            //validate not input none numeric
			            $("#editGarmentConsumptionDialog").find("#widthValueEditContainer").on('keydown', "#txtConValue"+widthCount+"", function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
			            widthCount++;
			            
						//$("#tblGarmentConsumptionDetailList tbody").append('<tr data-id="'+value.garmentconsumptiondetailcode+'"><td class="gcWidth"><select><option value='+value.width+' selected>'+value.width+'</option></select></td><td class="gcConValue">'+value.convalue+'</td><td><button class="btn btn-danger btnDeleteGC">Delete</button><button class="btn btn-info btnEditGC">Edit</button></td></tr>');						
					});
				},
				error: function(){
					callMessageDialog("Message", 'Error when get garment consumption detail!');			
				}
			});
			//end
			
			$("#editGarmentConsumptionDialog").dialog({
				show:{
					effect:"slide",
					duration: 300
				},
				title: "Edit Garment Consumption",
				height: 550,
				width: 500,
				modal: true,
				buttons:{
					"Save": {
	                	text: "Save",
	                	id: "btnSaveNewGarmentConsumption",
	                	click: function(){
		                		var customerOptionSelected = $("#editGarmentConsumptionDialog").find("#sltCustomer").find("option:selected");
								var customer  = customerOptionSelected.val();
								var garmentStyleOptionSelected = $("#editGarmentConsumptionDialog").find("#sltGarmentStyle").find("option:selected");
								var garmentstyle  = garmentStyleOptionSelected.val();
								var sizeOptionSelected = $("#editGarmentConsumptionDialog").find("#sltSize").find("option:selected");
								var size  = sizeOptionSelected.val();
								var description = $('#editGarmentConsumptionDialog').find('#txtDescription').val();
								//var garmentconsumptioncode = $('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val();
					
								//to validate con value
								var validateConValue = true;
								for(var i = 1; i < widthCount ; i++)
								{
									var convalue = $("#editGarmentConsumptionDialog").find("#txtConValue"+i+"").val();
									if(convalue=="" || convalue==null || convalue < 0 )
										{
											validateConValue = false;												
										}
								}
								
								if(validateConValue == true)
								{
									//validate description
									if(description.length < 150){
										//call ajax to edit garment consumption
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: 	
												JSON.stringify({	
													garmentconsumptioncode: garconCode,
													description: description					
											}),
											contentType: "application/json",
											url: getAbsolutePath() +  "garmentconsumption/edit",
											success: function(data){
												//loop width/convalue
												for(var i = 1; i < widthCount ; i++)
												{
													//get id of width & convalue textbox
													var widthcode = $("#editGarmentConsumptionDialog").find("#txtWidthName"+i+"").val();
													var convalue = $("#editGarmentConsumptionDialog").find("#txtConValue"+i+"").val();
													var garmentconsumptiondetailcode = $("#editGarmentConsumptionDialog").find("#txtDetailCode"+i+"").val();
													var garmentconsumption = $('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val();
													//call ajax to add garment consumption detail								
													$.ajax({
														dataType: "json",
														type: 'POST',
														data: 	
															JSON.stringify({									
																garmentconsumptiondetailcode: garmentconsumptiondetailcode,
																garmentconsumption: garmentconsumption,
																width : widthcode,
																convalue: convalue,						
														}),
														contentType: "application/json",
														url: getAbsolutePath() +  "garmentconsumptiondetail/edit",
														success: function(data){
															editSuccessMessageDialog();
															$("#editGarmentConsumptionDialog").dialog("close");
															loadData();
														},
														error: function(){
															editFailedMessageDialog();
														}
													});
												}	
												
											},
											error: function(){
												editFailedMessageDialog();
											}
										});
									}
									else
									{
										callMessageDialog("Message", 'Description must be less than 150 characters!');
									}
								}	
								else
								{
									callMessageDialog("Message", 'Add failed. Please check consumption value and try again!');
								}
							},
						},
						"Cancel": function(){
							$("#editGarmentConsumptionDialog").dialog("close");
						}
					}
				});
			}
		
		});
	};
	
	//action by button
//	function action(){	
//		$('.btnDelete').click(function(){
//			var gcCode= $(this).data('id');
//			$("#deleteGarmentConsumptionDialog").find("#messageContent").text('Are you sure you want to delete Garment Consumption "' + gcCode+'"?');
//			
//			$("#deleteGarmentConsumptionDialog").dialog({
//	    		show:{
//					effect:"slide",
//					duration: 300
//				},
//				title: "Delete Garment Consumption Confirm",
//				height: 200,
//				width: 400,
//				modal: true,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: getAbsolutePath() +  "garmentconsumption/delete/" + gcCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										deleteSuccessMessageDialog();
//										//close dialog
//										$("#deleteGarmentConsumptionDialog").dialog("close");
//										//reload table
//										$("#listGarmentConsumption").dataTable().fnDestroy();
//										$('#listGarmentConsumption tbody').empty();
//										loadData();
//									}else if(data.deleteStatus== false){
//										deleteFailedMessageDialog();
//										$("#deleteGarmentConsumptionDialog").dialog("close");
//									}else{
//										deleteFailedMessageDialog();
//									}
//								}else{
//									deleteFailedMessageDialog();
//								}
//							},
//							error: function(){
//								deleteFailedMessageDialog();
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteGarmentConsumptionDialog").dialog("close");
//					}
//				}
//			});
//		});
//		
//		/*
//		 * -----------------EDIT------------------------
//		 */
//		$('.btnEdit').click(function(){
//
//		//to clear tblGarmentConsumptionDetailList
//		$('#tblGarmentConsumptionDetailList > tbody').html("");
//		
//		//to get id of garconCode (selected)
//		var garconCode= $(this).data('id');
//		
//		$("#editGarmentConsumptionDialog").find("#widthValueEditContainer").empty();
//		var widthCount = 1;
//		//get data by id and set to dialog
//		$.ajax({
//			dataType: "json",
//			type: 'GET',
//			data: {},
//			url: getAbsolutePath() +  "garmentconsumption/detail/"+garconCode,
//			contentType: "application/json",
//			success: function(data){
//				//set garmentconsumption detail to dialog
//				var sltCustomerValue = data.garmentconsumption.customer;
//				$("#editGarmentConsumptionDialog").find("#sltCustomer option").each(function() { this.selected = (this.text == sltCustomerValue); });
//				$("#editGarmentConsumptionDialog").find('#sltCustomer').prop( "disabled", true );				
//				var sltGarmentStyleValue = data.garmentconsumption.garmentstyle;
//				$("#editGarmentConsumptionDialog").find("#sltGarmentStyle option").each(function() { this.selected = (this.text == sltGarmentStyleValue); });
//				$("#editGarmentConsumptionDialog").find('#sltGarmentStyle').prop( "disabled", true );
//				$('#editGarmentConsumptionDialog').find('#txtDescription').val(data.garmentconsumption.description);
//				$('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(data.garmentconsumption.garmentconsumptioncode);
//				var sltSizeValue = data.garmentconsumption.sizename;
//				$("#editGarmentConsumptionDialog").find("#sltSize option").each(function() { this.selected = (this.text == sltSizeValue); });
//				$("#editGarmentConsumptionDialog").find('#sltSize').prop( "disabled", true );
//				$('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val(data.garmentconsumption.garmentconsumptioncode),
//				$.each(data.garmentconsumption.garmentconsumptiondetails,function(key,value){
//		            var div = $("<div/>");
//		            div.html('<input  id="txtWidthName' + widthCount + '" data-code="'+ value.garmentconsumptiondetailcode + '"  type="text" value = "' + value.width + '" disabled/>'
//		            		+'<input type="text" value="' + value.convalue + '" id="txtConValue' + widthCount + '" />'
//		            		+'<input style="display:none" type="text" value="' + value.garmentconsumptiondetailcode + '" id="txtDetailCode' + widthCount + '" />');
//		            $("#editGarmentConsumptionDialog").find("#widthValueEditContainer").append(div);
//		            //validate not input none numeric
//		            $("#editGarmentConsumptionDialog").find("#widthValueEditContainer").on('keydown', "#txtConValue"+widthCount+"", function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
//		            widthCount++;
//		            
//					//$("#tblGarmentConsumptionDetailList tbody").append('<tr data-id="'+value.garmentconsumptiondetailcode+'"><td class="gcWidth"><select><option value='+value.width+' selected>'+value.width+'</option></select></td><td class="gcConValue">'+value.convalue+'</td><td><button class="btn btn-danger btnDeleteGC">Delete</button><button class="btn btn-info btnEditGC">Edit</button></td></tr>');						
//				});
//			},
//			error: function(){
//				callMessageDialog("Message", 'Error when get garment consumption detail!');			
//			}
//		});
//		//end
//		
//		$("#editGarmentConsumptionDialog").dialog({
//			show:{
//				effect:"slide",
//				duration: 300
//			},
//			title: "Edit Garment Consumption",
//			height: 550,
//			width: 500,
//			modal: true,
//			buttons:{
//				"Save": {
//                	text: "Save",
//                	id: "btnSaveNewGarmentConsumption",
//                	click: function(){
//                		var customerOptionSelected = $("#editGarmentConsumptionDialog").find("#sltCustomer").find("option:selected");
//						var customer  = customerOptionSelected.val();
//						var garmentStyleOptionSelected = $("#editGarmentConsumptionDialog").find("#sltGarmentStyle").find("option:selected");
//						var garmentstyle  = garmentStyleOptionSelected.val();
//						var sizeOptionSelected = $("#editGarmentConsumptionDialog").find("#sltSize").find("option:selected");
//						var size  = sizeOptionSelected.val();
//						var description = $('#editGarmentConsumptionDialog').find('#txtDescription').val();
//						//var garmentconsumptioncode = $('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val();
//			
//						//call ajax to edit garment consumption
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data: 	
//								JSON.stringify({	
//									garmentconsumptioncode: garconCode,
//									description: description					
//							}),
//							contentType: "application/json",
//							url: getAbsolutePath() +  "garmentconsumption/edit",
//							success: function(data){
//								//loop width/convalue
//								for(var i = 1; i < widthCount ; i++)
//								{
//									//get id of width & convalue textbox
//									var widthcode = $("#editGarmentConsumptionDialog").find("#txtWidthName"+i+"").val();
//									var convalue = $("#editGarmentConsumptionDialog").find("#txtConValue"+i+"").val();
//									var garmentconsumptiondetailcode = $("#editGarmentConsumptionDialog").find("#txtDetailCode"+i+"").val();
//									var garmentconsumption = $('#editGarmentConsumptionDialog').find('#txtGarmentConsumptionCode').val();
//									//call ajax to add garment consumption detail								
//									$.ajax({
//										dataType: "json",
//										type: 'POST',
//										data: 	
//											JSON.stringify({									
//												garmentconsumptiondetailcode: garmentconsumptiondetailcode,
//												garmentconsumption: garmentconsumption,
//												width : widthcode,
//												convalue: convalue,						
//										}),
//										contentType: "application/json",
//										url: getAbsolutePath() +  "garmentconsumptiondetail/edit",
//										success: function(data){
//											editSuccessMessageDialog();
//											$("#editGarmentConsumptionDialog").dialog("close");
//											loadData();
//										},
//										error: function(){
//											editFailedMessageDialog();
//										}
//									});
//								}	
//								
//							},
//							error: function(){
//								editFailedMessageDialog();
//							}
//						});
//						
//
//							
//						},
//					},
//					"Cancel": function(){
//						$("#editGarmentConsumptionDialog").dialog("close");
//					}
//				}
//			});
//		
//		});
//	};
			

	/**
	 * This function is used to call and set params for message dialog.
	 */
	function callMessageDialog(title, messageContent){
		$("#messageDialog").find("#messageContent").text(messageContent);
		$("#messageDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 350,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function validateSelectOption_AddSize()
	{
		var customerOptionSelected = $("#sltAddCustomer").find("option:selected");
		var customer  = customerOptionSelected.val();
		var garmentKindOptionSelected = $("#sltAddGarmentKind").find("option:selected");
		var garmentkind  = garmentKindOptionSelected.val();
		var typeOptionSelected = $("#sltAddType").find("option:selected");
		var type  = typeOptionSelected.val();
		var sizename =  $("#dialogCreateSize").find("#txtAddSize").val();
		if(customer == -1
			|| garmentkind == -1
			|| type == -1
			|| sizename.length < 1
			)
		{
			return false;
		}
		return true;	
	}
	
	//-------------MESSAGE DIALOG------------
	function addSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#AddSuccessDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function addFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#AddFailedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function editSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#EditSuccessDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function editFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#EditFailedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	function deleteSuccessMessageDialog(){
		var title = $("#SuccessTitle").text();
		$("#DeleteSuccessDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
					location.reload();
				}
			}
		}).prev(".ui-widget-header").css("color","#333");
	}
	
	function deleteFailedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#DeleteFailedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function CanNotGetDataDialogMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#CanNotGetDataDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	function IsExistedMessageDialog(){
		var title = $("#ErrorTitle").text();
		$("#IsExistedDialog").dialog({
			show:{
				effect:"slide",
				duration: 300
			},
			title: title,
			height: 250,
			width: 300,
			hide: {
				effect: "slide",
				duration: 300
			},
			modal: true,
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		}).prev(".ui-widget-header").css("color","#f5321c");
	}
	
	//VALIDATE DESCRIPTION
	$("#addGarmentConsumptionDialog").find('#txtDescription').on('input propertychange paste', function (e) {
		var des = $("#addGarmentConsumptionDialog").find("#txtDescription").val();
		if(des.length>150) {
			$("#addGarmentConsumptionDialog").find("#errorTxtDescription").text("Description must be less than 150 characters");
		}
		else{				
			$("#addGarmentConsumptionDialog").find("#errorTxtDescription").text("");
		}
	});
	$("#editGarmentConsumptionDialog").find('#txtDescription').on('input propertychange paste', function (e) {
		var des = $("#editGarmentConsumptionDialog").find("#txtDescription").val();
		if(des.length>150) {
			$("#editGarmentConsumptionDialog").find("#errorTxtDescription").text("Description must be less than 150 characters");
		}
		else{				
			$("#editGarmentConsumptionDialog").find("#errorTxtDescription").text("");
		}
	});
	
	loadCustomerForAdd();
	loadCustomerForAddForCopy();
	loadCustomerForEdit();
	loadGarmentStyleForEdit();
	loadSizeForEdit();
	btnAddNewGarmentConsumption_Click();
	btnAddNewGarmentConsumptionForCopy_Click();
	loadData();


});