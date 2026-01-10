package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.model.Kazna;
import rs.ac.np.police.trafficis.service.KaznaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kazne")
@CrossOrigin(origins = "*")
@Tag(name = "Kazne", description = "API za upravljanje kaznama i prekršajima")
public class KaznaController {

    private final KaznaService kaznaService;

    @Autowired
    public KaznaController(KaznaService kaznaService) {
        this.kaznaService = kaznaService;
    }

    // GET /api/kazne - Dobijanje svih kazni
    @GetMapping
    public ResponseEntity<List<Kazna>> getAllKazne() {
        List<Kazna> kazne = kaznaService.getAllKazne();
        return ResponseEntity.ok(kazne);
    }

    // GET /api/kazne/{id} - Dobijanje kazne po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<Kazna> getKaznaById(@PathVariable Integer id) {
        Optional<Kazna> kazna = kaznaService.getKaznaById(id);
        return kazna.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/kazne/status/{status} - Dobijanje kazni po statusu plaćanja
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Kazna>> getKazneByStatus(@PathVariable String status) {
        List<Kazna> kazne = kaznaService.getKazneByStatusPlacanja(status);
        return ResponseEntity.ok(kazne);
    }

    // GET /api/kazne/vrsta/{vrsta} - Dobijanje kazni po vrsti prekršaja
    @GetMapping("/vrsta/{vrsta}")
    public ResponseEntity<List<Kazna>> getKazneByVrsta(@PathVariable String vrsta) {
        List<Kazna> kazne = kaznaService.getKazneByVrstaPrekrsaja(vrsta);
        return ResponseEntity.ok(kazne);
    }

    // POST /api/kazne - Kreiranje nove kazne
    @PostMapping
    public ResponseEntity<?> createKazna(@RequestBody Kazna kazna) {
        try {
            Kazna createdKazna = kaznaService.createKazna(kazna);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdKazna);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/kazne/{id} - Ažuriranje kazne
    @PutMapping("/{id}")
    public ResponseEntity<?> updateKazna(@PathVariable Integer id, @RequestBody Kazna kazna) {
        try {
            Kazna updatedKazna = kaznaService.updateKazna(id, kazna);
            return ResponseEntity.ok(updatedKazna);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/kazne/{id} - Brisanje kazne
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKazna(@PathVariable Integer id) {
        try {
            kaznaService.deleteKazna(id);
            return ResponseEntity.ok("Kazna uspešno obrisana");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}