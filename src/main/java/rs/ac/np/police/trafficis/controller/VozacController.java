package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping
    public ResponseEntity<List<Vozac>> getAllVozaci() {
        List<Vozac> vozaci = vozacService.getAllVozaci();
        return ResponseEntity.ok(vozaci);
    }

    // GET /api/vozaci/{id} - Dobijanje vozača po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<Vozac> getVozacById(@PathVariable Integer id) {
        Optional<Vozac> vozac = vozacService.getVozacById(id);
        return vozac.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/vozaci/jmbg/{jmbg} - Dobijanje vozača po JMBG
    @GetMapping("/jmbg/{jmbg}")
    public ResponseEntity<Vozac> getVozacByJmbg(@PathVariable String jmbg) {
        Optional<Vozac> vozac = vozacService.getVozacByJmbg(jmbg);
        return vozac.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/vozaci/vozacka/{brojVozacke} - Dobijanje vozača po broju vozačke
    @GetMapping("/vozacka/{brojVozacke}")
    public ResponseEntity<Vozac> getVozacByBrojVozacke(@PathVariable String brojVozacke) {
        Optional<Vozac> vozac = vozacService.getVozacByBrojVozacke(brojVozacke);
        return vozac.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/vozaci - Kreiranje novog vozača
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
            return ResponseEntity.ok(updatedVozac);
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