package fuzzy.what2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_elements")


public class UsersElements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

//    private UsersGroup group;

    public static Element emptyElement(){
        Element element = new Element();
        element.setName("null");
        element.setGroup(null);
        element.setImg("null");
        return element;
    }


}
