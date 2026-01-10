package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.model.Zahtev;
import rs.ac.np.police.trafficis.service.ZahtevService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zahtevi")
@CrossOrigin(origins = "*")
@Tag(name = "Zahtevi", description = "API za upravljanje zahtevima i prijavama građana")
public class ZahtevController {

    private final ZahtevService zahtevService;

    @Autowired
    public ZahtevController(ZahtevService zahtevService) {
        this.zahtevService = zahtevService;
    }

    // GET /api/zahtevi - Dobijanje svih zahteva
    @GetMapping
    public ResponseEntity<List<Zahtev>> getAllZahtevi() {
        List<Zahtev> zahtevi = zahtevService.getAllZahtevi();
        return ResponseEntity.ok(zahtevi);
    }

    // GET /api/zahtevi/{id} - Dobijanje zahteva po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<Zahtev> getZahtevById(@PathVariable Integer id) {
        Optional<Zahtev> zahtev = zahtevService.getZahtevById(id);
        return zahtev.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/zahtevi/status/{status} - Dobijanje zahteva po statusu
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Zahtev>> getZahteviByStatus(@PathVariable String status) {
        List<Zahtev> zahtevi = zahtevService.getZahteviByStatus(status);
        return ResponseEntity.ok(zahtevi);
    }

    // GET /api/zahtevi/aktivni - Dobijanje aktivnih zahteva
    @GetMapping("/aktivni")
    public ResponseEntity<List<Zahtev>> getAktivniZahtevi() {
        List<Zahtev> zahtevi = zahtevService.getAktivniZahtevi();
        return ResponseEntity.ok(zahtevi);
    }

    // GET /api/zahtevi/najnoviji - Dobijanje najnovijih zahteva
    @GetMapping("/najnoviji")
    public ResponseEntity<List<Zahtev>> getNajnovijiZahtevi() {
        List<Zahtev> zahtevi = zahtevService.getNajnovijiZahtevi();
        return ResponseEntity.ok(zahtevi);
    }

    // POST /api/zahtevi - Kreiranje novog zahteva
    @PostMapping
    public ResponseEntity<?> createZahtev(@RequestBody Zahtev zahtev) {
        try {
            Zahtev createdZahtev = zahtevService.createZahtev(zahtev);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdZahtev);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/zahtevi/{id} - Ažuriranje zahteva
    @PutMapping("/{id}")
    public ResponseEntity<?> updateZahtev(@PathVariable Integer id, @RequestBody Zahtev zahtev) {
        try {
            Zahtev updatedZahtev = zahtevService.updateZahtev(id, zahtev);
            return ResponseEntity.ok(updatedZahtev);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/zahtevi/{id} - Brisanje zahteva
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteZahtev(@PathVariable Integer id) {
        try {
            zahtevService.deleteZahtev(id);
            return ResponseEntity.ok("Zahtev uspešno obrisan");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}