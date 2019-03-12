$(document).ready(function(){
		
	function loadFpiGridDetailByLotNumberAndVersion(lotnumber, version){
		//xóa tạo lại table
		$("#FpiGridDetailTableCover").html('');
		$("#FpiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
					'id="listFpiGridDetail">'+
					'<thead>'+
					'<tr>'+
					'<th rowspan="2">Total Pcs</th>'+
					'<th rowspan="2">Color</th>'+
					'<th colspan="3">Garment Style</th>'+
					'<th rowspan="2">Size</th>'+
					'<th rowspan="2">FA</th>'+
					'<th rowspan="2">FPI</th>'+
					'</tr>'+
					'<tr>'+
					'<th>Name</th>'+
					'<th>Image</th>'+
					'<th>Type</th>'+
					'</tr>'+
					'</thead>'+
					'</table>');
		
		///gọi ajax lấy FPI grid detail
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/fpi/fpiGridDetail/"+lotnumber+"/"+version,
//			url: "/Chori/fpi/fpiGridDetail/"+"Lot1/"+1,
			contentType: "application/json",
			success: function(data){
				
				if(data.fpiGridDetail.length==0){
					alert("Table have no data.");
				}
				
				$.each(data.fpiGridDetail,function(key,value){
					$('<tr>').append(
							$('<td>').html("<span "+((value.totalFpiPcs<=value.totalFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.totalFaPcs+"/"+value.totalFpiPcs+"/"+(value.totalFpiPcs-value.totalFaPcs)+"<span>"),
							$('<td>').html("<span "+((value.colorFpiPcs<=value.colorFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.colorName+'<br>'+value.colorFaPcs+"/"+value.colorFpiPcs+"/"+(value.colorFpiPcs-value.colorFaPcs)+"<span>"),
							$('<td>').html("<span "+((value.garmentstyleFpiPcs<=value.garmentstyleFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.garmentstyle+'<br>'+value.garmentstyleFaPcs+"/"+value.garmentstyleFpiPcs+"/"+(value.garmentstyleFpiPcs-value.garmentstyleFaPcs)+"<span>"),
							$('<td>').html(((value.imgUrl==null)||(value.imgUrl.trim()==''))?'':
										'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl+'" data-id="choriGarmentStyleImage/'+value.imgUrl+'" /></a>'),
							$('<td>').html("<span "+((value.typeFpiPcs<=value.typeFaPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.typecode+'<br>'+value.typeFaPcs+"/"+value.typeFpiPcs+"/"+(value.typeFpiPcs-value.typeFaPcs)+"<span>"),
							$('<td>').html(value.sizename),
							$('<td>').html(value.faPcs),
							$('<td>').html(value.fpiPcs)
					).appendTo('#listFpiGridDetail');	
					
					preAccessoryName = value.accessoryName;
				});
				
				$('#listFpiGridDetail').DataTable( {
					"sPaginationType": "full_numbers",
					paging: true,
			        rowsGroup: [0,1,2,3,4,5]
			    } );
			},
			error: function(){
//				alert("Can not get data!");
//				callMessageDialog("Message", "Something wrong with pi grid detail! 147");
				callMessageDialog("Message", "Not Assign Pi Grid Yet !");
			}
		});
		///end gọi ajax lấy FPI grid detail	
	}
	
	
	/***
	 * ------------------------------------------------ End phần load fpi grid detail
	 */
	
	//xóa tạo lại table
	$("#PiGridDetailTableCover").html('');
	$("#PiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
				'id="listPiGridDetail">'+
				'<thead>'+
				'<tr>'+
				'<th rowspan="2">Total Pcs</th>'+
				'<th rowspan="2">Color</th>'+
				'<th colspan="3">Garment Style</th>'+
				'<th rowspan="2">Size</th>'+
				'<th rowspan="2">Pcs</th>'+
				'<th rowspan="2">FA</th>'+
				'</tr>'+
				'<tr>'+
				'<th>Name</th>'+
				'<th>Image</th>'+
				'<th>Type</th>'+
				'</tr>'+
				'</thead>'+
				'</table>');
	
	///gọi ajax lấy PI grid detail
	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: "/Chori/pi/piGridDetailGroupedByColor/Lot1",
		contentType: "application/json",
		success: function(data){
			
			if(data.piGridDetail.length==0){
				alert("Table have no data.");
			}
			
			$.each(data.piGridDetail,function(key,value){
				$('<tr>').append(
						$('<td>').html(value.totalPcs+"/"+value.totalFaPcs),
						$('<td>').html(value.colorName+'<br>'+value.colorPcs+"/"+value.colorFaPcs),
						$('<td>').html(value.garmentstyle+'<br>'+value.garmentstylePcs+"/"+value.garmentstyleFaPcs),
						$('<td>').html(((value.imgUrl1==null)||(value.imgUrl1.trim()==''))?'':
									'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl1+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl1+'" data-id="choriGarmentStyleImage/'+value.imgUrl1+'" /></a>'),
						$('<td>').html(value.typecode+'<br>'+value.typePcs+"/"+value.typeFaPcs),
						$('<td>').html(value.sizename),
						$('<td>').html(value.pcs),
						$('<td>').html(value.faValue)
				).appendTo('#listPiGridDetail');	
				
//				preAccessoryName = value.accessoryName;
			});
			
			$('#listPiGridDetail').DataTable( {
				"sPaginationType": "full_numbers",
				paging: true,
		        rowsGroup: [0,1,2,3,4,5]
		    } );
		},
		error: function(){
//			alert("Can not get data!");
//			callMessageDialog("Message", "Something wrong with pi grid detail! 147");
			callMessageDialog("Message", "Not Assign Pi Grid Yet !");
		}
	});
	///end gọi ajax lấy PI grid detail	
	
	$(".fancybox").fancybox();
});