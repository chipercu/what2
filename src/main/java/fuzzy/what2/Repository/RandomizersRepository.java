package fuzzy.what2.Repository;

import fuzzy.what2.Model.Randomizer;
import fuzzy.what2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RandomizersRepository extends JpaRepository<Randomizer, Long> {
//    Randomizer findRandomizerByName(String name);
    Randomizer findRandomizerByNameAndOwner(String name, User owner);
    List<Randomizer> findAllByOwner(User owner);
}
