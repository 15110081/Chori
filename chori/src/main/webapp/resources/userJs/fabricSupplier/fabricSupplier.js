$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "fabricSupplier/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.fabricsuppliercontactModelList.length>0){
						tmp+='<table border="0">';
						$.each(value.fabricsuppliercontactModelList,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td>'+'<td>'+value1.tel+'</td>'+'</tr>';
						});
						tmp+='</table>';
					}
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.fabricsupcode),
							$('<td>').text(value.shortname),
							$('<td>').text(value.longname),
//							$('<td>').text(value.ischori==true?"Yes":"No"),
							$('<td>').text(value.address==null?'':value.address),
							$('<td>').text(value.tel),
							$('<td>').text(value.fax),
							$('<td>').text(value.taxno),
							$('<td>').html(value.fabricsuppliercontactModelList.length==0?'':tmp),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.fabricsupcode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.fabricsupcode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.fabricsupcode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listFabricSupplier');
				});
				action();

				$('#listFabricSupplier').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
//	function action(){
//		//hàm cho nút edit list factory
//		$('.btnEdit').click(function(){
//			
//			//lấy ra data-id
//			var fabricSupplierCode= $(this).data('id');
//			
//			//lấy thông tin qua id gán vào edit dialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: "/Chori/fabricSupplier/detail/"+fabricSupplierCode,
//				contentType: "application/json",
//				success: function(data){
//					//alert(data.factory.factorycode);
//					//gán các giá trị vào dialog
//					$('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val(data.fabricSupplier.fabricsupcode);
//					$('#editFabricSupplierDialog').find('#txtShortName').val(data.fabricSupplier.shortname);
//					$('#editFabricSupplierDialog').find('#txtLongName').val(data.fabricSupplier.longname);
//					$('#editFabricSupplierDialog').find('#txtAddress').val(data.fabricSupplier.address);
//					$('#editFabricSupplierDialog').find('#txtTel').val(data.fabricSupplier.tel);
//					$('#editFabricSupplierDialog').find('#txtFax').val(data.fabricSupplier.fax);
//					$('#editFabricSupplierDialog').find('#txtTaxNo').val(data.fabricSupplier.taxno);
//					$('#editFabricSupplierDialog').find('#txtStatus').val(data.fabricSupplier.status);
//					var ischori = data.fabricSupplier.ischori;
//					if(ischori){
//						$('#editFabricSupplierDialog').find('#cbIsChori').prop( "checked", true );
//					}else{
//						$('#editFabricSupplierDialog').find('#cbIsChori').prop( "checked", false );
//					}
//					
//					//duyệt qua mảng:
//					$.each(data.fabricSupplier.fabricsuppliercontactModelList,function(key,value){
//						//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
//						$("#tblFabricSupplierContactList tbody").append('<tr data-id="'+value.fabricsuppliercontactcode+'"><td class="fcName">'+value.name+'</td><td class="fcEmail">'+value.email+'</td><td><button class="btn btn-danger btnDeleteFC">Delete</button><button class="btn btn-info btnEditFC">Edit</button></td></tr>');
//						
//						$(".btnEditFC").bind("click", Edit);
//						$(".btnDeleteFC").bind("click", Delete);
//					});
//				},
//				error: function(){
//					alert("Lỗi lấy by id")
//				}
//			});
//			//end lấy thông tin qua id gán vào edit dialog
//			
//			$("#editFabricSupplierDialog").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Fabric Supplier",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Edit": function(){
//						
//						//check if user have entered required fields
//						if(!validateRequiredFieldForEdit()){
//							callMessageDialog("Warning message", "Please enter required fields!");
//							
//							//get value of code and name
//							//var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
//							//var fabricSupplierCode = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
//							var shortName= $('#editFabricSupplierDialog').find('#txtShortName').val();
//							
//							//if user dont enter code or enter just space
////							if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null){
////								$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
////								$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
////								$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
////							}
//							//if user dont enter name or enter just space
//							if(shortName.trim() === '' || shortName == null){
//								$("#editFabricSupplierDialog").find("#errorTxtShortName").text("Please enter fabric supplier short name!");
//								$("#editFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
//								$("#editFabricSupplierDialog").find("#txtShortName").css("background-color", "red");
//							}
//						}else{
//							//if user have entered all required fields, then check if input is overRange
//							if(!validateOverRangeWhenEdit()){
//								callMessageDialog("Warning message", "Your input is over range!");
//							}else{
//								//all inputs are valid here.
//								
//								//edit part when inputs are valid
//								var fabricsupcode = $('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val();
//								//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
//								var shortname = $('#editFabricSupplierDialog').find('#txtShortName').val();
//								var longname = $('#editFabricSupplierDialog').find('#txtLongName').val();
//								var address = $('#editFabricSupplierDialog').find('#txtAddress').val();
//								var tel = $('#editFabricSupplierDialog').find('#txtTel').val();
//								var fax = $('#editFabricSupplierDialog').find('#txtFax').val();
//								var taxno = $('#editFabricSupplierDialog').find('#txtTaxNo').val();
//								var status = $('#editFabricSupplierDialog').find('#txtStatus').val();
////								var ischori;
////								if($('#editFabricSupplierDialog').find('#cbIsChori').is(":checked")){
////									//alert('đã check');
////									ischori= true;
////								}else{
////									//alert('bỏ check');
////									ischori= false;
////								}
//								
//								var fabricsuppliercontactModelList = [];
//								//lặp qua table contact
//								$('#editFabricSupplierDialog').find('#tblFabricSupplierContactList tr').each(function() {
//									var fabricsuppliercontactcode = $(this).data('id');
//									var fcName = $(this).find(".fcName").html();
//									var fcEmail = $(this).find(".fcEmail").html();
//									if(typeof fcName === "undefined"){
//										return true;//continue
//									}
//									//console.log(x+' '+y);
//									fabricsuppliercontactModelList.push({
//								          "fabricsuppliercontactcode": fabricsuppliercontactcode,
//								          "fabricsupplierCode": $('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val(),
//								          "creator": null,
//								          "name": fcName,
//								          "email": fcEmail,
//								          "createdate": null
//								        });
//		
//									//console.log(JSON.stringify(factorycontactModellist));
//								 });
//								//end lặp qua table contact
//								
//								var fabricSupplier = {
//										fabricsupcode: fabricsupcode,
//										shortname: shortname,
//										longname: longname,
//										address: address,
//										tel: tel,
//										fax: fax,
//										taxno: taxno,
//										status: status,
////										ischori: ischori,
//										fabricsuppliercontactModelList : fabricsuppliercontactModelList
//								};
//								
//								console.log(JSON.stringify(fabricSupplier));
//								//
//								
//								//gọi ajax để edit
//								$.ajax({
//									dataType: "json",
//									type: 'POST',
//									data: JSON.stringify(fabricSupplier),
//									contentType: "application/json",
//									url: "/Chori/fabricSupplier/edit",
//									success: function(data){
//										if(data.editStatus==true){
//											//alert("Edit thành công");
//											
//											reloadTableWithStatus();
//											callMessageDialog("Message", 'Edit Fabric Supplier successfully!');
//											$("#editFabricSupplierDialog").dialog("close");
//											resetAfterEdit();
//										}else if(data.editStatus==false){
//											alert("Edit thất bại");
//										}
//									},
//									error: function(){
//										alert("Lỗi cmnr");
//									}
//								});
//								//end edit part when inputs are valid
//								
//							}
//						}
//						
////						//edit part when inputs are valid
////						var fabricsupcode = $('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val();
////						//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
////						var shortname = $('#editFabricSupplierDialog').find('#txtShortName').val();
////						var longname = $('#editFabricSupplierDialog').find('#txtLongName').val();
////						var address = $('#editFabricSupplierDialog').find('#txtAddress').val();
////						var tel = $('#editFabricSupplierDialog').find('#txtTel').val();
////						var fax = $('#editFabricSupplierDialog').find('#txtFax').val();
////						var taxno = $('#editFabricSupplierDialog').find('#txtTaxNo').val();
////						var status = $('#editFabricSupplierDialog').find('#txtStatus').val();
////						var ischori;
////						if($('#editFabricSupplierDialog').find('#cbIsChori').is(":checked")){
////							//alert('đã check');
////							ischori= true;
////						}else{
////							//alert('bỏ check');
////							ischori= false;
////						}
////						
////						var fabricsuppliercontactModelList = [];
////						//lặp qua table contact
////						$('#editFabricSupplierDialog').find('#tblFabricSupplierContactList tr').each(function() {
////							var fabricsuppliercontactcode = $(this).data('id');
////							var fcName = $(this).find(".fcName").html();
////							var fcEmail = $(this).find(".fcEmail").html();
////							if(typeof fcName === "undefined"){
////								return true;//continue
////							}
////							//console.log(x+' '+y);
////							fabricsuppliercontactModelList.push({
////						          "fabricsuppliercontactcode": fabricsuppliercontactcode,
////						          "fabricsupplierCode": $('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val(),
////						          "creator": null,
////						          "name": fcName,
////						          "email": fcEmail,
////						          "createdate": null
////						        });
////
////							//console.log(JSON.stringify(factorycontactModellist));
////						 });
////						//end lặp qua table contact
////						
////						var fabricSupplier = {
////								fabricsupcode: fabricsupcode,
////								shortname: shortname,
////								longname: longname,
////								address: address,
////								tel: tel,
////								fax: fax,
////								taxno: taxno,
////								status: status,
////								ischori: ischori,
////								fabricsuppliercontactModelList : fabricsuppliercontactModelList
////						};
////						
////						console.log(JSON.stringify(fabricSupplier));
////						//
////						
////						//gọi ajax để edit
////						$.ajax({
////							dataType: "json",
////							type: 'POST',
////							data: JSON.stringify(fabricSupplier),
////							contentType: "application/json",
////							url: "/Chori/fabricSupplier/edit",
////							success: function(data){
////								if(data.editStatus==true){
////									//alert("Edit thành công");
////									
////									reloadTableWithStatus();
////									callMessageDialog("Message", 'Edit Fabric Supplier successfully!');
////									$("#editFabricSupplierDialog").dialog("close");
////									resetAfterEdit();
////								}else if(data.editStatus==false){
////									alert("Edit thất bại");
////								}
////							},
////							error: function(){
////								alert("Lỗi cmnr");
////							}
////						});
////						//end edit part when inputs are valid
//					},
//					"Cancel": function(){
//						$("#editFabricSupplierDialog").dialog("close");
//						//Xóa các thông tin trong bảng contact
//						resetAfterEdit();
//					}
//				},
//				close: function(){
//					resetAfterEdit();
//				}
//			});
//		});
//		
//		$('.btnDelete').click(function(){
//			var fabricSupplierCode= $(this).data('id');
//			$("#deleteFabricSupplierDialog").find("#messageContent").text('Are you sure you want to delete Fabric Supplier "' + fabricSupplierCode+'"?');
//			
//			$("#deleteFabricSupplierDialog").dialog({
//	    		show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete Fabric Supplier Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/fabricSupplier/delete/" + fabricSupplierCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete Fabric Supplier "'+ fabricSupplierCode+ '" successfully!');
//										//close dialog
//										$("#deleteFabricSupplierDialog").dialog("close");
//										//reload table
////										$("#listFabricSupplier").dataTable().fnDestroy();
////										$('#listFabricSupplier tbody').empty();
////										loadData();
//										reloadTableWithStatus();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t Delete Fabric Supplier "'+ fabricSupplierCode+ '"!');
//										$("#deleteFabricSupplierDialog").dialog("close");
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
//						$("#deleteFabricSupplierDialog").dialog("close");
//					}
//				}
//			});
//		});
//	}
	
	//chuyển hàm action qua dropdownlist
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    //lấy ra data-id
			var fabricSupplierCode= $(this).data('id');
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
				//lấy thông tin qua id gán vào edit dialog
				$.ajax({
					dataType: "json",
					type: 'POST',
					data: JSON.stringify({
						fabricsupcode: fabricSupplierCode
					}),
					url: getAbsolutePath() +  "fabricSupplier/detail",
					contentType: "application/json",
					success: function(data){
						//alert(data.factory.factorycode);
						//gán các giá trị vào dialog
						$('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val(data.fabricSupplier.fabricsupcode);
						$('#editFabricSupplierDialog').find('#txtShortName').val(data.fabricSupplier.shortname);
						$('#editFabricSupplierDialog').find('#txtLongName').val(data.fabricSupplier.longname);
						$('#editFabricSupplierDialog').find('#txtAddress').val(data.fabricSupplier.address);
						$('#editFabricSupplierDialog').find('#txtTel').val(data.fabricSupplier.tel);
						$('#editFabricSupplierDialog').find('#txtFax').val(data.fabricSupplier.fax);
						$('#editFabricSupplierDialog').find('#txtTaxNo').val(data.fabricSupplier.taxno);
						$('#editFabricSupplierDialog').find('#txtStatus').val(data.fabricSupplier.status);
						$('#editFabricSupplierDialog').find('#txtRemark').val(data.fabricSupplier.remark);
						
						var ischori = data.fabricSupplier.ischori;
						if(ischori){
							$('#editFabricSupplierDialog').find('#cbIsChori').prop( "checked", true );
						}else{
							$('#editFabricSupplierDialog').find('#cbIsChori').prop( "checked", false );
						}
						
						//duyệt qua mảng:
						$.each(data.fabricSupplier.fabricsuppliercontactModelList,function(key,value){
							//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
							$("#tblFabricSupplierContactList tbody").append('<tr data-id="'+value.fabricsuppliercontactcode+'"><td class="fcName">'+value.name+'</td><td class="fcEmail">'+value.email+'</td>'+'</td><td class="fcTelephone">'+value.tel+'</td><td><button class="btn btn-info btnEditFC">Edit</button><button class="btn btn-danger btnDeleteFC">Delete</button></td></tr>');
							
							$(".btnEditFC").bind("click", Edit);
							$(".btnDeleteFC").bind("click", Delete);
						});
					},
					error: function(){
						alert("Lỗi lấy by id")
					}
				});
				//end lấy thông tin qua id gán vào edit dialog
				
				$("#editFabricSupplierDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Fabric Supplier",
					height: 550,
					width: 1000,
					buttons:{
						"Edit": function(){
							
							//check if user have entered required fields
							if(!validateRequiredFieldForEdit()){
								callMessageDialog("Warning message", "Please enter required fields!");
								
								//get value of code and name
								//var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
								//var fabricSupplierCode = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
								var shortName= $('#editFabricSupplierDialog').find('#txtShortName').val();
								
								//if user dont enter code or enter just space
//								if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null){
//									$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
//									$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
//									$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
//								}
								//if user dont enter name or enter just space
								if(shortName.trim() === '' || shortName == null){
									$("#editFabricSupplierDialog").find("#errorTxtShortName").text("Please enter fabric supplier short name!");
									$("#editFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
									$("#editFabricSupplierDialog").find("#txtShortName").css("background-color", "red");
								}
							}else{
								//if user have entered all required fields, then check if input is overRange
								if(!validateOverRangeWhenEdit()){
									callMessageDialog("Warning message", "Your input is over range!");
								}else{
									//all inputs are valid here.
									
									//edit part when inputs are valid
									var fabricsupcode = $('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val();
									//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
									var shortname = $('#editFabricSupplierDialog').find('#txtShortName').val();
									var longname = $('#editFabricSupplierDialog').find('#txtLongName').val();
									var address = $('#editFabricSupplierDialog').find('#txtAddress').val();
									var tel = $('#editFabricSupplierDialog').find('#txtTel').val();
									var fax = $('#editFabricSupplierDialog').find('#txtFax').val();
									var taxno = $('#editFabricSupplierDialog').find('#txtTaxNo').val();
									var status = $('#editFabricSupplierDialog').find('#txtStatus').val();
									var remark = $('#editFabricSupplierDialog').find('#txtRemark').val();
//									var ischori;
//									if($('#editFabricSupplierDialog').find('#cbIsChori').is(":checked")){
//										//alert('đã check');
//										ischori= true;
//									}else{
//										//alert('bỏ check');
//										ischori= false;
//									}
									
									var fabricsuppliercontactModelList = [];
									//lặp qua table contact
									$('#editFabricSupplierDialog').find('#tblFabricSupplierContactList tr').each(function() {
										var fabricsuppliercontactcode = $(this).data('id');
										var fcName = $(this).find(".fcName").html();
										var fcEmail = $(this).find(".fcEmail").html();
										var fcTelephone = $(this).find(".fcTelephone").html();
										if(typeof fcName === "undefined"){
											return true;//continue
										}
										//console.log(x+' '+y);
										fabricsuppliercontactModelList.push({
									          "fabricsuppliercontactcode": fabricsuppliercontactcode,
									          "fabricsupplierCode": $('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val(),
									          "creator": null,
									          "name": fcName,
									          "email": fcEmail,
									          "tel": fcTelephone,
									          "createdate": null
									        });
			
										//console.log(JSON.stringify(factorycontactModellist));
									 });
									//end lặp qua table contact
									
									var fabricSupplier = {
											fabricsupcode: fabricsupcode,
											shortname: shortname,
											longname: longname,
											address: address,
											tel: tel,
											fax: fax,
											taxno: taxno,
											status: status,
											remark: remark,
											fabricsuppliercontactModelList : fabricsuppliercontactModelList
									};
									
									console.log(JSON.stringify(fabricSupplier));
									//
									
									//gọi ajax để edit
									$.ajax({
										dataType: "json",
										type: 'POST',
										data: JSON.stringify(fabricSupplier),
										contentType: "application/json",
										url: getAbsolutePath() +  "fabricSupplier/edit",
										success: function(data){
											if(data.editStatus==true){
												//alert("Edit thành công");
												
												reloadTableWithStatus();
												callMessageDialog("Message", 'Edit fabric supplier successfully!');
												$("#editFabricSupplierDialog").dialog("close");
												resetAfterEdit();
											}else if(data.editStatus==false){
												alert("Edit thất bại");
											}
										},
										error: function(){
											alert("Lỗi cmnr");
										}
									});
									//end edit part when inputs are valid
									
								}
							}
						},
						"Cancel": function(){
							$("#editFabricSupplierDialog").dialog("close");
							//Xóa các thông tin trong bảng contact
							resetAfterEdit();
						}
					},
					close: function(){
						resetAfterEdit();
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	$("#deleteFabricSupplierDialog").find("#messageContent").text('Are you sure you want to delete fabric supplier "' + fabricSupplierCode+'"?');
				
				$("#deleteFabricSupplierDialog").dialog({
					modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Fabric Supplier Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									fabricsupcode: fabricSupplierCode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "fabricSupplier/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete fabric supplier "'+ fabricSupplierCode+ '" successfully!');
											//close dialog
											$("#deleteFabricSupplierDialog").dialog("close");
											//reload table
//											$("#listFabricSupplier").dataTable().fnDestroy();
//											$('#listFabricSupplier tbody').empty();
//											loadData();
											reloadTableWithStatus();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t delete fabric supplier "'+ fabricSupplierCode+ '"!');
											$("#deleteFabricSupplierDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
//									alert("unexpected error!");
									callMessageDialog("Message", 'Can\'t delete fabric supplier "'+ fabricSupplierCode+ '"!');
									$("#deleteFabricSupplierDialog").dialog("close");
								}
							});
						},
						"Cancel": function(){
							$("#deleteFabricSupplierDialog").dialog("close");
						}
					}
				});
		    };
		    //end if user choose delete option
		    
		});
	};
	//end chuyển action qua dropdownlist
	
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
			url: getAbsolutePath() +  "fabricSupplier/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listFabricSupplier").dataTable().fnDestroy();
				$('#listFabricSupplier tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.fabricsuppliercontactModelList.length>0){
						tmp+='<table border="0">';
						$.each(value.fabricsuppliercontactModelList,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td>'+'<td>'+value1.tel+'</td>'+'</tr>';
						});
						tmp+='</table>';
					}
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.fabricsupcode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
//								$('<td>').text(value.ischori==true?"Yes":"No"),
								$('<td>').text(value.address==null?'':value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').html(value.fabricsuppliercontactModelList.length==0?'':tmp),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.fabricsupcode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.fabricsupcode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.fabricsupcode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listFabricSupplier');
						
					}else{//không thì xuất ra theo cái status
//						$("#listFactory").dataTable().fnDestroy();
//						$('#listFactory tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.fabricsupcode),
									$('<td>').text(value.shortname),
									$('<td>').text(value.longname),
//									$('<td>').text(value.ischori==true?"Yes":"No"),
									$('<td>').text(value.address==null?'':value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),
									$('<td>').html(value.fabricsuppliercontactModelList.length==0?'':tmp),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.fabricsupcode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.fabricsupcode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.fabricsupcode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listFabricSupplier');
						}
					}
				});
				action();

				$('#listFabricSupplier').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
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
			url: getAbsolutePath() +  "fabricSupplier/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listFabricSupplier").dataTable().fnDestroy();
				$('#listFabricSupplier tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.fabricsuppliercontactModelList.length>0){
						tmp+='<table border="0">';
						$.each(value.fabricsuppliercontactModelList,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td>'+'<td>'+value1.tel+'</td>'+'</tr>';
						});
						tmp+='</table>';
					}
					
					//nếu value là all, 
					if(valueSelected=='All'){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.fabricsupcode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
//								$('<td>').text(value.ischori==true?"Yes":"No"),
								$('<td>').text(value.address==null?'':value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').html(value.fabricsuppliercontactModelList.length==0?'':tmp),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.fabricsupcode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.fabricsupcode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.fabricsupcode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listFabricSupplier');
						
					}else{//không thì xuất ra theo cái status
//						$("#listFactory").dataTable().fnDestroy();
//						$('#listFactory tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.fabricsupcode),
									$('<td>').text(value.shortname),
									$('<td>').text(value.longname),
//									$('<td>').text(value.ischori==true?"Yes":"No"),
									$('<td>').text(value.address==null?'':value.address),
									$('<td>').text(value.tel),
									$('<td>').text(value.fax),
									$('<td>').text(value.taxno),
									$('<td>').html(value.fabricsuppliercontactModelList.length==0?'':tmp),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.fabricsupcode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.fabricsupcode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.fabricsupcode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listFabricSupplier');
						}
					}
				});
				action();

				$('#listFabricSupplier').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
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
	$('#btnAddNewFabricSupplier').click(function(){
		$("#addFabricSupplierDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Fabric Supplier",
			height: 550,
			width: 1000,
			buttons:{
				"Save": function(){
					var fabricSupCodeInput = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						//get value of code and name
						//var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
						var fabricSupplierCode = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
						var shortName= $('#addFabricSupplierDialog').find('#txtShortName').val();
						
						//if user dont enter code or enter just space
						if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null){
							$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
							$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
							$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
						}
						//if user dont enter name or enter just space
						if(shortName.trim() === '' || shortName == null){
							$("#addFabricSupplierDialog").find("#errorTxtShortName").text("Please enter fabric supplier short name!");
							$("#addFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
							$("#addFabricSupplierDialog").find("#txtShortName").css("background-color", "red");
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
									fabricsupcode: fabricSupCodeInput
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "fabricSupplier/isExist",
								success: function(data){
									if(data.isExisted==true){
										//if code is existed
										$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Fabric supplier code you enter is existed, please choose different one!");
										$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
										$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
									}else if(data.isExisted==false){
										//alert("all input is ok!");
										$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Fabric supplier code is valid!");
										$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "green");
										$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "white");
										
										//add new fabric supplier here
										//main code when input is already valid
										var fabricsupcode = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
										//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
										var shortname = $('#addFabricSupplierDialog').find('#txtShortName').val();
										var longname = $('#addFabricSupplierDialog').find('#txtLongName').val();
										var address = $('#addFabricSupplierDialog').find('#txtAddress').val();
										var tel = $('#addFabricSupplierDialog').find('#txtTel').val();
										var fax = $('#addFabricSupplierDialog').find('#txtFax').val();
										var taxno = $('#addFabricSupplierDialog').find('#txtTaxNo').val();
										var status = $('#addFabricSupplierDialog').find('#txtStatus').val();
										var remark = $('#addFabricSupplierDialog').find('#txtRemark').val();
//										var ischori;
//										if($('#addFabricSupplierDialog').find('#cbIsChori').is(":checked")){
//											//alert('đã check');
//											ischori= true;
//										}else{
//											//alert('bỏ check');
//											ischori= false;
//										}
										//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
										
										var fabricsuppliercontactModelList = [];
										//lặp qua table contact
										$('#tblFabricSupplierContactList tr').each(function() {
											var fcName = $(this).find(".fcName").html();
											var fcEmail = $(this).find(".fcEmail").html();
											if(typeof fcName === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											fabricsuppliercontactModelList.push({
										          "fabricsuppliercontactcode": null,
										          "fabricsupplierCode": $('#addFactoryDialog').find('#txtFabricSupplierCode').val(),
										          "creator": null,
										          "name": fcName,
										          "email": fcEmail,
										          "createdate": null
										        });
					
											//console.log(JSON.stringify(fabricsuppliercontactModelList));
										 });
										//end lặp qua table contact
										
										var fabricSupplier = {
												fabricsupcode: fabricsupcode,
												shortname: shortname,
												longname: longname,
												address: address,
												tel: tel,
												fax: fax,
												taxno: taxno,
												status: status,
												remark: remark,
												fabricsuppliercontactModelList : fabricsuppliercontactModelList
										};
										
										console.log(JSON.stringify(fabricSupplier));
										//gọi ajax để add
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify(fabricSupplier),
											contentType: "application/json",
											url: getAbsolutePath() +  "fabricSupplier/add",//làm đến đây
											success: function(data){
												//alert("OK");
												if(data.addStatus==true){
													//alert("OK");
													reloadTableWithStatus();
													callMessageDialog("Message", 'Add new fabric supplier successfully!');
													$("#addFabricSupplierDialog").dialog("close");
													resetAfterAdd();
												}
											},
											error: function(){
												alert("Lỗi cmnr");
											}
										});
										//end main code when input is already valid
									}
								},
								error: function(){
									//alert("Lỗi cmnr");
									//here user delete code
								}
							});
						}
					}
					
