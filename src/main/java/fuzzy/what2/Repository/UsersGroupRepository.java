package fuzzy.what2.Repository;

import fuzzy.what2.Model.UsersGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersGroupRepository extends JpaRepository<UsersGroup, Long> {
}
