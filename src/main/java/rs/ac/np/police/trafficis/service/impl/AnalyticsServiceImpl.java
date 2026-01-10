package rs.ac.np.police.trafficis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.np.police.trafficis.model.Incident;
import rs.ac.np.police.trafficis.model.Kazna;
import rs.ac.np.police.trafficis.model.Vozac;
import rs.ac.np.police.trafficis.repository.*;
import rs.ac.np.police.trafficis.service.AnalyticsService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final IncidentRepository incidentRepository;
    private final KaznaRepository kaznaRepository;
    private final SignalizacijaRepository signalizacijaRepository;
    private final VozacRepository vozacRepository;
    private final ZahtevRepository zahtevRepository;

    @Autowired
    public AnalyticsServiceImpl(IncidentRepository incidentRepository,
                                KaznaRepository kaznaRepository,
                                SignalizacijaRepository signalizacijaRepository,
                                VozacRepository vozacRepository,
                                ZahtevRepository zahtevRepository) {
        this.incidentRepository = incidentRepository;
        this.kaznaRepository = kaznaRepository;
        this.signalizacijaRepository = signalizacijaRepository;
        this.vozacRepository = vozacRepository;
        this.zahtevRepository = zahtevRepository;
    }

    @Override
    public Map<String, Long> getIncidentStatistikaPoTezini() {
        List<Incident> incidenti = incidentRepository.findAll();
        return incidenti.stream()
                .collect(Collectors.groupingBy(
                        Incident::getTezinaIncidenta,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getIncidentStatistikaPoLokaciji() {
        List<Incident> incidenti = incidentRepository.findAll();
        return incidenti.stream()
                .collect(Collectors.groupingBy(
                        Incident::getLokacija,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getIncidentStatistikaZaPeriod(LocalDateTime start, LocalDateTime end) {
        List<Incident> incidenti = incidentRepository.findByDatumVremeBetween(start, end);
        return incidenti.stream()
                .collect(Collectors.groupingBy(
                        Incident::getTezinaIncidenta,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getKaznaStatistikaPoVrstiPrekrsaja() {
        List<Kazna> kazne = kaznaRepository.findAll();
        return kazne.stream()
                .collect(Collectors.groupingBy(
                        Kazna::getVrstaPrekrsaja,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getKaznaStatistikaPoStatusu() {
        List<Kazna> kazne = kaznaRepository.findAll();
        return kazne.stream()
                .collect(Collectors.groupingBy(
                        Kazna::getStatusPlacanja,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getSignalizacijaStatistikaPoStatusu() {
        Map<String, Long> statistika = new HashMap<>();
        statistika.put("ispravna", signalizacijaRepository.countByStatus("ispravna"));
        statistika.put("u kvaru", signalizacijaRepository.countByStatus("u kvaru"));
        statistika.put("u održavanju", signalizacijaRepository.countByStatus("u održavanju"));
        return statistika;
    }

    @Override
    public Map<String, Long> getTop10LokacijaSaIncidentima() {
        List<Incident> incidenti = incidentRepository.findAll();
        return incidenti.stream()
                .collect(Collectors.groupingBy(
                        Incident::getLokacija,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public Map<String, Object> getTop10VozacaSaKaznama() {
        List<Vozac> vozaci = vozacRepository.findAll();
        List<Map<String, Object>> top10 = new ArrayList<>();

        for (Vozac vozac : vozaci) {
            long brojKazni = kaznaRepository.countByVozac(vozac);
            if (brojKazni > 0) {
                Map<String, Object> vozacInfo = new HashMap<>();
                vozacInfo.put("vozac", vozac.getIme() + " " + vozac.getPrezime());
                vozacInfo.put("brojKazni", brojKazni);
                vozacInfo.put("iznosNeplacenih", kaznaRepository.sumNeplacenihKazni(vozac));
                top10.add(vozacInfo);
            }
        }

        top10.sort((a, b) -> Long.compare((Long) b.get("brojKazni"), (Long) a.get("brojKazni")));

        Map<String, Object> rezultat = new HashMap<>();
        rezultat.put("top10Vozaca", top10.stream().limit(10).collect(Collectors.toList()));
        return rezultat;
    }

    @Override
    public Map<String, Object> getMesecniIzvestajIncidenata(int godina, int mesec) {
        LocalDateTime start = LocalDateTime.of(godina, mesec, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusSeconds(1);

        List<Incident> incidenti = incidentRepository.findByDatumVremeBetween(start, end);

        Map<String, Object> izvestaj = new HashMap<>();
        izvestaj.put("period", mesec + "/" + godina);
        izvestaj.put("ukupanBrojIncidenata", incidenti.size());
        izvestaj.put("poTezini", incidenti.stream()
                .collect(Collectors.groupingBy(Incident::getTezinaIncidenta, Collectors.counting())));
        izvestaj.put("poStatusu", incidenti.stream()
                .collect(Collectors.groupingBy(Incident::getStatusIncidenta, Collectors.counting())));

        return izvestaj;
    }

    @Override
    public Map<String, Object> getGodisnjiiIzvestajIncidenata(int godina) {
        LocalDateTime start = LocalDateTime.of(godina, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(godina, 12, 31, 23, 59, 59);

        List<Incident> incidenti = incidentRepository.findByDatumVremeBetween(start, end);

        Map<String, Object> izvestaj = new HashMap<>();
        izvestaj.put("godina", godina);
        izvestaj.put("ukupanBrojIncidenata", incidenti.size());
        izvestaj.put("poTezini", incidenti.stream()
                .collect(Collectors.groupingBy(Incident::getTezinaIncidenta, Collectors.counting())));
        izvestaj.put("poMesecima", incidenti.stream()
                .collect(Collectors.groupingBy(
                        i -> i.getDatumVreme().getMonthValue(),
                        Collectors.counting()
                )));

        return izvestaj;
    }

    @Override
    public Map<String, Object> getDashboardStatistika() {
        Map<String, Object> dashboard = new HashMap<>();

        // Ukupan broj entiteta
        dashboard.put("ukupnoIncidenata", incidentRepository.count());
        dashboard.put("ukupnoKazni", kaznaRepository.count());
        dashboard.put("ukupnoVozaca", vozacRepository.count());
        dashboard.put("ukupnoSignalizacija", signalizacijaRepository.count());

        // Aktivni elementi
        dashboard.put("aktivniIncidenti", incidentRepository.findByStatusIncidenta("evidentiran").size());
        dashboard.put("neplaceneKazne", kaznaRepository.findByStatusPlacanja("nije plaćena").size());
        dashboard.put("neispravnaSignalizacija", signalizacijaRepository.findNeispravne().size());
        dashboard.put("aktivniZahtevi", zahtevRepository.countByStatus("primljen") +
                zahtevRepository.countByStatus("u obradi"));

        // Najnoviji incidenti
        dashboard.put("najnovijiIncidenti", incidentRepository.findTop10ByOrderByDatumVremeDesc());

        return dashboard;
    }
}