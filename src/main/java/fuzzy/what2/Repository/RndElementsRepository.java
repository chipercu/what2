package fuzzy.what2.Repository;

import fuzzy.what2.Model.Randomizer;
import fuzzy.what2.Model.RandomizerElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RndElementsRepository extends JpaRepository<RandomizerElement, Long> {
    RandomizerElement findRandomizerElementByName(String name);
    RandomizerElement findRandomizerElementByRandomizer(Randomizer randomizer);
    List<RandomizerElement> findAllByRandomizer(Randomizer randomizer);
    RandomizerElement findByRandomizerAndName(Randomizer randomizer, String name);
}
