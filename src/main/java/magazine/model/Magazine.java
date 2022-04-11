package magazine.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name="magazines")
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer edition;

    private LocalDate dateOfPublication;

    private Double price;

    @Transient
    private Double discountedPrice;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Topic> topics = new ArrayList<>();

    public Double getDiscountedPrice() {
        return price - (price * 0.15);
    }
}
