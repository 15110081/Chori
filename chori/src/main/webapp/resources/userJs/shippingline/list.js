$(document).ready(function(){
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() + "shippingline/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					callMessageDialog("Message", 'Table have no data');
				}
				
				$.each(data.list,function(key,value){
					var tmp='';
					if(value.shippinglinecontacts.length>0){
						tmp+='<table border="0">';
						$.each(value.shippinglinecontacts,function(key,value1){
							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
						});
						tmp+='</table>';
					}
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.shippinglinecode),
							$('<td>').text(value.shortname),
							$('<td>').text(value.longname),
							$('<td>').text(value.address),
							$('<td>').text(value.tel),
							$('<td>').text(value.fax),
							$('<td>').text(value.taxno),
							$('<td>').html(value.shippinglinecontacts.length==0?'':tmp),
//							$('<td>').html('<button class="btn btn-info btnEdit" data-id="'+value.shippinglinecode+'">Edit</button>'
//							+ '<button class="btn btn-danger btnDelete" data-id="'+value.shippinglinecode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.shippinglinecode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listShippingline');
				});
				//console.log(data);
				action();

				$('#listShippingline').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				callMessageDialog("Message", 'Can not get data');
			}
		});
	};
	
//	function action(){
//		//button edit shipping line
//		$('.btnEdit').click(function(){
//			
//			//to clear tblShippingLineContact
//			$('#tblShippinglineContactList > tbody').html("");
//			
//			//to get id of shippinglinecode (selected)
//			var splCode= $(this).data('id');
//			
//			//get data by id and set to dialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data: {},
//				url: getAbsolutePath() + "shippingline/detail/"+splCode,
//				contentType: "application/json",
//				success: function(data){
//					//set shipping line detail to dialog
//					$('#editShippinglineDialog').find('#txtShippinglineCode').val(data.shippingline.shippinglinecode);
//					$('#editShippinglineDialog').find('#txtShortName').val(data.shippingline.shortname);
//					$('#editShippinglineDialog').find('#txtLongName').val(data.shippingline.longname);
//					$('#editShippinglineDialog').find('#txtAddress').val(data.shippingline.address);
//					$('#editShippinglineDialog').find('#txtTel').val(data.shippingline.tel);
//					$('#editShippinglineDialog').find('#txtFax').val(data.shippingline.fax);
//					$('#editShippinglineDialog').find('#txtTaxNo').val(data.shippingline.taxno);
//					$('#editShippinglineDialog').find('#txtStatus').val(data.shippingline.status);
//					$('#editShippinglineDialog').find('#txtRemark').val(data.shippingline.remark==null?"":data.shippingline.remark);
//					$.each(data.shippingline.shippinglinecontacts,function(key,value){
//						$("#tblShippinglineContactList tbody").append('<tr data-id="'+value.shippinglinecontactcode+'"><td class="splName">'+value.name+'</td><td class="splEmail">'+value.email+'</td><td class="splTel">'+value.tel+'</td><td><button class="btn btn-danger btnDeleteSPL">Delete</button><button class="btn btn-info btnEditSPL">Edit</button></td></tr>');
//						
//						$(".btnEditSPL").bind("click", Edit);
//						$(".btnDeleteSPL").bind("click", Delete);
//					});
//				},
//				error: function(){
//					callMessageDialog("Message", 'Error when get shipping line detail!');			
//				}
//			});
//			//end
//			
//			$("#editShippinglineDialog").dialog({
//				show:{
//					effect:"slide",
//					duration: 500
//				},
//				title: "Edit Shipping Line",
//				height: 650,
//				width: 900,
//				modal: true,
//				buttons:{
//					"Save": {
//						//đang làm
//	                	text: "Save",
//	                	id: "btnSaveNewShippingLine",
//	                	click: function(){
//						var shippinglinecode = $('#editShippinglineDialog').find('#txtShippinglineCode').val();
//						var shortname = $('#editShippinglineDialog').find('#txtShortName').val();
//						var longname = $('#editShippinglineDialog').find('#txtLongName').val();
//						var address = $('#editShippinglineDialog').find('#txtAddress').val();
//						var tel = $('#editShippinglineDialog').find('#txtTel').val();
//						var fax = $('#editShippinglineDialog').find('#txtFax').val();
//						var taxno = $('#editShippinglineDialog').find('#txtTaxNo').val();
//						var status = $('#editShippinglineDialog').find('#txtStatus').val();
//						var remark = $('#editShippinglineDialog').find('#txtRemark').val();
//						var shippinglinecontactModellist = [];
//						//lặp qua table contact
//						$('#editShippinglineDialog').find('#tblShippinglineContactList tr').each(function() {
//							var shippinglinecontactcode = $(this).data('id');
//							var splName = $(this).find(".splName").html();
//							var splEmail = $(this).find(".splEmail").html();
//							var splTel = $(this).find(".splTel").html();
//							if(typeof splName === "undefined"){
//								return true;//continue
//							}
//							//console.log(x+' '+y);
//							shippinglinecontactModellist.push({
//						          "shippinglinecontactcode": shippinglinecontactcode,
//						          "shippinglinecode": $('#editShippinglineDialog').find('#txtShippinglineCode').val(),
//						          "creator": null,
//						          "name": splName,
//						          "email": splEmail,
//						          "tel": splTel,
//						          "createdate": null
//						        });
//						 });
//						//end lặp qua table contact
//						
//						var shippingline = {
//								shippinglinecode: shippinglinecode,
//								shortname: shortname,
//								longname: longname,
//								address: address,
//								tel: tel,
//								fax: fax,
//								taxno: taxno,
//								status: status,
//								remark: remark,
//								shippinglinecontacts : shippinglinecontactModellist
//						};
//						
//						//call ajax to edit
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data: JSON.stringify(shippingline),
//							contentType: "application/json",
//							url: getAbsolutePath() + "shippingline/edit",
//							success: function(data){
//								if(data.editStatus==true){
//									callMessageDialog("Message", 'Edit shipping line successfully!');
//									$("#editShippinglineDialog").dialog("close");
//									loadTableDataByStatus();
//								}else if(data.editStatus==false){
//									callMessageDialog("Message", 'Edit shipping line successfully!');
//								}
//							},
//							error: function(){
//								callMessageDialog("Message", 'Error when edit shipping line successfully');
//							}
//						});		
//	                	}
//					},
//					"Cancel": function(){
//						$("#editShippinglineDialog").dialog("close");
//						//Xóa các thông tin trong bảng contact
//						$("#tblShippinglineContactList > tbody").html("");
//					}
//				}
//			});
//		});
//		
//		$('.btnDelete').click(function(){
//			var splCode= $(this).data('id');
//			$("#deleteShippinglineDialog").find("#messageContent").text('Are you sure you want to delete Shipping Line "' + splCode+'"?');
//			
//			$("#deleteShippinglineDialog").dialog({
//	    		show:{
//					effect:"slide",
//					duration: 500
//				},
//				title: "Delete Shipping Line Confirm",
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
//							url: getAbsolutePath() + "shippingline/delete/" + splCode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete shipping line "'+ splCode+ '" successfully!');
//										//close dialog
//										$("#deleteShippinglineDialog").dialog("close");
//										//reload table
//										$("#listShippingline").dataTable().fnDestroy();
//										$('#listShippingline tbody').empty();
//										loadData();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Cannot delete shipping line  "'+ splCode+ '"!');
//										$("#deleteShippinglineDialog").dialog("close");
//									}else{
//										callMessageDialog("Message", 'Unexpected error when delete shipping line!');
//									}
//								}else{
//									callMessageDialog("Message", 'Unexpected error when delete shipping line!');
//								}
//							},
//							error: function(){
//								callMessageDialog("Message", 'Unexpected error when delete shipping line!');
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteShippinglineDialog").dialog("close");
//					}
//				}
//			});
//		});
//	}
	
	//action by dropdownlist
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
			//to clear tblShippingLineContact
			$('#tblShippinglineContactList > tbody').html("");
			
			//to get id of shippinglinecode (selected)
			var splCode= $(this).data('id');

		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
				//get data by id and set to dialog
				$.ajax({
					dataType: "json",
					type: 'POST',
					data:
						JSON.stringify({
							shippinglinecode : splCode
						}),
					url: getAbsolutePath() + "shippingline/detail",
					contentType: "application/json",
					success: function(data){
						//set shipping line detail to dialog
						$('#editShippinglineDialog').find('#txtShippinglineCode').val(data.shippingline.shippinglinecode);
						$('#editShippinglineDialog').find('#txtShortName').val(data.shippingline.shortname);
						$('#editShippinglineDialog').find('#txtLongName').val(data.shippingline.longname);
						$('#editShippinglineDialog').find('#txtAddress').val(data.shippingline.address);
						$('#editShippinglineDialog').find('#txtTel').val(data.shippingline.tel);
						$('#editShippinglineDialog').find('#txtFax').val(data.shippingline.fax);
						$('#editShippinglineDialog').find('#txtTaxNo').val(data.shippingline.taxno);
						$('#editShippinglineDialog').find('#txtStatus').val(data.shippingline.status);
						$('#editShippinglineDialog').find('#txtRemark').val(data.shippingline.remark==null?"":data.shippingline.remark);
						$.each(data.shippingline.shippinglinecontacts,function(key,value){
							$("#tblShippinglineContactList tbody").append('<tr data-id="'+value.shippinglinecontactcode+'"><td class="splName">'+value.name+'</td><td class="splEmail">'+value.email+'</td><td class="splTel">'+value.tel+'</td><td><button class="btn btn-danger btnDeleteSPL">Delete</button><button class="btn btn-info btnEditSPL">Edit</button></td></tr>');
							
							$(".btnEditSPL").bind("click", Edit);
							$(".btnDeleteSPL").bind("click", Delete);
						});
					},
					error: function(){
						callMessageDialog("Message", 'Error when get shipping line detail!');			
					}
				});
				//end
				
				$("#editShippinglineDialog").dialog({
					show:{
						effect:"slide",
						duration: 500
					},
					title: "Edit Shipping Line",
					height: 650,
					width: 900,
					modal: true,
					buttons:{
						"Save": {
							//đang làm
		                	text: "Save",
		                	id: "btnSaveNewShippingLine",
		                	click: function(){
							var shippinglinecode = $('#editShippinglineDialog').find('#txtShippinglineCode').val();
							var shortname = $('#editShippinglineDialog').find('#txtShortName').val();
							var longname = $('#editShippinglineDialog').find('#txtLongName').val();
							var address = $('#editShippinglineDialog').find('#txtAddress').val();
							var tel = $('#editShippinglineDialog').find('#txtTel').val();
							var fax = $('#editShippinglineDialog').find('#txtFax').val();
							var taxno = $('#editShippinglineDialog').find('#txtTaxNo').val();
							var status = $('#editShippinglineDialog').find('#txtStatus').val();
							var remark = $('#editShippinglineDialog').find('#txtRemark').val();
							var shippinglinecontactModellist = [];
							if(shippinglinecode.length<1 || shippinglinecode.length>50 || shortname.length<1 || shortname.length>50)
							{
								callMessageDialog("Message", 'Require field is not valid! Please check input data and try again.');
							}
							else
							{
								//lặp qua table contact
								$('#editShippinglineDialog').find('#tblShippinglineContactList tr').each(function() {
									var shippinglinecontactcode = $(this).data('id');
									var splName = $(this).find(".splName").html();
									var splEmail = $(this).find(".splEmail").html();
									var splTel = $(this).find(".splTel").html();
									if(typeof splName === "undefined"){
										return true;//continue
									}
									//console.log(x+' '+y);
									shippinglinecontactModellist.push({
								          "shippinglinecontactcode": shippinglinecontactcode,
								          "shippinglinecode": $('#editShippinglineDialog').find('#txtShippinglineCode').val(),
								          "creator": null,
								          "name": splName,
								          "email": splEmail,
								          "tel": splTel,
								          "createdate": null
								        });
								 });
								//end lặp qua table contact
								
								var shippingline = {
										shippinglinecode: shippinglinecode,
										shortname: shortname,
										longname: longname,
										address: address,
										tel: tel,
										fax: fax,
										taxno: taxno,
										status: status,
										remark: remark,
										shippinglinecontacts : shippinglinecontactModellist
								};
								
								//call ajax to edit
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify(shippingline),
									contentType: "application/json",
									url: getAbsolutePath() + "shippingline/edit",
									success: function(data){
										if(data.editStatus==true){
											callMessageDialog("Message", 'Edit shipping line successfully!');
											$("#editShippinglineDialog").dialog("close");
											loadTableDataByStatus();
										}else if(data.editStatus==false){
											callMessageDialog("Message", 'Edit shipping line successfully!');
										}
									},
									error: function(){
										callMessageDialog("Message", 'Error when edit shipping line successfully');
									}
								});	
							}
	
		                	}
						},
						"Cancel": function(){
							$("#editShippinglineDialog").dialog("close");
							//Xóa các thông tin trong bảng contact
							$("#tblShippinglineContactList > tbody").html("");
						}
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
				var splCode= $(this).data('id');
				$("#deleteShippinglineDialog").find("#messageContent").text('Are you sure you want to delete Shipping Line "' + splCode+'"?');
				
				$("#deleteShippinglineDialog").dialog({
		    		show:{
						effect:"slide",
						duration: 500
					},
					title: "Delete Shipping Line Confirm",
					height: 300,
					width: 400,
					modal: true,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:{},
								contentType: "application/json",
								data:
									JSON.stringify({
										shippinglinecode : splCode
									}),
								url: getAbsolutePath() + "shippingline/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete shipping line "'+ splCode+ '" successfully!');
											//close dialog
											$("#deleteShippinglineDialog").dialog("close");
											//reload table
											$("#listShippingline").dataTable().fnDestroy();
											$('#listShippingline tbody').empty();
											loadTableDataByStatus();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Cannot delete shipping line  "'+ splCode+ '"!');
											$("#deleteShippinglineDialog").dialog("close");
										}else{
											callMessageDialog("Message", 'Unexpected error when delete shipping line!');
										}
									}else{
										callMessageDialog("Message", 'Unexpected error when delete shipping line!');
									}
								},
								error: function(){
									callMessageDialog("Message", 'Unexpected error when delete shipping line!');
								}
							});
						},
						"Cancel": function(){
							$("#deleteShippinglineDialog").dialog("close");
						}
					}
				});
		    };
		    //end if user choose delete option
		    
		});
	};
	/**
	 * This function is used to change table data when choose status
	 */
	$('#ddlStatus').on('change',function(){
		loadTableDataByStatus();
	});
	