//					//main code when input is already valid
//					var fabricsupcode = $('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val();
//					//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
//					var shortname = $('#addFabricSupplierDialog').find('#txtShortName').val();
//					var longname = $('#addFabricSupplierDialog').find('#txtLongName').val();
//					var address = $('#addFabricSupplierDialog').find('#txtAddress').val();
//					var tel = $('#addFabricSupplierDialog').find('#txtTel').val();
//					var fax = $('#addFabricSupplierDialog').find('#txtFax').val();
//					var taxno = $('#addFabricSupplierDialog').find('#txtTaxNo').val();
//					var status = $('#addFabricSupplierDialog').find('#txtStatus').val();
//					var ischori;
//					if($('#addFabricSupplierDialog').find('#cbIsChori').is(":checked")){
//						//alert('đã check');
//						ischori= true;
//					}else{
//						//alert('bỏ check');
//						ischori= false;
//					}
//					//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
//					
//					var fabricsuppliercontactModelList = [];
//					//lặp qua table contact
//					$('#tblFabricSupplierContactList tr').each(function() {
//						var fcName = $(this).find(".fcName").html();
//						var fcEmail = $(this).find(".fcEmail").html();
//						if(typeof fcName === "undefined"){
//							return true;//continue
//						}
//						//console.log(x+' '+y);
//						fabricsuppliercontactModelList.push({
//					          "fabricsuppliercontactcode": null,
//					          "fabricsupplierCode": $('#addFactoryDialog').find('#txtFabricSupplierCode').val(),
//					          "creator": null,
//					          "name": fcName,
//					          "email": fcEmail,
//					          "createdate": null
//					        });
//
//						//console.log(JSON.stringify(fabricsuppliercontactModelList));
//					 });
//					//end lặp qua table contact
//					
//					var fabricSupplier = {
//							fabricsupcode: fabricsupcode,
//							shortname: shortname,
//							longname: longname,
//							address: address,
//							tel: tel,
//							fax: fax,
//							taxno: taxno,
//							status: status,
//							ischori: ischori,
//							fabricsuppliercontactModelList : fabricsuppliercontactModelList
//					};
//					
//					console.log(JSON.stringify(fabricSupplier));
//					//gọi ajax để add
//					$.ajax({
//						dataType: "json",
//						type: 'POST',
//						data: JSON.stringify(fabricSupplier),
//						contentType: "application/json",
//						url: "/Chori/fabricSupplier/add",//làm đến đây
//						success: function(data){
//							//alert("OK");
//							if(data.addStatus==true){
//								//alert("OK");
//								reloadTableWithStatus();
//								callMessageDialog("Message", 'Insert new Fabric Supplier successfully!');
//								$("#addFabricSupplierDialog").dialog("close");
//								resetAfterAdd();
//							}
//						},
//						error: function(){
//							alert("Lỗi cmnr");
//						}
//					});
//					//end main code when input is already valid
				},
				"Cancel": function(){
					$("#addFabricSupplierDialog").dialog("close");
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
		$('#addFabricSupplierDialog').find('#txtFabricSupplierCode').val('');
		$('#addFabricSupplierDialog').find('#txtShortName').val('');
		$('#addFabricSupplierDialog').find('#txtLongName').val('');
		$('#addFabricSupplierDialog').find('#txtAddress').val('');
		$('#addFabricSupplierDialog').find('#txtTel').val('');
		$('#addFabricSupplierDialog').find('#txtFax').val('');
		$('#addFabricSupplierDialog').find('#txtTaxNo').val('');
		$('#addFabricSupplierDialog').find('#txtStatus').val('');
		$('#addFabricSupplierDialog').find('#txtRemark').val('');
		$('#addFabricSupplierDialog').find('#cbIsChori').prop( "checked", false );
		$("#tblFabricSupplierContactList > tbody").html("");
		
		//reset css
		$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("");
		$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtShortName").text("");
		$("#addFabricSupplierDialog").find("#txtShortName").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtLongName").text("");
		$("#addFabricSupplierDialog").find("#txtLongName").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtAddress").text("");
		$("#addFabricSupplierDialog").find("#txtAddress").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtTel").text("");
		$("#addFabricSupplierDialog").find("#txtTel").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtFax").text("");
		$("#addFabricSupplierDialog").find("#txtFax").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtTaxNo").text("");
		$("#addFabricSupplierDialog").find("#txtTaxNo").css("background-color", "white");
		$("#addFabricSupplierDialog").find("#errorTxtRemark").text("");
		$("#addFabricSupplierDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi thêm mới
	
	//hàm reset các trường trong bảng sau khi edit
	function resetAfterEdit(){
		$('#editFabricSupplierDialog').find('#txtFabricSupplierCode').val('');
		$('#editFabricSupplierDialog').find('#txtShortName').val('');
		$('#editFabricSupplierDialog').find('#txtLongName').val('');
		$('#editFabricSupplierDialog').find('#txtAddress').val('');
		$('#editFabricSupplierDialog').find('#txtTel').val('');
		$('#editFabricSupplierDialog').find('#txtFax').val('');
		$('#editFabricSupplierDialog').find('#txtTaxNo').val('');
		$('#editFabricSupplierDialog').find('#txtStatus').val('');
		$('#editFabricSupplierDialog').find('#txtRemark').val('');
		$('#editFabricSupplierDialog').find('#cbIsChori').prop( "checked", false );
		$("#tblFabricSupplierContactList > tbody").html("");
		
		//reset css
		$("#editFabricSupplierDialog").find("#errorTxtShortName").text("");
		$("#editFabricSupplierDialog").find("#txtShortName").css("background-color", "white");
		$("#editFabricSupplierDialog").find("#errorTxtLongName").text("");
		$("#editFabricSupplierDialog").find("#txtLongName").css("background-color", "white");
		$("#editFabricSupplierDialog").find("#errorTxtAddress").text("");
		$("#editFabricSupplierDialog").find("#txtAddress").css("background-color", "white");
		$("#editFabricSupplierDialog").find("#errorTxtTel").text("");
		$("#editFabricSupplierDialog").find("#txtTel").css("background-color", "white");
		$("#editFabricSupplierDialog").find("#errorTxtFax").text("");
		$("#editFabricSupplierDialog").find("#txtFax").css("background-color", "white");
		$("#editFabricSupplierDialog").find("#errorTxtTaxNo").text("");
		$("#editFabricSupplierDialog").find("#txtTaxNo").css("background-color", "white");
		$("#editFabricSupplierDialog").find("#errorTxtRemark").text("");
		$("#editFabricSupplierDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi edit
	
	/**
	 * Các hàm thêm mới vào bảng FactoryContact
	 */
	$(function(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditFC").bind("click", Edit);
		$(".btnDeleteFC").bind("click", Delete);
		$("#btnAddNewFabricSupplierContact").bind("click", Add);
		$('#editFabricSupplierDialog').find('#btnAddNewFabricSupplierContact').bind("click", Add);
	});
	
//	function Bind4Edit(){
//		$(".btnEditFC").bind("click", Edit);
//		$(".btnDeleteFC").bind("click", Delete);
//		$("#btnAddNewFactoryContact").bind("click", Add);
//	}
	
	//hàm add 1 dòng vào table FactoryContactList
	function Add(){
		$("#tblFabricSupplierContactList tbody").append(
				"<tr data-id='0'>"+
				"<td><input maxlength='50' type='text'/></td>"+
				"<td><input maxlength='100' type='text'/></td>"+
				"<td><input maxlength='50' type='text'/></td>"+
				"<td><button class='btn btn-info btnSaveFC'>Save</button><button class='btn btn-danger btnDeleteFC'>Delete</button></td>"+
				"</tr>");
			
				$(".btnSaveFC").bind("click", Save);		
				$(".btnDeleteFC").bind("click", Delete);
	};
	//end hàm add 1 dòng vào table FactoryContactList
	
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
//			tdButtons.html("<button class='btn btn-info btnEditFC'>Edit</button><button class='btn btn-danger btnDeleteFC'>Delete</button>");
//
//			$(".btnEditFC").bind("click", Edit);
//			$(".btnDeleteFC").bind("click", Delete);
//		}
		
		//không nhập gì
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
	
	//
	function Edit(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTelephone = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

		tdName.html("<input maxlength='50' type='text' id='txtName' value='"+tdName.html()+"'/>");
		tdEmail.html("<input maxlength='100' type='text' id='txtEmail' value='"+tdEmail.html()+"'/>");
		tdTelephone.html("<input maxlength='50' type='text' id='txtTelephone' value='"+tdTelephone.html()+"'/>");
		tdButtons.html("<button class='btn btn-info btnSaveFC'>Save</button>");

		$(".btnSaveFC").bind("click", Save);
		$(".btnEditFC").bind("click", Edit);
		$(".btnDeleteFC").bind("click", Delete);
	};
	//
	
	//
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
	 * -----------------------------------------
	 * 
	 */
	
	//do not allow input " character 
	$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var fabricSupplierCode= $("#addFabricSupplierDialog").find("#txtFabricSupplierCode").val();
		if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null)
			return false;
		
		var shortName= $("#addFabricSupplierDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new fabric supplier
	 */
	function validateOverRangeWhenAdd(){
		if($("#addFabricSupplierDialog").find("#txtFabricSupplierCode").val().length>50 || $("#addFabricSupplierDialog").find("#txtShortName").val().length>50 
				|| $("#addFabricSupplierDialog").find("#txtLongName").val().length>100 || $("#addFabricSupplierDialog").find("#txtAddress").val().length>200
				|| $("#addFabricSupplierDialog").find("#txtTel").val().length>50 || $("#addFabricSupplierDialog").find("#txtFax").val().length>50
				|| $("#addFabricSupplierDialog").find("#txtTaxNo").val().length>50)
			return false;
		return true;
	}
	
	/**
	 * fabric supplier code on keyup function() to check if user entered, over range, fabric supplier is existed (3 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").on('change keyup paste',function(){
		var fabricSupplierCode= $(this).val();
		
		//if user dont enter or enter just space
		if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null){
			$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
			$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		if(fabricSupplierCode.length>50){
			$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("The code field's length is 50. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				fabricsupcode: fabricSupplierCode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "fabricSupplier/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Fabric supplier code you enter is existed, please choose different one!");
					$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
					$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
				}else if(data.isExisted==false&&$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").val().length<51){
					//if code is not existed and valid length
					$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Fabric supplier code is valid!");
					$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "green");
					$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "white");
					//if user dont enter or enter just space
					if(fabricSupplierCode.trim() === '' || fabricSupplierCode == null){
						$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
						$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
						$(this).css("background-color", "red");
					}
				}
			},
			error: function(){
				//when user clear the code
				$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").text("Please enter fabric supplier code!");
				$("#addFabricSupplierDialog").find("#errorTxtFabricSupplierCode").css("color", "red");
				$("#addFabricSupplierDialog").find("#txtFabricSupplierCode").css("background-color", "red");
			}
		});
	});
	
	/**
	 * fabric supplier's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#addFabricSupplierDialog").find("#errorTxtShortName").text("Please enter fabric supplier short name!");
			$("#addFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#addFabricSupplierDialog").find("#errorTxtShortName").text("The shortName field's length is 50. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addFabricSupplierDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier long name on keyup function() to check if over range (1 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#addFabricSupplierDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#addFabricSupplierDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier address on keyup function() to check if over range (1 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#addFabricSupplierDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#addFabricSupplierDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier tel on keyup function() to check if over range (1 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#addFabricSupplierDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#addFabricSupplierDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier fax on keyup function() to check if over range (1 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#addFabricSupplierDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#addFabricSupplierDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * fabric supplier tax no on keyup function() to check if over range (1 cases)
	 */
	$("#addFabricSupplierDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#addFabricSupplierDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#addFabricSupplierDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#addFabricSupplierDialog").find("#errorTxtTaxNo").text("");
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
		
		var shortName= $("#editFabricSupplierDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit fabric supplier
	 */
	function validateOverRangeWhenEdit(){
		if($("#editFabricSupplierDialog").find("#txtShortName").val().length>50 
				|| $("#editFabricSupplierDialog").find("#txtLongName").val().length>100 || $("#editFabricSupplierDialog").find("#txtAddress").val().length>200
				|| $("#editFabricSupplierDialog").find("#txtTel").val().length>50 || $("#editFabricSupplierDialog").find("#txtFax").val().length>50
				|| $("#editFabricSupplierDialog").find("#txtTaxNo").val().length>50)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] fabric supplier's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editFabricSupplierDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#editFabricSupplierDialog").find("#errorTxtShortName").text("Please enter fabric supplier short name!");
			$("#editFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#editFabricSupplierDialog").find("#errorTxtShortName").text("The shortName field's length is 50. Your input is over range!");
			$("#editFabricSupplierDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editFabricSupplierDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier long name on keyup function() to check if over range (1 cases)
	 */
	$("#editFabricSupplierDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#editFabricSupplierDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#editFabricSupplierDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#editFabricSupplierDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier address on keyup function() to check if over range (1 cases)
	 */
	$("#editFabricSupplierDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#editFabricSupplierDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#editFabricSupplierDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#editFabricSupplierDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier tel on keyup function() to check if over range (1 cases)
	 */
	$("#editFabricSupplierDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#editFabricSupplierDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#editFabricSupplierDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#editFabricSupplierDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier fax on keyup function() to check if over range (1 cases)
	 */
	$("#editFabricSupplierDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#editFabricSupplierDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#editFabricSupplierDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#editFabricSupplierDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] fabric supplier tax no on keyup function() to check if over range (1 cases)
	 */
	$("#editFabricSupplierDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#editFabricSupplierDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#editFabricSupplierDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#editFabricSupplierDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	//
//	loadData();
})