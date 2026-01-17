package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.request.ZahtevRequestDTO;
import rs.ac.np.police.trafficis.dto.response.ZahtevResponseDTO;
import rs.ac.np.police.trafficis.mapper.DTOMapper;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Signalizacija;
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
    public ResponseEntity<List<ZahtevResponseDTO>> getAllZahtevi() {
        List<Zahtev> zahtevi = zahtevService.getAllZahtevi();
        List<ZahtevResponseDTO> zahteviDTO = DTOMapper.toZahtevDTOList(zahtevi);
        return ResponseEntity.ok(zahteviDTO);
    }

    // GET /api/zahtevi/{id} - Dobijanje zahteva po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<ZahtevResponseDTO> getZahtevById(@PathVariable Integer id) {
        Optional<Zahtev> zahtev = zahtevService.getZahtevById(id);
        return zahtev.map(z -> ResponseEntity.ok(DTOMapper.toZahtevDTO(z)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/zahtevi/status/{status} - Dobijanje zahteva po statusu
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ZahtevResponseDTO>> getZahteviByStatus(@PathVariable String status) {
        List<Zahtev> zahtevi = zahtevService.getZahteviByStatus(status);
        List<ZahtevResponseDTO> zahteviDTO = DTOMapper.toZahtevDTOList(zahtevi);
        return ResponseEntity.ok(zahteviDTO);
    }

    // GET /api/zahtevi/aktivni - Dobijanje aktivnih zahteva
    @GetMapping("/aktivni")
    public ResponseEntity<List<ZahtevResponseDTO>> getAktivniZahtevi() {
        List<Zahtev> zahtevi = zahtevService.getAktivniZahtevi();
        List<ZahtevResponseDTO> zahteviDTO = DTOMapper.toZahtevDTOList(zahtevi);
        return ResponseEntity.ok(zahteviDTO);
    }

    // GET /api/zahtevi/najnoviji - Dobijanje najnovijih zahteva
    @GetMapping("/najnoviji")
    public ResponseEntity<List<ZahtevResponseDTO>> getNajnovijiZahtevi() {
        List<Zahtev> zahtevi = zahtevService.getNajnovijiZahtevi();
        List<ZahtevResponseDTO> zahteviDTO = DTOMapper.toZahtevDTOList(zahtevi);
        return ResponseEntity.ok(zahteviDTO);
    }

    // POST /api/zahtevi - Kreiranje novog zahteva
    @PostMapping
    public ResponseEntity<?> createZahtev(@RequestBody ZahtevRequestDTO requestDTO) {
        try {
            // Konvertuj DTO u Zahtev entitet
            Zahtev zahtev = new Zahtev();
            zahtev.setTipZahteva(requestDTO.getTipZahteva());
            zahtev.setOpis(requestDTO.getOpis());
            zahtev.setDatumVreme(requestDTO.getDatumVreme());
            zahtev.setStatus(requestDTO.getStatus());

            // Postavi incident ako postoji
            if (requestDTO.getIdIncidenta() != null) {
                Incident incident = new Incident();
                incident.setIdIncidenta(requestDTO.getIdIncidenta());
                zahtev.setIncident(incident);
            }

            // Postavi signalizaciju ako postoji
            if (requestDTO.getIdSignalizacije() != null) {
                Signalizacija signalizacija = new Signalizacija();
                signalizacija.setIdSignalizacije(requestDTO.getIdSignalizacije());
                zahtev.setSignalizacija(signalizacija);
            }

            Zahtev createdZahtev = zahtevService.createZahtev(zahtev);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdZahtev);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/zahtevi/{id} - Ažuriranje zahteva
    @PutMapping("/{id}")
    public ResponseEntity<?> updateZahtev(@PathVariable Integer id, @RequestBody ZahtevRequestDTO requestDTO) {
        try {
            // Konvertuj DTO u Zahtev entitet
            Zahtev zahtev = new Zahtev();
            zahtev.setIdZahteva(id);
            zahtev.setTipZahteva(requestDTO.getTipZahteva());
            zahtev.setOpis(requestDTO.getOpis());
            zahtev.setDatumVreme(requestDTO.getDatumVreme());
            zahtev.setStatus(requestDTO.getStatus());

            // Postavi incident ako postoji
            if (requestDTO.getIdIncidenta() != null) {
                Incident incident = new Incident();
                incident.setIdIncidenta(requestDTO.getIdIncidenta());
                zahtev.setIncident(incident);
            }

            // Postavi signalizaciju ako postoji
            if (requestDTO.getIdSignalizacije() != null) {
                Signalizacija signalizacija = new Signalizacija();
                signalizacija.setIdSignalizacije(requestDTO.getIdSignalizacije());
                zahtev.setSignalizacija(signalizacija);
            }

            Zahtev updatedZahtev = zahtevService.updateZahtev(id, zahtev);
            ZahtevResponseDTO responseDTO = DTOMapper.toZahtevDTO(updatedZahtev);
            return ResponseEntity.ok(responseDTO);
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