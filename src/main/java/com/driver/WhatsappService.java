package com.driver;

import java.util.*;

public class WhatsappService {
    private WhatsappRepository whatsappRepository = new WhatsappRepository();

    public String createUser(String name, String mobile) throws Exception {
        if (whatsappRepository.isUserExists(mobile)) {
            throw new Exception("User already exists");
        }
        return whatsappRepository.createUser(name, mobile);
    }

    public Group createGroup(List<User> users) {
        String groupName;
        if (users.size() == 2) {
            groupName = users.get(1).getName(); // Personal chat
        } else {
            groupName = "Group " + (whatsappRepository.getCustomGroupCount());
        }
        return whatsappRepository.createGroup(groupName, users);
    }

    public int createMessage(String content) {
        return whatsappRepository.createMessage(content);
    }

//    public int sendMessage(Message message, User sender, Group group) throws Exception {
//        if (!whatsappRepository.isGroupExists(group)) {
//            throw new Exception("Group does not exist");
//        }
//        if (!group.getUsers().contains(sender)) {
//            throw new Exception("You are not allowed to send message");
//        }
//        return whatsappRepository.sendMessage(message, sender, group);
//    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if (!whatsappRepository.isGroupExists(group)) {
            throw new Exception("Group does not exist");
        }
        User currentAdmin = whatsappRepository.getGroupAdmin(group);
        if (!approver.equals(currentAdmin)) {
            throw new Exception("Approver does not have rights");
        }
        if (!group.getUsers().contains(user)) {
            throw new Exception("User is not a participant");
        }
        whatsappRepository.changeAdmin(user, group);
        return "SUCCESS";
    }

    // Method to find user by mobile number
    public User findUserByMobile(String mobile) throws Exception {
        User user = whatsappRepository.getUserByMobile(mobile);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    // Method to find group by name
    public Group findGroupByName(String groupName) throws Exception {
        Group group = whatsappRepository.getGroupByName(groupName);
        if (group == null) {
            throw new Exception("Group not found");
        }
        return group;
    }

    // Bonus problems - non-scorable
    public int removeUser(User user) throws Exception {
        // Implementation for removing user from a group
        throw new UnsupportedOperationException("Not implemented");
    }

    public String findMessage(Date start, Date end, int K) throws Exception {
        // Implementation for finding Kth latest message
        throw new UnsupportedOperationException("Not implemented");
    }
}
