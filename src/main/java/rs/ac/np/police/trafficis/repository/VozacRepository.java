package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Vozac;

import java.util.Optional;

@Repository
public interface VozacRepository extends JpaRepository<Vozac, Integer> {

    // Pronalaženje vozača po JMBG
    Optional<Vozac> findByJmbg(String jmbg);

    // Pronalaženje vozača po broju vozačke dozvole
    Optional<Vozac> findByBrojVozacke(String brojVozacke);

    // Provera da li postoji vozač sa datim JMBG
    boolean existsByJmbg(String jmbg);

    // Provera da li postoji vozač sa datim brojem vozačke
    boolean existsByBrojVozacke(String brojVozacke);
}