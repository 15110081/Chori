$(document).ready(function(){
	var AccessoryCodeArray = [];
	$('#btnAdd').on('click',function(){
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/orderinternalaccessory/listaccessory",
			contentType: "application/json",
			success: function(data){
				$("#listAccessory").dataTable().fnDestroy();
				$('#listAccessory tbody').empty();
				var i = 1;
				if(data.list.length==0){
					alert("Table have no data..");
				}
				$.each(data.list,function(key,value){
					AccessoryCodeArray.push(value.accessorycode);
					$('<tr>').append(
							$('<td>').html('<input type="checkbox" name="chkListAcc" id="chk'+value.accessorycode+'" value="'+value.accessorycode+'" checked/>'),
							$('<td>').text(i++),
							$('<td>').text(value.name),							
							$('<td>').text(value.accessorycode)
							
					).appendTo('#listAccessory');
					
				});
				$('#listAccessory').DataTable( {
					"pagingType": "full_numbers"
			    } );
			},
			error: function(){
				alert("Can not get data!");
			}
		
		});
		$("#dialogOrderInternalAccessoryDetail").dialog({
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
				        	accessoryArray_isChecked.push({accessorycode:AccessoryCodeArray[i]});

					    }
				        else if(!$("#chk"+AccessoryCodeArray[i]+"").is(":checked")){
				        	accessoryArray_isUnChecked.push(AccessoryCodeArray[i]);			        	
				        }
					}
					
					console.log(accessoryArray_isChecked);
					///
					$.ajax({
						dataType: "json",
						type: 'POST',
						data: JSON.stringify(accessoryArray_isChecked),
						contentType: "application/json",
						url: "/Chori/orderinternalaccessoryDetail/add",
						success: function(data){
							if(data.status=="ok"){
									callMessageDialog("Message", "Assign Accessory successfully!");
							}else{
								alert("Error");
							}
						},
						error: function(){
							alert("failed");
						}
					});
					///
				},
				"Cancel": function(){
					$("#dialogOrderInternalAccessoryDetail").dialog("close");
					$("#listAccessory").dataTable().fnDestroy();
					$('#listAccessory tbody').empty();
				}
			},
			close: function(){
				
			}
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
						$("#messageDialog").dialog("close");
					}
				}
			});
		}
		
	})

})