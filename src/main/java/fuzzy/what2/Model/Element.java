package fuzzy.what2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "elements")
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;


    public static Element emptyElement(){
        Element element = new Element();
        element.setName("null");
        element.setGroup(null);
        element.setImg("null");
        return element;
    }


}
