<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/jquery-ui.css" type="text/css">
<script language="JavaScript" src="scripts/jquery-1.9.1.js"></script>
<script language="JavaScript" src="scripts/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css">
 <script type ="text/javascript">
  $(function() {
  $( "#startDate" ).datepicker({dateFormat:"yy-mm-dd"});
  $( "#prodDate" ).datepicker({dateFormat:"yy-mm-dd"});
  });
  </script>

<title>Add New Items</title>
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
<div class="pageTitle">&gt;&gt;Add New Item Record</div>
<br/><br/>
<form action="insertNewStatusSubmit.action" method="post">
<table>
<tr><th>Project</th><td>
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
<c:choose>
<c:when test="${statusBeanCustom.itemDesc == null}">
<td><textarea name="itemDesc" size=100 rows="4" cols="70" placeholder= " Please Briefly Describe This Item"></textarea></td></tr>
</c:when>
<c:otherwise>
<td><textarea name="itemDesc" size=100 rows="4" cols="70" >${statusBeanCustom.itemDesc}</textarea></td></tr>
</c:otherwise>
</c:choose>
<tr><th>Leader</th>
<td><input type="text" name="leader"  size="50" value="${statusBeanCustom.leader}" /></td></tr>
<tr><th>Owner</th>
<c:choose>
<c:when test="${statusBeanCustom.owner == null}">
<td><input type="text" name="owner"  size="50" placeholder= " Item should have an Owner" /></td></tr>
</c:when>
<c:otherwise>
<td><input type="text" name="owner"  size="50" />${statusBeanCustom.owner}</td></tr>
</c:otherwise>
</c:choose>
<tr><th>Atrack Activity</th>
<td><input type="text" name="extraComment" size="50"/></td></tr>
<tr><th>Start Date</th><td>
<c:choose>
<c:when test="${statusBeanCustom.startDate == null}">
<input type="text" id="startDate" name="startDate"  placeholder=" Please Select Start Date" /></td></tr>
</c:when>
<c:otherwise>
<input type="text" id="startDate" name="startDate" value="<fmt:formatDate value="${statusBeanCustom.createTime}" pattern="yyyy-MM-dd"/>" /></td></tr>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${statusBeanCustom.prodDate != null}">
<tr><th>Prod Date</th><td><input type="text" id="prodDate" name="prodDateCustom" value="<fmt:formatDate value="${statusBeanCustom.prodDate}" pattern="yyyy-MM-dd"/>" placeholder= " Please Select Prod Date"/>(EST)</td></tr>
</c:when>
<c:otherwise>
<tr><th>Prod Date</th><td><input type="text" id="prodDate" name="prodDateCustom" placeholder= " Please Select Prod Date"/>(EST)</td></tr>
</c:otherwise>
</c:choose>
<tr><th>Status Infor</th>
<c:choose>
<c:when test="${statusBeanCustom.status != null && statusBeanCustom.status != ''}">
<td><textarea name="status" size=100 rows="13" cols="70">${statusBeanCustom.status}</textarea></td></tr>
</c:when>
<c:otherwise>
<td><textarea name="status" size=100 rows="13" cols="70" placeholder=" Enter your Status Information Here"></textarea></td></tr>
</c:otherwise>
</c:choose>

<tr><th>Category</th><td><input type="radio" name="itemCategory" value="1" checked="checked"/>Daily Item<br/> 
<input type="radio" name="itemCategory" value="2"/>Document Tool</td></tr>
<tr><th>Status</th><td>
<select name="jobStatus"> 
                 <c:forEach items="${statusList}" var="jobStatus">
                 <c:choose>
                 <c:when test="${statusBeanCustom.jobStatus == jobStatus.projectStatus}">
                 <option value="${jobStatus.projectStatus}" selected>${jobStatus.projectStatus}</option>
                 </c:when>
                 <c:otherwise>
                 <option value="${jobStatus.projectStatus}">${jobStatus.projectStatus}</option>
                 </c:otherwise>
                 </c:choose>
                 </c:forEach>
                 </select>
</td></tr>
<tr><th>Status Percent</th>
<td><input type="text" name="statusPercent" value="0"/></td></tr>

<tr><th></th><td><input type="submit" name="Save" value="Save"/></td></tr>
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