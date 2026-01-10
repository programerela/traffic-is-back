package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Zahtev;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Signalizacija;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ZahtevRepository extends JpaRepository<Zahtev, Integer> {

    // Pronalaženje zahteva po statusu
    List<Zahtev> findByStatus(String status);

    // Pronalaženje zahteva po tipu
    List<Zahtev> findByTipZahteva(String tipZahteva);

    // Pronalaženje zahteva po incidentu
    List<Zahtev> findByIncident(Incident incident);

    // Pronalaženje zahteva po signalizaciji
    List<Zahtev> findBySignalizacija(Signalizacija signalizacija);

    // Pronalaženje zahteva u vremenskom periodu
    List<Zahtev> findByDatumVremeBetween(LocalDateTime start, LocalDateTime end);

    // Pronalaženje aktivnih zahteva (primljeni ili u obradi)
    List<Zahtev> findByStatusIn(List<String> statusi);

    // Najnoviji zahtevi
    List<Zahtev> findTop10ByOrderByDatumVremeDesc();

    // Broj zahteva po statusu
    long countByStatus(String status);
}