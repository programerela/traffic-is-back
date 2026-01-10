package rs.ac.np.police.trafficis.service;

import java.time.LocalDateTime;
import java.util.Map;

public interface AnalyticsService {

    // Statistika incidenata po težini
    Map<String, Long> getIncidentStatistikaPoTezini();

    // Statistika incidenata po lokaciji
    Map<String, Long> getIncidentStatistikaPoLokaciji();

    // Statistika incidenata po vremenskom periodu
    Map<String, Long> getIncidentStatistikaZaPeriod(LocalDateTime start, LocalDateTime end);

    // Statistika kazni po vrsti prekršaja
    Map<String, Long> getKaznaStatistikaPoVrstiPrekrsaja();

    // Statistika kazni po statusu plaćanja
    Map<String, Long> getKaznaStatistikaPoStatusu();

    // Statistika signalizacije po statusu
    Map<String, Long> getSignalizacijaStatistikaPoStatusu();

    // Top 10 lokacija sa najviše incidenata
    Map<String, Long> getTop10LokacijaSaIncidentima();

    // Top 10 vozača sa najviše kazni
    Map<String, Object> getTop10VozacaSaKaznama();

    // Mesečni izveštaj o incidentima
    Map<String, Object> getMesecniIzvestajIncidenata(int godina, int mesec);

    // Godišnji izveštaj o incidentima
    Map<String, Object> getGodisnjiiIzvestajIncidenata(int godina);

    // Trenutna statistika sistema (dashboard)
    Map<String, Object> getDashboardStatistika();
}