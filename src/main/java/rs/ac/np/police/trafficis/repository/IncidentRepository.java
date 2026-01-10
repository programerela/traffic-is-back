package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Vozilo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    // Pronalaženje incidenata po statusu
    List<Incident> findByStatusIncidenta(String statusIncidenta);

    // Pronalaženje incidenata po težini
    List<Incident> findByTezinaIncidenta(String tezinaIncidenta);

    // Pronalaženje incidenata po lokaciji
    List<Incident> findByLokacija(String lokacija);

    // Pronalaženje incidenata po vozaču
    List<Incident> findByVozac(Vozac vozac);

    // Pronalaženje incidenata po vozilu
    List<Incident> findByVozilo(Vozilo vozilo);

    // Pronalaženje incidenata u vremenskom periodu
    List<Incident> findByDatumVremeBetween(LocalDateTime start, LocalDateTime end);

    // Pronalaženje incidenata po lokaciji i vremenskom periodu
    List<Incident> findByLokacijaAndDatumVremeBetween(String lokacija, LocalDateTime start, LocalDateTime end);

    // Broj incidenata po težini
    @Query("SELECT COUNT(i) FROM Incident i WHERE i.tezinaIncidenta = :tezina")
    long countByTezina(@Param("tezina") String tezina);

    // Najnoviji incidenti
    List<Incident> findTop10ByOrderByDatumVremeDesc();
}