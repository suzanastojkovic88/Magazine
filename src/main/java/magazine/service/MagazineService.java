package magazine.service;

import magazine.model.Magazine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MagazineService {

    public List<Magazine> findAllMagazines();

    public Magazine findMagazine(Long id);

    public Magazine saveMagazine(Magazine magazine);

    public Magazine updateMagazine(Long id, String date, Double price);

    public void deleteMagazine(Long id);
}
