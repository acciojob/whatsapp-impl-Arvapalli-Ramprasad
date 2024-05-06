package com.driver;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("whatsapp")
public class WhatsappController {

    WhatsappService whatsappService = new WhatsappService();

    @PostMapping("/add-user")
    public String createUser(@RequestParam String name, @RequestParam String mobile) throws Exception {
        return whatsappService.createUser(name, mobile);
    }

    @PostMapping("/add-group")
    public Group createGroup(@RequestBody List<User> users) {
        return whatsappService.createGroup(users);
    }

    @PostMapping("/add-message")
    public int createMessage(@RequestParam String content) {
        return whatsappService.createMessage(content);
    }

//    @PutMapping("/send-message")
//    public int sendMessage(@RequestBody Message message, @RequestParam String senderMobile, @RequestParam String groupName) throws Exception {
//        User sender = whatsappService.findUserByMobile(senderMobile);
//        Group group = whatsappService.findGroupByName(groupName);
//        return whatsappService.sendMessage(message, sender, group);
//    }

    @PutMapping("/change-admin")
    public String changeAdmin(@RequestParam String approverMobile, @RequestParam String userMobile, @RequestParam String groupName) throws Exception {
        User approver = whatsappService.findUserByMobile(approverMobile);
        User user = whatsappService.findUserByMobile(userMobile);
        Group group = whatsappService.findGroupByName(groupName);
        return whatsappService.changeAdmin(approver, user, group);
    }
}
