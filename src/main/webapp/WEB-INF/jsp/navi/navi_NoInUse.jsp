<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body {
    font-size: 14px; padding:10px 10px 10px 0px; background-color:rgb(0,0,0)
}
a {
	text-decoration:none
} 
a:link {
    color: white;
}

/* visited link */
a:visited {
    color: white;
}

/* mouse over link */
 a:hover {
    color: white;
}

/* selected link */
a:active {
     color: white;
}
li {padding:0;margin:3px 5px;}
.headTag {color:rgb(0,204,0)}
</style>
<script type="text/javascript">
</script>



<title>Status Manager</title>

</head>
<body>

<body>
<ul style="list-style-type:none; margin:0px; padding:0px;">
<li style="list-style-type:none;">
<a href="${pageContext.request.contextPath }/addNew.action" target="right" >Add New</a></li>
<li><a href="${pageContext.request.contextPath }/status.action" target="right" >Browse All Status</a></li>
<%-- <li><a href="${pageContext.request.contextPath }/allFiles.action" target="right" >Browse All Files</a></li> --%>
<li><a href="${pageContext.request.contextPath }/resourceFiles.action" target="right" >Resource Files</a></li>
<li><a href="${pageContext.request.contextPath }/statusReport.action" target="right" >Status Report</a></li>

<li class="headTag">==Prod Release==</li>
<c:forEach items="${prodReleaseList}" var="prodRelease">
<tr>
	<a href="${pageContext.request.contextPath }/status.action?prodDate=<fmt:formatDate value="${prodRelease.prodDate}" pattern="yyyy-MM-dd"/>&projectName=${prodRelease.projectName}" target="right" >
	<font color="White"><td>${prodRelease.projectName}</td>(<td><fmt:formatDate value="${prodRelease.prodDate}" pattern="yyyy-MM-dd"/></td>)</font></a>
	<br/>
</tr>
</c:forEach>
<c:if test="${ismore == null}">
 <li><a href="${pageContext.request.contextPath }/navi.action?ismore=true" target="left" >Browse All>></a></li>
</c:if>
<c:if test="${ismore != null}">
 <li><a href="${pageContext.request.contextPath }/navi.action?" target="left" > <c:out value="<<Return"></c:out></a></li>
</c:if>


  <li class="headTag">==All Project==</li>
<c:forEach items="${allProjectList}" var="projectNames">
<tr>
	<a href="${pageContext.request.contextPath }/status.action?projectName=${projectNames.projectName}" target="right">
	<font color="White"><td>${projectNames.projectName}</td>(${projectNames.count})</font></a>
	<br/>
</tr>
</c:forEach>
  
  <li class="headTag">==Job Status==</li>
  <c:forEach items="${allStatusList}" var="projectStatus">
<tr>
	<a href="${pageContext.request.contextPath }/status.action?jobStatus=${projectStatus.job_status}" target="right">
	<font color="White"><td>${projectStatus.job_status}</td>(${projectStatus.resultNum} )</font></a>
	<br/>
</tr>
</c:forEach>
  
 <li class="headTag">==Owner==</li>
   <c:forEach items="${allOwnerList}" var="owner">
<tr>
	<a href="${pageContext.request.contextPath }/status.action?owner=${owner}" target="right">
	<font color="White"><td>${owner}</td></font></a>
	<br/>
</tr>
</c:forEach>
<br/>
<a href="${pageContext.request.contextPath }/logout.action" target="right"><font color="red"> Logout>></font></a>
 
 
</ul>
</body>
</html>