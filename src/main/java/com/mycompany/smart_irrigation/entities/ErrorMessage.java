package com.mycompany.smart_irrigation.entities;

import java.util.List;
import java.util.Set;

public class ErrorMessage {

    private List<String> messages;
    public ErrorMessage(List<String> messages){
        this.messages=messages;
    }
    public ErrorMessage(){

    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
