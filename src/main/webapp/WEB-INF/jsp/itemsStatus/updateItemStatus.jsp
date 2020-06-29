<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- 2018-04-09 Added by E633229 Begin-->
<link rel="stylesheet" href="css/jquery-ui.css" type="text/css">
<script language="JavaScript" src="scripts/jquery-1.9.1.js"></script>
<script language="JavaScript" src="scripts/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css">
<script type ="text/javascript">
  $(function() {
  $( "#prodDate" ).datepicker({dateFormat:"yy-mm-dd"});
  $( "#startDate" ).datepicker({dateFormat:"yy-mm-dd"});
  $( "#closeTime" ).datepicker({dateFormat:"yy-mm-dd"});
  });
</script>
 <!-- 2018-04-09 Added by E633229 End --> 

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
<%-- <a href="${pageContext.request.contextPath}/updateStatus.action?isOpenFirst=true&id=${statusBeanCustom.id}">Status Update Page</a> &nbsp;&nbsp;&nbsp;&nbsp; --%>
<a href="${pageContext.request.contextPath}/uploadFile.action?projectID=${statusBeanCustom.id}">Update Attachment Page</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/updateComment.action?isOpenFirst=true&projectID=${statusBeanCustom.id}">Update Comment</a>
<br>
<br>
<form action="updateStatusSubmit.action" method="post">
<table>
<tr><th>ID</td><td><input type="text" name="id"  size="4" value="${statusBeanCustom.id}" readonly="true"/></td></tr>
<tr><th>Project</td><td>
 <select name="projectName"> 
                 <c:forEach items="${projectName}" var="names">
                 <c:choose>
                 <c:when test="${statusBeanCustom.projectName == names.projectName}">
                 <option value="${names.projectName }" selected>${names.projectName}</option>
                 </c:when>
                 <c:otherwise>
                 <option value="${names.projectName }" >${names.projectName}</option>
                 </c:otherwise>
                 </c:choose>
                 </c:forEach>
                 </select>
</td></tr>
<tr><th>Item Desc</th>
<td><textarea name="itemDesc" rows="3" style="width:100%"  cols="70">${statusBeanCustom.itemDesc} </textarea></td></tr>
<tr><th>Leader</th><td><input type="text" name="leader" value="${statusBeanCustom.leader}"  size="50"/></td></tr>
<tr><th>Owner</th><td><input type="text" name="owner" value="${statusBeanCustom.owner}" size="50"/></td></tr>
<tr><th>Status Infor</th><td><textarea name="status" rows="12" style="width:100%"  cols="70">${statusBeanCustom.status}</textarea></td></tr>
<tr><th>Atrack Activity</th><td><input type="text" name="extraComment" size="20" value="${statusBeanCustom.extraComment}"/></td></tr>
<tr><th>Prod Date</th><td><input type="text" id="prodDate" name="prodDate" value="<fmt:formatDate value="${statusBeanCustom.prodDate}" pattern="yyyy-MM-dd"/>"  placeholder= "Please Select a Date"/>(EST)</td></tr>
<tr><th>Start date</th><td><input type="text" id="startDate" name="createTime" value="<fmt:formatDate value="${statusBeanCustom.createTime}" pattern="yyyy-MM-dd"/>" placeholder= "Please Select a Date"/></td></tr>
<tr><th>Close Time</th><td>
<c:if test="${statusBeanCustom.closeTime != null}">
<input type="text" id="closeTime" name="closeTime" value="<fmt:formatDate value="${statusBeanCustom.closeTime}" pattern="yyyy-MM-dd"/>"/>
</c:if>
<c:if test="${statusBeanCustom.closeTime == null}">
<input type="text" id="closeTime" name="closeTime" placeholder= "Please Select a Date"/>
</c:if>
</td></tr>
<tr><th>Close</th><td><input type="text" name="isClosed" value="${statusBeanCustom.isClosed}" /></td></tr>
<tr><th>Delete</th><td><input type="text" name="isDelete" value="${statusBeanCustom.isDelete}" /></td></tr>
<tr><th>Created by</th><td><input type="text" name="createBy"  value="${statusBeanCustom.createBy}" readonly="true"/></td></tr>
<tr><th>Update by</th><td><input type="hidden" name="updateBy" value="${UpdateBy} " readonly="true"/><input type="text" name="" value="${UpdateByName}" readonly="true"/></td></tr>
<tr><th>Status</th><td>
<select name="jobStatus"> 
                 <c:forEach items="${statusList}" var="status">
                 <c:choose>
                 <c:when test="${statusBeanCustom.jobStatus == status.projectStatus}">
                 <option value="${status.projectStatus}" selected>${status.projectStatus}</option>
                 </c:when>
                 <c:otherwise>
                 <option value="${status.projectStatus}" >${status.projectStatus}</option>
                 </c:otherwise>
                 </c:choose>
                 </c:forEach>
                 </select>
</td></tr>
<tr><th>Percentage</th><td><input type="text" name="statusPercent" value="${statusBeanCustom.statusPercent}"/></td></tr>
<tr><th>Update Time:</th>
<td><label style="color:red">
<jsp:useBean id="currentTime" class="java.util.Date"><fmt:formatDate value="${currentTime}" pattern="yyyy-MM-dd HH:mm:ss"/></jsp:useBean></label></td></tr>
<tr><th></th><td><input type="submit" name="Submit" value="Submit"/></td></tr>
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