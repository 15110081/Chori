<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="${pageContext.request.contextPath}/resources/userJs/sendemail/tinymce.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/userJs/operation/orderinternalaccessory/sendMailOrderinternalaccessory.js"></script>
<script >

</script>
 
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left" >Send Mail</div>
	</div>
		
	<form method="post" action="sendmailForOrderInternalAccessory" enctype="multipart/form-data">
		<table style="border: none; margin:20px 0px 0px 40px">
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblEmailTo" />:</td>
				<td><input type="text" name="mailTo" placeholder="To" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblCC" />:</td>
				<td><input type="text" name="CC" placeholder="CC" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblSubject" />:</td>
				<td><input type="text" name="subject" placeholder="subject" /></td>
			</tr>
			<!-- Order sheet no part -->
			<tr style="display: none;">
				<td><input type="text" name="orderSheetNo" placeholder="orderSheetNo" id="orderSheetNo"/></td>
			</tr>
			<!-- end Order sheet no part -->
			<tr>
				<td></td>
            <td>
           		<input style="margin: 10px 0px 20px 0px" class="btn btn-info" type="button" id="btnGetEmail" value="<spring:message code='lbl.orderinternalaccessory.dialogsendmail.btnGetEmailForm' />">   		
			</td>
			</tr>
			<tr>
				<td style="vertical-align: sub;"><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblMessage" />:</td>
            <td>
           		<textarea name="message" id="message" ></textarea>      		
			</td>
			</tr>
			<tr>
				<td style="margin: 10px 0px 20px 0px" ><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblAttachFile" />:</td>
				<td><input  style="margin: 10px 0px 20px 0px" type="file" name="attachFile" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="<spring:message code='lbl.orderinternalaccessory.dialogsendmail.btnSend' />" /></td>
				<td><input type="button" class="btn btn-info" value="<spring:message code='lbl.orderinternalaccessory.dialogsendmail.btnCancel' />" id="btnCancelSendMail" /></td>
			</tr>
		</table>
	</form>

	<div id="tblGeneralAccessoryOrderListCover" style="display:none">
	</div>
	
</div>