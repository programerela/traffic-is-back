package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Vozilo;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.repository.VoziloRepository;
import rs.ac.np.police.trafficis.service.VoziloService;

import java.util.List;
import java.util.Optional;

@Service
public class VoziloServiceImpl implements VoziloService {

    private final VoziloRepository voziloRepository;

    @Autowired
    public VoziloServiceImpl(VoziloRepository voziloRepository) {
        this.voziloRepository = voziloRepository;
    }

    @Override
    public Vozilo createVozilo(Vozilo vozilo) {
        if (existsByRegistracija(vozilo.getRegistracija())) {
            throw new RuntimeException("Vozilo sa registracijom " + vozilo.getRegistracija() + " već postoji");
        }
        return voziloRepository.save(vozilo);
    }

    @Override
    public Vozilo updateVozilo(Integer id, Vozilo vozilo) {
        Optional<Vozilo> postojeceVozilo = voziloRepository.findById(id);
        if (postojeceVozilo.isEmpty()) {
            throw new RuntimeException("Vozilo sa ID " + id + " ne postoji");
        }
        vozilo.setIdVozila(id);
        return voziloRepository.save(vozilo);
    }

    @Override
    public void deleteVozilo(Integer id) {
        if (!voziloRepository.existsById(id)) {
            throw new RuntimeException("Vozilo sa ID " + id + " ne postoji");
        }
        voziloRepository.deleteById(id);
    }

    @Override
    public Optional<Vozilo> getVoziloById(Integer id) {
        return voziloRepository.findById(id);
    }

    @Override
    public Optional<Vozilo> getVoziloByRegistracija(String registracija) {
        return voziloRepository.findByRegistracija(registracija);
    }

    @Override
    public List<Vozilo> getAllVozila() {
        return voziloRepository.findAll();
    }

    @Override
    public List<Vozilo> getVozilaByVozac(Vozac vozac) {
        return voziloRepository.findByVozac(vozac);
    }

    @Override
    public List<Vozilo> getVozilaByMarka(String marka) {
        return voziloRepository.findByMarka(marka);
    }

    @Override
    public boolean existsByRegistracija(String registracija) {
        return voziloRepository.existsByRegistracija(registracija);
    }
}