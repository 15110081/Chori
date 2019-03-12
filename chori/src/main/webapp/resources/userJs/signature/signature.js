$(document).ready(function(){
	
	/**
	 * This function is used to load data into table
	 */
	function loadData(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: getAbsolutePath() +  "signature/list",
			contentType: "application/json",
			success: function(data){
				var i = 1;
				if(data.list.length==0){
			
					$('#btnAddNewSignature').prop('disabled', false);
				}
				if(data.list.length>0){
					$('#btnAddNewSignature').prop('disabled', true);
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
							$('<td>').text(value.name),
							$('<td>').text(value.text1),
							$('<td>').html(((value.imgurl==null)||(value.imgurl.trim()=='')?'':'<a class="fancybox"  href="choriSignatureImage/'+value.imgurl+'"><img class="btnImg" height="100" width="100" src="choriSignatureImage/'+value.imgurl+'" data-id="choriSignatureImage/'+value.imgurl+'"/></a>')),
//							$('<td>').html(((value.imgurl1==null)||(value.imgurl1.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl1+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl1+'" data-id="choriAccessoryImage/'+value.imgurl1+'" /></a>')+((value.imgurl2==null)||(value.imgurl2.trim()=='')?'':'<a class="fancybox"  href="choriAccessoryImage/'+value.imgurl2+'"><img class="btnImg" height="100" width="100" src="choriAccessoryImage/'+value.imgurl2+'" data-id="choriAccessoryImage/'+value.imgurl2+'" /></a>')),
							$('<td>').text(value.text2),
							$('<td>').html('<select class="selectpicker selectOption" data-id="'+value.accordersigncode+'">'
									+'<option value="Options" disabled selected>Options</option>'
									+'<option value="Edit">Edit</option>'
									+'<option value="Delete">Delete</option></select>')
					).appendTo('#listSignature');
				});
				action();

				$('#listSignature').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
//				alert("Không lấy đc dữ liệu!");
			}
		});
	};
	
	/**
	 * This function handle update, delete in each row
	 */
	function action(){		
		//When click select
		$('.selectOption').on('change', function (e) {
			var optionSelected = $(this).find("option:selected");
		    var valueSelected  = optionSelected.val();		
		    
		    $(".selectOption").val("Options");
		//Function for edit button
		    if(valueSelected=="Edit"){
			
			var accordersigncode= $(this).data('id');
			
			//call ajax to get detail and load into editDialog
			$.ajax({
				dataType: "json",
				type: 'POST',
				data:JSON.stringify({
					accordersigncode: accordersigncode
				}),
				contentType: "application/json",
				url: getAbsolutePath() +  "signature/detail" ,
				success: function(data){
					//load data into input of dialog
					$("#editSignatureDialog").find('#accordersigncode').val(data.signature.accordersigncode);
					$("#editSignatureDialog").find('#name').val(data.signature.name);
					$("#editSignatureDialog").find('#text1').val(data.signature.text1);
					$("#editSignatureDialog").find('#text2').val(data.signature.text2);
					
					//show image part
					//img1 part
					if((data.signature.imgurl==null)||(data.signature.imgurl.trim()=='')){
						//do nothing
					}else{
						$("#editSignatureDialog").find('#image').append('Image: <img class="btnImgEditDialog" height="250" width="250" src="choriSignatureImage/'+data.signature.imgurl+'" data-id="choriSignatureImage/'+data.signature.imgurl+'" />');//////////
					}										
					//set action of the form:
					$("#editSignatureForm").attr("action", "signature/edit");
				},
				error: function(){
//					alert("error when call ajax to get detail and load into editDialog");
				}
			});
			
			$("#editSignatureDialog").dialog({
				modal: true,
				show:{
					effect:"blind",
					duration: 500
				},
				title: "Edit Signature",
				height: 500,
				width: 700,
				buttons:{
					"Cancel": function(){
						$(this).dialog("close");
						//after that clear 2 image:
						$("#editSignatureDialog").find('#image').html('');
						clearAfterCloseEditDialog();
					}
				},
				close: function(){
					$("#editSignatureDialog").find('#image').html('');
					clearAfterCloseEditDialog();
				}
			});
		};
		
		//Function for delete button
		if(valueSelected=="Delete"){
			var accordersigncode= $(this).data('id');
			$("#deleteSignatureDialog").find("#messageContent").text('Are you sure you want to delete this signature?');
			
			$("#deleteSignatureDialog").dialog({
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
								accordersigncode: accordersigncode
							}),
							contentType: "application/json",
							url: getAbsolutePath() +  "signature/delete",
							success: function(data){
								if(data.status=="ok"){
									if(data.deleteStatus== true){
										//close dialog
										$("#deleteSignatureDialog").dialog("close");
										//reload table
										$("#listSignature").dataTable().fnDestroy();
										$('#listSignature tbody').empty();
										loadData();
										//show delete status to user
										callMessageDialog("Message", 'Delete successfully!');
									}else if(data.deleteStatus== false){
										callMessageDialog("Message", 'Can\'t delete!');
										$("#deleteSignatureDialog").dialog("close");
									}else{
//										alert("this cant be show, 243!");
									}
								}else{
//										alert("unexpected error! (2), 404");
								}
							},
							error: function(){
								callMessageDialog("Message", 'Can\'t delete!');
							}
						});
					},
					"Cancel": function(){
						$("#deleteSignatureDialog").dialog("close");
					}
				}
			});
		};
		});
		
		
		//show image fullsize to user
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
	};
	
	function actionShowEditDialogImage(){
		//show image fullsize to user
//		$('.btnImgEditDialog').click(function(){
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
	};
	
	/**
	 * This function show add new Accessory dialog
	 */
	$('#btnAddNewSignature').click(function(){
		$("#addSignatureDialog").dialog({
			modal: true,
			show:{
				effect:"blind",
				duration: 500
			},
			title: "Add New Signature",
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
				effect:"slide",
				duration: 500
			},
			title: title,
			height: 200,
			width: 350,
			hide: {
				effect: "slide",
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
	 * function when press add button
	 */
	$("#addSignatureDialog").find("#btnAdd").click(function(e){
		
		//check if user have entered required fields
		if(!validateRequiredFieldForAdd()){
			callMessageDialog("Warning message", "Please enter required fields!");
			
			//get value of code and name			
			var name= $("#addSignatureDialog").find("#name").val();
						
			//if user dont enter name or enter just space
			if(name.trim() === '' || name == null){
				$("#addSignatureDialog").find("#errorName").text("Please enter name!");
				$("#addSignatureDialog").find("#errorName").css("color", "red");
				$("#addSignatureDialog").find("#name").css("background-color", "red");
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
		
		var name= $("#addSignatureDialog").find("#name").val();
		if(name.trim() === '' || name == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before add new accessory
	 */
	function validateOverRangeWhenAdd(){
		if($("#addSignatureDialog").find("#name").val().length>50
				|| $("#addSignatureDialog").find("#text1").val().length>100 || $("#addSignatureDialog").find("#text2").val().length>100)//test, need fix
			return false;
		return true;
	}
	
	/**
	 * accessory code on keyup function() to check if user entered, over range, accessory is existed (3 cases)
	 */
	$("#addSignatureDialog").find("#name").on('change keyup paste',function(){
		var name= $(this).val();
		
		//if user dont enter or enter just space
		if(name.trim() === '' || name == null){
			$("#addSignatureDialog").find("#errorName").text("Please enter name!");
			$("#addSignatureDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		if($("#addSignatureDialog").find("#errorName").val().length>50){
			$("#addSignatureDialog").find("#errorName").text("The name field's length is 50. Your input is over range!");
			$("#addSignatureDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}
		
		//check if signaturecode is existed
		$.ajax({
			dataType: "json",
			type: 'POST',
			data:JSON.stringify({
				name: name
			}),
			contentType: "application/json",
			url: getAbsolutePath() +  "signature/isExist",
			success: function(data){
				if(data.isExisted==true){// if code is existed, prevent user add new accessory
					//if code is existed
					$("#addSignatureDialog").find("#errorSignaturecode").text("Signature code is existed, please choose a different one!");
					$("#addSignatureDialog").find("#errorSignaturecode").css("color", "red");
					$("#addSignatureDialog").find("#accordersigncode").css("background-color", "red");
					$("#addSignatureDialog").find("#btnAdd").prop('disabled', true);
				}else if(data.isExisted==false&&$("#addSignatureDialog").find("#accordersigncode").val().length<12){
					//if code is not existed and valid length
					$("#addSignatureDialog").find("#errorSignaturecode").text("Signature code is valid");
					$("#addSignatureDialog").find("#errorSignaturecode").css("color", "green");
					$("#addSignatureDialog").find("#accordersigncode").css("background-color", "white");
					$("#addSignatureDialog").find("#btnAdd").prop('disabled', false);
				}
			},
			error: function(){
				//when user clear the code
				$("#addSignatureDialog").find("#errorSignaturecode").text("Please enter signature code!");
				$("#addSignatureDialog").find("#errorSignaturecode").css("color", "red");
				$(this).css("background-color", "red");
			}
		});
	});
	
	/**
	 * accessory name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#addSignatureDialog").find("#name").on('change keyup paste',function(){
		var name= $(this).val();
		
		//if user dont enter or enter just space
		if(name.trim() === '' || name == null){
			$("#addSignatureDialog").find("#errorName").text("Please enter name!");
			$("#addSignatureDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if($("#addSignatureDialog").find("#name").val().length>50){
			$("#addSignatureDialog").find("#errorName").text("The name field's length is 50. Your input is over range!");
			$("#addSignatureDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#addSignatureDialog").find("#errorName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * accessory dimension on keyup function() to check if over range (1 cases)
	 */
	$("#addSignatureDialog").find("#text1").on('change keyup paste',function(){
		var text1= $(this).val();
		
		//if input of user is over range
		if(text1.length>100){
			$("#addSignatureDialog").find("#errorText1").text("The title field's length is 100. Your input is over range!");
			$("#addSignatureDialog").find("#errorText1").css("color", "red");
			$(this).css("background-color", "red");
		}else if(text1.length<101){//valid input
			$("#addSignatureDialog").find("#errorText1").text("");
			$(this).css("background-color", "white");
		}				
	});
	
	$("#addSignatureDialog").find("#text2").on('change keyup paste',function(){
		var text2= $(this).val();
		//if input of user is over range
		if(text2.length>100){
			$("#addSignatureDialog").find("#errorText2").text("The full name field's length is 100. Your input is over range!");
			$("#addSignatureDialog").find("#errorText2").css("color", "red");
			$(this).css("background-color", "red");
		}else if(text2.length<101){//valid input
			$("#addSignatureDialog").find("#errorText2").text("");
			$(this).css("background-color", "white");
		}
	});	
	
	/**
	 * clear all input + css after close add dialog
	 */
	function clearAfterCloseAddDialog(){
		$("#addSignatureDialog").find("#accordersigncode").val("");
		$("#addSignatureDialog").find("#errorSignaturecode").text("");
		$("#addSignatureDialog").find("#accordersigncode").css("background-color", "white");
		
		$("#addSignatureDialog").find("#name").val("");
		$("#addSignatureDialog").find("#errorName").text("");
		$("#addSignatureDialog").find("#name").css("background-color", "white");
		
		$("#addSignatureDialog").find("#text1").val("");
		$("#addSignatureDialog").find("#errorText1").text("");
		$("#addSignatureDialog").find("#text1").css("background-color", "white");
		
		$("#addSignatureDialog").find("#text2").val("");
		$("#addSignatureDialog").find("#errorText2").text("");
		$("#addSignatureDialog").find("#text2").css("background-color", "white");
		
		//re-enable submit button
		$("#addSignatureDialog").find("#btnAdd").prop('disabled', false);
	}
	
	/**
	 * ---------------------------
	 * End function for adding
	 */
	
	/**
	 * check when user press edit in edit dialog
	 */
	$("#editSignatureDialog").find("#btnEditSig").click(function(){
		//check if user have entered required fields
		if(!validateRequiredFieldForEdit()){
			callMessageDialog("Warning message", "Please enter required fields!");
			
			//get value of name
			var name= $("#editSignatureDialog").find("#name").val();
			
			//if user dont enter name or enter just space
			if(name.trim() === '' || name == null){
				$("#editSignatureDialog").find("#errorName").text("Please enter name!");
				$("#editSignatureDialog").find("#errorName").css("color", "red");
				$("#editSignatureDialog").find("#name").css("background-color", "red");
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
		var name= $("#editSignatureDialog").find("#name").val();
		if(name.trim() === '' || name == null)
			return false;
		
		return true;
	}
	
	/**
	 * This function validate user input if it's over range before editting
	 */
	function validateOverRangeWhenEdit(){
		if($("#editSignatureDialog").find("#name").val().length>50 
				|| $("#editSignatureDialog").find("#text1").val().length>100 || $("#editSignatureDialog").find("#text2").val().length>100)
			return false;
		return true;
	}
	
	/**
	 * [Edit part] accessory name on keyup function() to check if user entered, over range (2 cases)
	 */
	$("#editSignatureDialog").find("#name").on('change keyup paste',function(){
		var name= $(this).val();
		
		//if user dont enter or enter just space
		if(name.trim() === '' || name == null){
			$("#editSignatureDialog").find("#errorName").text("Please enter signature name!");
			$("#editSignatureDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}
		//if input of user is over range
		else if($("#editSignatureDialog").find("#name").val().length>50){
			$("#editSignatureDialog").find("#errorName").text("The name field's length is 50. Your input is over range!");
			$("#editSignatureDialog").find("#errorName").css("color", "red");
			$(this).css("background-color", "red");
		}else{//valid input
			$("#editSignatureDialog").find("#errorName").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * [Edit part] accessory dimension on keyup function() to check if over range (2 cases)
	 */
	$("#editSignatureDialog").find("#text1").on('change keyup paste',function(){
		var text1= $(this).val();
		
		//if input of user is over range
		if(text1.length>100){
			$("#editSignatureDialog").find("#errorText1").text("The title field's length is 100. Your input is over range!");
			$("#editSignatureDialog").find("#errorText1").css("color", "red");
			$(this).css("background-color", "red");
		}else if(text1.length<101){//valid input
			$("#editSignatureDialog").find("#errorText1").text("");
			$(this).css("background-color", "white");
		}
	});
	
	$("#editSignatureDialog").find("#text2").on('change keyup paste',function(){
		var text2= $(this).val();
		
		//if input of user is over range
		if(text2.length>100){
			$("#editSignatureDialog").find("#errorText2").text("The full name field's length is 100. Your input is over range!");
			$("#editSignatureDialog").find("#errorText2").css("color", "red");
			$(this).css("background-color", "red");
		}else if(text2.length<101){//valid input
			$("#editSignatureDialog").find("#errorText2").text("");
			$(this).css("background-color", "white");
		}
	});
	
	/**
	 * clear all input + css after close edit dialog
	 */
	function clearAfterCloseEditDialog(){
		//$("#addAccessoryDialog").find("#name").val("");
		$("#editSignatureDialog").find("#errorName").text("");
		$("#editSignatureDialog").find("#name").css("background-color", "white");
		
		
		$("#editSignatureDialog").find("#errorText1").text("");
		$("#editSignatureDialog").find("#text1").css("background-color", "white");
		
		$("#editSignatureDialog").find("#errorText2").text("");
		$("#editSignatureDialog").find("#text2").css("background-color", "white");
		
		//re-enable submit button
		$("#editSignatureDialog").find("#btnEditSig").prop('disabled', false);
	}
	
	$(".fancybox").fancybox();
	loadData();	
})