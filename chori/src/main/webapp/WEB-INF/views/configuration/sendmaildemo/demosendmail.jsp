<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Mail</title>
</head>
<body>
	 <div class="container">
    <center>
      <h1>Spring MVC - Send e-mail with attachments</h1>
    </center>
    <form method="post" action="sendmail" enctype="multipart/form-data">

      <table class="table" width="100%">

        <tr>


          <td><input type="text" name="mailTo" placeholder="To" /></td>
          <td></td>
        </tr>


        <tr>

          <td><input type="text" name="subject" placeholder="subject" /></td>
          <td></td>
        </tr>

        <tr>

          <td><textarea cols="50" rows="10" name="message" placeholder="message"></textarea></td>
          <td><input type="file" name="attachFile123" /></td>
        </tr>

        <tr>
          <td><input type="submit" class="btn btn-info" value="Send" /></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>