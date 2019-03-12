$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "factory/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.factorycontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.factorycontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td>'+'<td>'+value1.tel+'</td>'+'</tr>';
						});
						tmp+='</table>';
					}
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.factorycode),
							$('<td>').text(value.shortname==null?'':value.shortname),
							$('<td>').text(value.longname==null?'':value.longname),
							$('<td>').text(value.address==null?'':value.address),
							$('<td>').text(value.tel==null?'':value.tel),
							$('<td>').text(value.fax==null?'':value.fax),
							$('<td>').text(value.taxno==null?'':value.taxno),
							$('<td>').html(value.factorycontactModellist.length==0?'':tmp),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.factorycode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.factorycode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id=\''+value.factorycode+'\'>'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listFactory');
				});
				action();

				$('#listFactory').DataTable( {
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
//			var facCode= $(this).data('id');
//			
//			//lấy thông tin qua id gán vào edit dialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: "/Chori/factory/detail/"+facCode,
//				contentType: "application/json",
//				success: function(data){
//					//alert(data.factory.factorycode);
//					//gán các giá trị vào dialog
//					$('#editFactoryDialog').find('#txtFactoryCode').val(data.factory.factorycode);
//					//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
//					$('#editFactoryDialog').find('#txtShortName').val(data.factory.shortname);
//					$('#editFactoryDialog').find('#txtLongName').val(data.factory.longname);
//					$('#editFactoryDialog').find('#txtAddress').val(data.factory.address);
//					$('#editFactoryDialog').find('#txtTel').val(data.factory.tel);
//					$('#editFactoryDialog').find('#txtFax').val(data.factory.fax);
//					$('#editFactoryDialog').find('#txtTaxNo').val(data.factory.taxno);
//					$('#editFactoryDialog').find('#txtStatus').val(data.factory.status);
//					
//					//duyệt qua mảng:
//					$.each(data.factory.factorycontactModellist,function(key,value){
//						//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
//						$("#tblFactoryContactList tbody").append('<tr data-id="'+value.factorycontactcode+'"><td class="fcName">'+value.name+'</td><td class="fcEmail">'+value.email+'</td><td><button class="btn btn-danger btnDeleteFC">Delete</button><button class="btn btn-info btnEditFC">Edit</button></td></tr>');
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
//			$("#editFactoryDialog").dialog({
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Factory",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Edit": function(){
//						
//						//check if user have entered required fields
//						if(!validateRequiredFieldForEdit()){
//							callMessageDialog("Warning message", "Please enter required fields!");
//							
//							var shortName= $('#editFactoryDialog').find('#txtShortName').val();
//							
//							//if user dont enter name or enter just space
//							if(shortName.trim() === '' || shortName == null){
//								$("#editFactoryDialog").find("#errorTxtShortName").text("Please enter factory short name!");
//								$("#editFactoryDialog").find("#errorTxtShortName").css("color", "red");
//								$("#editFactoryDialog").find("#txtShortName").css("background-color", "red");
//							}
//						}else{
//							//if user have entered all required fields, then check if input is overRange
//							if(!validateOverRangeWhenEdit()){
//								callMessageDialog("Warning message", "Your input is over range!");
//							}else{
//								//start editing
//								var factorycode = $('#editFactoryDialog').find('#txtFactoryCode').val();
//								var shortname = $('#editFactoryDialog').find('#txtShortName').val();
//								var longname = $('#editFactoryDialog').find('#txtLongName').val();
//								var address = $('#editFactoryDialog').find('#txtAddress').val();
//								var tel = $('#editFactoryDialog').find('#txtTel').val();
//								var fax = $('#editFactoryDialog').find('#txtFax').val();
//								var taxno = $('#editFactoryDialog').find('#txtTaxNo').val();
//								var status = $('#editFactoryDialog').find('#txtStatus').val();
//								
//								var factorycontactModellist = [];
//								//lặp qua table contact
//								$('#editFactoryDialog').find('#tblFactoryContactList tr').each(function() {
//									var factorycontactcode = $(this).data('id');
//									var fcName = $(this).find(".fcName").html();
//									var fcEmail = $(this).find(".fcEmail").html();
//									if(typeof fcName === "undefined"){
//										return true;//continue
//									}
//									//console.log(x+' '+y);
//									factorycontactModellist.push({
//								          "factorycontactcode": factorycontactcode,
//								          "factoryCode": $('#editFactoryDialog').find('#txtFactoryCode').val(),
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
//								var factory = {
//										factorycode: factorycode,
//										shortname: shortname,
//										longname: longname,
//										address: address,
//										tel: tel,
//										fax: fax,
//										taxno: taxno,
//										status: status,
//										factorycontactModellist : factorycontactModellist
//								};
//								
//								console.log(JSON.stringify(factory));
//								//
//								
//								//gọi ajax để edit
//								$.ajax({
//									dataType: "json",
//									type: 'POST',
//									data: JSON.stringify(factory),
//									contentType: "application/json",
//									url: "/Chori/factory/edit",
//									success: function(data){
//										//alert("OK");
//										if(data.editStatus==true){
//											//alert("Edit thành công");
//											reloadTableWithStatus();
//											callMessageDialog("Message", 'Edit Factory successfully!');
//											$("#editFactoryDialog").dialog("close");
//											resetAfterEdit();
//										}else if(data.editStatus==false){
//											alert("Edit thất bại");
//										}
//									},
//									error: function(){
//										alert("Lỗi cmnr");
//									}
//								});
//								//end editing
//							}
//						}
//					},
//					"Cancel": function(){
//						$("#editFactoryDialog").dialog("close");
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
//			var facCode= $(this).data('id');
//			$("#deleteFactoryDialog").find("#messageContent").text('Are you sure you want to delete Factory "' + facCode+'"?');
//			
//			$("#deleteFactoryDialog").dialog({
//	    		show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete Factory Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/factory/delete/" + facCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete Factory "'+ facCode+ '" successfully!');
//										//close dialog
//										$("#deleteFactoryDialog").dialog("close");
//										//reload table
////										$("#listFactory").dataTable().fnDestroy();
////										$('#listFactory tbody').empty();
////										loadData();
//										reloadTableWithStatus();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t Delete Factory "'+ facCode+ '"!');
//										$("#deleteFactoryDialog").dialog("close");
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
//						$("#deleteFactoryDialog").dialog("close");
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
			var facCode= $(this).data('id');
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
				//lấy thông tin qua id gán vào edit dialog
				$.ajax({
					dataType: "json",
					type: 'POST',
					data: JSON.stringify({
						factorycode: facCode
					}),
					url: getAbsolutePath() +  "factory/detail",
					contentType: "application/json",
					success: function(data){
						//alert(data.factory.factorycode);
						//gán các giá trị vào dialog
						$('#editFactoryDialog').find('#txtFactoryCode').val(data.factory.factorycode);
						//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
						$('#editFactoryDialog').find('#txtShortName').val(data.factory.shortname);
						$('#editFactoryDialog').find('#txtLongName').val(data.factory.longname);
						$('#editFactoryDialog').find('#txtAddress').val(data.factory.address);
						$('#editFactoryDialog').find('#txtTel').val(data.factory.tel);
						$('#editFactoryDialog').find('#txtFax').val(data.factory.fax);
						$('#editFactoryDialog').find('#txtTaxNo').val(data.factory.taxno);
						$('#editFactoryDialog').find('#txtStatus').val(data.factory.status);
						$('#editFactoryDialog').find('#txtRemark').val(data.factory.remark);
						
						//duyệt qua mảng:
						$.each(data.factory.factorycontactModellist,function(key,value){
							//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
							$("#tblFactoryContactList tbody").append('<tr data-id="'+value.factorycontactcode+'"><td class="fcName">'+value.name+'</td><td class="fcEmail">'+value.email+'</td><td class="fcTelephone">'+value.tel+'</td><td><button class="btn btn-info btnEditFC">Edit</button><button class="btn btn-danger btnDeleteFC">Delete</button></td></tr>');
							
							$(".btnEditFC").bind("click", Edit);
							$(".btnDeleteFC").bind("click", Delete);
						});
						
						//duyệt qua mảng:
						$.each(data.factory.factoryaccountinformationModelList,function(key,value){
							//tmp+='<tr><td>'+value.name+'</td><td>'+value.email+'</td></tr>';
							$("#tblFactoryaccountinformationList tbody").append('<tr data-id="'+value.factoryaccountinfocode+'"><td class="fcBankName">'+value.bankname+'</td><td class="fcBankBranch">'+value.bankbranch+'</td><td class="fcAccountNumber">'+value.accountnumber+'</td><td class="fcAddress">'+value.address+'</td><td class="fcSwiftCode">'+value.swiftcode+'</td><td><button class="btn btn-info btnEditFAI">Edit</button><button class="btn btn-danger btnDeleteFAI">Delete</button></td></tr>');
							
							$(".btnEditFAI").bind("click", EditFAI);
							$(".btnDeleteFAI").bind("click", DeleteFAI);
						});
					},
					error: function(){
						alert("Lỗi lấy by id")
					}
				});
				//end lấy thông tin qua id gán vào edit dialog
				
				$("#editFactoryDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Factory",
					height: 550,
					width: 1000,
					buttons:{
						"Edit": function(){
							
							//check if user have entered required fields
							if(!validateRequiredFieldForEdit()){
								callMessageDialog("Warning message", "Please enter required fields!");
								
								var shortName= $('#editFactoryDialog').find('#txtShortName').val();
								
								//if user dont enter name or enter just space
								if(shortName.trim() === '' || shortName == null){
									$("#editFactoryDialog").find("#errorTxtShortName").text("Please enter factory short name!");
									$("#editFactoryDialog").find("#errorTxtShortName").css("color", "red");
									$("#editFactoryDialog").find("#txtShortName").css("background-color", "red");
								}
							}else{
								//if user have entered all required fields, then check if input is overRange
								if(!validateOverRangeWhenEdit()){
									callMessageDialog("Warning message", "Your input is over range!");
								}else{
									//start editing
									var factorycode = $('#editFactoryDialog').find('#txtFactoryCode').val();
									var shortname = $('#editFactoryDialog').find('#txtShortName').val();
									var longname = $('#editFactoryDialog').find('#txtLongName').val();
									var address = $('#editFactoryDialog').find('#txtAddress').val();
									var tel = $('#editFactoryDialog').find('#txtTel').val();
									var fax = $('#editFactoryDialog').find('#txtFax').val();
									var taxno = $('#editFactoryDialog').find('#txtTaxNo').val();
									var status = $('#editFactoryDialog').find('#txtStatus').val();
									var remark = $('#editFactoryDialog').find('#txtRemark').val();
									
									var factorycontactModellist = [];
									//lặp qua table contact
									$('#editFactoryDialog').find('#tblFactoryContactList tr').each(function() {
										var factorycontactcode = $(this).data('id');
										var fcName = $(this).find(".fcName").html();
										var fcEmail = $(this).find(".fcEmail").html();
										var fcTelephone = $(this).find(".fcTelephone").html();
										if(typeof fcName === "undefined"){
											return true;//continue
										}
										//console.log(x+' '+y);
										factorycontactModellist.push({
									          "factorycontactcode": factorycontactcode,
									          "factoryCode": $('#editFactoryDialog').find('#txtFactoryCode').val(),
									          "creator": null,
									          "name": fcName,
									          "email": fcEmail,
									          "tel": fcTelephone,
									          "createdate": null
									        });
			
										//console.log(JSON.stringify(factorycontactModellist));
									 });
									//end lặp qua table contact
									
									var factoryaccountinformationModelList = [];
									//lặp qua table factory account information
									$('#editFactoryDialog').find('#tblFactoryaccountinformationList tr').each(function() {
										var factoryaccountinfocode = $(this).data('id');
										var fcBankName = $(this).find(".fcBankName").html();
										var fcBankBranch = $(this).find(".fcBankBranch").html();
										var fcAccountNumber = $(this).find(".fcAccountNumber").html();
										var fcAddress = $(this).find(".fcAddress").html();
										var fcSwiftCode = $(this).find(".fcSwiftCode").html();
//										var fcButtons = $(this).find(".fcBankName").html();
										
										if(typeof fcBankName === "undefined"){
											return true;//continue
										}
										//console.log(x+' '+y);
										factoryaccountinformationModelList.push({
											factoryaccountinfocode: factoryaccountinfocode,
											factoryCode: $('#addFactoryDialog').find('#txtFactoryCode').val(),
											creator: null,
											bankname: fcBankName,
											bankbranch: fcBankBranch,
											accountnumber: fcAccountNumber,
											address: fcAddress,
											swiftcode: fcSwiftCode
									        });
				
										console.log(JSON.stringify(factoryaccountinformationModelList));
									 });
									//end lặp qua table factory account information
									
									var factory = {
											factorycode: factorycode,
											shortname: shortname,
											longname: longname,
											address: address,
											tel: tel,
											fax: fax,
											taxno: taxno,
											status: status,
											remark: remark,
											factorycontactModellist : factorycontactModellist,
											factoryaccountinformationModelList: factoryaccountinformationModelList
									};
									
									console.log(JSON.stringify(factory));
									//
									
									//gọi ajax để edit
									$.ajax({
										dataType: "json",
										type: 'POST',
										data: JSON.stringify(factory),
										contentType: "application/json",
										url: getAbsolutePath() +  "factory/edit",
										success: function(data){
											//alert("OK");
											if(data.editStatus==true){
												//alert("Edit thành công");
												reloadTableWithStatus();
												callMessageDialog("Message", 'Edit factory successfully!');
												$("#editFactoryDialog").dialog("close");
												resetAfterEdit();
											}else if(data.editStatus==false){
												alert("Edit thất bại");
											}
										},
										error: function(){
											alert("Lỗi cmnr");
										}
									});
									//end editing
								}
							}
						},
						"Cancel": function(){
							$("#editFactoryDialog").dialog("close");
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
		    	$("#deleteFactoryDialog").find("#messageContent").text('Are you sure you want to delete factory "' + facCode+'"?');
				
				$("#deleteFactoryDialog").dialog({
					modal: true,
		    		show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Factory Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									factorycode: facCode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "factory/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete factory "'+ facCode+ '" successfully!');
											//close dialog
											$("#deleteFactoryDialog").dialog("close");
											//reload table
//											$("#listFactory").dataTable().fnDestroy();
//											$('#listFactory tbody').empty();
//											loadData();
											reloadTableWithStatus();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t delete factory "'+ facCode+ '"!');
											$("#deleteFactoryDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
									//alert("unexpected error!");
									callMessageDialog("Message", 'Can\'t delete factory "'+ facCode+ '"!');
									$("#deleteFactoryDialog").dialog("close");
								}
							});
						},
						"Cancel": function(){
							$("#deleteFactoryDialog").dialog("close");
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
			url: getAbsolutePath() +  "factory/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listFactory").dataTable().fnDestroy();
				$('#listFactory tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.factorycontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.factorycontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td>'+'<td>'+value1.tel+'</td>'+'</tr>';
						});
						tmp+='</table>';
					}
					
					//nếu value là all thì gọi lại loadData()
					if(valueSelected=='All'){
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.factorycode),
								$('<td>').text(value.shortname==null?'':value.shortname),
								$('<td>').text(value.longname==null?'':value.longname),
								$('<td>').text(value.address==null?'':value.address),
								$('<td>').text(value.tel==null?'':value.tel),
								$('<td>').text(value.fax==null?'':value.fax),
								$('<td>').text(value.taxno==null?'':value.taxno),
								$('<td>').html(value.factorycontactModellist.length==0?'':tmp),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.factorycode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.factorycode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.factorycode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listFactory');
						
					}else{//không thì xuất ra theo cái status
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.factorycode),
									$('<td>').text(value.shortname==null?'':value.shortname),
									$('<td>').text(value.longname==null?'':value.longname),
									$('<td>').text(value.address==null?'':value.address),
									$('<td>').text(value.tel==null?'':value.tel),
									$('<td>').text(value.fax==null?'':value.fax),
									$('<td>').text(value.taxno==null?'':value.taxno),
									$('<td>').html(value.factorycontactModellist.length==0?'':tmp),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.factorycode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.factorycode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.factorycode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listFactory');
						}
					}
				});
				//console.log(data);
				action();

				$('#listFactory').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
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
		var optionSelected = $('#ddlStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "factory/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listFactory").dataTable().fnDestroy();
				$('#listFactory tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.factorycontactModellist.length>0){
						tmp+='<table border="0">';
						$.each(value.factorycontactModellist,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td>'+'<td>'+value1.tel+'</td>'+'</tr>';
						});
						tmp+='</table>';
					}
					
					//nếu value là all thì gọi lại loadData()
					if(valueSelected=='All'){
//						$("#listFactory").dataTable().fnDestroy();
//						$('#listFactory tbody').empty();
//						loadData();
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.factorycode),
								$('<td>').text(value.shortname==null?'':value.shortname),
								$('<td>').text(value.longname==null?'':value.longname),
								$('<td>').text(value.address==null?'':value.address),
								$('<td>').text(value.tel==null?'':value.tel),
								$('<td>').text(value.fax==null?'':value.fax),
								$('<td>').text(value.taxno==null?'':value.taxno),
								$('<td>').html(value.factorycontactModellist.length==0?'':tmp),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.factorycode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.factorycode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.factorycode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listFactory');
						
					}else{//không thì xuất ra theo cái status
//						$("#listFactory").dataTable().fnDestroy();
//						$('#listFactory tbody').empty();
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.factorycode),
									$('<td>').text(value.shortname==null?'':value.shortname),
									$('<td>').text(value.longname==null?'':value.longname),
									$('<td>').text(value.address==null?'':value.address),
									$('<td>').text(value.tel==null?'':value.tel),
									$('<td>').text(value.fax==null?'':value.fax),
									$('<td>').text(value.taxno==null?'':value.taxno),
									$('<td>').html(value.factorycontactModellist.length==0?'':tmp),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.factorycode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.factorycode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.factorycode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listFactory');
						}
					}
				});
				//console.log(data);
				action();

				$('#listFactory').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
				//alert("Thành công");
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
		//
	};
	
	/**
	 * Hàm hiển thị lên add dialog để nhập 
	 */
	$('#btnAddNewFactory').click(function(){
		$("#addFactoryDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Factory",
			height: 550,
			width: 1000,
			buttons:{
				"Save": function(){
					
					var factoryCodeInput = $('#addFactoryDialog').find('#txtFactoryCode').val();
					//check if user have entered all required fields
					if(!validateRequiredFieldForAdd()){
						callMessageDialog("Warning message", "Please enter required fields!");
						
						//get value of code and name
						//var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
						var factoryCode = $('#addFactoryDialog').find('#txtFactoryCode').val();
						var shortName= $('#addFactoryDialog').find('#txtShortName').val();
						
						//if user dont enter code or enter just space
						if(factoryCode.trim() === '' || factoryCode == null){
							$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Please enter factory code!");
							$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
							$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "red");
						}
						//if user dont enter name or enter just space
						if(shortName.trim() === '' || shortName == null){
							$("#addFactoryDialog").find("#errorTxtShortName").text("Please enter factory short name!");
							$("#addFactoryDialog").find("#errorTxtShortName").css("color", "red");
							$("#addFactoryDialog").find("#txtShortName").css("background-color", "red");
						}
					}else{
						//if user have entered all required fields, then check if input is overRange
						if(!validateOverRangeWhenAdd()){
							callMessageDialog("Warning message", "Your input is over range!");
						}else{//check if the code is not existed
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									factorycode: factoryCodeInput
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "factory/isExist",
								success: function(data){
									if(data.isExisted==true){
										//if code is existed
										$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Factory code you enter is existed, please choose different one!");
										$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
										$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "red");
									}else if(data.isExisted==false){
										//alert("all input is ok!");
										$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Factory code is valid!");
										$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "green");
										$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "white");
										
										//start adding
										var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
										//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
										var shortname = $('#addFactoryDialog').find('#txtShortName').val();
										var longname = $('#addFactoryDialog').find('#txtLongName').val();
										var address = $('#addFactoryDialog').find('#txtAddress').val();
										var tel = $('#addFactoryDialog').find('#txtTel').val();
										var fax = $('#addFactoryDialog').find('#txtFax').val();
										var taxno = $('#addFactoryDialog').find('#txtTaxNo').val();
										var status = $('#addFactoryDialog').find('#txtStatus').val();
										//var factorycode = $('#addFactoryDialog').find('#txtFactoryCode').val();
										
										var factorycontactModellist = [];
										//lặp qua table contact
										$('#tblFactoryContactList tr').each(function() {
											var fcName = $(this).find(".fcName").html();
											var fcEmail = $(this).find(".fcEmail").html();
											var fcTelephone = $(this).find(".fcTelephone").html();
											if(typeof fcName === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											factorycontactModellist.push({
										          "factorycontactcode": null,
										          "factoryCode": $('#addFactoryDialog').find('#txtFactoryCode').val(),
										          "creator": null,
										          "name": fcName,
										          "email": fcEmail,
										          "tel": fcTelephone,
										          "createdate": null
										        });
					
											console.log(JSON.stringify(factorycontactModellist));
										 });
										//end lặp qua table contact
										
										
										var factoryaccountinformationModelList = [];
										//lặp qua table factory account information
										$('#tblFactoryaccountinformationList tr').each(function() {
//											var fcName = $(this).find(".fcName").html();
//											var fcEmail = $(this).find(".fcEmail").html();
//											var fcTelephone = $(this).find(".fcTelephone").html();
											
											var fcBankName = $(this).find(".fcBankName").html();
											var fcBankBranch = $(this).find(".fcBankBranch").html();
											var fcAccountNumber = $(this).find(".fcAccountNumber").html();
											var fcAddress = $(this).find(".fcAddress").html();
											var fcSwiftCode = $(this).find(".fcSwiftCode").html();
//											var fcButtons = $(this).find(".fcBankName").html();
											
											if(typeof fcBankName === "undefined"){
												return true;//continue
											}
											//console.log(x+' '+y);
											factoryaccountinformationModelList.push({
//										          "factorycontactcode": null,
//										          "factoryCode": $('#addFactoryDialog').find('#txtFactoryCode').val(),
//										          "creator": null,
//										          "name": fcName,
//										          "email": fcEmail,
//										          "tel": fcTelephone,
//										          "createdate": null
										          
												factoryaccountinfocode: null,
												factoryCode: $('#addFactoryDialog').find('#txtFactoryCode').val(),
												creator: null,
												bankname: fcBankName,
												bankbranch: fcBankBranch,
												accountnumber: fcAccountNumber,
												address: fcAddress,
												swiftcode: fcSwiftCode
										        });
					
											console.log(JSON.stringify(factoryaccountinformationModelList));
										 });
										//end lặp qua table factory account information
										
										
										var factory = {
												factorycode: factorycode,
												shortname: shortname,
												longname: longname,
												address: address,
												tel: tel,
												fax: fax,
												taxno: taxno,
												status: status,
												factorycontactModellist : factorycontactModellist,
												factoryaccountinformationModelList : factoryaccountinformationModelList
										};
										
										console.log(JSON.stringify(factory));
										//gọi ajax để add
										$.ajax({
											dataType: "json",
											type: 'POST',
											data: JSON.stringify(factory),
											contentType: "application/json",
											url: getAbsolutePath() +  "factory/add",
											success: function(data){
												if(data.addStatus==true){
													reloadTableWithStatus();
													callMessageDialog("Message", 'Add new factory successfully!');
													$("#addFactoryDialog").dialog("close");
													resetAfterAdd();
												}
											},
											error: function(){
												alert("Lỗi cmnr");
											}
										});
										//end adding
									}
								},error: function(){
									
								}
							});
						}
					}
				},
				"Cancel": function(){
					$("#addFactoryDialog").dialog("close");
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
		$('#addFactoryDialog').find('#txtFactoryCode').val('');
		$('#addFactoryDialog').find('#txtShortName').val('');
		$('#addFactoryDialog').find('#txtLongName').val('');
		$('#addFactoryDialog').find('#txtAddress').val('');
		$('#addFactoryDialog').find('#txtTel').val('');
		$('#addFactoryDialog').find('#txtFax').val('');
		$('#addFactoryDialog').find('#txtTaxNo').val('');
		$('#addFactoryDialog').find('#txtStatus').val('');
		$('#addFactoryDialog').find('#txtRemark').val('');
		$("#tblFactoryContactList > tbody").html("");
		$("#tblFactoryaccountinformationList > tbody").html("");
		
		//reset css
		$("#addFactoryDialog").find("#errorTxtFactoryCode").text("");
		$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtShortName").text("");
		$("#addFactoryDialog").find("#txtShortName").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtLongName").text("");
		$("#addFactoryDialog").find("#txtLongName").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtAddress").text("");
		$("#addFactoryDialog").find("#txtAddress").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtTel").text("");
		$("#addFactoryDialog").find("#txtTel").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtFax").text("");
		$("#addFactoryDialog").find("#txtFax").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtTaxNo").text("");
		$("#addFactoryDialog").find("#txtTaxNo").css("background-color", "white");
		$("#addFactoryDialog").find("#errorTxtRemark").text("");
		$("#addFactoryDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi thêm mới
	
	//hàm reset các trường trong bảng sau khi edit
	function resetAfterEdit(){
		$('#editFactoryDialog').find('#txtFactoryCode').val('');
		$('#editFactoryDialog').find('#txtShortName').val('');
		$('#editFactoryDialog').find('#txtLongName').val('');
		$('#editFactoryDialog').find('#txtAddress').val('');
		$('#editFactoryDialog').find('#txtTel').val('');
		$('#editFactoryDialog').find('#txtFax').val('');
		$('#editFactoryDialog').find('#txtTaxNo').val('');
		$('#editFactoryDialog').find('#txtStatus').val('');
		$('#editFactoryDialog').find('#txtRemark').val('');
		$("#tblFactoryContactList > tbody").html("");
		$("#tblFactoryaccountinformationList > tbody").html("");
		
		//reset css
		$("#editFactoryDialog").find("#errorTxtShortName").text("");
		$("#editFactoryDialog").find("#txtShortName").css("background-color", "white");
		$("#editFactoryDialog").find("#errorTxtLongName").text("");
		$("#editFactoryDialog").find("#txtLongName").css("background-color", "white");
		$("#editFactoryDialog").find("#errorTxtAddress").text("");
		$("#editFactoryDialog").find("#txtAddress").css("background-color", "white");
		$("#editFactoryDialog").find("#errorTxtTel").text("");
		$("#editFactoryDialog").find("#txtTel").css("background-color", "white");
		$("#editFactoryDialog").find("#errorTxtFax").text("");
		$("#editFactoryDialog").find("#txtFax").css("background-color", "white");
		$("#editFactoryDialog").find("#errorTxtTaxNo").text("");
		$("#editFactoryDialog").find("#txtTaxNo").css("background-color", "white");
		$("#editFactoryDialog").find("#errorTxtRemark").text("");
		$("#editFactoryDialog").find("#txtRemark").css("background-color", "white");
	}
	//end hàm reset các trường trong bảng sau khi edit
	
	/**
	 * Các hàm thêm mới vào bảng FactoryContact
	 */
	$(function(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditFC").bind("click", Edit);
		$(".btnDeleteFC").bind("click", Delete);
		$("#btnAddNewFactoryContact").bind("click", Add);
		$('#editFactoryDialog').find('#btnAddNewFactoryContact').bind("click", Add);
		
		//Add, Save, Edit and Delete functions code
		$(".btnEditFAI").bind("click", EditFAI);
		$(".btnDeleteFAI").bind("click", DeleteFAI);
		$("#btnAddNewFactoryaccountinformation").bind("click", AddFAI);
		$('#editFactoryDialog').find('#btnAddNewFactoryaccountinformation').bind("click", AddFAI);
	});
	
	function Bind4Edit(){
		$(".btnEditFC").bind("click", Edit);
		$(".btnDeleteFC").bind("click", Delete);
		$("#btnAddNewFactoryContact").bind("click", Add);
	}
	
	//hàm add 1 dòng vào table FactoryContactList
	function Add(){
		$("#tblFactoryContactList tbody").append(
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
	 * end Các hàm thêm mới vào bảng FactoryContact
	 */
	/**
	 * các hàm thêm mới vào bảng Factory account information
	 */
	
//	$(function(){
//		//Add, Save, Edit and Delete functions code
//		$(".btnEditFC").bind("click", Edit);
//		$(".btnDeleteFC").bind("click", Delete);
//		$("#btnAddNewFactoryContact").bind("click", Add);
//		$('#editFactoryDialog').find('#btnAddNewFactoryContact').bind("click", Add);
//	});
	
	function Bind4EditFAI(){
		$(".btnEditFAI").bind("click", EditFAI);
		$(".btnDeleteFAI").bind("click", DeleteFAI);
		$("#btnAddNewFactoryaccountinformation").bind("click", AddFAI);
	}
	
	//hàm add 1 dòng vào table FactoryContactList
	function AddFAI(){
		$("#tblFactoryaccountinformationList tbody").append(
				"<tr data-id='0'>"+
				"<td><input maxlength='200' type='text'/></td>"+
				"<td><input maxlength='200' type='text'/></td>"+
				"<td><input maxlength='30' type='text'/></td>"+
				"<td><input maxlength='200' type='text'/></td>"+
				"<td><input maxlength='30' type='text'/></td>"+
				"<td><button class='btn btn-info btnSaveFAI'>Save</button><button class='btn btn-danger btnDeleteFAI'>Delete</button></td>"+
				"</tr>");
			
				$(".btnSaveFAI").bind("click", SaveFAI);		
				$(".btnDeleteFAI").bind("click", DeleteFAI);
	};
	//end hàm add 1 dòng vào table FactoryContactList
	
	//hàm add dòng đã nhập vào table
	function SaveFAI(){
		var par = $(this).parent().parent(); //tr
		var tdBankName = par.children("td:nth-child(1)");
		var tdBankBranch = par.children("td:nth-child(2)");
		var tdAccountNumber = par.children("td:nth-child(3)");
		var tdAddress = par.children("td:nth-child(4)");
		var tdSwiftCode = par.children("td:nth-child(5)");
		var tdButtons = par.children("td:nth-child(6)");

		tdBankName.html(tdBankName.children("input[type=text]").val());
		tdBankName.addClass( "fcBankName" );
		tdBankBranch.html(tdBankBranch.children("input[type=text]").val());
		tdBankBranch.addClass( "fcBankBranch" );
		tdAccountNumber.html(tdAccountNumber.children("input[type=text]").val());
		tdAccountNumber.addClass( "fcAccountNumber" );
		tdAddress.html(tdAddress.children("input[type=text]").val());
		tdAddress.addClass( "fcAddress" );
		tdSwiftCode.html(tdSwiftCode.children("input[type=text]").val());
		tdSwiftCode.addClass( "fcSwiftCode" );
		tdButtons.html("<button class='btn btn-info btnEditFAI'>Edit</button><button class='btn btn-danger btnDeleteFAI'>Delete</button>");

		$(".btnEditFAI").bind("click", EditFAI);
		$(".btnDeleteFAI").bind("click", DeleteFAI);
	};
	//end hàm add dòng đã nhập vào table
	
	//
	function EditFAI(){
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
		tdButtons.html("<button class='btn btn-info btnSaveFAI'>Save</button>");

		$(".btnSaveFAI").bind("click", SaveFAI);
		$(".btnEditFAI").bind("click", EditFAI);
		$(".btnDeleteFAI").bind("click", DeleteFAI);
	};
	//
	
	//
	function DeleteFAI(){
		var par = $(this).parent().parent(); //tr
		par.remove();
	}; 
	//
	
	/**
	 * end các hàm thêm mới vào bảng Factory account information
	 */
	
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
	 * ---------------------------------------
	 */
	
	//do not allow input " character 
	$("#addFactoryDialog").find("#txtFactoryCode").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var factoryCode= $("#addFactoryDialog").find("#txtFactoryCode").val();
		if(factoryCode.trim() === '' || factoryCode == null)
			return false;
		
		var shortName= $("#addFactoryDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new
	 */
	function validateOverRangeWhenAdd(){
		if($("#addFactoryDialog").find("#txtFactoryCode").val().length>50 || $("#addFactoryDialog").find("#txtShortName").val().length>50 
				|| $("#addFactoryDialog").find("#txtLongName").val().length>100 || $("#addFactoryDialog").find("#txtAddress").val().length>200
				|| $("#addFactoryDialog").find("#txtTel").val().length>50 || $("#addFactoryDialog").find("#txtFax").val().length>50
				|| $("#addFactoryDialog").find("#txtTaxNo").val().length>50)
			return false;
		return true;
	}
	
	/**
	 * factory code on keyup function() to check if user entered, over range, factory is existed (3 cases)
	 */
	$("#addFactoryDialog").find("#txtFactoryCode").on('change keyup paste',function(){
		var factoryCode= $(this).val();
		
		//if user dont enter or enter just space
		if(factoryCode.trim() === '' || factoryCode == null){
			$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Please enter factory code!");
			$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		if(factoryCode.length>50){
			$("#addFactoryDialog").find("#errorTxtFactoryCode").text("The code field's length is 50. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				factorycode: factoryCode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "factory/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Factory code you enter is existed, please choose different one!");
					$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
					$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "red");
				}else if(data.isExisted==false&&$("#addFactoryDialog").find("#txtFactoryCode").val().length<51){
					//if code is not existed and valid length
					$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Factory code is valid!");
					$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "green");
					$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "white");
					//nếu xóa hết
					if(factoryCode.trim() === '' || factoryCode == null){
						$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Please enter factory code!");
						$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
						$(this).css("background-color", "red");
					}
				}
			},
			error: function(){
				//when user clear the code
				$("#addFactoryDialog").find("#errorTxtFactoryCode").text("Please enter factory code!");
				$("#addFactoryDialog").find("#errorTxtFactoryCode").css("color", "red");
				$("#addFactoryDialog").find("#txtFactoryCode").css("background-color", "red");
			}
		});
	});
	
	/**
	 * factory's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addFactoryDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#addFactoryDialog").find("#errorTxtShortName").text("Please enter factory short name!");
			$("#addFactoryDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#addFactoryDialog").find("#errorTxtShortName").text("The shortName field's length is 50. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addFactoryDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * factory's long name on keyup function() to check if over range (1 cases)
	 */
	$("#addFactoryDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#addFactoryDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#addFactoryDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * factory address on keyup function() to check if over range (1 cases)
	 */
	$("#addFactoryDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#addFactoryDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#addFactoryDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * factory tel on keyup function() to check if over range (1 cases)
	 */
	$("#addFactoryDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#addFactoryDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#addFactoryDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * factory fax on keyup function() to check if over range (1 cases)
	 */
	$("#addFactoryDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#addFactoryDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#addFactoryDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * factory tax no on keyup function() to check if over range (1 cases)
	 */
	$("#addFactoryDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#addFactoryDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#addFactoryDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#addFactoryDialog").find("#errorTxtTaxNo").text("");
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
		
		var shortName= $("#editFactoryDialog").find("#txtShortName").val();
		if(shortName.trim() === '' || shortName == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before edit factory
	 */
	function validateOverRangeWhenEdit(){
		if($("#editFactoryDialog").find("#txtShortName").val().length>50 
				|| $("#editFactoryDialog").find("#txtLongName").val().length>100 || $("#editFactoryDialog").find("#txtAddress").val().length>200
				|| $("#editFactoryDialog").find("#txtTel").val().length>50 || $("#editFactoryDialog").find("#txtFax").val().length>50
				|| $("#editFactoryDialog").find("#txtTaxNo").val().length>50)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] factory's short name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editFactoryDialog").find("#txtShortName").on('change keyup paste',function(){
		var shortName= $(this).val();
		
		//if user dont enter or enter just space
		if(shortName.trim() === '' || shortName == null){
			$("#editFactoryDialog").find("#errorTxtShortName").text("Please enter factory short name!");
			$("#editFactoryDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if(shortName.length>50){
			$("#editFactoryDialog").find("#errorTxtShortName").text("The shortName field's length is 50. Your input is over range!");
			$("#editFactoryDialog").find("#errorTxtShortName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editFactoryDialog").find("#errorTxtShortName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] factory long name on keyup function() to check if over range (1 cases)
	 */
	$("#editFactoryDialog").find("#txtLongName").on('change keyup paste',function(){
		var longName= $(this).val();
		
		//if input of user is over range
		if(longName.length>100){
			$("#editFactoryDialog").find("#errorTxtLongName").text("The long name field's length is 100. Your input is over range!");
			$("#editFactoryDialog").find("#errorTxtLongName").css("color", "red");
			$(this).css("background-color", "red");
		}else if(longName.length<101){//valid input
			$("#editFactoryDialog").find("#errorTxtLongName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] factory address on keyup function() to check if over range (1 cases)
	 */
	$("#editFactoryDialog").find("#txtAddress").on('change keyup paste',function(){
		var address= $(this).val();
		
		//if input of user is over range
		if(address.length>200){
			$("#editFactoryDialog").find("#errorTxtAddress").text("The address field's length is 200. Your input is over range!");
			$("#editFactoryDialog").find("#errorTxtAddress").css("color", "red");
			$(this).css("background-color", "red");
		}else if(address.length<201){//valid input
			$("#editFactoryDialog").find("#errorTxtAddress").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] factory tel on keyup function() to check if over range (1 cases)
	 */
	$("#editFactoryDialog").find("#txtTel").on('change keyup paste',function(){
		var tel= $(this).val();
		
		//if input of user is over range
		if(tel.length>50){
			$("#editFactoryDialog").find("#errorTxtTel").text("The telephone field's length is 50. Your input is over range!");
			$("#editFactoryDialog").find("#errorTxtTel").css("color", "red");
			$(this).css("background-color", "red");
		}else if(tel.length<51){//valid input
			$("#editFactoryDialog").find("#errorTxtTel").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] factory fax on keyup function() to check if over range (1 cases)
	 */
	$("#editFactoryDialog").find("#txtFax").on('change keyup paste',function(){
		var fax= $(this).val();
		
		//if input of user is over range
		if(fax.length>50){
			$("#editFactoryDialog").find("#errorTxtFax").text("The fax field's length is 50. Your input is over range!");
			$("#editFactoryDialog").find("#errorTxtFax").css("color", "red");
			$(this).css("background-color", "red");
		}else if(fax.length<51){//valid input
			$("#editFactoryDialog").find("#errorTxtFax").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] factory tax no on keyup function() to check if over range (1 cases)
	 */
	$("#editFactoryDialog").find("#txtTaxNo").on('change keyup paste',function(){
		var taxNo= $(this).val();
		
		//if input of user is over range
		if(taxNo.length>50){
			$("#editFactoryDialog").find("#errorTxtTaxNo").text("The tax no field's length is 50. Your input is over range!");
			$("#editFactoryDialog").find("#errorTxtTaxNo").css("color", "red");
			$(this).css("background-color", "red");
		}else if(taxNo.length<51){//valid input
			$("#editFactoryDialog").find("#errorTxtTaxNo").text("");
			$(this).css("background-color", "white");
		}
	});
	
	//
//	loadData();
})