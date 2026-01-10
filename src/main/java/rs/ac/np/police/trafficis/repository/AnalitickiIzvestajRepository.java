package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.AnalitickiIzvestaj;
import rs.ac.np.police.trafficis.model.Korisnik;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnalitickiIzvestajRepository extends JpaRepository<AnalitickiIzvestaj, Integer> {

    // Pronalaženje izveštaja po tipu analitike
    List<AnalitickiIzvestaj> findByTipAnalitike(String tipAnalitike);

    // Pronalaženje izveštaja po korisniku
    List<AnalitickiIzvestaj> findByKorisnik(Korisnik korisnik);

    // Pronalaženje izveštaja u vremenskom periodu
    List<AnalitickiIzvestaj> findByDatumGenerisanjaBetween(LocalDateTime start, LocalDateTime end);

    // Najnoviji izveštaji
    List<AnalitickiIzvestaj> findTop10ByOrderByDatumGenerisanjaDesc();

    // Pronalaženje izveštaja po tipu i korisniku
    List<AnalitickiIzvestaj> findByTipAnalitikeAndKorisnik(String tipAnalitike, Korisnik korisnik);
}