package fuzzy.what2.Controller;

import fuzzy.what2.Model.User;
import fuzzy.what2.Model.UsersElement;
import fuzzy.what2.Model.UsersGroup;
import fuzzy.what2.Service.UsersRandomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRandomController {
    final UsersRandomService service;

    public UserRandomController(UsersRandomService usersRandomService) {
        this.service = usersRandomService;
    }

    @GetMapping("/getMyGroups")
    public List<UsersGroup> getMyGroups(){
        return service.getMyGroups();
    }
    @PostMapping("/createNewGroup")
    public String createNewGroup(@RequestParam Long userId, @RequestParam String groupName){
        return service.createNewGroup(userId, groupName);
    }

    @PostMapping("/addElementToUserGroup")
    public String addElementToUserGroup(@RequestParam Long userId, @RequestParam String groupName, @RequestParam String elementName){
        service.addElementToUserGroup(userId, groupName, elementName);
        return service.checkElementFromGroup(userId, groupName, elementName);
    }


    @PostMapping("/createUser")
    public String createUser(User User){
        return service.createUser(User);
    }
    @DeleteMapping("/deleteUserById")
    public String deleteUserById(@RequestParam Long userId){
        return service.deleteUserById(userId);
    }
    @GetMapping("/getAllElementsFromGroup")
    public List<UsersElement> getAllElementsFromGroup(@RequestParam Long userId, @RequestParam String groupName){
        return service.getAllElementsFromGroup(userId, groupName);
    }

}
