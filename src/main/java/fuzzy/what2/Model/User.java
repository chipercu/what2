package fuzzy.what2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private Boolean isActive;

    @OneToMany()
    private List<UsersGroup> individualGroup;


    public UsersGroup createNewGroup(String groupName){
        return new UsersGroup(groupName, this);
    }



}
