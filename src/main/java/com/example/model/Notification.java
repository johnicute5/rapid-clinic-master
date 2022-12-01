package com.example.model;

public class Notification {

    private String notificationId;

    public Notification(String message) {
        this.message = message;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    private String sendTo;
    private String patientId;
    private String appointmentDate;
    private String details;
    private String message;

    //constructor with Id;
    public Notification(String notificationId,String sendTo, String patientId, String appointmentDate, String details, String message, String type, Boolean hasSend, String sendDate, String hasSendDate) {
        this.notificationId = notificationId;
        this.sendTo = sendTo;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.details = details;
        this.message = message;
        this.type = type;
        this.hasSend = hasSend;
        this.sendDate = sendDate;
        this.hasSendDate = hasSendDate;
    }

    //constructor with without Id;
    public Notification(String patientId, String sendTo, String appointmentDate, String details, String message, String type, Boolean hasSend, String sendDate, String hasSendDate) {
        this.patientId = patientId;
        this.sendTo = sendTo;
        this.appointmentDate = appointmentDate;
        this.details = details;
        this.message = message;
        this.type = type;
        this.hasSend = hasSend;
        this.sendDate = sendDate;
        this.hasSendDate = hasSendDate;
    }

    private String type;

    public Notification() {
        super();
    }
    private Boolean hasSend;
    private String sendDate;
    private String hasSendDate;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHasSend() {
        return hasSend;
    }

    public void setHasSend(Boolean hasSend) {
        this.hasSend = hasSend;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getHasSendDate() {
        return hasSendDate;
    }

    public void setHasSendDate(String hasSendDate) {
        this.hasSendDate = hasSendDate;
    }


}
