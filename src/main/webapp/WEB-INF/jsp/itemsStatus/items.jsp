<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<script language="JavaScript" src="scripts/jquery-1.9.1.js"></script>
<script language="JavaScript" src="scripts/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style.css"  />
<script type ="text/javascript">
  $(function() {
  $( "#prodDate" ).datepicker({dateFormat:"yy-mm-dd"});
  });
</script>
	
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
	table-layout: fixed;
}
table td {
	border-left: 1px solid #3ca4c3;
	border-top: 1px solid #3ca4c3;
	padding: 6px 8px;
	text-align: center;
	vertical-align: middle;
/* 	word-wrap: break-word; */
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
.reviewTag {
	display: inline-block; 
	color: green;
	}
</style>
</head>
<body>

<div> 
<c:if test="${filterBean.prodDate != null &&  filterBean.projectName != null && projectComments != null}">
<c:forEach items="${projectComments}" var="comments" varStatus="index">
<td style="text-align:left;vertical-align:middle">${comments}</td>
</c:forEach>
</c:if>
</div>

<form action="status.action" method="post" id="filterForm">
<table >
<tr>
<td>Owner: </td> 
<td colspan="2" > <input type="text" name="owner" value="${filterBean.owner}" width="6" ></td>
<td>prodDate: </td> 
<td colspan="2"><input type="text" id="prodDate" name="prodDate" value="<fmt:formatDate value="${filterBean.prodDate}" pattern="yyyy-MM-dd" />" placeholder= "Please Select a Date"/> 	
<c:choose>
<c:when test="${filterBean.projectName == null}">
<td>ProjectName:</td>
<td> <select name="projectName"> 
                 <option value="">-ALL-</option>
                 <c:forEach items="${projectName}" var="names">
                 <option value="${names.projectName }">${names.projectName}</option>
                 </c:forEach>
                 </select></td>
</c:when>
<c:otherwise>
<td>ProjectName:</td>
<td> <select name="projectName"> 
                 <option value="">-ALL-</option>
                 <c:forEach items="${projectName}" var="names">
                 <c:choose>
                 <c:when test="${filterBean.projectName == names.projectName}">
                 <option value="${names.projectName }" selected>${names.projectName}</option>
                 </c:when>
                 <c:otherwise>
                 <option value="${names.projectName }" >${names.projectName}</option>
                 </c:otherwise>
                 </c:choose>
                 </c:forEach>
                 </select></td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.isDelete==null}">
<td>IS DELETE:<input type="checkbox" name="isDelete" value="true"  />
</c:when>
<c:otherwise>
<td>IS DELETE:<input type="checkbox" name="isDelete" value="true"  checked="true"/>
</c:otherwise>
</c:choose> 
<c:choose>
<c:when test="${filterBean.isClose==null}">
<td>IS CLOSE:<input type="checkbox" name="isClose" value="true" />
</c:when>
<c:otherwise>
<td>IS CLOSE:<input type="checkbox" name="isClose" value="true" checked="true"/>
</c:otherwise>
</c:choose> 
<c:choose>
<c:when test="${filterBean.isOnlyShowLatestInfo==null}">
<td>Only Latest Infor:<input type="checkbox" name="isOnlyShowLatestInfo" value="true"/>
</c:when>
<c:otherwise>
<td>Only Latest Infor:<input type="checkbox" name="isOnlyShowLatestInfo" value="true" checked="true"/>
</c:otherwise>
</c:choose>   
<c:choose>
<c:when test="${filterBean.isReverse==null}">
<td colspan="2">Reverse Status:<input type="checkbox" name="isReverse" value="true" />
</c:when>
<c:otherwise>
<td colspan="2">Reverse Status:<input type="checkbox" name="isReverse" value="true" checked="true"/></td>
</c:otherwise>
</c:choose>
<%-- <td><a href="javascript:copyTask('${filterBean.projectName}','${filterBean.prodDate}')">[Copy Task]</a> </td> --%>
</tr>
<tr>
<td>Job Status:</td>
<c:choose>
<c:when test="${filterStatus == null}">
 <c:forEach items="${statusList}" var="status">
