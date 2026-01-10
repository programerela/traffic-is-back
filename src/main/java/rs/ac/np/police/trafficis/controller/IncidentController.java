package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.model.Incident;
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
    public ResponseEntity<List<Incident>> getAllIncidenti() {
        List<Incident> incidenti = incidentService.getAllIncidenti();
        return ResponseEntity.ok(incidenti);
    }

    // GET /api/incidenti/{id} - Dobijanje incidenta po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Integer id) {
        Optional<Incident> incident = incidentService.getIncidentById(id);
        return incident.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/incidenti/status/{status} - Dobijanje incidenata po statusu
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Incident>> getIncidentiByStatus(@PathVariable String status) {
        List<Incident> incidenti = incidentService.getIncidentiByStatus(status);
        return ResponseEntity.ok(incidenti);
    }

    // GET /api/incidenti/tezina/{tezina} - Dobijanje incidenata po težini
    @GetMapping("/tezina/{tezina}")
    public ResponseEntity<List<Incident>> getIncidentiByTezina(@PathVariable String tezina) {
        List<Incident> incidenti = incidentService.getIncidentiByTezina(tezina);
        return ResponseEntity.ok(incidenti);
    }

    // GET /api/incidenti/lokacija/{lokacija} - Dobijanje incidenata po lokaciji
    @GetMapping("/lokacija/{lokacija}")
    public ResponseEntity<List<Incident>> getIncidentiByLokacija(@PathVariable String lokacija) {
        List<Incident> incidenti = incidentService.getIncidentiByLokacija(lokacija);
        return ResponseEntity.ok(incidenti);
    }

    // GET /api/incidenti/period - Dobijanje incidenata za period
    @GetMapping("/period")
    public ResponseEntity<List<Incident>> getIncidentiByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<Incident> incidenti = incidentService.getIncidentiByPeriod(start, end);
        return ResponseEntity.ok(incidenti);
    }

    // GET /api/incidenti/najnoviji - Dobijanje najnovijih incidenata
    @GetMapping("/najnoviji")
    public ResponseEntity<List<Incident>> getNajnovijiIncidenti() {
        List<Incident> incidenti = incidentService.getNajnovijiIncidenti();
        return ResponseEntity.ok(incidenti);
    }

    // POST /api/incidenti - Kreiranje novog incidenta
    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody Incident incident) {
        try {
            Incident createdIncident = incidentService.createIncident(incident);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncident);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/incidenti/{id} - Ažuriranje incidenta
    @PutMapping("/{id}")
    public ResponseEntity<?> updateIncident(@PathVariable Integer id, @RequestBody Incident incident) {
        try {
            Incident updatedIncident = incidentService.updateIncident(id, incident);
            return ResponseEntity.ok(updatedIncident);
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