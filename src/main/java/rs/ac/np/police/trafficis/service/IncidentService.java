package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Vozilo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IncidentService {

    // Kreiranje novog incidenta
    Incident createIncident(Incident incident);

    // Ažuriranje incidenta
    Incident updateIncident(Integer id, Incident incident);

    // Brisanje incidenta
    void deleteIncident(Integer id);

    // Pronalaženje incidenta po ID-u
    Optional<Incident> getIncidentById(Integer id);

    // Pronalaženje svih incidenata
    List<Incident> getAllIncidenti();

    // Pronalaženje incidenata po statusu
    List<Incident> getIncidentiByStatus(String statusIncidenta);

    // Pronalaženje incidenata po težini
    List<Incident> getIncidentiByTezina(String tezinaIncidenta);

    // Pronalaženje incidenata po lokaciji
    List<Incident> getIncidentiByLokacija(String lokacija);

    // Pronalaženje incidenata po vozaču
    List<Incident> getIncidentiByVozac(Vozac vozac);

    // Pronalaženje incidenata po vozilu
    List<Incident> getIncidentiByVozilo(Vozilo vozilo);

    // Pronalaženje incidenata u vremenskom periodu
    List<Incident> getIncidentiByPeriod(LocalDateTime start, LocalDateTime end);

    // Najnoviji incidenti
    List<Incident> getNajnovijiIncidenti();

    // Broj incidenata po težini
    long countByTezina(String tezina);
}