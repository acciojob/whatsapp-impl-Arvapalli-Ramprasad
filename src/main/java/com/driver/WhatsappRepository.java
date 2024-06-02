package com.driver;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<String, User> userMap;
    private HashMap<Group,List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private static int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.userMap = new HashMap<String,User>();
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group,List<User>>();
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

    public HashMap<Group,List<User>> getGroupUserMap() {
        return groupUserMap;
    }

    public void setGroupUserMap(HashMap<Group,List<User>> groupUserMap) {
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
//        if (users == null || users.isEmpty()) {
//            throw new IllegalArgumentException("User list cannot be null or empty");
//        }
//
//        // Validate minimum number of users required
//        if (users.size() < 2) {
//            throw new IllegalArgumentException("At least two users are required to create a group");
//        }
//        Group group = new Group(users);
//        User admin = users.get(0);
//        if (users.size()==2) {
//            group.setAdmin(admin);
//            group.setName(users.get(1).getName());
//        } else {
//            customGroupCount++;
//            group.setName("Group " + customGroupCount);
//            group.setAdmin(admin);
//
//        }
//        groupUserMap.put(group.getName(), group);
//        return group;

//        return null;


        if(users.size()==2){
            // The list contains at least 2 users where the first user is the admin.
            // If there are only 2 users, the group is a personal chat and the group name should be kept as the name of the second user(other than admin)
            // If there are 2+ users, the name of group should be "Group #count". For example, the name of first group would be "Group 1", second would be "Group 2" and so on.
            // Note that a personal chat is not considered a group and the count is not updated for personal chats.
            User admin = users.get(0);
            String name = users.get(1).getName();
            Group group = new Group(name,users.size());
            groupUserMap.put(group,users);
            return group;
        }
        customGroupCount++;
        User admin = users.get(0);
        String name = "Group "+customGroupCount;
        Group group = new Group(name,users.size());
        groupUserMap.put(group,users);
        return group;
    }

    public int createMessage(String content) {


        messageId++;
        return messageId;

//        return 0;
    }

//    public int sendMessage(Message message, User sender, Group group) throws Exception {
//        if (!groupUserMap.containsValue(group)) {
//            throw new Exception("Group does not exist");
//        }
//        if (!group.getName().contains(sender.getName())){
//            throw new Exception("You are not allowed to send message");
//        }
//        List<Message> messages = groupMessageMap.getOrDefault(group, new ArrayList<>());
//        messages.add(message);
//        groupMessageMap.put(group, messages);
//        return groupMessageMap.get(group).size();
//    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
        // Check if the group exists in the groupUserMap
        if (!groupUserMap.containsValue(group)) {
            throw new Exception("Group does not exist");
        }


        // Verify that the sender is a member of the specified group
        if (groupUserMap.containsKey(group) && !groupUserMap.containsValue(sender)) {
            throw new Exception("You are not allowed to send message");
        }


        // Retrieve the list of messages for the specified group or initialize an empty list
        List<Message> messages = groupMessageMap.getOrDefault(group, new ArrayList<>());


        // Add the new message to the list of messages for the group
        messages.add(message);


        // Update the groupMessageMap with the updated list of messages for the group
        groupMessageMap.put(group, messages);


        // Return the size of the list of messages for the group
        return messages.size();

//        return 0;
    }

    // Method to check if a user is a member of a group
//    private boolean isUserMemberOfGroup(User user, Group group) {
//
//
//        List<User> members = group.getParticipants();
//        return members != null && members.contains(user);
//
////        return false;
//    }

//    public void changeAdmin(User approver, User user, Group group) throws Exception {
//        if (!groupUserMap.containsValue(group)) {
//            throw new Exception("Group does not exist");
//        }
//        if (!group.getAdmin().equals(approver)) {
//            throw new Exception("Approver does not have rights");
//        }
//        if (!group.getParticipants().contains(user)) {
//            throw new Exception("User is not a participant");
//        }
//        group.setAdmin(user);
//    }

}