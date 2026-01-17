package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.response.VozacResponseDTO;
import rs.ac.np.police.trafficis.mapper.DTOMapper;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.service.VozacService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vozaci")
@CrossOrigin(origins = "*")
@Tag(name = "Vozači", description = "API za upravljanje vozačima")
public class VozacController {

    private final VozacService vozacService;

    @Autowired
    public VozacController(VozacService vozacService) {
        this.vozacService = vozacService;
    }

    // GET /api/vozaci - Dobijanje svih vozača
    @Operation(summary = "Dobijanje svih vozača", description = "Vraća listu svih registrovanih vozača")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Uspešno vraćena lista vozača")
    })
    @GetMapping
    public ResponseEntity<List<VozacResponseDTO>> getAllVozaci() {
        List<Vozac> vozaci = vozacService.getAllVozaci();
        List<VozacResponseDTO> vozaciDTO = DTOMapper.toVozacDTOList(vozaci);
        return ResponseEntity.ok(vozaciDTO);
    }

    // GET /api/vozaci/{id} - Dobijanje vozača po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<VozacResponseDTO> getVozacById(@PathVariable Integer id) {
        Optional<Vozac> vozac = vozacService.getVozacById(id);
        return vozac.map(v -> ResponseEntity.ok(DTOMapper.toVozacDTO(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/vozaci/jmbg/{jmbg} - Dobijanje vozača po JMBG
    @GetMapping("/jmbg/{jmbg}")
    public ResponseEntity<VozacResponseDTO> getVozacByJmbg(@PathVariable String jmbg) {
        Optional<Vozac> vozac = vozacService.getVozacByJmbg(jmbg);
        return vozac.map(v -> ResponseEntity.ok(DTOMapper.toVozacDTO(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/vozaci/vozacka/{brojVozacke} - Dobijanje vozača po broju vozačke
    @GetMapping("/vozacka/{brojVozacke}")
    public ResponseEntity<VozacResponseDTO> getVozacByBrojVozacke(@PathVariable String brojVozacke) {
        Optional<Vozac> vozac = vozacService.getVozacByBrojVozacke(brojVozacke);
        return vozac.map(v -> ResponseEntity.ok(DTOMapper.toVozacDTO(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/vozaci - Kreiranje novog vozača
    @Operation(summary = "Kreiranje novog vozača", description = "Kreira novog vozača u sistemu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vozač uspešno kreiran"),
            @ApiResponse(responseCode = "400", description = "Nevažeći podaci ili vozač već postoji")
    })
    @PostMapping
    public ResponseEntity<?> createVozac(@RequestBody Vozac vozac) {
        try {
            Vozac createdVozac = vozacService.createVozac(vozac);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVozac);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/vozaci/{id} - Ažuriranje vozača
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVozac(@PathVariable Integer id, @RequestBody Vozac vozac) {
        try {
            Vozac updatedVozac = vozacService.updateVozac(id, vozac);
            VozacResponseDTO responseDTO = DTOMapper.toVozacDTO(updatedVozac);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/vozaci/{id} - Brisanje vozača
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVozac(@PathVariable Integer id) {
        try {
            vozacService.deleteVozac(id);
            return ResponseEntity.ok("Vozač uspešno obrisan");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}