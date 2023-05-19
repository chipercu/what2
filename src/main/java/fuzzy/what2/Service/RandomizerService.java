package fuzzy.what2.Service;

import fuzzy.what2.Model.Randomizer;
import fuzzy.what2.Model.RandomizerElement;
import fuzzy.what2.Model.User;
import fuzzy.what2.Model.User;
import fuzzy.what2.Repository.RandomizersRepository;
import fuzzy.what2.Repository.RndElementsRepository;
import fuzzy.what2.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomizerService {
    final UsersRepository usersRepository;
    final RandomizersRepository randomizersRepository;
    final RndElementsRepository elementsRepository;


    public RandomizerService(UsersRepository usersRepository, RandomizersRepository randomizersRepository, RndElementsRepository elementsRepository) {
        this.usersRepository = usersRepository;
        this.randomizersRepository = randomizersRepository;
        this.elementsRepository = elementsRepository;
    }


    public List<Randomizer> getAllRandomizer(Long userId){
        List<Randomizer> list = new ArrayList<>();
        if (checkNull(userId)){
            Optional<User> userFromDB = getUserFromDB(userId);
            if (userFromDB.isPresent()){
                return randomizersRepository.findAllByOwner(userFromDB.get());
            }
        }
        return list;
    }

    public List<RandomizerElement> getAllElementsFromRandomizer(Long userId, String randomizer){
        List<RandomizerElement> list = new ArrayList<>();
        if (checkNull(userId, randomizer)){
            if (checkIfExistUserAnRandomizer(userId, randomizer)){
                return elementsRepository.findAllByRandomizer(getRandomizerFromDB(randomizer, getUserFromDB(userId).get()).get());
            }
        }
        return list;
    }

    public String getRandomElementFromRandomizer(Long userId, String randomizer){
        if (checkNull(userId, randomizer) && checkEmpty(randomizer)){
            if (checkIfExistUserAnRandomizer(userId, randomizer)){
                List<RandomizerElement> all = elementsRepository.findAllByRandomizer(getRandomizerFromDB(randomizer, getUserFromDB(userId).get()).get());
                if (!all.isEmpty()){
                    return randomizer + " : " + all.get(ThreadLocalRandom.current().nextInt(all.size())).getName();
                }
            }
        }
        return "empty";
    }


    public Boolean addElementToRandomizer(Long userId, String randomizer, String name){
        if (checkNull(userId, randomizer, name) && checkEmpty(randomizer, name)){
            Optional<User> user = getUserFromDB(userId);
            if (user.isPresent()){
                Optional<Randomizer> randomizerFromDB = getRandomizerFromDB(randomizer, user.get());
                if (randomizerFromDB.isPresent()){
                    RandomizerElement element = elementsRepository.findByRandomizerAndName(randomizerFromDB.get(), name);
                    if (element != null){
                        return false;
                    }else {
                        elementsRepository.saveAndFlush(new RandomizerElement(name, "image", getRandomizerFromDB(randomizer, getUserFromDB(userId).get()).get()));
                        return getRandomizerElementFromDB(randomizerFromDB.get(), name).isPresent();
                    }
                }
            }
        }
        return false;
    }

    private Boolean checkIfExistUserAnRandomizer(Long userId, String randomizer){
        return getUserFromDB(userId)
                .filter(user -> getRandomizerFromDB(randomizer, user).isPresent())
                .isPresent();
    }

    public Boolean addRandomizer(Long userId, String name){
        if (checkNull(userId, name) && checkEmpty(name)){
            Optional<User> userFromDB = getUserFromDB(userId);
            if (userFromDB.isPresent()){
                User owner = userFromDB.get();
                Optional<Randomizer> randomizerFromDB = getRandomizerFromDB(name, owner);
                if (randomizerFromDB.isEmpty()){
                    Randomizer randomizer = new Randomizer(name, owner);
                    randomizersRepository.saveAndFlush(randomizer);
                    return getRandomizerFromDB(name, owner).isPresent();
                }
            }
        }
        return false;
    }


    private Optional<RandomizerElement> getRandomizerElementFromDB(Randomizer randomizer, String name){
        RandomizerElement element = elementsRepository.findByRandomizerAndName(randomizer, name);
        if (element != null){
            return Optional.of(element);
        }
        return Optional.empty();

    }

    private Optional<Randomizer> getRandomizerFromDB(String name, User owner){
        Randomizer randomizerByOwner = randomizersRepository.findRandomizerByNameAndOwner(name, owner);
        if (randomizerByOwner != null){
            return Optional.of(randomizerByOwner);
        }
        return Optional.empty();
    }


    private Optional<User> getUserFromDB(Long userId){
        User User = usersRepository.getUserById(userId);
        if (User != null){
            return Optional.of(User);
        }
        return Optional.empty();
    }
    private Optional<User> getUserFromDB(String login){
        User User = usersRepository.getUserByLogin(login);
        if (User != null){
            return Optional.of(User);
        }
        return Optional.empty();
    }


    private Boolean checkEmpty(String... s){
        for (String o: s){
            if (o.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private Boolean checkNull(Object... obj){
        for (Object o: obj){
            if (o == null){
                return false;
            }
        }
        return true;
    }


}
