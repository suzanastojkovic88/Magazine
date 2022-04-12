package magazine.controller;

import magazine.dto.MagazineDTO;
import magazine.service.MagazineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/magazines")
public class MagazineController {

    private final MagazineService magazineService;

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping
    public List<MagazineDTO> getMagazines() {
        return magazineService.findAllMagazines();

    }

    @GetMapping(path = "/{id}")
    public MagazineDTO getMagazine(@PathVariable("id") Long id) {

        return magazineService.findMagazine(id);
    }

    @PostMapping
    public MagazineDTO addMagazine(@RequestBody MagazineDTO magazineDTO) {
        return magazineService.saveMagazine(magazineDTO);

    }

    @PutMapping(path = "/{id}")
    public MagazineDTO updateMagazine(@PathVariable Long id, @RequestBody MagazineDTO magazineDTO) {
        magazineDTO.setId(id);
        return magazineService.saveMagazine(magazineDTO);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteMagazine(@PathVariable("id") Long id) {
        magazineService.deleteMagazine(id);
        return "Magazine is successfully deleted.";
    }
}