<td><input type="checkbox" name="isJobStatus" value="${status.projectStatus}" checked="true">${status.projectStatus}</td>
</c:forEach>
</c:when>
<c:otherwise>
<c:forEach items="${statusList}" var="status">
<c:forEach items="${filterStatus}" var="map">
<c:if test="${map.key == status.projectStatus }">
<td><input type="checkbox" name="isJobStatus" value="${status.projectStatus}" ${map.value}>${status.projectStatus}</td>
</c:if>
</c:forEach>
</c:forEach>
</c:otherwise>
</c:choose>
</tr>
<tr>
 <td>Hide Columns: </td>
 <c:choose>
 <c:when test="${filterBean.hideOrder==null}">
<td><input type="checkbox" name="hideOrder" value="true" />Order </td>
 </c:when>
 <c:otherwise>
<td><input type="checkbox" name="hideOrder" value="true" checked="true"/>Order </td>
 </c:otherwise>
 </c:choose>
<c:choose>
<c:when test="${filterBean.hideLeader==null}">
<td><input type="checkbox" name="hideLeader" value="true"  />Leader </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideLeader" value="true" checked="true" />Leader </td>
</c:otherwise>
</c:choose> 
<c:choose>
<c:when test="${filterBean.hideOwner==null}">
<td><input type="checkbox" name="hideOwner" value="true"   />Owner </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideOwner" value="true"  checked="true" />Owner </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hideStatusInfo==null}">
<td><input type="checkbox" name="hideStatusInfo" value="true" />Status Info </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideStatusInfo" value="true"  checked="true" />Status Info </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hideDocument==null}">
<td><input type="checkbox" name="hideDocument" value="true"  />Attachments </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideDocument" value="true"  checked="true"/>Attachments </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hideProdDate==null}">
<td><input type="checkbox" name="hideProdDate" value="true" />ProdDate </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideProdDate" value="true" checked="true"/>ProdDate </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hideActivity==null}">
<td><input type="checkbox" name="hideActivity" value="true"  />Activity </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideActivity" value="true"  checked="true"/>Activity </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hidePercent==null}">
<td><input type="checkbox" name="hidePercent" value="true"  />Percentage </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hidePercent" value="true"  checked="true"/>Percentage </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hideJobStatus==null}">
<td><input type="checkbox" name="hideJobStatus" value="true"  />Item Status </td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideJobStatus" value="true" checked="true" />Item Status </td>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${filterBean.hideComment==null}">
<td><input type="checkbox" name="hideComment" value="true"/>Comments</td>
</c:when>
<c:otherwise>
<td><input type="checkbox" name="hideComment" value="true" checked="true" />Comments</td>
</c:otherwise>
</c:choose>
<td colspan="2" ><input type="submit" name="filter" value="filter" /></td>
<input type="hidden" name="isFilter" value="true" />
</tr>
</table>
</form>
 
<form action="releaseNotes.action" method="post" id="ReleasePanelId">
<div style="width: 100%; height: 20px">
Total:&nbsp; <c:out value="${totalRecords}" />&nbsp;&nbsp;&nbsp;&nbsp;
Reviewed:&nbsp; <c:out value="${reviewedTag}"/> &nbsp;&nbsp;&nbsp;
Select All:
<input type="checkbox" name="selAll" onClick="javascript:selectAll(this)"> &nbsp;</input>
<input type="button" name="Generate UAT Release Notes" value="Generate UAT Release"  onClick="javascript:generateRelease('DEV_COM','UAT')"/>
<input type="button" name="Generate Prod Release Notes" value="Generate Prod Release" onClick="javascript:generateRelease('PASS','PASS')"/>
<c:if test="${filterBean.prodDate != null && filterBean.projectName != null && projectComments != null}">
<input type="button" name="Edit" value="Edit Project Comments" onClick="javascript:updateProjectComment('${filterBean.projectName}','<fmt:formatDate value="${filterBean.prodDate}" pattern="yyyy-MM-dd" />')"> &nbsp;</input>
</c:if>
</div>
<div id="tableContainer">
<br>
<table>
 <tr class="status" >
                                                <td></td>
