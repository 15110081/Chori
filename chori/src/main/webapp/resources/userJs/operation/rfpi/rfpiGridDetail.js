$(document).ready(function(){
		function loadRfpiGridDetail(lotnumber,version){
	//xóa tạo lại table
	$("#RfpiGridDetailTableCover").html('');
	$("#RfpiGridDetailTableCover").append('<table class="table table-striped table-bordered display responsive"'+
				'id="listRfpiGridDetail">'+
				'<thead>'+
				'<tr>'+
				'<th rowspan="2">Total Pcs</th>'+
				'<th rowspan="2">Color</th>'+
				'<th colspan="3">Garment Style</th>'+
				'<th rowspan="2">Size</th>'+
				'<th rowspan="2">FPI</th>'+
				'<th rowspan="2">RFPI</th>'+
				'</tr>'+
				'<tr>'+
				'<th>Name</th>'+
				'<th>Image</th>'+
				'<th>Type</th>'+
				'</tr>'+
				'</thead>'+
				'</table>');
	
	///gọi ajax lấy RFPI grid detail
	$.ajax({
		dataType: "json",
		type: 'GET',
		data: {},
		url: "/Chori/rfpi/rfpiGridDetail/"+ lotnumber +"/"+ version,
		contentType: "application/json",
		success: function(data){
			
			if(data.rfpiGridDetail.length==0){
				//alert("Table have no data.");
			}
			
			$.each(data.rfpiGridDetail,function(key,value){
				$('<tr>').append(
						$('<td>').html("<span "+((value.totalRfpiPcs<=value.totalFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.totalFpiPcs+"/"+value.totalRfpiPcs+"/"+(value.totalRfpiPcs-value.totalFpiPcs)+"<span>"),
						$('<td>').html("<span "+((value.colorRfpiPcs<=value.colorFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.colorName+'<br>'+value.colorFpiPcs+"/"+value.colorRfpiPcs+"/"+(value.colorRfpiPcs-value.colorFpiPcs)+"<span>"),
						$('<td>').html("<span "+((value.garmentstyleRfpiPcs<=value.garmentstyleFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.garmentstyle+'<br>'+value.garmentstyleFpiPcs+"/"+value.garmentstyleRfpiPcs+"/"+(value.garmentstyleRfpiPcs-value.garmentstyleFpiPcs)+"<span>"),
						$('<td>').html(((value.imgUrl==null)||(value.imgUrl.trim()==''))?'':
									'<a class="fancybox" rel="group"  href="choriGarmentStyleImage/'+value.imgUrl+'"><img class="btnImg" height="100" width="100" src="choriGarmentStyleImage/'+value.imgUrl+'" data-id="choriGarmentStyleImage/'+value.imgUrl+'" /></a>'),
						$('<td>').html("<span "+((value.typeRfpiPcs<=value.typeFpiPcs)?'style="color: blue;"':'style="color: red;"')+" >"+value.typecode+'<br>'+value.typeFpiPcs+"/"+value.typeRfpiPcs+"/"+(value.typeRfpiPcs-value.typeFpiPcs)+"<span>"),
						$('<td>').html(value.sizename),
						$('<td>').html(value.fpiPcs),
						$('<td>').html(value.rfpiPcs)
				).appendTo('#listRfpiGridDetail');	
				
				preAccessoryName = value.accessoryName;
			});
			
			$('#listRfpiGridDetail').DataTable( {
				"sPaginationType": "full_numbers",
				paging: true,
		        rowsGroup: [0,1,2,3,4,5]
		    } );
		},
		error: function(){
//			alert("Can not get data!");
//			callMessageDialog("Message", "Something wrong with pi grid detail! 147");
//			callMessageDialog("Message", "Not Assign Pi Grid Yet !");
		}
	});
	///end gọi ajax lấy RFPI grid detail	
	
	/***
	 * ------------------------------------------------ End phần load rfpi grid detail
	 */	
		}
		loadRfpiGridDetail("Lot2",1);
	$(".fancybox").fancybox();
});