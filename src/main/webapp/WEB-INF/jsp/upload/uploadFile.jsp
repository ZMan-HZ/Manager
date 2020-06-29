<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="scripts/jquery-1.9.1.js"></script>
<script language="JavaScript" src="scripts/jquery-ui.js"></script>
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
 	vertical-align: middle; 
	background:#d2eaf1;
 }
 table th { 
   	color: white;
    text-align: center; 
 	border: 1px solid #3ca4c3;
	border-collapse:collapse;
	background:#30839c;
	padding: 6px 8px;}
.pageTitle {
	font-size: 15px;
}
</style>
<script type="text/javascript">
function submitData() {
	if(document.getElementById("namefile").value != "") {
   		document.getElementById("FileUploadForm").submit();
	}
}
</script>
<script type="text/javascript">
function updateDescription(attachmentId,projectID) {
	var desc = document.getElementById("fileDesc_"+attachmentId);
	var fileDesc = desc.value;
	window.location.href="${pageContext.request.contextPath}/updateFileDescription.action?id="+attachmentId+"&fileDesc="+fileDesc+"&projectID="+projectID;
}
</script>
</head>
<body>
<a href="${pageContext.request.contextPath}/updateStatus.action?id=${statusBeanCustom.id} ">Status Update Page</a> &nbsp;&nbsp;&nbsp;&nbsp;
<%-- <a href="${pageContext.request.contextPath}/uploadFile.action?id=${statusBeanCustom.id } ">Update Attachment Page</a> &nbsp;&nbsp;&nbsp;&nbsp; --%>
<a href="${pageContext.request.contextPath}/updateComment.action?isOpenFirst=true&projectID=${statusBeanCustom.id} ">Update Comment</a>
<br>
<br>
<form action="uploadFileSubmit.action" method="POST" enctype="multipart/form-data" id="FileUploadForm"> 
<table>
<tr>
<th>ID</th>
<td><input type="text" name="projectID"  value="${statusBeanCustom.id}" readonly="true"/></td>
</tr>
<tr>
<th>Project</th>
<td><input type="text" name="projectName"  value="${statusBeanCustom.projectName}" readonly="true"/>     </td>
</tr>
<tr>
<th>Item Desc</th>
<td><input type="hidden" name="itemDesc" value="${statusBeanCustom.itemDesc}" readonly="true"/>${statusBeanCustom.itemDesc}</td>
</tr>
<tr>
<th>File</th>
<td><input type="file" name="file" id="namefile" size=90></td>
</tr>
<tr>
<th>Description</th>
<td><input type="text" name="description"  value="${statusBeanCustom.description}" size=90></td>
</tr>
<tr>
<th>Delete Old</th>
<td><input type="checkbox" name="deleteOld"></td>
</tr>
<tr>
<th>&nbsp;</th>
<td><input type="button" value="Submit" onclick="submitData()"></td>
</tr>
</table>
</form>
<br>
        
<form action="deleteFileSubmit.action" method="POST"> 
<input type="submit" value="Delete">&nbsp;&nbsp;&nbsp;&nbsp;
<label> <font color="blue">Total: ${Total}</font></label>
<input type="hidden" name="projectID" value="${statusBeanCustom.id}">
<br>
<table>
<tr>
<th>&nbsp;</th>
<th>File Name</th>
<th>File Description</th>
<th>Upload Time</th>
<th>Upload By</th>
<th></th>
</tr>
<c:forEach  items="${projectAttachmentBeanList}" var="attachement" varStatus="order">
<tr>
<td><input type="checkbox" name="selectedId" value="${attachement.id }"></td>
<td><a href="${pageContext.request.contextPath }/downloadFile.action?id=${attachement.id}">${attachement.fileName}</a></td>
<td>
<textarea id="fileDesc_${attachement.id}" rows="2" style="width:100%"  cols="45">${attachement.fileDesc}</textarea>
<%-- <input type="text" id="file_${attachement.fileName }" size="30"  value="${attachement.fileDesc }" /> --%></td>
<td nowrap><fmt:formatDate value="${attachement.createTime }" pattern="yyyy-MM-dd"/></td>
<td>${attachement.userNm }</td>
<%-- <td><input type="button" name="update" value="Update" onclick="javascript:updateFileDescription('${attachement.id }')"/></td> --%>
<td><input type="button" name="update" value="Update" onclick="javascript:updateDescription('${attachement.id}','${statusBeanCustom.id} ')" /></td>
</tr>
</c:forEach>
</table>
</form>
</body>
</html>

<script type="text/javascript">
var isOpenFirst ='${isOpenFirst}';
if(isOpenFirst =='no') {
	window.alert("Successfully Completed!");
}
</script>