<c:if test="${filterBean.hideOrder==null}">     <td>Order</td></c:if>
                                                <td>Project</td>
	                                            <td>Item Description</td>
<c:if test="${filterBean.hideLeader==null}">    <td>Leader</td></c:if>
<c:if test="${filterBean.hideOwner==null}">     <td>Owner</td></c:if>
<c:if test="${filterBean.hideStatusInfo==null}"><td>Weekly Status Information</td></c:if>
<c:if test="${filterBean.hideDocument==null}">	<td>Attachments / TestCase</td></c:if>
<c:if test="${filterBean.hideProdDate==null}">	<td>Prod Date</td></c:if>
<c:if test="${filterBean.hideActivity==null}">  <td>Activity</td></c:if>
<c:if test="${filterBean.hidePercent==null}">   <td>Percentage</td></c:if>
<c:if test="${filterBean.hideJobStatus==null}"> <td>Item Status</td></c:if>
<c:if test="${filterBean.hideComment==null}">   <td>Comments / Scripts</td></c:if>
</tr>
<c:forEach items="${statusBeanList}" var="statusBean" varStatus="order">
<c:choose>
<c:when test="${order.count % 2 == 0}">
<tr class="firstLine">
</c:when>
<c:otherwise>
<tr class="secondLine">
</c:otherwise>
</c:choose>
                                                <td style="text-align:center;vertical-align:middle"><input name="checkbox_id" type="checkbox" value="${statusBean.id}" /></td>
<c:if test="${filterBean.hideOrder==null}"><c:choose><c:when test="${statusBean.reviewedTag == null}">
                                                <td><a href="javascript:updateStatus('${statusBean.id}')">${order.count}</a></td></c:when><c:otherwise>
                                                <td><a href="javascript:updateStatus('${statusBean.id}')">${order.count}</a><br/><font color="red">[R]</font></td></c:otherwise></c:choose></c:if>
                                                <td><a name="#_${statusBean.id}">${statusBean.projectName}</a><br/> </td> 
                                                <td style="text-align:left;vertical-align:middle">
                                                ${statusBean.itemDesc}<br/>
                                                <c:if test="${statusBean.remainedQuestions != null }"><font color="red" size="2"><b>[Problems Remained] !!!</b></font></c:if></td> 
<c:if test="${filterBean.hideLeader==null}">    <td>${statusBean.leader} </td></c:if>
<c:if test="${filterBean.hideOwner==null}">     <td>${statusBean.owner}</td></c:if>

<c:choose>
<c:when test="${filterBean.isOnlyShowLatestInfo==null}">
<c:if test="${filterBean.hideStatusInfo==null}"><td style="text-align:left;vertical-align:top">
                                               <c:forEach items="${statusBean.statusInforList}" var="statusInfor" varStatus="order">
                                                <c:if test="${!order.last}">${statusInfor}</c:if>
                                                <c:if test="${order.last }"><font color="lilac">${statusInfor} </font></c:if></c:forEach></td>
                                                </c:if>
</c:when>
<c:otherwise>
<c:if test="${filterBean.hideStatusInfo==null}"><td style="text-align:left;vertical-align:top">
                                                <c:forEach items="${statusBean.statusInforList}" var="statusInfor" varStatus="order">
                                                <c:if test="${order.last}">${statusInfor}</c:if></c:forEach></td></c:if>
</c:otherwise>
</c:choose>

