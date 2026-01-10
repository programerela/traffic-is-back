package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Obavestenje;
import rs.ac.np.police.trafficis.model.Incident;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Integer> {

    // Pronalaženje obaveštenja po vrsti
    List<Obavestenje> findByVrsta(String vrsta);

    // Pronalaženje obaveštenja po prioritetu
    List<Obavestenje> findByPrioritet(String prioritet);

    // Pronalaženje obaveštenja po incidentu
    List<Obavestenje> findByIncident(Incident incident);

    // Pronalaženje obaveštenja u vremenskom periodu
    List<Obavestenje> findByDatumVremeBetween(LocalDateTime start, LocalDateTime end);

    // Pronalaženje hitnih obaveštenja
    List<Obavestenje> findByVrstaOrderByDatumVremeDesc(String vrsta);

    // Najnovija obaveštenja (poslednjih 10)
    List<Obavestenje> findTop10ByOrderByDatumVremeDesc();

    // Obaveštenja visokog prioriteta
    List<Obavestenje> findByPrioritetOrderByDatumVremeDesc(String prioritet);
}