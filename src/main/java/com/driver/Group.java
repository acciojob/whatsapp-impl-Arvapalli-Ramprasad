package com.driver;

import java.util.List;

public class Group {
    private String name;
    private List<User> users;

    public Group(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
