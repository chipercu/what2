package fuzzy.what2.Service;

import fuzzy.what2.Model.UsersGroup;
import fuzzy.what2.Repository.UsersElementsRepository;
import fuzzy.what2.Repository.UsersGroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersRandomService {
    final UsersElementsRepository elementsRepository;
    final UsersGroupRepository groupRepository;


    public UsersRandomService(UsersElementsRepository elementsRepository, UsersGroupRepository groupRepository) {
        this.elementsRepository = elementsRepository;
        this.groupRepository = groupRepository;
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