function loadTableDataByStatus(){
	var optionSelected = $('#ddlStatus').find("option:selected");
	var valueSelected  = optionSelected.val();

	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: getAbsolutePath() + "shippingline/list",
		contentType: "application/json",
		success: function(data){
			
			$("#listShippingline").dataTable().fnDestroy();
			$('#listShippingline tbody').empty();
			
			var i = 1;
			if(data.list.length==0){
				callMessageDialog("Message", 'There are no shipping line to show');
			}
			$.each(data.list,function(key,value){
				var tmp='';
				if(value.shippinglinecontacts.length>0){
					tmp+='<table border="0">';
					$.each(value.shippinglinecontacts,function(key,value1){
						tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td><td>'+value1.tel+'</td></tr>';
					});
					tmp+='</table>';
				}
				
				//if value =All, get all data
				if(valueSelected=='All'){
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.shippinglinecode),
							$('<td>').text(value.shortname),
							$('<td>').text(value.longname),
							$('<td>').text(value.address),
							$('<td>').text(value.tel),
							$('<td>').text(value.fax),
							$('<td>').text(value.taxno),
							$('<td>').html(value.shippinglinecontacts.length==0?'':tmp),
//							$('<td>').html('<button class="btn btn-info btnEdit" data-id="'+value.shippinglinecode+'">Edit</button>'
//							+'<button class="btn btn-danger btnDelete" data-id="'+value.shippinglinecode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.shippinglinecode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listShippingline');
					
				}else{						
					if(valueSelected==value.status){
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.shippinglinecode),
								$('<td>').text(value.shortname),
								$('<td>').text(value.longname),
								$('<td>').text(value.address),
								$('<td>').text(value.tel),
								$('<td>').text(value.fax),
								$('<td>').text(value.taxno),
								$('<td>').html(value.shippinglinecontacts.length==0?'':tmp),
//								$('<td>').html('<button class="btn btn-info btnEdit" data-id="'+value.shippinglinecode+'">Edit</button>'
//								+'<button class="btn btn-danger btnDelete" data-id="'+value.shippinglinecode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.shippinglinecode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listShippingline');
					}
				}
			});
			action();

			$('#listShippingline').DataTable( {
				"pagingType": "full_numbers"
		    } );
		},
		error: function(){
			callMessageDialog("Message", 'Can not get data');
		}
	});
}
	//
	
	/**
	 * Show add shipping line dialog
	 */
	$('#btnAddNewShippingline').click(function(){
		//clearAllInput
		$('#addShippinglineDialog').find('#txtShippinglineCode').val("");
		$('#addShippinglineDialog').find('#txtShortName').val("");
		$('#addShippinglineDialog').find('#txtLongName').val("");
		$('#addShippinglineDialog').find('#txtAddress').val("");
		$('#addShippinglineDialog').find('#txtTel').val("");
		$('#addShippinglineDialog').find('#txtFax').val("");
		$('#addShippinglineDialog').find('#txtTaxNo').val("");
		$('#addShippinglineDialog').find('#txtStatus').val("");
		$('#addShippinglineDialog').find('#txtRemark').val("");
		$('#tblShippinglineContactList > tbody').html("");
		$("#addShippinglineDialog").dialog({
			show:{
				effect:"slide",
				duration: 500
			},
			title: "Add New Shipping Line",
			height: 650,
			width: 900,
			modal: true,
			buttons:{
				"Save": {
                	text: "Save",
                	id: "btnSaveNewShippingLine",
                	click: function(){
					var shippinglinecode = $('#addShippinglineDialog').find('#txtShippinglineCode').val().trim();
					var shortname = $('#addShippinglineDialog').find('#txtShortName').val().trim();
					var longname = $('#addShippinglineDialog').find('#txtLongName').val();
					var address = $('#addShippinglineDialog').find('#txtAddress').val();
					var tel = $('#addShippinglineDialog').find('#txtTel').val();
					var fax = $('#addShippinglineDialog').find('#txtFax').val();
					var taxno = $('#addShippinglineDialog').find('#txtTaxNo').val();
					var status = $('#addShippinglineDialog').find('#txtStatus').val();
					var remark = $('#addShippinglineDialog').find('#txtRemark').val();
					var shippinglinecontactModellist = [];
					if(shippinglinecode.length<1 || shippinglinecode.length>50 || shortname.length<1 || shortname.length>50)
					{
						callMessageDialog("Message", 'Require field is not valid! Please check input data and try again.');
					}
					else
					{
						
						//lặp qua table contact
						$('#tblShippinglineContactList tr').each(function() {
							var splName = $(this).find(".splName").html();
							var splEmail = $(this).find(".splEmail").html();
							var splTel = $(this).find(".splTel").html();
							if(typeof splName === "undefined"){
								return true;//continue
							}
							//console.log(x+' '+y);
							shippinglinecontactModellist.push({
						          "shippinglinecontactcode": null,
						          "shippinglinecode": $('#addShippinglineDialog').find('#txtShippinglineCode').val(),
						          "creator": null,
						          "name": splName,
						          "email": splEmail,
						          "tel": splTel,
						          "createdate": null
						        });
						 });
						//end lặp qua table contact
						
						var shippingline = {
								shippinglinecode: shippinglinecode,
								shortname: shortname,
								longname: longname,
								address: address,
								tel: tel,
								fax: fax,
								taxno: taxno,
								status: status,
								remark: remark,
								shippinglinecontacts : shippinglinecontactModellist
						};
						//call ajax to add 
						$.ajax({
							dataType: "json",
							type: 'POST',
							data: JSON.stringify(shippingline),
							contentType: "application/json",
							url: getAbsolutePath() + "shippingline/add",
							success: function(data){
								if(data.addStatus==true){
									callMessageDialog("Message", 'Add new shipping line successfully!');
									$("#addShippinglineDialog").dialog("close");
									loadTableDataByStatus();
								}
							},
							error: function(){
								callMessageDialog("Message", 'Error when add new shipping line ');
							}
						});
					}

                	}
				},
				"Cancel": function(){
					$("#addShippinglineDialog").dialog("close");
				}
			}
		});
	});
	
	/**
	 * Function to add new data on table ShippingLineContact
	 */
	$(function(){
		//Add, Save, Edit and Delete functions code
		$(".btnEditSPL").bind("click", Edit);
		$(".btnDeleteSPL").bind("click", Delete);
		$("#btnAddNewShippinglineContact").bind("click", Add);
		$('#editShippinglineDialog').find('#btnAddNewShippinglineContact').bind("click", Add);
	});
	
	//Function add 1 row in ShippingLineContactList table
	function Add(){
		$("#tblShippinglineContactList tbody").append(
				"<tr data-id='0'>"+
				"<td><input type='text' id='txtContactName'/></td>"+
				"<td><input type='text' id='txtContactEmail'/></td>"+
				"<td><input type='text' id='txtContactTel'/></td>"+
				"<td><button class='btn btn-info btnSaveSPL' id='tettet'>Save</button>" +
				"<button class='btn btn-danger btnDeleteSPL'>Delete</button></td>"+
				"</tr>");
			
				$(".btnSaveSPL").bind("click", Save);		
				$(".btnDeleteSPL").bind("click", Delete);
				$('#tettet').prop( "disabled", true );
	};
	
	//function for validate email format
	function validateEmail(email) {
	  var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	  return re.test(email);
	}
	
	//Function add row which is inputed to table
	function Save(){		
		
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTel = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");


		if(tdName.children("input[type=text]").val().trim().length<1 || tdName.children("input[type=text]").val().trim().length>50)
		{
			callMessageDialog("Warning", 'Contact name must be between 1-50 characters!');
		}
		else if(tdEmail.children("input[type=text]").val().trim().length>100)
		{
			callMessageDialog("Warning", 'Contact email must be less than or equal 100 characters!');
		}
		else if(tdTel.children("input[type=text]").val().trim().length>50)
		{
			callMessageDialog("Warning", 'Contact telephone must be less than or equal 50 characters!');
		}
		else if(!validateEmail(tdEmail.children("input[type=text]").val().toString()))
		{
			callMessageDialog("Warning", 'Invalid email format!');
		}
		else {		
			tdName.html(tdName.children("input[type=text]").val().trim());
			tdName.addClass( "splName" );
			tdEmail.html(tdEmail.children("input[type=text]").val().trim());
			tdEmail.addClass( "splEmail" );
			tdTel.html(tdTel.children("input[type=text]").val().trim());
			tdTel.addClass( "splTel" );
			tdButtons.html("<button class='btn btn-danger btnDeleteSPL'>Delete</button><button class='btn btn-info btnEditSPL'>Edit</button>");
	
			$(".btnEditSPL").bind("click", Edit);
			$(".btnDeleteSPL").bind("click", Delete);
		}
	};
	//end hàm add dòng đã nhập vào table
	
	//
	function Edit(){
		var par = $(this).parent().parent(); //tr
		var tdName = par.children("td:nth-child(1)");
		var tdEmail = par.children("td:nth-child(2)");
		var tdTel = par.children("td:nth-child(3)");
		var tdButtons = par.children("td:nth-child(4)");

		tdName.html("<input type='text' id='txtName' value='"+tdName.html()+"'/>");
		tdEmail.html("<input type='text' id='txtEmail' value='"+tdEmail.html()+"'/>");
		tdTel.html("<input type='text' id='txtTel' value='"+tdTel.html()+"'/>");
		tdButtons.html("<button class='btn btn-info btnSaveSPL'>Save</button>");

		$(".btnSaveSPL").bind("click", Save);
		$(".btnEditSPL").bind("click", Edit);
		$(".btnDeleteSPL").bind("click", Delete);
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
			show:{
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 200,
			width: 350,
			hide: {
				effect: "slide",
				duration: 300
			},
			buttons:{
				"OK": function(){
					$(this).dialog("close");
				}
			}
		});
	}
	
	//------------------------VALIDATION-------------------------
	
	//do not allow input " character 
	$("#addShippinglineDialog").find('#txtShippinglineCode').keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	
	//DO NOT INPUT NON-NUMBER in Tel, Fax, Tax No
