package com.example.s528116.smartinventory;

import android.widget.TextView;

import java.util.Date;

public class MessageContainer {
    private String title, sender, messageDocId, message;
    private Date composeDate;
    public MessageContainer(String title, String sender, Date composeDate, String messageDocId, String message){
        this.title = title;
        this. sender = sender;
        this.composeDate = composeDate;
        this.messageDocId = messageDocId;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getSender() {
        return sender;
    }

    public String getMessageDocId() {
        return messageDocId;
    }

    public Date getComposeDate() {
        return composeDate;
    }

    public String getMessage() {
        return message;
    }
}
