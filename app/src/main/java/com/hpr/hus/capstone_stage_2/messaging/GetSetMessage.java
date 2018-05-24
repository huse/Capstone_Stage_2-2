package com.hpr.hus.capstone_stage_2.messaging;

import java.util.Date;

public class GetSetMessage {

    private String messageText;
    private String messageUser;
    private String emailUser;
    private String uriPhoto;
    private long messageTime;

    public GetSetMessage(String messageText, String messageUser, String emailUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.emailUser = emailUser;
        // this.uriPhoto = uriPhoto;


        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public GetSetMessage() {

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getUriPhoto() {
        return uriPhoto;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}

