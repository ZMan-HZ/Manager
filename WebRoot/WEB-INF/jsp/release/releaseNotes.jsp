<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<style type="text/css">
body {
    font-size: 13px; padding:10px; background-color:white; color:rgb(32, 74, 132); font-family:Calibri
}
 table {
	border-right: 1px solid white;
	border-bottom: 1px solid white;
	border-collapse:collapse;
 }
 table td {
	border-left: 1px solid white;
	border-top: 1px solid white;
	padding: 6px 8px; 
	text-align: left; 
	vertical-align: top;
	background-color:rgb(217, 217, 217);
 }
 table th { 
 	border-right: 1px solid white;
 	border-left: 1px solid white;
	border-top: 1px solid white;
	border-bottom: 1px solid white;
	border-collapse:collapse;
	background-color:rgb(196, 188, 150);
	padding: 6px 8px;}
 
 table tr.status {background:#30839c; font-weight:bold; color:white;}
 
 
 tr.secondLine {background:white;}
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
    padding-top: 20px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
    margin: auto;
    padding: 10px;
    border: 1px solid #888;
    width: 85%;
    height: 93%;
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

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>
</head>
<body>
<div><a href="javascript:history.go(-1);">Return</a></div>

<c:choose>
<c:when test="${itemAbleToRelease}">
<table>
<tr>
<th>Item</th>
<c:if test="${!flag}"><th>Change Type</th></c:if>
<th>Developer</th>
<th>Code <br>Reviewer</th>
<th>DEV <br>Tester</th>
<c:if test="${!flag}"><th>DEV <br>TestCase</th></c:if>
<th>DEV <br>Test Reviewer</th>
<c:if test="${!flag}"><th>Impact</th></c:if>
<c:if test="${flag}">
<th>SQA <br>Tester</th>
<th>SQA <br>TestCase Reviewer</th>
<th>SQA <br>Test Reviewer</th>
</c:if>
</tr>

 <c:forEach items="${statusBeanCutomList}" var="statusBeanCutom" varStatus="order">
<tr>
<td>${statusBeanCutom.itemDesc}</td>
<c:if test="${!flag}"><td><font color="blue">${statusBeanCutom.changeType}</font></td> </c:if>
<td>${statusBeanCutom.owner}</td>
<td>&nbsp;</td>
<td>${statusBeanCutom.owner}</td>
<c:if test="${!flag}"><td><c:forEach items="${attachmentBeanCustomList}" var="attachment"><c:if test="${attachment.projectId == statusBeanCutom.id}"><a href="${pageContext.request.contextPath }/downloadFile.action?id=${attachment.id}">${attachment.fileName}</a>
<br/></c:if></c:forEach></td></c:if>
<td>&nbsp;</td>
<c:if test="${!flag}"><td>&nbsp;</td></c:if>
<c:if test="${flag}">
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
</c:if>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<font size = '4' color="red"><b>
<c:if test="${!flag}"> UAT </c:if>
<c:if test="${flag}"> PROD </c:if>
</b></font><font size = '4' > Release Notes:<br/>
There's at least one item Status which SELECTED is not <font size = '4' color="red"><b>
<c:if test="${!flag}"> 'DEV_COM' or 'UAT'</c:if>
<c:if test="${flag}"> 'PASS'</c:if>
</b></font></font>
<br/><br/>
<font size = '4' > Please Update Status First</font>;
</c:otherwise>
</c:choose>
</body>
</html>





