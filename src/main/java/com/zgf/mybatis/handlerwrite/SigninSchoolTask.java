package com.zgf.mybatis.handlerwrite;

import java.util.Date;

public class SigninSchoolTask {
    private Long wid;

    private String taskName;

    private String taskDemand;

    private Long noticeWid;

    private String taskType;

    private Boolean signDoor;

    private Byte signMode;

    private Boolean signCondition;

    private Boolean signRate;

    private String signWeek;

    private Date startDate;

    private Date stopDate;

    private Boolean noEndDate;

    private Boolean isPause;

    private Date signinStartTime;

    private Date signinEndTime;

    private Boolean photoFromSchool;

    private Boolean placeFromSchool;

    private Boolean qrcodeFromSchool;

    private String qrcodeReceivers;

    private String taskDescUrl;

    private Boolean isPhoto;

    private String photoRequireDesc;

    private String attachments;

    private String remindTimes;

    private String strongRemindType;

    private String strongRemindTime;

    private Long schoolId;

    private Long operatorId;

    private String operatorName;

    private Date createTime;

    private Date updateTime;

    private Boolean isDeleted;

    private String dataStatus;

    private Byte dataIndex;

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskDemand() {
        return taskDemand;
    }

    public void setTaskDemand(String taskDemand) {
        this.taskDemand = taskDemand == null ? null : taskDemand.trim();
    }

    public Long getNoticeWid() {
        return noticeWid;
    }

    public void setNoticeWid(Long noticeWid) {
        this.noticeWid = noticeWid;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public Boolean getSignDoor() {
        return signDoor;
    }

    public void setSignDoor(Boolean signDoor) {
        this.signDoor = signDoor;
    }

    public Byte getSignMode() {
        return signMode;
    }

    public void setSignMode(Byte signMode) {
        this.signMode = signMode;
    }

    public Boolean getSignCondition() {
        return signCondition;
    }

    public void setSignCondition(Boolean signCondition) {
        this.signCondition = signCondition;
    }

    public Boolean getSignRate() {
        return signRate;
    }

    public void setSignRate(Boolean signRate) {
        this.signRate = signRate;
    }

    public String getSignWeek() {
        return signWeek;
    }

    public void setSignWeek(String signWeek) {
        this.signWeek = signWeek == null ? null : signWeek.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Boolean getNoEndDate() {
        return noEndDate;
    }

    public void setNoEndDate(Boolean noEndDate) {
        this.noEndDate = noEndDate;
    }

    public Boolean getIsPause() {
        return isPause;
    }

    public void setIsPause(Boolean isPause) {
        this.isPause = isPause;
    }

    public Date getSigninStartTime() {
        return signinStartTime;
    }

    public void setSigninStartTime(Date signinStartTime) {
        this.signinStartTime = signinStartTime;
    }

    public Date getSigninEndTime() {
        return signinEndTime;
    }

    public void setSigninEndTime(Date signinEndTime) {
        this.signinEndTime = signinEndTime;
    }

    public Boolean getPhotoFromSchool() {
        return photoFromSchool;
    }

    public void setPhotoFromSchool(Boolean photoFromSchool) {
        this.photoFromSchool = photoFromSchool;
    }

    public Boolean getPlaceFromSchool() {
        return placeFromSchool;
    }

    public void setPlaceFromSchool(Boolean placeFromSchool) {
        this.placeFromSchool = placeFromSchool;
    }

    public Boolean getQrcodeFromSchool() {
        return qrcodeFromSchool;
    }

    public void setQrcodeFromSchool(Boolean qrcodeFromSchool) {
        this.qrcodeFromSchool = qrcodeFromSchool;
    }

    public String getQrcodeReceivers() {
        return qrcodeReceivers;
    }

    public void setQrcodeReceivers(String qrcodeReceivers) {
        this.qrcodeReceivers = qrcodeReceivers == null ? null : qrcodeReceivers.trim();
    }

    public String getTaskDescUrl() {
        return taskDescUrl;
    }

    public void setTaskDescUrl(String taskDescUrl) {
        this.taskDescUrl = taskDescUrl == null ? null : taskDescUrl.trim();
    }

    public Boolean getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(Boolean isPhoto) {
        this.isPhoto = isPhoto;
    }

    public String getPhotoRequireDesc() {
        return photoRequireDesc;
    }

    public void setPhotoRequireDesc(String photoRequireDesc) {
        this.photoRequireDesc = photoRequireDesc == null ? null : photoRequireDesc.trim();
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments == null ? null : attachments.trim();
    }

    public String getRemindTimes() {
        return remindTimes;
    }

    public void setRemindTimes(String remindTimes) {
        this.remindTimes = remindTimes == null ? null : remindTimes.trim();
    }

    public String getStrongRemindType() {
        return strongRemindType;
    }

    public void setStrongRemindType(String strongRemindType) {
        this.strongRemindType = strongRemindType == null ? null : strongRemindType.trim();
    }

    public String getStrongRemindTime() {
        return strongRemindTime;
    }

    public void setStrongRemindTime(String strongRemindTime) {
        this.strongRemindTime = strongRemindTime == null ? null : strongRemindTime.trim();
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }

    public Byte getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(Byte dataIndex) {
        this.dataIndex = dataIndex;
    }
}