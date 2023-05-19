package fuzzy.what2.Repository;

import fuzzy.what2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    User getUserById(Long id);
    User getUserByLogin(String login);

}
