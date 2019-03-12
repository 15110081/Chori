$(document).ready(function(){
	tinymce.init({
		  selector: 'textarea',
		  height: 500,
		  theme: 'modern',
		  plugins: [
		    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
		    'searchreplace wordcount visualblocks visualchars code fullscreen',
		    'insertdatetime media nonbreaking save table contextmenu directionality',
		    'emoticons template paste textcolor colorpicker textpattern imagetools codesample'
		  ],
		  toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
		  toolbar2: 'print preview media | forecolor backcolor emoticons | codesample',
		  image_advtab: true,
		  templates: [
		    { title: 'Test template 1', content: 'Test 1' },
		    { title: 'Test template 2', content: 'Test 2' }
		  ],
		  content_css: [
		    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
		    '//www.tinymce.com/css/codepen.min.css'
		  ]
		 });
	
	function getCurrentDate(){
		 var today = new Date();
		 var dd = today.getDate();
		 var mm = today.getMonth()+1; //January is 0!
		 var yyyy = today.getFullYear();
		
		 if(dd<10) {
		     dd='0'+dd
		 } 
		
		 if(mm<10) {
		     mm='0'+mm
		 } 

		 //to get month in name
		 var month = new Array();
		 month[1] = "Jan";
		 month[2] = "Feb";
		 month[3] = "Mar";
		 month[4] = "Apr";
		 month[5] = "May";
		 month[6] = "Jun";
		 month[7] = "Jul";
		 month[8] = "Aug";
		 month[9] = "Sep";
		 month[10] = "Oct";
		 month[11] = "Nov";
		 month[12] = "Dec";
		 
		 today = month[mm]+'/'+dd+'/'+yyyy;
		 return today;
	 }
	
	//print number with commas as thousands separators
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	//hàm lấy tham số từ url
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

	//hàm lấy order sheet no từ url, gán vào input id
	function getAndSetOrderSheetNo() {
		
		var orderSheetNo = getUrlParameter('orderSheetNo');
		$("#orderSheetNo").val(orderSheetNo);
	}
	function GetEmail_Click(){
		$('#btnGetEmail').on('click', function (e) {
			var orderSheetNo = getUrlParameter('orderSheetNo');
			var currentDate = getCurrentDate();
			$("#tblGeneralAccessoryOrderListCover").html("");
			tinyMCE.activeEditor.setContent('');
		
			var tblOrderSheetNo = '<table style="width: 575px; border-collapse: collapse;">' +
			'<tbody><tr>'+	
			'<td style="border: 1px solid black; width: 143px;">Order sheet nos .</td>'+
			'<td style="border: 1px solid black; width: 200.742px; text-align: center;">'+ orderSheetNo + '</td>' +
			'</tr></tbody></table>';
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/orderinternalaccessory/detail/" + orderSheetNo,
				contentType: "application/json",
				success: function(data){
					
					$("#tblGeneralAccessoryOrderListCover").append('<table style="width: 100%; border-collapse: collapse;"'+
							'id="tblGeneralAccessoryOrderList">'+
							'<thead>'+
							'<tr>'+
								'<th style="border: 1px solid black; width: 75px;">Acessories name </th>'+
								'<th style="border: 1px solid black; width: 75px;">Code</th>'+
								'<th style="border: 1px solid black; width: 75px;">Order qty</th>'+
								'<th style="border: 1px solid black; width: 75px;">Act delv qty</th>'+
								'<th style="border: 1px solid black; width: 75px;">Unit Price</th>'+
								'<th style="border: 1px solid black; width: 75px;">Unit</th>'+
								'<th style="border: 1px solid black; width: 75px;">Price</th>'+
								'<th style="border: 1px solid black; width: 100px;">Del. Date</th>'+
						
							'</tr>'+
							'</thead>'+
						'</table>');
					if(data.listOrderInternalAccessoryDetail.length==0){

					}
					$.each(data.listOrderInternalAccessoryDetail,function(key,value){
						$('<tr>').append(
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.accessoryname),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.accessorycode),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.orderquantity==null?"":value.orderquantity),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.actualdelvquantity==null?"0":value.actualdelvquantity),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.unitprice),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.unit),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(value.price==null?"":value.price),
								$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(getCurrentDate())	
								
						).appendTo('#tblGeneralAccessoryOrderList');
					
					});
					$.ajax({
						dataType: "json",
						type: 'POST',
						data: JSON.stringify({
							accessorysuppliercode: data.currentorderinternalaccessory.accsuplierCode
						}),
						url: "/Chori/accessorysupplier/detail/",
						contentType: "application/json",
						success: function(data2){
							var	accessorySupplier = data2.accessorysupplier.longname;
							$.ajax({
								dataType: "json",
								type: 'POST',
								data:  JSON.stringify({
									factorycode: data.currentorderinternalaccessory.factoryCode
								}),
								url: "/Chori/factory/detail/",
								contentType: "application/json",
								success: function(data3){
									var factoryName = data3.factory.longname;
									var factoryAddress = data3.factory.address;
									var factoryTel = data3.factory.tel;
									var factoryFax = data3.factory.fax;
							var tblGeneralAccessoryOrderListString = $("#tblGeneralAccessoryOrderListCover").html();
							tinyMCE.execCommand('mceInsertContent',false,
									 '<p style="text-align: left; font-size: 16px;">To: <strong>'+ accessorySupplier 
									  +'</strong><span style="font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; '
									  + ' Date: '+ currentDate +'</span></p>'
									  + '<div><strong>ATT: </strong></div><p style="margin-top: 0px;"><strong>From: </strong></p>'
									  + tblOrderSheetNo
									  + '<p><br />&nbsp;We here by confirmed to place following orders to your company.</p>'
									  + tblGeneralAccessoryOrderListString 
									  + '<p><strong>TOTAL: </strong></p>'
									  + '<p><strong>The detail barcode for white color is put in two enclosed sheets</strong></p>'
									  + '<p>Deliver to: '+ factoryName +'</p>'
									  + '<p>'+ factoryAddress +'</p>'
									  + '<p style="color: blue;">Tel:'+ factoryTel +'&nbsp; &nbsp; Fax: '+ factoryFax +'</p>'
									  + '<p>Please confirm to deliver the quality in good condition without defects, goods will be returned if the quality is granted as defective and un-usable.</p>'
									  + '<p>Kindly confirm the delivery date. Thank you.</p>'
									  + '<p style="text-align: right; margin-right: 60px;">THAI CHORI CO.,LTD.</p>'
									  + '<p style="text-align: right;">GENERAL MANAGER OF TEXTILE DEPT</p>'
									  + '<p style="text-align: right; margin-top: -20px; margin-right: 50px;"><strong>HIDEYUKI HIGASHIDA</strong></p>');					
						},
						error: function(){
							alert("Error");
						}
							});
						}
					});
					
				}
			});
		});
		$('#btnCancelSendMail').on('click', function (e) {	
			window.location.href = "/Chori/listorderinternalaccessory";
			// clear all model table Accessory when cancel order internal accessory
			$.ajax({
				dataType: "json",
				type: 'GET',
				data: {},
				url: "/Chori/orderinternalaccessory/clearDataAfterCloseAddOrderInternalAccDetail",
				contentType: "application/json",
				success: function(data){
					//alert("ok");
				},
				error: function(){
					alert("Can't get data");
				}
			});
			
			//delete table inter accessory detail 
			$("#OrderInternalAccessoryDetailTableCover").html('');
		});
	}
	
	//vừa vào gọi để lấy ngay
	GetEmail_Click()
	getAndSetOrderSheetNo();
})