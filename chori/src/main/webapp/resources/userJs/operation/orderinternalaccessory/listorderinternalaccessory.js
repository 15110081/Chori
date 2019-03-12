$(document).ready(function(){
	/**
	 * --------------START: Load oderinternalaccessory list & set data to table ------------------
	 */
	function loadData(){
	//list
		$("#listOrderInternalAccessory").dataTable().fnDestroy();
		$('#listOrderInternalAccessory tbody').empty();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/orderinternalaccessory/list",
			contentType: "application/json",
			success: function(data){
				
				var i = 1;
				if(data.list.length==0){
					alert("Table have no data.");
				}
				$.each(data.list,function(key,value){
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.orderSheetNo),
							$('<td>').text(value.accsuplierName),
							$('<td>').text(value.factoryName),
						
							$('<td>').text(value.status),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.orderSheetNo+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option></select>')
									
									
					).appendTo('#listOrderInternalAccessory');
				});
				action();
				
				$('#listOrderInternalAccessory').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Error");
			}
		});
	}
	
	//load AccessorySupplier  to dropdownbox
	function loadDropDownAccessorySupplier(){
    	$.ajax({
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/accessorysupplier/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#ddlAccSupplierCode').append($('<option>', {
                            value: value.accessorysuppliercode,
                            text: value.shortname
                        }));
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error');
			}
    	});
    }
	//load Factory  to dropdownbox
	function loadDropDownFactory(){
    	$.ajax({
    		
    		dataType: "json",
			type: 'GET',
			data:{},
			contentType: "application/json",
			url: "/Chori/factory/list",
			success: function(data){
				if(data.status == "ok"){
					$.each( data.list, function( key, value ) {
                        $('#ddlFactoryCode').append($('<option>', {
                            value: value.factorycode,
                            text: value.shortname
                        }));
                        
                        
                    });
				}else{
					alert('This alert should never show!');
				}
			},
			error: function(){
				alert('Error');
			}
    	});
    }

	// datetimepicker Edit
	$(function() {	
		   var dateFormat = "dd/mm/yy",
		   estdeliverydate = $("#txtEstDeliveryDate")
		        .datepicker({
		          defaultDate: "+1w",
		          changeMonth: true,
		          dateFormat : 'dd/mm/yy',
		          numberOfMonths: 1
		        })
		        .on( "change", function() {
		        	actualdeliverydate.datepicker( "option", "minDate", getDate( this ) );
		        }),
		       actualdeliverydate = $("#txtActualDeliveryDate").datepicker({
		        defaultDate: "+1w",
		        changeMonth: true,
		        dateFormat : 'dd/mm/yy',
		        numberOfMonths: 1
		      }),
		      
		      orderdate = $("#txtOrderDate").datepicker({
		    	  changeMonth: true,
			        dateFormat : 'dd/mm/yy',
			        numberOfMonths: 1
		      })
		      

		 
		    function getDate( element ) {
		      var date;
		      try {
		        date = $.datepicker.parseDate( dateFormat, element.value );
		      } catch( error ) {
		        date = null;
		      }
		 
		      return date;
		    }
		  });

	var finalAccessoryDetailList = [];
	//when click button create new order internal accessory
	function action(){
		$('#btnCreateOrInternalAcc').on('click', function (e) {
			$(window).attr('location','createorderinternalaccessory')
		});
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    var orderSheetNo= $(this).data('id');//this is RoleID of each record.
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $("#dialogEditOrderInt").find("#errorTxtDescription").text("");
			$("#dialogEditOrderInt").find("#txtDescription").css("border-color", "");
		    $(".selectOption").val("Options");
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	
				$.ajax({
		    		dataType: "json",
					type: 'GET',
					data:{},
					contentType: "application/json",
					url: "/Chori/orderinternalaccessory/detail/"+orderSheetNo,
					success: function(data){
						//if load detail success, then open edit dialog
						if(data.status== "ok"){
							
							$('#dialogEditOrderInt').find('#ddlAccSupplierCode').val(data.currentorderinternalaccessory.accsuplierCode);
							$('#dialogEditOrderInt').find('#ddlFactoryCode').val(data.currentorderinternalaccessory.factoryCode);
							$('#dialogEditOrderInt').find('#txtInvoiceNumber').val(data.currentorderinternalaccessory.invoiceNumber);
							$('#dialogEditOrderInt').find('#txtOrderSheetNo').val(data.currentorderinternalaccessory.orderSheetNo);
							$('#dialogEditOrderInt').find('#ddlStatus').val(data.currentorderinternalaccessory.status);
							$('#dialogEditOrderInt').find('#txtOrderDate').val(data.currentorderinternalaccessory.orderDate);
							$('#dialogEditOrderInt').find('#txtEstDeliveryDate').val(data.currentorderinternalaccessory.estimatedevlDate);	
							$('#dialogEditOrderInt').find('#txtActualDeliveryDate').val(data.currentorderinternalaccessory.actualdevlDate);
							$('#dialogEditOrderInt').find('#areaRemak').val(data.currentorderinternalaccessory.remark);
							
						}else{
							alert('This alert should never show!');
						}
						
						//phần load dữ liệu cho bảng
						$("#OrderInternalAccessoryDetailTableCoverEditVer").html('');
						$("#OrderInternalAccessoryDetailTableCoverEditVer").append('<table class="table table-striped table-bordered display responsive"'+
									'id="listOrderInternalDetail">'+
									'<thead>'+
									'<h4 align="center"> Order Accessories </h4> ' + 
										'<tr>'+
											'<th>Acessories name </th>'+
											'<th>Code</th>'+
											'<th>Order qty</th>'+
											'<th>Act delv qty</th>'+
											'<th>Unit</th>'+
											'<th>Unit Price</th>'+
											'<th>Refer Unit Price</th>'+
											'<th>Price</th>'+
											'<th>Currency</th>'+
											'<th>Photo</th>'+
										'</tr>'+
									'</thead>'+
								'</table>');
						

								if(data.listOrderInternalAccessoryDetail.length==0){

								}
								$.each(data.listOrderInternalAccessoryDetail,function(key,value){
									$('<tr>').append(
											$('<td>').text(value.accessoryname),
											$('<td>').text(value.accessorycode),
											$('<td>').html('<input type="number" min="1" class="form-control" id="txtOrderQty' + value.accessorycode +'" name="txtOrderQty" value="'+(value.orderquantity==null?"":value.orderquantity)+'">'),
											$('<td>').html('<input type="number" min="0" class="form-control" id="txtActDelvQty' + value.accessorycode +'" name="txtActDelvQty" value="'+(value.actualdelvquantity==null?"0":value.actualdelvquantity)+'">'),
											$('<td>').text(value.unit),
											$('<td>').html('<input type="number" min="1" class="form-control" id="txtUnitPrice' + value.accessorycode +'" name="txtUnitPrice" value="'+value.unitprice+'" >'),
											$('<td>').text(value.unitprice==null?"":value.unitprice),
											$('<td>').html('<label  id="txtPrice' + value.accessorycode +'"  >'+(value.price==null?"":value.price)+'</label>'),
											$('<td>').text(value.currency==null?"":value.currency),
											$('<td>').html(((value.image==null)||(value.image.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.image+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.image+'" data-id="choriAccessoryImage/'+value.image+'" /></a>'))
									).appendTo('#listOrderInternalDetail');	
									

									/**
									 * Allow input numeric only in column OrderQty form
									 */
									
									 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCoverEditVer").on('keydown', '#txtOrderQty'+value.accessorycode,
												function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
											||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
											||35<=e.keyCode&&40>=e.keyCode
											||(e.shiftKey||48>e.keyCode||57<e.keyCode)
											&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
									 
									 /**
										 * Allow input numeric only in column ActDelvQty form
										 */
									 
									 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCoverEditVer").on('keydown', '#txtActDelvQty'+value.accessorycode,
												function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
											||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
											||35<=e.keyCode&&40>=e.keyCode
											||(e.shiftKey||48>e.keyCode||57<e.keyCode)	
											&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
									 
									 /**
										 * Allow input numeric only in column UnitPrice form
										 */
									 
									 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCoverEditVer").on('keydown', '#txtUnitPrice'+value.accessorycode,
												function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
											||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
											||35<=e.keyCode&&40>=e.keyCode
											||(e.shiftKey||48>e.keyCode||57<e.keyCode)	
											&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
										

									//event for change orderQty value
										$("#txtOrderQty"+value.accessorycode+"").on('input propertychange paste', function (e) {
											var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
											var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
											var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
											if(actDelvQty==0)
												$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
											else
												$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
										})
										
										$("#txtActDelvQty"+value.accessorycode+"").on('input propertychange paste', function (e) {
											var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
											var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
											var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
											if(actDelvQty==0)
												$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
											else
												$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
										})
										
										//event for change unitPrice value
										$("#txtUnitPrice"+value.accessorycode+"").on('input propertychange paste', function (e) {
											var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
											var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
											var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
											if(actDelvQty==0)
												$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
											else
												$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
										})
									
									//mới add để lưu code
									finalAccessoryDetailList.push(value.accessorycode);
									finalAccessoryDetailList = finalAccessoryDetailList.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
									
									
								});

								$('#listOrderInternalDetail').DataTable( {
									"pagingType": "full_numbers"
							    } );
//							},
//							error: function(){
//								alert("Error");
//							}
//						});
						//end phần load dữ liệu cho bảng
					},
					error: function(){
						alert('Cant get role detail!');
					}
		    	});
				$("#dialogEditOrderInt").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Oder General Accessory",
					height: 600,
					width: 1280,
					buttons:{
						//đang làm
						"Send Mail": function(){
							$(window).attr('location','sendemailOrderGeneralAccessory?orderSheetNo='+orderSheetNo);

						},
						"Save": function(){	
							///start
							var accessorysuplier=$("#ddlAccSupplierCode").val();
							var factory=$("#ddlFactoryCode").val();
							var ordersheetno=$("#txtOrderSheetNo").val();
							var invoicenumber=$("#txtInvoiceNumber").val();
							
							var remark=$("#areaRemak").val();
							var status=$("#ddlStatus").val();
							var validateOrderQuantityStatus = true;
							//////////////////////////////
							var lstOrderInternalAccessoryDetailModel = [];
							
							$.each(finalAccessoryDetailList,function(key,value){
								if($("#txtOrderQty"+value+"").val() <=0)
								{
									callMessageDialog("Message", "Order Quantity must be greater than 0");
									validateOrderQuantityStatus = false;
								}
								if($("#txtUnitPrice"+value+"").val() <=0)
								{
									callMessageDialog("Message", "Unit Price must be greater than 0");
									validateOrderQuantityStatus = false;
								}
								
								
								lstOrderInternalAccessoryDetailModel.push({
									accessorycode: value,
									orderquantity: $("#txtOrderQty"+value+"").val(),
									unitprice: $("#txtUnitPrice"+value+"").val(),
									price: $("#txtPrice"+value+"").text(),
									actualdelvquantity: $("#txtActDelvQty"+value+"").val()
								});
							});
//							console.log(lstOrderInternalAccessoryDetailModel);
							
							console.log({
								orderSheetNo:ordersheetno,
								accsuplierCode:accessorysuplier,
								factoryCode:factory,
								orderDate:$("#txtOrderDate").datepicker('getDate'),
								estimatedevlDate:$("#txtEstDeliveryDate").datepicker('getDate'),
								actualdevlDate:$("#txtActualDeliveryDate").datepicker('getDate'),
								status:status,
								remark:remark,
								invoiceNumber:invoicenumber,
								lstOrderInternalAccessoryDetailModel: lstOrderInternalAccessoryDetailModel
							});
							
							if(!compareEstVsActualDay()){
								callMessageDialog("Message", "Actual Delivery Date can't smaller than Est Delivery Date ");
							}
							else{
								
								if(validateOrderQuantityStatus == true){
							//ajax add
							$.ajax({
					    		dataType: "json",
								type: 'POST',
								data:
									JSON.stringify({
										orderSheetNo:ordersheetno,
										accsuplierCode:accessorysuplier,
										factoryCode:factory,
										orderDate:$("#txtOrderDate").datepicker('getDate'),
										estimatedevlDate:$("#txtEstDeliveryDate").datepicker('getDate'),
										actualdevlDate:$("#txtActualDeliveryDate").datepicker('getDate'),
										status:status,
										remark:remark,
										invoiceNumber:invoicenumber,
										lstOrderInternalAccessoryDetailModel: lstOrderInternalAccessoryDetailModel
									}),
								contentType: "application/json",
								url: "/Chori/orderinternalaccessory/edit",
								success: function(data){
									callMessageDialog("Message", "Edit Order Successfully!");
									$("#dialogEditOrderInt").dialog("close");
									loadData();
									//clear list detail sau khi edit
									$.ajax({
										dataType: "json",
										type: 'GET',
										data: {},
										url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
										contentType: "application/json",
										success: function(data){
											
										},
										error: function(){
											alert("Can't get data");
										}
									});
									//end clear list detail sau khi edit
								},
								error: function(){
//									addFailedMessageDialog();
									alert("edit fail");
								}
					    	});
							}
							}
							///end
						},
						"Cancel": function(){
							$("#dialogEditOrderInt").dialog("close");
							//do ko edit nên clear list detail
							$.ajax({
								dataType: "json",
								type: 'GET',
								data: {},
								url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
								contentType: "application/json",
								success: function(data){
									
								},
								error: function(){
									alert("Can't get data");
								}
							});
							//end do ko edit nên clear list detail
						},
						"Cancel Order": function(){
							var ordersheetno=$("#txtOrderSheetNo").val();
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									orderSheetNo:ordersheetno
								}),
								url: "/Chori/orderinternalaccessory/cancelOrder",
								contentType: "application/json",
								success: function(data){
									callMessageDialog("Message", "Cancel Order Successfully!");
									$("#dialogEditOrderInt").dialog("close");
									loadData();
								},
								error: function(){
									alert("Can't get data");
								}
							});
						}
					}
				});
		    };
		});
	}
	
	//Hàm load list accessory
	//Assign Accessory
	var AccessoryCodeArray = [];
	$('#btnAdd').on('click',function(){
		var accessorysuppliercode = $("#dialogEditOrderInt").find("#ddlAccSupplierCode").val();
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/orderinternalaccessory/listaccessoryEditVer/"+accessorysuppliercode,
			contentType: "application/json",
			success: function(data){
				$("#listAccessory").dataTable().fnDestroy();
				$('#listAccessory tbody').empty();
				var i = 1;
				if(data.list.length==0){
//						alert("Table have no data..");
				}
				$.each(data.list,function(key,value){
					AccessoryCodeArray.push(value.accessorycode);
					$('<tr>').append(
							value.checkedForOrderInternal==true?//xem xem có ở bảng dưới ko? nếu có mới check
							$('<td>').html('<input type="checkbox" name="chkListAcc" id="chk'+value.accessorycode+'" value="'+value.accessorycode+'" checked/>'):
								$('<td>').html('<input type="checkbox" name="chkListAcc" id="chk'+value.accessorycode+'" value="'+value.accessorycode+'" />'),
							$('<td>').text(i++),
							$('<td>').text(value.name),							
							$('<td>').text(value.accessorycode)
							
					).appendTo('#listAccessory');
					
				});
				$('#listAccessory').DataTable( {
					"pagingType": "full_numbers"
			    });
			},
			error: function(){
				alert("Can not get data!");
			}
		
		});
		
		$("#dialogOrderInternalAccessoryDetailEditVer").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Internal Accessories ",
			height: 500,
			width: 1200,
			buttons:{
				"Save": function(){
					var accessoryArray_isChecked=[];
					var accessoryArray_isUnChecked=[];
					
					//delete duplicate accessory code in array
					AccessoryCodeArray = AccessoryCodeArray.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
					//to know which checkbox is checked and push its accessory code to array
					for(var i =0;i<AccessoryCodeArray.length;i++)
					{
				        if($("#chk"+AccessoryCodeArray[i]+"").is(":checked")) {
				        	accessoryArray_isChecked.push({
				        		accessorycode:AccessoryCodeArray[i],
				        		accessorysuppliercode: $("#ddlAccSupplierCode").val(),
				        		orderDate: $("#txtOrderDate").val()
				        	});

					    }
				        else if(!$("#chk"+AccessoryCodeArray[i]+"").is(":checked")){
				        	accessoryArray_isUnChecked.push(AccessoryCodeArray[i]);			        	
				        }
					}
					
					console.log(accessoryArray_isChecked);
					// add Accessory in orderinternalaccessoryDetail Controler
					$.ajax({
						dataType: "json",
						type: 'POST',
						data: JSON.stringify(accessoryArray_isChecked),
						contentType: "application/json",
						url: "/Chori/orderinternalaccessoryDetailEditVer/add",
						success: function(data){
							if(data.status=="ok"){
									callMessageDialog("Message", "Assign Accessory successfully!");
									loadListOrderInternalAccessoryDetailModel();
							}else{
								alert("Error");
							}
						},
						error: function(){
							alert("failed");
						}
					});
					// clear all model table Accessory when save add order internal accessory   
//					$.ajax({
//						dataType: "json",
//						type: 'GET',
//						data: {},
//						url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
//						contentType: "application/json",
//						success: function(data){
////								alert("clear ok");
//						},
//						error: function(){
//							alert("Can't get data");
//						}
//					});
					///
				},
				"Cancel": function(){
					$("#dialogOrderInternalAccessoryDetailEditVer").dialog("close");
					$("#listAccessory").dataTable().fnDestroy();
					$('#listAccessory tbody').empty();
				}
			},
			close: function(){
				$("#dialogOrderInternalAccessoryDetailEditVer").dialog("close");
				$("#listAccessory").dataTable().fnDestroy();
				$('#listAccessory tbody').empty();
			}
		});
	});
	//End hàm load list accessory
	
	//test
	//hàm lưu accessory code list cuối để add
	var finalAccessoryDetailList = [];
	/**
	 * Hàm load list order Internal Accessory detail for edit dialog
	 */
	function loadListOrderInternalAccessoryDetailModel(){
		//xóa tạo lại table
		$("#OrderInternalAccessoryDetailTableCoverEditVer").html('');
		$("#OrderInternalAccessoryDetailTableCoverEditVer").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listOrderInternalDetail">'+
					'<thead>'+
					'<h4 align="center"> Order Accessories </h4> ' + 
						'<tr>'+
							'<th>Acessories name </th>'+
							'<th>Code</th>'+
							'<th>Order qty</th>'+
							'<th>Act delv qty</th>'+
							'<th>Unit</th>'+
							'<th>Unit Price</th>'+
							'<th>Refer Unit Price</th>'+
							'<th>Price</th>'+
							'<th>Currency</th>'+
							'<th>Photo</th>'+
						'</tr>'+
					'</thead>'+
				'</table>');
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/orderinternalaccessoryDetailAddEditVer/list",
			contentType: "application/json",
			success: function(data){
				finalAccessoryDetailList.length = 0;//
				if(data.listOrderInternalDetailAdd.length==0){

				}
				$.each(data.listOrderInternalDetailAdd,function(key,value){
					$('<tr>').append(
							$('<td>').text(value.accessoryname),
							$('<td>').text(value.accessorycode),
							$('<td>').html('<input type="number" min="1"  class="form-control" id="txtOrderQty' + value.accessorycode +'" name="txtOrderQty" value="'+(value.orderquantity==null?"":value.orderquantity)+'">'),
							$('<td>').html('<input type="number" min="0"  class="form-control" id="txtActDelvQty' + value.accessorycode +'" name="txtActDelvQty" value="'+(value.actualdelvquantity==null?"0":value.actualdelvquantity)+'">'),
							$('<td>').text(value.unit),
							$('<td>').html('<input type="number" min="1"  class="form-control" id="txtUnitPrice' + value.accessorycode +'" name="txtUnitPrice" value="'+value.unitprice+'" >'),
							$('<td>').text(value.unitprice==null?"":value.unitprice),
							$('<td>').html('<label  id="txtPrice' + value.accessorycode +'"  >'+(value.price==null?"":value.price)+'</label>'),
							$('<td>').text(value.currency==null?"":value.currency),
							$('<td>').html(((value.image==null)||(value.image.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.image+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.image+'" data-id="choriAccessoryImage/'+value.image+'" /></a>'))
					).appendTo('#listOrderInternalDetail');
					

					/**
					 * Allow input numeric only in column OrderQty form
					 */
					
					 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCoverEditVer").on('keydown', '#txtOrderQty'+value.accessorycode,
								function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
							||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
							||35<=e.keyCode&&40>=e.keyCode
							||(e.shiftKey||48>e.keyCode||57<e.keyCode)
							&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
					 
					 /**
						 * Allow input numeric only in column ActDelvQty form
						 */
					 
					 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCoverEditVer").on('keydown', '#txtActDelvQty'+value.accessorycode,
								function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
							||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
							||35<=e.keyCode&&40>=e.keyCode
							||(e.shiftKey||48>e.keyCode||57<e.keyCode)	
							&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
					 
					 /**
						 * Allow input numeric only in column UnitPrice form
						 */
					 
					 $("#listOrderInternalDetail,#OrderInternalAccessoryDetailTableCoverEditVer").on('keydown', '#txtUnitPrice'+value.accessorycode,
								function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])
							||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)
							||35<=e.keyCode&&40>=e.keyCode
							||(e.shiftKey||48>e.keyCode||57<e.keyCode)	
							&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
						

					//event for change orderQty value
						$("#txtOrderQty"+value.accessorycode+"").on('input propertychange paste', function (e) {
							var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
							var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
							var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
							if(actDelvQty==0)
								$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
							else
								$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
						})
						
						$("#txtActDelvQty"+value.accessorycode+"").on('input propertychange paste', function (e) {
							var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
							var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
							var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
							if(actDelvQty==0)
								$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
							else
								$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
						})
						
						//event for change unitPrice value
						$("#txtUnitPrice"+value.accessorycode+"").on('input propertychange paste', function (e) {
							var orderQty = $("#txtOrderQty"+value.accessorycode+"").val();
							var unitPrice = $("#txtUnitPrice"+value.accessorycode+"").val();
							var actDelvQty = $("#txtActDelvQty"+value.accessorycode+"").val()
							if(actDelvQty==0)
								$("#txtPrice"+value.accessorycode+"").text(orderQty*unitPrice);
							else
								$("#txtPrice"+value.accessorycode+"").text(actDelvQty*unitPrice);
						})
					
					
					//mới add để lưu code
					finalAccessoryDetailList.push(value.accessorycode);
					finalAccessoryDetailList = finalAccessoryDetailList.reduce(function(a,b){if(a.indexOf(b)<0)a.push(b);return a;},[]);
		
				});

				$('#listOrderInternalDetail').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Error");
			}
		});
	};
	//test
	
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
	 * -- Hàm kiểm tra send mail --
	 */
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
	
	function checkIfAddSuccessfully(){
		//message for add part
		if(getUrlParameter('sendMailStatus')==='Success'){
			callMessageDialog("Message", "Send mail successfully!");
		}
		if(getUrlParameter('sendMailStatus')==='Failed'){
			callMessageDialog("Warning Message", "Send mail failed!");
		}
	};
	
	 function compareEstVsActualDay(){
		 var EstDate = $("#txtEstDeliveryDate").val();
		 var ActDate = $("#txtActualDeliveryDate").val();
		 if (ActDate < EstDate)
			 return false;
		 return true;
	 };
	/**
	 * -- End hàm kiểm tra send mail --
	 */
	
	
	
	loadData();
	loadDropDownAccessorySupplier();
	loadDropDownFactory();
	checkIfAddSuccessfully();
});