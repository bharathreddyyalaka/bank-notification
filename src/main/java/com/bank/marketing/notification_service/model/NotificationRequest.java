package com.bank.marketing.notification_service.model;


public class NotificationRequest {

    private Long campaignId;
    private String recipient;
    private String message;
    private String subject;

    // Constructors
    public NotificationRequest() {
    }

    public NotificationRequest(String recipient, String message, String subject, Long campaignId) {
        this.recipient = recipient;
        this.message = message;
        this.subject = subject;
        this.campaignId =campaignId;
    }

    // Getters and Setters
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }
}

