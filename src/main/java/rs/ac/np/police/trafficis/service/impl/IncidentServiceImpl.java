package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.model.Vozilo;
import rs.ac.np.police.trafficis.repository.IncidentRepository;
import rs.ac.np.police.trafficis.service.IncidentService;
import rs.ac.np.police.trafficis.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final NotificationService notificationService;

    @Autowired
    public IncidentServiceImpl(IncidentRepository incidentRepository,
                               NotificationService notificationService) {
        this.incidentRepository = incidentRepository;
        this.notificationService = notificationService;
    }

    @Override
    public Incident createIncident(Incident incident) {
        if (incident.getDatumVreme() == null) {
            incident.setDatumVreme(LocalDateTime.now());
        }
        if (incident.getStatusIncidenta() == null) {
            incident.setStatusIncidenta("evidentiran");
        }

        // Sačuvaj incident
        Incident savedIncident = incidentRepository.save(incident);

        // Automatski kreiraj obaveštenje za javnost
        notificationService.createNotificationFromIncident(savedIncident);

        return savedIncident;
    }

    @Override
    public Incident updateIncident(Integer id, Incident incident) {
        Optional<Incident> postojeciIncident = incidentRepository.findById(id);
        if (postojeciIncident.isEmpty()) {
            throw new RuntimeException("Incident sa ID " + id + " ne postoji");
        }
        incident.setIdIncidenta(id);
        return incidentRepository.save(incident);
    }

    @Override
    public void deleteIncident(Integer id) {
        if (!incidentRepository.existsById(id)) {
            throw new RuntimeException("Incident sa ID " + id + " ne postoji");
        }
        incidentRepository.deleteById(id);
    }

    @Override
    public Optional<Incident> getIncidentById(Integer id) {
        return incidentRepository.findById(id);
    }

    @Override
    public List<Incident> getAllIncidenti() {
        return incidentRepository.findAll();
    }

    @Override
    public List<Incident> getIncidentiByStatus(String statusIncidenta) {
        return incidentRepository.findByStatusIncidenta(statusIncidenta);
    }

    @Override
    public List<Incident> getIncidentiByTezina(String tezinaIncidenta) {
        return incidentRepository.findByTezinaIncidenta(tezinaIncidenta);
    }

    @Override
    public List<Incident> getIncidentiByLokacija(String lokacija) {
        return incidentRepository.findByLokacija(lokacija);
    }

    @Override
    public List<Incident> getIncidentiByVozac(Vozac vozac) {
        return incidentRepository.findByVozac(vozac);
    }

    @Override
    public List<Incident> getIncidentiByVozilo(Vozilo vozilo) {
        return incidentRepository.findByVozilo(vozilo);
    }

    @Override
    public List<Incident> getIncidentiByPeriod(LocalDateTime start, LocalDateTime end) {
        return incidentRepository.findByDatumVremeBetween(start, end);
    }

    @Override
    public List<Incident> getNajnovijiIncidenti() {
        return incidentRepository.findTop10ByOrderByDatumVremeDesc();
    }

    @Override
    public long countByTezina(String tezina) {
        return incidentRepository.countByTezina(tezina);
    }
}