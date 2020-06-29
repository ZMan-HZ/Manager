package com.ssc.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class StatusBean implements Serializable{
	/**
	 * 
	 */

	public StatusBean() {

	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private int id;
	private String itemDesc;
	private String leader;
	private String owner;
	private String status = "";
	private Date prodDate;
	private Date createTime = new Date();
	private Date closeTime;
	private String isClosed = "N";
	private String isDelete = "N";
	private String createBy;
	private Date updateTime;
	private String updateBy;
	private String projectName;
	private String jobStatus;
	private int statusPercent;
	private String hasAttachment = "N";
	private String comments = "";
	private String attachments_url = "";
	private int groupId;
	private int itemCategory;
	private String extraComment;
	private String dbScripts;
	private boolean hasTestCase = false;
	private String remainedQuestions;
	private String projectComments;
	private String isReverse = "";

	
	
	
	public boolean isHasTestCase() {
		return hasTestCase;
	}

	public void setHasTestCase(boolean hasTestCase) {
		this.hasTestCase = hasTestCase;
	}

	public String getIsReverse() {
		return isReverse;
	}

	public void setIsReverse(boolean isReverse) {
		if (isReverse) {
			this.isReverse = "not";
		} else {
			this.isReverse = "";
		}
	}

	public void setIsClosed(boolean isClosed) {
		if (isClosed) {
			this.isClosed = "Y";
		} else {
			this.isClosed = "N";
		}
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		if (isDelete) {
			this.isDelete = "Y";
		} else {
			this.isDelete = "N";
		}
	}

	public String getProjectComments() {
		return projectComments;
	}

	public void setProjectComments(String projectComments) {
		this.projectComments = projectComments;
	}

	public String getRemainedQuestions() {
		return remainedQuestions;
	}

	public void setRemainedQuestions(String remainedquestions) {
		this.remainedQuestions = remainedquestions;
	}

	public StatusBean(int id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getProdDate() {
		return prodDate;
	}

	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public void setIsClosed(String isClosed) {
		if (isClosed != null) {
			isClosed = isClosed.toUpperCase();
		}
		if (this.isClosed.equalsIgnoreCase("N")
				&& isClosed.equalsIgnoreCase("Y")) {
			this.closeTime = new Date();
		}
		this.isClosed = isClosed;
	}

	public void setIsDelete(String isDelete) {
		if (isDelete != null) {
			isDelete = isDelete.toUpperCase();
		}
		this.isDelete = isDelete;
	}

	public String getDateAsHtmlString(Date date) {
		if (date == null) {
			return "&nbsp;";
		} else {
			return this.sdf.format(date);
		}
	}

	public String getDateAsHtmlInputString(Date date) {
		if (date == null) {
			return "";
		} else {
			return this.sdf.format(date);
		}
	}

	public String getTimeAsString(Date date) {
		if (date == null) {
			return "&nbsp;";
		} else {
			return this.sdf2.format(date);
		}
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setStatusPercent(int statusPercent) {
		this.statusPercent = statusPercent;
	}

	public int getStatusPercent() {
		return statusPercent;
	}

	public void setHasAttachment(String hasAttachment) {
		this.hasAttachment = hasAttachment;
	}

	public String getHasAttachment() {
		return hasAttachment;
	}

	public void setComments(String comment) {
		this.comments = comment;
	}

	public String getComments() {
		return comments;
	}

	public void setAttachments_url(String attachments_url) {
		this.attachments_url = attachments_url;
	}

	public String getAttachments_url() {
		return attachments_url;
	}

	public String getAttachments_urlForHtml() {
		if (StringUtils.isEmpty(attachments_url)) {
			return "<span style=\"color:red\">TestCase: N<span><br>";
		} else {
			StringBuilder sb = new StringBuilder();
			String[] allFileLink = attachments_url.split("@@@");
			for (String link : allFileLink) {
				if (link != null && link.length() > 0) {
					String[] result = link.split("\\|");
					String temp = result[1].toUpperCase();
					if (temp.indexOf("TESTCASE") != -1 || temp.indexOf("TEST_CASE") != -1) {
						hasTestCase = true;
					}
					// <a
					// href="DownloadFile?fileId=<%=projectAttachmentBean.getId()
					// %>"><%=projectAttachmentBean.getFileName() %></a>
					sb.append("<a href=\"DownloadFile?fileId=").append(result[0]).append("\">").append(result[1]).append("</a><br>");
				}
			}
			if (hasTestCase) {
				sb.insert(0, "<span style=\"color:green\">TestCase: Y<span><br>");
			} else {
				sb.insert(0, "<span style=\"color:red\">TestCase: N<span><br>");
			}
			return sb.toString();
		}
	}

	public String getTestCase_urlForHtml() {
		if (StringUtils.isEmpty(attachments_url)) {
			return " N ";
		} else {
			StringBuilder sb = new StringBuilder();
			String[] allFileLink = attachments_url.split("@@@");
			for (String link : allFileLink) {
				if (link != null && link.length() > 0) {
					String[] result = link.split("\\|");
					String temp = result[1].toUpperCase();
					if (temp.indexOf("TESTCASE") != -1
							|| temp.indexOf("TEST_CASE") != -1) {
						sb.append("<a href=\"DownloadFile?fileId=")
								.append(result[0]).append("\">")
								.append(result[1]).append("</a><br>");
						break;
					}
				}
			}
			if (sb.length() == 0) {
				sb.append(" N ");
			}
			return sb.toString();
		}
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setItemCategory(int itemCategory) {
		this.itemCategory = itemCategory;
	}

	public int getItemCategory() {
		return itemCategory;
	}

	public void setExtraComment(String extraComment) {
		this.extraComment = extraComment;
	}

	public String getExtraComment() {
		return extraComment;
	}

	public void setDbScripts(String dbScripts) {
		this.dbScripts = dbScripts;
	}

	public String getDbScripts() {
		return dbScripts;
	}

}
