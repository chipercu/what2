package fuzzy.what2.Repository;

import fuzzy.what2.Model.UsersElement;
import fuzzy.what2.Model.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersElementsRepository extends JpaRepository<UsersElement, Long> {

    List<UsersElement> getUsersElementByGroup(UsersGroup group);

}
