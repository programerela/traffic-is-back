package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Signalizacija;
import rs.ac.np.police.trafficis.repository.SignalizacijaRepository;
import rs.ac.np.police.trafficis.service.SignalizacijaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SignalizacijaServiceImpl implements SignalizacijaService {

    private final SignalizacijaRepository signalizacijaRepository;

    @Autowired
    public SignalizacijaServiceImpl(SignalizacijaRepository signalizacijaRepository) {
        this.signalizacijaRepository = signalizacijaRepository;
    }

    @Override
    public Signalizacija createSignalizacija(Signalizacija signalizacija) {
        if (signalizacija.getStatus() == null) {
            signalizacija.setStatus("ispravna");
        }
        return signalizacijaRepository.save(signalizacija);
    }

    @Override
    public Signalizacija updateSignalizacija(Integer id, Signalizacija signalizacija) {
        Optional<Signalizacija> postojecaSignalizacija = signalizacijaRepository.findById(id);
        if (postojecaSignalizacija.isEmpty()) {
            throw new RuntimeException("Signalizacija sa ID " + id + " ne postoji");
        }
        signalizacija.setIdSignalizacije(id);
        return signalizacijaRepository.save(signalizacija);
    }

    @Override
    public void deleteSignalizacija(Integer id) {
        if (!signalizacijaRepository.existsById(id)) {
            throw new RuntimeException("Signalizacija sa ID " + id + " ne postoji");
        }
        signalizacijaRepository.deleteById(id);
    }

    @Override
    public Optional<Signalizacija> getSignalizacijaById(Integer id) {
        return signalizacijaRepository.findById(id);
    }

    @Override
    public List<Signalizacija> getAllSignalizacija() {
        return signalizacijaRepository.findAll();
    }

    @Override
    public List<Signalizacija> getSignalizacijaByStatus(String status) {
        return signalizacijaRepository.findByStatus(status);
    }

    @Override
    public List<Signalizacija> getSignalizacijaByTip(String tipSignalizacije) {
        return signalizacijaRepository.findByTipSignalizacije(tipSignalizacije);
    }

    @Override
    public List<Signalizacija> getNeispravnaSignalizacija() {
        return signalizacijaRepository.findNeispravne();
    }

    @Override
    public List<Signalizacija> getSignalizacijaZaProveru(LocalDate datum) {
        return signalizacijaRepository.findByDatumPoslednjeProvereBefore(datum);
    }

    @Override
    public long countByStatus(String status) {
        return signalizacijaRepository.countByStatus(status);
    }
}