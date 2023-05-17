package fuzzy.what2.Controller;

import fuzzy.what2.Model.UsersGroup;
import fuzzy.what2.Service.UsersRandomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
