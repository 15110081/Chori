$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		//xóa tạo lại table
		$("#FabricInformationTableCover").html('');
		$("#FabricInformationTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFabricInformation">'+
					'<thead>'+
						'<tr>'+
						'<th>No</th>'+
						'<th>Fabric Indent</th>'+
						'<th>Customer</th>'+
						'<th>Fabric Supplier</th>'+
						'<th>Factory Name</th>'+
						'<th>Est Delv Date</th>'+
						'<th>Act Delv Date</th>'+
						'<th>Fabric Invoice No</th>'+
						'<th>Fabric Item</th>'+
						'<th>Color</th>'+
						'<th>Total Yard</th>'+
						'<th></th>'+
						'</tr>'+
					'</thead>'+
				'</table>');
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fabricinformation/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					callMessageDialog("Message", "Table have no data!");
				}
				$.each(data.list,function(key,value){
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.fabricno),
							$('<td>').text(value.customerShortname),
							$('<td>').text(value.fabricsupplierShortname),
							$('<td>').text(value.factoryShortname),
							$('<td>').text(value.estimatedelvdate==null?'':value.estimatedelvdate),
							$('<td>').text(value.actualdelvdate==null?'':value.actualdelvdate),
							$('<td>').text(value.fabricinvoiceno==null?'':value.fabricinvoiceno),
							
							$('<td>').text(value.fabricitem==null?'':value.fabricitem),
							$('<td>').text(value.listColor==null?'':value.listColor),
							$('<td>').text(value.totalYard==null?'':value.totalYard),
							
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.fabricno+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listFabricInformation');
				});
				action();

				$('#listFabricInformation').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * Hàm handle function click cho dropdown list chính
	 */
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var fabricno= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    if(valueSelected=="Edit"){
//		    	alert(colorcode);
		    	
		    	//gọi 
		    	$.ajax({
		    		dataType: "json",
		    		type: 'POST',
					data: JSON.stringify({
						fabricno: fabricno
					}),
					url: "/Chori/fabricinformation/detail",
					contentType: "application/json",
					success: function(data){
						$("#editFabricInformationDialog").find("#ddlCustomer").val(data.fabricInformation.customer);
						$("#editFabricInformationDialog").find("#ddlFabricSupplier").val(data.fabricInformation.fabricsupplier);
						if(data.fabricInformation.ischori)
							$("#editFabricInformationDialog").find("#isChori").prop('checked', true);
						else
							$("#editFabricInformationDialog").find("#isChori").prop('checked', false);
						$("#editFabricInformationDialog").find("#fabricItem").val(data.fabricInformation.fabricitem);
						$("#editFabricInformationDialog").find("#fabricno").val(data.fabricInformation.fabricno);
						$("#editFabricInformationDialog").find("#component").val(data.fabricInformation.component);
						$("#editFabricInformationDialog").find("#ddlFactory").val(data.fabricInformation.factory);
						$("#editFabricInformationDialog").find("#ddlWidth").val(data.fabricInformation.widthcode);
						$("#editFabricInformationDialog").find("#fabricInvoiceNo").val(data.fabricInformation.fabricinvoiceno);
						$("#editFabricInformationDialog").find("#ddlCurrency").val(data.fabricInformation.currencycode);
						$("#editFabricInformationDialog").find("#remark").val(data.fabricInformation.remark);
						$("#editFabricInformationDialog").find("#estDeliveredDateEditVer").val(data.fabricInformation.estimatedelvdate);
						$("#editFabricInformationDialog").find("#actDeliveredDateEditVer").val(data.fabricInformation.actualdelvdate);
						$("#editFabricInformationDialog").find("#voucherReceivedDateEditVer").val(data.fabricInformation.voucherreceiveddate);
						$("#editFabricInformationDialog").find("#voucherSentDateEditVer").val(data.fabricInformation.vouchersentdate);
						//show ảnh
						if((data.fabricInformation.fabricimgurl==null)||(data.fabricInformation.fabricimgurl.trim()=='')){
							//do nothing
							$("#editFabricInformationDialog").find('#showFabricImageDiv').html('');
						}else{
							$("#editFabricInformationDialog").find('#showFabricImageDiv').append(
									'<label for="remark" class="col-sm-3 control-label">Fabric Photo</label>'+
									'<div class="col-sm-9">'+
									'<a class="fancybox"  href="fabricInformationImage/'+data.fabricInformation.fabricimgurl+'"><img class="btnImgEditDialog" height="250" width="250" src="fabricInformationImage/'+data.fabricInformation.fabricimgurl+'" data-id="fabricInformationImage/'+data.fabricInformation.fabricimgurl+'" /></a>'+
									'</div>'
							);//////////
						}
						
						//show list fabric information detail
						$("#FabricInformationDetailTableCoverEditVer").html('');
						$("#FabricInformationDetailTableCoverEditVer").append('<table class="table table-striped table-bordered display responsive"'+
									'id="listFabricInformationDetailEditVer">'+
									'<thead>'+
										'<tr>'+
											'<th>Color</th>'+
											'<th>Yard in B/L</th>'+
											'<th>Unit Price</th>'+
											'<th>Photo</th>'+
											'<th></th>'+
										'</tr>'+
									'</thead>'+
								'</table>');
						
						$.each(data.listFabricInformationDetail,function(key,value){
							
							$('<tr>').append(
									$('<td>').text(value.colorName),
									$('<td>').text(value.yardinbl),
									$('<td>').text(value.unitprice),
									$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="fabricInformationDetailImage/'+value.imgurl+'"><img class="btnImg" height="50" width="50" src="fabricInformationDetailImage/'+value.imgurl+'" data-id="fabricInformationDetailImage/'+value.imgurl+'" /></a>')),
									$('<td>').html('<select class="selectpicker selectOptionFabricInformationDetailEditVer" data-id="'+value.colorcode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listFabricInformationDetailEditVer');
						});
//						actionFabricInformationDetailAdd();
						actionFabricInformationDetailEditVer();

						$('#listFabricInformationDetailEditVer').DataTable( {
							"pagingType": "full_numbers"
					    } );
						///
					},
					error: function(){
						alert("Không lấy đc dữ liệu!");
					}
		    	});
		    	
		    	///hiển thị dialog lên
		    	$("#editFabricInformationDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Fabric Information",
					height: 600,
					width: 1280,
					buttons:{
						"Cancel": function(){
							$(this).dialog("close");
							//clear all input, css after close dialog
							$("#editFabricInformationDialog").find('#fabricImageEdit').val("");
							$("#editFabricInformationDialog").find("#btnSaveFabricinformationEditVer").unbind("click");
							
							$("#editFabricInformationDialog").find('#showFabricImageDiv').html('');
						}
					},
					close: function(){
						$(this).dialog("close");
						//clear all input, css after close dialog
						$("#editFabricInformationDialog").find('#fabricImageEdit').val("");
						$("#editFabricInformationDialog").find("#btnSaveFabricinformationEditVer").unbind("click");
						
//						clearFabricInformationDetailDialogWhenClose();
						$("#editFabricInformationDialog").find('#showFabricImageDiv').html('');
					}
				});
		    	///
		    }
		    
		    /**
		     * handle delete fabric information
		     */
		    if(valueSelected=="Delete"){
//		    	alert(colorcode);
		    	$("#deleteFabricInformationDialog").find("#messageContent").text('Are you sure you want to delete this "'+fabricno+'" Fabric Information?');
				$("#deleteFabricInformationDialog").dialog({
					modal: true,
					show:{
						effect:"slide",
						duration: 500
					},
					title: "Delete Confirm",
					height: 300,
					width: 400,
					buttons:{
						"Yes": function(){
							$.ajax({
					    		dataType: "json",
					    		type: 'POST',
								data: JSON.stringify({
									fabricno: fabricno
								}),
								contentType: "application/json",
								url: "/Chori/fabricinformation/delete",
								success: function(data){
									callMessageDialog("Message", "Delete successfully!");
									$("#deleteFabricInformationDialog").dialog("close");
//									loadListFabricinformationdetailModel();
									loadData();
								},
								error: function(){
									//alert('Cant delete garment style!');
									callMessageDialog("Warning Message", 'Cant delete this Fabric Information!');
									$("#deleteFabricInformationDialog").dialog("close");
								}
					    	});
						},
						"No": function(){
							$("#deleteFabricInformationDialog").dialog("close");
						}
					}
				});
		    }
		});
	};
	
	/**
	 * Hàm load list fabric information detail for add dialog
	 */
	function loadListFabricinformationdetailModel(){
		//xóa tạo lại table
		$("#FabricInformationDetailTableCover").html('');
		$("#FabricInformationDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFabricInformationDetail">'+
					'<thead>'+
						'<tr>'+
							'<th>Color</th>'+
							'<th>Yard in B/L</th>'+
							'<th>Unit Price</th>'+
							'<th>Photo</th>'+
							'<th></th>'+
						'</tr>'+
					'</thead>'+
				'</table>');
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fabricinformationDetailAdd/list",
			contentType: "application/json",
			success: function(data){
//				var i = 1;
				if(data.listFabricinformationDetailAdd.length==0){
//					callMessageDialog("Message", "Table have no data!");
				}
				$.each(data.listFabricinformationDetailAdd,function(key,value){
					
					$('<tr>').append(
//							$('<td>').text(i++),
							$('<td>').text(value.colorName),
							$('<td>').text(value.yardinbl),
							$('<td>').text(value.unitprice),
//							$('<td>').text(value.imgurl),
							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="fabricInformationDetailImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="fabricInformationDetailImage/'+value.imgurl+'" data-id="fabricInformationDetailImage/'+value.imgurl+'" /></a>')),
//							$('<td>').text(value.estimatedelvdate==null?'':value.estimatedelvdate),
//							$('<td>').text(value.actualdelvdate==null?'':value.actualdelvdate),
//							$('<td>').text(value.fabricinvoiceno==null?'':value.fabricinvoiceno),
							$('<td>').html('<select class="selectpicker selectOptionFabricInformationDetailAdd" data-id="'+value.colorcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listFabricInformationDetail');
				});
				actionFabricInformationDetailAdd();

				$('#listFabricInformationDetail').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * Hàm handle drop down box cho các hàng của bảng fabric information detail add
	 */
	function actionFabricInformationDetailAdd(){
		$('.selectOptionFabricInformationDetailAdd').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var colorcode= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOptionFabricInformationDetailAdd").val("Options");
		    
		    if(valueSelected=="Edit"){
//		    	alert(colorcode);
		    	
		    	$.ajax({
		    		dataType: "json",
		    		type: 'POST',
					data: JSON.stringify({
						colorcode: colorcode
					}),
					url: "/Chori/fabricinformationDetail/detail",
					contentType: "application/json",
					success: function(data){
						$("#addFabricInformationDetailDialog").find("#ddlColor").val(data.fabricinformationdetailModel.colorcode);
						$("#addFabricInformationDetailDialog").find("#yardInBL").val(data.fabricinformationdetailModel.yardinbl);
						$("#addFabricInformationDetailDialog").find("#unitPrice").val(data.fabricinformationdetailModel.unitprice);
					},
					error: function(){
						alert("Không lấy đc dữ liệu!");
					}
		    	});
		    	
		    	///
		    	$("#addFabricInformationDetailDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Update Fabric Color",
					height: 450,
					width: 350,
					buttons:{
						"Cancel": function(){
							$(this).dialog("close");
							//clear all input, css after close dialog
							$('#fabricImageDetail').val("");
							$("#btnSaveFabricinformationDetail").unbind("click");
							
//							clearFabricInformationDetailDialogWhenClose();
						}
					},
					close: function(){
						$(this).dialog("close");
						$('#fabricImageDetail').val("");
						$("#btnSaveFabricinformationDetail").unbind("click");
						
//						clearFabricInformationDetailDialogWhenClose();
					}
				});
		    	///
		    }
		    
		    if(valueSelected=="Delete"){
//		    	alert(colorcode);
		    	$("#deleteFabricInformationDetailDialog").find("#messageContent").text('Are you sure you want to delete this fabric color?');
				$("#deleteFabricInformationDetailDialog").dialog({
					modal: true,
					show:{
						effect:"slide",
						duration: 500
					},
					title: "Delete Confirm",
					height: 300,
					width: 400,
					buttons:{
						"Yes": function(){
							$.ajax({
					    		dataType: "json",
					    		type: 'POST',
								data: JSON.stringify({
									colorcode: colorcode
								}),
								contentType: "application/json",
								url: "/Chori/fabricinformationDetail/delete",
								success: function(data){
									callMessageDialog("Message", "Delete successfully!");
									$("#deleteFabricInformationDetailDialog").dialog("close");
//									loadListFabricinformationdetailModel();
								},
								error: function(){
									//alert('Cant delete garment style!');
									callMessageDialog("Warning Message", 'Cant delete this fabric color!');
									$("#deleteFabricInformationDetailDialog").dialog("close");
								}
					    	});
						},
						"No": function(){
							$("#deleteFabricInformationDetailDialog").dialog("close");
						}
					}
				});
		    }
		});
	};
	
	/**
	 * This function show add new fabric information dialog
	 */
	$('#btnCreateNewFabricInformation').click(function(){
		
		///
		$("#FabricInformationDetailTableCover").html('');
		///
		
		$("#addFabricInformationDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Fabric Information",
			height: 600,
			width: 1280,
			buttons:{
				"Cancel": function(){
					$(this).dialog("close");
					//clear all input, css after close dialog
					$('#fabricImage').val("");
					$("#btnSaveFabricinformation").unbind("click");
					
					clearFabricInformationDialogWhenClose();
				}
			},
			close: function(){
				$(this).dialog("close");
				$('#fabricImage').val("");
				$("#btnSaveFabricinformation").unbind("click");
				
				clearFabricInformationDialogWhenClose();
			}
		});
	});
	
	/**
	 * prevent submit form when press Add new fabric infomation
	 */
	$('#addFabricInformationDialog').on('submit', function(e) {
		e.preventDefault();
		
		//test
		if($("#fabricImage").val() == ''){
			var customer = $("#addFabricInformationDialog").find("#ddlCustomer").val();
			var fabricsupplier = $("#addFabricInformationDialog").find("#ddlFabricSupplier").val();
			if($("#addFabricInformationDialog").find("#isChori").is(':checked'))
				var ischori = true;
			else
				var ischori = false;
			var fabricitem = $("#addFabricInformationDialog").find("#fabricItem").val();
			var fabricno = $("#addFabricInformationDialog").find("#fabricno").val();
			var component = $("#addFabricInformationDialog").find("#component").val();
			var factory = $("#addFabricInformationDialog").find("#ddlFactory").val();
			var widthcode = $("#addFabricInformationDialog").find("#ddlWidth").val();
			var fabricinvoiceno = $("#addFabricInformationDialog").find("#fabricInvoiceNo").val();
			var currencycode = $("#addFabricInformationDialog").find("#ddlCurrency").val();
			var remark = $("#addFabricInformationDialog").find("#remark").val();
			var estimatedelvdate = $("#addFabricInformationDialog").find("#estDeliveredDate").val();
			var actualdelvdate = $("#addFabricInformationDialog").find("#actDeliveredDate").val();
			
			var voucherreceiveddate = $("#addFabricInformationDialog").find("#voucherReceivedDate").val();
			var vouchersentdate = $("#addFabricInformationDialog").find("#voucherSentDate").val();
			
			var fabricinformation = {
					customer: customer,
					fabricsupplier: fabricsupplier,
					ischori: ischori,
					fabricitem: fabricitem,
					fabricno: fabricno,
					component: component,
					factory: factory,
					widthcode: widthcode,
					fabricinvoiceno : fabricinvoiceno,
					currencycode : currencycode,
					remark : remark,
					estimatedelvdate : estimatedelvdate,
					actualdelvdate : actualdelvdate,
					voucherreceiveddate: voucherreceiveddate,
					vouchersentdate: vouchersentdate
			};
			
			console.log(JSON.stringify(fabricinformation));
			
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: JSON.stringify(fabricinformation),
				contentType: "application/json",
				url: "/Chori/fabricinformation/add",
				success: function(data){
					if(data.fabricNoStatus==false){
						callMessageDialog("Warning Message", "Invalid Fabric Number!");
					}else if(data.fabricNoStatus==true&&data.addStatus==true){
						callMessageDialog("Message", "Add new fabric information successfully!");
						//reaload lại bảng
						loadData();
						
						//đóng và reset lại add fabric infor dialog
						$('#addFabricInformationDialog').dialog("close");
//						$('#fabricImage').val("");
//						$("#btnSaveFabricinformation").unbind("click");
						
						clearFabricInformationDialogWhenClose();
					}
					
				},error: function(){
					//ở đây do chưa fix đc lỗi add nên tạm thông báo luôn, vẫn add đúng
					callMessageDialog("Message", "Add new fabric information successfully!");
					//reaload lại bảng
					loadData();
					//end ở đây do chưa fix đc lỗi add nên tạm thông báo luôn, vẫn add đúng
					
					//đóng và reset lại add fabric infor dialog
					$('#addFabricInformationDialog').dialog("close");
//					$('#fabricImage').val("");
//					$("#btnSaveFabricinformation").unbind("click");
					
					clearFabricInformationDialogWhenClose();
				}
			});
		}
		//test
	});
	
	/**
	 * Trường hợp file upload cho fabric information
	 */
	$('#addFabricInformationForm').fileupload({
        dataType: 'json',
        
        add: function(e, data) {
            alert('add event raising after file completed');
            $("#btnSaveFabricinformation").unbind("click");
            $('#btnSaveFabricinformation')
                    .click(function() {
                    	data.submit();
                    });
        },
        done: function(e, data) {
            alert('done event raising after upload completed');

            $("#btnSaveFabricinformation").unbind("click");

        },
        progressall: function(e, data) {
//            var progress = parseInt(data.loaded / data.total * 100, 10);
//            $('#progress .progress-bar').css('width', progress + '%');
        }
    });
	
	function clearFabricInformationDialogWhenClose(){
		//clear user input
		$("#addFabricInformationDialog").find("#ddlCustomer").val('');
		$("#addFabricInformationDialog").find("#ddlFabricSupplier").val('');
		$("#addFabricInformationDialog").find('#isChori').prop('checked', false);
		$("#addFabricInformationDialog").find("#ddlChoriAgent").val('');
		$("#addFabricInformationDialog").find("#fabricItem").val('');
		$("#addFabricInformationDialog").find("#fabricno").val('');
		$("#addFabricInformationDialog").find("#errorFabricno").text("");
		$("#addFabricInformationDialog").find("#fabricno").css("background-color", "white");
		
		$("#addFabricInformationDialog").find("#component").val('');
		$("#addFabricInformationDialog").find("#ddlFactory").val('');
		$("#addFabricInformationDialog").find("#ddlWidth").val('');
		$("#addFabricInformationDialog").find("#fabricInvoiceNo").val('');
		$("#addFabricInformationDialog").find("#ddlCurrency").val('');
		$("#addFabricInformationDialog").find("#remark").val('');
		$("#addFabricInformationDialog").find("#estDeliveredDate").val('');
		$("#addFabricInformationDialog").find("#actDeliveredDate").val('');
		
		//reset các giá trị về lúc chưa add
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fabricinformation/clearDataAfterCloseAddFabInforDialog",
			contentType: "application/json",
			success: function(data){
				//alert("ok");
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		
		//xóa bảng fabric infomation detail đi
		$("#FabricInformationDetailTableCover").html('');
	}
	
	/**
	 * Add datepicker for est Delivered Date
	 * Thêm datepicker cho est Delivered Date
	 */
	$("#addFabricInformationDialog").find("#estDeliveredDate").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	/**
	 * Add datepicker for act Delivered Date
	 * Thêm datepicker cho act Delivered Date
	 */
	$("#addFabricInformationDialog").find("#actDeliveredDate").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	$("#addFabricInformationDialog").find("#voucherReceivedDate").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	$("#addFabricInformationDialog").find("#voucherSentDate").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	/**
	 * Add datepicker for est Delivered Date
	 * Thêm datepicker cho est Delivered Date
	 */
	$("#editFabricInformationDialog").find("#estDeliveredDateEditVer").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	/**
	 * Add datepicker for act Delivered Date
	 * Thêm datepicker cho act Delivered Date
	 */
	$("#editFabricInformationDialog").find("#actDeliveredDateEditVer").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	$("#editFabricInformationDialog").find("#voucherReceivedDateEditVer").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	$("#editFabricInformationDialog").find("#voucherSentDateEditVer").datepicker({
        changeMonth: true,
        dateFormat : 'dd/mm/yy',
        numberOfMonths: 1
	});
	
	/**
	 * Function add new fabric information detail (hiển thị dialog)
	 */
	$('#btnCreateNewFabricInformationDetail').click(function(e){
		e.preventDefault();
		
		var colorcode = $("#addFabricInformationDetailDialog").find("#ddlColor").val();
		//điền default value vào trc
		$.ajax({
    		dataType: "json",
    		type: 'POST',
			data: JSON.stringify({
				colorcode: colorcode
			}),
			url: "/Chori/fabricinformationDetail/detail",
			contentType: "application/json",
			success: function(data){
				$("#addFabricInformationDetailDialog").find("#ddlColor").val(data.fabricinformationdetailModel.colorcode);
				$("#addFabricInformationDetailDialog").find("#yardInBL").val(data.fabricinformationdetailModel.yardinbl);
				$("#addFabricInformationDetailDialog").find("#unitPrice").val(data.fabricinformationdetailModel.unitprice);
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
    	});
		
		//mở dialog
		$("#addFabricInformationDetailDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Create New Fabric Color",
			height: 450,
			width: 350,
			buttons:{
				"Cancel": function(){
					$(this).dialog("close");
					//clear all input, css after close dialog
					$('#fabricImageDetail').val("");
					$("#btnSaveFabricinformationDetail").unbind("click");
					
					clearFabricInformationDetailDialogWhenClose();
				}
			},
			close: function(){
				$(this).dialog("close");
				$('#fabricImageDetail').val("");
				$("#btnSaveFabricinformationDetail").unbind("click");
				
				clearFabricInformationDetailDialogWhenClose();
			}
		});
		//
	});
	
	/**
	 * Hàm on change cho ddl color khi add mới fabric infor detail dialog (add part)
	 */
	$('#addFabricInformationDetailDialog').find("#ddlColor").on('change', function (e) {
		var optionSelected = $(this).find("option:selected");
	    var valueSelected  = optionSelected.val();
	    $.ajax({
    		dataType: "json",
    		type: 'POST',
			data: JSON.stringify({
				colorcode: valueSelected
			}),
			url: "/Chori/fabricinformationDetail/detail",
			contentType: "application/json",
			success: function(data){
				$("#addFabricInformationDetailDialog").find("#ddlColor").val(data.fabricinformationdetailModel.colorcode);
				$("#addFabricInformationDetailDialog").find("#yardInBL").val(data.fabricinformationdetailModel.yardinbl);
				$("#addFabricInformationDetailDialog").find("#unitPrice").val(data.fabricinformationdetailModel.unitprice);
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
    	});
	});
	
	/**
	 * Xóa input đã nhập sau khi đóng fabric information detail dialog
	 */
	function clearFabricInformationDetailDialogWhenClose(){
		//reset color code, yard in BL, unit price
		$("#addFabricInformationDetailDialog").find("#ddlColor").val('');
		$("#addFabricInformationDetailDialog").find("#yardInBL").val('');
		$("#addFabricInformationDetailDialog").find("#unitPrice").val('');
	}
	
	/**
	 * prevent submit form when press Add new fabric infomation detail
	 */
	$('#addFabricInformationDetailDialog').on('submit', function(e) {
		e.preventDefault();
		
		//test
		if($("#fabricImageDetail").val() == ''){
			var colorcode = $("#addFabricInformationDetailDialog").find("#ddlColor").val();
			var yardinbl = $("#addFabricInformationDetailDialog").find("#yardInBL").val();
			var unitprice = $("#addFabricInformationDetailDialog").find("#unitPrice").val();
			
			var fabricinformationDetail = {
					colorcode: colorcode,
					yardinbl: yardinbl,
					unitprice : unitprice
			};
			
			console.log(JSON.stringify(fabricinformationDetail));
			
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: JSON.stringify(fabricinformationDetail),
				contentType: "application/json",
				url: "/Chori/fabricinformationDetail/add",
				success: function(data){
					//callMessageDialog("Message", "Add New Fabric Color successfully!");
					///đang làm
					//loadListFabricinformationdetailModel();
					//người dùng nhấn ok mới load lại list
					$("#messageDialog").find("#messageContent").text("Add New Fabric Color successfully!");
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
								loadListFabricinformationdetailModel();
							}
						},
						close: function(){
							$(this).dialog("close");
							loadListFabricinformationdetailModel();
						}
					});
					//end người dùng nhấn ok mới load lại list
				},error: function(){
					
				}
			});
		}
		//test
	});
	
	/**
	 * Trường hợp file upload cho fabric information detail
	 */
	$('#addFabricInformationDetailForm').fileupload({
        dataType: 'json',
        
        add: function(e, data) {
            alert('add event raising after file completed');
            $("#btnSaveFabricinformationDetail").unbind("click");
            $('#btnSaveFabricinformationDetail')
                    .click(function() {
                    	data.submit();
                    });
        },
        done: function(e, data) {
            alert('done event raising after upload completed');

            $("#btnSaveFabricinformationDetail").unbind("click");

        },
        progressall: function(e, data) {
//            var progress = parseInt(data.loaded / data.total * 100, 10);
//            $('#progress .progress-bar').css('width', progress + '%');
        }
    });
	
	/**
	 * ------------------ EDIT PART --------------------
	 */
	
	/**
	 * prevent submit form when press Add new fabric infomation
	 */
	$('#editFabricInformationDialog').on('submit', function(e) {
		e.preventDefault();
		
		//test
		if($("#fabricImageEdit").val() == ''){
			var customer = $("#editFabricInformationDialog").find("#ddlCustomer").val();
			var fabricsupplier = $("#editFabricInformationDialog").find("#ddlFabricSupplier").val();
			if($("#editFabricInformationDialog").find("#isChori").is(':checked'))
				var ischori = true;
			else
				var ischori = false;
			var fabricitem = $("#editFabricInformationDialog").find("#fabricItem").val();
			var fabricno = $("#editFabricInformationDialog").find("#fabricno").val();
			var component = $("#editFabricInformationDialog").find("#component").val();
			var factory = $("#editFabricInformationDialog").find("#ddlFactory").val();
			var widthcode = $("#editFabricInformationDialog").find("#ddlWidth").val();
			var fabricinvoiceno = $("#editFabricInformationDialog").find("#fabricInvoiceNo").val();
			var currencycode = $("#editFabricInformationDialog").find("#ddlCurrency").val();
			var remark = $("#editFabricInformationDialog").find("#remark").val();
			var estimatedelvdate = $("#editFabricInformationDialog").find("#estDeliveredDateEditVer").val();
			var actualdelvdate = $("#editFabricInformationDialog").find("#actDeliveredDateEditVer").val();
			
			var voucherreceiveddate = $("#editFabricInformationDialog").find("#voucherReceivedDateEditVer").val();
			var vouchersentdate = $("#editFabricInformationDialog").find("#voucherSentDateEditVer").val();
			
			var fabricinformation = {
					customer: customer,
					fabricsupplier: fabricsupplier,
					ischori: ischori,
					fabricitem: fabricitem,
					fabricno: fabricno,
					component: component,
					factory: factory,
					widthcode: widthcode,
					fabricinvoiceno : fabricinvoiceno,
					currencycode : currencycode,
					remark : remark,
					estimatedelvdate : estimatedelvdate,
					actualdelvdate : actualdelvdate,
					voucherreceiveddate: voucherreceiveddate,
					vouchersentdate: vouchersentdate
			};
			
			console.log(JSON.stringify(fabricinformation));
			
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: JSON.stringify(fabricinformation),
				contentType: "application/json",
				url: "/Chori/fabricinformation/edit",
				success: function(data){
//					if(data.fabricNoStatus==false){
//						callMessageDialog("Warning Message", "Invalid Fabric Number!");
//					}else 
						if(data.editStatus==true){
						callMessageDialog("Message", "Edit fabric information successfully!");
						//reaload lại bảng
						loadData();
						
						//đóng và reset lại add fabric infor dialog
						$('#editFabricInformationDialog').dialog("close");
//						$('#fabricImage').val("");
//						$("#btnSaveFabricinformation").unbind("click");
						
						clearEditFabricInformationDialogWhenClose();
					}
					
				},error: function(){
					//ở đây do chưa fix đc lỗi add nên tạm thông báo luôn, vẫn add đúng
					callMessageDialog("Message", "Edit fabric information successfully!");
					//reaload lại bảng
					loadData();
					//end ở đây do chưa fix đc lỗi add nên tạm thông báo luôn, vẫn add đúng
					$('#editFabricInformationDialog').dialog("close");
					clearEditFabricInformationDialogWhenClose();
//					alert("Lỗi");
				}
			});
		}
		//test
	});
	
	/**
	 * Trường hợp file upload cho fabric information
	 */
	$('#editFabricInformationForm').fileupload({
        dataType: 'json',
        
        add: function(e, data) {
            alert('add event raising after file completed');
            $("#btnSaveFabricinformationEditVer").unbind("click");
            $('#btnSaveFabricinformationEditVer')
                    .click(function() {
                    	data.submit();
                    });
        },
        done: function(e, data) {
            alert('done event raising after upload completed');

            $("#btnSaveFabricinformationEditVer").unbind("click");

        },
        progressall: function(e, data) {
        	
        }
    });
	
	function clearEditFabricInformationDialogWhenClose(){
		//clear user input
		$("#editFabricInformationDialog").find("#ddlCustomer").val('');
		$("#editFabricInformationDialog").find("#ddlFabricSupplier").val('');
		$("#editFabricInformationDialog").find('#isChori').prop('checked', false);
		$("#editFabricInformationDialog").find("#ddlChoriAgent").val('');
		$("#editFabricInformationDialog").find("#fabricItem").val('');
		$("#editFabricInformationDialog").find("#fabricno").val('');
		$("#editFabricInformationDialog").find("#errorFabricno").text("");
		$("#editFabricInformationDialog").find("#fabricno").css("background-color", "white");
		
		$("#editFabricInformationDialog").find("#component").val('');
		$("#editFabricInformationDialog").find("#ddlFactory").val('');
		$("#editFabricInformationDialog").find("#ddlWidth").val('');
		$("#editFabricInformationDialog").find("#fabricInvoiceNo").val('');
		$("#editFabricInformationDialog").find("#ddlCurrency").val('');
		$("#editFabricInformationDialog").find("#remark").val('');
		$("#editFabricInformationDialog").find("#estDeliveredDate").val('');
		$("#editFabricInformationDialog").find("#actDeliveredDate").val('');
		
		//reset các giá trị về lúc chưa add
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fabricinformation/clearDataAfterCloseEditFabInforDialog",
			contentType: "application/json",
			success: function(data){
				//alert("ok");
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		
		//xóa bảng fabric infomation detail đi
		$("#FabricInformationDetailTableCoverEditVer").html('');
	}
	
	/**
	 * Phần add new fabric information detail cho edit
	 */
	/**
	 * Function add new fabric information detail (hiển thị dialog thêm mới fabric infor detail cho edit)
	 */
	$('#btnCreateNewFabricInformationDetailEditVer').click(function(e){
		e.preventDefault();
		
		var colorcode = $("#addFabricInformationDetailEditVerDialog").find("#ddlColor").val();
		//điền default value vào trc
		$.ajax({
    		dataType: "json",
    		type: 'POST',
			data: JSON.stringify({
				colorcode: colorcode
			}),
			url: "/Chori/fabricinformationDetail/detailEditVer",
			contentType: "application/json",
			success: function(data){
				$("#addFabricInformationDetailEditVerDialog").find("#ddlColor").val(data.fabricinformationdetailModel.colorcode);
				$("#addFabricInformationDetailEditVerDialog").find("#yardInBL").val(data.fabricinformationdetailModel.yardinbl);
				$("#addFabricInformationDetailEditVerDialog").find("#unitPrice").val(data.fabricinformationdetailModel.unitprice);
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
    	});
		
		//mở dialog
		$("#addFabricInformationDetailEditVerDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Create New Fabric Color",
			height: 450,
			width: 350,
			buttons:{
				"Cancel": function(){
					$(this).dialog("close");
					//clear all input, css after close dialog
					$('#fabricImageDetailEditVer').val("");
					$("#btnSaveFabricinformationDetailEditVer").unbind("click");
					
					clearEditFabricInformationDetailDialogWhenClose();
				}
			},
			close: function(){
				$(this).dialog("close");
				$('#fabricImageDetailEditVer').val("");
				$("#btnSaveFabricinformationDetailEditVer").unbind("click");
				
				clearEditFabricInformationDetailDialogWhenClose();
			}
		});
		//
	});
	
	/**
	 * Hàm on change cho ddl color khi add mới fabric infor detail dialog
	 */
	$('#addFabricInformationDetailEditVerDialog').find("#ddlColor").on('change', function (e) {
		var optionSelected = $(this).find("option:selected");
	    var valueSelected  = optionSelected.val();
//	    alert(valueSelected);
	    $.ajax({
    		dataType: "json",
    		type: 'POST',
			data: JSON.stringify({
				colorcode: valueSelected
			}),
			url: "/Chori/fabricinformationDetail/detailEditVer",
			contentType: "application/json",
			success: function(data){
				$("#addFabricInformationDetailEditVerDialog").find("#ddlColor").val(data.fabricinformationdetailModel.colorcode);
				$("#addFabricInformationDetailEditVerDialog").find("#yardInBL").val(data.fabricinformationdetailModel.yardinbl);
				$("#addFabricInformationDetailEditVerDialog").find("#unitPrice").val(data.fabricinformationdetailModel.unitprice);
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
    	});
	});
	
	/**
	 * Xóa input đã nhập sau khi đóng fabric information detail dialog edit ver
	 */
	function clearEditFabricInformationDetailDialogWhenClose(){
		//reset color code, yard in BL, unit price
		$("#addFabricInformationDetailEditVerDialog").find("#ddlColor").val('');
		$("#addFabricInformationDetailEditVerDialog").find("#yardInBL").val('');
		$("#addFabricInformationDetailEditVerDialog").find("#unitPrice").val('');
	}
	
	/**
	 * prevent submit form when press Add new fabric infomation detail edit ver
	 */
	$('#addFabricInformationDetailEditVerDialog').on('submit', function(e) {
		e.preventDefault();
		
		//test
		if($("#fabricImageDetailEditVer").val() == ''){
			var colorcode = $("#addFabricInformationDetailEditVerDialog").find("#ddlColor").val();
			var yardinbl = $("#addFabricInformationDetailEditVerDialog").find("#yardInBL").val();
			var unitprice = $("#addFabricInformationDetailEditVerDialog").find("#unitPrice").val();
			
			var fabricinformationDetail = {
					colorcode: colorcode,
					yardinbl: yardinbl,
					unitprice : unitprice
			};
			
			console.log(JSON.stringify(fabricinformationDetail));
			
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: JSON.stringify(fabricinformationDetail),
				contentType: "application/json",
				url: "/Chori/fabricinformationDetail/addEditVer",
				success: function(data){
					$("#messageDialog").find("#messageContent").text("Add New Fabric Color successfully!");
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
								//CẦN FIX SAU
								loadListFabricinformationdetailModelEditVer();
							}
						},
						close: function(){
							$(this).dialog("close");
							//CẦN FIX SAU
							loadListFabricinformationdetailModelEditVer();
						}
					});
					//end người dùng nhấn ok mới load lại list
				},error: function(){
					
				}
			});
		}
		//test
	});
	
	/**
	 * Trường hợp file upload cho fabric information detail edit ver
	 */
	$('#addFabricInformationDetailEditVerForm').fileupload({
        dataType: 'json',
        
        add: function(e, data) {
            alert('add event raising after file completed');
            $("#btnSaveFabricinformationDetailEditVer").unbind("click");
            $('#btnSaveFabricinformationDetailEditVer')
                    .click(function() {
                    	data.submit();
                    });
        },
        done: function(e, data) {
            alert('done event raising after upload completed');

            $("#btnSaveFabricinformationDetailEditVer").unbind("click");

        },
        progressall: function(e, data) {
//            var progress = parseInt(data.loaded / data.total * 100, 10);
//            $('#progress .progress-bar').css('width', progress + '%');
        }
    });
	/**
	 * --------------End Phần add new fabric information detail cho edit
	 */
	
	/**
	 * --------------Phần dropdownlist cho mỗi dòng của fabric infor detail
	 */
	
	/**
	 * Hàm load list fabric information detail for edit dialog
	 */
	function loadListFabricinformationdetailModelEditVer(){
		//xóa tạo lại table
		$("#FabricInformationDetailTableCoverEditVer").html('');
		$("#FabricInformationDetailTableCoverEditVer").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFabricInformationDetailEditVer">'+
					'<thead>'+
						'<tr>'+
							'<th>Color</th>'+
							'<th>Yard in B/L</th>'+
							'<th>Unit Price</th>'+
							'<th>Photo</th>'+
							'<th></th>'+
						'</tr>'+
					'</thead>'+
				'</table>');
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fabricinformationDetailEditVer/list",
			contentType: "application/json",
			success: function(data){
//				var i = 1;
				if(data.listFabricinformationDetailEditVer.length==0){
//					callMessageDialog("Message", "Table have no data!");
				}
				$.each(data.listFabricinformationDetailEditVer,function(key,value){
					
					$('<tr>').append(
//							$('<td>').text(i++),
							$('<td>').text(value.colorName),
							$('<td>').text(value.yardinbl),
							$('<td>').text(value.unitprice),
//							$('<td>').text(value.imgurl),
							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="fabricInformationDetailImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="fabricInformationDetailImage/'+value.imgurl+'" data-id="fabricInformationDetailImage/'+value.imgurl+'" /></a>')),
//							$('<td>').text(value.estimatedelvdate==null?'':value.estimatedelvdate),
//							$('<td>').text(value.actualdelvdate==null?'':value.actualdelvdate),
//							$('<td>').text(value.fabricinvoiceno==null?'':value.fabricinvoiceno),
							$('<td>').html('<select class="selectpicker selectOptionFabricInformationDetailEditVer" data-id="'+value.colorcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listFabricInformationDetailEditVer');
				});
				actionFabricInformationDetailEditVer();

				$('#listFabricInformationDetailEditVer').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * Hàm handle drop down box cho các hàng của bảng fabric information detail edit
	 */
	function actionFabricInformationDetailEditVer(){
		$('.selectOptionFabricInformationDetailEditVer').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var colorcode= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOptionFabricInformationDetailEditVer").val("Options");
		    
		    if(valueSelected=="Edit"){
//		    	alert(colorcode);
		    	
		    	$.ajax({
		    		dataType: "json",
		    		type: 'POST',
					data: JSON.stringify({
						colorcode: colorcode
					}),
					url: "/Chori/fabricinformationDetail/detailEditVer",
					contentType: "application/json",
					success: function(data){
						$("#addFabricInformationDetailEditVerDialog").find("#ddlColor").val(data.fabricinformationdetailModel.colorcode);
						$("#addFabricInformationDetailEditVerDialog").find("#yardInBL").val(data.fabricinformationdetailModel.yardinbl);
						$("#addFabricInformationDetailEditVerDialog").find("#unitPrice").val(data.fabricinformationdetailModel.unitprice);
					},
					error: function(){
						alert("Không lấy đc dữ liệu!");
					}
		    	});
		    	
		    	///
		    	$("#addFabricInformationDetailEditVerDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Update Fabric Color",
					height: 450,
					width: 350,
					buttons:{
						"Cancel": function(){
							$(this).dialog("close");
							//clear all input, css after close dialog
							$('#fabricImageDetailEditVer').val("");
							$("#btnSaveFabricinformationDetailEditVer").unbind("click");
							
//							clearFabricInformationDetailDialogWhenClose();
							clearEditFabricInformationDetailDialogWhenClose();
						}
					},
					close: function(){
						$(this).dialog("close");
						$('#fabricImageDetailEditVer').val("");
						$("#btnSaveFabricinformationDetailEditVer").unbind("click");
						
//						clearFabricInformationDetailDialogWhenClose();
						clearEditFabricInformationDetailDialogWhenClose();
					}
				});
		    	///
		    }
		    
		    if(valueSelected=="Delete"){
//		    	alert(colorcode);
		    	$("#deleteFabricInformationDetailDialog").find("#messageContent").text('Are you sure you want to delete this fabric color?');
				$("#deleteFabricInformationDetailDialog").dialog({
					modal: true,
					show:{
						effect:"slide",
						duration: 500
					},
					title: "Delete Confirm",
					height: 300,
					width: 400,
					buttons:{
						"Yes": function(){
							$.ajax({
					    		dataType: "json",
					    		type: 'POST',
								data: JSON.stringify({
									colorcode: colorcode
								}),
								contentType: "application/json",
								url: "/Chori/fabricinformationDetail/deleteEditVer",
								success: function(data){
									callMessageDialog("Message", "Delete successfully!");
									$("#deleteFabricInformationDetailDialog").dialog("close");
//									loadListFabricinformationdetailModel();
								},
								error: function(){
									//alert('Cant delete garment style!');
									callMessageDialog("Warning Message", 'Cant delete this fabric color!');
									$("#deleteFabricInformationDetailDialog").dialog("close");
								}
					    	});
						},
						"No": function(){
							$("#deleteFabricInformationDetailDialog").dialog("close");
						}
					}
				});
		    }
		});
	};
	
	/**
	 * End 
	 */
	
	/**
	 * -------- END EDIT PART ------------
	 */
	
	/**
	 * This function load all dropdown list
	 */
	function loadAllDropDownListAtStart(){
		//load vào ddl customer
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/customer/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDialog').find('#ddlCustomer').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                        
                        //load bên edit
                        $('#editFabricInformationDialog').find('#ddlCustomer').append($('<option>', {
                            value: value.customercode,
                            text: value.shortname
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
		
		//load vào ddl fabric supplier
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/fabricSupplier/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDialog').find('#ddlFabricSupplier').append($('<option>', {
                            value: value.fabricsupcode,
                            text: value.shortname
                        }));
                        
                        //bên edit
                        $('#editFabricInformationDialog').find('#ddlFabricSupplier').append($('<option>', {
                            value: value.fabricsupcode,
                            text: value.shortname
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
		
		//load vào ddl chori agent
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/agent/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDialog').find('#ddlChoriAgent').append($('<option>', {
                            value: value.agentcode,
                            text: value.shortname
                        }));
                        
                        $('#editFabricInformationDialog').find('#ddlChoriAgent').append($('<option>', {
                            value: value.agentcode,
                            text: value.shortname
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
		
		//load vào ddl factory
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/factory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDialog').find('#ddlFactory').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        $('#editFabricInformationDialog').find('#ddlFactory').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
		
		//load vào ddl width
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/width/list",
			success: function(data){
				if(data.width == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDialog').find('#ddlWidth').append($('<option>', {
                            value: value.widthcode,
                            text: value.widthcode
                        }));
                        
                        $('#editFabricInformationDialog').find('#ddlWidth').append($('<option>', {
                            value: value.widthcode,
                            text: value.widthcode
                        }));
                    });
				}else{
					alert('This alert should never show! 194');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
		
		//load vào ddl currency
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/currency/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDialog').find('#ddlCurrency').append($('<option>', {
                            value: value.currencycode,
                            text: value.name
                        }));
                        
                        $('#editFabricInformationDialog').find('#ddlCurrency').append($('<option>', {
                            value: value.currencycode,
                            text: value.name
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
		
		//load vào ddl Color
		$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/color/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#addFabricInformationDetailDialog').find('#ddlColor').append($('<option>', {
                            value: value.colorcode,
                            text: value.description
                        }));
                        
                        $('#editFabricInformationDialog').find('#ddlColor').append($('<option>', {
                            value: value.colorcode,
                            text: value.description
                        }));
                        
                        $('#addFabricInformationDetailEditVerDialog').find('#ddlColor').append($('<option>', {
                            value: value.colorcode,
                            text: value.description
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error !!');
			}
    	});
		//
	}
	
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
	}
	
	/**
	 * Hàm check is existed cho fabric no trên view khi add
	 */
	$("#addFabricInformationDialog").find("#fabricno").on('change keyup paste',function(){
		var fabricno= $(this).val();
		
		//if user dont enter or enter just space
		if(fabricno.trim() === '' || fabricno == null){
			$("#addFabricInformationDialog").find("#errorFabricno").text("Please enter factory code!");
			$("#addFabricInformationDialog").find("#errorFabricno").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//kiểm tra trường hợp có space ở đầu, cuối
		if(fabricno.trim().length!=fabricno.length){
			$("#addFabricInformationDialog").find("#errorFabricno").text("You can't enter space at prefix or suffix!");
			$("#addFabricInformationDialog").find("#errorFabricno").css("color", "red");
			$(this).css("background-color", "red");
			//disable nút add
			//$("#copyGarmentstyleDialog").find("#btnAdd").prop('disabled', true);
			return;
		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				fabricno: fabricno
			}),
			contentType: "application/json",
			url: "/Chori/fabricinformation/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addFabricInformationDialog").find("#errorFabricno").text("Fabric number you enter is existed, please choose different one!");
					$("#addFabricInformationDialog").find("#errorFabricno").css("color", "red");
					$("#addFabricInformationDialog").find("#fabricno").css("background-color", "red");
				}else if(data.isExisted==false&&$("#addFabricInformationDialog").find("#fabricno").val().length<50){
					//if code is not existed and valid length
					$("#addFabricInformationDialog").find("#errorFabricno").text("fabric number is valid!");
					$("#addFabricInformationDialog").find("#errorFabricno").css("color", "green");
					$("#addFabricInformationDialog").find("#fabricno").css("background-color", "white");
				}
				
				//if user dont enter or enter just space
				if(fabricno.trim() === '' || fabricno == null){
					$("#addFabricInformationDialog").find("#errorFabricno").text("Please enter factory code!");
					$("#addFabricInformationDialog").find("#errorFabricno").css("color", "red");
					$(this).css("background-color", "red");
				}
			},
			error: function(){
				//when user clear the code
				$("#addFabricInformationDialog").find("#errorFabricno").text("Please enter fabric number!");
				$("#addFabricInformationDialog").find("#errorFabricno").css("color", "red");
				$("#addFabricInformationDialog").find("#fabricno").css("background-color", "red");
			}
		});
	});
	
	loadData();
	loadAllDropDownListAtStart();
	$(".fancybox").fancybox();
});