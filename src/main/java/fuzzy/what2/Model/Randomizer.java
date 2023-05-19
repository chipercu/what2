package fuzzy.what2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "randomizers")
public class Randomizer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "elements")
    private List<RandomizerElement> elementList;

    public Randomizer() {
    }

    public Randomizer(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
}
