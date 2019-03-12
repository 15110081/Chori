$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "customer/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					var tmp1 ='';
					if(value.customercontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.customercontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					if(value.brandModellist.length>0){
						tmp1+='<table border="0">';
						$.each(value.brandModellist,function(key,value1){
							tmp1+='<tr><td>'+value1.brandname+'</td></tr>';
						});
						tmp1+='</table>';
					}
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.customercode),
							$('<td>').text(value.shortname),							
							$('<td>').text(value.address),
							$('<td>').text(value.tel),
							$('<td>').text(value.fax),
							$('<td>').text(value.taxno),
							$('<td>').html(value.customercontactModellist.length==0?'':tmp),
							$('<td>').html(value.brandModellist.length==0?'':tmp1),
							$('<td>').text(value.remark==null?"":value.remark),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.customercode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.customercode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.customercode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'</select>')
					).appendTo('#listCustomer');
				});
				action();
				reloadTableWithStatus();

				$('#listCustomer').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
	function action(){
		//hàm cho nút edit list factory
		$('.selectOption').on('change', function (e){
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
			//lấy ra data-id
			var cusCode= $(this).data('id');
			//alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
			
		  //If user choose edit option
		    if(valueSelected=="Edit"){
		    	//lấy thông tin qua id gán vào edit dialog		    
			$.ajax({
				dataType: "json",
				type: 'POST',
				data: JSON.stringify({
					customercode: cusCode
				}),
				url: getAbsolutePath() +  "customer/detail",
				contentType: "application/json",
				success: function(data){
					//alert(data.factory.factorycode);
					// gán các giá trị vào dialog
					$('#editCustomerDialog').find('#txtCustomerCode').val(data.currentcustomer.customercode);
					
					$('#editCustomerDialog').find('#txtShortName').val(data.currentcustomer.shortname);
					$('#editCustomerDialog').find('#txtAddress').val(data.currentcustomer.address);
					$('#editCustomerDialog').find('#txtTel').val(data.currentcustomer.tel);
					$('#editCustomerDialog').find('#txtFax').val(data.currentcustomer.fax);
					$('#editCustomerDialog').find('#txtTaxNo').val(data.currentcustomer.taxno);
					$('#editCustomerDialog').find('#txtStatus').val(data.currentcustomer.status);	
					$('#editCustomerDialog').find('#txtRemark').val(data.currentcustomer.remark);	
					
					//duyệt qua mảng:
					$.each(data.currentcustomer.customercontactModellist,function(key,value){
						//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
						$("#tblCustomerContactList tbody").append('<tr data-id="'+value.customercontactcode+'"><td class="ccName">'+value.name+'</td><td class="ccEmail">'+value.email+'</td><td class="ccTel">'+value.tel+'</td><td><button class="btn btn-primary btnEditCC">Edit</button><button class="btn btn-danger btnDeleteCC">Delete</button></td></tr>');
						
						$(".btnEditCC").bind("click", EditContact);
						$(".btnDeleteCC").bind("click", DeleteContact);
					});
					
					$.each(data.currentcustomer.brandModellist,function(key,value){
						//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
						$("#tblBrandList tbody").append('<tr data-id="'+value.brandcode+'"><td class="cbBrandname">'+value.brandname+'</td><td><button class="btn btn-primary btnEditBrand">Edit</button><button class="btn btn-danger btnDeleteBrand">Delete</button></td></tr>');
						
						$(".btnEditBrand").bind("click", EditBrand);
						$(".btnDeleteBrand").bind("click", DeleteBrand);
					});
					
					//duyệt qua mảng:
					$.each(data.currentcustomer.customeraccountinformationModellist,function(key,value){
						//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
						$("#tblCustomeraccountinformationList tbody").append('<tr data-id="'+value.customeraccountinfocode+'"><td class="caiBankName">'+value.bankname+'</td><td class="caiBankBranch">'+value.bankbranch+'</td><td class="caiAccountNumber">'+value.accountnumber+'</td><td class="caiAddress">'+value.address+'</td><td class="caiSwiftCode">'+value.swiftcode+'</td><td><button class="btn btn-info btnEditCAI">Edit</button><button class="btn btn-danger btnDeleteCAI">Delete</button></td></tr>');
						
						$(".btnEditCAI").bind("click", EditCAI);
						$(".btnDeleteCAI").bind("click", DeleteCAI);
					});
					
				},
				error: function(){
					callMessageDialog("Message", "Can not get ID!");	
				}
			});
			//end lấy thông tin qua id gán vào edit dialog
			
			$("#editCustomerDialog").dialog({
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Customer",
				height: 750,
				width: 1400,
				modal: true,
				buttons:{
					"Save": function(){
						
						//check if user have entered required fields
						if(!validateRequiredFieldForEdit()){
							callMessageDialog("Warning message", "Please enter required fields!");
							
							//get value of code and name
							//var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
							//var fabricSupplierCode = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
							var shortName= $('#editCustomerDialog').find('#txtShortName').val();
							
							//if user dont enter code or enter just space
//							if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null){
//								$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
//								$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
//								$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
//							}
							//if user dont enter name or enter just space
							if(shortName.trim() === '' || shortName == null){
								$("#editCustomerDialog").find("#errorTxtShortName").text("Please enter customer full name!");
								$("#editCustomerDialog").find("#errorTxtShortName").css("color", "red");
								$("#editCustomerDialog").find("#txtShortName").css("background-color", "red");
							}
						}else{
							//if user have entered all required fields, then check if input is overRange
							if(!validateOverRangeWhenEdit()){
								callMessageDialog("Warning message", "Your input is over range!");
							}else{
								//all inputs are valid here.
								
								//edit part when inputs are valid
								var customercode = $('#editCustomerDialog').find('#txtCustomerCode').val();
								var shortname = $('#editCustomerDialog').find('#txtShortName').val();
								var address = $('#editCustomerDialog').find('#txtAddress').val();
								var tel = $('#editCustomerDialog').find('#txtTel').val();
								var fax = $('#editCustomerDialog').find('#txtFax').val();
								var taxno = $('#editCustomerDialog').find('#txtTaxNo').val();
								var status = $('#editCustomerDialog').find('#txtStatus').val();
								var remark = $('#editCustomerDialog').find('#txtRemark').val();
								
								var customercontactModellist = [];
								//lặp qua table contact
								$('#editCustomerDialog').find('#tblCustomerContactList tr').each(function() {
									var customercontactcode = $(this).data('id');
									var ccName = $(this).find(".ccName").html();
									var ccEmail = $(this).find(".ccEmail").html();
									var ccTel = $(this).find(".ccTel").html();
									if(typeof ccName === "undefined"){
										return true;//continue
									}
									//console.log(x+' '+y);
									customercontactModellist.push({
								          "customercontactcode": customercontactcode,
								          "customerCode": $('#editCustomerDialog').find('#txtCustomerCode').val(),
								          "creator": null,
								          "name": ccName,
								          "email": ccEmail,
								          "tel" : ccTel,
								          "createdate": null
								        });
		
									//console.log(JSON.stringify(factorycontactModellist));
								 });
								//end lặp qua table contact
								
								//lặp qua table brand
								var brandModellist = [];
								$('#editCustomerDialog').find('#tblBrandList tr').each(function() {
									var brandcode = $(this).data('id');									
									var cbBrandname = $(this).find(".cbBrandname").html();
									if(typeof cbBrandname === "undefined"){
										return true;//continue
									}
									//console.log(x+' '+y);
									brandModellist.push({
								          "brandcode": brandcode,
								          "customerCode": $('#editCustomerDialog').find('#txtCustomerCode').val(),
								          "creator": null,
								          "brandname": cbBrandname,
								          "createdate": null
								        });
		
									//console.log(JSON.stringify(factorycontactModellist));
								 });
								//end lặp qua table brand
								
								var customeraccountinformationModellist = [];
								//lặp qua table customer account information
								$('#editCustomerDialog').find('#tblCustomeraccountinformationList tr').each(function() {
									var customeraccountinfocode = $(this).data('id');
									var caiBankName = $(this).find(".caiBankName").html();
									var caiBankBranch = $(this).find(".caiBankBranch").html();
									var caiAccountNumber = $(this).find(".caiAccountNumber").html();
									var caiAddress = $(this).find(".caiAddress").html();
									var caiSwiftCode = $(this).find(".caiSwiftCode").html();
//									var caiButtons = $(this).find(".caiBankName").html();
									
									if(typeof caiBankName === "undefined"){
										return true;//continue
									}
									//console.log(x+' '+y);
									customeraccountinformationModellist.push({
										customeraccountinfocode: customeraccountinfocode,
										customerCode: $('#addCustomerDialog').find('#txtCustomerCode').val(),
										creator: null,
										bankname: caiBankName,
										bankbranch: caiBankBranch,
										accountnumber: caiAccountNumber,
										address: caiAddress,
										swiftcode: caiSwiftCode
								        });
			
									console.log(JSON.stringify(customeraccountinformationModellist));
								 });
								//end lặp qua table customer account information
								
								var customer = {
										customercode: customercode,
										shortname: shortname,
										address: address,
										tel: tel,
										fax: fax,
										taxno: taxno,
										status: status,
										remark: remark,
										customercontactModellist : customercontactModellist,
										brandModellist : brandModellist,
										customeraccountinformationModellist : customeraccountinformationModellist
								};
								
								console.log(JSON.stringify(customer));
								//
								
								//gọi ajax để edit
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify(customer),
									contentType: "application/json",
									url: getAbsolutePath() +  "customer/edit",
									success: function(data){
										if(data.editStatus==true){
											//alert("Edit thành công");
											
											reloadTableWithStatus();
											callMessageDialog("Message", 'Edit customer successfully!');
											$("#editCustomerDialog").dialog("close");
											resetAfterEdit();
										}else if(data.editStatus==false){
											callMessageDialog("Message", "Edit customer unsuccessfully!");
										}
									},
									error: function(){
				//						callMessageDialog("Message", "Error!");
									}
								});
								//end edit part when inputs are valid
								
							}
						}
						
					},
					"Cancel": function(){
						$("#editCustomerDialog").dialog("close");
						//Xóa các thông tin trong bảng contact
						resetAfterEdit();
					}
				},
				close: function(){
					resetAfterEdit();
				}
			});
		};
//		 //if user choose delete option
//		 if(valueSelected=="Delete"){
//			
//			$("#deleteCustomerDialog").find("#messageContent").text('Are you sure you want to delete Customer "' + cusCode+'"?');
//			
//			$("#deleteCustomerDialog").dialog({
//	    		show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete Customer Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/customer/delete/" + cusCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete Customer "'+ cusCode+ '" successfully!');
//										//close dialog
//										$("#deleteCustomerDialog").dialog("close");
//										//reload table
////										$("#listFabricSupplier").dataTable().fnDestroy();
////										$('#listFabricSupplier tbody').empty();
////										loadData();
//										reloadTableWithStatus();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t Delete Customer "'+ cusCode+ '"!');
//										$("#deleteCustomerDialog").dialog("close");
//									}else{
//										alert("this cant be show, 243!");
//									}
//								}else{
//									alert("unexpected error! (2), 404");
//								}
//							},
//							error: function(){
//								alert("unexpected error!");
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteCustomerDialog").dialog("close");
//					}
//				}
//			});
//		};
		 //end if user choose delete option
		});
	};
	//end chuyển action qua dropdownlist
	

	
	/**
	 * reload lại table vs status
	 */
	function reloadTableWithStatus(){
		//lấy ra value đc chọn
		var optionSelected = $('#ddlStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "customer/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listCustomer").dataTable().fnDestroy();
				$('#listCustomer tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
		//			alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					var tmp1 ='';
					if(value.customercontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.customercontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					if(value.brandModellist.length>0){
						tmp1+='<table border="0">';
						$.each(value.brandModellist,function(key,value1){
							tmp1+='<tr><td>'+value1.brandname+'</td></tr>';
						});
						tmp1+='</table>';
					}
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.customercode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').html(value.customercontactModellist.length==0?'':tmp),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.customercode+'">Edit</button>'),
								$('<td>').html(value.brandModellist.length==0?'':tmp1),
								$('<td>').text(value.remark==null?"":value.remark),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.customercode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.customercode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'</select>')
						).appendTo('#listCustomer');
						
					}else{//không thì xuất ra theo cái status
//						$("#listFactory").dataTable().fnDestroy();
//						$('#listFactory tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.customercode),
									$('<td>').text(value.shortname),
									$('<td>').text(value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),
									$('<td>').html(value.customercontactModellist.length==0?'':tmp),
									$('<td>').html(value.brandModellist.length==0?'':tmp1),
									$('<td>').text(value.remark==null?"":value.remark),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.customercode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.customercode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.customercode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'</select>')
							).appendTo('#listCustomer');
						}
					}
				});
				action();

				$('#listCustomer').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
		//		alert("Không lấy đc dữ liệu!");
			}
		});
		//
	}
	
	/**
	 * Hàm hiển thị lên add dialog để nhập 
	 */
	$('#btnAddNewCustomer').click(function(){
		$("#addCustomerDialog").dialog({
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Customer",
			height: 750,
			width: 1400,
			modal: true,
			buttons:{
				"Save": function(){
					var customerCodeInput = $('#addCustomerDialog').find('#txtCustomerCode').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						//get value of code and name
						//var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
						var customerCode = $('#addCustomerDialog').find('#txtCustomerCode').val();
						var shortName= $('#addCustomerDialog').find('#txtShortName').val();
						
						//if user dont enter code or enter just space
						if(customerCode.trim() === '' || customerCode == null){
							$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Please enter customer!");
							$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "red");
							$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "red");
						}
						//if user dont enter name or enter just space
						if(shortName.trim() === '' || shortName == null){
							$("#addCustomerDialog").find("#errorTxtShortName").text("Please enter customer full name!");
							$("#addCustomerDialog").find("#errorTxtShortName").css("color", "red");
							$("#addCustomerDialog").find("#txtShortName").css("background-color", "red");
						}
					}else{
						//if user have entered all required fields, then check if input is overRange
						if(!validateOverRangeWhenAdd()){
							callMessageDialog("Warning message", "Your input is over range!");
							//return false;
						}else{//check if the code is not existed
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									customercode: customerCodeInput
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "customer/isExist",
								success: function(data){
									if(data.isExisted==true){
										//if code is existed
										$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Customer is existed, please choose different one!");
										$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "red");
										$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "red");
									}else if(data.isExisted==false){
										//alert("all input is ok!");
										$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Customer is valid!");
										$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "green");
										$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "white");
										
										//add new fabric supplier here
										//main code when input is already valid
										var customercode = $('#addCustomerDialog').find('#txtCustomerCode').val();
										//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
										var shortname = $('#addCustomerDialog').find('#txtShortName').val();
										var address = $('#addCustomerDialog').find('#txtAddress').val();
										var tel = $('#addCustomerDialog').find('#txtTel').val();
										var fax = $('#addCustomerDialog').find('#txtFax').val();
										var taxno = $('#addCustomerDialog').find('#txtTaxNo').val();
										var status = $('#addCustomerDialog').find('#txtStatus').val();
										var remark = $('#addCustomerDialog').find('#txtRemark').val();
										
										var customercontactModellist = [];
										//lặp qua table contact
										$('#tblCustomerContactList tr').each(function() {
											var ccName = $(this).find(".ccName").html();
											var ccEmail = $(this).find(".ccEmail").html();
											var ccTel = $(this).find(".ccTel").html();
											if(typeof ccName === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											customercontactModellist.push({
										          "customercontactcode": null,
										          "customerCode": $('#addCustomerDialog').find('#txtCustomerCode').val(),
										          "creator": null,
										          "name": ccName,
										          "email": ccEmail,
										          "tel": ccTel,
										          "createdate": null
										        });
					
											//console.log(JSON.stringify(fabricsuppliercontactModelList));
										 });
										//end lặp qua table contact
										
										var brandModellist = [];
										//lặp qua table brand
										$('#tblBrandList tr').each(function() {
											var cbBrandname = $(this).find(".cbBrandname").html();											
											if(typeof cbBrandname === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											brandModellist.push({
										          "brandcode": null,
										          "customerCode": $('#addCustomerDialog').find('#txtCustomerCode').val(),
										          "creator": null,
										          "brandname": cbBrandname,
										          "createdate": null
										        });
					
											//console.log(JSON.stringify(fabricsuppliercontactModelList));
										 });
										//end lặp qua table contact
										
										var customeraccountinformationModellist = [];
										
										//lặp qua table customer account information
										$('#tblCustomeraccountinformationList tr').each(function() {									
											var caiBankName = $(this).find(".caiBankName").html();
											var caiBankBranch = $(this).find(".caiBankBranch").html();
											var caiAccountNumber = $(this).find(".caiAccountNumber").html();
											var caiAddress = $(this).find(".caiAddress").html();
											var caiSwiftCode = $(this).find(".caiSwiftCode").html();
											
											if(typeof caiBankName === "undefined"){
												return true;//continue
											}

											customeraccountinformationModellist.push({
										          
												//customeraccountinfocode: null,
												"customercode": $('#addCustomerDialog').find('#txtCustomerCode').val(),
												//creator: null,
												"bankname": caiBankName,
												"bankbranch": caiBankBranch,
												"accountnumber": caiAccountNumber,
												"address": caiAddress,
												"swiftcode": caiSwiftCode
										        });
					
											console.log(JSON.stringify(customeraccountinformationModellist));
										 });
										//end lặp qua table customer account information
										
										var customer = {
												customercode: customercode,
												shortname: shortname,
												address: address,
												tel: tel,
												fax: fax,
												taxno: taxno,
												status: status,
												remark: remark,
												customercontactModellist : customercontactModellist,
												brandModellist : brandModellist,
												customeraccountinformationModellist : customeraccountinformationModellist
										};
										
										console.log(JSON.stringify(customer));
										//gọi ajax để add
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify(customer),
											contentType: "application/json",
											url: getAbsolutePath() +  "customer/add",//làm đến đây
											success: function(data){
												//alert("OK");
												if(data.addStatus==true){
													//alert("OK");
													reloadTableWithStatus();
													callMessageDialog("Message", 'Add customer successfully!');
													$("#addCustomerDialog").dialog("close");
													resetAfterAdd();
												}
											},
											error: function(){
						//						alert("Error");
											}
										});
										//end main code when input is already valid
									}
								},
								error: function(){
									
									//here user delete code
								}
							});
						}
					}
					

				},
				"Cancel": function(){
					$("#addCustomerDialog").dialog("close");
					resetAfterAdd();
				}
			},
			close: function(){
				resetAfterAdd();
			}
		});
	});
	
	//hàm reset các trường trong bảng sau khi thêm mới
	function resetAfterAdd(){
		$('#addCustomerDialog').find('#txtCustomerCode').val('');
		$('#addCustomerDialog').find('#txtShortName').val('');
		$('#addCustomerDialog').find('#txtAddress').val('');
		$('#addCustomerDialog').find('#txtTel').val('');
		$('#addCustomerDialog').find('#txtFax').val('');
		$('#addCustomerDialog').find('#txtTaxNo').val('');
		$('#addCustomerDialog').find('#txtStatus').val('');		
		$('#addCustomerDialog').find('#txtRemark').val('');	
		$("#tblCustomerContactList > tbody").html("");
		$("#tblBrandList > tbody").html("");
		$("#tblCustomeraccountinformationList > tbody").html("");
		
		//reset css
		$("#addCustomerDialog").find("#errorTxtCustomerCode").text("");
		$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "white");
		$("#addCustomerDialog").find("#errorTxtShortName").text("");
		$("#addCustomerDialog").find("#txtShortName").css("background-color", "white");
		$("#addCustomerDialog").find("#errorTxtLongName").text("");
		$("#addCustomerDialog").find("#txtAddress").css("background-color", "white");
		$("#addCustomerDialog").find("#errorTxtTel").text("");
		$("#addCustomerDialog").find("#txtTel").css("background-color", "white");
		$("#addCustomerDialog").find("#errorTxtFax").text("");
		$("#addCustomerDialog").find("#txtFax").css("background-color", "white");
		$("#addCustomerDialog").find("#errorTxtTaxNo").text("");
		$("#addCustomerDialog").find("#txtTaxNo").css("background-color", "white");
		$("#addCustomerDialog").find("#errorTxtRemark").text("");
		$("#addCustomerDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi thêm mới
	
	//hàm reset các trường trong bảng sau khi edit
	function resetAfterEdit(){
		$('#editCustomerDialog').find('#txtCustomerCode').val('');
		$('#editCustomerDialog').find('#txtShortName').val('');
		$('#editCustomerDialog').find('#txtAddress').val('');
		$('#editCustomerDialog').find('#txtTel').val('');
		$('#editCustomerDialog').find('#txtFax').val('');
		$('#editCustomerDialog').find('#txtTaxNo').val('');
		$('#editCustomerDialog').find('#txtStatus').val('');
		$('#editCustomerDialog').find('#txtRemark').val('');
		$("#tblCustomerContactList > tbody").html("");
		$("#tblBrandList > tbody").html("");
		$("#tblCustomeraccountinformationList > tbody").html("");
		
		//reset css
		$("#editCustomerDialog").find("#errorTxtShortName").text("");
		$("#editCustomerDialog").find("#txtShortName").css("background-color", "white");
		$("#editCustomerDialog").find("#errorTxtLongName").text("");
		$("#editCustomerDialog").find("#txtAddress").css("background-color", "white");
		$("#editCustomerDialog").find("#errorTxtTel").text("");
		$("#editCustomerDialog").find("#txtTel").css("background-color", "white");
		$("#editCustomerDialog").find("#errorTxtFax").text("");
		$("#editCustomerDialog").find("#txtFax").css("background-color", "white");
		$("#editCustomerDialog").find("#errorTxtTaxNo").text("");
		$("#editCustomerDialog").find("#txtTaxNo").css("background-color", "white");
		$("#editCustomerDialog").find("#errorTxtRemark").text("");
		$("#editCustomerDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi edit
	
	/**
	 * Các hàm thêm mới vào bảng CustomerContact
	 */
	$(function contact(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditCC").bind("click", EditContact);
		$(".btnDeleteCC").bind("click", DeleteContact);
		$("#btnAddNewCustomerContact").bind("click", AddContact);
		$('#editCustomerDialog').find('#btnAddNewCustomerContact').bind("click", AddContact);
	});
	
	$(function brand(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditBrand").bind("click", EditBrand);
		$(".btnDeleteBrand").bind("click", DeleteBrand);		
		$("#btnAddNewBrand").bind("click", AddBrand);
		$('#editCustomerDialog').find('#btnAddNewBrand').bind("click", AddBrand);
	});
	
	$(function account(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditCAI").bind("click", EditCAI);
		$(".btnDeleteCAI").bind("click", DeleteCAI);
		$('#addCustomerDialog').find("#btnAddNewCustomeraccountinformation").bind("click", AddCAI);
		$('#editCustomerDialog').find('#btnAddNewCustomeraccountinformation').bind("click", AddCAI);
	});
	
//	function Bind4Edit(){
//		$(".btnEditCC").bind("click", Edit);
//		$(".btnDeleteCC").bind("click", Delete);
//		$("#btnAddNewFactoryContact").bind("click", Add);
//	}
	
	//hàm add 1 dòng vào table CustomerContactList
	function AddContact(){
		$("#tblCustomerContactList tbody").append(
				"<tr data-id='0'>"+
				"<td><input maxlength='50' type='text'/></td>"+
				"<td><input maxlength='100' type='text'/></td>"+
				"<td><input maxlength='50' type='text'/></td>"+
				"<td><button class='btn btn-primary btnSaveCC'>Save</button><button class='btn btn-danger btnDeleteCC'>Delete</button></td>"+
				"</tr>");		
			
				$(".btnSaveCC").bind("click", SaveContact);		
				$(".btnDeleteCC").bind("click", DeleteContact);
	};
	//end hàm add 1 dòng vào table CustomerContactList
	
	//function for validate email format
//	function validateEmail(email) {
//	  var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//	  return re.test(email);
//	}
//	
	//hàm add dòng đã nhập vào table
	function SaveContact(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTel = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

//		if(!validateEmail(tdEmail.children("input[type=text]").val().toString()))
//		{
//			callMessageDialog("Warning", 'Invalid email format!');
//		}
//		else {

		
			tdName.html(tdName.children("input[type=text]").val());
			tdName.addClass( "ccName" );
			tdEmail.html(tdEmail.children("input[type=text]").val());
			tdEmail.addClass( "ccEmail" );
			tdTel.html(tdTel.children("input[type=text]").val());
			tdTel.addClass( "ccTel" );
			tdButtons.html("<button class='btn btn-primary btnEditCC'>Edit</button><button class='btn btn-danger btnDeleteCC'>Delete</button>");

			$(".btnEditCC").bind("click", EditContact);
			$(".btnDeleteCC").bind("click", DeleteContact);
//		}
	};
	//end hàm add dòng đã nhập vào table
	
	//
	function EditContact(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTel = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

		tdName.html("<input type='text' maxlength='50' id='txtName' value='"+tdName.html()+"'/>");
		tdEmail.html("<input type='text' maxlength='100' id='txtEmail' value='"+tdEmail.html()+"'/>");
		tdTel.html("<input type='text' id='txtTel' maxlength='50' value='"+tdTel.html()+"'/>");
		tdButtons.html("<button class='btn btn-primary btnSaveCC'>Save</button>");

		$(".btnSaveCC").bind("click", SaveContact);
		$(".btnEditCC").bind("click", EditContact);
		$(".btnDeleteCC").bind("click", DeleteContact);
	};
	//
	
	//
	function DeleteContact(){
		var par = $(this).parent().parent(); //tr
		par.remove();
	}; 
	//
	
	//hàm add 1 dòng vào table BrandList
	function AddBrand(){
		
		$("#tblBrandList tbody").append(
				"<tr data-id='0'>"+
				"<td><input type='text' maxlength='50'/></td>"+
				"<td><button class='btn btn-primary btnSaveBrand'>Save</button><button class='btn btn-danger btnDeleteBrand'>Delete</button></td>"+
				"</tr>");
			
				$(".btnSaveBrand").bind("click", SaveBrand);		
				$(".btnDeleteBrand").bind("click", DeleteBrand);
	};
	//end hàm add 1 dòng vào table BrandList
	
	//hàm add dòng đã nhập vào table
	function SaveBrand(){
		var par = $(this).parent().parent(); //tr
		var tdBrandname = par.children("td:nth-child(1)");		
		var tdButtons = par.children("td:nth-child(2)");

		tdBrandname.html(tdBrandname.children("input[type=text]").val());
		tdBrandname.addClass( "cbBrandname" );		
		tdButtons.html("<button class='btn btn-primary btnEditBrand'>Edit</button><button class='btn btn-danger btnDeleteBrand'>Delete</button>");

		$(".btnEditBrand").bind("click", EditBrand);
		$(".btnDeleteBrand").bind("click", DeleteBrand);
	};
	//end hàm add dòng đã nhập vào table
	
	//
	function EditBrand(){
		var par = $(this).parent().parent(); //tr
		var tdBrandname = par.children("td:nth-child(1)");
		var tdButtons = par.children("td:nth-child(2)");

		tdBrandname.html("<input type='text' maxlength='50' id='txtBrandname' value='"+tdBrandname.html()+"'/>");		
		tdButtons.html("<button class='btn btn-primary btnSaveBrand'>Save</button>");

		$(".btnSaveBrand").bind("click", SaveBrand);
		$(".btnEditBrand").bind("click", EditBrand);
		$(".btnDeleteBrand").bind("click", DeleteBrand);
	};
	//
	
	//
	function DeleteBrand(){
		var par = $(this).parent().parent(); //tr
		par.remove();
	}; 
	//
	
	//hàm add 1 dòng vào table CustomeraccountinformationList
	function AddCAI(){
		$("#tblCustomeraccountinformationList tbody").append(
				"<tr data-id='0'>"+
				"<td><input maxlength='200' type='text'/></td>"+
				"<td><input maxlength='200' type='text'/></td>"+
				"<td><input maxlength='30' type='text'/></td>"+
				"<td><input maxlength='200' type='text'/></td>"+
				"<td><input maxlength='30' type='text'/></td>"+
				"<td><button class='btn btn-info btnSaveCAI'>Save</button><button class='btn btn-danger btnDeleteCAI'>Delete</button></td>"+
				"</tr>");
			
				$(".btnSaveCAI").bind("click", SaveCAI);		
				$(".btnDeleteCAI").bind("click", DeleteCAI);
	};
	//end hàm add 1 dòng vào table CustomeraccountinformationList
	
	//hàm add dòng đã nhập vào table
	function SaveCAI(){
		var par = $(this).parent().parent(); //tr
		var tdBankName = par.children("td:nth-child(1)");
		var tdBankBranch = par.children("td:nth-child(2)");
		var tdAccountNumber = par.children("td:nth-child(3)");
		var tdAddress = par.children("td:nth-child(4)");
		var tdSwiftCode = par.children("td:nth-child(5)");
		var tdButtons = par.children("td:nth-child(6)");

		tdBankName.html(tdBankName.children("input[type=text]").val());
		tdBankName.addClass( "caiBankName" );
		tdBankBranch.html(tdBankBranch.children("input[type=text]").val());
		tdBankBranch.addClass( "caiBankBranch" );
		tdAccountNumber.html(tdAccountNumber.children("input[type=text]").val());
		tdAccountNumber.addClass( "caiAccountNumber" );
		tdAddress.html(tdAddress.children("input[type=text]").val());
		tdAddress.addClass( "caiAddress" );
		tdSwiftCode.html(tdSwiftCode.children("input[type=text]").val());
		tdSwiftCode.addClass( "caiSwiftCode" );
		tdButtons.html("<button class='btn btn-info btnEditCAI'>Edit</button><button class='btn btn-danger btnDeleteCAI'>Delete</button>");

		$(".btnEditCAI").bind("click", EditCAI);
		$(".btnDeleteCAI").bind("click", DeleteCAI);
	};
	//end hàm add dòng đã nhập vào table
	
	//
	function EditCAI(){
		var par = $(this).parent().parent(); //tr
		var tdBankName = par.children("td:nth-child(1)");
		var tdBankBranch = par.children("td:nth-child(2)");
		var tdAccountNumber = par.children("td:nth-child(3)");
		var tdAddress = par.children("td:nth-child(4)");
		var tdSwiftCode = par.children("td:nth-child(5)");
		var tdButtons = par.children("td:nth-child(6)");

		tdBankName.html("<input maxlength='200' type='text' id='txtBankName' value='"+tdBankName.html()+"'/>");
		tdBankBranch.html("<input maxlength='200' type='text' id='txtBankBranch' value='"+tdBankBranch.html()+"'/>");
		tdAccountNumber.html("<input maxlength='30' type='text' id='txtAccountNumber' value='"+tdAccountNumber.html()+"'/>");
		tdAddress.html("<input maxlength='200' type='text' id='txtAddress' value='"+tdAddress.html()+"'/>");
		tdSwiftCode.html("<input maxlength='30' type='text' id='txtSwiftCode' value='"+tdSwiftCode.html()+"'/>");
		tdButtons.html("<button class='btn btn-info btnSaveCAI'>Save</button>");

		$(".btnSaveCAI").bind("click", SaveCAI);
		$(".btnEditCAI").bind("click", EditCAI);
		$(".btnDeleteCAI").bind("click", DeleteCAI);
	};
	//
	
	//
	function DeleteCAI(){
		var par = $(this).parent().parent(); //tr
		par.remove();
	}; 
	//
	
	/**
	 * end các hàm thêm mới vào bảng customer account information
	 */
	
	/**
	 * This function is used to call and set params for message dialog.
	 */
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
					$(this).dialog("close");
				}
			}
		});
	}
	
	/**
	 * -----------------------------------------
	 * 
	 */
	
	//do not allow input " character 
	$("#addCustomerDialog").find("#txtCustomerCode").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var customerCode= $("#addCustomerDialog").find("#txtCustomerCode").val();
		if(customerCode.trim() === '' || customerCode == null)
			return false;
		
		var shortName= $("#addCustomerDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new customer
	 */
	function validateOverRangeWhenAdd(){
		if($("#addCustomerDialog").find("#txtCustomerCode").val().length>50 || $("#addCustomerDialog").find("#txtShortName").val().length>50
				|| $("#addCustomerDialog").find("#txtAddress").val().length>200 
				|| $("#addCustomerDialog").find("#txtTel").val().length>50 || $("#addCustomerDialog").find("#txtFax").val().length>50
				|| $("#addCustomerDialog").find("#txtTaxNo").val().length>50|| $("#addCustomerDialog").find("#txtRemark").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * customer code on keyup function() to check if user entered, over range, customer is existed (3 cases)
	 */
	$("#addCustomerDialog").find("#txtCustomerCode").on('change keyup paste',function(){
		var customerCode= $(this).val();
		
		//if user dont enter or enter just space
		if(customerCode.trim() === '' || customerCode == null){
			$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Please enter customer!");
			$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		if(customerCode.length>50){
			$("#addCustomerDialog").find("#errorTxtCustomerCode").text("The field's length is 50. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if customercode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				customercode: customerCode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "customer/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new customer
					//if code is existed
					$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Please enter another customer!");
					$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "red");
					$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "red");
				}else if(data.isExisted==false&&$("#addCustomerDialog").find("#txtCustomerCode").val().length<51){
					//if code is not existed and valid length
					$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Customer is valid!");
					$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "green");
					$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "white");
				}
			},
			error: function(){
				//when user clear the code
				$("#addCustomerDialog").find("#errorTxtCustomerCode").text("Please enter customer!");
				$("#addCustomerDialog").find("#errorTxtCustomerCode").css("color", "red");
				$("#addCustomerDialog").find("#txtCustomerCode").css("background-color", "red");
			}
		});
	});
	
	/**
	 * customer's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addCustomerDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#addCustomerDialog").find("#errorTxtShortName").text("Please enter customer full name!");
			$("#addCustomerDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#addCustomerDialog").find("#errorTxtShortName").text("The full name field's length is 50. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addCustomerDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	
	/**
	 * customer address on keyup function() to check if over range (1 cases)
	 */
	$("#addCustomerDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#addCustomerDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#addCustomerDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * customer tel on keyup function() to check if over range (1 cases)
	 */
	$("#addCustomerDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#addCustomerDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#addCustomerDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * customer fax on keyup function() to check if over range (1 cases)
	 */
	$("#addCustomerDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#addCustomerDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#addCustomerDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * customer tax no on keyup function() to check if over range (1 cases)
	 */
	$("#addCustomerDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#addCustomerDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#addCustomerDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	$("#addCustomerDialog").find("#txtRemark").on('change keyup paste',function(){
		var remark= $(this).val();
		
		//if input of user is over range
		if(remark.length>500){
			$("#addCustomerDialog").find("#errorTxtRemark").text("The remark field's length is 500. Your input is over range!");
			$("#addCustomerDialog").find("#errorTxtRemark").css("color", "red");
			$(this).css("background-color", "red");
		}else if(remark.length<501){//valid input
			$("#addCustomerDialog").find("#errorTxtRemark").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * ------------------End part for adding-------------------
	 */
	
	/**
	 * This function is used to validate required fields before editing
	 */
	function validateRequiredFieldForEdit(){
//		var fabricSupplierCode= $("#editFabricSupplierDialog").find("#txtFabricSupplierCode").val();
//		if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null)
//			return false;
		
		var shortName= $("#editCustomerDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit customer
	 */
	function validateOverRangeWhenEdit(){
		if($("#editCustomerDialog").find("#txtShortName").val().length>50 
				|| $("#editCustomerDialog").find("#txtAddress").val().length>200
				|| $("#editCustomerDialog").find("#txtTel").val().length>50 || $("#editCustomerDialog").find("#txtFax").val().length>50
				|| $("#editCustomerDialog").find("#txtTaxNo").val().length>50 || $("#editCustomerDialog").find("#txtRemark").val().length>500)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] customer's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editCustomerDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#editCustomerDialog").find("#errorTxtShortName").text("Please enter customer full name!");
			$("#editCustomerDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#editCustomerDialog").find("#errorTxtShortName").text("The full name field's length is 50. Your input is over range!");
			$("#editCustomerDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editCustomerDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	
	
	/**
	 * [Edit part] customer address on keyup function() to check if over range (1 cases)
	 */
	$("#editCustomerDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#editCustomerDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#editCustomerDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#editCustomerDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] customer tel on keyup function() to check if over range (1 cases)
	 */
	$("#editCustomerDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#editCustomerDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#editCustomerDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#editCustomerDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] customer fax on keyup function() to check if over range (1 cases)
	 */
	$("#editCustomerDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#editCustomerDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#editCustomerDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#editCustomerDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] customer tax no on keyup function() to check if over range (1 cases)
	 */
	$("#editCustomerDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#editCustomerDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#editCustomerDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#editCustomerDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	$("#editCustomerDialog").find("#txtRemark").on('change keyup paste',function(){
		var remark= $(this).val();
		
		//if input of user is over range
		if(remark.length>500){
			$("#editCustomerDialog").find("#errorTxtRemark").text("The remark field's length is 500. Your input is over range!");
			$("#editCustomerDialog").find("#errorTxtRemark").css("color", "red");
			$(this).css("background-color", "red");
		}else if(remark.length<501){//valid input
			$("#editCustomerDialog").find("#errorTxtRemark").text("");
			$(this).css("background-color", "white");
		}
	});
	
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
			url: getAbsolutePath() +  "customer/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listCustomer").dataTable().fnDestroy();
				$('#listCustomer tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					var tmp1 ='';
					if(value.customercontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.customercontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					if(value.brandModellist.length>0){
						tmp1+='<table border="0">';
						$.each(value.brandModellist,function(key,value1){
							tmp1+='<tr><td>'+value1.brandname+'</td></tr>';
						});
						tmp1+='</table>';
					}
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.customercode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').html(value.customercontactModellist.length==0?'':tmp),
								$('<td>').html(value.brandModellist.length==0?'':tmp1),
								$('<td>').text(value.remark==null?"":value.remark),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.customercode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.customercode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.customercode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'</select>')
						).appendTo('#listCustomer');
						
					}else{//không thì xuất ra theo cái status
//						$("#listFactory").dataTable().fnDestroy();
//						$('#listFactory tbody').empty();
						
						if(valueSelected==value.status){
							
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.customercode),
									$('<td>').text(value.shortname),
									$('<td>').text(value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),
									$('<td>').html(value.customercontactModellist.length==0?'':tmp),
									$('<td>').html(value.brandModellist.length==0?'':tmp1),
									$('<td>').text(value.remark==null?"":value.remark),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.customercode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.customercode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.customercode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'</select>')
							).appendTo('#listCustomer');
						}
					}
				});
				action();

				$('#listCustomer').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	});
	
	loadData();
})