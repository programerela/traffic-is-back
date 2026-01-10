package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Obavestenje;
import rs.ac.np.police.trafficis.repository.ObavestenjeRepository;
import rs.ac.np.police.trafficis.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final ObavestenjeRepository obavestenjeRepository;

    @Autowired
    public NotificationServiceImpl(ObavestenjeRepository obavestenjeRepository) {
        this.obavestenjeRepository = obavestenjeRepository;
    }

    @Override
    public Obavestenje createNotificationFromIncident(Incident incident) {
        Obavestenje obavestenje = new Obavestenje();
        obavestenje.setIncident(incident);
        obavestenje.setDatumVreme(LocalDateTime.now());

        // Automatsko generisanje teksta i prioriteta na osnovu težine incidenta
        switch (incident.getTezinaIncidenta().toLowerCase()) {
            case "sa poginulima":
                obavestenje.setTekst("HITNO: Saobraćajna nesreća sa smrtnim ishodom na lokaciji " +
                        incident.getLokacija() + ". Saobraćaj je obustavljen.");
                obavestenje.setVrsta("hitno");
                obavestenje.setPrioritet("visok");
                break;
            case "sa povređenima":
                obavestenje.setTekst("Saobraćajna nesreća sa povređenim licima na lokaciji " +
                        incident.getLokacija() + ". Očekuje se spora vožnja.");
                obavestenje.setVrsta("saobraćajno upozorenje");
                obavestenje.setPrioritet("visok");
                break;
            case "veći":
                obavestenje.setTekst("Saobraćajna nesreća na lokaciji " +
                        incident.getLokacija() + ". Saobraćaj otežan.");
                obavestenje.setVrsta("saobraćajno upozorenje");
                obavestenje.setPrioritet("srednji");
                break;
            default: // manji
                obavestenje.setTekst("Manji saobraćajni incident na lokaciji " +
                        incident.getLokacija());
                obavestenje.setVrsta("obaveštenje");
                obavestenje.setPrioritet("nizak");
        }

        return obavestenjeRepository.save(obavestenje);
    }

    @Override
    public Obavestenje createManualNotification(String tekst, String vrsta, String prioritet) {
        Obavestenje obavestenje = new Obavestenje();
        obavestenje.setTekst(tekst);
        obavestenje.setVrsta(vrsta);
        obavestenje.setPrioritet(prioritet);
        obavestenje.setDatumVreme(LocalDateTime.now());

        return obavestenjeRepository.save(obavestenje);
    }

    @Override
    public List<Obavestenje> getActiveNotifications() {
        // Obaveštenja iz poslednja 24 sata
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        return obavestenjeRepository.findByDatumVremeBetween(cutoff, LocalDateTime.now());
    }

    @Override
    public List<Obavestenje> getNotificationsByPrioritet(String prioritet) {
        return obavestenjeRepository.findByPrioritetOrderByDatumVremeDesc(prioritet);
    }

    @Override
    public void deleteNotification(Integer id) {
        if (!obavestenjeRepository.existsById(id)) {
            throw new RuntimeException("Obaveštenje sa ID " + id + " ne postoji");
        }
        obavestenjeRepository.deleteById(id);
    }
}