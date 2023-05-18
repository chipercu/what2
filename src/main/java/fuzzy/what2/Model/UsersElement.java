package fuzzy.what2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_elements")


public class UsersElement {
    public UsersElement() {
    }

    public UsersElement(String name, UsersGroup group) {
        this.name = name;
        this.group = group;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

    @ManyToOne(fetch = FetchType.EAGER)
    private UsersGroup group;

//    private UsersGroup group;

    public static Element emptyElement(){
        Element element = new Element();
        element.setName("null");
        element.setGroup(null);
        element.setImg("null");
        return element;
    }


}
