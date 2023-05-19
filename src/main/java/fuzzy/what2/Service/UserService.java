package fuzzy.what2.Service;

import fuzzy.what2.Model.User;
import fuzzy.what2.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserService {
    final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return usersRepository.findAll();
    }


}
