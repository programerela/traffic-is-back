package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Kazna;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Incident;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface KaznaService {

    // Kreiranje nove kazne
    Kazna createKazna(Kazna kazna);

    // Ažuriranje kazne
    Kazna updateKazna(Integer id, Kazna kazna);

    // Brisanje kazne
    void deleteKazna(Integer id);

    // Pronalaženje kazne po ID-u
    Optional<Kazna> getKaznaById(Integer id);

    // Pronalaženje svih kazni
    List<Kazna> getAllKazne();

    // Pronalaženje kazni po vozaču
    List<Kazna> getKazneByVozac(Vozac vozac);

    // Pronalaženje kazni po incidentu
    List<Kazna> getKazneByIncident(Incident incident);

    // Pronalaženje kazni po statusu plaćanja
    List<Kazna> getKazneByStatusPlacanja(String statusPlacanja);

    // Pronalaženje neplaćenih kazni za vozača
    List<Kazna> getNeplaceneKazneByVozac(Vozac vozac);

    // Pronalaženje kazni po vrsti prekršaja
    List<Kazna> getKazneByVrstaPrekrsaja(String vrstaPrekrsaja);

    // Ukupan iznos neplaćenih kazni za vozača
    BigDecimal getSumNeplacenihKazni(Vozac vozac);

    // Broj kazni po vozaču
    long countKazneByVozac(Vozac vozac);
}