package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.repository.VozacRepository;
import rs.ac.np.police.trafficis.service.VozacService;

import java.util.List;
import java.util.Optional;

@Service
public class VozacServiceImpl implements VozacService {

    private final VozacRepository vozacRepository;

    @Autowired
    public VozacServiceImpl(VozacRepository vozacRepository) {
        this.vozacRepository = vozacRepository;
    }

    @Override
    public Vozac createVozac(Vozac vozac) {
        if (existsByJmbg(vozac.getJmbg())) {
            throw new RuntimeException("Vozač sa JMBG " + vozac.getJmbg() + " već postoji");
        }
        if (existsByBrojVozacke(vozac.getBrojVozacke())) {
            throw new RuntimeException("Vozač sa brojem vozačke " + vozac.getBrojVozacke() + " već postoji");
        }
        return vozacRepository.save(vozac);
    }

    @Override
    public Vozac updateVozac(Integer id, Vozac vozac) {
        Optional<Vozac> postojeciVozac = vozacRepository.findById(id);
        if (postojeciVozac.isEmpty()) {
            throw new RuntimeException("Vozač sa ID " + id + " ne postoji");
        }
        vozac.setIdVozaca(id);
        return vozacRepository.save(vozac);
    }

    @Override
    public void deleteVozac(Integer id) {
        if (!vozacRepository.existsById(id)) {
            throw new RuntimeException("Vozač sa ID " + id + " ne postoji");
        }
        vozacRepository.deleteById(id);
    }

    @Override
    public Optional<Vozac> getVozacById(Integer id) {
        return vozacRepository.findById(id);
    }

    @Override
    public Optional<Vozac> getVozacByJmbg(String jmbg) {
        return vozacRepository.findByJmbg(jmbg);
    }

    @Override
    public Optional<Vozac> getVozacByBrojVozacke(String brojVozacke) {
        return vozacRepository.findByBrojVozacke(brojVozacke);
    }

    @Override
    public List<Vozac> getAllVozaci() {
        return vozacRepository.findAll();
    }

    @Override
    public boolean existsByJmbg(String jmbg) {
        return vozacRepository.existsByJmbg(jmbg);
    }

    @Override
    public boolean existsByBrojVozacke(String brojVozacke) {
        return vozacRepository.existsByBrojVozacke(brojVozacke);
    }
}