<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/jquery-ui.css" />
<script language="JavaScript" src="scripts/jquery-1.9.1.js"></script>
<script language="JavaScript" src="scripts/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style.css"  />
<script type ="text/javascript">
  $(function() {
  $( "#prodDate" ).datepicker({dateFormat:"yy-mm-dd"});
  });
</script>

<title>Insert title here</title>
<style type="text/css">
body {
    font-size: 12px; padding:10px; background-color:rgba(0,0,0,0.2)
}
table {
	border-right: 1px solid #3ca4c3;
	border-bottom: 1px solid #3ca4c3;
	border-collapse:collapse;
 }
 table td {
	border-left: 1px solid #3ca4c3;
	border-top: 1px solid #3ca4c3;
	padding: 6px 8px; 
	text-align: left; 
	vertical-align: top;
	background:#d2eaf1;
 }
 table th { 
   	color: white;
   	text-align: left;
 	border: 1px solid #3ca4c3;
	border-collapse:collapse;
	background:#30839c;
	padding: 6px 8px;}
 .pageTitle {
	font-size: 15px;
}
</style>
</head>
<body>
<br>
<form action="updateProjectCommentsSubmit.action" method="post">
<table>
<tr><th>Project</th>
<td><input type="text" name="projectName" value="${projectCommentVo.projectName}" readonly="true"/></td></tr>
<tr><th>Prod Date</th>
<td>
<input type="text" id="prodDate" name="prodDate" value="<fmt:formatDate value="${projectCommentBeanCustom.prodDate}" pattern="yyyy-MM-dd"/>"  placeholder= "Please Select a Date">
<font color="red">Only <b>Admin</b> Can Update ProdDate</font>
<input type="hidden" name="prodDateOrigin" value="<fmt:formatDate value="${projectCommentBeanCustom.prodDate}" pattern="yyyy-MM-dd" />"/></td>
</tr>
<tr><th>Project Comments</th>
<td style="text-align:left;vertical-align:middle">
<textarea name="projectComments" rows="30" style="width:100%"  cols="140">${projectCommentBeanCustom.projectComments}</textarea>
</td></tr>
<tr><th></th><td><input type="submit" name="Save" value="Save"/> </td></tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
var isOpenFirst ='${isOpenFirst}';
if(isOpenFirst =='no') {
// 	alert(window.parent.location);
	window.parent.document.getElementById("prodDate").value ="<fmt:formatDate value="${projectCommentBeanCustom.prodDate}" pattern="yyyy-MM-dd" />";
	window.alert("Update Completed!");
}
</script>