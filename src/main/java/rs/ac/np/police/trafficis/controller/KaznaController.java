package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.request.KaznaRequestDTO;
import rs.ac.np.police.trafficis.dto.response.KaznaResponseDTO;
import rs.ac.np.police.trafficis.mapper.DTOMapper;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Kazna;
import rs.ac.np.police.trafficis.model.Vozac;
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
    public ResponseEntity<List<KaznaResponseDTO>> getAllKazne() {
        List<Kazna> kazne = kaznaService.getAllKazne();
        List<KaznaResponseDTO> kazneDTO = DTOMapper.toKaznaDTOList(kazne);
        return ResponseEntity.ok(kazneDTO);
    }

    // GET /api/kazne/{id} - Dobijanje kazne po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<KaznaResponseDTO> getKaznaById(@PathVariable Integer id) {
        Optional<Kazna> kazna = kaznaService.getKaznaById(id);
        return kazna.map(k -> ResponseEntity.ok(DTOMapper.toKaznaDTO(k)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/kazne/status/{status} - Dobijanje kazni po statusu plaćanja
    @GetMapping("/status/{status}")
    public ResponseEntity<List<KaznaResponseDTO>> getKazneByStatus(@PathVariable String status) {
        List<Kazna> kazne = kaznaService.getKazneByStatusPlacanja(status);
        List<KaznaResponseDTO> kazneDTO = DTOMapper.toKaznaDTOList(kazne);
        return ResponseEntity.ok(kazneDTO);
    }

    // GET /api/kazne/vrsta/{vrsta} - Dobijanje kazni po vrsti prekršaja
    @GetMapping("/vrsta/{vrsta}")
    public ResponseEntity<List<Kazna>> getKazneByVrsta(@PathVariable String vrsta) {
        List<Kazna> kazne = kaznaService.getKazneByVrstaPrekrsaja(vrsta);
        return ResponseEntity.ok(kazne);
    }

    // POST /api/kazne - Kreiranje nove kazne
    @PostMapping
    public ResponseEntity<?> createKazna(@RequestBody KaznaRequestDTO requestDTO) {
        try {
            // Konvertuj DTO u Kazna entitet
            Kazna kazna = new Kazna();
            kazna.setDatumIzdavanja(requestDTO.getDatumIzdavanja());
            kazna.setIznos(requestDTO.getIznos());
            kazna.setOpisPrekrsaja(requestDTO.getOpisPrekrsaja());
            kazna.setStatusPlacanja(requestDTO.getStatusPlacanja());
            kazna.setVrstaPrekrsaja(requestDTO.getVrstaPrekrsaja());

            // Postavi vozača
            if (requestDTO.getIdVozaca() != null) {
                Vozac vozac = new Vozac();
                vozac.setIdVozaca(requestDTO.getIdVozaca());
                kazna.setVozac(vozac);
            }

            // Postavi incident ako postoji
            if (requestDTO.getIdIncidenta() != null) {
                Incident incident = new Incident();
                incident.setIdIncidenta(requestDTO.getIdIncidenta());
                kazna.setIncident(incident);
            }

            Kazna createdKazna = kaznaService.createKazna(kazna);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdKazna);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/kazne/{id} - Ažuriranje kazne
    @PutMapping("/{id}")
    public ResponseEntity<?> updateKazna(@PathVariable Integer id, @RequestBody KaznaRequestDTO requestDTO) {
        try {
            // Konvertuj DTO u Kazna entitet
            Kazna kazna = new Kazna();
            kazna.setIdKazne(id);
            kazna.setDatumIzdavanja(requestDTO.getDatumIzdavanja());
            kazna.setIznos(requestDTO.getIznos());
            kazna.setOpisPrekrsaja(requestDTO.getOpisPrekrsaja());
            kazna.setStatusPlacanja(requestDTO.getStatusPlacanja());
            kazna.setVrstaPrekrsaja(requestDTO.getVrstaPrekrsaja());

            // Postavi vozača
            if (requestDTO.getIdVozaca() != null) {
                Vozac vozac = new Vozac();
                vozac.setIdVozaca(requestDTO.getIdVozaca());
                kazna.setVozac(vozac);
            }

            // Postavi incident ako postoji
            if (requestDTO.getIdIncidenta() != null) {
                Incident incident = new Incident();
                incident.setIdIncidenta(requestDTO.getIdIncidenta());
                kazna.setIncident(incident);
            }

            Kazna updatedKazna = kaznaService.updateKazna(id, kazna);
            KaznaResponseDTO responseDTO = DTOMapper.toKaznaDTO(updatedKazna);
            return ResponseEntity.ok(responseDTO);
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