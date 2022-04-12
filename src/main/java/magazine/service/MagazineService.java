package magazine.service;
import magazine.dto.MagazineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MagazineService {

    List<MagazineDTO> findAllMagazines();

    MagazineDTO findMagazine(Long id);

    MagazineDTO saveMagazine(MagazineDTO magazineDTO);

    void deleteMagazine(Long id);
}
