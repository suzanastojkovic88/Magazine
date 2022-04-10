package magazine.controller;

import magazine.dto.MagazineDTO;
import magazine.model.Magazine;
import magazine.service.MagazineService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/magazines")
public class MagazineController {

    @Autowired
    private ModelMapper modelMapper;

    private final MagazineService magazineService;

    @Autowired
    public MagazineController(MagazineService magazineService) {

        this.magazineService = magazineService;
    }

    @GetMapping
    public List<MagazineDTO> getMagazines() {
        return magazineService.findAllMagazines()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public MagazineDTO getMagazine(@PathVariable("id") Long id) {
        Magazine magazine = magazineService.findMagazine(id);
        return toDTO(magazine);
    }

    @PostMapping
    public MagazineDTO addMagazine(@RequestBody MagazineDTO magazineDTO){
        Magazine magazine = magazineService.saveMagazine(toMagazine(magazineDTO));
        return toDTO(magazine);
    }

    @PutMapping(path = "{id}")
    public MagazineDTO updateMagazine(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Double price){

        Magazine magazine = magazineService.updateMagazine(id, date, price);
        return toDTO(magazine);
    }

    @DeleteMapping(path = "{id}")
    public String deleteMagazine(
            @PathVariable("id") Long id){

        magazineService.deleteMagazine(id);
        return "Magazine is successfully deleted.";
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
