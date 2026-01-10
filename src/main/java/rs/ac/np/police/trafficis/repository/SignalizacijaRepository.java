package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Signalizacija;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SignalizacijaRepository extends JpaRepository<Signalizacija, Integer> {

    // Pronalaženje signalizacije po statusu
    List<Signalizacija> findByStatus(String status);

    // Pronalaženje signalizacije po tipu
    List<Signalizacija> findByTipSignalizacije(String tipSignalizacije);

    // Pronalaženje signalizacije po lokaciji
    List<Signalizacija> findByLokacija(String lokacija);

    // Pronalaženje signalizacije u kvaru
    @Query("SELECT s FROM Signalizacija s WHERE s.status = 'u kvaru'")
    List<Signalizacija> findNeispravne();

    // Pronalaženje signalizacije koja treba da se proveri (starija od određenog datuma)
    List<Signalizacija> findByDatumPoslednjeProvereBefore(LocalDate datum);

    // Broj signalizacija po statusu
    long countByStatus(String status);
}