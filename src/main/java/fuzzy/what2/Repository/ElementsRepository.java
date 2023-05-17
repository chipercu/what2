package fuzzy.what2.Repository;

import fuzzy.what2.Model.Element;
import fuzzy.what2.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementsRepository extends JpaRepository<Element, Long> {

    Element getElementByGroup(Group group);

    List<Element> getElementsByGroup(Group group);




}
