package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.response.ObavestenjeResponseDTO;
import rs.ac.np.police.trafficis.mapper.DTOMapper;
import rs.ac.np.police.trafficis.model.Obavestenje;
import rs.ac.np.police.trafficis.service.NotificationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
@Tag(name = "Obaveštenja", description = "API za upravljanje obaveštenjima za javnost (automatski se kreiraju iz incidenata)")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // GET /api/notifications/active - Dobijanje aktivnih obaveštenja (24h)
    @GetMapping("/active")
    public ResponseEntity<List<ObavestenjeResponseDTO>> getActiveNotifications() {
        List<Obavestenje> notifications = notificationService.getActiveNotifications();
        List<ObavestenjeResponseDTO> notificationsDTO = DTOMapper.toObavestenjeDTOList(notifications);
        return ResponseEntity.ok(notificationsDTO);
    }

    // GET /api/notifications/prioritet/{prioritet} - Dobijanje obaveštenja po prioritetu
    @GetMapping("/prioritet/{prioritet}")
    public ResponseEntity<List<ObavestenjeResponseDTO>> getNotificationsByPrioritet(@PathVariable String prioritet) {
        List<Obavestenje> notifications = notificationService.getNotificationsByPrioritet(prioritet);
        List<ObavestenjeResponseDTO> notificationsDTO = DTOMapper.toObavestenjeDTOList(notifications);
        return ResponseEntity.ok(notificationsDTO);
    }

    // POST /api/notifications - Kreiranje manuelnog obaveštenja
    @PostMapping
    public ResponseEntity<?> createNotification(@RequestBody Map<String, String> request) {
        try {
            String tekst = request.get("tekst");
            String vrsta = request.get("vrsta");
            String prioritet = request.get("prioritet");

            Obavestenje notification = notificationService.createManualNotification(tekst, vrsta, prioritet);
            return ResponseEntity.status(HttpStatus.CREATED).body(notification);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // DELETE /api/notifications/{id} - Brisanje obaveštenja
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Integer id) {
        try {
            notificationService.deleteNotification(id);
            return ResponseEntity.ok("Obaveštenje uspešno obrisano");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}