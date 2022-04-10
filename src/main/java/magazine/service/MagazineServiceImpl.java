package magazine.service;

import magazine.model.Magazine;
import magazine.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MagazineServiceImpl implements MagazineService {

    private final MagazineRepository magazineRepository;

    @Autowired
    public MagazineServiceImpl(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @Override
    public List<Magazine> findAllMagazines() {

        return magazineRepository.findAll();
    }

    @Override
    public Magazine findMagazine(Long id) {
        boolean exists = magazineRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Magazine with id " + id + " does not exist.");
        }
        Magazine magazine = magazineRepository.getById(id);
        return magazine;
    }

    @Override
    public Magazine saveMagazine(Magazine magazine) {
        Optional<Magazine> m = magazineRepository
                .findMagazineByTitle(magazine.getTitle());
        if(m.isPresent()){
            throw new IllegalStateException("Magazine is already added.");
        }
        return magazineRepository.save(magazine);
    }

    @Transactional
    public Magazine updateMagazine(Long id, String date, Double price) {
    Magazine magazine = magazineRepository.findById(id)
            .orElseThrow(()-> new IllegalStateException(
                    "Magazine with id " + id + " does not exist."));
    if(date != null){
        magazine.setDateOfPublication(LocalDate.parse(date));
    }
    if(price != null){
        magazine.setPrice(price);
    }
        return magazine;
    }

    @Override
    public void deleteMagazine(Long id) {
        boolean exist = magazineRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Magazine with id " + id + " does not exist.");
        }
    magazineRepository.deleteById(id);
    }
}
