<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<a href="${pageContext.request.contextPath}/updateStatus.action?id=${statusBeanCustom.id }">Status Update Page</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/uploadFile.action?id=${statusBeanCustom.id}">Update Attachment Page</a> &nbsp;&nbsp;&nbsp;&nbsp;
<br>
<br>
<form action="updateCommentSubmit.action" method="post">
<table>
<tr><th>ID</th><td> <input type="hidden" name="id" value="${statusBeanCustom.id}"/> ${statusBeanCustom.id}</td></tr>
<tr><th>Project</th><td><input type="hidden" name="projectName" value="${statusBeanCustom.projectName}"/>${statusBeanCustom.projectName}</td></tr>
<tr><th>Item Desc</th><td>${statusBeanCustom.itemDesc}</td></tr>
<tr><th>DB scripts</th><td><textarea name="dbScripts" rows="10" style="width:100%"  cols="140">${statusBeanCustom.dbScripts}</textarea></td></tr>
<tr><th>History/Comment</th><td><textarea name="comments" rows="8" style="width:100%"  cols="140">${statusBeanCustom.comments}</textarea></td></tr>
<tr><th>Remained Questions</th><td><textarea name="remainedQuestions" rows="16" style="width:100%"  cols="140">${statusBeanCustom.remainedQuestions}</textarea></td></tr>
<tr><th>All Reviewed</th><td><input type="checkbox" name="reviewedTag" ></td></tr>
<tr><th>Update by</th><td><input type="hidden" name="updateBy" value="${UpdateBy}" readonly="true"/><input type="text" name="" value="${UpdateByName}" readonly="true"/></td></tr>
<tr><th>Update Time:</th><td><label style="color:red"><jsp:useBean id="currentTime" class="java.util.Date"><fmt:formatDate value="${currentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
<input type="hidden" name="updateTime" value="<fmt:formatDate value="${currentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly="true"/></jsp:useBean></label></td></tr>
<tr><th></th><td><input type="submit" name="Save" value="Update"/>
</table>
</form>
</body>
</html>
<script type="text/javascript">
var isOpenFirst ='${isOpenFirst}';
if(isOpenFirst =='no') {
	window.alert("Update Completed!");
}
</script>