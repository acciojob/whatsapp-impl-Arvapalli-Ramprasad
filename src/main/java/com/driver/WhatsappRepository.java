package com.driver;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<String, User> userMap;
    private HashMap<String,Group> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private static int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.userMap = new HashMap<String,User>();
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<String,Group>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public HashMap<String, Group> getGroupUserMap() {
        return groupUserMap;
    }

    public void setGroupUserMap(HashMap<String, Group> groupUserMap) {
        this.groupUserMap = groupUserMap;
    }

    public HashMap<Group, List<Message>> getGroupMessageMap() {
        return groupMessageMap;
    }

    public void setGroupMessageMap(HashMap<Group, List<Message>> groupMessageMap) {
        this.groupMessageMap = groupMessageMap;
    }

    public HashMap<Message, User> getSenderMap() {
        return senderMap;
    }

    public void setSenderMap(HashMap<Message, User> senderMap) {
        this.senderMap = senderMap;
    }

    public HashMap<Group, User> getAdminMap() {
        return adminMap;
    }

    public void setAdminMap(HashMap<Group, User> adminMap) {
        this.adminMap = adminMap;
    }

    public HashSet<String> getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(HashSet<String> userMobile) {
        this.userMobile = userMobile;
    }

    public static int getCustomGroupCount() {
        return customGroupCount;
    }

    public void setCustomGroupCount(int customGroupCount) {
        this.customGroupCount = customGroupCount;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String createUser(String name, String mobile) throws Exception {
//        System.out.println(mobile);
        if(userMobile.contains(mobile)) {
            throw new Exception("User already exists");
        }
        User user = new User(name,mobile);
//        user.setMobile(mobile);
//        user.setName(name);
        userMap.put(mobile, user);
        userMobile.add(mobile);

        return "SUCCESS";
    }

    public Group createGroup(List<User> users) {
        Group group = new Group(users);
        if (users.size()==2) {
            User admin = users.get(0);
            group.setAdmin(admin);
            group.setName(users.get(1).getName());
        } else {
            customGroupCount++;
            group.setName("Group " + customGroupCount);
            User admin = users.get(0);
            group.setAdmin(admin);

        }
        groupUserMap.put(group.getName(), group);
        return group;
    }

    public int createMessage(String content) {
        messageId++;
        return messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
        if (!groupUserMap.containsValue(group)) {
            throw new Exception("Group does not exist");
        }
        if (!group.getName().contains((CharSequence) sender)) {
            throw new Exception("You are not allowed to send message");
        }
        List<Message> messages = groupMessageMap.getOrDefault(group, new ArrayList<>());
        messages.add(message);
        groupMessageMap.put(group, messages);
        return groupMessageMap.get(group).size();
    }

    public void changeAdmin(User approver, User user, Group group) throws Exception {
        if (!groupUserMap.containsValue(group)) {
            throw new Exception("Group does not exist");
        }
        if (!group.getAdmin().equals(approver)) {
            throw new Exception("Approver does not have rights");
        }
        if (!group.getParticipants().contains(user)) {
            throw new Exception("User is not a participant");
        }
        group.setAdmin(user);
    }

}