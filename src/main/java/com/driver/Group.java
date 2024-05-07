package com.driver;

import java.util.List;

public class Group {
    private String name;
    private int numberOfParticipants;
    private User admin;
    private List<User> participants;

    public Group(String name, int numberOfParticipants) {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }

    public Group(List<User> participants) {
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }


    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void changeAdmin(User approver, User user) throws Exception {
        if (!approver.equals(this.admin)) {
            throw new Exception("Approver does not have rights");
        }
        if (!participants.contains(user)) {
            throw new Exception("User is not a participant");
        }
        this.admin = user;
    }
}