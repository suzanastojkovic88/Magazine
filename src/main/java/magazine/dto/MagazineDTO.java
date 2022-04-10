package magazine.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MagazineDTO {

    private Long id;

    private String title;

    private Integer edition;

    private LocalDate dateOfPublication;

    private Double price;

    private Double discountedPrice;
}
