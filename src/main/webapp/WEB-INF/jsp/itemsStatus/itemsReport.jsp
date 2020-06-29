<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
	background-color: rgba(0, 0, 0, 0.2)
}

table {
	border-right: 1px solid #3ca4c3;
	border-bottom: 1px solid #3ca4c3;
	border-collapse: collapse;
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
	border-collapse: collapse;
	padding: 6px 8px;
}

table tr.status {
	background: #30839c;
	font-weight: bold;
	color: white;
}

tr.secondLine {
	background: white;
}

tr.firstLine {
	background: #d2eaf1;
}

a {
	text-decoration: none
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
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
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

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<div style="width: 100%; height: 5px">&nbsp;</div>
	<div style="width: 100%; height: 20px">
		<font style="color: green; font-size: 15px; font-weight: bold">${TotalOpened} Items Opened</font>
	</div>
	<div>
		<table>
			<tr class="status">
				<td>Project</td>
				<td>Item Description</td>
				<td>Leader</td>
				<td>Owner</td>
				<td>Weekly Status Information</td>
				<%-- <c:if test="${filterBean.hideDocument==null}">	<td>Attachments / TestCase</td></c:if> --%>
				<td>Prod Date</td>
				<%-- <c:if test="${filterBean.hideActivity==null}">  <td>Activity</td></c:if> --%>
				<%-- <c:if test="${filterBean.hidePercent==null}">   <td>Percentage</td></c:if> --%>
				<td>Item Status</td>
				<%-- <c:if test="${filterBean.hideComment==null}">   <td>Comments / Scripts</td></c:if> --%>
			</tr>

			<c:forEach items="${openedStatusBeanList}" var="openedStatusBean" varStatus="order">
				<c:choose>
					<c:when test="${order.count % 2 == 0}">
						<tr class="firstLine">
					</c:when>
					<c:otherwise>
						<tr class="secondLine">
					</c:otherwise>
				</c:choose>
				<td>${openedStatusBean.projectName}</td>
				<td style="text-align:left;vertical-align:middle">${openedStatusBean.itemDesc}</td>
				<td>${openedStatusBean.leader}</td>
				<td>${openedStatusBean.owner}</td>
				<td style="text-align:left;vertical-align:top"><c:forEach items="${openedStatusBean.statusInforList}" var="statusInfor" varStatus="order">
				    <c:if test="${!order.last}">${statusInfor}</c:if>
					<c:if test="${order.last }"><font color="blue">${statusInfor} </font></c:if>
				</c:forEach>
				</td>
				<%-- <c:if test="${filterBean.hideDocument==null}"><td><a href="javascript:modifyWin2( )">[Add File]</a><br> </td></c:if> --%>
				<td nowrap><font color="red">
				<c:choose>
				<c:when test="${openedStatusBean.prodDate !=null}">
				<fmt:formatDate value="${openedStatusBean.prodDate}" pattern="yyyy-MM-dd" /> (EST)
				</c:when>
				<c:otherwise>
				<fmt:formatDate value="${openedStatusBean.prodDate}" pattern="yyyy-MM-dd" />
				</c:otherwise>
				</c:choose>
				</font></td>
				<%-- <c:if test="${filterBean.hideActivity==null}"> <td><font color="red"> </font></td></c:if> --%>
				<%-- <c:if test="${filterBean.hidePercent==null}">  <td></td></c:if> --%>
				<td>${openedStatusBean.jobStatus}</td>
				<%-- <c:if test="${filterBean.hideComment==null}"> <td><a href="javascript:modifyWin3( )">[Add]</a><br></td></c:if> --%>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div style="width: 100%; height: 15px">&nbsp;</div>
	<div style="width: 100%; height: 20px">
		<font style="color: green; font-size: 15px; font-weight: bold">${TotalCompleted} Items Completed</font>
	</div>
	<div>
		<table>
			<tr class="status">
				<td>Project</td>
				<td>Item Description</td>
				<td>Leader</td>
				<td>Owner</td>
				<td>Weekly Status Information</td>
				<%-- <c:if test="${filterBean.hideDocument==null}">	<td>Attachments / TestCase</td></c:if> --%>
				<td>Prod Date</td>
				<%-- <c:if test="${filterBean.hideActivity==null}">  <td>Activity</td></c:if> --%>
				<%-- <c:if test="${filterBean.hidePercent==null}">   <td>Percentage</td></c:if> --%>
				<td>Item Status</td>
				<%-- <c:if test="${filterBean.hideComment==null}">   <td>Comments / Scripts</td></c:if> --%>
			</tr>

			<c:forEach items="${completedStatusBeanList}" var="completedStatusBean" varStatus="order">
				<c:choose>
					<c:when test="${order.count % 2 == 0}">
						<tr class="firstLine">
					</c:when>
					<c:otherwise>
						<tr class="secondLine">
					</c:otherwise>
				</c:choose>
				<td>${completedStatusBean.projectName}</td>
				<td style="text-align:left;vertical-align:middle">${completedStatusBean.itemDesc}</td>
				<td>${completedStatusBean.leader}</td>
				<td>${completedStatusBean.owner}</td>
				<td style="text-align:left;vertical-align:top"><c:forEach items="${completedStatusBean.statusInforList}" var="statusInfor" varStatus="order">
				    <c:if test="${!order.last}">${statusInfor}</c:if>
					<c:if test="${order.last }"><font color="blue">${statusInfor} </font></c:if>
				</c:forEach>
				</td>
				<%-- <c:if test="${filterBean.hideDocument==null}"><td><a href="javascript:modifyWin2( )">[Add File]</a><br> </td></c:if> --%>
				<td nowrap><font color="red">
				<c:choose>
				<c:when test="${completedStatusBean.prodDate !=null}">
				<fmt:formatDate value="${completedStatusBean.prodDate}" pattern="yyyy-MM-dd" /> (EST)
				</c:when>
				<c:otherwise>
				<fmt:formatDate value="${completedStatusBean.prodDate}" pattern="yyyy-MM-dd" />
				</c:otherwise>
				</c:choose>
				</font></td>
				<%-- <c:if test="${filterBean.hideActivity==null}"> <td><font color="red"> </font></td></c:if> --%>
				<%-- <c:if test="${filterBean.hidePercent==null}">  <td></td></c:if> --%>
				<td>${completedStatusBean.jobStatus}</td>
				<%-- <c:if test="${filterBean.hideComment==null}"> <td><a href="javascript:modifyWin3( )">[Add]</a><br></td></c:if> --%>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div style="width: 100%; height: 15px">&nbsp;</div>
	<div style="width: 100%; height: 20px">
		<font style="color: green; font-size: 15px; font-weight: bold">${TotalonHold} Items on Hold</font>
	</div>
	<div>
		<table>
			<tr class="status">
				<td>Project</td>
				<td>Item Description</td>
				<td>Leader</td>
				<td>Owner</td>
				<td>Weekly Status Information</td>
				<%-- <c:if test="${filterBean.hideDocument==null}">	<td>Attachments / TestCase</td></c:if> --%>
				<td>Prod Date</td>
				<%-- <c:if test="${filterBean.hideActivity==null}">  <td>Activity</td></c:if> --%>
				<%-- <c:if test="${filterBean.hidePercent==null}">   <td>Percentage</td></c:if> --%>
				<td>Item Status</td>
				<%-- <c:if test="${filterBean.hideComment==null}">   <td>Comments / Scripts</td></c:if> --%>
			</tr>

			<c:forEach items="${onHoldStatusBeanList}" var="onHoldStatusBean" varStatus="order">
				<c:choose>
					<c:when test="${order.count % 2 == 0}">
						<tr class="firstLine">
					</c:when>
					<c:otherwise>
						<tr class="secondLine">
					</c:otherwise>
				</c:choose>
				<td>${onHoldStatusBean.projectName}</td>
				<td style="text-align:left;vertical-align:middle">${onHoldStatusBean.itemDesc}</td>
				<td>${onHoldStatusBean.leader}</td>
				<td>${onHoldStatusBean.owner}</td>
				<td style="text-align:left;vertical-align:top"><c:forEach items="${onHoldStatusBean.statusInforList}" var="statusInfor" varStatus="order">
				    <c:if test="${!order.last}">${statusInfor}</c:if>
					<c:if test="${order.last }"><font color="blue">${statusInfor} </font></c:if>
				</c:forEach>
				</td>
				<%-- <c:if test="${filterBean.hideDocument==null}"><td><a href="javascript:modifyWin2( )">[Add File]</a><br> </td></c:if> --%>
				<td nowrap><font color="red">
				<c:choose>
				<c:when test="${onHoldStatusBean.prodDate !=null}">
				<fmt:formatDate value="${onHoldStatusBean.prodDate}" pattern="yyyy-MM-dd" /> (EST)
				</c:when>
				<c:otherwise>
				<fmt:formatDate value="${onHoldStatusBean.prodDate}" pattern="yyyy-MM-dd" />
				</c:otherwise>
				</c:choose>
				</font></td>
				<%-- <c:if test="${filterBean.hideActivity==null}"> <td><font color="red"> </font></td></c:if> --%>
				<%-- <c:if test="${filterBean.hidePercent==null}">  <td></td></c:if> --%>
				<td>${onHoldStatusBean.jobStatus}</td>

				<%-- <c:if test="${filterBean.hideComment==null}"> <td><a href="javascript:modifyWin3( )">[Add]</a><br></td></c:if> --%>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>