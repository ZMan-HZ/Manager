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
	text-align: center; 
	vertical-align: middle;
 }
 table th { 
 	border-right: 1px solid #3ca4c3;
	border-bottom: 1px solid #3ca4c3;
	border-collapse:collapse;
	padding: 6px 8px;}
 
 table tr.status {background:#30839c; font-weight:bold; color:white;}
 
 
 tr {background:white;}
 tr.firstLine {background:#d2eaf1;}
a {
	text-decoration:none
} 
a:link {
    color: blue;
}

/* visited link */
a:visited {
    color: blue;
}

/* mouse over link */
 a:hover {
    color: blue;
}

/* selected link */
a:active {
     color: blue;
}

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 0px; /* Location of the box */
	left: 0;
	top: 0;
	width: 50%; 
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
/* 	background-color: rgb(0, 0, 0); /* Fallback color */ 
/* 	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */ 
}
/* Modal Content */
.modal-content {
	margin: auto;
	padding: 10px;
	border: 1px solid #888;
	width:96%;
	height: 96%;
	border-radius: 10px;
	background: #d2eaf1;
	background: -moz-linear-gradient(#fff, #999);
	background: -webkit-linear-gradient(#fff, #999);
	background: -o-linear-gradient(#fff, #999);
}
/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}
.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>
<div>
<form action="allFiles.action" method="POST" id="filterForm"> 
ProjectName: 
<select name="projectName"> 
                 <option value="">-ALL-</option>
                 <c:forEach items="${projectName}" var="names">
                 <c:choose>
                 <c:when test="${projectAttachmentVo.projectName == names.projectName}">
                 <option value="${names.projectName}" selected>${names.projectName}</option>
                 </c:when>
                 <c:otherwise>
                 <option value="${names.projectName }">${names.projectName}</option>
                 </c:otherwise>
                 </c:choose>
                 </c:forEach>
</select>
File Name: <input type="text" name="fileName" size="20" value="${projectAttachmentVo.fileName}"/>
Item Desc: <input type="text" name="itemDesc" size="20" value="${projectAttachmentVo.itemDesc}"/>
Order By: <select name="orderBy">
<option value=""></option>
<c:choose>
<c:when test="${oderByItemDesc == projectAttachmentVo.orderBy}">
<option value="b.Item_Desc" selected>Item Desc</option>
</c:when>
<c:otherwise>
<option value="b.Item_Desc">Item Desc</option>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${oderByUploadTime == projectAttachmentVo.orderBy}">
<option value="a.UPLOAD_TIME DESC" selected>Upload Time</option>
</c:when>
<c:otherwise>
<option value="a.UPLOAD_TIME DESC">Upload Time</option>
</c:otherwise>
</c:choose>
</select>

<input type="submit" name="filter" value="filter" />
</form>
</div><br/>
<label><font color="blue">Total: ${Total}</font></label>
<br/><br/>
<table>
<tr class="status">
<th>Project</th>
<th>File Name</th>
<th>File Desc</th>
<th>Item Desc</th>
<th>Upload Time</th>
<th>Upload User</th>
<th>Modify</th>
</tr>
<c:forEach items="${projectAttachmentList}" var="attachment" varStatus="index">
<tr>
<td>${attachment.projectName}</td>
<td style="text-align:left;vertical-align:top"><a href="downloadFile.action?id=${attachment.id}">${attachment.fileName}</a></td>
<td style="text-align:left;vertical-align:top">${attachment.fileDesc}</td>
<td style="text-align:left;vertical-align:top">${attachment.projectItemDesc}</td>
<td nowrap><fmt:formatDate value="${attachment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
<td>${attachment.userNm}</td>
<td><a href="javascript:updateFile('${attachment.projectId}')">[Modify]</a></td>
</tr>
</c:forEach>
</table>
<div id="myModal" class="modal">
  <div class="modal-content">
    <span class="close" id="close">&times;</span>
    <iframe id="testFrame" frameborder="0"></iframe>
  </div>
<script type="text/javascript">
var modal = document.getElementById('myModal');
var closeButton = document.getElementById('close');
var testFrame = document.getElementById('testFrame');

closeButton.onclick = function() {
    modal.style.display = "none";
    testFrame.src="";
    document.getElementById("filterForm").submit();
}

function updateFile(statusId) {
	modal.style.display = "block";
	testFrame.src="${pageContext.request.contextPath}/uploadFile.action?projectID=" + statusId;
	testFrame.style.width="100%"; 
	testFrame.style.height="95%";
	
}
</script>
</div>
</body>
</html>