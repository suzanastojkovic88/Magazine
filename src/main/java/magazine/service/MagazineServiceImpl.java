package magazine.service;

import lombok.RequiredArgsConstructor;
import magazine.dto.MagazineDTO;
import magazine.model.Magazine;
import magazine.repository.MagazineRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MagazineServiceImpl implements MagazineService {

    private final ModelMapper modelMapper;

    private final MagazineRepository magazineRepository;


    @Override
    public List<MagazineDTO> findAllMagazines() {
        return magazineRepository.findAll().stream()
                .map(magazine -> modelMapper.map(magazine, MagazineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MagazineDTO findMagazine(Long id) {
        Magazine magazine = magazineRepository.findMagazineById(id)
                .orElseThrow(()->new RuntimeException("Magazine with id "+ id + " does not exist."));
        return modelMapper.map(magazine, MagazineDTO.class);
    }

    @Override
    public MagazineDTO saveMagazine(MagazineDTO magazineDTO) {
        Magazine magazine = modelMapper.map(magazineDTO, Magazine.class);
        Magazine savedMagazine = magazineRepository.save(magazine);
        return modelMapper.map(savedMagazine, MagazineDTO.class);
    }

    @Override
    public void deleteMagazine(Long id) {
        boolean exist = magazineRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Magazine with id " + id + " does not exist.");
        }
    magazineRepository.deleteById(id);
    }

    private MagazineDTO toDTO(Magazine magazine){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        MagazineDTO magazineDTO = new MagazineDTO();
        magazineDTO = modelMapper.map(magazine, MagazineDTO.class);
        return magazineDTO;
    }
    private Magazine toMagazine(MagazineDTO magazineDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Magazine magazine = new Magazine();
        magazine = modelMapper.map(magazineDTO, Magazine.class);
        return magazine;
    }
}
