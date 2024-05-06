package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class WhatsappRepository {
    private Map<String, User> userMap = new HashMap<>();
    private List<Group> groups = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private int groupIdCounter = 1;
    private int messageIdCounter = 1;

    public String createUser(String name, String mobile) throws Exception {
        if (userMap.containsKey(mobile)) {
            throw new Exception("User already exists with mobile number: " + mobile);
        }

        User user = new User(name, mobile);
        userMap.put(mobile, user);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users) {
        String groupName;
        if (users.size() == 2) {
            groupName = users.get(1).getName(); // Personal chat
        } else {
            groupName = "Group " + groupIdCounter++;
        }

        Group group = new Group(groupName, users);
        groups.add(group);
        return group;
    }

    public int createMessage(String content) {
        Message message = new Message(messageIdCounter++, content, new Date());
        messages.add(message);
        return message.getId();
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
        if (!groups.contains(group)) {
            throw new Exception("Group does not exist");
        }

        if (!group.getUsers().contains(sender)) {
            throw new Exception("You are not allowed to send message");
        }

        messages.add(message);
        return messages.size(); // Return number of messages in the group
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception {
        if (!groups.contains(group)) {
            throw new Exception("Group does not exist");
        }

        if (!group.getUsers().contains(approver)) {
            throw new Exception("Approver does not have rights");
        }

        if (!group.getUsers().contains(user)) {
            throw new Exception("User is not a participant");
        }

        // Implement admin change logic (for example, update adminMap)
        return "SUCCESS";
    }

}
