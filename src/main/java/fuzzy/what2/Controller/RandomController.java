package fuzzy.what2.Controller;


import fuzzy.what2.Model.Element;
import fuzzy.what2.Model.Group;
import fuzzy.what2.Service.RandomService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class RandomController {

    private final RandomService service;

    public RandomController(RandomService service) {
        this.service = service;
    }

    @GetMapping("/getRandomByGroup")
    public Element getRandomByGroup(@RequestParam(required = false, defaultValue = "random") String name) {
        return service.getRandomElementByGroup(name);
    }

    @GetMapping("/addElement")
    public String addElement(@RequestParam String name, @RequestParam(required = false, defaultValue = "empty") String group) {
        service.addElement(name, group);
        return "save";
    }
    @GetMapping("/getAllGroups")
    public List<Group> getAllGroups(){
        return service.getAllGroups();
    }

    @GetMapping("/getAllElementsByGroup")
    public List<Element> getAllElementsByGroup(@RequestParam(required = false, defaultValue = "empty") String group) {
        return service.getAllElementsByGroup(group);
    }

    @GetMapping("/createGroup")
    public String createGroup(@RequestParam String name) {
        return service.createGroup(name);
    }
    @DeleteMapping("/deleteGroup")
    public String deleteGroup(@RequestParam(required = false) String name){
        return service.deleteGroup(name);
    }


}
