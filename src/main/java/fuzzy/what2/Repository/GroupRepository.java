package fuzzy.what2.Repository;

import fuzzy.what2.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getGroupByName(String name);
    List<Group> getGroupsByName(String name);

    void deleteByName(String name);



}
