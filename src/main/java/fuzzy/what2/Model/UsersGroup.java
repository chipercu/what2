package fuzzy.what2.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users_group")
public class UsersGroup {

    public UsersGroup(String name, User user) {
        this.name = name;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UsersElement> elements;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


    public void addElement(UsersElement element){
        if (!getElements().contains(element)){
            getElements().add(element);
        }
    }



}
