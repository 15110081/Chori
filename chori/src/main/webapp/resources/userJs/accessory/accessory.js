$(document).ready(function(){
	
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		
		$("#listAccessory").dataTable().fnDestroy();
		$('#listAccessory tbody').empty();
		
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessory/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
//					var tmp='';
//					if(value.fabricsuppliercontactModelList.length>0){
//						tmp+='<table border="0">';
//						$.each(value.fabricsuppliercontactModelList,function(key,value1){
//							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td></tr>';
//						});
//						tmp+='</table>';
//					}
					
					$('<tr>').append(
							$('<td>').text(i++),
							$('<td>').text(value.accessorycode),
							$('<td>').text(value.accessorygroupcode),
							$('<td>').text(value.name),
							$('<td>').text(value.kind=="Internal"?"General Accessory":"Accessory"),
							$('<td>').text(value.dimension),
							$('<td>').text(value.mode=="Manufacturing"?"Sewing":value.mode),
							$('<td>').text(value.unitcode),
							$('<td>').text(value.percontainer==null?'':value.percontainer),
							$('<td>').text(value.containerUnitCode),
							$('<td>').text(value.colorcode),
							$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
//							$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.accessorycode+'">Edit</button>'),
//							$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.accessorycode+'">Delete</button>')
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorycode+'">'
//									+'<option value="Options" disabled selected>Options</option>'
//									+'<option value="Edit">Edit</option>'
//									+'<option value="Delete">Delete</option></select>')
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listAccessory');
					
//					if(value.mode=="Manufacturing")
//						alert("Sewing");
				});
				action();

				$('#listAccessory').DataTable( {
					"pagingType": "full_numbers",
					"scrollX": true
			    } );
			},
			error: function(){
				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * This function handle update, delete in each row
	 */
//	function action(){
//		
//		//Function for delete button
//		$('.btnDelete').click(function(){
//			var accessorycode= $(this).data('id');
//			$("#deleteAccessoryDialog").find("#messageContent").text('Are you sure you want to delete Accessory "' + accessorycode+'"?');
//			
//			$("#deleteAccessoryDialog").dialog({
//				modal: true,
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Delete Accessory Confirm",
//				height: 200,
//				width: 400,
//				buttons:{
//					"OK": function(){
//						$.ajax({
//							dataType: "json",
//							type: 'POST',
//							data:{},
//							contentType: "application/json",
//							url: "/Chori/accessory/delete/" + accessorycode,
//							success: function(data){
//								if(data.status=="ok"){
//									if(data.deleteStatus== true){
//										//show delete status to user
//										callMessageDialog("Message", 'Delete Accessory "'+ accessorycode+ '" successfully!');
//										//close dialog
//										$("#deleteAccessoryDialog").dialog("close");
//										//reload table
//										$("#listAccessory").dataTable().fnDestroy();
//										$('#listAccessory tbody').empty();
//										loadData();
//									}else if(data.deleteStatus== false){
//										callMessageDialog("Message", 'Can\'t Delete Accessory "'+ accessorycode+ '"!');
//										$("#deleteAccessoryDialog").dialog("close");
//									}else{
//										alert("this cant be show, 243!");
//									}
//								}else{
//									alert("unexpected error! (2), 404");
//								}
//							},
//							error: function(){
////								alert("unexpected error!");
//								callMessageDialog("Message", 'Can\'t Delete Accessory "'+ accessorycode+ '"!');
//								$("#deleteAccessoryDialog").dialog("close");
//							}
//						});
//					},
//					"Cancel": function(){
//						$("#deleteAccessoryDialog").dialog("close");
//					}
//				}
//			});
//		});
//		
//		//Function for edit button
//		$('.btnEdit').click(function(){
//			
//			var accessorycode= $(this).data('id');
//			
//			//call ajax to get detail and load into editDialog
//			$.ajax({
//				dataType: "json",
//				type: 'GET',
//				data:{},
//				contentType: "application/json",
//				url: "/Chori/accessory/detail/" + accessorycode,
//				success: function(data){
//					//load data into input of dialog
//					$("#editAccessoryDialog").find('#accessorycode').val(data.accessory.accessorycode);
//					$("#editAccessoryDialog").find('#name').val(data.accessory.name);
//					$("#editAccessoryDialog").find('#kind').val(data.accessory.kind);
//					$("#editAccessoryDialog").find('#dimension').val(data.accessory.dimension);
//					$("#editAccessoryDialog").find('#mode').val(data.accessory.mode);
//					$("#editAccessoryDialog").find('#unitcode').val(data.accessory.unitcode);
//					$("#editAccessoryDialog").find('#colorcode').val(data.accessory.colorcode);
//					
//					$("#editAccessoryDialog").find('#accessorygroupcode').val(data.accessory.accessorygroupcode);
//					$("#editAccessoryDialog").find('#percontainer').val(data.accessory.percontainer);
//					$("#editAccessoryDialog").find('#containerUnitCode').val(data.accessory.containerUnitCode);
//					
//					//show image part	
//					//img1 part
//					if((data.accessory.imgurl1==null)||(data.accessory.imgurl1.trim()=='')){
//						//do nothing
//					}else{
//						$("#editAccessoryDialog").find('#2images').append('Image1: <img class="btnImgEditDialog" height="250" width="250" src="choriAccessoryImage/'+data.accessory.imgurl1+'" data-id="choriAccessoryImage/'+data.accessory.imgurl1+'" />');//////////
//					}
//					//img2 part
//					if((data.accessory.imgurl2==null)||(data.accessory.imgurl2.trim()=='')){
//						//do nothing
//					}else{
//						$("#editAccessoryDialog").find('#2images').append('Image2: <img class="btnImgEditDialog" height="250" width="250" src="choriAccessoryImage/'+data.accessory.imgurl2+'" data-id="choriAccessoryImage/'+data.accessory.imgurl2+'" />');//////////
//					}
//					
//					actionShowEditDialogImage();
//					
//					//set action of the form:
//					$("#editAccessoryForm").attr("action", "accessory/edit");
//				},
//				error: function(){
//					alert("error when call ajax to get detail and load into editDialog");
//				}
//			});
//			
//			$("#editAccessoryDialog").dialog({
//				modal: true,
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Edit Accessory",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Cancel": function(){
//						$(this).dialog("close");
//						//after that clear 2 image:
//						$("#editAccessoryDialog").find('#2images').html('');
//						clearAfterCloseEditDialog();
//					}
//				},
//				close: function(){
//					$("#editAccessoryDialog").find('#2images').html('');
//					clearAfterCloseEditDialog();
//				}
//			});
//		});
//		
//		//show image fullsize to user
//		$('.btnImg').click(function(){
//			var src= $(this).data('id');
//			
//			$("#imageToShow").attr("src",src);
//			
//			$("#imageDialog").dialog({
//				modal: true,
//				show:{
//					effect:"blind",
//					duration: 500
//				},
//				title: "Image Full Size",
//				height: 500,
//				width: 700,
//				buttons:{
//					"Close": function(){
//						$(this).dialog("close");
//					}
//				},
//				close: function(){
//				}
//			});
//		});
//	};
	
	//action change button to dropdownlist
	function action(){
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();
		    
		    //lấy ra data-id
		    var accessorycode= $(this).data('id');
		    //alert('You choose '+roleIdSeleted+', and the option is: '+ valueSelected);
		    $(".selectOption").val("Options");
		    
		    
		    //If user choose edit option
		    if(valueSelected=="Edit"){
		    	//call ajax to get detail and load into editDialog
		    	editOption(accessorycode);
				///////////////////
				
				$("#editAccessoryDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Edit Accessory",
					height: 500,
					width: 700,
					buttons:{
						"Cancel": function(){
							$(this).dialog("close");
							//after that clear 2 image:
							$("#editAccessoryDialog").find('#2images').html('');
							clearAfterCloseEditDialog();
						}
					},
					close: function(){
						$("#editAccessoryDialog").find('#2images').html('');
						clearAfterCloseEditDialog();
					}
				});
		    };
		    
		    //if user choose delete option
		    if(valueSelected=="Delete"){
		    	$("#deleteAccessoryDialog").find("#messageContent").text('Are you sure you want to delete accessory "' + accessorycode+'"?');
				
				$("#deleteAccessoryDialog").dialog({
					modal: true,
					show:{
						effect:"blind",
						duration: 500
					},
					title: "Delete Accessory Confirm",
					height: 200,
					width: 400,
					buttons:{
						"OK": function(){
							$.ajax({
								dataType: "json",
								type: 'POST',
								data: JSON.stringify({
									accessorycode: accessorycode
								}),
								contentType: "application/json",
								url: getAbsolutePath() +  "accessory/delete",
								success: function(data){
									if(data.status=="ok"){
										if(data.deleteStatus== true){
											//show delete status to user
											callMessageDialog("Message", 'Delete accessory "'+ accessorycode+ '" successfully!');
											//close dialog
											$("#deleteAccessoryDialog").dialog("close");
											//reload table
											$("#listAccessory").dataTable().fnDestroy();
											$('#listAccessory tbody').empty();
//											loadData();
											reloadTableWithStatus();
										}else if(data.deleteStatus== false){
											callMessageDialog("Message", 'Can\'t delete accessory "'+ accessorycode+ '"!');
											$("#deleteAccessoryDialog").dialog("close");
										}else{
											alert("this cant be show, 243!");
										}
									}else{
										alert("unexpected error! (2), 404");
									}
								},
								error: function(){
//									alert("unexpected error!");
									callMessageDialog("Message", 'Can\'t delete accessory "'+ accessorycode+ '"!');
									$("#deleteAccessoryDialog").dialog("close");
								}
							});
						},
						"Cancel": function(){
							$("#deleteAccessoryDialog").dialog("close");
						}
					}
				});
		    };
		    //end if user choose delete option
		});
	};
	//end action change button to dropdownlist
	
	function editOption(accessorycode){
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				accessorycode: accessorycode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "accessory/detail",
			success: function(data){
				//load data into input of dialog
				$("#editAccessoryDialog").find('#accessorycode').val(data.accessory.accessorycode);
				$("#editAccessoryDialog").find('#name').val(data.accessory.name);
				$("#editAccessoryDialog").find('#kind').val(data.accessory.kind);
				$("#editAccessoryDialog").find('#dimension').val(data.accessory.dimension);
				$("#editAccessoryDialog").find('#mode').val(data.accessory.mode);
				$("#editAccessoryDialog").find('#unitcode').val(data.accessory.unitcode);
				$("#editAccessoryDialog").find('#colorcode').val(data.accessory.colorcode);
				
				$("#editAccessoryDialog").find('#accessorygroupcode').val(data.accessory.accessorygroupcode);
				$("#editAccessoryDialog").find('#percontainer').val(data.accessory.percontainer);
				$("#editAccessoryDialog").find('#containerUnitCode').val(data.accessory.containerUnitCode);
				$("#editAccessoryDialog").find('#status').val(data.accessory.status);
				$("#editAccessoryDialog").find('#accessorychoricode').val(data.accessory.accessorychoricode);
				
				//show image part
				$("#editAccessoryDialog").find('#2images').html('');
				//img1 part
				if((data.accessory.imgurl1==null)||(data.accessory.imgurl1.trim()=='')){
					//do nothing
				}else{
					$("#editAccessoryDialog").find('#2images').append('Image 1: <a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl1+'"><img class="btnImgEditDialog" height="250" width="250" src="choriAccessoryImage/'+data.accessory.imgurl1+'" data-id="choriAccessoryImage/'+data.accessory.imgurl1+'" /></a> <button class="btn btn-danger btnDeleteImg" data-id="'+data.accessory.imgurl1+'">Delete Image 1</button> <br/>');//////////
				}
				//img2 part
				if((data.accessory.imgurl2==null)||(data.accessory.imgurl2.trim()=='')){
					//do nothing
				}else{
					$("#editAccessoryDialog").find('#2images').append('Image 2: <a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl2+'"><img class="btnImgEditDialog" height="250" width="250" src="choriAccessoryImage/'+data.accessory.imgurl2+'" data-id="choriAccessoryImage/'+data.accessory.imgurl2+'" /></a> <button class="btn btn-danger btnDeleteImg" data-id="'+data.accessory.imgurl2+'">Delete Image 2</button> <br/>');//////////
				}
				
				//img3 part
				if((data.accessory.imgurl3==null)||(data.accessory.imgurl3.trim()=='')){
					//do nothing
				}else{
					$("#editAccessoryDialog").find('#2images').append('Image 3: <a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl3+'"><img class="btnImgEditDialog" height="250" width="250" src="choriAccessoryImage/'+data.accessory.imgurl3+'" data-id="choriAccessoryImage/'+data.accessory.imgurl3+'" /></a> <button class="btn btn-danger btnDeleteImg" data-id="'+data.accessory.imgurl3+'">Delete Image 3</button> <br/>');//////////
				}
				
				//img4 part
				if((data.accessory.imgurl4==null)||(data.accessory.imgurl4.trim()=='')){
					//do nothing
				}else{
					$("#editAccessoryDialog").find('#2images').append('Image 4: <a class="fancybox"  href="choriAccessoryImage/'+data.accessory.imgurl4+'"><img class="btnImgEditDialog" height="250" width="250" src="choriAccessoryImage/'+data.accessory.imgurl4+'" data-id="choriAccessoryImage/'+data.accessory.imgurl4+'" /></a> <button class="btn btn-danger btnDeleteImg" data-id="'+data.accessory.imgurl4+'">Delete Image 4</button> <br/>');//////////
				}
				
//				actionShowEditDialogImage();
				
				//set action of the form:
				$("#editAccessoryForm").attr("action", "accessory/edit");
				
				$(".btnDeleteImg").click(function(e){
					e.preventDefault();
					
					var imgUrlDelete = $(this).data('id');
					
					$("#deleteImageDialog").find("#messageContent").text("Are you sure to delete this image?");
					
					//mở dialog
					$("#deleteImageDialog").dialog({
						modal: true,
						show:{
							effect:"blind",
							duration: 500
						},
						title: "Delete Confirm",
						height: 300,
						width: 300,
						buttons:{
							"OK": function(){
								//
								
//								alert(imgUrlDelete+""+ $("#editAccessoryDialog").find('#accessorycode').val());
								
								$.ajax({
									dataType: "json",
									type: 'POST',
									data: JSON.stringify({
										accessorycode: $("#editAccessoryDialog").find('#accessorycode').val(),
										imgUrlDelete: imgUrlDelete
									}),
									contentType: "application/json",
									url: getAbsolutePath() +  "accessory/deleteImage",
									success: function(data){
										//alert("OK");
										if(data.deleteImageStatus==true){
											callMessageDialog("Message", 'Delete image successfully!');
											$("#deleteImageDialog").dialog("close");
											editOption(accessorycode);
											loadData();
										}
									},
									error: function(){
										alert("Lỗi cmnr");
									}
								});
								//
							},
							"Cancel": function(){
								$(this).dialog("close");
							}
						},
						close: function(){
							
						}
					});
					//end mở dialog
				});
			},
			error: function(){
				alert("error when call ajax to get detail and load into editDialog");
			}
		});
	}
	
	///hàm onChange trên ddlStatus
	$('#ddlStatus').on('change',function(){
		//lấy ra value đc chọn
		var optionSelected = $(this).find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessory/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAccessory").dataTable().fnDestroy();
				$('#listAccessory tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
//					var tmp='';
//					if(value.factorycontactModellist.length>0){
//						tmp+='<table border="0">';
//						$.each(value.factorycontactModellist,function(key,value1){
//							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td></tr>';
//						});
//						tmp+='</table>';
//					}
					
					//nếu value là all thì gọi lại loadData()
					if(valueSelected=='All'){
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.accessorycode),
								$('<td>').text(value.accessorygroupcode),
								$('<td>').text(value.name),
								$('<td>').text(value.kind=="Internal"?"General Accessory":"Accessory"),
								$('<td>').text(value.dimension),
								$('<td>').text(value.mode=="Manufacturing"?"Sewing":value.mode),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.percontainer==null?'':value.percontainer),
								$('<td>').text(value.containerUnitCode),
								$('<td>').text(value.colorcode),
								$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.accessorycode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.accessorycode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorycode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listAccessory');
						
					}else{//không thì xuất ra theo cái status
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.accessorycode),
									$('<td>').text(value.accessorygroupcode),
									$('<td>').text(value.name),
									$('<td>').text(value.kind=="Internal"?"General Accessory":"Accessory"),
									$('<td>').text(value.dimension),
									$('<td>').text(value.mode=="Manufacturing"?"Sewing":value.mode),
									$('<td>').text(value.unitcode),
									$('<td>').text(value.percontainer==null?'':value.percontainer),
									$('<td>').text(value.containerUnitCode),
									$('<td>').text(value.colorcode),
									$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.accessorycode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.accessorycode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorycode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessory');
						}
					}
				});
				//console.log(data);
				action();

				$('#listAccessory').DataTable( {
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
	///end 
	
	///
	function reloadTableWithStatus(){
		//lấy ra value đc chọn
		var optionSelected = $('#ddlStatus').find("option:selected");
		var valueSelected  = optionSelected.val();
		
		//
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessory/list",
			contentType: "application/json",
			success: function(data){
				
				$("#listAccessory").dataTable().fnDestroy();
				$('#listAccessory tbody').empty();
				
				var i = 1;
				if(data.list.length==0){
//					alert("Bảng hiện tại chưa có dữ liệu.");
				}
				$.each(data.list,function(key,value){
//					var tmp='';
//					if(value.factorycontactModellist.length>0){
//						tmp+='<table border="0">';
//						$.each(value.factorycontactModellist,function(key,value1){
//							tmp+='<tr><td>'+value1.name+'</td><td>'+value1.email+'</td></tr>';
//						});
//						tmp+='</table>';
//					}
					
					//nếu value là all thì gọi lại loadData()
					if(valueSelected=='All'){
						
						$('<tr>').append(
								$('<td>').text(i++),
								$('<td>').text(value.accessorycode),
								$('<td>').text(value.accessorygroupcode),
								$('<td>').text(value.name),
								$('<td>').text(value.kind=="Internal"?"General Accessory":"Accessory"),
								$('<td>').text(value.dimension),
								$('<td>').text(value.mode=="Manufacturing"?"Sewing":value.mode),
								$('<td>').text(value.unitcode),
								$('<td>').text(value.percontainer==null?'':value.percontainer),
								$('<td>').text(value.containerUnitCode),
								$('<td>').text(value.colorcode),
								$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
//								$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.accessorycode+'">Edit</button>'),
//								$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.accessorycode+'">Delete</button>')
								$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorycode+'">'
										+'<option value="Options" disabled selected>Options</option>'
										+'<option value="Edit">Edit</option>'
										+'<option value="Delete">Delete</option></select>')
						).appendTo('#listAccessory');
						
					}else{//không thì xuất ra theo cái status
						
						if(valueSelected==value.status){
							$('<tr>').append(
									$('<td>').text(i++),
									$('<td>').text(value.accessorycode),
									$('<td>').text(value.accessorygroupcode),
									$('<td>').text(value.name),
									$('<td>').text(value.kind=="Internal"?"General Accessory":"Accessory"),
									$('<td>').text(value.dimension),
									$('<td>').text(value.mode=="Manufacturing"?"Sewing":value.mode),
									$('<td>').text(value.unitcode),
									$('<td>').text(value.percontainer==null?'':value.percontainer),
									$('<td>').text(value.containerUnitCode),
									$('<td>').text(value.colorcode),
									$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
//									$('<td>').html('<button class="btn btn-primary btnEdit" data-id="'+value.accessorycode+'">Edit</button>'),
//									$('<td>').html('<button class="btn btn-danger btnDelete" data-id="'+value.accessorycode+'">Delete</button>')
									$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accessorycode+'">'
											+'<option value="Options" disabled selected>Options</option>'
											+'<option value="Edit">Edit</option>'
											+'<option value="Delete">Delete</option></select>')
							).appendTo('#listAccessory');
						}
					}
				});
				//console.log(data);
				action();

				$('#listAccessory').DataTable( {
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
	///
	
	/**
	 * This function is used to show edit dialog image
	 */
	function actionShowEditDialogImage(){
		//show image fullsize to user
		$('.btnImgEditDialog').click(function(){
			var src= $(this).data('id');
			
			$("#imageToShow").attr("src",src);
			
			$("#imageDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Image Full Size",
				height: 500,
				width: 700,
				buttons:{
					"Close": function(){
						$(this).dialog("close");
					}
				},
				close: function(){
				}
			});
		});
	};
	
	/**
	 * This function show add new Accessory dialog
	 */
	$('#btnAddNewAccessory').click(function(){
		$("#addAccessoryDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Accessory",
			height: 500,
			width: 700,
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
	 * This function is used to load data into dropDownLists
	 */
	function loadAllDropDownList() {
		//alert("Test OK!");

		//add <option> into select #kind
		$('#kind').append($('<option>', {
			value : 'Internal',
			text : 'General Accessory'
		}), $('<option>', {
			value : 'External',
			text : 'Accessory'
		}));
		//edit part
		$("#editAccessoryDialog").find('#kind').append($('<option>', {
			value : 'Internal',
			text : 'General Accessory'
		}), $('<option>', {
			value : 'External',
			text : 'Accessory'
		}));
		
		//add <option> into select #mode
		$('#mode').append($('<option>', {
			value : 'Packing',
			text : 'Packing'
		}), $('<option>', {
			value : 'Manufacturing',
			text : 'Sewing'
		}));
		//edit part
		$("#editAccessoryDialog").find('#mode').append($('<option>', {
			value : 'Packing',
			text : 'Packing'
		}), $('<option>', {
			value : 'Manufacturing',
			text : 'Sewing'
		}));
		
		//add <option> into select #status
		$('#status').append($('<option>', {
			value : 'Active',
			text : 'Active'
		}), $('<option>', {
			value : 'In-Active',
			text : 'In-Active'
		}));
		//edit part
		$("#editAccessoryDialog").find('#status').append($('<option>', {
			value : 'Active',
			text : 'Active'
		}), $('<option>', {
			value : 'In-Active',
			text : 'In-Active'
		}));
		
		//call ajax and load data into #unitcode
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "unit/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#unitcode').append($('<option>', {
						value : value.unitcode,
						text : value.name
					}));
					//edit part
					$("#editAccessoryDialog").find('#unitcode').append($('<option>', {
						value : value.unitcode,
						text : value.name
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu unit (40)!');
			}
		});
		
		//call ajax and load data into #colorcode
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "color/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#colorcode').append($('<option>', {
						value : value.colorcode,
						text : value.colorcode
					}));
					//edit part
					$("#editAccessoryDialog").find('#colorcode').append($('<option>', {
						value : value.colorcode,
						text : value.colorcode
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu color (60)!');
			}
		});
		
		//call ajax and load data into #ddlgroupName
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "accessorygroup/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#accessorygroupcode').append($('<option>', {
						value : value.accessorygroupCode,
						text : value.accessorygroupCode
					}));
					//edit part
					$("#editAccessoryDialog").find('#accessorygroupcode').append($('<option>', {
						value : value.accessorygroupCode,
						text : value.accessorygroupCode
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu color (60)!');
			}
		});
		
		//call ajax and load data into #containerUnit
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "unit/list",
			contentType: "application/json",
			success: function(data){
				$.each(data.list,function(key,value){
					$('#containerUnitCode').append($('<option>', {
						value : value.unitcode,
						text : value.name
					}));
					//edit part
					$("#editAccessoryDialog").find('#containerUnitCode').append($('<option>', {
						value : value.unitcode,
						text : value.name
					}));
				});
			},
			error: function(){
				alert('Lỗi load dữ liệu color (60)!');
			}
		});
	}
	
	/**
	 * function when press add button
	 */
	$("#addAccessoryDialog").find("#btnAdd").click(function(e){
		
		//check if user have entered required fields
		if(!validateRequiredFieldForAdd()){
			callMessageDialog("Warning message", "Please enter required fields!");
			
			//get value of code and name
			var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
			var name= $("#addAccessoryDialog").find("#name").val();
			
			//if user dont enter code or enter just space
			if(accessorycode.trim() === '' || accessorycode == null){
				$("#addAccessoryDialog").find("#errorAccessorycode").text("Please enter accessory code!");
				$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "red");
				$("#addAccessoryDialog").find("#accessorycode").css("background-color", "red");
			}
			//if user dont enter name or enter just space
			if(name.trim() === '' || name == null){
				$("#addAccessoryDialog").find("#errorName").text("Please enter accessory name!");
				$("#addAccessoryDialog").find("#errorName").css("color", "red");
				$("#addAccessoryDialog").find("#name").css("background-color", "red");
			}
			return false;
		}else{
			//if user have entered all required fields, then check if input is overRange
			if(!validateOverRangeWhenAdd()){
				callMessageDialog("Warning message", "Your input is over range!");
				return false;
			}
		}
	});
	
	/**
	 * This function is used to validate required fields before adding
	 */
	function validateRequiredFieldForAdd(){
		var accessorycode= $("#addAccessoryDialog").find("#accessorycode").val();
		if(accessorycode.trim() === '' || accessorycode == null)
			return false;
		
		var name= $("#addAccessoryDialog").find("#name").val();
		if(name.trim() === '' || name == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new accessory
	 */
	function validateOverRangeWhenAdd(){
		if($("#addAccessoryDialog").find("#accessorycode").val().length>100 || $("#addAccessoryDialog").find("#name").val().length>50 
				|| $("#addAccessoryDialog").find("#dimension").val().length>100)//test, need fix
			return false;
		return true;
	}
	
	//do not allow input " character 
	$("#addAccessoryDialog").find("#accessorycode").keydown(function(e) {
		if(e.keyCode==222) return false;
	});
	
	/**
	 * accessory code on keyup function() to check if user entered, over range, accessory is existed (3 cases)
	 */
	$("#addAccessoryDialog").find("#accessorycode").on('change keyup paste',function(){
		var accessorycode= $(this).val();
		
		//if user dont enter or enter just space
		if(accessorycode.trim() === '' || accessorycode == null){
			$("#addAccessoryDialog").find("#errorAccessorycode").text("Please enter accessory code!");
			$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		if($("#addAccessoryDialog").find("#accessorycode").val().length>100){
			$("#addAccessoryDialog").find("#errorAccessorycode").text("The code field's length is 100. Your input is over range!");
			$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if accessorycode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data: JSON.stringify({
				accessorycode: accessorycode
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "accessory/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addAccessoryDialog").find("#errorAccessorycode").text("Accessory code you enter is existed, please choose different one!");
					$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "red");
					$("#addAccessoryDialog").find("#accessorycode").css("background-color", "red");
					$("#addAccessoryDialog").find("#btnAdd").prop('disabled', true);
				}else if(data.isExisted==false&&$("#addAccessoryDialog").find("#accessorycode").val().length<101){
					//if code is not existed and valid length
					$("#addAccessoryDialog").find("#errorAccessorycode").text("Accessory code is valid");
					$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "green");
					$("#addAccessoryDialog").find("#accessorycode").css("background-color", "white");
					$("#addAccessoryDialog").find("#btnAdd").prop('disabled', false);
					//if user dont enter or enter just space
					if(accessorycode.trim() === '' || accessorycode == null){
						$("#addAccessoryDialog").find("#errorAccessorycode").text("Please enter accessory code!");
						$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "red");
						$(this).css("background-color", "red");
					}
				}
			},
			error: function(){
				//when user clear the code
				$("#addAccessoryDialog").find("#errorAccessorycode").text("Please enter accessory code!");
				$("#addAccessoryDialog").find("#errorAccessorycode").css("color", "red");
				$(this).css("background-color", "red");
			}
		});
	});
	
	/**
	 * accessory name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addAccessoryDialog").find("#name").on('change keyup paste',function(){
		var name= $(this).val();
		
		//if user dont enter or enter just space
		if(name.trim() === '' || name == null){
			$("#addAccessoryDialog").find("#errorName").text("Please enter accessory name!");
			$("#addAccessoryDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if($("#addAccessoryDialog").find("#name").val().length>50){
			$("#addAccessoryDialog").find("#errorName").text("The name field's length is 50. Your input is over range!");
			$("#addAccessoryDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addAccessoryDialog").find("#errorName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * accessory dimension on keyup function() to check if over range (1 cases)
	 */
	$("#addAccessoryDialog").find("#dimension").on('change keyup paste',function(){
		var dimension= $(this).val();
		
		//if input of user is over range
		if(dimension.length>100){
			$("#addAccessoryDialog").find("#errorDimension").text("The dimension field's length is 100. Your input is over range!");
			$("#addAccessoryDialog").find("#errorDimension").css("color", "red");
			$(this).css("background-color", "red");
		}else if(dimension.length<101){//valid input
			$("#addAccessoryDialog").find("#errorDimension").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * clear all input + css after close add dialog
	 */
	function clearAfterCloseAddDialog(){
		$("#addAccessoryDialog").find("#accessorycode").val("");
		$("#addAccessoryDialog").find("#errorAccessorycode").text("");
		$("#addAccessoryDialog").find("#accessorycode").css("background-color", "white");
		
		$("#addAccessoryDialog").find("#name").val("");
		$("#addAccessoryDialog").find("#errorName").text("");
		$("#addAccessoryDialog").find("#name").css("background-color", "white");
		
		$("#addAccessoryDialog").find("#dimension").val("");
		$("#addAccessoryDialog").find("#errorDimension").text("");
		$("#addAccessoryDialog").find("#dimension").css("background-color", "white");
		
		//re-enable submit button
		$("#addAccessoryDialog").find("#btnAdd").prop('disabled', false);
	}
	
	/**
	 * ---------------------------
	 * End function for adding
	 */
	
	/**
	 * check when user press edit in edit dialog
	 */
	$("#editAccessoryDialog").find("#btnEditAcc").click(function(){
		//check if user have entered required fields
		if(!validateRequiredFieldForEdit()){
			callMessageDialog("Warning message", "Please enter required fields!");
			
			//get value of name
			var name= $("#editAccessoryDialog").find("#name").val();
			
			//if user dont enter name or enter just space
			if(name.trim() === '' || name == null){
				$("#editAccessoryDialog").find("#errorName").text("Please enter accessory name!");
				$("#editAccessoryDialog").find("#errorName").css("color", "red");
				$("#editAccessoryDialog").find("#name").css("background-color", "red");
			}
			return false;
		}else{
			//if user have entered all required fields, then check if input is overRange
			if(!validateOverRangeWhenEdit()){
				callMessageDialog("Warning message", "Your input is over range!");
				return false;
			}
		}
	});
	
	/**
	 * This function is used to validate required fields before editting
	 */
	function validateRequiredFieldForEdit(){
		var name= $("#editAccessoryDialog").find("#name").val();
		if(name.trim() === '' || name == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before editting
	 */
	function validateOverRangeWhenEdit(){
		if($("#editAccessoryDialog").find("#name").val().length>50 
				|| $("#editAccessoryDialog").find("#dimension").val().length>100)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] accessory name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editAccessoryDialog").find("#name").on('change keyup paste',function(){
		var name= $(this).val();
		
		//if user dont enter or enter just space
		if(name.trim() === '' || name == null){
			$("#editAccessoryDialog").find("#errorName").text("Please enter accessory name!");
			$("#editAccessoryDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if($("#editAccessoryDialog").find("#name").val().length>50){
			$("#editAccessoryDialog").find("#errorName").text("The name field's length is 50. Your input is over range!");
			$("#editAccessoryDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editAccessoryDialog").find("#errorName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] accessory dimension on keyup function() to check if over range (2 cases)
	 */
	$("#editAccessoryDialog").find("#dimension").on('change keyup paste',function(){
		var dimension= $(this).val();
		
		//if input of user is over range
		if(dimension.length>100){
			$("#editAccessoryDialog").find("#errorDimension").text("The dimension field's length is 100. Your input is over range!");
			$("#editAccessoryDialog").find("#errorDimension").css("color", "red");
			$(this).css("background-color", "red");
		}else if(dimension.length<101){//valid input
			$("#editAccessoryDialog").find("#errorDimension").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * clear all input + css after close edit dialog
	 */
	function clearAfterCloseEditDialog(){
		//$("#addAccessoryDialog").find("#name").val("");
		$("#editAccessoryDialog").find("#errorName").text("");
		$("#editAccessoryDialog").find("#name").css("background-color", "white");
		
		//$("#addAccessoryDialog").find("#dimension").val("");
		$("#editAccessoryDialog").find("#errorDimension").text("");
		$("#editAccessoryDialog").find("#dimension").css("background-color", "white");
		
		//re-enable submit button
		$("#editAccessoryDialog").find("#btnEditAcc").prop('disabled', false);
	}
	
	/**
	 * -- Hàm kiểm tra add --
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
		if(getUrlParameter('addResultStatus')==='Success'){
			callMessageDialog("Message", "Add new accessory successfully!");
		}
		if(getUrlParameter('addResultStatus')==='Failed'){
			callMessageDialog("Warning Message", "Add new accessory failed!");
		}
		//message for edit part
		if(getUrlParameter('editResultStatus')==='Success'){
			callMessageDialog("Message", "Edit accessory successfully!");
		}
		if(getUrlParameter('editResultStatus')==='Failed'){
			callMessageDialog("Warning Message", "Edit accessory failed!");
		}
	};
	/**
	 * -- End hàm kiểm tra add --
	 */
	
	/**
	 * Hàm max length cho number
	 */
	var inputQuantity = [];
	$(".quantity").each(function(i) {
        inputQuantity[i]=this.defaultValue;
        $(this).data("idx",i); // save this field's index to access later
      });
	  $(".quantity").on("keyup", function (e) {
	    var $field = $(this),
	        val=this.value,
	        $thisIndex=parseInt($field.data("idx"),10); // retrieve the index
	//        window.console && console.log($field.is(":invalid"));
	      //  $field.is(":invalid") is for Safari, it must be the last to not error in IE8
	    if (this.validity && this.validity.badInput || isNaN(val) || $field.is(":invalid") ) {
	        this.value = inputQuantity[$thisIndex];
	        return;
	    } 
	    if (val.length > Number($field.attr("maxlength"))) {
	      val=val.slice(0, 5);
	      $field.val(val);
	    }
	    inputQuantity[$thisIndex]=val;
	  });
	/**
	 * End hàm max length cho number
	 */
	
	$(".fancybox").fancybox();
	
//	loadData();
	loadAllDropDownList();
	checkIfAddSuccessfully();
})