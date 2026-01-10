package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Vozilo;
import rs.ac.np.police.trafficis.model.Vozac;

import java.util.List;
import java.util.Optional;

public interface VoziloService {

    // Kreiranje novog vozila
    Vozilo createVozilo(Vozilo vozilo);

    // Ažuriranje vozila
    Vozilo updateVozilo(Integer id, Vozilo vozilo);

    // Brisanje vozila
    void deleteVozilo(Integer id);

    // Pronalaženje vozila po ID-u
    Optional<Vozilo> getVoziloById(Integer id);

    // Pronalaženje vozila po registraciji
    Optional<Vozilo> getVoziloByRegistracija(String registracija);

    // Pronalaženje svih vozila
    List<Vozilo> getAllVozila();

    // Pronalaženje vozila po vozaču
    List<Vozilo> getVozilaByVozac(Vozac vozac);

    // Pronalaženje vozila po marki
    List<Vozilo> getVozilaByMarka(String marka);

    // Provera da li postoji vozilo sa datom registracijom
    boolean existsByRegistracija(String registracija);
}