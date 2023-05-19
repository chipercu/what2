package fuzzy.what2.Controller;


import fuzzy.what2.Model.Element;
import fuzzy.what2.Model.Group;
import fuzzy.what2.Model.Randomizer;
import fuzzy.what2.Model.RandomizerElement;
import fuzzy.what2.Service.RandomService;
import fuzzy.what2.Service.RandomizerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class RandomController {

    private final RandomService service;
    private final RandomizerService randomizerService;

    public RandomController(RandomService service, RandomizerService randomizerService) {
        this.service = service;
        this.randomizerService = randomizerService;
    }


    @PostMapping("/addRandomizer")
    public Boolean addRandomizer(@RequestParam(required = false) Long userId, @RequestParam(required = false) String name){
        return randomizerService.addRandomizer(userId, name);
    }
    @GetMapping("/getAllRandomizer")
    public List<Randomizer> getAllRandomizer(@RequestParam(required = false) Long userId){
        return randomizerService.getAllRandomizer(userId);
    }
    @PostMapping("/addElementToRandomizer")
    public Boolean addElementToRandomizer(@RequestParam(required = false) Long userId,
                                          @RequestParam(required = false) String randomizer,
                                          @RequestParam(required = false) String name){
        return randomizerService.addElementToRandomizer(userId, randomizer, name);
    }
    @GetMapping("/getAllElementsFromRandomizer")
    public List<RandomizerElement> getAllElementsFromRandomizer(@RequestParam(required = false) Long userId,
                                                                 @RequestParam(required = false) String randomizer){
        return randomizerService.getAllElementsFromRandomizer(userId, randomizer);
    }
    @GetMapping("/getRandomElementFromRandomizer")
    public String getRandomElementFromRandomizer(@RequestParam(required = false) Long userId,
                                                 @RequestParam(required = false) String randomizer){
        return randomizerService.getRandomElementFromRandomizer(userId, randomizer);
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
