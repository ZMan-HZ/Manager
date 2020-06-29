package com.ssc.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectStatus_NotInUse implements Serializable{
    private int id;

    private String itemDesc;

    private String leader;

    private String owner;

    private String status;

    private Date prodDate;

    private Date createTime;

    private Date closeTime;

    private String isClosed;

    private String isDelete;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String projectNm;

    private String jobStatus;

    private int statusPercent;

    private String hasAttachment;

    private String otherComment;

    private String attachmentsUrl;

    private int groupId;

    private String extraComment;

    private int itemCategory;

    private String dbScripts;

    private String remainedQuestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed == null ? null : isClosed.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getProjectNm() {
        return projectNm;
    }

    public void setProjectNm(String projectNm) {
        this.projectNm = projectNm == null ? null : projectNm.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public int getStatusPercent() {
        return statusPercent;
    }

    public void setStatusPercent(int statusPercent) {
        this.statusPercent = statusPercent;
    }

    public String getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(String hasAttachment) {
        this.hasAttachment = hasAttachment == null ? null : hasAttachment.trim();
    }

    public String getOtherComment() {
        return otherComment;
    }

    public void setOtherComment(String otherComment) {
        this.otherComment = otherComment == null ? null : otherComment.trim();
    }

    public String getAttachmentsUrl() {
        return attachmentsUrl;
    }

    public void setAttachmentsUrl(String attachmentsUrl) {
        this.attachmentsUrl = attachmentsUrl == null ? null : attachmentsUrl.trim();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getExtraComment() {
        return extraComment;
    }

    public void setExtraComment(String extraComment) {
        this.extraComment = extraComment == null ? null : extraComment.trim();
    }

    public int getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(int itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getDbScripts() {
        return dbScripts;
    }

    public void setDbScripts(String dbScripts) {
        this.dbScripts = dbScripts == null ? null : dbScripts.trim();
    }

    public String getRemainedQuestions() {
        return remainedQuestions;
    }

    public void setRemainedQuestions(String remainedQuestions) {
        this.remainedQuestions = remainedQuestions == null ? null : remainedQuestions.trim();
    }
}