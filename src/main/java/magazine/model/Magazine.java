package magazine.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Double getDiscountedPrice() {
        return price - (price * 0.15);
    }
}
