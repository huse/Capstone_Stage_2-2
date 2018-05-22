package com.hpr.hus.capstone_stage_2.messaging;

import android.net.Uri;

import java.util.Date;

public class GetSetMessage {

    private String messageText;
    private String messageUser;
    private String emailUser;
    private Uri uriPhoto;
    private long messageTime;

    public GetSetMessage(String messageText, String messageUser, String emailUser, Uri uriPhoto) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.emailUser = emailUser;
        this.uriPhoto = uriPhoto;


        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public GetSetMessage(){

    }

    public String getMessageText() {
        return messageText;
    }
    public String getEmailUser() {
        return emailUser;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }
    public Uri getUriPhoto() {
        return uriPhoto;
    }


    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}

