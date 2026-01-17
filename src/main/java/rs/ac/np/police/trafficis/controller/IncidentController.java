package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.request.IncidentRequestDTO;
import rs.ac.np.police.trafficis.dto.response.IncidentResponseDTO;
import rs.ac.np.police.trafficis.mapper.DTOMapper;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Vozilo;
import rs.ac.np.police.trafficis.service.IncidentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidenti")
@CrossOrigin(origins = "*")
@Tag(name = "Incidenti", description = "API za upravljanje saobraćajnim incidentima")
public class IncidentController {

    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // GET /api/incidenti - Dobijanje svih incidenata
    @GetMapping
    public ResponseEntity<List<IncidentResponseDTO>> getAllIncidenti() {
        List<Incident> incidenti = incidentService.getAllIncidenti();
        List<IncidentResponseDTO> incidentiDTO = DTOMapper.toIncidentDTOList(incidenti);
        return ResponseEntity.ok(incidentiDTO);
    }

    // GET /api/incidenti/{id} - Dobijanje incidenta po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponseDTO> getIncidentById(@PathVariable Integer id) {
        Optional<Incident> incident = incidentService.getIncidentById(id);
        return incident.map(i -> ResponseEntity.ok(DTOMapper.toIncidentDTO(i)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/incidenti/status/{status} - Dobijanje incidenata po statusu
    @GetMapping("/status/{status}")
    public ResponseEntity<List<IncidentResponseDTO>> getIncidentiByStatus(@PathVariable String status) {
        List<Incident> incidenti = incidentService.getIncidentiByStatus(status);
        List<IncidentResponseDTO> incidentiDTO = DTOMapper.toIncidentDTOList(incidenti);
        return ResponseEntity.ok(incidentiDTO);
    }

    // GET /api/incidenti/tezina/{tezina} - Dobijanje incidenata po težini
    @GetMapping("/tezina/{tezina}")
    public ResponseEntity<List<IncidentResponseDTO>> getIncidentiByTezina(@PathVariable String tezina) {
        List<Incident> incidenti = incidentService.getIncidentiByTezina(tezina);
        List<IncidentResponseDTO> incidentiDTO = DTOMapper.toIncidentDTOList(incidenti);
        return ResponseEntity.ok(incidentiDTO);
    }

    // GET /api/incidenti/lokacija/{lokacija} - Dobijanje incidenata po lokaciji
    @GetMapping("/lokacija/{lokacija}")
    public ResponseEntity<List<IncidentResponseDTO>> getIncidentiByLokacija(@PathVariable String lokacija) {
        List<Incident> incidenti = incidentService.getIncidentiByLokacija(lokacija);
        List<IncidentResponseDTO> incidentiDTO = DTOMapper.toIncidentDTOList(incidenti);
        return ResponseEntity.ok(incidentiDTO);
    }

    // GET /api/incidenti/period - Dobijanje incidenata za period
    @GetMapping("/period")
    public ResponseEntity<List<IncidentResponseDTO>> getIncidentiByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Incident> incidenti = incidentService.getIncidentiByPeriod(start, end);
        List<IncidentResponseDTO> incidentiDTO = DTOMapper.toIncidentDTOList(incidenti);
        return ResponseEntity.ok(incidentiDTO);
    }

    // GET /api/incidenti/najnoviji - Dobijanje najnovijih incidenata
    @GetMapping("/najnoviji")
    public ResponseEntity<List<IncidentResponseDTO>> getNajnovijiIncidenti() {
        List<Incident> incidenti = incidentService.getNajnovijiIncidenti();
        List<IncidentResponseDTO> incidentiDTO = DTOMapper.toIncidentDTOList(incidenti);
        return ResponseEntity.ok(incidentiDTO);
    }

    // POST /api/incidenti - Kreiranje novog incidenta
    @Operation(summary = "Kreiranje novog incidenta", description = "Kreira novi saobraćajni incident i automatski generiše obaveštenje za javnost")
    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody IncidentRequestDTO requestDTO) {
        try {
            // Konvertuj DTO u Incident entitet
            Incident incident = new Incident();
            incident.setDatumVreme(requestDTO.getDatumVreme());
            incident.setLokacija(requestDTO.getLokacija());
            incident.setOpis(requestDTO.getOpis());
            incident.setTezinaIncidenta(requestDTO.getTezinaIncidenta());
            incident.setStatusIncidenta(requestDTO.getStatusIncidenta());

            // Postavi vozača ako je prosleđen ID
            if (requestDTO.getIdVozaca() != null) {
                Vozac vozac = new Vozac();
                vozac.setIdVozaca(requestDTO.getIdVozaca());
                incident.setVozac(vozac);
            }

            // Postavi vozilo ako je prosleđen ID
            if (requestDTO.getIdVozila() != null) {
                Vozilo vozilo = new Vozilo();
                vozilo.setIdVozila(requestDTO.getIdVozila());
                incident.setVozilo(vozilo);
            }

            Incident createdIncident = incidentService.createIncident(incident);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncident);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/incidenti/{id} - Ažuriranje incidenta
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncident(@PathVariable Integer id, @RequestBody IncidentRequestDTO requestDTO) {
        try {
            // Konvertuj DTO u Incident entitet
            Incident incident = new Incident();
            incident.setIdIncidenta(id);
            incident.setDatumVreme(requestDTO.getDatumVreme());
            incident.setLokacija(requestDTO.getLokacija());
            incident.setOpis(requestDTO.getOpis());
            incident.setTezinaIncidenta(requestDTO.getTezinaIncidenta());
            incident.setStatusIncidenta(requestDTO.getStatusIncidenta());

            // Postavi vozača ako je prosleđen ID
            if (requestDTO.getIdVozaca() != null) {
                Vozac vozac = new Vozac();
                vozac.setIdVozaca(requestDTO.getIdVozaca());
                incident.setVozac(vozac);
            }

            // Postavi vozilo ako je prosleđen ID
            if (requestDTO.getIdVozila() != null) {
                Vozilo vozilo = new Vozilo();
                vozilo.setIdVozila(requestDTO.getIdVozila());
                incident.setVozilo(vozilo);
            }

            Incident updatedIncident = incidentService.updateIncident(id, incident);
            IncidentResponseDTO responseDTO = DTOMapper.toIncidentDTO(updatedIncident);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/incidenti/{id} - Brisanje incidenta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncident(@PathVariable Integer id) {
        try {
            incidentService.deleteIncident(id);
            return ResponseEntity.ok("Incident uspešno obrisan");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}