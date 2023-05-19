package fuzzy.what2.Repository;

import fuzzy.what2.Model.User;
import fuzzy.what2.Model.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersGroupRepository extends JpaRepository<UsersGroup, Long> {

    Optional<UsersGroup> getUsersGroupByNameAndUser(String name, User User);


}
