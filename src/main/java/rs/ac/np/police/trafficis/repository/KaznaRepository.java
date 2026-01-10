package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Kazna;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Incident;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface KaznaRepository extends JpaRepository<Kazna, Integer> {

    // Pronalaženje kazni po vozaču
    List<Kazna> findByVozac(Vozac vozac);

    // Pronalaženje kazni po incidentu
    List<Kazna> findByIncident(Incident incident);

    // Pronalaženje kazni po statusu plaćanja
    List<Kazna> findByStatusPlacanja(String statusPlacanja);

    // Pronalaženje kazni po vrsti prekršaja
    List<Kazna> findByVrstaPrekrsaja(String vrstaPrekrsaja);

    // Pronalaženje neplaćenih kazni
    List<Kazna> findByStatusPlacanjaAndVozac(String statusPlacanja, Vozac vozac);

    // Pronalaženje kazni u određenom vremenskom periodu
    List<Kazna> findByDatumIzdavanjaBetween(LocalDate start, LocalDate end);

    // Ukupan iznos neplaćenih kazni za vozača
    @Query("SELECT SUM(k.iznos) FROM Kazna k WHERE k.vozac = :vozac AND k.statusPlacanja = 'nije plaćena'")
    BigDecimal sumNeplacenihKazni(@Param("vozac") Vozac vozac);

    // Broj kazni po vozaču
    long countByVozac(Vozac vozac);
}