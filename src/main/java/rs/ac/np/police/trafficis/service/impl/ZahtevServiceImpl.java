package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Zahtev;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Signalizacija;
import rs.ac.np.police.trafficis.repository.ZahtevRepository;
import rs.ac.np.police.trafficis.service.ZahtevService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ZahtevServiceImpl implements ZahtevService {

    private final ZahtevRepository zahtevRepository;

    @Autowired
    public ZahtevServiceImpl(ZahtevRepository zahtevRepository) {
        this.zahtevRepository = zahtevRepository;
    }

    @Override
    public Zahtev createZahtev(Zahtev zahtev) {
        if (zahtev.getDatumVreme() == null) {
            zahtev.setDatumVreme(LocalDateTime.now());
        }
        if (zahtev.getStatus() == null) {
            zahtev.setStatus("primljen");
        }
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev updateZahtev(Integer id, Zahtev zahtev) {
        Optional<Zahtev> postojeciZahtev = zahtevRepository.findById(id);
        if (postojeciZahtev.isEmpty()) {
            throw new RuntimeException("Zahtev sa ID " + id + " ne postoji");
        }
        zahtev.setIdZahteva(id);
        return zahtevRepository.save(zahtev);
    }

    @Override
    public void deleteZahtev(Integer id) {
        if (!zahtevRepository.existsById(id)) {
            throw new RuntimeException("Zahtev sa ID " + id + " ne postoji");
        }
        zahtevRepository.deleteById(id);
    }

    @Override
    public Optional<Zahtev> getZahtevById(Integer id) {
        return zahtevRepository.findById(id);
    }

    @Override
    public List<Zahtev> getAllZahtevi() {
        return zahtevRepository.findAll();
    }

    @Override
    public List<Zahtev> getZahteviByStatus(String status) {
        return zahtevRepository.findByStatus(status);
    }

    @Override
    public List<Zahtev> getZahteviByTip(String tipZahteva) {
        return zahtevRepository.findByTipZahteva(tipZahteva);
    }

    @Override
    public List<Zahtev> getZahteviByIncident(Incident incident) {
        return zahtevRepository.findByIncident(incident);
    }

    @Override
    public List<Zahtev> getZahteviBySignalizacija(Signalizacija signalizacija) {
        return zahtevRepository.findBySignalizacija(signalizacija);
    }

    @Override
    public List<Zahtev> getAktivniZahtevi() {
        List<String> aktivniStatusi = Arrays.asList("primljen", "u obradi");
        return zahtevRepository.findByStatusIn(aktivniStatusi);
    }

    @Override
    public List<Zahtev> getNajnovijiZahtevi() {
        return zahtevRepository.findTop10ByOrderByDatumVremeDesc();
    }

    @Override
    public long countByStatus(String status) {
        return zahtevRepository.countByStatus(status);
    }
}