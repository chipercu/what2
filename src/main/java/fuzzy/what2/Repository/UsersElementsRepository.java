package fuzzy.what2.Repository;

import fuzzy.what2.Model.UsersElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersElementsRepository extends JpaRepository<UsersElements, Long> {
}
