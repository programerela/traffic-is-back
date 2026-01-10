package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.model.Vozilo;
import rs.ac.np.police.trafficis.service.VoziloService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vozila")
@CrossOrigin(origins = "*")
@Tag(name = "Vozila", description = "API za upravljanje vozilima")
public class VoziloController {

    private final VoziloService voziloService;

    @Autowired
    public VoziloController(VoziloService voziloService) {
        this.voziloService = voziloService;
    }

    // GET /api/vozila - Dobijanje svih vozila
    @GetMapping
    public ResponseEntity<List<Vozilo>> getAllVozila() {
        List<Vozilo> vozila = voziloService.getAllVozila();
        return ResponseEntity.ok(vozila);
    }

    // GET /api/vozila/{id} - Dobijanje vozila po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<Vozilo> getVoziloById(@PathVariable Integer id) {
        Optional<Vozilo> vozilo = voziloService.getVoziloById(id);
        return vozilo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/vozila/registracija/{registracija} - Dobijanje vozila po registraciji
    @GetMapping("/registracija/{registracija}")
    public ResponseEntity<Vozilo> getVoziloByRegistracija(@PathVariable String registracija) {
        Optional<Vozilo> vozilo = voziloService.getVoziloByRegistracija(registracija);
        return vozilo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/vozila/marka/{marka} - Dobijanje vozila po marki
    @GetMapping("/marka/{marka}")
    public ResponseEntity<List<Vozilo>> getVozilaByMarka(@PathVariable String marka) {
        List<Vozilo> vozila = voziloService.getVozilaByMarka(marka);
        return ResponseEntity.ok(vozila);
    }

    // POST /api/vozila - Kreiranje novog vozila
    @PostMapping
    public ResponseEntity<?> createVozilo(@RequestBody Vozilo vozilo) {
        try {
            Vozilo createdVozilo = voziloService.createVozilo(vozilo);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVozilo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/vozila/{id} - Ažuriranje vozila
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVozilo(@PathVariable Integer id, @RequestBody Vozilo vozilo) {
        try {
            Vozilo updatedVozilo = voziloService.updateVozilo(id, vozilo);
            return ResponseEntity.ok(updatedVozilo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/vozila/{id} - Brisanje vozila
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVozilo(@PathVariable Integer id) {
        try {
            voziloService.deleteVozilo(id);
            return ResponseEntity.ok("Vozilo uspešno obrisano");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}