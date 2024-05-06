package com.driver;
import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("whatsapp")
public class WhatsappController {
    private WhatsappService whatsappService = new WhatsappService();

    @PostMapping("/add-users")
    public String createUser(@RequestParam String name, @RequestParam String mobile) throws Exception {
        return whatsappService.createUser(name, mobile);
    }

    @PostMapping("/add-groups")
    public Group createGroup(@RequestBody List<User> users) {
        return whatsappService.createGroup(users);
    }

    @PostMapping("/add-messages")
    public int createMessage(@RequestParam String content) {
        return whatsappService.createMessage(content);
    }

    @PutMapping("/send-messages")
    public int sendMessage(@RequestBody Message message, @RequestBody User sender, @RequestBody Group group) throws Exception {
        return whatsappService.sendMessage(message, sender, group);
    }

    @PutMapping("/change-admins")
    public String changeAdmin(@RequestBody User approver, @RequestBody User user, @RequestBody Group group) throws Exception {
        return whatsappService.changeAdmin(approver, user, group);
    }

    // Implement other endpoints as needed based on requirements

    // Bonus endpoint (non-scorable)
    @DeleteMapping("/remove-userss")
    public int removeUser(@RequestBody User user) throws Exception {
        // Implement logic to remove user from group
        throw new UnsupportedOperationException("Not implemented");
    }

    // Bonus endpoint (non-scorable)
    @GetMapping("/find-messagess")
    public String findMessage(@RequestParam Date start, @RequestParam Date end, @RequestParam int K) throws Exception {
        // Implement logic to find messages within a given date range
        throw new UnsupportedOperationException("Not implemented");
    }
}
