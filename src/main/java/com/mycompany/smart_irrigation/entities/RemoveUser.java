package com.mycompany.smart_irrigation.entities;

public class RemoveUser {
    private final User user;

    public RemoveUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