//	$("#addShippinglineDialog,editShippinglineDialog").on('keydown', '#txtTel,#txtFax,#txtTaxNo', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
//	$("#addShippinglineDialog,editShippinglineDialog").on('keydown', '#txtTel,#txtFax,#txtTaxNo', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
	
	$("#addShippinglineDialog").find('#txtShippinglineCode').on('input propertychange paste', function (e) {
		var splcode = $("#addShippinglineDialog").find("#txtShippinglineCode").val();
		if(splcode.length>50 || splcode.length<1) {
			$("#addShippinglineDialog").find("#txtShippinglineCode").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtShippinglineCode").text("Shipping line code must be between 1-50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else if($.trim(splcode) !== splcode){
			$("#addShippinglineDialog").find("#txtShippinglineCode").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtShippinglineCode").text("Not allow to type the space as a first/last character");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtShippinglineCode").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtShippinglineCode").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#addShippinglineDialog").find('#txtShortName').on('input propertychange paste', function (e) {
		var shortname = $("#addShippinglineDialog").find("#txtShortName").val();
		if(shortname.length>50 || shortname.length<1) {
			$("#addShippinglineDialog").find("#txtShortName").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtShortName").text("Shipping line short name must be between 1-50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else if($.trim(shortname) !== shortname){
			$("#addShippinglineDialog").find("#txtShortName").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtShortName").text("Not allow to type the space as a first/last character");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtShortName").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtShortName").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#addShippinglineDialog").find('#txtLongName').on('input propertychange paste', function (e) {
		var longname = $("#addShippinglineDialog").find("#txtLongName").val();
		if(longname.length>100) {
			$("#addShippinglineDialog").find("#txtLongName").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtLongName").text("Shipping line long name must be less than 100 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtLongName").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtLongName").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#addShippinglineDialog").find('#txtAddress').on('input propertychange paste', function (e) {
		var address = $("#addShippinglineDialog").find("#txtAddress").val();
		if(address.length>200) {
			$("#addShippinglineDialog").find("#txtAddress").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtAddress").text("Shipping line address must be less than 200 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtAddress").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtAddress").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#addShippinglineDialog").find('#txtTel').on('input propertychange paste', function (e) {
		var tel = $("#addShippinglineDialog").find("#txtTel").val();
		if(tel.length>50) {
			$("#addShippinglineDialog").find("#txtTel").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtTel").text("Shipping line tel must be less than 50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtTel").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtTel").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#addShippinglineDialog").find('#txtFax').on('input propertychange paste', function (e) {
		var fax = $("#addShippinglineDialog").find("#txtFax").val();
		if(fax.length>50) {
			$("#addShippinglineDialog").find("#txtFax").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtFax").text("Shipping line fax must be less than 50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtFax").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtFax").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#addShippinglineDialog").find('#txtTaxNo').on('input propertychange paste', function (e) {
		var taxno = $("#addShippinglineDialog").find("#txtTaxNo").val();
		if(taxno.length>50) {
			$("#addShippinglineDialog").find("#txtTaxNo").css("border-left", "red 5px solid");
			$("#addShippinglineDialog").find("#errorTxtTaxNo").text("Shipping line tax no must be less than 50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#addShippinglineDialog").find("#txtTaxNo").css("border-left", "blue 5px solid");
			$("#addShippinglineDialog").find("#errorTxtTaxNo").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});

	
	//For edit
	$("#editShippinglineDialog").find('#txtShippinglineCode').on('input propertychange paste', function (e) {
		var splcode = $("#editShippinglineDialog").find("#txtShippinglineCode").val();
		if(splcode.length>50 || splcode.length<1) {
			$("#editShippinglineDialog").find("#txtShippinglineCode").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtShippinglineCode").text("Shipping line code must be between 1-50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else if($.trim(splcode) !== splcode){
			$("#editShippinglineDialog").find("#txtShippinglineCode").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtShippinglineCode").text("Not allow to type the space as a first/last character");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtShippinglineCode").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtShippinglineCode").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#editShippinglineDialog").find('#txtShortName').on('input propertychange paste', function (e) {
		var shortname = $("#editShippinglineDialog").find("#txtShortName").val();
		if(shortname.length>50 || shortname.length<1) {
			$("#editShippinglineDialog").find("#txtShortName").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtShortName").text("Shipping line short name must be between 1-50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else if($.trim(shortname) !== shortname){
			$("#editShippinglineDialog").find("#txtShortName").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtShortName").text("Not allow to type the space as a first/last character");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtShortName").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtShortName").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#editShippinglineDialog").find('#txtLongName').on('input propertychange paste', function (e) {
		var longname = $("#editShippinglineDialog").find("#txtLongName").val();
		if(longname.length>100) {
			$("#editShippinglineDialog").find("#txtLongName").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtLongName").text("Shipping line long name must be less than 100 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtLongName").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtLongName").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#editShippinglineDialog").find('#txtAddress').on('input propertychange paste', function (e) {
		var address = $("#editShippinglineDialog").find("#txtAddress").val();
		if(address.length>200) {
			$("#editShippinglineDialog").find("#txtAddress").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtAddress").text("Shipping line address must be less than 200 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtAddress").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtAddress").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#editShippinglineDialog").find('#txtTel').on('input propertychange paste', function (e) {
		var tel = $("#editShippinglineDialog").find("#txtTel").val();
		if(tel.length>50) {
			$("#editShippinglineDialog").find("#txtTel").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtTel").text("Shipping line tel must be less than 50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtTel").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtTel").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#editShippinglineDialog").find('#txtFax').on('input propertychange paste', function (e) {
		var fax = $("#editShippinglineDialog").find("#txtFax").val();
		if(fax.length>50) {
			$("#editShippinglineDialog").find("#txtFax").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtFax").text("Shipping line fax must be less than 50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtFax").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtFax").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	
	$("#editShippinglineDialog").find('#txtTaxNo').on('input propertychange paste', function (e) {
		var taxno = $("#editShippinglineDialog").find("#txtTaxNo").val();
		if(taxno.length>50) {
			$("#editShippinglineDialog").find("#txtTaxNo").css("border-left", "red 5px solid");
			$("#editShippinglineDialog").find("#errorTxtTaxNo").text("Shipping line tax no must be less than 50 characters");
			$('#btnSaveNewShippingLine').prop( "disabled", true );
		}
		else{				
			$("#editShippinglineDialog").find("#txtTaxNo").css("border-left", "blue 5px solid");
			$("#editShippinglineDialog").find("#errorTxtTaxNo").text("");
			$('#btnSaveNewShippingLine').prop( "disabled", false );
		}
	});
	

//	$("#addShippinglineDialog").find('#txtShippinglineCode,#txtShortName,#txtLongName,#txtAddress,#txtTel,#txtFax,#txtTaxNo').on('input propertychange paste', function (e) {
//		var splcode = $("#addShippinglineDialog").find("#txtShippinglineCode").val();
//		var shortname = $("#addShippinglineDialog").find("#txtShortName").val();
//		var longname = $("#addShippinglineDialog").find("#txtLongName").val();
//		var address = $("#addShippinglineDialog").find("#txtAddress").val();
//		var tel = $("#addShippinglineDialog").find("#txtTel").val();
//		var fax = $("#addShippinglineDialog").find("#txtFax").val();
//		var taxno = $("#addShippinglineDialog").find("#txtTaxNo").val();
//		if(splcode.length>10 || splcode.length<1
//				|| shortname.length>20 || shortname.length<1
//				|| longname.length>50
//				|| address>200
//				|| tel>20
//				|| fax>20
//				|| taxno>20){
//			//$('#btnSaveNewAccesoryConsumption').prop( "disabled", true );	
//			
//			if(splcode.length>20 || splcode.length<1) {
//				$("#addShippinglineDialog").find("#txtShippinglineCode").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtShippinglineCode").text("Shippingline must be between 1-20 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtShippinglineCode").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtShippinglineCode").text("");
//			}
//			
//			if(shortname.length>20 || shortname.length<1) {
//				$("#addShippinglineDialog").find("#txtShortName").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtShortName").text("Short name must be between 1-20 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtShortName").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtShortName").text("");
//			}
//			
//			if(longname.length>50) {
//				$("#addShippinglineDialog").find("#txtLongName").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtLongName").text("Long name must be between 1-50 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtLongName").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtLongName").text("");
//			}
//			
//			if(address>200) {
//				$("#addShippinglineDialog").find("#txtAddress").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtAddress").text("Address must be between 1-200 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtAddress").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtAddress").text("");
//			}
//			
//			if(tel>20) {
//				$("#addShippinglineDialog").find("#txtTel").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtTel").text("Tel must be between 1-20 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtTel").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtTel").text("");
//			}
//			
//			if(fax>20) {
//				$("#addShippinglineDialog").find("#txtFax").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtFax").text("Fax must be between 1-20 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtFax").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtFax").text("");
//			}
//			
//			if(taxno>20) {
//				$("#addShippinglineDialog").find("#txtTaxNo").css("border-left", "red 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtTaxNo").text("Tax No must be between 1-20 characters");
//			}
//			else{				
//				$("#addShippinglineDialog").find("#txtTaxNo").css("border-left", "blue 5px solid");
//				$("#addShippinglineDialog").find("#errorTxtTaxNo").text("");
//			}		
//		}
//		else {
//			//$("#btnSaveNewAccesoryConsumption").prop( "disabled", false  );			
//		}
//	});
	
	
	loadData();
})
