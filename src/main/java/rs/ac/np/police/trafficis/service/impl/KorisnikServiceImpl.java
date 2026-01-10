package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Korisnik;
import rs.ac.np.police.trafficis.repository.KorisnikRepository;
import rs.ac.np.police.trafficis.service.KorisnikService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public KorisnikServiceImpl(KorisnikRepository korisnikRepository,
                               PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Korisnik createKorisnik(Korisnik korisnik) {
        if (existsByUsername(korisnik.getUsername())) {
            throw new RuntimeException("Korisnik sa username-om " + korisnik.getUsername() + " već postoji");
        }
        // Enkripcija lozinke
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        return korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik updateKorisnik(Integer id, Korisnik korisnik) {
        Optional<Korisnik> postojeciKorisnik = korisnikRepository.findById(id);
        if (postojeciKorisnik.isEmpty()) {
            throw new RuntimeException("Korisnik sa ID " + id + " ne postoji");
        }
        korisnik.setIdUser(id);
        // Enkripcija lozinke ako je promenjena
        if (korisnik.getPassword() != null && !korisnik.getPassword().isEmpty()) {
            korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        } else {
            // Zadrži staru lozinku ako nova nije poslata
            korisnik.setPassword(postojeciKorisnik.get().getPassword());
        }
        return korisnikRepository.save(korisnik);
    }

    @Override
    public void deleteKorisnik(Integer id) {
        if (!korisnikRepository.existsById(id)) {
            throw new RuntimeException("Korisnik sa ID " + id + " ne postoji");
        }
        korisnikRepository.deleteById(id);
    }

    @Override
    public Optional<Korisnik> getKorisnikById(Integer id) {
        return korisnikRepository.findById(id);
    }

    @Override
    public Optional<Korisnik> getKorisnikByUsername(String username) {
        return korisnikRepository.findByUsername(username);
    }

    @Override
    public List<Korisnik> getAllKorisnici() {
        return korisnikRepository.findAll();
    }

    @Override
    public List<Korisnik> getKorisniciByRole(String role) {
        return korisnikRepository.findByRole(role);
    }

    @Override
    public boolean existsByUsername(String username) {
        return korisnikRepository.existsByUsername(username);
    }
}