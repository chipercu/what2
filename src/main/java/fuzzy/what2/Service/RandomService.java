package fuzzy.what2.Service;

import fuzzy.what2.Model.Element;
import fuzzy.what2.Model.Group;
import fuzzy.what2.Repository.ElementsRepository;
import fuzzy.what2.Repository.GroupRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomService {

    private final ElementsRepository elementsRepository;
    private final GroupRepository groupRepository;

    public RandomService(ElementsRepository repository, GroupRepository groupRepository) {
        this.elementsRepository = repository;
        this.groupRepository = groupRepository;
    }

    @PostConstruct
    public void init(){
        Group empty = groupRepository.getGroupByName("empty");
        if (empty == null){
            Group group = new Group();
            group.setName("empty");
            groupRepository.saveAndFlush(group);
        }
    }


    public Element getRandomElementByGroup(String name) {
        Group group;
        if (name.equals("random")){
            List<Group> all = groupRepository.findAll();
            int randomElementIndex = ThreadLocalRandom.current().nextInt(all.size());
            group = all.get(randomElementIndex);
        }else {
            group = groupRepository.getGroupByName(name);
        }
        List<Element> elementsByGroup = elementsRepository.getElementsByGroup(group);
        if (!elementsByGroup.isEmpty()){
            Element element = elementsByGroup.get(ThreadLocalRandom.current().nextInt(elementsByGroup.size()));
            if (element != null){
                return element;
            }else {
                return Element.emptyElement();
            }
        }else {
            return Element.emptyElement();
        }
    }




    public void addElement(String name, String groupName) {
        Element element = new Element();

        Group group;
        element.setName(name);
        if (groupName.equals("empty")){
            group = groupRepository.getGroupByName("empty");
        }else {
            group = groupRepository.getGroupByName(groupName);
            if (group == null){
                group = new Group();
                group.setName(groupName);
                groupRepository.saveAndFlush(group);
            }
        }
        element.setGroup(group);
        elementsRepository.saveAndFlush(element);
    }

    public List<Element> getAllElementsByGroup(String group) {
        List<Element> elements = new ArrayList<>();
        Group groupByName = groupRepository.getGroupByName(group);
        if (groupByName != null){
            elements = elementsRepository.getElementsByGroup(groupByName);
        }
       return elements;
    }

    public String createGroup(String name) {
        Group group = new Group();
        if (!name.isEmpty()){
            group.setName(name);
        }else {
            return "group not save, name is undefined";
        }
        groupRepository.saveAndFlush(group);
        return "save";
    }

    public String deleteGroup(String name) {
        if (name == null || name.isEmpty()){
            groupRepository.findAll().stream().filter(e -> e.getName().isEmpty()).forEach(e -> groupRepository.deleteById(e.getId()));
            return "all empty group deleting";
        }else {
            Group groupByName = groupRepository.getGroupByName(name);

            if (groupByName != null){
                groupRepository.delete(groupByName);
                return "group " + groupByName.getName() + " is deleting";
            }else {
                return "group by name " + name + " not exist";
            }

        }
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
