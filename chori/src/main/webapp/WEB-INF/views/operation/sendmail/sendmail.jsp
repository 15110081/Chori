<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="${pageContext.request.contextPath}/resources/userJs/sendemail/tinymce.min.js"></script>
  
 <script>
 //initialize tinyMCE for textarea
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

  //function to get current date
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
		 month[0] = "Jan";
		 month[1] = "Feb";
		 month[2] = "Mar";
		 month[3] = "Apr";
		 month[4] = "May";
		 month[5] = "Jun";
		 month[6] = "Jul";
		 month[7] = "Aug";
		 month[8] = "Sep";
		 month[9] = "Oct";
		 month[10] = "Nov";
		 month[11] = "Dec";
		 
		 today = month[mm]+'/'+dd+'/'+yyyy;
		 return today;
	 }

  	//print number with commas as thousands separators
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	 //when click get email form
	function GetEmailForm_Click(){
		//reset div cover 
		$("#tblAccessoryOrderListCover").html("");
		tinyMCE.activeEditor.setContent('');
	/*	var tblAccessoryOrderList = '<table style="width: 575px; border-collapse: collapse;"'+
		'id="tblAccessoryOrderList">'+
			'<thead>'+
				'<tr>'+
				'<th style="border: 1px solid black; width: 86px;">ITEM</th>'+
				'<th style="border: 1px solid black; width: 91px;">DIMENSION</th>'+
				'<th style="border: 1px solid black; width: 87px;">QTY</th>'+
				'<th style="border: 1px solid black; width: 90.1914px;">UNIT PRICE</th>'+
				'<th style="border: 1px solid black; width: 83.8086px;">UNIT</th>'+
				'<th style="border: 1px solid black; width: 32px;">AMOUNT</th>'+
				'<th style="border: 1px solid black; width: 82px;">DEL. Date</th>'+					
				'</tr>'+
			'</thead>'+
		'</table>'; */

		//data get by current form
		var orderSheetNo = "ASLK V15-15A";
		var accessoryUnitPrice = 350;
		var accessoryQty = 57706;
		var accessoryCode = "BTNR";
		var delvDate = getCurrentDate();

		var accSuplierName = "TAN LOC COMPANY";
		var factoryCode = "FAC0002";
		var currentDate = getCurrentDate();

		//create ordersheetno table
		var tblOrderSheetNo = '<table style="width: 575px; border-collapse: collapse;">' +
		'<tbody><tr>'+	
		'<td style="border: 1px solid black; width: 143px;">Order sheet nos .</td>'+
		'<td style="border: 1px solid black; width: 200.742px; text-align: center;">'+ orderSheetNo + '</td>' +
		'<td style="border: 1px solid black; width: 228.258px; text-align: center;">ALSHIAKA V15-15/2016</td>' +
		'</tr></tbody></table>';

		//get detail information of an accessory
		$.ajax({
			dataType: "json",
			type: 'GET',
			data: {},
			url: "/Chori/accessory/detail/" + accessoryCode,
			contentType: "application/json",
			success: function(data){
				//get detail info						
				var accessoryName = data.accessory.name;
				var accessoryDimension = data.accessory.dimension;
				var accessoryUnitCode = data.accessory.unitcode;

				//create accessory table with detail information
				$("#tblAccessoryOrderListCover").append('<table style="width: 575px; border-collapse: collapse;"'+
						'id="tblAccessoryOrderList">'+
						'<thead>'+
							'<tr>'+
							'<th style="border: 1px solid black; width: 75px;">ITEM</th>'+
							'<th style="border: 1px solid black; width: 73px;">DIMENSION</th>'+
							'<th style="border: 1px solid black; width: 80px;">QTY</th>'+
							'<th style="border: 1px solid black; width: 68px;">UNIT PRICE</th>'+
							'<th style="border: 1px solid black; width: 72px;">UNIT</th>'+
							'<th style="border: 1px solid black; width: 92px;">AMOUNT</th>'+
							'<th style="border: 1px solid black; width: 100px;">DEL. Date</th>'+					
							'</tr>'+
						'</thead>'+
					'</table>');
				$('<tr>').append(
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(accessoryName),							
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(accessoryDimension),
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(numberWithCommas(accessoryQty)),
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(accessoryUnitPrice),
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(accessoryUnitCode),							
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(numberWithCommas(accessoryQty*accessoryUnitPrice)),						
						$('<td style="border: 1px solid black; text-align: center; font-weight:bold;">').text(getCurrentDate())			
				).appendTo('#tblAccessoryOrderList');

				//get html of tblAccessoryOrderListCover (it include tblAccessoryOrderList)
				var tblAccessoryOrderListString = $("#tblAccessoryOrderListCover").html();

				//load factory name + tel + fax by factory code
				$.ajax({
					dataType: "json",
					type: 'GET',
					data: {},
					url: getAbsolutePath() +  "factory/detail/"+ factoryCode,
					contentType: "application/json",
					success: function(data){
						//var factoryName = "DAI VIET GARMENT JOINT STOCK CO";
						//var factoryAddress = "62 Tan Thanh St, Tan Thanh ward";
						//var factoryTel = "3 8496016";
						//var factoryFax = "38429860";
						var factoryName = data.factory.longname;
						var factoryAddress = data.factory.address;
						var factoryTel = data.factory.tel;
						var factoryFax = data.factory.fax;

						//binding to textarea
						tinyMCE.execCommand('mceInsertContent',false,
								  '<p style="text-align: left; font-size: 16px;">To: <strong>'+ accSuplierName 
								  +'</strong><span style="font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; '
								  + ' Date: '+ currentDate +'</span></p>'
								  + '<div><strong>ATT: </strong></div><p style="margin-top: 0px;"><strong>From: </strong></p>'
								  + tblOrderSheetNo
								  + '<p><br />&nbsp;We here by confirmed to place following orders to your company.</p>'
								  + tblAccessoryOrderListString 
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
						alert("Lỗi lấy by id")
					}
				});
			},
			error: function(){
			}
		});
	};
</script>

<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left" >Send Mail</div>
	</div>
		
	<form method="post" action="sendmailForOrderExternalAccessory" enctype="multipart/form-data">
		<table style="border: none; margin:20px 0px 0px 40px">
			<tr>
				<td>Email To:</td>
				<td><input type="text" name="mailTo" placeholder="To" /></td>
			</tr>
			<tr>
				<td>CC:</td>
				<td><input type="text" name="CC" placeholder="CC" /></td>
			</tr>
			<tr>
				<td>Subject:</td>
				<td><input type="text" name="subject" placeholder="subject" /></td>
			</tr>
			<tr>
				<td></td>
            <td>
           		<input style="margin: 10px 0px 20px 0px" class="btn btn-info" type="button" id="btnGetEmailForm" value="Get Email Form"  onclick="GetEmailForm_Click()">   		
			</td>
			</tr>
			<tr>
				<td style="vertical-align: sub;">Message:</td>
            <td>
           		<textarea name="message" id="message" ></textarea>      		
			</td>
			</tr>
			<tr>
				<td style="margin: 10px 0px 20px 0px" >Attach file:</td>
				<td><input  style="margin: 10px 0px 20px 0px" type="file" name="attachFile" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="Send" /></td>
				<td><input type="button" class="btn btn-info" value="Cancel" id="btnCancelSendMail" /></td>
			</tr>
		</table>
	</form>

	<div id="tblAccessoryOrderListCover" style="display:none">
	</div>
	
</div>
		