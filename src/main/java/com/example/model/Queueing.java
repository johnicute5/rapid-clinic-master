package com.example.model;

public class Queueing {

    private String queueId;
    private boolean checkin;
    private String type;
    private String startTime;
    private String endTime;
    private String patientId;
    private String ReportId;

    public Queueing(String queueId, boolean checkin, String type, String startTime, String endTime, String patientId, String reportId) {
        this.queueId = queueId;
        this.checkin = checkin;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        ReportId = reportId;
    }

    public Queueing(boolean checkin,String type, String startTime, String endTime, String patientId, String reportId) {

        this.checkin = checkin;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        ReportId = reportId;
    }



    public Queueing() {
        super();
    }
    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public boolean isCheckin() {
        return checkin;
    }

    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }


}