<c:if test="${filterBean.hideDocument==null}">  <td style="text-align:left;vertical-align:top"><a href="javascript:uploadFile('${statusBean.id}')">[Add File]</a><br>
                                                <c:choose>
                                                <c:when test="${statusBean.hasTestCase}">
                                                <span style="color:green">TestCase: Y<span><br>
                                                </c:when>
                                                <c:otherwise>
                                                <span style="color:red">TestCase: N<span><br>
                                                </c:otherwise>
                                                </c:choose>
                                                
                                                <c:forEach items="${attachmentBeanCustomList}" var="attachment">
                                                <c:if test="${attachment.projectId == statusBean.id}">
                                                <a href="${pageContext.request.contextPath }/downloadFile.action?id=${attachment.id}">${attachment.fileName}</a><br/>
                                                </c:if>
                                                </c:forEach>
                                                
                                                </td></c:if>
<c:if test="${filterBean.hideProdDate==null}">  <td nowrap><font color="red">
				                                <c:choose>
				                                <c:when test="${statusBean.prodDate !=null}">
				                                <fmt:formatDate value="${statusBean.prodDate}" pattern="yyyy-MM-dd" /> (EST)
				                                </c:when>
				                                <c:otherwise>
                                                <fmt:formatDate value="${statusBean.prodDate}" pattern="yyyy-MM-dd" />
				                                </c:otherwise>
				                                </c:choose>
                                                </font></td></c:if>
<c:if test="${filterBean.hideActivity==null}">  <td>${statusBean.extraComment}</td></c:if>
<c:if test="${filterBean.hidePercent==null}">   <td>${statusBean.statusPercent}</td></c:if>
<c:if test="${filterBean.hideJobStatus==null}">	<td>${statusBean.jobStatus}  </td></c:if>
<c:if test="${filterBean.hideComment==null}">   <td style="text-align:left;vertical-align:top"><a href="javascript:updateComment('${statusBean.id}')">[Add]</a><br/>
                                                <c:if test="${statusBean.dbScripts != null }">${statusBean.dbScripts}<br/></c:if>
                                                <c:if test="${statusBean.comments != null }">${statusBean.comments}<br/></c:if>
                                                <c:if test="${statusBean.remainedQuestions != null }"><font color="red"><b>[Problems]:</b></font><br/>${statusBean.remainedQuestions}</c:if>
                                                 </td></c:if>
</tr>
</c:forEach>
</table>
</div>
</form>

<div id="myModal" class="modal">
	<div id="popWin" class="modal-content" align="middle">
		<span class="close" id="close" >&times;</span>
		<iframe id="testFrame" frameborder="0" ></iframe>
	</div>
