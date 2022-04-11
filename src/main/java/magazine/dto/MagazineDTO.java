package magazine.dto;

import lombok.Getter;
import lombok.Setter;
import magazine.model.Topic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MagazineDTO {

    private Long id;

    private String title;

    private Integer edition;

    private LocalDate dateOfPublication;

    private Double price;

    private Double discountedPrice;

    private List<Topic> topics = new ArrayList<>();
}
