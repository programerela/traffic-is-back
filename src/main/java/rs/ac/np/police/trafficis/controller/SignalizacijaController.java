package rs.ac.np.police.trafficis.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.np.police.trafficis.dto.response.SignalizacijaResponseDTO;
import rs.ac.np.police.trafficis.mapper.DTOMapper;
import rs.ac.np.police.trafficis.model.Signalizacija;
import rs.ac.np.police.trafficis.service.SignalizacijaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/signalizacija")
@CrossOrigin(origins = "*")
@Tag(name = "Signalizacija", description = "API za upravljanje saobraćajnom signalizacijom")
public class SignalizacijaController {

    private final SignalizacijaService signalizacijaService;

    @Autowired
    public SignalizacijaController(SignalizacijaService signalizacijaService) {
        this.signalizacijaService = signalizacijaService;
    }

    // GET /api/signalizacija - Dobijanje sve signalizacije
    @GetMapping
    public ResponseEntity<List<SignalizacijaResponseDTO>> getAllSignalizacija() {
        List<Signalizacija> signalizacija = signalizacijaService.getAllSignalizacija();
        List<SignalizacijaResponseDTO> signalizacijaDTO = DTOMapper.toSignalizacijaDTOList(signalizacija);
        return ResponseEntity.ok(signalizacijaDTO);
    }

    // GET /api/signalizacija/{id} - Dobijanje signalizacije po ID-u
    @GetMapping("/{id}")
    public ResponseEntity<SignalizacijaResponseDTO> getSignalizacijaById(@PathVariable Integer id) {
        Optional<Signalizacija> signalizacija = signalizacijaService.getSignalizacijaById(id);
        return signalizacija.map(s -> ResponseEntity.ok(DTOMapper.toSignalizacijaDTO(s)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/signalizacija/status/{status} - Dobijanje signalizacije po statusu
    @GetMapping("/status/{status}")
    public ResponseEntity<List<SignalizacijaResponseDTO>> getSignalizacijaByStatus(@PathVariable String status) {
        List<Signalizacija> signalizacija = signalizacijaService.getSignalizacijaByStatus(status);
        List<SignalizacijaResponseDTO> signalizacijaDTO = DTOMapper.toSignalizacijaDTOList(signalizacija);
        return ResponseEntity.ok(signalizacijaDTO);
    }

    // GET /api/signalizacija/neispravna - Dobijanje neispravne signalizacije
    @GetMapping("/neispravna")
    public ResponseEntity<List<SignalizacijaResponseDTO>> getNeispravnaSignalizacija() {
        List<Signalizacija> signalizacija = signalizacijaService.getNeispravnaSignalizacija();
        List<SignalizacijaResponseDTO> signalizacijaDTO = DTOMapper.toSignalizacijaDTOList(signalizacija);
        return ResponseEntity.ok(signalizacijaDTO);
    }

    // POST /api/signalizacija - Kreiranje nove signalizacije
    @PostMapping
    public ResponseEntity<?> createSignalizacija(@RequestBody Signalizacija signalizacija) {
        try {
            Signalizacija createdSignalizacija = signalizacijaService.createSignalizacija(signalizacija);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSignalizacija);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // PUT /api/signalizacija/{id} - Ažuriranje signalizacije
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSignalizacija(@PathVariable Integer id, @RequestBody Signalizacija signalizacija) {
        try {
            Signalizacija updatedSignalizacija = signalizacijaService.updateSignalizacija(id, signalizacija);
            SignalizacijaResponseDTO responseDTO = DTOMapper.toSignalizacijaDTO(updatedSignalizacija);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // DELETE /api/signalizacija/{id} - Brisanje signalizacije
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSignalizacija(@PathVariable Integer id) {
        try {
            signalizacijaService.deleteSignalizacija(id);
            return ResponseEntity.ok("Signalizacija uspešno obrisana");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}