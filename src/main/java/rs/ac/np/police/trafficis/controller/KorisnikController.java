package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.request.KorisnikDTO;
import rs.ac.np.police.trafficis.mapper.KorisnikMapper;
import rs.ac.np.police.trafficis.model.Korisnik;
import rs.ac.np.police.trafficis.service.KorisnikService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/korisnici")
@CrossOrigin(origins = "*")
@Tag(name = "Korisnici", description = "API za upravljanje korisnicima sistema (lozinke su enkriptovane BCrypt-om)")
public class KorisnikController {

    private final KorisnikService korisnikService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    // GET /api/korisnici - Dobijanje svih korisnika (bez lozinki)
    @GetMapping
    public ResponseEntity<List<KorisnikDTO>> getAllKorisnici() {
        List<Korisnik> korisnici = korisnikService.getAllKorisnici();
        List<KorisnikDTO> korisniciDTO = KorisnikMapper.toDTOList(korisnici);
        return ResponseEntity.ok(korisniciDTO);
    }

    // GET /api/korisnici/{id} - Dobijanje korisnika po ID-u (bez lozinke)
    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDTO> getKorisnikById(@PathVariable Integer id) {
        Optional<Korisnik> korisnik = korisnikService.getKorisnikById(id);
        return korisnik.map(k -> ResponseEntity.ok(KorisnikMapper.toDTO(k)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/korisnici/username/{username} - Dobijanje korisnika po username-u (bez lozinke)
    @GetMapping("/username/{username}")
    public ResponseEntity<KorisnikDTO> getKorisnikByUsername(@PathVariable String username) {
        Optional<Korisnik> korisnik = korisnikService.getKorisnikByUsername(username);
        return korisnik.map(k -> ResponseEntity.ok(KorisnikMapper.toDTO(k)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/korisnici/role/{role} - Dobijanje korisnika po ulozi (bez lozinki)
    @GetMapping("/role/{role}")
    public ResponseEntity<List<KorisnikDTO>> getKorisniciByRole(@PathVariable String role) {
        List<Korisnik> korisnici = korisnikService.getKorisniciByRole(role);
        List<KorisnikDTO> korisniciDTO = KorisnikMapper.toDTOList(korisnici);
        return ResponseEntity.ok(korisniciDTO);
    }

    // POST /api/korisnici - Kreiranje novog korisnika
    @PostMapping
    public ResponseEntity<?> createKorisnik(@RequestBody Korisnik korisnik) {
        try {
            Korisnik createdKorisnik = korisnikService.createKorisnik(korisnik);
            KorisnikDTO korisnikDTO = KorisnikMapper.toDTO(createdKorisnik);
            return ResponseEntity.status(HttpStatus.CREATED).body(korisnikDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/korisnici/{id} - Ažuriranje korisnika
    @PutMapping("/{id}")
    public ResponseEntity<?> updateKorisnik(@PathVariable Integer id, @RequestBody Korisnik korisnik) {
        try {
            Korisnik updatedKorisnik = korisnikService.updateKorisnik(id, korisnik);
            KorisnikDTO korisnikDTO = KorisnikMapper.toDTO(updatedKorisnik);
            return ResponseEntity.ok(korisnikDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/korisnici/{id} - Brisanje korisnika
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKorisnik(@PathVariable Integer id) {
        try {
            korisnikService.deleteKorisnik(id);
            return ResponseEntity.ok("Korisnik uspešno obrisan");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}