package fuzzy.what2.Service;

import fuzzy.what2.Model.User;
import fuzzy.what2.Model.UsersElement;
import fuzzy.what2.Model.UsersGroup;
import fuzzy.what2.Repository.UsersElementsRepository;
import fuzzy.what2.Repository.UsersGroupRepository;
import fuzzy.what2.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersRandomService {
    final UsersElementsRepository elementsRepository;
    final UsersGroupRepository groupRepository;
    final UsersRepository usersRepository;


    public UsersRandomService(UsersElementsRepository elementsRepository, UsersGroupRepository groupRepository, UsersRepository usersRepository) {
        this.elementsRepository = elementsRepository;
        this.groupRepository = groupRepository;
        this.usersRepository = usersRepository;
    }

    private Boolean checkEmpty(String... s){
        for (String o: s){
            if (o.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private Boolean checkNull(Object... obj){
        for (Object o: obj){
            if (o == null){
                return false;
            }
        }
        return true;
    }

    private Boolean checkUser(Long userId){
        if (userId != null){
            return usersRepository.getUserById(userId) != null;
        }
        return false;
    }

    private Optional<User> getUserFromDB(Long userId){
        User user = usersRepository.getUserById(userId);
        if (user != null){
            return Optional.of(user);
        }
        return Optional.empty();
    }
    private Optional<User> getUserFromDB(String login){
        User user = usersRepository.getUserByLogin(login);
        if (user != null){
            return Optional.of(user);
        }
        return Optional.empty();
    }



    public String createNewGroup(Long userId, String name){
        if (checkNull(userId, name) && !name.isEmpty()){
            Optional<User> userFromDB = getUserFromDB(userId);
            if (userFromDB.isPresent()){
                groupRepository.saveAndFlush(userFromDB.get().createNewGroup(name));
                return "Пользователь " + userFromDB.get().getLogin() + " добавил группу " + name;
            }else {
                return "Ошибка добавления новой группы, пользователь c ID: " + userId + " не был найден";
            }
        }else {
            return "Ошибка добавления новой группы, не указан пользователь или имя новой группы";
        }
    }

    public void addElementToUserGroup(Long userId, String groupName, String elementName){
        if (checkNull(userId, groupName, elementName) && checkEmpty(groupName, elementName)){
             getUserFromDB(userId)
                     .flatMap(user -> groupRepository.getUsersGroupByNameAndUser(groupName, user))
                     .ifPresent(group -> {
                         elementsRepository.saveAndFlush(new UsersElement(elementName, group));
                     });
        }
    }

    public UsersElement getElementFromGroup(Long userId, String groupName, String elementName){
        if (checkNull(userId, groupName) && checkEmpty(groupName)){
            Optional<User> userFromDB = getUserFromDB(userId);
            if (userFromDB.isPresent()){
                Optional<UsersGroup> usersGroupByNameAndUser = groupRepository.getUsersGroupByNameAndUser(groupName, userFromDB.get());
                if (usersGroupByNameAndUser.isPresent()){
                    return usersGroupByNameAndUser.get().getElements().stream()
                            .filter(element -> element.getName().equals(elementName))
                            .findFirst().orElse(null);
                }
            }

        }
        return null;
    }
    public String checkElementFromGroup(Long userId, String groupName, String elementName){
        UsersElement element = getElementFromGroup(userId, groupName, elementName);
        return element != null ? element.toString() : "Элемент не найден";
    }

    public List<UsersElement> getAllElementsFromGroup(Long userId, String groupName){
        List<UsersElement> list = new ArrayList<>();
        if (checkNull(userId, groupName) && checkEmpty(groupName)){
            Optional<User> userFromDB = getUserFromDB(userId);
            if (userFromDB.isPresent()){
                Optional<UsersGroup> usersGroupByNameAndUser = groupRepository.getUsersGroupByNameAndUser(groupName, userFromDB.get());
                usersGroupByNameAndUser.ifPresent(group -> list.addAll(elementsRepository.getUsersElementByGroup(group)));
            }
        }
        return list;
    }




    public String deleteUserById(Long userId){
        Optional<User> userFromDB = getUserFromDB(userId);
        if (userFromDB.isPresent()){
            usersRepository.delete(userFromDB.get());
            return "Пользователь " + userFromDB.get().getLogin() + " был удален";
        }else {
            return "Пользователь с ID:" + userId + " не был найден";
        }
    }

    public String createUser(User user){
        Optional<User> userFromDB = getUserFromDB(user.getLogin());
        if (userFromDB.isPresent()){
            return "Пользователь " + userFromDB.get().getLogin() + " уже существует";
        }else {
            if (checkNull(user.getLogin(), user.getPassword()) && checkEmpty(user.getLogin(), user.getPassword())){
                User user1 = usersRepository.saveAndFlush(user);
                return "Пользователь " + user1.getLogin() + " был успешно зарегистрирован";
            }else {
                return "Пользователь не был зарегистрирован, пустой логин или пароль";
            }
        }
    }


    public List<UsersGroup> getMyGroups() {
        final List<UsersGroup> groupList = new ArrayList<>();
        List<UsersGroup> all = groupRepository.findAll();
        if (!all.isEmpty()){
            groupList.addAll(all);
        }
       return groupList;
    }
}
