package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Kazna;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.repository.KaznaRepository;
import rs.ac.np.police.trafficis.service.KaznaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class KaznaServiceImpl implements KaznaService {

    private final KaznaRepository kaznaRepository;

    @Autowired
    public KaznaServiceImpl(KaznaRepository kaznaRepository) {
        this.kaznaRepository = kaznaRepository;
    }

    @Override
    public Kazna createKazna(Kazna kazna) {
        if (kazna.getDatumIzdavanja() == null) {
            kazna.setDatumIzdavanja(LocalDate.now());
        }
        if (kazna.getStatusPlacanja() == null) {
            kazna.setStatusPlacanja("nije plaćena");
        }
        return kaznaRepository.save(kazna);
    }

    @Override
    public Kazna updateKazna(Integer id, Kazna kazna) {
        Optional<Kazna> postojecaKazna = kaznaRepository.findById(id);
        if (postojecaKazna.isEmpty()) {
            throw new RuntimeException("Kazna sa ID " + id + " ne postoji");
        }
        kazna.setIdKazne(id);
        return kaznaRepository.save(kazna);
    }

    @Override
    public void deleteKazna(Integer id) {
        if (!kaznaRepository.existsById(id)) {
            throw new RuntimeException("Kazna sa ID " + id + " ne postoji");
        }
        kaznaRepository.deleteById(id);
    }

    @Override
    public Optional<Kazna> getKaznaById(Integer id) {
        return kaznaRepository.findById(id);
    }

    @Override
    public List<Kazna> getAllKazne() {
        return kaznaRepository.findAll();
    }

    @Override
    public List<Kazna> getKazneByVozac(Vozac vozac) {
        return kaznaRepository.findByVozac(vozac);
    }

    @Override
    public List<Kazna> getKazneByIncident(Incident incident) {
        return kaznaRepository.findByIncident(incident);
    }

    @Override
    public List<Kazna> getKazneByStatusPlacanja(String statusPlacanja) {
        return kaznaRepository.findByStatusPlacanja(statusPlacanja);
    }

    @Override
    public List<Kazna> getNeplaceneKazneByVozac(Vozac vozac) {
        return kaznaRepository.findByStatusPlacanjaAndVozac("nije plaćena", vozac);
    }

    @Override
    public List<Kazna> getKazneByVrstaPrekrsaja(String vrstaPrekrsaja) {
        return kaznaRepository.findByVrstaPrekrsaja(vrstaPrekrsaja);
    }

    @Override
    public BigDecimal getSumNeplacenihKazni(Vozac vozac) {
        BigDecimal sum = kaznaRepository.sumNeplacenihKazni(vozac);
        return sum != null ? sum : BigDecimal.ZERO;
    }

    @Override
    public long countKazneByVozac(Vozac vozac) {
        return kaznaRepository.countByVozac(vozac);
    }
}