</div>		
<script type="text/javascript">
		var modal = document.getElementById('myModal');
		var closeButton = document.getElementById('close');
		var testFrame = document.getElementById('testFrame');
		var tempStatusId;

		function updateStatus(statusId) {
			tempStatusId = statusId;
			modal.style.display = "block";
			testFrame.src = "${pageContext.request.contextPath}/updateStatus.action?isOpenFirst=true&id="	+ statusId;
			testFrame.style.width = "100%";
			testFrame.style.height = "95%";
		}
		closeButton.onclick = function() {
			modal.style.display = "none";
			testFrame.src = "";
			document.getElementById("filterForm").action = "${pageContext.request.contextPath}/status.action#_"	+ tempStatusId;
			document.getElementById("filterForm").submit();
		}
		function uploadFile(statusId) {
			tempStatusId = statusId;
			modal.style.display = "block";
			testFrame.src = "${pageContext.request.contextPath}/uploadFile.action?projectID=" + statusId;
			testFrame.style.width = "100%";
			testFrame.style.height = "95%";
		}
		function updateComment(statusId) {
			tempStatusId = statusId;
			modal.style.display = "block";
			testFrame.src = "${pageContext.request.contextPath}/updateComment.action?isOpenFirst=true&projectID="	+ statusId;
			testFrame.style.width = "100%";
			testFrame.style.height = "95%";
		}
		function updateProjectComment(projectName,prodDate) {
			modal.style.display = "block";
			testFrame.src = "${pageContext.request.contextPath}/projectComments.action?isOpenFirst=true&projectName="+ projectName +"&prodDate="+prodDate;
			testFrame.style.width = "100%";
			testFrame.style.height = "95%";
		}
		function generateRelease(status,statusToFlag) {
			var ReleasePanelForm = document.getElementById("ReleasePanelId");
			ReleasePanelForm.action=ReleasePanelForm.action + "?statusFlag=" + status +"&statusToFlag="+statusToFlag;
			ReleasePanelForm.submit();
		}
		function selectAll(source) {
			var mycheckboxes = document.getElementsByName('checkbox_id');
			for (var i = 0, n = mycheckboxes.length; i < n; i++) {
				mycheckboxes[i].checked = source.checked;
			}
		}
		function copyTask(projectNm,prodDate) {
			var container = document.getElementById('tableContainer');
			var checks = container.getElementsByTagName('input');
			var checkedIds = [];
			var indexInCheckedIds = 0;
			for (var i = 0; i < checks.length; i++) {
				if (checks[i].checked == true) {
					checkedIds[indexInCheckedIds] = checks[i].value;
					indexInCheckedIds++;
				}
			}
			alert(checkedIds);
			modal.style.display = "block";
			testFrame.src = "copyDetail.jsp?prodDate=" + prodDate.value	+ "&projectNm=" + projectNm.value + "&checkedIds=" + checkedIds;
			testFrame.style.width = "100%";
			testFrame.style.height = "95%";
		}
		(function($) {
		    $.fn.dragDiv = function(divWrap) {
		     var isDown = false;
		        return this.each(function() {
		            var $divMove = $(this);
		            var $divWrap = divWrap ? $divMove.parents(divWrap) : $divMove;
		            var mX = 0, mY = 0;
		            var dX = 0, dY = 0;
		            if(document.attachEvent) {
		                $divMove[0].attachEvent('onselectstart', function() {
		                    return false;
		                });
		            }
		            $divMove.mousedown(function(event) {
		                var event = event || window.event;
		                mX = event.clientX;
		                mY = event.clientY;
		                dX = $divWrap.offset().left;
		                dY = $divWrap.offset().top;
		                isDown = true;
		            });
		            $(document).mousemove(function(event) {
		                var event = event || window.event;
		                var x = event.clientX;
		                var y = event.clientY;
		                if(isDown) {
		                    $divWrap.css({"left": x - mX + dX, "top": y - mY + dY});
		                }
		            });
		            $(document).mouseup(function() {
		                isDown = false;
		            });
		        });
		    };
		})(jQuery);
		$(document).ready(function() {
		    $("#myModal").dragDiv();
		});
		
		
		
</script>

<script type="text/javascript">
// var mx,my,ox,oy;
// function e(event){
// 	 if(!event){
// 		 event = window.event;
// 		 event.target = event.srcElement ;
// 		 event.layerX = event.offsetX;
// 		 event.layerY = event.offsetY;
// 	 }
// 	 event.mx = event.pageX || event.clientX + document.body.scrollLeft;
// 	 event.my = event.pageY || event.clientY + document.body.scrollTop;
// // 	 console.log(event.mx);
// 	 return event;
//    }
// 	document.onmousedown = function(event){
// 		event = e(event);
// 		o = event.target;
// 		console.log(o);
// 		ox = parseInt(o.offsetLeft);
// 		oy = parseInt(o.offsetTop);
// 		mx = event.mx;
// 		my = event.my;
// 		document.onmousemove = move;
// 		document.onmouseup = stop;
// 	}	
// 	function move(event){
// 		event = e(event);
// 		o.style.left = ox + event.mx -mx ;
// 		o.style.top = oy + event.my - my  ;
// 	}
// 	function stop(event){
// 		event = e(event);
// 		ox = parseInt(o.offsetLeft);
// 		oy = parseInt(o.offsetTop);
// 		mx = event.mx;
// 		my = event.my;
// 		o = document.onmousemove = document.onmouseup = null;
// 	}
</script>

</body>
</html>
