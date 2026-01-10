package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Zahtev;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Signalizacija;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ZahtevService {

    // Kreiranje novog zahteva
    Zahtev createZahtev(Zahtev zahtev);

    // Ažuriranje zahteva
    Zahtev updateZahtev(Integer id, Zahtev zahtev);

    // Brisanje zahteva
    void deleteZahtev(Integer id);

    // Pronalaženje zahteva po ID-u
    Optional<Zahtev> getZahtevById(Integer id);

    // Pronalaženje svih zahteva
    List<Zahtev> getAllZahtevi();

    // Pronalaženje zahteva po statusu
    List<Zahtev> getZahteviByStatus(String status);

    // Pronalaženje zahteva po tipu
    List<Zahtev> getZahteviByTip(String tipZahteva);

    // Pronalaženje zahteva po incidentu
    List<Zahtev> getZahteviByIncident(Incident incident);

    // Pronalaženje zahteva po signalizaciji
    List<Zahtev> getZahteviBySignalizacija(Signalizacija signalizacija);

    // Pronalaženje aktivnih zahteva
    List<Zahtev> getAktivniZahtevi();

    // Najnoviji zahtevi
    List<Zahtev> getNajnovijiZahtevi();

    // Broj zahteva po statusu
    long countByStatus(String status);
}