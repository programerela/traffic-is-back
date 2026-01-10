package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Vozac;

import java.util.List;
import java.util.Optional;

public interface VozacService {

    // Kreiranje novog vozača
    Vozac createVozac(Vozac vozac);

    // Ažuriranje vozača
    Vozac updateVozac(Integer id, Vozac vozac);

    // Brisanje vozača
    void deleteVozac(Integer id);

    // Pronalaženje vozača po ID-u
    Optional<Vozac> getVozacById(Integer id);

    // Pronalaženje vozača po JMBG
    Optional<Vozac> getVozacByJmbg(String jmbg);

    // Pronalaženje vozača po broju vozačke
    Optional<Vozac> getVozacByBrojVozacke(String brojVozacke);

    // Pronalaženje svih vozača
    List<Vozac> getAllVozaci();

    // Provera da li postoji vozač sa datim JMBG
    boolean existsByJmbg(String jmbg);

    // Provera da li postoji vozač sa datim brojem vozačke
    boolean existsByBrojVozacke(String brojVozacke);
}