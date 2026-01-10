package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Vozilo;
import rs.ac.np.police.trafficis.model.Vozac;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoziloRepository extends JpaRepository<Vozilo, Integer> {

    // Pronalaženje vozila po registraciji
    Optional<Vozilo> findByRegistracija(String registracija);

    // Pronalaženje svih vozila po vozaču
    List<Vozilo> findByVozac(Vozac vozac);

    // Pronalaženje vozila po marki
    List<Vozilo> findByMarka(String marka);

    // Pronalaženje vozila po modelu
    List<Vozilo> findByModel(String model);

    // Provera da li postoji vozilo sa datom registracijom
    boolean existsByRegistracija(String registracija);
}