package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.service.AnalyticsService;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
@Tag(name = "Analitika", description = "API za statistiku i analitičke izveštaje")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // GET /api/analytics/dashboard - Dashboard statistika
    @Operation(summary = "Dashboard statistika", description = "Vraća kompletnu statistiku sistema - ukupno incidenata, kazni, aktivni zahtevi, najnoviji incidenti")
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        Map<String, Object> dashboard = analyticsService.getDashboardStatistika();
        return ResponseEntity.ok(dashboard);
    }

    // GET /api/analytics/incidenti/tezina - Statistika incidenata po težini
    @GetMapping("/incidenti/tezina")
    public ResponseEntity<Map<String, Long>> getIncidentStatistikaPoTezini() {
        Map<String, Long> statistika = analyticsService.getIncidentStatistikaPoTezini();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/incidenti/lokacija - Statistika incidenata po lokaciji
    @GetMapping("/incidenti/lokacija")
    public ResponseEntity<Map<String, Long>> getIncidentStatistikaPoLokaciji() {
        Map<String, Long> statistika = analyticsService.getIncidentStatistikaPoLokaciji();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/incidenti/top-lokacije - Top 10 lokacija sa incidentima
    @GetMapping("/incidenti/top-lokacije")
    public ResponseEntity<Map<String, Long>> getTop10Lokacija() {
        Map<String, Long> statistika = analyticsService.getTop10LokacijaSaIncidentima();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/incidenti/period - Statistika incidenata za period
    @GetMapping("/incidenti/period")
    public ResponseEntity<Map<String, Long>> getIncidentStatistikaZaPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        Map<String, Long> statistika = analyticsService.getIncidentStatistikaZaPeriod(start, end);
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/kazne/vrsta - Statistika kazni po vrsti prekršaja
    @GetMapping("/kazne/vrsta")
    public ResponseEntity<Map<String, Long>> getKaznaStatistikaPoVrsti() {
        Map<String, Long> statistika = analyticsService.getKaznaStatistikaPoVrstiPrekrsaja();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/kazne/status - Statistika kazni po statusu plaćanja
    @GetMapping("/kazne/status")
    public ResponseEntity<Map<String, Long>> getKaznaStatistikaPoStatusu() {
        Map<String, Long> statistika = analyticsService.getKaznaStatistikaPoStatusu();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/vozaci/top-kazne - Top 10 vozača sa najviše kazni
    @GetMapping("/vozaci/top-kazne")
    public ResponseEntity<Map<String, Object>> getTop10VozacaSaKaznama() {
        Map<String, Object> statistika = analyticsService.getTop10VozacaSaKaznama();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/signalizacija/status - Statistika signalizacije po statusu
    @GetMapping("/signalizacija/status")
    public ResponseEntity<Map<String, Long>> getSignalizacijaStatistika() {
        Map<String, Long> statistika = analyticsService.getSignalizacijaStatistikaPoStatusu();
        return ResponseEntity.ok(statistika);
    }

    // GET /api/analytics/izvestaj/mesecni - Mesečni izveštaj
    @GetMapping("/izvestaj/mesecni")
    public ResponseEntity<Map<String, Object>> getMesecniIzvestaj(
            @RequestParam int godina,
            @RequestParam int mesec) {
        Map<String, Object> izvestaj = analyticsService.getMesecniIzvestajIncidenata(godina, mesec);
        return ResponseEntity.ok(izvestaj);
    }

    // GET /api/analytics/izvestaj/godisnji - Godišnji izveštaj
    @GetMapping("/izvestaj/godisnji")
    public ResponseEntity<Map<String, Object>> getGodisnjiiIzvestaj(@RequestParam int godina) {
        Map<String, Object> izvestaj = analyticsService.getGodisnjiiIzvestajIncidenata(godina);
        return ResponseEntity.ok(izvestaj);
    }
}