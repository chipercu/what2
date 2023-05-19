package fuzzy.what2.Model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rnd_elements")
public class RandomizerElement {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "randomizers_id")
    private Randomizer randomizer;

    public RandomizerElement() {
    }

    public RandomizerElement(String name, String image, Randomizer randomizer) {
        this.name = name;
        this.image = image;
        this.randomizer = randomizer;
    }
}
