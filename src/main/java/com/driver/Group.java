package com.driver;

import java.util.List;

public class Group {
    private String name;
    private int numberOfParticipants;
    private String admin;
    private List<User> participants;

    public Group(String name, int numberOfParticipants) {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }

//    public Group(List<User> users) {
//        if (users != null) {
//            this.participants = users;
//            this.numberOfParticipants = users.size();
//            if (numberOfParticipants == 2) {
//                // If there are only 2 users, set the group name as the name of the second user
//                this.name = users.get(1).getName();
//            } else {
//                // If there are more than 2 users, set the group name as "Group #count"
//                this.name = "Group " + WhatsappRepository.getCustomGroupCount();
//            }
//        }
//    }


    public Group(List<User> participants) {
        this.participants = participants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getNumberOfParticipants() {
//        return numberOfParticipants;
//    }
//
//    public void setNumberOfParticipants(int numberOfParticipants) {
//        this.numberOfParticipants = numberOfParticipants;
//    }


    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public List<User> getParticipants() {
        return participants;
    }

//    public void setParticipants(List<User> participants) {
//        this.participants = participants;
//    }

    public void changeAdmin(User approver, String user) throws Exception {
        if (!approver.equals(this.admin)) {
            throw new Exception("Approver does not have rights");
        }
        if (!participants.contains(user)) {
            throw new Exception("User is not a participant");
        }
        this.admin = user;
    }
}
