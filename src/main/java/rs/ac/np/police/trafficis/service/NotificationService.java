package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Obavestenje;

import java.util.List;

public interface NotificationService {

    // Automatsko kreiranje obaveštenja na osnovu incidenta
    Obavestenje createNotificationFromIncident(Incident incident);

    // Kreiranje manuelnog obaveštenja
    Obavestenje createManualNotification(String tekst, String vrsta, String prioritet);

    // Dobijanje svih aktivnih obaveštenja
    List<Obavestenje> getActiveNotifications();

    // Dobijanje obaveštenja po prioritetu
    List<Obavestenje> getNotificationsByPrioritet(String prioritet);

    // Brisanje obaveštenja
    void deleteNotification(Integer id);
}