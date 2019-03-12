$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessorysupplier/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.accsupcontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.accsupcontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.telephone+'</td></tr>';
						});
						tmp+='</table>';
					}
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.accessorysuppliercode),
							$('<td>').text(value.shortname),
							$('<td>').text(value.longname),
							$('<td>').text(value.address),
							$('<td>').text(value.tel),
							$('<td>').text(value.fax),
							$('<td>').text(value.taxno),
							$('<td>').text(value.remark),
							$('<td>').html(value.accsupcontactModellist.length==0?'':tmp),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorysuppliercode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listAccSup');
				});
				action();

				$('#listAccSup').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		});
	};
	
	function action(){
		//hàm cho nút edit list accessorysupplier
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			var accsupCode= $(this).data('id');
			
			$(".selectOption").val("Options");
			//Select option edit
			if(valueSelected=="Edit"){
			//lấy thông tin qua id gán vào edit dialog
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:JSON.stringify({
					accessorysuppliercode: accsupCode
				}),
				url: getAbsolutePath() +  "accessorysupplier/detail/",
				contentType: "application/json",
				success: function(data){
					//alert(data.accessorysupplier.accsupCode);
					// gán các giá trị vào dialog
					$('#editAccSupDialog').find('#txtAccSupCode').val(data.accessorysupplier.accessorysuppliercode);					
					$('#editAccSupDialog').find('#txtShortName').val(data.accessorysupplier.shortname);
					$('#editAccSupDialog').find('#txtLongName').val(data.accessorysupplier.longname);
					$('#editAccSupDialog').find('#txtAddress').val(data.accessorysupplier.address);
					$('#editAccSupDialog').find('#txtTel').val(data.accessorysupplier.tel);
					$('#editAccSupDialog').find('#txtFax').val(data.accessorysupplier.fax);
					$('#editAccSupDialog').find('#txtTaxNo').val(data.accessorysupplier.taxno);
					$('#editAccSupDialog').find('#txtRemark').val(data.accessorysupplier.remark);
					$('#editAccSupDialog').find('#txtStatus').val(data.accessorysupplier.status);
					$('#editAccSupDialog').find('#txtEmail').val(data.accessorysupplier.email);
					
					//duyệt qua mảng:
					$.each(data.accessorysupplier.accsupcontactModellist,function(key,value){
$("#tblAccSupContactList tbody").append('<tr data-id="'+value.accessorycontact+'"><td class="fcName">'+value.name+'</td><td class="fcEmail">'+value.email+'</td><td class="fcTelephone">'+value.telephone+'</td><td><button class="btn btn-danger btnDeleteFC">Delete</button><button class="btn btn-info btnEditFC">Edit</button></td></tr>');
						
						$(".btnEditFC").bind("click", Edit);
						$(".btnDeleteFC").bind("click", Delete);
					});
				},
				error: function(){
					callMessageDialog("Message", "Can not get ID!");	
				}
			});
			//end lấy thông tin qua id gán vào edit dialog
			
			$("#editAccSupDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Accessory Supplier",
				height: 600,
				width: 1100,
				buttons:{
					"Edit": function(){
						
						//check if user have entered required fields
						if(!validateRequiredFieldForEdit()){
							callMessageDialog("Warning message", "Please enter required fields!");
							var shortName= $('#editAccSupDialog').find('#txtShortName').val();
							if(shortName.trim() === '' || shortName == null){
								$("#editAccSupDialog").find("#errorTxtShortName").text("Please enter customer short name!");
								$("#editAccSupDialog").find("#errorTxtShortName").css("color", "red");
								$("#editAccSupDialog").find("#txtShortName").css("background-color", "red");
							}
						}else{
							//if user have entered all required fields, then check if input is overRange
							if(!validateOverRangeWhenEdit()){
								callMessageDialog("Warning message", "Your input is over range!");
							}else{
								//all inputs are valid here.
								
								//edit part when inputs are valid
								var accsupcode = $('#editAccSupDialog').find('#txtAccSupCode').val();
								var shortname = $('#editAccSupDialog').find('#txtShortName').val();
								var longname = $('#editAccSupDialog').find('#txtLongName').val();
								var address = $('#editAccSupDialog').find('#txtAddress').val();
								var tel = $('#editAccSupDialog').find('#txtTel').val();
								var fax = $('#editAccSupDialog').find('#txtFax').val();
								var taxno = $('#editAccSupDialog').find('#txtTaxNo').val();
								var remark = $('#editAccSupDialog').find('#txtRemark').val();
								var status = $('#editAccSupDialog').find('#txtStatus').val();
								var email = $('#editAccSupDialog').find('#txtEmail').val();
								
								var accsupcontactModellist  = [];
								//lặp qua table contact
								$('#editAccSupDialog').find('#tblAccSupContactList tr').each(function() {
									var accsupcontactcode= $(this).data('id');
									var fcName = $(this).find(".fcName").html();
									var fcEmail = $(this).find(".fcEmail").html();
									var fcTelephone = $(this).find(".fcTelephone").html();
									if(typeof fcName === "undefined"){
										return true;//continue
									}
									//console.log(x+' '+y);
									accsupcontactModellist .push({
								          "accessorycontact": accsupcontactcode,
								          "accessoryCode": $('#editAccSupDialog').find('#txtAccSupCode').val(),
								          "creator": null,
								          "name": fcName,
								          "email": fcEmail,
								          "telephone": fcTelephone,
								          "createdate": null
								        });
		
									//console.log(JSON.stringify(factorycontactModellist));
								 });
								//end lặp qua table contact
								
								var accsupp  = {
										accessorysuppliercode: accsupcode,
										shortname: shortname,
										longname: longname,
										address: address,
										tel: tel,
										fax: fax,
										taxno: taxno,
										status: status,
										remark: remark,
										email: email,
										accsupcontactModellist : accsupcontactModellist
								};
								console.log(JSON.stringify(accsupp));
								
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify(accsupp),
									contentType: "application/json",
									url: getAbsolutePath() +  "accessorysupplier/edit",
									success: function(data){
										if(data.editStatus==true){
											
											callMessageDialog("Message", 'Edit Accessory Supplier successfully!');
											resetAfterEdit();
											$("#listAccSup").dataTable().fnDestroy();
											$('#listAccSup tbody').empty();
											$("#editAccSupDialog").dialog("close");
											reloadTableWithStatus();
										}else if(data.editStatus==false){
											callMessageDialog("Message", "Edit Accessory Supplier unsuccessfully!");
										}else if(data.editStatus==='invalid'){
											callMessageDialog("Message", "Invalid input!");
										}
									},
									error: function(){
										callMessageDialog("Message", "Error!");
									}
								});
								//end edit part when inputs are valid
								
							}
						}
						

					},
					"Cancel": function(){
						$("#editAccSupDialog").dialog("close");
						//Xóa các thông tin trong bảng contact
						resetAfterEdit();
					}
				},
				close: function(){
					resetAfterEdit();
				}
			});
		}
		//when click button delete
			if(valueSelected=="Delete"){
			var accsupCode= $(this).data('id');
			$("#deleteAccSupDialog").find("#messageContent").text('Are you sure you want to delete accessory supplier "' + accsupCode+'"?');
			
			$("#deleteAccSupDialog").dialog({
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
								accessorysuppliercode: accsupCode
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "accessorysupplier/delete/",
							success: function(data){
								if(data.status=="ok"){
									if(data.deleteStatus== true){
										//show delete status to user
										callMessageDialog("Message", 'Delete Accessory Supplier "'+ accsupCode+ '" successfully!');
										//close dialog
										$("#deleteAccSupDialog").dialog("close");
										//reload table
										reloadTableWithStatus();
									}else if(data.deleteStatus== false){
										callMessageDialog("Message", 'Can\'t Delete Accessory Supplier "'+ accsupCode + '"!');
										$("#deleteAccSupDialog").dialog("close");
									}else{
										alert("this cant be show, 243!");
									}
								}else{
									alert("unexpected error! (2), 404");
								}
							},
							error: function(){
								alert("unexpected error!");
							}
						});
					},
					"Cancel": function(){
						$("#deleteAccSupDialog").dialog("close");
					}
				}
			});
		};
	});
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
			url: getAbsolutePath() +  "accessorysupplier/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAccSup").dataTable().fnDestroy();
				$('#listAccSup tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.accsupcontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.accsupcontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.telephone+'</td></tr>';
						});
						tmp+='</table>';
					}
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.accessorysuppliercode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').text(value.remark),
								$('<td>').html(value.accsupcontactModellist.length==0?'':tmp),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorysuppliercode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listAccSup');
						
					}else{//không thì xuất ra theo cái status
//						$("#listAccSup").dataTable().fnDestroy();
//						$('#listAccSup tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.accessorysuppliercode),
									$('<td>').text(value.shortname),
									$('<td>').text(value.longname),
									$('<td>').text(value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),
									$('<td>').text(value.remark),
									$('<td>').html(value.accsupcontactModellist.length==0?'':tmp),
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorysuppliercode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccSup');
						}
					}
				});
				action();

				$('#listAccSup').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	});
	
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
			url: getAbsolutePath() +  "accessorysupplier/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAccSup").dataTable().fnDestroy();
				$('#listAccSup tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.accsupcontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.accsupcontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.telephone+'</td></tr>';
						});
						tmp+='</table>';
					}
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.accessorysuppliercode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').text(value.remark),
								$('<td>').html(value.accsupcontactModellist.length==0?'':tmp),
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorysuppliercode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listAccSup');
						
					}else{//không thì xuất ra theo cái status
//						$("#listAccSup").dataTable().fnDestroy();
//						$('#listAccSup tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.accessorysuppliercode),
									$('<td>').text(value.shortname),
									$('<td>').text(value.longname),
									$('<td>').text(value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),
									$('<td>').text(value.remark),
									$('<td>').html(value.accsupcontactModellist.length==0?'':tmp),
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorysuppliercode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccSup');
						}
					}
				});
				action();

				$('#listAccSup').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	}
	
	/**
	 * Hàm hiển thị lên add dialog để nhập 
	 */
	$('#btnAddNewAccSup').click(function(){
		$("#addAccSupDialog").find("#errorTxtAccSupCode").text("");
		$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
		$("#addAccSupDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Accessory Supplier",
			height: 600,
			width: 1100,
			buttons:{
				"Save":{
					text: "Save",
	            	id: "btnSaveNewAccSup",
	            	click: function(){
					var accsupCodeInput = $('#addAccSupDialog').find('#txtAccSupCode').val();
					
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
//						
						//get value of code and name
//						var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
//						var accsupCode = $('#addAccSupDialog').find('#txtAccSupCode').val();
//						var shortName= $('#addAccSupDialog').find('#txtShortName').val();
//						
//						//if user dont enter code or enter just space
//						if(accsupCode.trim() === '' || accsupCode == null){
//							$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Please enter accessory supplier code!");
//							$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
//							$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
//						}
//						//if user dont enter name or enter just space
//						if(shortName.trim() === '' || shortName == null){
//							$("#addAccSupDialog").find("#errorTxtShortName").text("Please enter accessory supplier short name!");
//							$("#addAccSupDialog").find("#errorTxtShortName").css("color", "red");
//							$("#addAccSupDialog").find("#txtShortName").css("background-color", "red");
//						}
					}else{
						//if user have entered all required fields, then check if input is overRange
						if(!validateOverRangeWhenAdd()){
							callMessageDialog("Warning message", "Your input is over range!");
							//return false;
						}else{//check if the code is not existed
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:JSON.stringify({
									accessorysuppliercode: accsupCodeInput
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "accessorysupplier/isExist/",
								success: function(data){
									var accsupInput = $('#addAccSupDialog').find('#txtAccSupCode').val();
//									if(data.isExisted==true){
//										//if code is existed
//										$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Accessory supplier code you enter is existed, please choose different one!");
//										$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
//										$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
//									}else 
										if(data.isExisted==false){
											if(accsupInput.length>50||accsupInput.length<1){
												$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Your input must between 1 - 50 characters");
												$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
												$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
												$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
												$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
											}
											else if($.trim(accsupInput) !== accsupInput){
												$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Not allow to type the space as a first/last character");
												$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
												$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
												$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
												$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
											}else{
												$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Accessory supplier code is valid!");
												$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "green");
												$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "green");
												$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
												$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", false );
											}
										//alert("all input is ok!");
//										$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Accessory supplier code is valid!");
//										$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "green");
//										$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
										
										//add new fabric supplier here
										//main code when input is already valid
										var accsupcode = $('#addAccSupDialog').find('#txtAccSupCode').val();
										var shortname = $('#addAccSupDialog').find('#txtShortName').val();
										var longname = $('#addAccSupDialog').find('#txtLongName').val();
										var address = $('#addAccSupDialog').find('#txtAddress').val();
										var tel = $('#addAccSupDialog').find('#txtTel').val();
										var fax = $('#addAccSupDialog').find('#txtFax').val();
										var taxno = $('#addAccSupDialog').find('#txtTaxNo').val();
										var remark = $('#addAccSupDialog').find('#txtRemark').val();
										var status = $('#addAccSupDialog').find('#txtStatus').val();
										var email = $('#addAccSupDialog').find('#txtEmail').val();
										
										var accsupcontactModellist = [];
										//lặp qua table contact
										$('#tblAccSupContactList tr').each(function() {
											var fcName = $(this).find(".fcName").html();
											var fcEmail = $(this).find(".fcEmail").html();
											var fcTelephone = $(this).find(".fcTelephone").html();
											if(typeof fcName === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											accsupcontactModellist.push({
										          "accessorycontact": null,
										          "accessoryCode": $('#addAccSupDialog').find('#txtAccSupCode').val(),
										          "creator": null,
										          "name": fcName,
										          "email": fcEmail,
										          "telephone": fcTelephone,
										          "createdate": null
										        });
					
										
										 });
										//end lặp qua table contact
										
										var accsup = {
												accessorysuppliercode: accsupcode,
												shortname: shortname,
												longname: longname,
												address: address,
												tel: tel,
												fax: fax,
												taxno: taxno,
												remark: remark,
												status: status,
												email: email,
												accsupcontactModellist : accsupcontactModellist
										};
										
										console.log(JSON.stringify(accsup));
										//gọi ajax để add
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify(accsup),
											contentType: "application/json",
											url: getAbsolutePath() +  "accessorysupplier/add",
											success: function(data){
												//alert("OK");
												if(data.addStatus==true){
													//alert("OK");
													
													callMessageDialog("Message", 'Add New Accessory Supplier successfully!');
													resetAfterAdd();
													$("#listAccSup").dataTable().fnDestroy();
													$('#listAccSup tbody').empty();
													$("#addAccSupDialog").dialog("close");
													reloadTableWithStatus();
												}else if(data.addStatus==='invalid'){
													callMessageDialog("Message", "Invalid input!");
												}
											},
											error: function(){
										//		alert("Error");
											}
										});
										//end main code when input is already valid
									}else{
										$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Accessory supplier code has been created!");
										$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
										$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
										$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
										$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
									}
									
								},
								error: function(){
									$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Please enter accessory supplier code!");
									$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
									$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
									$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
									//here user delete code
								}
							});
						}
					}
					
	            	}
				},
				"Cancel": function(){
					$("#addAccSupDialog").dialog("close");
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
		$('#addAccSupDialog').find('#txtAccSupCode').val('');
		$('#addAccSupDialog').find('#txtShortName').val('');
		$('#addAccSupDialog').find('#txtLongName').val('');
		$('#addAccSupDialog').find('#txtAddress').val('');
		$('#addAccSupDialog').find('#txtTel').val('');
		$('#addAccSupDialog').find('#txtFax').val('');
		$('#addAccSupDialog').find('#txtTaxNo').val('');
		$('#addAccSupDialog').find('#txtRemark').val('');
		$('#addAccSupDialog').find('#txtStatus').val('');
		$('#addAccSupDialog').find('#txtEmail').val('');
		$('#addAccSupDialog').find('#txtRemark').val('');
		$("#tblAccSupContactList > tbody").html("");
		
		//reset css
		$("#addAccSupDialog").find("#errorTxtAccSupCode").text("");
		$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtShortName").text("");
		$("#addAccSupDialog").find("#txtShortName").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtLongName").text("");
		$("#addAccSupDialog").find("#txtLongName").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtAddress").text("");
		$("#addAccSupDialog").find("#txtAddress").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtTel").text("");
		$("#addAccSupDialog").find("#txtTel").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtFax").text("");
		$("#addAccSupDialog").find("#txtFax").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtTaxNo").text("");
		$("#addAccSupDialog").find("#txtTaxNo").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtEmail").text("");
		$("#addAccSupDialog").find("#txtEmail").css("background-color", "white");
		$("#addAccSupDialog").find("#errorTxtRemark").text("");
		$("#addAccSupDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi thêm mới
	
	//hàm reset các trường trong bảng sau khi edit
	function resetAfterEdit(){
		$('#editAccSupDialog').find('#txtAccSupCode').val('');
		$('#editAccSupDialog').find('#txtShortName').val('');
		$('#editAccSupDialog').find('#txtLongName').val('');
		$('#editAccSupDialog').find('#txtAddress').val('');
		$('#editAccSupDialog').find('#txtTel').val('');
		$('#editAccSupDialog').find('#txtFax').val('');
		$('#editAccSupDialog').find('#txtTaxNo').val('');
		$('#editAccSupDialog').find('#txtRemark').val('');
		$('#editAccSupDialog').find('#txtStatus').val('');	
		$('#editAccSupDialog').find('#txtEmail').val('');
		$('#editAccSupDialog').find('#txtRemark').val('');
		$("#tblAccSupContactList > tbody").html("");
		
		//reset css
		$("#editAccSupDialog").find("#errorTxtShortName").text("");
		$("#editAccSupDialog").find("#txtShortName").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtLongName").text("");
		$("#editAccSupDialog").find("#txtLongName").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtAddress").text("");
		$("#editAccSupDialog").find("#txtAddress").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtTel").text("");
		$("#editAccSupDialog").find("#txtTel").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtFax").text("");
		$("#editAccSupDialog").find("#txtFax").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtTaxNo").text("");
		$("#editAccSupDialog").find("#txtTaxNo").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtEmail").text("");
		$("#editAccSupDialog").find("#txtEmail").css("background-color", "white");
		$("#editAccSupDialog").find("#errorTxtRemark").text("");
		$("#editAccSupDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi edit
	
	/**
	 * Các hàm thêm mới vào bảng FactoryContact
	 */
	$(function(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditFC").bind("click", Edit);
		$(".btnDeleteFC").bind("click", Delete);
		$("#btnAddNewAccSupContact").bind("click", Add);
		$('#editAccSupDialog').find('#btnAddNewAccSupContact').bind("click", Add);
	});
	
//	function Bind4Edit(){
//		$(".btnEditFC").bind("click", Edit);
//		$(".btnDeleteFC").bind("click", Delete);
//		$("#btnAddNewFactoryContact").bind("click", Add);
//	}
	
	//hàm add 1 dòng vào table AccessorySupplierContactList
	function Add(){
		$("#tblAccSupContactList tbody").append(
				"<tr data-id='0'>"+
				"<td><input type='text'/></td>"+
				"<td><input type='text'/></td>"+
				"<td><input type='text'/></td>"+
				"<td><button class='btn btn-info btnSaveFC'>Save</button><button class='btn btn-danger btnDeleteFC'>Delete</button></td>"+
				"</tr>");
			
				$(".btnSaveFC").bind("click", Save);		
				$(".btnDeleteFC").bind("click", Delete);
	};
	//end hàm add 1 dòng vào table AccessorySupplierContactList
	
	//function for validate email format
	function validateEmail(email) {
	  var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	  return re.test(email);
	}
	
	//hàm add dòng đã nhập vào table
	function Save(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTelephone = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

//		if(!validateEmail(tdEmail.children("input[type=text]").val().toString()))
//		{
//			callMessageDialog("Warning", 'Invalid email format!');
//		}
//		else {	
//			tdName.html(tdName.children("input[type=text]").val());
//			tdName.addClass( "fcName" );
//			tdEmail.html(tdEmail.children("input[type=text]").val());
//			tdEmail.addClass( "fcEmail" );
//			tdTelephone.html(tdTelephone.children("input[type=text]").val());
//			tdTelephone.addClass( "fcTelephone" );
//			tdButtons.html("<button class='btn btn-danger btnDeleteFC'>Delete</button><button class='btn btn-info btnEditFC'>Edit</button>");
//	
//			$(".btnEditFC").bind("click", Edit);
//			$(".btnDeleteFC").bind("click", Delete);
//		}
		if(tdEmail.children("input[type=text]").val().trim().length==0&&tdEmail.children("input[type=text]").val().length==0)
		{
			tdName.html(tdName.children("input[type=text]").val());
			tdName.addClass( "fcName" );
			tdEmail.html(tdEmail.children("input[type=text]").val());
			tdEmail.addClass( "fcEmail" );
			tdTelephone.html(tdTelephone.children("input[type=text]").val());
			tdTelephone.addClass( "fcTelephone" );
			tdButtons.html("<button class='btn btn-info btnEditFC'>Edit</button><button class='btn btn-danger btnDeleteFC'>Delete</button>");

			$(".btnEditFC").bind("click", Edit);
			$(".btnDeleteFC").bind("click", Delete);
			//nhập toàn cách
		}else if(tdEmail.children("input[type=text]").val().trim().length==0&&tdEmail.children("input[type=text]").val().length>0){
			callMessageDialog("Warning", 'Please left email field blank if you don\'t enter email!');
			//nhập gì đó khác cách sẽ kiểm tra định dạng email
		}else if(!validateEmail(tdEmail.children("input[type=text]").val().toString())){
			callMessageDialog("Warning", 'Invalid email format!');
			//nhập đúng định dạng thì cho insert
		}else {	
			tdName.html(tdName.children("input[type=text]").val());
			tdName.addClass( "fcName" );
			tdEmail.html(tdEmail.children("input[type=text]").val());
			tdEmail.addClass( "fcEmail" );
			tdTelephone.html(tdTelephone.children("input[type=text]").val());
			tdTelephone.addClass( "fcTelephone" );
			tdButtons.html("<button class='btn btn-info btnEditFC'>Edit</button><button class='btn btn-danger btnDeleteFC'>Delete</button>");

			$(".btnEditFC").bind("click", Edit);
			$(".btnDeleteFC").bind("click", Delete);
		}
	};
	//end hàm add dòng đã nhập vào table
	
	// when click edit AccessorySupplierContact
	function Edit(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTelephone = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

//		tdName.html("<input type='text' id='txtName' value='"+tdName.html()+"'/>");
//		tdEmail.html("<input type='text' id='txtEmail' value='"+tdEmail.html()+"'/>");
//		tdTelephone.html("<input type='text' id='txtTelephone' value='"+tdTelephone.html()+"'/>");
//		tdButtons.html("<button class='btn btn-info btnSaveFC'>Save</button>");

		tdName.html("<input maxlength='50' type='text' id='txtName' value='"+tdName.html()+"'/>");
		tdEmail.html("<input maxlength='100' type='text' id='txtEmail' value='"+tdEmail.html()+"'/>");
		tdTelephone.html("<input maxlength='50' type='text' id='txtTelephone' value='"+tdTelephone.html()+"'/>");
		tdButtons.html("<button class='btn btn-info btnSaveFC'>Save</button>");
		
		$(".btnSaveFC").bind("click", Save);
		$(".btnEditFC").bind("click", Edit);
		$(".btnDeleteFC").bind("click", Delete);
	};
	//
	
	//function delete AccessorySupplierContact
	function Delete(){
		var par = $(this).parent().parent(); //tr
		par.remove();
	}; 
	//
	
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
	$("#addAccSupDialog").find("#txtAccSupCode").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var accsupCode= $("#addAccSupDialog").find("#txtAccSupCode").val();
		if(accsupCode.trim() === '' || accsupCode == null)
			return false;
		
		var shortName= $("#addAccSupDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new fabric supplier
	 */
	function validateOverRangeWhenAdd(){
		if($("#addAccSupDialog").find("#txtAccSupCode").val().length>50 || $("#addAccSupDialog").find("#txtShortName").val().length>50 
				|| $("#addAccSupDialog").find("#txtLongName").val().length>100 || $("#addAccSupDialog").find("#txtAddress").val().length>200
				|| $("#addAccSupDialog").find("#txtTel").val().length>50 || $("#addAccSupDialog").find("#txtFax").val().length>50
				|| $("#addAccSupDialog").find("#txtTaxNo").val().length>50 || $("#addAccSupDialog").find("#txtEmail").val().length>100)
			return false;
		return true;
	}
	
	/**
	 * fabric supplier code on keyup function() to check if user entered, over range, fabric supplier is existed (3 cases)
	 */
	$("#addAccSupDialog").find("#txtAccSupCode").on('change keyup paste',function(){
		var accsupCode= $(this).val();
		
		//if user dont enter or enter just space
//		if(accsupCode.trim() === '' || accsupCode == null){
//			$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Please enter accessory supplier code!");
//			$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
//			$(this).css("background-color", "red");
//		}
//		//if input of user is over range
//		if(accsupCode.length>20 || accsupCode.length<1){
//			$("#addAccSupDialog").find("#errorTxtAccSupCode").text("The code field's length is 20. Your input is over range!");
//			$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
//			$(this).css("background-color", "red");
//		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				accessorysuppliercode: accsupCode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "accessorysupplier/isExist/",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Please enter another accessory supplier code!");
					$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
					$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
				}else if(data.isExisted==false){
					if(accsupCode.length>50||accsupCode.length<1){
						$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Your input must between 1 - 50 characters");
						$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
						$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
						$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
						$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
					}
					else if($.trim(accsupCode) !== accsupCode){
						$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Not allow to type the space as a first/last character");
						$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
						$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
						$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
						$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
					}else{
						$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Accessory supplier type code is valid!");
						$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "green");
						$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "green");
						$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "white");
						$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", false );
					}
				}else {
					$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Accessory supplier has been created!");
					$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
					$("#addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
					$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
					$('#listAccSup,#btnSaveNewAccSup').prop( "disabled", true );
				}
			},
			error: function(){
				//when user clear the code
				$("#addAccSupDialog").find("#errorTxtAccSupCode").text("Please enter accessory supplier code!");
				$("#addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
				$("#addAccSupDialog").find("#txtAccSupCode").css("background-color", "red");
			}
		});
	});
	
	
	$("#addAccSupDialog").find("#txtAccSupCode").on('input propertychange paste',function(e){
		var input= $(this).val();
		if(input.length>50||input.length<1){
			$("addAccSupDialog").find("#errorTxtAccSupCode").text("Your input between 1 - 50 characters");
			$("addAccSupDialog").find("#errorTxtAccSupCode").css("color", "red");
			$("addAccSupDialog").find("#txtAccSupCode").css("border-color", "red");
			$('btnSaveNewAccSup').prop( "disabled", true );
		}
		else if($.trim(input) !== input){
			$("addAccSupDialog").find("#txtAccSupCode").css("border-left", "red 5px solid");
			$("addAccSupDialog").find("#errorTxtAccSupCode").text("Not allow to type the space as a first/last character");
			$('btnSaveNewAccSup').prop( "disabled", true );
		}
		else{
			$("addAccSupDialog").find("#errorTxtAccSupCode").text("");
			$("addAccSupDialog").find("#txtAccSupCode").css("border-color", "green");
			$('btnSaveNewAccSup').prop( "disabled", false );
		}
	})
	
	
	/**
	 * accessory supplier's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addAccSupDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#addAccSupDialog").find("#errorTxtShortName").text("Please enter short name!");
			$("#addAccSupDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#addAccSupDialog").find("#errorTxtShortName").text("The shortName field's length is 50. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addAccSupDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier long name on keyup function() to check if over range (1 cases)
	 */
	$("#addAccSupDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#addAccSupDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#addAccSupDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier address on keyup function() to check if over range (1 cases)
	 */
	$("#addAccSupDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#addAccSupDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#addAccSupDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier tel on keyup function() to check if over range (1 cases)
	 */
	$("#addAccSupDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#addAccSupDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#addAccSupDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier fax on keyup function() to check if over range (1 cases)
	 */
	$("#addAccSupDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#addAccSupDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#addAccSupDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier tax no on keyup function() to check if over range (1 cases)
	 */
	$("#addAccSupDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#addAccSupDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#addAccSupDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	$("#addAccSupDialog").find("#txtRemark").on('change keyup paste',function(){
		var txtRemark= $(this).val();
		
		//if input of user is over range
		if(txtRemark.length>50){
			$("#addAccSupDialog").find("#errorTxtRemark").text("The remark no field's length is 50. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtRemark").css("color", "red");
			$(this).css("background-color", "red");
		}else if(txtRemark.length<51){//valid input
			$("#addAccSupDialog").find("#errorTxtRemark").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * email on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#addAccSupDialog").find("#txtEmail").on('change keyup paste',function(){
		var email= $(this).val();
		
		//if input of user is over range
		if(email.length>100){
			$("#addAccSupDialog").find("#errorTxtEmail").text("The email field's length is 100. Your input is over range!");
			$("#addAccSupDialog").find("#errorTxtEmail").css("color", "red");
			$(this).css("background-color", "red");
		}else{
			if($("#addAccSupDialog").find("#txtEmail").val().length!=$("#addAccSupDialog").find("#txtEmail").val().trim().length){
				//if user enter space on prefix, suffix
				$("#addAccSupDialog").find("#errorTxtEmail").text("You can't use space on prefix and suffix!");
				$("#addAccSupDialog").find("#errorTxtEmail").css("color", "red");
				$(this).css("background-color", "red");
			}else {
				if(!isValidationEmail(email)){//valid input
					$("#addAccSupDialog").find("#errorTxtEmail").text("Email pattern is wrong!");
					$("#addAccSupDialog").find("#errorTxtEmail").css("color", "red");
					$(this).css("background-color", "red");
				}else {
					if(email.length<101){//valid input
						$("#addAccSupDialog").find("#errorTxtEmail").text("");
						$(this).css("background-color", "white");
					}
				}
			}
		} 
	});
	
	/**
	 * this function validate email
	 */
	 function isValidationEmail($email) {
		 var emailReg = /^([\w-\.]+@([\w-])+([\w-]+\.)+[\w-]{2,4})?$/;
		 return emailReg.test( $email );
	 }
	
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
		
		var shortName= $("#editAccSupDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit fabric supplier
	 */
	function validateOverRangeWhenEdit(){
		if($("#editAccSupDialog").find("#txtShortName").val().length>50 
				|| $("#editAccSupDialog").find("#txtLongName").val().length>100 || $("#editAccSupDialog").find("#txtAddress").val().length>200
				|| $("#editAccSupDialog").find("#txtTel").val().length>50 || $("#editAccSupDialog").find("#txtFax").val().length>50
				|| $("#editAccSupDialog").find("#txtTaxNo").val().length>50 || $("#editAccSupDialog").find("#txtEmail").val().length>100)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] fabric supplier's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editAccSupDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#editAccSupDialog").find("#errorTxtShortName").text("Please enter short name!");
			$("#editAccSupDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#editAccSupDialog").find("#errorTxtShortName").text("The shortName field's length is 50. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editAccSupDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier long name on keyup function() to check if over range (1 cases)
	 */
	$("#editAccSupDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#editAccSupDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#editAccSupDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier address on keyup function() to check if over range (1 cases)
	 */
	$("#editAccSupDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#editAccSupDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#editAccSupDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier tel on keyup function() to check if over range (1 cases)
	 */
	$("#editAccSupDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#editAccSupDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#editAccSupDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier fax on keyup function() to check if over range (1 cases)
	 */
	$("#editAccSupDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#editAccSupDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#editAccSupDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier tax no on keyup function() to check if over range (1 cases)
	 */
	$("#editAccSupDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#editAccSupDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#editAccSupDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	$("#editAccSupDialog").find("#txtRemark").on('change keyup paste',function(){
		var txtRemark= $(this).val();
		
		//if input of user is over range
		if(txtRemark.length>50){
			$("#editAccSupDialog").find("#errorTxtRemark").text("The remark no field's length is 50. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtRemark").css("color", "red");
			$(this).css("background-color", "red");
		}else if(txtRemark.length<51){//valid input
			$("#editAccSupDialog").find("#errorTxtRemark").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * email on keyup function() to check if over range, no white space (1 cases)
	 */
	$("#editAccSupDialog").find("#txtEmail").on('change keyup paste',function(){
		var email= $(this).val();
		
		//if input of user is over range
		if(email.length>100){
			$("#editAccSupDialog").find("#errorTxtEmail").text("The email field's length is 100. Your input is over range!");
			$("#editAccSupDialog").find("#errorTxtEmail").css("color", "red");
			$(this).css("background-color", "red");
		}else{
			if($("#editAccSupDialog").find("#txtEmail").val().length!=$("#editAccSupDialog").find("#txtEmail").val().trim().length){
				//if user enter space on prefix, suffix
				$("#editAccSupDialog").find("#errorTxtEmail").text("You can't use space on prefix and suffix!");
				$("#editAccSupDialog").find("#errorTxtEmail").css("color", "red");
				$(this).css("background-color", "red");
			}else {
				if(!isValidationEmail(email)){//valid input
					$("#editAccSupDialog").find("#errorTxtEmail").text("Email pattern is wrong!");
					$("#editAccSupDialog").find("#errorTxtEmail").css("color", "red");
					$(this).css("background-color", "red");
				}else {
					if(email.length<101){//valid input
						$("#editAccSupDialog").find("#errorTxtEmail").text("");
						$(this).css("background-color", "white");
					}
				}
			}
		} 
	});
	
	//
	loadData();
})