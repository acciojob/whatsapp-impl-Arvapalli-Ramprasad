package com.driver;

import java.util.*;

public class WhatsappRepository {
    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, Group> groups = new HashMap<>();
    private HashMap<Integer, Message> messages = new HashMap<>();
    private int customGroupCount = 0;

    public String createUser(String name, String mobile) {
        User user = new User(name, mobile);
        users.put(mobile, user);
        return "SUCCESS";
    }

    public boolean isUserExists(String mobile) {
        return users.containsKey(mobile);
    }

    public User getUserByMobile(String mobile) {
        return users.get(mobile);
    }

    public Group createGroup(String groupName, List<User> users) {
        Group group = new Group(groupName, users);
        groups.put(groupName, group);
        return group;
    }

    public Group getGroupByName(String groupName) {
        for (Group group : groups.values()) {
            if (group.getName().equals(groupName)) {
                return group;
            }
        }
        return null;
    }

    public int createMessage(String content) {
        int messageId = messages.size() + 1; // Generate message ID
        Message message = new Message(messageId, content, new Date());
        messages.put(messageId, message);
        return messageId;
    }

    public boolean isGroupExists(Group group) {
        return groups.containsValue(group);
    }

    public User getGroupAdmin(Group group) {
        // Retrieve admin logic here
        return null;
    }

    public void changeAdmin(User newAdmin, Group group) {
        // Change group admin logic here
    }

    public int getCustomGroupCount() {
        return customGroupCount;
    }
}
